package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   //getSession() 메서드를 사용하여 현재 요청과 관련된 HttpSession 객체를 가져옵니다
	    HttpSession session = request.getSession(); // 세션을 얻어옵니다.
	    
        // 세션을 무효화하여 로그아웃 처리합니다.
        session.invalidate();

        // 로그아웃 후에는 로그인 페이지로 이동하도록 설정합니다.
        ActionForward forward = new ActionForward();
        forward.setRedirect(true);
        forward.setPath("login.com");
        return forward;
        
	}

}
