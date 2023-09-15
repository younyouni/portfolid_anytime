package net.post.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;
import net.member.db.MemberDAO;
import net.photo.db.PhotoDAO;
import net.post.db.PostBean;
import net.post.db.PostDAO;
import net.school.db.School;

public class PostModifyView implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		PostDAO postdao = new PostDAO();
		PostBean postdata = new PostBean();
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();
		PhotoDAO photodao = new PhotoDAO();
		MemberDAO memberdao = new MemberDAO();
		
		List<BoardBean> boardlist = new ArrayList<BoardBean>();
		
		HttpSession session = request.getSession();
		School school = (School) session.getAttribute("school");
		int school_num = school.getSchool_num();
		int board_num = 0;
		if (request.getParameter("board_num") != null) {
			board_num = Integer.parseInt(request.getParameter("board_num"));
		}
		
		boardlist = boarddao.BoardList(school_num);
		System.out.println("가져온 boardlist = " + boardlist);
		
		request.setAttribute("boardlist", boardlist);

		// 파라미터로 전달받은 수정할 글 번호를 num변수에 저장합니다.
		int post_num = Integer.parseInt(request.getParameter("post_num"));

		// 글 내용을 불러와서 postdata객체에 저장합니다.
		postdata = postdao.getDetail(post_num);
		boarddata = boarddao.getDetail(post_num);
		ArrayList<String> photodata = photodao.getPaths(post_num);
		// 글 내용 불러오기 실패한 경우입니다.
		if (postdata == null) {
			System.out.println("(수정)상세보기 실패");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "게시판 수정 상세 보기 실패입니다.");
		}
		System.out.println("(수정)상세보기 성공");
		System.out.println("view : "+postdata.getSubject());

		// 수정 폼 페이지로 이동할 때 원문 글 내용을 보여주기 때문에 postdata 객체를
		// request 객체에 저장합니다.
		request.setAttribute("postdata", postdata);
		request.setAttribute("boarddata", boarddata);
		request.setAttribute("paths", photodata);
		forward.setRedirect(false);
		// 글 수정 폼 페이지로 이동하기위해 경로를 설정합니다.
		forward.setPath("post/postModifyView.jsp");
		return forward;
	}
}
