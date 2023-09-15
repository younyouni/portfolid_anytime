package net.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberUpdateProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		MemberDAO dao = new MemberDAO();
		Member m = new Member();
		String userid = (String) request.getSession().getAttribute("userid");
		String password = request.getParameter("password");

		int passwordcheck = dao.isPassword(userid, password);

		if (passwordcheck == 0) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}

		System.out.println("회원정보 수정 액션");
		MemberDAO mdao = new MemberDAO();
		Member newm = new Member();
		int result = 0;

		newm.setUserid(userid);
		newm.setPassword(password);
		newm.setEmail(request.getParameter("email"));
		newm.setNickname(request.getParameter("nickname"));
		newm.setAddress_num(request.getParameter("address_num"));
		newm.setAddress1(request.getParameter("address1"));
		newm.setAddress2(request.getParameter("address2"));
		newm.setPhone_num(request.getParameter("phone_num"));

		result = mdao.update(newm);
		
		if (result == 0) {
			System.out.println("회원정보 수정 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "회원정보 수정이 되지 않았습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<meta http-equiv='Content-Type' content='text/html;charset=utf-8' />");
		out.println("<script>");
		out.println("alert('회원정보 수정이 완료되었습니다.');");
		out.println("location.href='account.com'");
		out.println("</script>");
		out.close();

		System.out.println("회원정보 수정 완료");
		
		return null;
	}

}
