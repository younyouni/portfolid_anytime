package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MemberAgreementAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		
		String enter_year = request.getParameter("enter_year");
	    String campus_name = request.getParameter("campus_name");

	    HttpSession session = request.getSession();
	    session.setAttribute("enter_year", enter_year);
	    session.setAttribute("campus_name", campus_name);
	    
	   
		forward.setRedirect(false); // 주소 변경없이 jsp페이지의 내용을 보여줍니다.
		forward.setPath("member/agreement.jsp");
		return forward;
	}
}
