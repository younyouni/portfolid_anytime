<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
	href="/Anytime/image/new/nav.logo.png">
<title>애니타임</title>
<link type="text/css" href="/Anytime/css/common.css" rel="stylesheet">
<link type="text/css" href="/Anytime/css/common.partial.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/main/container.community.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/main/modal.css"
	rel="stylesheet">
<script src="js/jquery-3.7.0.js"></script>
<script>
	var school_num = ${school.school_num};
	var userid = "${userid}";
	var school_check = "${member.school_check}";
	console.log(school_check);
</script>
<script type="text/javascript" src="/Anytime/js/modal.js"></script>
<style>
body {
	margin: 0;
	padding: 0;
	display: flex;
	flex-direction: column;
	min-height: 100vh;
	background-color: #ffffff !important;
}

element.style {
	margin-left: -200px;
	margin-top: -135.5px;
	display: none;
}
</style>
</head>
<body style="">
	<jsp:include page="../header.jsp" />
	<jsp:include page="../submenu.jsp" />

	<div id="container" class="community">
		<aside class="none">
			<form class="search">
				<input type="search" name="keyword" class="text"
					placeholder="전체 게시판의 글을 검색하세요!">
			</form>
			<div class="title">
				<a class="hamburger"></a>
				<h1>${school.school_name}애니타임</h1>
				<ol class="buttons">
					<li><a id="searchArticle">글 검색</a></li>
				</ol>
			</div>
		</aside>
		<div class="banners">
			<a
				href="https://ad.everytime.kr/adClick?adToken=yj25o47dUEdaGWIfaYHQSntu4pJm3%2F6s2%2Fgsu4OwnMpOuuGckaITHzWM%2BgrA0BjvozqCg2G3ptHhUCRi7xFUVSKVoZ3M7a8nh4AL3ORyj3ImaYocFqZKmO0jlvPKz37AiQmRvXix56rFasRWheUHQHyF%2FFL1pc0gK%2BIPAM%2FC9vGRUrINUPoxiQjSJHf8%2BHdeIa7%2BS9ujYtBPfz0%2FxasvvNjpQbqxTR%2FF5iQIm%2F5hkkEdw6C7KzIP%2BBynXAgU7nAs"><img
				src="https://cf-eba.everytime.kr/20230731_GalaxyTabS9Ultra_256GB_home.png"></a>
		</div>
		<div class="leftside">
			<div class="card pconly">
				<c:if test="${!empty userid}">
					<form class="logged">
						<img src="https://cf-fpi.everytime.kr/0.png" class="picture">
						<p class="nickname">${member.nickname}</p>
						<p class="userid">${member.userid}</p>
						<ul class="buttons">
							<li><a href="account.com">내 정보</a></li>
							<li><a href="logout.com">로그아웃</a></li>
						</ul>
						<hr>
					</form>
				</c:if>
				<c:if test="${ empty userid}">
					<form class="login">
						<h3>
							커뮤니티 이용을 위해<br> <strong>로그인</strong>이 필요합니다!
						</h3>
						<a href="login.com" class="button login">로그인</a> <a
							href="register.com" class="button register">에브리타임 회원가입</a>
					</form>
				</c:if>
			</div>
			<div class="card">
				<div class="menus">
					<a href="/myarticle" class="myarticle">내가 쓴 글</a> <a
						href="/mycommentarticle" class="mycommentarticle">댓글 단 글</a> <a
						href="/myscrap" class="myscrap">내 스크랩</a>
					<c:if test="${member.board_admin eq 1}">
						<a href="boardContol.com" class="myboard">게시판 관리</a>
					</c:if>
					<c:if test="${member.board_admin eq 0}">
						<p>
							새로운 게시판을<br>생성할수 있어요! <a class="button createboard"
								href="#createboard" data-toggle="modal">게시판 생성하기</a>
						</p>
					</c:if>
					<hr>
				</div>
			</div>
		</div>
		<jsp:include page="../rightside.jsp" />

		<c:forEach var="outerList" items="${postTotalList}">
			<div class="main">
				<div class="card">
					<div class="board">
						<h3>
							<c:forEach var="board" items="${outerList}" begin="0" end="0">
								<a href="PostList.bo?board_num=${board.board_num}">
								   ${board.boardname}</a>
							</c:forEach>
						</h3>
						<c:forEach var="post" items="${outerList}">
							<a class="list"
								href="PostDetailAction.bo?post_num=${post.post_num}"> 
								<time>${post.post_date}</time>
								<p>${post.subject}</p>
								<hr>
							</a>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:forEach>
		
		<form id="createBoard" class="modal"
			style="margin-left: -200px; margin-top: -220.5px; display: none;">
			<a title="닫기" class="close"></a> <span class="new">새 게시판 만들기</span>
			<p>
				<label>단체/학과 게시판 생성에는 관리자 승인이 필요합니다.</label> <label id="boarddetail">유형</label>
			<div id="option">
				<label> <input type="radio" name="boardtype" value="2">
					단체
				</label> <label> <input type="radio" name="boardtype" value="3">
					학과
				</label> <label> <input type="radio" name="boardtype" value="4">
					커스텀
				</label>
			</div>
			</p>
			<p></p>
			<label id="boarddetail">게시판 이름</label> <input type="text"
				class="info" name="name" placeholder="게시판 이름" class="text boardinfo">
			<label id="boarddetail">게시판 소개</label> <input type="text"
				class="info" name="content" placeholder="게시판 소개"
				class="text boardinfo">
			<div class="purpose" style="display: none;">
				<label id="boarddetail" class="purpose">개설 목적</label>
				<p id="explain" class="purpose">승인 심사시 필요</p>
			</div>
			<input type="text" name="purpose" placeholder="개설 목적"
				class="text info boardinfo purpose" style="display: none;">

			<label class="custom-checkbox-label"> <input type="checkbox"
				class="custom-checkbox" name="anony"> <span
				class="custom-checkbox-image"></span>
			</label> <input type="submit" value="완료" class="button">
		</form>
	</div>
	<!--<jsp:include page="../footer.jsp" />-->
</body>
</html>