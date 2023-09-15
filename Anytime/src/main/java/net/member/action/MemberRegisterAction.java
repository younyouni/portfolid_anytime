package net.member.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.school.db.School;
import net.school.db.SchoolDAO;

public class MemberRegisterAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		

		// SchoolDAO를 사용하여 학교 리스트를 가져옴
        SchoolDAO schooldao = new SchoolDAO();
        List<School> schoolList = schooldao.searchSchool(); 

        // JSP 페이지로 데이터를 전달하기 위해 request 객체에 속성으로 저장합니다.
        request.setAttribute("schoolList", schoolList);

		
		forward.setRedirect(false); // 주소 변경없이 jsp페이지의 내용을 보여줍니다.
		forward.setPath("member/register.jsp");
		return forward;
	}

}
