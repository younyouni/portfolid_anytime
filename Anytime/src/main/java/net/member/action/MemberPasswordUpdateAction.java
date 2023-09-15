package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberPasswordUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		String userid = (String) request.getSession().getAttribute("userid");
		MemberDAO mdao = new MemberDAO();
		Member m = mdao.member_info(userid);
		
		request.setAttribute("member", m);
		forward.setPath("member/changePassword.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
