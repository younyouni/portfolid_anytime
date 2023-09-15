package net.member.action;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class MemberForgotAction_id_Email implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String email = request.getParameter("email");
		
		MemberDAO memberdao = new MemberDAO();
		String userid = memberdao.getUserIdByEmail(email);
		
		if(userid != null) {

	        // 이메일로 인증번호 전송
	        String senderEmail = "chaji281@naver.com"; //  이메일 주소
	        String senderPassword = "hta1234$"; //  이메일 비밀번호

	        String subject = "애니타입 아이디 찾기";
	        String body = "가입하신 아이디는  " + userid + " 입니다.";

	        try {
	           Properties props = new Properties();
	           props.put("mail.smtp.host", "smtp.naver.com");
	           props.put("mail.smtp.port", "465");
	           props.put("mail.smtp.auth", "true");
	           props.put("mail.smtp.ssl.enable", "true");
	           props.put("mail.smtp.ssl.trust", "smtp.naver.com");
	           props.put("mail.smtp.ssl.protocols", "TLSv1.2");

	           Session session = Session.getInstance(props, new Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(senderEmail, senderPassword);
	                }
	            });

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(senderEmail));
	            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
	            message.setSubject(subject);
	            message.setText(body);

	            Transport.send(message);
	            
	            response.setContentType("text/html; charset=UTF-8");
	            response.getWriter().println("<script>");
	            response.getWriter().println("alert('아이디가 발송되었습니다.');");
	            response.getWriter().println("location.href='login.com';"); // 다음 페이지 URL 변경
	            response.getWriter().println("</script>");
	            
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	        
		}else {
			response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>");
            response.getWriter().println("alert('해당 이메일이 존재하지 않습니다.');");
            response.getWriter().println("history.back();");
            response.getWriter().println("</script>");
		}
        return null;
	}

}
