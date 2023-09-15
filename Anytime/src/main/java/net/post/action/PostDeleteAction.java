package net.post.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.post.db.PostDAO;

public class PostDeleteAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int post_num = Integer.parseInt(request.getParameter("post_num"));
		PostDAO postdao = new PostDAO();
		int result = postdao.postDelete(post_num);
		PrintWriter out = response.getWriter();
		out.print(result);
		out.close();
		return null;
		
	}
}
