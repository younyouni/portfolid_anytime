package net.board.action;

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
import net.post.action.Action;
import net.post.action.ActionForward;
import net.post.db.PostBean;
import net.post.db.PostDAO;
import net.school.db.School;

public class BoardMainAction implements Action {
//로그인 후, 메인보드페이지로 이동 
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		
		String userid =(String) session.getAttribute("userid");
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
		// HOT 리스트를 받아옵니다.
		List<PostBean> hotlist = new ArrayList<PostBean>();
		PostDAO hotpostdao = new PostDAO();
		hotlist = hotpostdao.getHotList(school_num, 1, 10 );
		System.out.println(hotlist.size());
		
		// BEST 리스트를 받아옵니다.
		List<PostBean> bestlist = new ArrayList<PostBean>();
		PostDAO bestpostdao = new PostDAO();
		bestlist = bestpostdao.getBestList(school_num, 1, 10 );
		System.out.println(bestlist.size());
		
		// 학교별 기본 게시판 번호를 받아옵니다.
		BoardDAO bdao = new BoardDAO();
		int[] board_nums = bdao.getBoardNums(school_num);

		// 학교별 & 게시판 별 게시물 리스트를 받아옵니다.
		List<ArrayList<PostBean>> postTotalList 
				= new ArrayList<ArrayList<PostBean>>();
		PostDAO postdao = new PostDAO();

		postTotalList = postdao.getPostTotalList(school_num, board_nums);
		if (postTotalList == null) {
			System.out.println("postTotalList 값은 null");
		}
		
	//----------------------------서브 메뉴 출력용 코드------------------------------------------
	
		BoardDAO boarddao = new BoardDAO();
		List<BoardBean> boardlist = new ArrayList<BoardBean>();
		
		boardlist = boarddao.BoardList(school_num);
		System.out.println("가져온 boardlist = " + boardlist);
		
		request.setAttribute("boardlist", boardlist);
	
		//------------------------------------------------------------------------------------
		request.setAttribute("postTotalList", postTotalList);
		
		session.setAttribute("hotlist", hotlist);
		session.setAttribute("bestlist", bestlist);
		request.setAttribute("member", m);
		forward.setRedirect(false);
		forward.setPath("board/mainBoard.jsp");
		return forward;
	}

}