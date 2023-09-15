package net.comment.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.comment.db.CommentDAO;
import net.post.action.Action;
import net.post.action.ActionForward;

public class CommentListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CommentDAO cdao = new CommentDAO();
		int post_num = Integer.parseInt(request.getParameter("post_num"));
		System.out.println(post_num);

		JsonArray jarray = cdao.getCommentList(post_num);

		JsonObject object = new JsonObject();

		JsonElement je = new Gson().toJsonTree(jarray);
		object.add("postlist", je);

		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(object.toString());
		System.out.println(object.toString());
		return null;
	}

}
