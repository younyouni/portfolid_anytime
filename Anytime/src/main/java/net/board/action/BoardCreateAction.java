package net.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;
import net.member.db.MemberDAO;

public class BoardCreateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardDAO bdao = new BoardDAO();
		BoardBean board = new BoardBean();
		MemberDAO mdao = new MemberDAO();

		int school_num = Integer.parseInt(request.getParameter("school_num"));
		System.out.println("school_num : " + school_num);

		String userid = request.getParameter("userid");
		int member_num = mdao.findNum(userid);
		System.out.println("member_num : " + member_num);

		int board_type = Integer.parseInt(request.getParameter("board_type"));
		System.out.println("board_type : " + board_type);

		String board_name = request.getParameter("board_name");
		System.out.println("board_name : " + board_name);

		String board_content = request.getParameter("board_content");
		System.out.println("board_content : " + board_content);

		int board_anony = Integer.parseInt(request.getParameter("board_anony"));
		System.out.println("board_anony : " + board_anony);

		String board_purpose = request.getParameter("board_purpose");
		System.out.println("board_purpose : " + board_purpose);

		board.setSchool_num(school_num);
		board.setNum(member_num);
		board.setType(board_type);
		board.setName(board_name);
		board.setContent(board_content);
		board.setAnonymous(board_anony);
		board.setPurpose(board_purpose);

		int result = bdao.insertBoard(board);
		if (result == 0) {
			System.out.println("게시판 생성 실패입니다.");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시판 생성에 실패했습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if (result == 1) {
			out.println("alert('게시판이 생성되었습니다. 단체/학과 게시판은, 승인 심사에 통과되면 바로 이용가능합니다.');");
			out.println("location.href='boardControl.com'");
		}
		out.println("</script>");
		out.close();

		response.getWriter().print(result);
		return null;
	}

}
