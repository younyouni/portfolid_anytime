package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberAccountAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		String userid = (String) request.getSession().getAttribute("userid");
		MemberDAO mdao = new MemberDAO();
		Member m = mdao.member_info(userid);

		if (m == null) {
			forward.setPath("error/error.jsp");
			forward.setRedirect(false);
			request.setAttribute("message", "아이디에 해당하는 정보가 없습니다.");
			return forward;
		}

		forward.setPath("/member/memberAccount.jsp");
		forward.setRedirect(false);
		request.setAttribute("member", m);
		return forward;
	}
}
