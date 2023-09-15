<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="net.school.db.School"%>
<!DOCTYPE html>
<link rel="shortcut icon" type="image/x-icon"
	href="/Anytime/image/new/nav.logo.png">
<link type="text/css" href="/Anytime/css/common.css" rel="stylesheet">
<link type="text/css" href="/Anytime/css/common.partial.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/container.article.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/container.community.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/container.modal.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/post/postlist.css"
	rel="stylesheet">
<script src="js/jquery-3.7.0.js"></script>
<script>
	$(function() {

		var currentPage = location.pathname;
		var newPath = currentPage.replace('/Anytime/', ''); // 나중에 설정 바꾸고서 날리기
		console.log("currentPage" + currentPage);
		console.log("newPath" + newPath);

		if (newPath.includes('.bo')) {
				$(".bo").addClass("active");
		} else{
			$(".bo").removeClass("active");
		}
		$("li").on("click", function() {
			if (!userid) {
				event.preventDefault();
				alert("로그인이 필요합니다.");
				location.href = "login.com";
			}
		});
	});
</script>
<nav>
	<%
	School school = (School) session.getAttribute("school");
	%>
	<div class="wrap">
		<div id="logo">
			<a href="MainBoard.bo"><img src="/Anytime/image/new/nav.logo.png"></a>
			<p>
				<span class="name multiple">애니타임</span><span class="subname">${school.school_name}</span>
			</p>
		</div>
		<c:if test="${empty userid}">
			<div id="account">
				<a href="login.com" class="button">로그인</a> <a href="register.com"
					class="button red">회원가입</a>
			</div>
		</c:if>
		<c:if test="${!empty userid}">
			<div id="account">
				<a href="/message" title="쪽지함" class="icon message">쪽지함</a> <a
					href="account.com" title="내 정보" class="icon my">내 정보</a> <input
					type="hidden" id="userid" value="${userid}"> <input
					type="hidden" id="userSchool" value="21"> <input
					type="hidden" id="userCampus" value="37">
			</div>
		</c:if>
		<ul id="menu">
			<li class="bo"><a href="MainBoard.bo">게시판</a></li>
			<li><a href="/timetable">시간표</a></li>
			<li><a href="/lecture">강의실</a></li>
			<li><a href="/calculator">학점계산기</a></li>
		</ul>
	</div>
</nav>