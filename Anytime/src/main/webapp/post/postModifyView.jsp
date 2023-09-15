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
<link type="text/css" href="/Anytime/css/post/postwrite.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.js"></script>
<script>
	$(document).ready(function() {

		$('input[type=file]').change(function(event) {

			console.log($(this).val());

			const inputfile = $(this).val().split('\\');
			const filename = inputfile[inputfile.length - 1];
			const pattern = /(gif|jpg|jpeg|png)$/i;
			$('#filevalue').val(inputfile[inputfile.length - 1]);

			if (pattern.test(filename)) {
				$('#file-name').text(filename);
				const reader = new FileReader();
				reader.readAsDataURL(event.target.files[0]);
				reader.onload = function() {
					$('#fileName').attr('src', this.result);
				};
			} else {
				alert('이미지 파일(gif, jpg, jpeg, png)이 아닌 경우 무시됩니다.');
				$(this).val('')
			}
		})

	})
</script>
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

			<form class="write"
				action="PostModifyAction.bo?post_num=${postdata.post_num}"
				method="post" enctype="multipart/form-data" name="boardform"
				onsubmit="validateForm()">
				<p>
					<input name="subject" autocomplete="off" placeholder="글 제목"
						class="title" value="${postdata.subject}">
				</p>
				<p>
					<textarea name="content" class="smallplaceholder">${postdata.content}</textarea>
				</p>

				<ul class="option">
					<li title="첨부" class="attach"><label class="file-label">
							<c:if test="${!empty paths }">
								<span class="file-name" id="file-name">${paths.get(0)}</span>
							</c:if> <c:if test="${empty paths }">
								<span class="file-name" id="file-name"></span>
							</c:if> <input type="file" name="fileName" class="fileName"
							style="display: none;"> <c:forEach var="b"
								items="${paths}">
								<input type="hidden" id="filevalue" name="check" value="${b}">
								<figure class="attach">
									<img id="fileName" src="/Anytime/boardupload/${b}" alt="none"
										style="width: 30px; height: 30px; margin: -13px 0 0 250px;">
								</figure>
							</c:forEach>
					</label>
					</li>

					<!-- 
					 <input type="hidden" name="check" value="${memberinfo.memberfile}">
					 -->
					<li title="완료" class="submit">
						<button type="submit" class="btn btn-primary">등록</button>
					</li>
					<li title="익명" class="anonym active"></li>
					<li title="질문" class="question"></li>
				</ul>
				<div class="clearBothOnly"></div>
				<input type="hidden" name="article_id" value="183955867">
			</form>
			<a id="writeArticleButton" style="display: none;">새 글을 작성해주세요!</a>
			</article>
			<div class="clearBothOnly"></div>
			<div class="pagination">
				<a href="PostList.bo?board_num=${boarddata.board_num}"
					id="goListButton" class="list">글 목록</a>
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
