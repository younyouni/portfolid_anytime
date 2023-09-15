package net.comment.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.post.action.Action;
import net.post.action.ActionForward;

public class CommentDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int comments_num = Integer.parseInt(request.getParameter("comments_num"));

		net.comment.db.CommentDAO dao = new net.comment.db.CommentDAO();

		int result = dao.commentDelete(comments_num);

		PrintWriter out = response.getWriter();
		out.println(result);
		out.close();

		return null;
	}

}
