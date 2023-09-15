<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>애니타임</title>
<link type="text/css" href="/Anytime/css/common.css" rel="stylesheet">
<link type="text/css" href="/Anytime/css/common.partial.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/container.article.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/container.community.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/container.modal.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.js"></script>
<script src="js/comment.js"></script>
<script>
	var userid = "${userid}";
	var anonymous = ${anonymous};
	var currentUserNum = ${currentUserNum};  /* 현재 로그인한 유저의 고유번호*/
	var writerNum = ${postdata.num}; /* post(게시물) 작성한 유저의 고유번호 */
</script>
<style>
.status.disabled {
  pointer-events: none; /* 클릭 및 이벤트 무시 */
  color: gray; /* 색상 변경 */
  text-decoration: none; /* 밑줄 제거 */
  cursor: not-allowed; /* 마우스 커서 스타일 변경 */
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
					   <!-- 사용자 아이디와 게시물 작성자 아이디가 일치하는 경우에만 수정 버튼 표시 -->
					   <c:choose>
					       <c:when test="${currentUserNum eq postdata.num}">
					           <li class="update" id="updateButton">수정</li>
					           <li class="del">삭제</li>
					       </c:when>
					       <c:otherwise>
					           <li class="messagesend" data-modal="messageSend"
					               data-article-id="${postdata.post_num}" data-is-anonym="${boarddata.anonymous}">쪽지</li>
					           <li class="abuse">신고</li>
					       </c:otherwise>
					   </c:choose>
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
							<li title="취소" class="cancel"
								style="margin-right: 5px; background-image: url('/Anytime/image/cancel.png') !important; background-size: 12px;"></li>
							<li title="완료" class="submit submit_origin"></li>
						</ul>
						<div class="clearBothOnly"></div>
					</form>
				</div>
			</article>
			<div class="clearBothOnly"></div>
			<div class="pagination">
				<a id="goListButton" class="list">글 목록</a>
				<div class="center-block">
					<ul class="pagination justify-content-center">
					</ul>
				</div>
			</div>
			<%--------------------------------- comment시작 ---------------------------------------------------%>
		</div>
		<hr>
		<jsp:include page="../rightside3.jsp" />
	</div>
	<jsp:include page="../footer.jsp" />
	<script>
    $(document).ready(function() {
        // "수정" 버튼을 클릭하면 동작하는 스크립트
        $("#updateButton").click(function() {
            // 사용자 아이디와 게시물 작성자 아이디가 일치하는 경우에만 페이지 이동
            if (currentUserNum == writerNum) {
                // post_num 값 가져오기
                var post_num = $("#post_num").val();
                
                // postModify.jsp 페이지로 이동
                window.location.href = "PostModifyView.bo?post_num=" + post_num    
            }
        });
        
     // "삭제" 버튼을 클릭하면 동작하는 스크립트
        $(".del").click(function() {
            // 사용자 아이디와 게시물 작성자 아이디가 일치하는 경우에만 확인 창을 띄웁니다.
            if (currentUserNum == writerNum) {
                var confirmation = confirm("내용을 삭제하시겠습니까?");
                if (confirmation) {
                    // 삭제 동작을 수행합니다.
                    var post_num = $("#post_num").val();

                    $.ajax({
                        type: "POST",
                        url: "PostDeleteAction.bo",  // 실제 삭제 처리를 담당하는 서버 측 URL
                        data: { post_num: post_num },
                        success: function(response) {
                            if (response == 0) {
                                alert("삭제 실패했습니다.");
                            } else {
                                // 삭제 성공 시, 해당 게시물을 화면에서 숨깁니다.
                                alert("게시물이 삭제되었습니다.");
                                window.location.href = "PostList.bo?board_num=" + ${boarddata.board_num}
                                $(".article").hide();  // 해당 게시물 영역을 숨깁니다.
                            }
                        },
                        error: function() {
                            alert("삭제 요청 실패했습니다.");
                        }
                    });
                }
            }
        });
     
        $("#goListButton").click(function() {
        	window.location.href = "PostList.bo?board_num=" + ${boarddata.board_num}
        });
     	
    });
</script>
</body>
</html>