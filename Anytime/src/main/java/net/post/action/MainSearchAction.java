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
import net.post.db.PostBean;
import net.post.db.PostDAO;
import net.school.db.School;

public class MainSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		PostDAO postdao = new PostDAO();
		
		HttpSession session = request.getSession();
		School school = (School) session.getAttribute("school");
		int school_num = school.getSchool_num();
		
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page") != null) {
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
		hotlist = hotpostdao.getHotList(1, 10);
		
		
		// 포스트 리스트를 받아옵니다.
		List<PostBean> postlist = new ArrayList<PostBean>();
		int listcount = 0;
		int index = -1;		//search_field에 존재하지 않는 값으로 초기화
		
		String search_word = "";

		search_word = String.valueOf(request.getParameter("search_word"));
		
		List<PostBean> boardnumlist = new ArrayList<PostBean>();
		
		boardnumlist = postdao.findSchoolBoardlistcount(school_num);		//학교번호로 해당하는 보드번호를 모두 가져옴
		System.out.println("갖고온 boardnumlist = " + boardnumlist);
		
		
		listcount = postdao.getSearchListCount(boardnumlist, search_word);	//가져온 모든 보드번호에 해당하는 게시물중 검색어에 해당하는 총 게시물의 수를 가져옴
		postlist = postdao.getSearchPostList(boardnumlist, search_word, page, limit); // 검색어에 해당하는 모든 게시물의 내용을 가져옴
		
		System.out.println("리스트 카운트 수 = " + listcount);
		System.out.println("갖고 오는 값 = " + postlist);
		
		int maxpage = (listcount + limit - 1) / limit;
		System.out.println("총 페이지수 = " + maxpage);
		
		int startpage = ((page -1) / 10) * 10 + 1;
		int endpage = startpage + 10 - 1;
		
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 = " + endpage);
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 = " + startpage);
		
		if (endpage > maxpage) 
			endpage = maxpage;
			
		int emptycheck = 0;
		
		if (postlist != null) {
			emptycheck = 2;
		}
		
		//----------------------------서브 메뉴 출력용 코드------------------------------------------
		BoardDAO boarddao = new BoardDAO();
		List<BoardBean> boardlist = new ArrayList<BoardBean>();
		
		boardlist = boarddao.BoardList(school_num);
		request.setAttribute("boardlist", boardlist);
		//------------------------------------------------------------------------------------
		
		
		
		
		session.setAttribute("allsearchcheck", 1);			//메인검색에서 접근했는지 체크
		
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
		
		request.setAttribute("hotlist", hotlist);
		
		request.setAttribute("emptycheck", emptycheck);
		
		forward.setPath("post/postList.jsp");
		forward.setRedirect(false);
		return forward;

	}
}
