package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.com")
public class MemberFrontController extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 요청된 전체 URL중에서 포트 번호 다음 부터 마지막 문자열까지 반환됩니다. 예) contextPath가 "/JspProject" 인 경우
		 * http://localhost:8088/JspProject/login.net로 요청하면 getRequestURI()는
		 * "/JspProject/login.net" 반환됩니다.
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
		case "/anytime.com": // AnytimeMain.jsp로 포워딩
			action = new MainPageAction();
			break;

		case "/login.com":
			action = new MemberLoginAction();
			break;

		case "/loginProcess.com":
			action = new MemberLoginProcessAction();
			break;

		case "/idcheck.com":
			action = new MemberIdCheckAction();
			break;

		case "/forgotid.com":
			action = new MemberForgotAction_id();
			break;
			
		case "/forgotidEmail.com":
			action = new MemberForgotAction_id_Email();
			break;	
			
		case "/forgotpassword.com":
			action = new MemberForgotAction_password();
			break;
		
		case "/forgotpassword_emailcheck.com" :
			action = new MemberForgotAction_password_Emailcheck();
			break;
			
		case "/forgotpasswordEmailSend.com":
			action = new MemberForgotAction_password_EmailSend();	
			break;
			
		case "/forgotpasswordresult.com":
			action = new MemberForgotAction_password_result();
			break;
			
		case "/forgotpasswordresultProcess.com":
			action = new MemberForgotAction_password_result_process();
			break;
			
		case "/register.com":
			action = new MemberRegisterAction();
			break;

		case "/agreement.com":
			action = new MemberAgreementAction();
			break;

		case "/nicknamecheck.com":
			action = new MemberNicknameCheckAction();
			break;
			
		case "/join.com":
			action = new MemberJoinAction();
			break;

		case "/joinProcess.com":
			action = new MemberJoinProcessAction();
			break;

		case "/logout.com":
			action = new MemberLogoutAction();
			break;

		case "/emailcheck.com" :
			action = new EmailCheckAction();
			break;
			
		case "/sendEmail.com" :
			action = new SendEmailAction();
			break;
			
		case "/certificateEmailSend.com" :
			action = new MemberCertificateAction_Emailsend();
			break;
			
		case "/certificate_email.com" :
			action = new MemberCertificateAction_Emailcheck();
			break;
			
		case "/certificate_process.com":
			action = new MemberCertificateAction_Process();
			break;
			
			
			
			
		case "/account.com":
			action = new MemberAccountAction();
			break;

		case "/certificate.com":
			action = new MemberCertificateAction();
			break;

		case "/passwordUpdate.com":
			action = new MemberPasswordUpdateAction();
			break;

		case "/checkpassword.com":
			action = new MemberPasswordCheckAction();
			break;

		case "/passwordUpdateProcess.com":
			action = new MemberPasswordUpdateProcess();
			break;

		case "/memberUpdate.com":
			action = new MemberUpdateAction();
			break;

		case "/updateProcess.com":
			action = new MemberUpdateProcessAction();
			break;

		case "/boardContol.com":
			action = new MemberBoardControlAction();
			break;

		case "/memberDelete.com":
			action = new MemberDeleteAction();
			break;
			
		case "/memberDeleteProcess.com":
			action = new MemberDeleteProcessAction();
			break;

		/*
		 * case "/.com": action = new (); break;
		 */
		/*
		 * case "/join.com": action=new MemberJoinAction(); break;
		 */


//         case "/memberList.com":
//            action = new MemberSearchAction();
//            break;
//           case "/memberDelete.net":
//               action = new MemberDeleteAction();
//               break;   
		default:
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath(contextPath + "/anytimeMain.jsp");
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