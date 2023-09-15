package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.Member;
import net.member.db.MemberDAO;

//checkpassword.com
public class MemberPasswordCheckAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		String userid = (String) request.getSession().getAttribute("userid");
		Member m = dao.member_info(userid);
		
		int result = dao.isPassword(m.getUserid(), request.getParameter("password"));
		response.getWriter().print(result);
		System.out.println(result);
		return null;
	}

}
