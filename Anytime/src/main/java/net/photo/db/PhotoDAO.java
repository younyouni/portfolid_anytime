package net.photo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PhotoDAO {
	private DataSource ds;

	// 생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public PhotoDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}

	}

	public ArrayList<String> getPaths(int num) {
		ArrayList<String> paths = new ArrayList<>();
		String sql = "SELECT PATH FROM PHOTO WHERE POST_NUM = ?";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, num);

			try (ResultSet rs = pstmt.executeQuery()) {
				
				while (rs.next()) {
					paths.add(rs.getString("PATH")); 
	                
//    	                
//    	            }
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return paths;
	}
}