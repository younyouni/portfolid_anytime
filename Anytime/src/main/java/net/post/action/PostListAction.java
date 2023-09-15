//글 목록 보기와 관련된 클래스
package net.post.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;
import net.post.db.PostBean;
import net.post.db.PostDAO;
import net.school.db.School;


public class PostListAction implements Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		PostDAO postdao = new PostDAO();
		List<PostBean> postlist = new ArrayList<PostBean>();
		
		HttpSession session = request.getSession();
		School school = (School) session.getAttribute("school");
		int school_num = school.getSchool_num();
		
		// HOT 리스트를 받아옵니다.
		List<PostBean> hotlist = new ArrayList<PostBean>();
		PostDAO hotpostdao = new PostDAO();
		hotlist = hotpostdao.getHotList(school_num, 1, 10);
		
		
		
		
		int board_num = 0;
		if (request.getParameter("board_num") != null) {
			board_num = Integer.parseInt(request.getParameter("board_num"));
		}

		
		System.out.println("학교번호 :" + school_num + "보드번호 :" + board_num);
		
		
		//로그인 성공시 파라미터 page가 없어요. 그래서 초기값이 필요합니다.
		int page = 1; // 보여줄 페이지																		//처음이라 무조건 1페이지를 보여줌
		int limit = 10; // 한 페이지에 보여줄 게시판 목록의 수														//한 페이지에 보여줄 게시물의 수
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));										//페이지 page값을 가져옴
		}
		System.out.println("넘어온 페이지 = " + page);
		
		//추가
		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));									//표시할 게시물 갯수 가져옴 근데 필요없음
		}
		System.out.println("넘어온 limit = " + limit);	
		
		//총 리스트 수를 받아옵니다.
		int listcount = postdao.getListCount(board_num);												//가져올 게시물의 총 수

		//리스트를 받아옵니다.
		postlist = postdao.getPostList(board_num, page, limit);											//가져올 게시물들 값
		
		String board_name = postdao.findBoardname(board_num);
		/*
		 총 페이지 수 = (DB에 저장된 총 리스트의 수 + 한 페이지에서 보여주는 리스트의 수 - 1)/ 한 페이지에서 보여주는 리스트의 수
		 
		 예를 들어 한 페이지에서 보여주는 리스트의 수가 10개인 경우
		 예1) DB에 저장된 총 리스트의 수가 0이면 총 페이지수는 0페이지
		 예2) DB에 저장된 총 리스트의 수가 ( 1-10 ) 이면 총 페이지수는 1페이지
		 예3) DB에 저장된 총 리스트의 수가 ( 11-20 ) 이면 총 페이지수는 2페이지
		 예4) DB에 저장된 총 리스트의 수가 ( 21-30 ) 이면 총 페이지수는 3페이지
		 */
		int maxpage = (listcount + limit - 1) / limit;													// 총 게시물이 20개면, 20 + 10 - 1 = 29고 / 10하면 2페이지          	   
		System.out.println("총 페이지수 =" + maxpage);
		/*
		 startpage : 현재 페이지 그룹에서 맨 처음 표시될 페이지 수를 의미합니다.
		 ([1], [11], [21] 등...) 보여줄 페이지가 30개일 경우
		 [1][2][3]...[30]까지 다 표시하기에는 너무 많기 때문에 보통 한 페이지에는
		 10페이지 정도 까지 이동할 수 있게 표시합니다.
		 
		 예) 페이지 그룹이 아래와 같은 경우
		 	[1][2][3][4][5][6][7][8][9][10]
		 페이지그룹 시작 페이지는 startpage에 마지막 페이지는 endpage에 구합니다.
		 
		 예로 1-10페이지의 내용을 나타낼때는 페이지 그룹은 [1][2][3]...[10]로 표시되고
		 11-20페이지의 내용을 나타낼때는 페이지 그룹은 [11][12][13]..[20]까지 표시됩니다.
		 */
		int startpage = ((page - 1) / 10) * 10 + 1;														// 페이지네이션 스타트 페이지 1,  11, 21, 31,
	      System.out.println("현재 페이지에 보여줄 시작 페이지 수 :"+startpage);												
	      
	    //endpage : 현재 페이지 그룹에서 보여줄 마지막 페이지 수([10], [20], [30] 등...)
	    int endpage = startpage+ 10 - 1;//10															// 페이지네이션 라스트 페이지 10, 20, 30, 40,
	    System.out.println("현재 페이지에 보여줄 마지막 페이지 수:"+endpage);
	    
	    /*
	     마지막 그룹의 마지막 페이지 값은 최대 페이지 값입니다.
	     예로 마지막 페이지 그룹이 [21]~[30]인 경우
	     시작페이지는 21(startpage=21)와 마지막페이지는 30(endpage=30) 이지만
	     최대 페이지(maxpage)가 25라면 [21]~[25]까지만 표시되도록 합니다.
	    */
	    
	    if (endpage > maxpage)																			// 한 페이지 네이션에서 최대는 1~10, 11~20 이지만 게시물이 적다면 그 페이지까지로 조절
	    	endpage = maxpage;
	    
	    int emptycheck = 0;
		
		if (postlist != null) {
			emptycheck = 1;
		}
	    
		//----------------------------서브 메뉴 출력용 코드------------------------------------------
				BoardDAO boarddao = new BoardDAO();
				List<BoardBean> boardlist = new ArrayList<BoardBean>();
				
				boardlist = boarddao.BoardList(school_num);
				System.out.println("가져온 boardlist = " + boardlist);
				
				request.setAttribute("boardlist", boardlist);
		//------------------------------------------------------------------------------------
	    
	    String state = request.getParameter("state");
	    
	    if (state == null) {
	    	System.out.println("state==null");
	    	request.setAttribute("page", page); // 현재 페이지 수
	    	request.setAttribute("maxpage", maxpage); // 최대 페이지 수
	    	
	    	//현재 페이지에 표시할 첫 페이지 수
	    	request.setAttribute("startpage", startpage);
	    	
	    	//현재 페이지에 표시할 끝 페이지 수
	    	request.setAttribute("endpage", endpage);
	    	
	    	request.setAttribute("listcount", listcount); // 총 글의 수
	    	
	    	//해당 페이지의 글 목록을 갖고 있는 리스트
	    	request.setAttribute("postlist", postlist);
	    	
	    	request.setAttribute("limit", limit);
	    	request.setAttribute("board_num", board_num);
	    	request.setAttribute("board_name", board_name);
	    	
	    	session.setAttribute("hotlist", hotlist);
	    	
	    	request.setAttribute("emptycheck", emptycheck);
	    	
	    	session.setAttribute("board_num", board_num);
	    	
	    	session.setAttribute("allsearchcheck", 0);			//보드검색에서 접근했는지 체크
	    	
	    	ActionForward forward = new ActionForward();
	    	forward.setRedirect(false);
	    	
	    	//글 목록 페이지로 이동하기 위해 경로를 설정합니다.
	    	forward.setPath("post/postList.jsp");
	    	return forward; // BoardFrontController.java로 리턴됩니다.
	    
	    	} else {
	    	System.out.println("state = ajax");
	    
	    	//위에서 request로 담았던 것을 JsonObject에 담습니다.
	    	JsonObject object = new JsonObject();
	    	object.addProperty("page", page); //{"page" : 변수 page의 값} 형식으로 저장
	    	object.addProperty("maxpage", maxpage);
	    	object.addProperty("startpage", startpage);
	    	object.addProperty("endpage", endpage);
	    	object.addProperty("listcount", listcount);
	    	object.addProperty("limit", limit);
	    	
	    	//JsonObject에 List 형식을 담을 수 있는 addProperty() 존재하지 않습니다.
	    	//void com.google.gson.JsonObject.add(String property, JsonElement value) 메서드를 통하여 저장합니다.
	    	//List형식을 JsonElement로 바꾸어 주어야 object에 저장할 수 있습니다.
	    	
	    	//List => JsonElement
	    	JsonElement je = new Gson().toJsonTree(postlist);
	    	System.out.println("postlist=" + je.toString());
	    	object.add("postlist", je);
	    	
	    	response.setContentType("application/json;charset=utf-8");
	    	response.getWriter().print(object);
	    	System.out.println(object.toString());
	    	return null;    
	    }//else end
	}
}
