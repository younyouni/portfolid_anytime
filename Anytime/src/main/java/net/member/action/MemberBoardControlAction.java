package net.member.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;
import net.member.db.MemberDAO;

public class MemberBoardControlAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		String userid = (String) request.getSession().getAttribute("userid");
		MemberDAO mdao = new MemberDAO();
		BoardDAO bdao = new BoardDAO();
		List<BoardBean> boardlist = null;

		int user_num = mdao.findNum(userid);
		int boardCount = bdao.boardlistcount(user_num);

		if (boardCount > 0) {
			boardlist = bdao.getBoardList(user_num);
		}

		request.setAttribute("boardlist", boardlist);
		request.setAttribute("boardCount", boardCount);
		forward.setPath("member/boardControl.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
