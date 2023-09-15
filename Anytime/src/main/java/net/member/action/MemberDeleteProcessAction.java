package net.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class MemberDeleteProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDAO mdao = new MemberDAO();
		int passwordcheck = 0;
		String userid = (String) request.getSession().getAttribute("userid");
		String password = request.getParameter("password");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("회원 탈퇴 액션 시작");

		passwordcheck = mdao.isPassword(userid, password);

		if (passwordcheck == 0) {
			out.println("<script>");
			out.println("alert('현재 비밀번호와 일치하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
			return null;
		}

		int result = mdao.delete(userid);

		if (result == 1) {
			out.println("<script>");
			out.println("alert('회원 탈퇴가 정상적으로 처리되었습니다.')");
			out.println("location.href='anytime.com'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원 탈퇴가 실패하었습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		out.close();

		return null;
	}

}
