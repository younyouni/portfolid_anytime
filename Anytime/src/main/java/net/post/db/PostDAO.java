package net.post.db;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PostDAO {

	private DataSource ds;

	// 생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public PostDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}

	}

	// post 테이블의 게시물 개수를 가져오는 메서드
	public int getListCount() {
		String sql = "select count(*) from post";
		int x = 0;
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					x = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount() 에러: " + ex);
		}
		return x;
	} // getListcount() end

	public List<PostBean> getPostList(int page, int limit) { // 매개변수 2개짜리
		// page : 페이지
		// limit : 페이지 당 목록의 수
		// board_re_ref desc, board_re_seq asc에 의해 정렬한 것을
		// 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문입니다.

		String sql = "select * " + "   from ( select rownum rnum, j.* "
				+ "       from(select post.*, nvl(cnt,0) as COMMENT_COUNT "
				+ "               from post left outer join (select post_num, count(*) cnt "
				+ "                                      from comments"
				+ "                                       group by post_num) sub_C "
				+ "               on post.post_num = sub_C.post_num" + "               order by post.post_num desc) j "
				+ "         where rownum <= ? " + "      ) " + " where rnum>=? and rnum <= ?";

		List<PostBean> list = new ArrayList<PostBean>();
		// 한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지 ...
		int startrow = (page - 1) * limit + 1; // 읽기 시작할 row 번호(1 11 21 31 ...)
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호(10 20 30 40 ...)
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, endrow);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);

			try (ResultSet rs = pstmt.executeQuery()) {
				// DB에서 가져온 데이터를 BoardBean에 담습니다.
				while (rs.next()) {
					PostBean post = new PostBean();
					post.setPost_num(rs.getInt("POST_NUM"));
					post.setBoard_num(rs.getInt("BOARD_NUM"));
					post.setNum(rs.getInt("NUM"));
					post.setSubject(rs.getString("SUBJECT"));
					post.setContent(rs.getString("CONTENT"));
					post.setFile_count(rs.getInt("FILE_COUNT"));
					post.setPost_date(rs.getString("POST_DATE"));
					post.setLike_count(rs.getInt("LIKE_COUNT"));
					post.setScrap_count(rs.getInt("SCRAP_COUNT"));
					post.setReport_count(rs.getInt("REPORT_COUNT"));
					post.setComment_count(rs.getInt("COMMENT_COUNT"));

					list.add(post); // 값을 담은 객체를 리스트에 저장합니다.
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getPostList() 에러 : " + ex);
		}
		return list;
	}

	public int getListCount(int board_num) { // 매개변수 1개짜리
		String sql = "select count(*) from post where board_num = ? ";
		int x = 0;
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, board_num);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					x = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount() 에러: " + ex);
		}
		return x;
	} // getListcount() end

	
	/**********************************************이 아래로 이종옥 작성 내용**************************************************/
	
	// 해당 board_num을 가진 게시물 리스트를 페이지 수에 따라 내용을 갖고가는 메서드
	public List<PostBean> getPostList(int board_num, int page, int limit) { // 매개변수 3개짜리
		// page : 페이지
		// limit : 페이지 당 목록의 수
		// board_re_ref desc, board_re_seq asc에 의해 정렬한 것을
		// 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문입니다.

		String sql = "SELECT * " + " FROM (SELECT ROWNUM RNUM, j.* "
				+ "       FROM (SELECT post.*, NVL(cnt, 0) AS COMMENT_COUNT " + "               FROM post "
				+ "               LEFT OUTER JOIN (SELECT post_num, COUNT(*) cnt "
				+ "                                FROM comments "
				+ "                                GROUP BY post_num) sub_C "
				+ "               ON post.post_num = sub_C.post_num " + "               WHERE post.board_num = ? "
				+ "               ORDER BY post.post_num DESC) j " + "         WHERE ROWNUM <= ? " + "      ) "
				+ " WHERE RNUM >= ? AND RNUM <= ?";

		List<PostBean> list = new ArrayList<PostBean>();
		// 한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지 ...
		int startrow = (page - 1) * limit + 1; // 읽기 시작할 row 번호(1 11 21 31 ...)
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호(10 20 30 40 ...)
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, board_num);
			pstmt.setInt(2, endrow);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					String nickname = findNickname(rs.getInt("NUM"));
					String boardname = findBoardname(rs.getInt("BOARD_NUM"));
					
					if(boardAnonymousCheck(rs.getInt("BOARD_NUM")) == 1) {
						nickname = "익명";
					}
					
					PostBean post = new PostBean();
					post.setPost_num(rs.getInt("POST_NUM"));
					post.setBoard_num(rs.getInt("BOARD_NUM"));
					post.setNum(rs.getInt("NUM"));
					post.setSubject(rs.getString("SUBJECT"));
					post.setContent(rs.getString("CONTENT"));
					post.setFile_count(rs.getInt("FILE_COUNT"));
					post.setPost_date(rs.getString("POST_DATE"));
					post.setLike_count(rs.getInt("LIKE_COUNT"));
					post.setScrap_count(rs.getInt("SCRAP_COUNT"));
					post.setReport_count(rs.getInt("REPORT_COUNT"));
					post.setComment_count(rs.getInt("COMMENT_COUNT"));
					post.setNickname(nickname);
					post.setBoardname(boardname);
					list.add(post); // 값을 담은 객체를 리스트에 저장합니다.
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getPostList() 에러 : " + ex);
		}
		return list;
	}
	// 해당 board_num을 가진 게시물 리스트를 검색필터, 검색어에 해당하는 내용을 페이지 수에 따라 갖고 가는 메서드
	public List<PostBean> getPostList(int board_num, String search_field, 
			String search_word, int page, int limit) {
		int a = 0;
		String keyword = "%" + search_word + "%";

		String sql = "SELECT * " 
				+ " FROM (SELECT ROWNUM RNUM, j.* "
				+ "       FROM (SELECT post.*, NVL(cnt, 0) AS COMMENT_COUNT " 
				+ "               FROM post "
				+ "               LEFT OUTER JOIN (SELECT post_num, COUNT(*) cnt "
				+ "                                FROM comments "
				+ "                                GROUP BY post_num) sub_C "
				+ "               ON post.post_num = sub_C.post_num " 
				+ "               WHERE post.board_num = ? ";

		if ("subject".equals(search_field)) {
			a = 1;
			sql += " AND post.subject LIKE ? ";
		} else if ("content".equals(search_field)) {
			a = 1;
			sql += " AND post.content LIKE ? ";
		} else if ("all".equals(search_field)) {
			a = 2;
			sql += " AND (post.subject LIKE ? OR post.content LIKE ?) ";
		}

		sql += " ORDER BY post.post_num DESC) j " 
				+ "         WHERE ROWNUM <= ? " 
				+ "      ) "
				+ " WHERE RNUM >= ? AND RNUM <= ?";

		List<PostBean> list = new ArrayList<PostBean>();
		// 한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지 ...
		int startrow = (page - 1) * limit + 1; // 읽기 시작할 row 번호(1 11 21 31 ...)
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호(10 20 30 40 ...)
		try (Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			if (a == 1) {
				pstmt.setInt(1, board_num);
				pstmt.setString(2, keyword);
				pstmt.setInt(3, endrow);
				pstmt.setInt(4, startrow);
				pstmt.setInt(5, endrow);
			} else if (a == 2) {
				pstmt.setInt(1, board_num);
				pstmt.setString(2, keyword);
				pstmt.setString(3, keyword);
				pstmt.setInt(4, endrow);
				pstmt.setInt(5, startrow);
				pstmt.setInt(6, endrow);
			}
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					String nickname = findNickname(rs.getInt("NUM"));
					String boardname = findBoardname(rs.getInt("BOARD_NUM"));
					
					if(boardAnonymousCheck(rs.getInt("BOARD_NUM")) == 1) {
						nickname = "익명";
					}
					PostBean post = new PostBean();
					post.setPost_num(rs.getInt("POST_NUM"));
					post.setBoard_num(rs.getInt("BOARD_NUM"));
					post.setNum(rs.getInt("NUM"));
					post.setSubject(rs.getString("SUBJECT"));
					post.setContent(rs.getString("CONTENT"));
					post.setFile_count(rs.getInt("FILE_COUNT"));
					post.setPost_date(rs.getString("POST_DATE"));
					post.setLike_count(rs.getInt("LIKE_COUNT"));
					post.setScrap_count(rs.getInt("SCRAP_COUNT"));
					post.setReport_count(rs.getInt("REPORT_COUNT"));
					post.setComment_count(rs.getInt("COMMENT_COUNT"));
					post.setNickname(nickname);
					post.setBoardname(boardname);
					list.add(post); // 값을 담은 객체를 리스트에 저장합니다.
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getPostList() 에러 : " + ex);
		}
		return list;
	}
	
	// school_num에 해당하는 board_num들을 가져가는 메서드
	public List<PostBean> findSchoolBoardlistcount(int school_num){
		
		List<PostBean> bnc = new ArrayList<PostBean>();
		
		String sql = "select board_num from board where school_num = ? ";
		
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, school_num);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					PostBean list = new PostBean();
					
					list.setBoard_num(rs.getInt("BOARD_NUM"));
					
					bnc.add(list);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bnc;
	}
	
		// 해당 보드번호 리스트에 키워드와 검색어에 해당하는 게시물들의 총 수를 구하는 메서드
		public int getSearchListCount(List<PostBean> bnc, String search_word) { // search에 사용하는 검색어에 관한 게시물의 총 갯수
			
			String qm = "";
			for(int i = 0; i < bnc.size(); i++) {
	       	 	qm += "?,";
	        }
			qm = qm.substring(0, qm.length() - 1);
			
			
			String sql = "SELECT COUNT(*) FROM post WHERE board_num in ("+ qm +") "
			           + "AND (subject LIKE ? OR content LIKE ?)";
			
			int x = 0;
			try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
				int i = 1;
				for (PostBean bncc : bnc) {
						pstmt.setInt(i++, bncc.getBoard_num());
					}
					pstmt.setString(i++, "%" + search_word + "%");
					pstmt.setString(i++, "%" + search_word + "%");

				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						x = rs.getInt(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("getListCount() 에러: " + ex);
			}
			return x;
		}


	// 갖고온 board_num 리스트들을 검색필터, 검색어와 페이지에 해당하는 내용들을 갖고가는 메서드
	public List<PostBean> getSearchPostList(List<PostBean> bnc, String search_word, int page, int limit) {
		// page : 페이지
		// limit : 페이지 당 목록의 수
		// board_re_ref desc, board_re_seq asc에 의해 정렬한 것을
		// 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문입니다.
		
		String keyword = "%" + search_word + "%";
		
		String qm = "";
		for(int i = 0; i < bnc.size(); i++) {
       	 	qm += "?,";
        }
		qm = qm.substring(0, qm.length() - 1);

		String sql = "SELECT * FROM (SELECT ROWNUM RNUM, j.* "
	             + "FROM (SELECT post.*, NVL(cnt, 0) AS COMMENT_COUNT " 
	             + "      FROM post "
	             + "      LEFT OUTER JOIN (SELECT post_num, COUNT(*) cnt "
	             + "                       FROM comments "
	             + "                       GROUP BY post_num) sub_C "
	             + "      ON post.post_num = sub_C.post_num "
	         	 + "      WHERE post.board_num IN (" + qm +") "
	         	 + "      AND (post.subject LIKE ? OR post.content LIKE ?) "
	             + "      ORDER BY post.post_num DESC) j "
	             + "WHERE ROWNUM <= ?) "
	             + "WHERE RNUM >= ? AND RNUM <= ?";

		List<PostBean> list = new ArrayList<PostBean>();
		// 한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지 ...
		int startrow = (page - 1) * limit + 1; // 읽기 시작할 row 번호(1 11 21 31 ...)
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호(10 20 30 40 ...)
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
				
			int i = 1;
			for (PostBean bncc : bnc) {
					pstmt.setInt(i++, bncc.getBoard_num());
				}
				pstmt.setString(i++, keyword);
				pstmt.setString(i++, keyword);
				pstmt.setInt(i++, endrow);
				pstmt.setInt(i++, startrow);
				pstmt.setInt(i++, endrow);

			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					String nickname = findNickname(rs.getInt("NUM"));
					String boardname = findBoardname(rs.getInt("BOARD_NUM"));
					
					if(boardAnonymousCheck(rs.getInt("BOARD_NUM")) == 1) {
						nickname = "익명";
					}
					
					PostBean post = new PostBean();
					post.setPost_num(rs.getInt("POST_NUM"));
					post.setBoard_num(rs.getInt("BOARD_NUM"));
					post.setNum(rs.getInt("NUM"));
					post.setSubject(rs.getString("SUBJECT"));
					post.setContent(rs.getString("CONTENT"));
					post.setFile_count(rs.getInt("FILE_COUNT"));
					post.setPost_date(rs.getString("POST_DATE"));
					post.setLike_count(rs.getInt("LIKE_COUNT"));
					post.setScrap_count(rs.getInt("SCRAP_COUNT"));
					post.setReport_count(rs.getInt("REPORT_COUNT"));
					post.setComment_count(rs.getInt("COMMENT_COUNT"));
					post.setNickname(nickname);
					post.setBoardname(boardname);
					list.add(post); // 값을 담은 객체를 리스트에 저장합니다.
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getPostList() 에러 : " + ex);
		}
		return list;
	}

	// 유저 번호에 해당하는 닉네임 찾기
	public String findNickname(int num) {
		String sql = "select nickname from member where num = ?";
		String nickname = "알 수 없음";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, num);
			try (ResultSet rs = pstmt.executeQuery()) {
					
				if (rs.next()) {
					nickname = rs.getString("NICKNAME");
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("findNickname() 에러 : " + ex);
		}
		return nickname;
	}
	
	// 보드 번호에 해당하는 게시판 이름 찾기
	public String findBoardname(int board_num) {
		String boardname = "";
		String sql = "select name from board where board_num = ? ";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, board_num);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					boardname = rs.getString("name");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardname;
	}
	
	// 보드 익명 여부 체크
	public int boardAnonymousCheck(int board_num) {
		int c = 0;
		String sql = "select anonymous from board where board_num = ? ";
		
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, board_num);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					c = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	// 해당 보드 번호에 키워드와 검색어에 해당하는 게시물들의 총 수를 구하는 메서드
	public int getListCount(int board_num, String search_field, String search_word) { // search에 사용하는 검색어에 관한 게시물의 총 갯수

		String sql = "select count(*) from post where board_num = ? ";
		int a = 0;
		if ("subject".equals(search_field)) {
			sql += "and subject like ?";
			a = 1;
		} else if ("content".equals(search_field)) {
			sql += "and content like ?";
			a = 1;
		} else if ("all".equals(search_field)) {
			sql += "and subject LIKE ? OR content LIKE ?";
			a = 2;
		}

		int x = 0;
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, board_num);
			if (a == 1) {
				pstmt.setString(2, "%" + search_word + "%");
			} else if (a == 2) {
				pstmt.setString(2, "%" + search_word + "%");
				pstmt.setString(3, "%" + search_word + "%");
			}

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					x = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount() 에러: " + ex);
		}
		return x;
	}

	public void setReadCountUpdate(int num) {
	}
	
	/**********************************************이 위로 이종옥 작성 내용**************************************************/
	

	// 게시물 내용 확인하는 메서드
	public PostBean getDetail(int num) {
		PostBean post = null;

		String sql = "SELECT * FROM post WHERE POST_NUM = ? ";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, num);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					post = new PostBean();
					post.setPost_num(rs.getInt("POST_NUM"));
					post.setBoard_num(rs.getInt("BOARD_NUM"));
					post.setNum(rs.getInt("NUM"));
					post.setSubject(rs.getString("SUBJECT"));
					post.setContent(rs.getString("CONTENT"));
					post.setFile_count(rs.getInt("FILE_COUNT"));
					post.setPost_date(rs.getString("POST_DATE"));
					post.setLike_count(rs.getInt("LIKE_COUNT"));
					post.setScrap_count(rs.getInt("SCRAP_COUNT"));
					post.setReport_count(rs.getInt("REPORT_COUNT"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return post;
	}

	/* ********** BY UNIUNI CREATED ********** */ // 공감수(like_count)를 통해 Hot게시물(공감수 10개이상)과 Best게시물(공감수 100개 이상)을 가져오는
													// 메소드 모음.

	// post 테이블의 게시물 개수를 가져오는 메서드 => 공감수가 10개 이상인 게시물의 개수를 가져오는 메소드
	
	public int getHotListCount() {
		String sql = " select count(*) from post " 
				   + " where like_count >= 10";
		
		int x = 0;
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					x = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount() 에러: " + ex);
		}
		return x;
	} // getListcount() end

	public List<PostBean> getHotList(int page, int limit) {

		String sql = "select * "
				+ "   from ( select rownum rnum, j.* "
				+ "       from(select post.*, nvl(cnt,0) as COMMENT_COUNT "
				+ "               from post left outer join (select post_num, count(*) cnt "
				+ "                            		    	from comments"
				+ "                              			group by post_num) sub_C "
				+ "               on post.post_num = sub_C.post_num" 
				+ "               where post.like_count >= 10 "
				+ "               order by post.post_num desc) j "
				+ "         where rownum <= ? " 
				+ "      ) "
				+ " where rnum >= ? and rnum <= ?";

		
		List<PostBean> list = new ArrayList<PostBean>();
		// 한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지 ...
		int startrow = (page - 1) * limit + 1; // 읽기 시작할 row 번호(1 11 21 31 ...)
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호(10 20 30 40 ...)
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, endrow);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);

			try (ResultSet rs = pstmt.executeQuery()) {
				// DB에서 가져온 데이터를 BoardBean에 담습니다.
				while (rs.next()) {
					PostBean post = new PostBean();
					post.setPost_num(rs.getInt("POST_NUM"));
					post.setBoard_num(rs.getInt("BOARD_NUM"));
					post.setNum(rs.getInt("NUM"));
					post.setSubject(rs.getString("SUBJECT"));
					post.setContent(rs.getString("CONTENT"));
					post.setFile_count(rs.getInt("FILE_COUNT"));
					post.setPost_date(rs.getString("POST_DATE"));
					post.setLike_count(rs.getInt("LIKE_COUNT"));
					post.setScrap_count(rs.getInt("SCRAP_COUNT"));
					post.setReport_count(rs.getInt("REPORT_COUNT"));
					post.setComment_count(rs.getInt("COMMENT_COUNT"));

					list.add(post); // 값을 담은 객체를 리스트에 저장합니다.
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getPostList() 에러 : " + ex);
		}
		return list;
	}

	public int getBestListCount() {
		String sql = " select count(*) from post " + " WHERE like_count >= 100 ";
		int x = 0;
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					x = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount() 에러: " + ex);
		}
		return x;
	} // getListcount() end

	public List<PostBean> getBestList(int page, int limit) {
		// page : 페이지
		// limit : 페이지 당 목록의 수
		// board_re_ref desc, board_re_seq asc에 의해 정렬한 것을
		// 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문입니다.

		String sql = "select * " + "   from ( select rownum rnum, j.* "
				+ "       from(select post.*, nvl(cnt,0) as COMMENT_COUNT "
				+ "               from post left outer join (select post_num, count(*) cnt "
				+ "                            		    from comments"
				+ "                              			group by post_num) sub_C "
				+ "               on post.post_num = sub_C.post_num" + "               where post.like_count >= 100 "
				+ "               order by post.post_num desc) j " + "         where rownum <= ? " + "      ) "
				+ " where rnum>=? and rnum <= ?";

		List<PostBean> list = new ArrayList<PostBean>();
		// 한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지 ...
		int startrow = (page - 1) * limit + 1; // 읽기 시작할 row 번호(1 11 21 31 ...)
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호(10 20 30 40 ...)
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, endrow);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);

			try (ResultSet rs = pstmt.executeQuery()) {
				// DB에서 가져온 데이터를 BoardBean에 담습니다.
				while (rs.next()) {
					PostBean post = new PostBean();
					post.setPost_num(rs.getInt("POST_NUM"));
					post.setBoard_num(rs.getInt("BOARD_NUM"));
					post.setNum(rs.getInt("NUM"));
					post.setSubject(rs.getString("SUBJECT"));
					post.setContent(rs.getString("CONTENT"));
					post.setFile_count(rs.getInt("FILE_COUNT"));
					post.setPost_date(rs.getString("POST_DATE"));
					post.setLike_count(rs.getInt("LIKE_COUNT"));
					post.setScrap_count(rs.getInt("SCRAP_COUNT"));
					post.setReport_count(rs.getInt("REPORT_COUNT"));
					post.setComment_count(rs.getInt("COMMENT_COUNT"));

					list.add(post); // 값을 담은 객체를 리스트에 저장합니다.
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getPostList() 에러 : " + ex);
		}
		return list;
	}

	// 학교별로(school_num) 공감수(like_count)가 10개 이상인 게시물을 가져오는 Hot게시물에 대한 메소드 모음
	public int getHotListCount(int school_num) {
		String sql = "	SELECT COUNT(*) "
				+ "		FROM POST P "
				+ "		JOIN BOARD B ON P.BOARD_NUM = B.BOARD_NUM "
				+ "		WHERE B.SCHOOL_NUM = ? AND P.like_count>=10 ";

		int count = 0;
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, school_num);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					count = rs.getInt(1);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getHotListCount() 에러:" + ex);
		}
		return count;
	}

	public List<PostBean> getHotList(int school_num, int page, int limit) {
		String sql = "select * " 
				+ "from(select rownum rnum, j.* "
				+ "    from(select post.POST_NUM, post.BOARD_NUM, post.NUM, "
				+ "			post.SUBJECT, post.CONTENT, post.FILE_COUNT, "
				+ "			TO_CHAR(post.post_date, 'MM-DD HH24:MI') post_date, "
				+ "			post.LIKE_COUNT, post.SCRAP_COUNT, post.REPORT_COUNT, "
				+ "			nvl(cnt,0) as COMMENT_COUNT, board.school_num " 
				+ "        from post "
				+ "        left outer join (select POST_NUM, count(*) cnt " 
				+ "                        from COMMENTS "
				+ "                        group by POST_NUM) sub_C " 
				+ "        on post.post_num = sub_C.post_num "
				+ "        join board on post.board_num = board.board_num "
				+ "        WHERE board.school_num = ? AND post.like_count >= 10 "
				+ "        order by post.post_num desc) j " 
				+ "    where rownum <= ? " 
				+ "    ) "
				+ "where rnum>=? and rnum <= ?";

		List<PostBean> list = new ArrayList<PostBean>();

		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		
		try (Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, school_num);
			pstmt.setInt(2, endrow);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					String nickname = findNickname(rs.getInt("NUM"));
					String boardname = findBoardname(rs.getInt("BOARD_NUM"));
					
					if(boardAnonymousCheck(rs.getInt("BOARD_NUM")) == 1) {
						nickname = "익명";
					}
					
					PostBean post = new PostBean();
					post.setPost_num(rs.getInt("POST_NUM"));
					post.setBoard_num(rs.getInt("BOARD_NUM"));
					post.setNum(rs.getInt("NUM"));
					post.setSubject(rs.getString("SUBJECT"));
					post.setContent(rs.getString("CONTENT"));
					post.setFile_count(rs.getInt("FILE_COUNT"));
					post.setPost_date(rs.getString("POST_DATE"));
					post.setLike_count(rs.getInt("LIKE_COUNT"));
					post.setScrap_count(rs.getInt("SCRAP_COUNT"));
					post.setReport_count(rs.getInt("REPORT_COUNT"));
					post.setComment_count(rs.getInt("COMMENT_COUNT"));
					post.setNickname(nickname);
					post.setBoardname(boardname);

					list.add(post); // 값을 담은 객체를 리스트에 저장합니다.
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getHotList() 에러 : " + ex);
		}
		return list;

	}

	// 학교별로(school_num) 공감수(like_count)가 10개 이상인 게시물을 가져오는 Best게시물에 대한 메소드 모음
	public int getBestListCount(int school_num) {
		String sql = "SELECT COUNT(*) " + "FROM POST P " + "JOIN BOARD B ON P.BOARD_NUM = B.BOARD_NUM "
				+ "WHERE B.SCHOOL_NUM = ? AND P.like_count>=100 ";

		int count = 0;
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, school_num);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					count = rs.getInt(1);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getHotListCount() 에러:" + ex);
		}
		return count;
	}

	public List<PostBean> getBestList(int school_num, int page, int limit) {
		String sql = "select * " + "from(select rownum rnum, j.* "
				+ "    from(select post.POST_NUM, post.BOARD_NUM, post.NUM, post.SUBJECT, post.CONTENT, post.FILE_COUNT, TO_CHAR(post.post_date, 'MM-DD HH24:MI') post_date, post.LIKE_COUNT, post.SCRAP_COUNT, post.REPORT_COUNT, nvl(cnt,0) as COMMENT_COUNT, board.school_num " 
				+ "        from post "
				+ "        left outer join (select POST_NUM, count(*) cnt " 
				+ "                        from COMMENTS "
				+ "                        group by POST_NUM) sub_C " 
				+ "        on post.post_num = sub_C.post_num "
				+ "        join board on post.board_num = board.board_num "
				+ "        WHERE board.school_num = ? AND post.like_count >= 100 "
				+ "        order by post.post_num desc) j " 
				+ "    where rownum <= ? " 
				+ "    ) "
				+ "where rnum>=? and rnum <= ?";

		List<PostBean> list = new ArrayList<PostBean>();

		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, school_num);
			pstmt.setInt(2, endrow);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					String nickname = findNickname(rs.getInt("NUM"));
					String boardname = findBoardname(rs.getInt("BOARD_NUM"));
					
					if(boardAnonymousCheck(rs.getInt("BOARD_NUM")) == 1) {
						nickname="익명";
					}

					PostBean post = new PostBean();
					post.setPost_num(rs.getInt("POST_NUM"));
					post.setBoard_num(rs.getInt("BOARD_NUM"));
					post.setNum(rs.getInt("NUM"));
					post.setSubject(rs.getString("SUBJECT"));
					post.setContent(rs.getString("CONTENT"));
					post.setFile_count(rs.getInt("FILE_COUNT"));
					post.setPost_date(rs.getString("POST_DATE"));
					post.setLike_count(rs.getInt("LIKE_COUNT"));
					post.setScrap_count(rs.getInt("SCRAP_COUNT"));
					post.setReport_count(rs.getInt("REPORT_COUNT"));
					post.setComment_count(rs.getInt("COMMENT_COUNT"));
					post.setNickname(nickname);
					post.setBoardname(boardname);

					list.add(post); // 값을 담은 객체를 리스트에 저장합니다.
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getHotList() 에러 : " + ex);
		}
		return list;

	}


	public boolean postInsert(PostBean postdata, String userid, String filename) {
		int post_num = 0;
		
		//POST_SEQ.nextVAL 시퀀스 구하기 - 1, 2, 3
		String max_sql  = "select POST_SEQ.nextVAL from DUAL";
		
		boolean result = false;

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(max_sql);) {
			con.setAutoCommit(false);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					post_num = rs.getInt(1);
				}
			
				
				postdata.setPost_num(post_num);
				
				postDataInsert(con, postdata, userid);
				
				if(filename!=null) {
				    photoDataInsert(con, post_num, filename);
				    
				}
				con.commit();
				con.setAutoCommit(true);
				result =  true;
			}catch(Exception e) {
				e.printStackTrace();
		          if (con != null) {
		             try {
		                con.rollback(); // rollback합니다.
		             } catch(Exception ex) {
		                ex.printStackTrace();
		             }
		          }
		       }
		} catch (Exception ex) {
			System.out.println("boardInsert() 에러: " + ex);
			ex.printStackTrace();
		}
		return result;
	}

	public boolean postDataInsert(Connection con, PostBean postdata, String userid) throws Exception{

		int result = 0;
		String num = "(select num from member where userid = '" + userid + "')";
		String sql = "INSERT INTO POST " + "VALUES(?, ?," + num + ", ?, ?," + "?, sysdate ,? ,? ,? )";
		
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, postdata.getPost_num());
			pstmt.setInt(2, postdata.getBoard_num());
			pstmt.setString(3, postdata.getSubject());
			pstmt.setString(4, postdata.getContent());
			pstmt.setInt(5, postdata.getFile_count()); // FILE_COUNT 필드
			pstmt.setInt(6, 0); // LIKE_COUNT 필드
			pstmt.setInt(7, 0); // SCRAP_COUNT 필드
			pstmt.setInt(8, 0); // REPORT_COUNT 필드

			result = pstmt.executeUpdate(); // 삽입 성공시 result는 1

			if (result == 1) {
				System.out.println("데이터 삽입이 모두 완료되었습니다.");
				return true;
			}

		}
		return false;
	}

	public boolean photoDataInsert(Connection con, int post_num,String filename) throws Exception {
		
		String sql = "INSERT INTO PHOTO " + "VALUES(PHOTO_SEQ.nextVAL," + post_num + ", ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			pstmt.setString(1, filename);

			int result = pstmt.executeUpdate(); // 삽입 성공시 result는 1

			if (result == 1) {
				System.out.println("데이터 삽입이 모두 완료되었습니다.");
				return true;
			}
		} 
		return false;
	}


	public ArrayList<PostBean> getPostSimpleList(int school_num, int board_num) {

		ArrayList<PostBean> list = new ArrayList<PostBean>();
		String sql = "	SELECT * 																	"
				+ "		FROM (	SELECT B.BOARD_NUM, B.NAME AS BOARD_NAME, P.SUBJECT, 				"
				+ "				TO_CHAR(P.POST_DATE, 'MM-DD HH24:MI') POST_DATE, P.POST_NUM			"
				+ "				FROM BOARD B 														"
				+ "				LEFT JOIN POST P ON B.BOARD_NUM = P.BOARD_NUM 						"
				+ "				WHERE B.SCHOOL_NUM = ? AND B.BOARD_NUM = ?							"
				+ "				AND B.TYPE = 1 AND B.APPROVAL = 'Y'									"
				+ "				ORDER BY P.POST_DATE DESC											"
				+ "			 ) 																		"
				+ "		WHERE ROWNUM <= 4";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, school_num);
			pstmt.setInt(2, board_num);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					PostBean post = new PostBean();
					post.setBoard_num(rs.getInt(1));
					post.setBoardname(rs.getString(2));
					post.setSubject(rs.getString(3));
					post.setPost_date(rs.getString(4));
					post.setPost_num(rs.getInt(5));

					list.add(post); // 값을 담은 객체를 리스트에 저장합니다.
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getPostList() 에러 : " + ex);
		}
		return list;
	}

	public List<ArrayList<PostBean>> getPostTotalList
											(int school_num, int[] board_nums) {

		List<ArrayList<PostBean>> totalList
			= new ArrayList<ArrayList<PostBean>>();
		
		for (int i = 0; i < board_nums.length; i++) {
			ArrayList<PostBean> list = getPostSimpleList(school_num, board_nums[i]);
			// 기존 리스트를 복사하여 새로운 객체로 추가
			totalList.add(new ArrayList<>(list)); 
		}
		return totalList; 
	}

	
	public boolean postModify(PostBean modifypost, String filename) {
		boolean result = false;
		try (Connection con = ds.getConnection();){
			con.setAutoCommit(false);
			try {
			postDataUpdate(con, modifypost);
			photoDataUpdate(con, modifypost.getPost_num(), filename);
			con.commit();
			con.setAutoCommit(true);
			result=true;
		   }catch(Exception e) {
			 e.printStackTrace();
	          if (con != null) {
	             try {
	                con.rollback(); // rollback합니다.
	             } catch(Exception ex) {
	                ex.printStackTrace();
	             }
	          }
	       }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	return result;
}
				
		
	private void photoDataUpdate(Connection con, int post_num, String filename)
			throws Exception {
		
		String select_sql = "SELECT * from photo where post_num=?";
		
		
		 try (PreparedStatement pstmt = con.prepareStatement(select_sql);) {
			 pstmt.setInt(1, post_num);
			 try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						photoDataUpdate2(con, post_num, filename);
					}else {
						photoDataInsert(con, post_num, filename);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("getPostList() 에러 : " + ex);
			}
			
		}
		
	private boolean photoDataUpdate2(Connection con, int post_num, String filename) 
			throws Exception {	
		    String sql = "UPDATE photo "
		               + "SET PATH= ? "
		               + "WHERE POST_NUM=? ";

	    try (PreparedStatement pstmt = con.prepareStatement(sql);) {
	        pstmt.setString(1, filename); // 파일 경로를 설정하는 대신 파일명을 설정하도록 변경했습니다.
	        pstmt.setInt(2, post_num);
	        int result = pstmt.executeUpdate();

	        if (result == 1) {
	            System.out.println("성공적으로 파일 업데이트");
	            return true;
	        }
	    }
	    return false;
	}
	

		private boolean postDataUpdate(Connection con, PostBean modifypost) 
				throws Exception{
		String sql = "UPDATE post "
				   + "SET SUBJECT=?, CONTENT=?, FILE_COUNT=? "
				   + "WHERE POST_NUM=? ";
		
		try( PreparedStatement pstmt = con.prepareStatement(sql);){
			 	pstmt.setString(1, modifypost.getSubject());
	            pstmt.setString(2, modifypost.getContent());
	            pstmt.setInt(3, modifypost.getFile_count());
	            pstmt.setInt(4, modifypost.getPost_num());
	            int result = pstmt.executeUpdate();
	            
	            if(result == 1) {
	                System.out.println("성공 업데이트");
	                return true;
	             }

		        
		      } 
		return false;
	}
		

	public int postDelete(int post_num) {
		int result = 0;

        String sql = "delete from post WHERE post_num = ?";
        try (Connection con = ds.getConnection();
		         PreparedStatement pstmt = con.prepareStatement(sql);){
            pstmt.setInt(1, post_num);
            result = pstmt.executeUpdate();

            if(result == 1)
				System.out.println("게시물이 삭제 되었습니다.");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("postDelete() 에러 : " + e);
		}
		return result;
	}




}