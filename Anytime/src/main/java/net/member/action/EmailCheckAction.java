package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmailCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward=new ActionForward();
		
		    request.setAttribute("enter_year", request.getParameter("enter_year"));
		    request.setAttribute("campus_name", request.getParameter("campus_name"));
		    // 다음 코드에서 사용할 수 있습니다.
		   
			forward.setRedirect(false); // 주소 변경없이 jsp페이지의 내용을 보여줍니다.
			forward.setPath("member/mailcheck.jsp");
			return forward; 
		    
	}

}
