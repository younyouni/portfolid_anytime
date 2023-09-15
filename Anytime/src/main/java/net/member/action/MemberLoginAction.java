package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberLoginAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userid ="";
		
		Cookie[] cookies = request.getCookies(); //현재 HTTP 요청에서 전송된 쿠키들을 가져옵니다. 
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("userid")) {
					System.out.println(userid);
					userid = cookies[i].getValue();
				}
			}
		}
         
		
		//보여주는 view페이지
		request.setAttribute("userid", userid);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false); // 주소 변경없이 jsp페이지의 내용을 보여줍니다.
		forward.setPath("member/loginForm.jsp");
		return forward;
	}
}
