package net.comment.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.comment.db.Comment;
import net.comment.db.CommentDAO;
import net.member.db.MemberDAO;
import net.post.action.Action;
import net.post.action.ActionForward;

public class CommentReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommentDAO dao = new CommentDAO();
		Comment co = new Comment();
		MemberDAO mdao = new MemberDAO();

		System.out.println("comment reply action 시작");
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		int num = mdao.findNum(userid);
		System.out.println(Integer.parseInt(request.getParameter("post_num")));
		System.out.println(num);
		co.setPost_num(Integer.parseInt(request.getParameter("post_num")));
		co.setNum(num);
		co.setContent(request.getParameter("content"));
		co.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		co.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		co.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
		int ok = dao.commentsReply(co);
		response.getWriter().print(ok);
		return null;
	}

}
