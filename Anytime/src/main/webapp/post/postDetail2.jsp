<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>애니타임</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.js"></script>
<script src="js/comment.js"></script>
<script>
	var userid = "${userid}";
	var anonymous = ${anonymous};
	var currentUserNum = ${currentUserNum};
	var writerNum = ${postdata.num};
</script>
<style>
.status.disabled {
  pointer-events: none; /* 클릭 및 이벤트 무시 */
  color: gray; /* 색상 변경 */
  text-decoration: none; /* 밑줄 제거 */
  cursor: not-allowed; /* 마우스 커서 스타일 변경 */
}

article form.writecomment ul.option li.cancel {
	margin-right: 5px;
	background-image: url('/Anytime/image/cancel.png') !important;
}

</style>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<jsp:include page="../submenu.jsp" />
	<div id="container" class="article">
		<input type="hidden" id="isUser" value="1"> <input
			type="hidden" id="post_num" value="${postdata.post_num}"> <br>
		<aside class="none">
			<div class="title">
				<a class="hamburger"></a>
				<h1>
					<a href="PostList.bo?board_num=${boarddata.board_num}">${boarddata.name}</a>
				</h1>
			</div>
		</aside>
		<div class="wrap title">
			<h1>
				<a href="PostList.bo?board_num=${boarddata.board_num}">${boarddata.name}</a>
			</h1>
			<p>${boarddata.content}</p>
			<hr>
		</div>
		<div class="wrap articles">
			<article>
				<a class="article"> <img src="https://cf-fpi.everytime.kr/0.png"
					class="picture large">
					<div class="profile">
						<c:if test="${anonymous eq 0}">
							<h3 class="large">${nickname}</h3>
						</c:if>
						<c:if test="${anonymous eq 1}">
							<h3 class="large">익명</h3>
						</c:if>
						<time class="large">${postdata.post_date}</time>
					</div>

					<ul class="status">
						<li class="update">수정</li>
						<li class="del">삭제</li>
						<li class="messagesend" data-modal="messageSend"
							data-article-id="311337052" data-is-anonym="1">쪽지</li>
						<li class="abuse">신고</li>
					</ul>

					<hr>
					<h2 class="large">${postdata.subject}</h2>
					<p class="large">${postdata.content}</p>
					<div class="attaches multiple">
						<c:forEach var="b" items="${paths}">
							<figure class="attach">
								<img src="/Anytime/boardupload/${b}" alt="none">
							</figure>
						</c:forEach>
					</div>
					<ul class="status left">
						<li title="공감" class="vote">${postdata.like_count}</li>
						<li title="댓글" class="comment">0</li>
						<li title="스크랩" class="scrap">${postdata.scrap_count}</li>
					</ul>
					<hr>
					<div class="buttons">
						<span class="posvote">공감</span><span class="scrap">스크랩</span>
					</div> <input type="hidden" id="comment_post_num"
					value="${postdata.post_num}">
				</a>
				<%--------------------------------- comment시작 ---------------------------------------------------%>

				<div class="comments" style="display: block;">
					<form class="writecomment">
						<input type="text" name="text" maxlength="300" autocomplete="off"
							placeholder="댓글을 입력하세요." class="text">
						<ul class="option">
							<li title="취소" class="cancel"></li>
							<li title="완료" class="submit submit_origin"></li>
						</ul>
						<div class="clearBothOnly"></div>
					</form>
				</div>
			</article>
			<%--------------------------------- comment시작 ---------------------------------------------------%>
			<div class="pagination">
				<a id="goListButton" class="list">글 목록</a>
				<div class="center-block">
					<ul class="pagination justify-content-center">
					</ul>
				</div>
			</div>
		</div>
		<hr>
		<jsp:include page="../rightside3.jsp" />
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>