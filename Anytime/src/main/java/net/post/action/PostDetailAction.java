package net.post.action;

import java.io.IOException;
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

public class PostDetailAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PostDAO postdao = new PostDAO();
		PostBean postdata = new PostBean();
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();
		PhotoDAO photodao = new PhotoDAO();
		MemberDAO memberdao = new MemberDAO();
		
		HttpSession session = request.getSession();
		School school = (School) session.getAttribute("school");
		int school_num = school.getSchool_num();
		int board_num = 0;
		if (request.getParameter("board_num") != null) {
			board_num = Integer.parseInt(request.getParameter("board_num"));
		}
		List<BoardBean> boardlist = new ArrayList<BoardBean>();
		
		boardlist = boarddao.BoardList(school_num);
		System.out.println("가져온 boardlist = " + boardlist);
		
		request.setAttribute("boardlist", boardlist);
		
		// 게시물 접근하는 유저와 게시물 작성자와 동일한 유저인지 판단 필요
		String userid = (String) request.getSession().getAttribute("userid");
		int currentUserNum = memberdao.findNum(userid);

		// 글번호 파라미터 값을 post_num변수에 저장합니다.
		int post_num = Integer.parseInt(request.getParameter("post_num"));

		// 내용을 확인할 글의 조회수를 증가시킵니다.
//		postdao.setReadCountUpdate(post_num);

		// 글의 내용을 DAO에서 읽은 후 얻은 결과를 postdata 객체에 저장합니다.
		postdata = postdao.getDetail(post_num);
		boarddata = boarddao.getDetail(post_num);
		ArrayList<String> photodata = photodao.getPaths(post_num);
		// postdata=null; //error 테스트를 위한 값 설정
		// DAO에서 글의 내용을 읽지 못했을 경우 null을 반환합니다.
		if (postdata == null) {
			System.out.println("상세보기 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 읽지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}

		if (boarddata.getAnonymous() == 0) {
			int writerNum = postdata.getNum();
			String nickname = memberdao.getNickname(writerNum);
			request.setAttribute("nickname", nickname);
		}
		System.out.println("상세보기 성공");

		// boarddata 객체를 request 객체에 저장합니다.
		request.setAttribute("postdata", postdata);
		request.setAttribute("boarddata", boarddata);
		request.setAttribute("paths", photodata);
		request.setAttribute("currentUserNum", currentUserNum);
		request.setAttribute("anonymous", boarddata.getAnonymous()); // 0 : 닉네임 1 : 익명

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("post/postDetail.jsp"); // 글 내용 보기 페이지로 이동하기 위해 경로를 설정합니다.
		return forward;
	}

}
