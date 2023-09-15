package net.post.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;


public class PostWriteAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//데이터베이스 작업을 수행하는 BoardDAO클래스의 인스턴스 생성
		BoardDAO boarddao = new BoardDAO();
		//
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String board_name = boarddao.getBoard_name(board_num);
		request.setAttribute("board_num", board_num);
		request.setAttribute("board_name", board_name);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false); // 포워딩 방식으로 주소가 바뀌지 않아요
		forward.setPath("post/postWrite.jsp");
		return forward;
	}

}
