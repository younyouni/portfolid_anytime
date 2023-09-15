package net.comment.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.comment.db.Comment;
import net.comment.db.CommentDAO;
import net.member.db.MemberDAO;
import net.post.action.Action;
import net.post.action.ActionForward;

public class CommentAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("comments add action start");
		CommentDAO dao = new CommentDAO();

		Comment co = new Comment();
		MemberDAO mdao = new MemberDAO();

		String userid = request.getParameter("userid");
		int num = mdao.findNum(userid);

		co.setNum(num);
		co.setContent(request.getParameter("content"));
		System.out.println("content=" + co.getContent());

		co.setPost_num(Integer.parseInt(request.getParameter("post_num")));

		int ok = dao.commentsInsert(co);
		response.getWriter().print(ok);

		return null;
	}

}
