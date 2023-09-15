package net.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.Member;
import net.member.db.MemberDAO;
import net.school.db.School;
import net.school.db.SchoolDAO;

public class MemberLoginProcessAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		String userid = request.getParameter("userid");
		String pass = request.getParameter("password");
		MemberDAO mdao = new MemberDAO();
		SchoolDAO sdao = new SchoolDAO();
		int result = mdao.isId(userid, pass);
		System.out.println("결과는 " + result);

		// 로그인 성공
		if (result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("userid", userid);
			
			Member m = mdao.member_info(userid);
			School school = sdao.school_info(userid);
			if (school != null) {
				System.out.println("결과는 1");
			}
			session.setAttribute("member", m);
			session.setAttribute("school", school);

			String IDStore = request.getParameter("autologin");
			Cookie cookie = new Cookie("userid", userid);

			// ID 기억하기를 체크한 경우
			if (IDStore != null && IDStore.equals("store")) {
				// cookie.setMaxAge(60 * 60 * 24); //쿠키의 유효시간을 24시간으로 설정합니다.
				cookie.setMaxAge(60 * 60 * 24);
				// 클라이언트로 쿠키값을 전송합니다.
				response.addCookie(cookie);
				System.out.println("쿠키확인");
			} else {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}

			forward.setRedirect(true);
			forward.setPath("MainBoard.bo");
			return forward;
		} else {
			String message = "비밀번호가 일치하지 않습니다.";
			if (result == -1)
				message = "아이디가 존재하지 않습니다.";
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + message + "');");
			out.println("location.href='login.com';");
			out.println("</script>");
			out.close();
			return null;
		}
	}
}
