package net.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberJoinProcessAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
	
		// 추가된 부분: 세션에서 enter_year와 campus_name 값을 가져옵니다.
        HttpSession session = request.getSession();
        String enterYear = (String) session.getAttribute("enter_year");
        String campusName = (String) session.getAttribute("campus_name");

		
		
		String userid = request.getParameter("userid");
		String nickname = request.getParameter("nickname");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String address_num=request.getParameter("address_num");
		String address1 =request.getParameter("address1");
		String address2 =request.getParameter("address2");
		String phone_num = request.getParameter("phone_num");
		
		// 새로 추가된 enter_year 값을 받아오기_ session으로 불러오기
		int admission_year = Integer.parseInt(enterYear);
		
		Member m=new Member();
	
        m.setUserid(userid);
        m.setNickname(nickname);
        m.setPassword(password);
        m.setEmail(email);
        m.setAddress_num(address_num);
        m.setAddress1(address1);
        m.setAddress2(address2);
        m.setPhone_num(phone_num);
        m.setAdmission_year(admission_year);
      
		
        MemberDAO mdao=new MemberDAO();
        
        // 새로 추가된 campus_name 값을 받아오기_ session로 불러오기
        String campus_name = campusName;
		
		
		
		//MemberDAO에서 campus_name넣고 school_num 받아오기
		int school_num = mdao.getSchoolNumByName(campus_name);
		m.setSchool_num(school_num);

		int result= mdao.insert(m);
		//글씨 깨짐 방지
		response.setContentType("text/html;charset=utf-8");
		
		//result=0;
		if (result == 0) {
		    System.out.println("회원 가입 실패입니다.");
		    // JavaScript를 사용하여 alert 창 띄우기
		    String script = "<script>alert('회원 가입 실패입니다.');</script>";
		    response.getWriter().write(script);
		    // JavaScript를 사용하여 이전 화면으로 돌아가기
		    String goBackScript = "<script>history.back();</script>";
		    response.getWriter().write(goBackScript);
		    // forward 대신 null을 반환하거나, 아무 작업도 하지 않고 함수를 종료해도 됩니다.
		    return null;
		}
		
		PrintWriter out=response.getWriter();
		out.println("<script>");
		if(result==1) { //삽입이 된 경우
			out.println("alert('회원 가입을 축하합니다.');");
			out.println("location.href='login.com';");
		}
		out.println("</script>");
		out.close();
		return null;
		
	}
	
}
