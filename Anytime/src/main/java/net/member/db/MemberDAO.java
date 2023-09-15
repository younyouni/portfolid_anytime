package net.member.db;

import java.net.URLDecoder;
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

public class MemberDAO {
	private DataSource ds;

	// 생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}

	public int isId(String userid) {
		int result = -1; // DB에 해당 id가 없습니다.
		String sql = "select userid from member where userid= ? ";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, userid);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					result = 0; // DB에 해당 id가 있습니다.
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	} // isId end

	public int isId(String userid, String pass) {
		int result = -1;// 아이디가 존재하지 않습니다.
		String sql = "select userid, password from member where userid = ? ";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, userid);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					if (rs.getString(2).equals(pass)) {
						result = 1; // 아이디와 비밀번호가 일치하지 않는 경우
					} else {
						result = 0; // 비밀번호가 일치하지 않는 경우
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insert(Member m) {
		int result = 0;
		String sql = " INSERT INTO MEMBER " + "(num, userid, password, email, nickname, address_num, "
				+ " address1, address2, phone_num, admission_year, school_num, school_check, board_admin, account_status) "
				+ " VALUES(member_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, m.getUserid());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getNickname());
			pstmt.setString(5, m.getAddress_num());
			pstmt.setString(6, m.getAddress1());
			pstmt.setString(7, m.getAddress2());
			pstmt.setString(8, m.getPhone_num());
			pstmt.setInt(9, m.getAdmission_year());
			pstmt.setInt(10, m.getSchool_num());
			pstmt.setString(11, "N");
			pstmt.setInt(12, 0);
			pstmt.setInt(13, 1);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}// insert end

	public int getSchoolNumByName(String campus_name) {
		int num = -1;
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT school_num FROM school WHERE school_name = ?")) {
			pstmt.setString(1, campus_name);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt("school_num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	public int isNickname(String nickname) {
		int result = -1; // DB에 해당 id가 없습니다.
		String sql = "select nickname from member where nickname= ? ";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, nickname);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					result = 0; // DB에 해당 id가 있습니다.
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//이메일로 userid 불러오기
	public String getUserIdByEmail(String email) {
		String userid = null;

		String sql = "select userid from member where email = ? ";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, email);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					userid = rs.getString("userid");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userid;
	}

	
	//불러온 uerid로 password 업데이트하기
	public boolean updatePasswordByUserid(String userid, String password) {

		boolean result = false;
		String sql = "update member set password = ? where userid = ?";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, password);
			pstmt.setString(2, userid);

			if (pstmt.executeUpdate() == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//학교 웹메일 인증시 school_check 값 Y로 바꾸기
	public boolean updateMember(String userid) {
	    boolean result = false;
	    String sql = "UPDATE member SET school_check = ? WHERE userid = ?";
	    try (Connection con = ds.getConnection(); 
	    		PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setString(1, "Y");
	        pstmt.setString(2, userid);
	        if (pstmt.executeUpdate() == 1) {
	            result = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
	
	
	public Member member_info(String userid) { // password 제외하고 받는 것으로 수정 필요
		Member m = null;
		String sql = "select * from member where userid=?";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, userid);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					m = new Member();
					m.setNum(rs.getInt(1));
					m.setSchool_num(rs.getInt(2));
					m.setUserid(rs.getString(3));
					m.setNickname(rs.getString(4));
					m.setPassword(rs.getString(5));
					m.setEmail(rs.getString(6));
					m.setSchool_check(rs.getString(7));
					m.setAddress_num(rs.getString(8));
					m.setAddress1(rs.getString(9));
					m.setAddress2(rs.getString(10));
					m.setPhone_num(rs.getString(11));
					m.setBoard_admin(rs.getInt(12));
					m.setAccount_status(rs.getInt(13));
					m.setAdmission_year(rs.getInt(14));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}

	// => 스쿨 객체 정보 갖고 가는 것으로 수정되어 필요없을 시, 삭제 예정
	public String school_name(String userid) {
		String sql = "	select s.school_name " + "		from member m join school s " + "		using(school_num) "
				+ "		where school_num = (select school_num " + "							from member "
				+ "							where userid = ?)";
		String school_name = "";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, userid);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					school_name = rs.getString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return school_name;
	}

	public int isPassword(String userid, String oldpassword) {
		int result = -1;

		String sql = " 	select password " + "		from member" + "		where userid =?";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, userid);
			System.out.println(oldpassword);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					if (oldpassword.equals(rs.getString(1)))
						result = 1; // 기존 비밀번호를 옳게 입력한 경우
					else
						result = 0; // 기존 비밀번호를 옳게 입력하지 않은 경우
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int passwordUpdate(int num, String encodedPassword) {
		int result = 0;
		String sql = "update member set password = ? where num = ?";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			String decodedPassword = URLDecoder.decode(encodedPassword, "UTF-8");
			System.out.println("Decoded Password: " + decodedPassword);
			pstmt.setString(1, decodedPassword);
			pstmt.setInt(2, num);

			result = pstmt.executeUpdate();
			if (result == 1)
				System.out.println("데이터 수정 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int passwordUpdate(Member newm) {
		int result = 0;
		String sql = "update member set password= ? where userid =? ";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, newm.getPassword());
			pstmt.setString(2, newm.getUserid());

			result = pstmt.executeUpdate();
			if (result == 1)
				System.out.println("데이터 수정 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int update(Member newm) {
		int result = 0;
		String sql = "	update member set email = ?, nickname = ?, "
				+ "		address_num = ?, address1 = ?, address2 =?, phone_num = ? " + " 	where userid = ?";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, newm.getEmail());
			pstmt.setString(2, newm.getNickname());
			pstmt.setString(3, newm.getAddress_num());
			pstmt.setString(4, newm.getAddress1());
			pstmt.setString(5, newm.getAddress2());
			pstmt.setString(6, newm.getPhone_num());
			pstmt.setString(7, newm.getUserid());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int delete(String userid) {
		int result = 0;
		String sql = "delete from member where userid = ?";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, userid);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("delete() 에러 : " + e);
		}
		return result;
	}

	public int findNum(String userid) {
		int num = 0;

		String sql = "select num from member where userid =? ";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, userid);

			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					num = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("findNum() 에러 : " + e);
		}
		return num;
	}

	public String getNickname(int writerNum) {
		String sql = "select nickname from member where num = ?";
		String nickname = "";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, writerNum);
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					nickname = rs.getString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getNickname() 에러 : " + e);
		}
		return nickname;
	}



	

}
// class end
