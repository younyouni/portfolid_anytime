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

public class CommentUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("comments update action start");
		CommentDAO dao = new CommentDAO();
		Comment co = new Comment();

		co.setContent(request.getParameter("content"));
		System.out.println("content = " + co.getContent());

		co.setComments_num(Integer.parseInt(request.getParameter("comments_num")));

		int ok = dao.commentsUpdate(co);
		response.getWriter().print(ok);

		return null;
	}

}
