package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberForgotAction_password_Emailcheck implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
        String userid = request.getParameter("userid");
		
		HttpSession session = request.getSession();
		session.setAttribute("userid", userid);
		
		
		forward.setRedirect(false); // 주소 변경없이 jsp페이지의 내용을 보여줍니다.
		forward.setPath("member/forgotPassword_mailcheck.jsp");
		return forward; 
	   
	}

}
