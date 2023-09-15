package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MemberJoinAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward=new ActionForward();
	

	    request.setAttribute("enter_year", request.getParameter("enter_year"));
	    request.setAttribute("campus_name", request.getParameter("campus_name"));

	    // 다음 코드에서 사용할 수 있습니다.
		
		
		forward.setRedirect(false);
		forward.setPath("member/joinForm.jsp");
		return forward;
	}
}
