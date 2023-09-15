package net.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberForgotAction_password_result_process implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		String password = request.getParameter("password");

	
		MemberDAO mdao = new MemberDAO();
		
		boolean result = mdao.updatePasswordByUserid(userid, password);
		
		//글씨 깨짐 방지
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
		if(result) {
			session.removeAttribute("userid");
			
			out.println("<script>");
			out.println("alert('비밀번호가 변경되었습니다.');");
			out.println("location.href='login.com';");
			out.println("</script>");
		}else {
			 System.out.println("비밀번호 변경 실패입니다.");
			    // JavaScript를 사용하여 alert 창 띄우기
			    String script = "<script>alert('비밀번호 변경 실패입니다.');</script>";
			    response.getWriter().write(script);
			    // JavaScript를 사용하여 이전 화면으로 돌아가기
			    String goBackScript = "<script>history.back();</script>";
			    response.getWriter().write(goBackScript);
			    // forward 대신 null을 반환하거나, 아무 작업도 하지 않고 함수를 종료해도 됩니다.
			    return null;
		}
		out.close();
		return null;
	}

}
