package net.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.post.db.PostBean;

public class BoardDAO {
	private DataSource ds;

	// 생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public BoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}

	}

	public BoardBean getDetail(int num) {
		BoardBean board = null;

		String sql = "SELECT * FROM BOARD " + "WHERE BOARD_NUM = " + "(SELECT BOARD_NUM FROM POST "
				+ "WHERE POST_NUM = ?)";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, num);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					board = new BoardBean();
					board.setBoard_num(rs.getInt("BOARD_NUM"));
					board.setSchool_num(rs.getInt("SCHOOL_NUM"));
					board.setNum(rs.getInt("NUM"));
					board.setType(rs.getInt("TYPE"));
					board.setName(rs.getString("NAME"));
					board.setContent(rs.getString("CONTENT"));
					board.setAnonymous(rs.getInt("ANONYMOUS"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return board;
	}

	public int boardlistcount(int user_num) {
		int boardlistcount = 0;
		String sql = "select count(*) from board where num = ?";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, user_num);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					boardlistcount = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardlistcount;
	}

	public List<BoardBean> getBoardList(int user_num) {
		List<BoardBean> boardlist = new ArrayList<BoardBean>();
		String sql = "select name, approval from board where num =?";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, user_num);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					BoardBean board = new BoardBean();
					board.setName(rs.getString("name"));
					board.setApproval(rs.getString("approval"));
					boardlist.add(board);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getBoardList() 에러 : " + ex);
		}
		return boardlist;
	}

	public String getBoard_name(int board_num) {
		String board_name = null;
		String sql = "select name from board where board_num = ?";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, board_num);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					board_name = rs.getString("NAME");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board_name;
	}

	public int[] getBoardNums(int school_num) {
	
		String sql = "SELECT BOARD_NUM FROM BOARD "
				+ "	  WHERE TYPE = 1 AND SCHOOL_NUM = ? "
				+ "	  AND APPROVAL ='Y' AND NAME !='공지사항' ";
		
		int[] board_nums = null;
		int i = 0;

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, school_num);

			try (ResultSet rs = pstmt.executeQuery()) {
				List<Integer> boardNumList = new ArrayList<>(); 
				while (rs.next()) {
					boardNumList.add(rs.getInt("BOARD_NUM"));
				}
				// 리스트를 배열로 변환
				board_nums = new int[boardNumList.size()];
				for (Integer boardNum : boardNumList) {
					board_nums[i++] = boardNum;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return board_nums;
	}

	public int insertBoard(BoardBean board) {
		int result = 0;
		String sql = "	insert into board (	board_num, school_num, num, 								"
				+ "							type, name, content, anonymous, 							"
				+ "							approval)													"
				+ "		values			  (	board_seq.nextval,?,?,										"
				+ "							?,?,?,?,													"
				+ "							? )															";
		String updatesql = "update member set board_admin = 1 where num = ?";
		String approvalByType = null;

		if (board.getType() == 2 || board.getType() == 3) {
			approvalByType = "N";
		} else if (board.getType() == 4) {
			approvalByType = "Y";
		}

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				PreparedStatement pstmt_update = con.prepareStatement(updatesql);) {

			con.setAutoCommit(false); // 트랜잭션 시작

			pstmt.setInt(1, board.getSchool_num());
			pstmt.setInt(2, board.getNum());
			pstmt.setInt(3, board.getType());
			pstmt.setString(4, board.getName());
			pstmt.setString(5, board.getContent());
			pstmt.setInt(6, board.getAnonymous());
			pstmt.setString(7, approvalByType);

			result = pstmt.executeUpdate();

			if (result > 0 && board.getType() == 4) {
				pstmt_update.setInt(1, board.getNum());

				int updateResult = pstmt_update.executeUpdate();
				if (updateResult > 0) {
					con.commit();
				} else {
					System.out.println("유저 관리자 권한 수정 실패");
					con.rollback();
					
				}
			} else if (board.getType() == 2 || board.getType() == 3) {
				con.commit();
			} else {
				System.out.println("게시판 생성 실패");
				con.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<BoardBean> BoardList (int school_num){
		List<BoardBean> boardlist = new ArrayList<BoardBean>();
		
		String sql = "select * from board where school_num = ? ";
		
		try (Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			pstmt.setInt(1, school_num);
			
			
			
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					BoardBean board = new BoardBean();
					
					board.setBoard_num(rs.getInt("BOARD_NUM"));
					board.setSchool_num(rs.getInt("SCHOOL_NUM"));
					board.setNum(rs.getInt("NUM"));
					board.setType(rs.getInt("TYPE"));
					board.setName(rs.getString("NAME"));
					board.setContent(rs.getString("CONTENT"));
					board.setAnonymous(rs.getInt("ANONYMOUS"));
					board.setApproval(rs.getString("approval"));			
					
					boardlist.add(board);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardlist;
	}

	
}
