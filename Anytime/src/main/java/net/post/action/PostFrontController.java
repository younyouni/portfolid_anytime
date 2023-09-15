package net.post.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.action.BoardMainAction;
import net.comment.action.CommentAddAction;
import net.comment.action.CommentDeleteAction;
import net.comment.action.CommentListAction;
import net.comment.action.CommentReplyAction;
import net.comment.action.CommentUpdateAction;

@WebServlet("*.bo")
public class PostFrontController extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Request = request.getRemoteHost();
		System.out.println("접속 URI = " + Request);
		/*
		 * 요청된 전체 URL중에서 포트 번호 다음 부터 마지막 문자열까지 반환됩니다. 예)
		 * http://localhost:8088/JspProject/PostList.bo인 경우 "/JspProject/PostList.bo"
		 * 반환됩니다.
		 */

		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);

		// getContextPath() : 컨텍스트 경로가 반환됩니다.
		// contextPath는 "JspProject"가 반환됩니다.
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);

		// RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터 마지막 위치 문자까지 추출합니다.
		// command는 "/login.net" 반환됩니다.
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);

		// 초기화
		ActionForward forward = null;
		Action action = null;

		switch (command) {

		case "/MainBoard.bo": // 게시물 리스트 출력 하는 기능
			action = new BoardMainAction();
			break;

		case "/MainSearch.bo": // 메인화면 서치 기능
			action = new MainSearchAction();
			break;

		case "/PostList.bo": // 게시물 리스트 불러오는 기능
			action = new PostListAction();
			break;

		case "/PostWrite.bo": // 게시물 작성폼 불러 오는 기능 ( 추후 ajax로 기능 전환 )
			action = new PostWriteAction();
			break;

		case "/PostAddAction.bo": // 게시물 작성폼의 내용을 저장해 데이터 베이스로 저장하는 기능
			action = new PostAddAction();
			break;

		case "/postSearch.bo": // 게시판별 게시물 검색어 서치 기능
			action = new PostSearchAction();
			break;

		case "/PostDetailAction.bo": // 게시물을 작성한 데이터 베이스를 불러와 폼에 보여주는 기능
			action = new PostDetailAction();
			break;

		case "/PostModifyView.bo": // 작성된 게시물을 데이터 베이스로 불러와 수정하는 폼에 보여주는 기능
			action = new PostModifyView();
			break;
		case "/PostModifyAction.bo": // 수정한 게시물의 내용들을 데이터 베이스로 저장하는 기능
			action = new PostModifyAction();
			break;
//		case "/PostReplyView.bo": // 게시물의 답글폼 기능 ( 삭제 ) => 게시물의 답글기능은 구현X
//			action = new PostReplyView();
//			break;
//		case "/PostReplyAction.bo": // 게시물의 답글 기능 ( 삭제 ) => 게시물의 답글기능은 구현X
//			action = new PostReplyAction();
//			break;
		case "/PostDeleteAction.bo": // 작성된 게시물을 삭제하는 기능
			 action = new PostDeleteAction();
			break;
		case "/PostFileDown.bo": // 작성된 게시물에 있는 파일을 다운로드 하는 기능
			action = new PostFileDownAction();
			break;
		case "/HotListView.bo": // 핫게시물(공감수 10개이상) 게시물 리스트화면을 보여주는 기능
			action = new HotListViewAction();
			break;
//		case "/HotListDetailView.bo": // HotListView리스트 화면을 클릭시 게시물의 디테일한 부분을 보여주는 기능
//			action = new HotListViewAction(); // ex) 제목, 내용, 익명/닉네임 선택체크박스 등등...
//			break;
		case "/BestListView.bo": // 베스트게시물(공감수 100개이상) 게시물 리스트화면을 보여주는 기능
			action = new BestListViewAction();
			break;
//		case "/BestListDetailView.bo": // BEstListView리스트 화면을 클릭시 게시물의 디테일한 부분을 보여주는 기능
//			action = new BestListDetailViewAction(); // ex) 제목, 내용, 익명/닉네임 선택체크박스 등등...
//			break;

		case "/CommentList.bo":
			action = new CommentListAction();
			break;
			
		case "/CommentAdd.bo":
			action = new CommentAddAction();
			break;

		case "/CommentUpdate.bo":
			action = new CommentUpdateAction();
			break;

		case "/CommentDelete.bo":
			action = new CommentDeleteAction();
			break;
			
		case "/CommentReply.bo":
			action = new CommentReplyAction();
			break;

		} // switch end

		forward = action.execute(request, response);

		if (forward != null) {
			if (forward.isRedirect()) { // 리다이렉트 됩니다.
				response.sendRedirect(forward.getPath());
			} else { // 포워딩됩니다.
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}

	} // doProcess end

	// doProcess(request, response)메소드를 구현하여 요청이 GET방식이든
	// POST방식으로 전송되어 오든 같은 메ㅅ드에서 요청을 처리할 수 있도록 하였습니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}
}
