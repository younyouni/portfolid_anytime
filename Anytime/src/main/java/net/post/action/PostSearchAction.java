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
import net.member.db.Member;
import net.member.db.MemberDAO;
import net.post.db.PostBean;
import net.post.db.PostDAO;
import net.school.db.School;

public class PostSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		PostDAO postdao = new PostDAO();

		int page = 1;
		int limit = 10;

		HttpSession session = request.getSession();

		String userid = (String) session.getAttribute("userid");
		System.out.println("userid: " + userid);

		MemberDAO mdao = new MemberDAO();
		Member m = mdao.member_info(userid);

		School school = (School) session.getAttribute("school");
		int school_num = 0;

		if (school != null) {
			school_num = school.getSchool_num();
			System.out.println("school_num: " + school_num);
		} else {
			System.out.println("school 객체가 세션에 존재하지 않습니다.");
		}

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = " + page);

		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("넘어온 limit = " + limit);

		// HOT 리스트를 받아옵니다.
		List<PostBean> hotlist = new ArrayList<PostBean>();
		PostDAO hotpostdao = new PostDAO();
		hotlist = hotpostdao.getHotList(school_num, 1, 10);
		System.out.println(hotlist.size());

		int board_num = 0;
		if (request.getParameter("board_num") != null) {
			board_num = Integer.parseInt(request.getParameter("board_num"));

		}
		System.out.println("보드 넘버 조회 = " + board_num);

		List<PostBean> postlist = new ArrayList<PostBean>();
		int listcount = 0;
		int index = -1; // search_field에 존재하지 않는 값으로 초기화

		String search_word = "";

		// (memberList.net?page=2&search_field=-1&search_word=)
		if (request.getParameter("search_word") == null || 
				request.getParameter("search_word").equals("")) {
			// 총 리스트 수를 받아옵니다.
			listcount = postdao.getListCount(board_num);
			postlist = postdao.getPostList(board_num, page, limit);
		} else {
			index = Integer.parseInt(request.getParameter("search_field"));
			String[] search_field = new String[] { "all", "subject", "content" };

			search_word = String.valueOf(request.getParameter("search_word"));
			listcount = postdao.getListCount(board_num, search_field[index], search_word);
			postlist = 
				postdao.getPostList(board_num, search_field[index], search_word, page, limit);
		}

		String board_name = postdao.findBoardname(board_num);

		int maxpage = (listcount + limit - 1) / limit;
		System.out.println("총 페이지수 = " + maxpage);

		int startpage = ((page - 1) / 10) * 10 + 1;
		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 = " + endpage);
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 = " + startpage);

		if (endpage > maxpage)
			endpage = maxpage;

		int emptycheck = 0;

		if (postlist != null) {
			emptycheck = 2;
		}

		// ----------------------------서브 메뉴 출력용
		// 코드------------------------------------------
		BoardDAO boarddao = new BoardDAO();
		List<BoardBean> boardlist = new ArrayList<BoardBean>();

		boardlist = boarddao.BoardList(school_num);
		System.out.println("가져온 boardlist = " + boardlist);

		request.setAttribute("boardlist", boardlist);
		// ------------------------------------------------------------------------------------

		request.setAttribute("page", page); // 현재 페이지 수
		request.setAttribute("maxpage", maxpage); // 최대 페이지 수

		// 현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("startpage", startpage);

		// 현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("endpage", endpage);

		request.setAttribute("listcount", listcount); // 총 글의 수
		request.setAttribute("postlist", postlist);
		request.setAttribute("search_field", index);
		request.setAttribute("search_word", search_word);
		request.setAttribute("board_name", board_name);

		session.setAttribute("hotlist", hotlist);

		session.setAttribute("board_num", board_num);

		request.setAttribute("emptycheck", emptycheck);
		session.setAttribute("allsearchcheck", 0); // 보드검색에서 접근했는지 체크

		forward.setPath("post/postList.jsp");
		forward.setRedirect(false);
		return forward;

	}

}
