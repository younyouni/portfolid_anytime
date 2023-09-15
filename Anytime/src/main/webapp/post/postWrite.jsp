<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>애니타임</title>
<jsp:include page="../header.jsp" />
<jsp:include page="../submenu.jsp" />
<link type="text/css" href="/Anytime/css/common.css" rel="stylesheet">
<link type="text/css" href="/Anytime/css/common.partial.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/container.article.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/container.community.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/container.modal.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/post/postwrite.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.js"></script>
<script src="/Anytime/js/list.js"></script>
<script>
function validateForm() {
    var subject = document.querySelector('.write .title').value;
    var content = document.querySelector('.write .smallplaceholder').value;
    
    if (subject.trim() === '' || content.trim() === '') {
        alert('제목과 내용을 모두 입력해주세요.');
        return false;
    }
}

$(document).ready(function() {
const fileInput = document.getElementById('post_file');
const fileNameElement = document.getElementById('file-name');

fileInput.addEventListener('change', (event) => {
  const selectedFile = event.target.files[0];
  if (selectedFile) {
    fileNameElement.textContent = selectedFile.name;
  } else {
    fileNameElement.textContent = '';
  }
	});

});

</script>
</head>
<body>

	<div id="container" class="article">
		<input type="hidden" id="isUser" value="1"> <input
			type="hidden" id="boardId" value="370455"> <br>
		<div class="wrap title">
			<h1>
				<a href="PostList.bo?board_num=${board_num}">${board_name}</a>
			</h1>
			<hr>
		</div>
		<div class="wrap articles">
			<form class="write" action="PostAddAction.bo" method="post"
				enctype="multipart/form-data" name="boardform" onsubmit="validateForm()">
				<p>
					<input name="subject" autocomplete="off" placeholder="글 제목"
						class="title">
				</p>
				<p>
					<textarea name="content"
						placeholder="에브리타임은 누구나 기분 좋게 참여할 수 있는 커뮤니티를 만들기 위해 커뮤니티 이용규칙을 제정하여 운영하고 있습니다. 위반 시 게시물이 삭제되고 서비스 이용이 일정 기간 제한될 수 있습니다. 

아래는 이 게시판에 해당하는 핵심 내용에 대한 요약 사항이며, 게시물 작성 전 커뮤니티 이용규칙 전문을 반드시 확인하시기 바랍니다. 

※ 정치·사회 관련 행위 금지 
- 국가기관, 정치 관련 단체, 언론, 시민단체에 대한 언급 혹은 이와 관련한 행위 
- 정책·외교 또는 정치·정파에 대한 의견, 주장 및 이념, 가치관을 드러내는 행위 
- 성별, 종교, 인종, 출신, 지역, 직업, 이념 등 사회적 이슈에 대한 언급 혹은 이와 관련한 행위 
- 위와 같은 내용으로 유추될 수 있는 비유, 은어 사용 행위 
* 해당 게시물은 시사·이슈 게시판에만 작성 가능합니다. 

※ 홍보 및 판매 관련 행위 금지 
- 영리 여부와 관계 없이 사업체·기관·단체·개인에게 직간접적으로 영향을 줄 수 있는 게시물 작성 행위 
- 위와 관련된 것으로 의심되거나 예상될 수 있는 바이럴 홍보 및 명칭·단어 언급 행위 
* 해당 게시물은 홍보게시판에만 작성 가능합니다. 

※ 불법촬영물 유통 금지
불법촬영물등을 게재할 경우 전기통신사업법에 따라 삭제 조치 및 서비스 이용이 영구적으로 제한될 수 있으며 관련 법률에 따라 처벌받을 수 있습니다. 

※ 그 밖의 규칙 위반 
- 타인의 권리를 침해하거나 불쾌감을 주는 행위 
- 범죄, 불법 행위 등 법령을 위반하는 행위 
- 욕설, 비하, 차별, 혐오, 자살, 폭력 관련 내용을 포함한 게시물 작성 행위 
- 음란물, 성적 수치심을 유발하는 행위 
- 스포일러, 공포, 속임, 놀라게 하는 행위"
						class="smallplaceholder">
</textarea>
				</p>
				<ol class="thumbnails">
					<li class="new"></li>
				</ol>
				<div class="clearBothOnly"></div>

				<ul class="option">

					<!--<li title="해시태그" class="hashtag"></li>-->
					<li title="첨부" class="attach"><label class="file-label">
							<span class="file-name" id="file-name"></span> 파일 선택 <input
							type="file" id="post_file" name="post_file" class="file-input">
					</label></li>
					<li title="완료" class="submit" type="submit">
						<button type="submit" class="btn btn-primary">등록</button>
					</li>
					<!-- <li title="익명" class="anonym"></li> -->
				</ul>
				<div class="clearBothOnly"></div>
				<input type="hidden" name="board_num" value="${board_num}">
			</form>


			<c:forEach var="post" items="${postlist}">
				<article>
					<a class="article"
						href="PostDetailAction.bo?post_num=${post.post_num}"> <!-- * * * * * * * * * * * 추후 이미지 추가 기능 구현됐을때 수정할것 * * * * * * * * * * * 
         		<div class="attachthumbnail" style="background-image: url('../image/test_thumbnail.png');"></div>
         		-->
						<h2 class="medium">${post.subject}</h2>
						<p class="small">${post.content}</p> <time class="small">${post.post_date}</time>
						<h3 class="small">${post.nickname}</h3>
						<ul class="status">
							<li class="attach">${post.file_count}</li>
							<li title="공감" class="vote">${post.like_count}</li>
							<li title="댓글" class="comment">${post.comment_count}</li>
						</ul>
						<hr> <input type="hidden" name="311208877_comment_anonym"
						value="0">
					</a>

					<div class="comments"></div>
				</article>
			</c:forEach>



			<c:set var="board_num" value="${postlist[0].board_num}"
				scope="request" />
			<!-- 보드넘버 추출 -->


			<div class="clearBothOnly"></div>
			<div class="center-block">
				<ul class="pagination justify-content-center">
					<c:if test="${page <= 1 }">
						<li class="page-item hidden"><a class="page-link gray">&nbsp;&nbsp;이전</a>
						</li>
					</c:if>

					<c:if test="${page > 1 }">
						<li class="page-item"><a
							href="PostList.bo?board_num=${board_num}&page=${page-1}"
							class="page-link">&nbsp;&nbsp;이전</a></li>
					</c:if>

					<c:forEach var="a" begin="${startpage}" end="${endpage}">
						<c:if test="${a == page }">
							<li class="page-item active"><a class="page-link">${a}</a></li>
						</c:if>

						<c:if test="${a != page }">
							<li class="page-item"><a
								href="PostList.bo?board_num=${board_num}&page=${a}"
								class="page-link">${a}</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${page >= maxpage }">
						<li class="page-item hidden"><a class="page-link gray">다음&nbsp;&nbsp;</a>
						</li>
					</c:if>
					<c:if test="${page < maxpage }">
						<li class="page-item"><a
							href="PostList.bo?board_num=${board_num}&page=${page+1}"
							class="page-link">다음&nbsp;&nbsp;</a></li>
					</c:if>
				</ul>
			</div>
		</div>


		<hr>


		<jsp:include page="../rightside3.jsp" />


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
		<form id="transferMoimForm" class="modal"
			data-gtm-form-interact-id="0">
			<a title="닫기" class="close"></a>
			<h3>게시판 양도</h3>
			<p>
				<label>양도인 비밀번호</label> <input type="password"
					name="transferer_password" class="text"
					data-gtm-form-interact-field-id="1">
			</p>
			<p>
				<label>피양도인 아이디</label> <input type="text" name="transferee_userid"
					class="text" data-gtm-form-interact-field-id="0">
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
	</div>

	<jsp:include page="../footer.jsp" />

</body>
</html>