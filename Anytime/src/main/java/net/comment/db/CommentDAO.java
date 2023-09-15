package net.comment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class CommentDAO {

	private DataSource ds;

	public CommentDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}

	}

	public JsonArray getCommentList(int post_num) {
		String sql = "	SELECT 	c.COMMENTS_NUM, c.NUM, c.CONTENT, c.LIKE_COUNT,				"
				+ "    			c.COMMENTS_DATE, c.RE_LEV, c.RE_SEQ, c.RE_REF, m.NICKNAME	"
				+ "		FROM COMMENTS c JOIN MEMBER m 										"
				+ "		ON c.NUM = m.NUM 													"
				+ "		WHERE c.POST_NUM = ? 												"
				+ "		ORDER BY c.RE_REF ASC, c.RE_SEQ ASC									";

		JsonArray array = new JsonArray();
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, post_num);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					JsonObject object = new JsonObject();
					object.addProperty("comments_num", rs.getInt(1));
					object.addProperty("num", rs.getInt(2));
					object.addProperty("content", rs.getString(3));
					object.addProperty("like_count", rs.getInt(4));
					object.addProperty("comments_date", rs.getString(5));
					object.addProperty("re_lev", rs.getInt(6));
					object.addProperty("re_seq", rs.getInt(7));
					object.addProperty("re_ref", rs.getInt(8));
					object.addProperty("nickname", rs.getString(9));
					object.addProperty("post_num", post_num);
					array.add(object);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			System.out.println(sql);
			System.out.println("getCommentList() 에러 : " + ex);
		}
		return array;
	}

	public int commentsInsert(Comment c) {
		int result = 0;
		String sql = "	insert into comments 																"
				+ "		(	COMMENTS_NUM, POST_NUM, NUM, 													"
				+ "			CONTENT, LIKE_COUNT, COMMENTS_DATE, 											"
				+ "			RE_REF, RE_LEV, RE_SEQ, REPORT_COUNT)											"
				+ "		values(	comments_seq.nextval, ?, ?, 												"
				+ "				?, 0, sysdate, 																"
				+ "				comments_seq.nextval, 0, 0, 0)												";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			// 새로운 글을 등록하는 부분입니다.
			pstmt.setInt(1, c.getPost_num());
			pstmt.setInt(2, c.getNum());
			pstmt.setString(3, c.getContent());
			result = pstmt.executeUpdate();
			if (result == 1)
				System.out.println("댓글 등록 완료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int commentsUpdate(Comment co) {
		int result = 0;
		String sql = "  update comments set content = ? where comments_num = ?";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, co.getContent());
			pstmt.setInt(2, co.getComments_num());

			result = pstmt.executeUpdate();
			if (result == 1)
				System.out.println("댓글이 수정 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int commentDelete(int comments_num) {
		int result = 0;
		String sql = "delete from comments where comments_num = ?";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, comments_num);
			result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("댓글이 삭제 되었습니다.");
			} // try 3
		} catch (Exception e) {
			System.out.println("commentDelete() 에러 : " + e);
			e.printStackTrace();
		}
		return result;
	}

	public int commentsReply(Comment c) {
		int result = 0;

		try (Connection con = ds.getConnection();) {
			con.setAutoCommit(false);

			try {
				reply_update(con, c.getRe_ref(), c.getRe_seq());
				result = reply_insert(con, c);
				con.commit();
				con.setAutoCommit(true);
			} catch (Exception e) {
				if (con != null) {
					try {
						con.rollback(); // rollback 합니다.
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private int reply_insert(Connection con, Comment c) {
		int result = 0;
		String sql = "  insert into COMMENTS (	COMMENTS_NUM, POST_NUM, NUM, 				"
				+ "								CONTENT, LIKE_COUNT, COMMENTS_DATE, 		"
				+ "								RE_REF, RE_LEV, RE_SEQ, REPORT_COUNT)		"
				+ "		values(	comments_seq.nextval, ?, ?, ?, 0, sysdate, ?, ?, ?, 0)		";

		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, c.getPost_num());
			System.out.println();
			pstmt.setInt(2, c.getNum());
			pstmt.setString(3, c.getContent());
			pstmt.setInt(4, c.getRe_ref());
			pstmt.setInt(5, c.getRe_lev() + 1);
			pstmt.setInt(6, c.getRe_seq() + 1);

			result = pstmt.executeUpdate();

			if (result == 1)
				System.out.println("대댓글 삽입 되었습니다.");
		} catch (Exception e) {
			System.out.println("reply_insert() 에러 : " + e);
			e.printStackTrace();
		}
		return result;
	}

	private void reply_update(Connection con, int re_ref, int re_seq) throws Exception {
		String update_sql = "	update comments 												"
				+ "				set	re_seq = re_seq + 1										"
				+ "				where re_ref = ?											"
				+ "				and re_seq > ? 												";
		try (PreparedStatement pstmt = con.prepareStatement(update_sql);) {
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			pstmt.executeUpdate();
		}

	}

}
