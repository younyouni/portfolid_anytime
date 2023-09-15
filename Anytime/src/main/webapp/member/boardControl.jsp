<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp" />
<title>게시판 관리 - 애니타임</title>
<link type="text/css" href="/Anytime/css/main/container.modal.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/main/modal.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/common.css" rel="stylesheet">
<link type="text/css" href="/Anytime/css/common.partial.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/main/container.community.css"
	rel="stylesheet">
<link data-vue-meta="ssr" rel="preload"
	href="/Anytime/css/myInfo/13659cd4ee3a59b7dba9.css">
<link data-vue-meta="ssr" rel="preload"
	href="/Anytime/css/myInfo/f2eecc900b6da2aeb5af.css">
<link data-vue-meta="ssr" rel="stylesheet"
	href="/Anytime/css/myInfo/0a14fb2000808afce700.css">
<link rel="stylesheet" type="text/css"
	href="/Anytime/css/myInfo/f2eecc900b6da2aeb5af.css">
<link rel="stylesheet" type="text/css"
	href="/Anytime/css/myInfo/13659cd4ee3a59b7dba9.css">
<script src="js/jquery-3.7.0.js"></script>
<script>
	var school_num = ${school.school_num};
	var userid = "${userid}";
</script>
<script type="text/javascript" src="/Anytime/js/modal.js"></script>
<style>
body {
	margin: 0;
	padding: 0;
	display: flex;
	flex-direction: column;
	min-height: 100vh;
}

element.style {
	margin-left: -200px;
	margin-top: -135.5px;
	display: none;
}

#boardSection>a.createboard {
	color: #fff;
	font-weight: bold;
	background-color: #7869e6;
	display: block;
	line-height: 40px;
	border-radius: 12px;
	font-size: 16px;
	text-align: center;
	cursor: pointer;
}

p.noboard {
	display: flex;
	padding: 13px 24px;
	justify-content: space-between;
	line-height: 24px;
	word-wrap: break-word;
	color: #292929;
	font-size: 16px;
}

form.modal {
	padding: 25px 25px 15px 25px;
	width: 400px;
	position: fixed;
	z-index: 1000;
	left: 50%;
	top: 50%;
	background-color: #fff;
}

form.modal a.close {
	display: block;
	float: right;
	width: 20px;
	height: 20px;
	background: transparent
		url(/Anytime/image/new/container.modal.close.png) no-repeat center
		center;
	background-size: 15px 15px;
	cursor: pointer;
}

form.modal .button {
	display: block!important;
	float: right!important;
	margin-left: 5px!important;
	padding: 0 12px!important;
	height: 30px!important;
	line-height: 30px!important;
	border: 0!important;
	border-radius: 18px!important;
	color: #fff!important;
	font-size: 12px!important;
	font-weight: bold!important;
	background-color: #7869E6!important;
	cursor: pointer!important;
}
</style>
</head>
<body id="my">
	<div data-v-1eab7d2c="" class="boardcontrol">
		<div data-v-ffaea544="" class="navbar">
			<a data-v-ffaea544="" class="hamburger">메뉴 열기</a>
			<h1 data-v-ffaea544="">게시판 관리</h1>
		</div>
		<div data-v-1eab7d2c="" class="container">
			<section data-v-1eab7d2c="" id="boardSection">
				<h1 data-v-1eab7d2c="">관리 중인 게시판</h1>
				<c:choose>
					<c:when test="${boardCount > 0 }">
						<c:forEach var="b" items="${boardlist}">
							<a data-v-1eab7d2c="" href="/528552">${b.name } <c:if
									test="${b.approval =='N' }">
									<p>승인 심사 진행 중</p>
								</c:if>
							</a>
							<hr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<p data-v-1eab7d2c="" class="noboard">관리 중인 게시판이 없어요</p>
					</c:otherwise>
				</c:choose>
				<a class="button createboard" href="#createboard"
					data-toggle="modal">게시판 생성하기</a>
			</section>
		</div>
	</div>

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
		<label id="boarddetail">게시판 이름</label> <input type="text" class="info"
			name="name" placeholder="게시판 이름" class="text boardinfo"> <label
			id="boarddetail">게시판 소개</label> <input type="text" class="info"
			name="content" placeholder="게시판 소개" class="text boardinfo">
		<div class="purpose" style="display: none;">
			<label id="boarddetail" class="purpose">개설 목적</label>
			<p id="explain" class="purpose">승인 심사시 필요</p>
		</div>
		<input type="text" name="purpose" placeholder="개설 목적"
			class="text info boardinfo purpose" style="display: none;"> <label
			class="custom-checkbox-label"> <input type="checkbox"
			class="custom-checkbox" name="anony"> <span
			class="custom-checkbox-image"></span>
		</label> <input type="submit" value="완료" class="button">
	</form>
	<jsp:include page="../footer2.jsp" />
</body>
</html>