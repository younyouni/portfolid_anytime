package net.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberPasswordUpdateProcess implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		MemberDAO dao = new MemberDAO();
		String userid = (String) request.getSession().getAttribute("userid");
		String password = request.getParameter("oldpassword");
		String newpassword = request.getParameter("password");

		int passwordcheck = dao.isPassword(userid, password);
		System.out.println(passwordcheck);

		if (passwordcheck == 0) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}

		System.out.println("비밀번호 수정 액션");
		MemberDAO mdao = new MemberDAO();
		Member newm = new Member();
		int result = 0;

		newm.setUserid(userid);
		newm.setPassword(newpassword);

		System.out.println("newpassword = " + newm.getPassword());

		result = mdao.passwordUpdate(newm);

		if (result == 0) {
			System.out.println("비밀번호 수정 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "비밀번호 수정이 되지 않았습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(); // 세션을 얻어옵니다.
		session.invalidate();
		out.println("<meta http-equiv='Content-Type' content='text/html;charset=utf-8' />");
		out.println("<script>");
		out.println("alert('비밀번호 수정이 완료되었습니다.');");
		out.println("location.href='anytime.com'");
		out.println("</script>");
		out.close();

		System.out.println("비밀번호 수정 완료");
		System.out.println("newpassword = " + newm.getPassword());

		return null;
	}

}
