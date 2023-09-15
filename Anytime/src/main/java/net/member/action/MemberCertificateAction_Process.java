package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberCertificateAction_Process implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		//세션으로 아이디 불러오기 
		HttpSession session = request.getSession();
		String userid =(String)session.getAttribute("userid");		
		
		MemberDAO mdao = new MemberDAO();
		boolean result = mdao.updateMember(userid);
		
		if (result) {
    		forward.setRedirect(true);
    		forward.setPath("MainBoard.bo");
    	} else {
    		forward = null;
    	}
    	
    	return forward;
    }

}
