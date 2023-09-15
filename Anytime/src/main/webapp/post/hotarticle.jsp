<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon"
	href="/Anytime/image/new/nav.logo.png">
<script src="/Anytime/js/hotlist.js"></script>
<title>애니타임</title>
<link href="/Anytime/css/post/postlist.css" type="text/css"
	rel="stylesheet">
<link href="/Anytime/css/best/common.css" type="text/css"
	rel="stylesheet">
<link href="/Anytime/css/best/common.partial.css" type="text/css"
	rel="stylesheet">
<link href="/Anytime/css/best/container.article.css" type="text/css"
	rel="stylesheet">
<link href="/Anytime/css/best/container.community.css" type="text/css"
	rel="stylesheet">
<link href="/Anytime/css/best/container.modal.css" type="text/css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/hot/common.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/hot/common.partial.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/hot/container.article.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/hot/container.community.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/hot/container.modal.css"
	rel="stylesheet">
<style>
#container>div.rightside {
	top: 15px !important;
}
</style>
</head>
<body style="">
	<jsp:include page="../header.jsp" />
	<jsp:include page="../submenu.jsp" />
	<div id="container" class="article">
		<input type="hidden" id="isUser" value="1"> <input
			type="hidden" id="boardId" value="hotarticle">
		<aside class="none">
			<div class="title">
				<a class="hamburger"></a>
				<h1>
					<a href="HotListView.bo">HOT 게시판</a>
				</h1>
			</div>
		</aside>
		<div class="wrap title">
			<h1>
				<a href="HotListView.bo">HOT 게시판</a>
			</h1>
			<p>공감 10개를 받으면 HOT 게시물로 자동 선정됩니다.</p>
			<hr>
		</div>
		<div class="wrap articles">

			<c:forEach var="post" items="${hotlist}">
				<article>
					<a class="article"
						href="PostDetailAction.bo?post_num=${post.post_num}"> <img
						src="https://cf-fpi.everytime.kr/0.png" class="picture medium">
						<h3 class="medium">${post.nickname}</h3> 
						<time class="medium">${post.post_date}</time>
						<hr>
						<h2 class="medium bold">${post.subject}</h2>
						<p class="medium">${post.content}<br>
						</p> <a href="PostList.bo?board_num=${post.board_num}"
						class="boardname"
						style="padding-left: 15px; padding-bottom: 10px;">${post.boardname}</a>
						<ul class="status"
							style="padding-right: 15px; padding-bottom: 10px;">
							<li class="attach">${post.file_count}</li>
							<li title="공감" class="vote">${post.like_count}</li>
							<li title="댓글" class="comment">${post.comment_count}</li>
						</ul>
					</a>
					<div class="comments"></div>
				</article>
			</c:forEach>

			<div class="clearBothOnly"></div>
			<div class="center-block">
				<ul class="pagination justify-content-center">
					<c:if test="${page <= 1 }">
						<li class="page-item hidden"><a class="page-link gray">&nbsp;&nbsp;이전</a>
						</li>
					</c:if>

					<c:if test="${page > 1 }">
						<li class="page-item"><a href="HotListView.bo?page=${page-1}"
							class="page-link">&nbsp;&nbsp;이전</a></li>
					</c:if>

					<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a == page }">
							<li class="page-item active"><a class="page-link">${a}</a></li>
						</c:if>

						<c:if test="${a != page }">
							<li class="page-item"><a href="HotListView.bo?page=${a}"
								class="page-link">${a}</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${page >= maxpage }">
						<li class="page-item hidden"><a class="page-link gray">다음&nbsp;&nbsp;</a>
						</li>
					</c:if>
					<c:if test="${page < maxpage }">
						<li class="page-item"><a href="HotListView.bo?page=${page+1}"
							class="page-link">다음&nbsp;&nbsp;</a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<hr>
		<jsp:include page="../rightside3.jsp" />
	</div>
	<form id="abuseForm" class="modal">
		<a title="닫기" class="close"></a>
		<h3>신고 사유 선택</h3>
		<ul>
			<li><a data-reason="1">게시판 성격에 부적절함</a></li>
			<li><a data-reason="2">욕설/비하</a></li>
			<li><a data-reason="3">음란물/불건전한 만남 및 대화</a></li>
			<li><a data-reason="4">상업적 광고 및 판매</a></li>
			<li><a data-reason="5">유출/사칭/사기</a></li>
			<li><a data-reason="6">낚시/놀람/도배</a></li>
			<li><a data-reason="7">정당/정치인 비하 및 선거운동</a></li>
		</ul>
	</form>
	<form id="manageMoimForm" class="modal">
		<a title="닫기" class="close"></a>
		<h3>게시판 설정</h3>
		<p>
			<label>소개</label> <input type="text" name="info" class="text">
		</p>
		<p class="hide">
			<label>인기 글 금지</label> <input type="checkbox"
				id="manageMoimForm_is_not_selected_hot_article"
				name="is_not_selected_hot_article"><label
				for="manageMoimForm_is_not_selected_hot_article" class="checkbox">글이
				공감을 받아도 인기 글 및 HOT 게시물에 선정되지 않음</label>
		</p>
		<input type="button" value="게시판 양도" class="button light floatLeft">
		<input type="button" value="게시판 삭제" class="button light floatLeft">
		<input type="submit" value="수정" class="button">
	</form>
	<form id="transferMoimForm" class="modal">
		<a title="닫기" class="close"></a>
		<h3>게시판 양도</h3>
		<p>
			<label>양도인 비밀번호</label> <input type="password"
				name="transferer_password" class="text">
		</p>
		<p>
			<label>피양도인 아이디</label> <input type="text" name="transferee_userid"
				class="text">
		</p>
		<input type="submit" value="양도 요청" class="button">
	</form>
	<form id="attachThumbnailForm" class="modal">
		<a title="닫기" class="close"></a>
		<h3>첨부된 이미지</h3>
		<p>
			<label>설명 추가</label>
			<textarea name="caption" class="text"
				placeholder="이 이미지에 대한 설명을 입력하세요."></textarea>
		</p>
		<input type="button" value="첨부 삭제" class="button light floatLeft">
		<input type="submit" value="설명 저장" class="button">
	</form>
	<form id="messageSend" class="modal">
		<a title="닫기" class="close"></a>
		<h3>쪽지 보내기</h3>
		<p>
			<textarea name="message" class="text" placeholder="내용을 입력해주세요."></textarea>
		</p>
		<input type="submit" value="전송" class="button">
	</form>
	<jsp:include page="../footer.jsp" />
</body>
</html>

