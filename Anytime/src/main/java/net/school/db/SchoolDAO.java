package net.school.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SchoolDAO {
   private DataSource ds;
   
   
   public SchoolDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}


   public School school_info(String userid) {
      School s = null;

      String sql = "   select s.* "
            + "      from school s "
            + "      where s.school_num = (   select school_num "
            + "                        from member " 
            + "                        where userid = ?)";

      try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
         pstmt.setString(1, userid);
         try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
               s = new School();
               s.setSchool_num(rs.getInt(1));
               s.setSchool_name(rs.getString(2));
               s.setSchool_email(rs.getString(3));
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }	
      } catch (Exception e) {
         e.printStackTrace();
      }
      return s;
   }


public List<School> getSchoolList() {
  String sql ="select school_name, cnt "
	    	+ "from SCHOOL join(select school_num, count(*) cnt "
			+ "from member "
			+ "group by school_num) b	"			
			+ "on school.school_num = b.school_num"; 

	
	List<School> schools = new ArrayList<>();
	 try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                School school = new School();
	                school.setSchool_name(rs.getString("SCHOOL_NAME"));
	                school.setTotal_count(rs.getInt("cnt"));
	                schools.add(school);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return schools;
	}


public List<School> searchSchool() {
    //String sql = "SELECT SCHOOL_NUM, SCHOOL_NAME FROM SCHOOL WHERE SCHOOL_NAME LIKE ?";
	String sql = "SELECT SCHOOL_NUM, SCHOOL_NAME FROM SCHOOL ";
    List<School> schoolList = new ArrayList<>();
    try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
       // pstmt.setString(1, "%" + name + "%");
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                School school = new School();
                school.setSchool_num(rs.getInt("SCHOOL_NUM"));
                school.setSchool_name(rs.getString("SCHOOL_NAME"));
                schoolList.add(school);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return schoolList;
}

}
