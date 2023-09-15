<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 보기-수정/삭제/공지로설정</title>
<jsp:include page="../header.jsp" />
<link type="text/css" href="/Anytime/css/common.css" rel="stylesheet">
<link type="text/css" href="/Anytime/css/common.partial.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/container.article.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/container.community.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/container.modal.css"
	rel="stylesheet">

</head>
<body class="selectDisabled" style="">
	<div id="submenu">
		<div class="wrap">
			<div class="divider"></div>
			<div class="group">
				<ul>
					<li><a href="/410136" data-id="410136">컴공과</a></li>
					<li><a href="/374150" data-id="374150">산디과</a></li>
					<li><a href="/451992" data-id="451992">법학과</a></li>
				</ul>
			</div>
			<div class="group">
				<ul>
					<li><a href="/385967" data-id="385967">경영학과</a></li>
					<li><a href="/374637" data-id="374637">국문과</a></li>
					<li><a href="/374637" data-id="374637">기계공학과</a></li>
				</ul>
			</div>
			<div class="divider"></div>
			<div class="group">
				<ul>
					<li><a href="/410136" data-id="410136">새내기게시판</a></li>
					<li><a href="/374150" data-id="374150">취업준비</a></li>
					<li><a href="/451992" data-id="451992">알바게시판</a></li>
				</ul>
			</div>
			<div class="divider"></div>
			<div class="group">
				<ul>
					<li><a href="/413675" data-id="413675" class="new">총학생회</a></li>
					<li><a href="/256283" data-id="256283">성신학보사</a></li>
					<li><a href="/399202" data-id="399202">성신미러사</a></li>
					<li><a href="/502420" data-id="502420">취업서포터즈</a></li>
					<li><a href="/256401" data-id="256401">학생복지위원회</a></li>
					<li><a href="/481941" data-id="481941">교육방송국</a></li>
					<li><a href="/521796" data-id="521796">동아리연합회</a></li>
					<li><a class="more">더 보기</a></li>
				</ul>
			</div>
			<div class="group hidden">
				<ul>
					<li><a href="/480157" data-id="480157" class="new">사화과학대
							학생회</a></li>
					<li><a href="/484857" data-id="484857">간호대학 학생회</a></li>
					<li><a href="/486426" data-id="486426" class="new">공과대 학생회</a></li>
					<li><a href="/521949" data-id="521949" class="new">음악대학
							학생회</a></li>
					<li><a href="/521981" data-id="521981">미술대학 학생회</a></li>
					<li><a href="/522969" data-id="522969" class="new">법과대 학생회</a></li>
					<li><a href="/524988" data-id="524988">자연과학대학 학생회</a></li>
				</ul>
			</div>
			<div class="divider"></div>
			<div class="group">
				<ul>
					<li><a href="/380513" data-id="380513" class="new">💉 나이팅
							게일 of Sungshin 💉</a></li>
					<li><a href="/389562" data-id="389562" class="new">선진경영❤</a></li>
					<li><a href="/373768" data-id="373768" class="new">👩🏻‍💻산디스탈👩🏻‍💻</a></li>
					<li><a href="/389931" data-id="389931" class="new">💖융보공잘보공드루와💖</a></li>
					<li><a href="/483256" data-id="483256" class="new">⚖️자주성신
							정의법대 법학부⚖️</a></li>
					<li><a href="/386867" data-id="386867" class="new">💻AI는
							정시에 만나💻 </a></li>
					<li><a href="/383263" data-id="383263" class="new">🦍🦍미대모임
							🦍🦍</a></li>
					<li><a class="more">더 보기</a></li>
				</ul>
			</div>
			<div class="group hidden">
				<ul>
					<li><a href="/380947" data-id="380947" class="new">🌸써디공서디공🌸</a></li>
					<li><a href="/412524" data-id="412524" class="new">👩🏻 🏫
							성신의 뿌리, 민족사범 🏫</a></li>
					<li><a href="/409942" data-id="409942" class="new">진리통계</a></li>
					<li><a href="/394952" data-id="394952" class="new">💵지성
							경제💵</a></li>
					<li><a href="/392012" data-id="392012" class="new">🌱생생정보톡🍀</a></li>
					<li><a href="/403480" data-id="403480" class="new">🎼수정이들의
							음대🎶</a></li>
					<li><a href="/413582" data-id="413582" class="new">모여라
							세계글비 🌏🌍🌎</a></li>
					<li><a href="/412537" data-id="412537" class="new">으리으리한
							의류</a></li>
				</ul>
			</div>
			<div class="group hidden">
				<ul>
					<li><a href="/399725" data-id="399725" class="new">🧠심리왕
							왕심리👑</a></li>
					<li><a href="/413311" data-id="413311" class="new">수정이는
							정외진 길로 걷지 않아!</a></li>
					<li><a href="/392070" data-id="392070" class="new">영문모를수정</a></li>
					<li><a href="/413291" data-id="413291" class="new">사복을
							입고👩</a></li>
					<li><a href="/380568" data-id="380568" class="new">케미 좋은
							수정이들</a></li>
					<li><a href="/412516" data-id="412516">📖나랏말싸미 참겨레국문📖</a></li>
					<li><a href="/415352" data-id="415352" class="new">❄️메디컬보드❄️</a></li>
					<li><a href="/416441" data-id="416441">오지고 지리고🌏</a></li>
				</ul>
			</div>
			<div class="group hidden">
				<ul>
					<li><a href="/388552" data-id="388552" class="new">융대수정이들모임🎶🎬🤹
							♀️</a></li>
					<li><a href="/464976" data-id="464976" class="new">뷰티산업
							Su정🎨</a></li>
					<li><a href="/413327" data-id="413327" class="new">정문? 후문?
							아你고 중문으로 와 🇨🇳</a></li>
					<li><a href="/415600" data-id="415600" class="new">🍎푸드덕
							푸드덕🍎</a></li>
					<li><a href="/415627" data-id="415627" class="new">🌿순수청정🌿</a></li>
					<li><a href="/413637" data-id="413637" class="new">🍇🥕세상은
							요식영🍓🍋</a></li>
					<li><a href="/390022" data-id="390022">🇫🇷프문(불문) 수정🇫🇷</a></li>
					<li><a href="/493838" data-id="493838">🎫 킵예경 킵고잉 🎫</a></li>
				</ul>
			</div>
			<div class="divider"></div>
			<hr>
		</div>
		<input type="hidden" id="communityCampusId" value="24">
	</div>
	<div id="container" class="article">
		<input type="hidden" id="isUser" value="1"> <input
			type="hidden" id="boardId" value="528552">
		<aside class="none">
			<div class="title">
				<a class="hamburger"></a>
				<h1>
					<a href="/528552">고사모</a>
				</h1>
			</div>
		</aside>
		<div class="wrap title">
			<ol class="buttons">
				<li><a id="manageMoim">관리하기</a></li>
			</ol>
			<h1>
				<a href="/528552">고사모</a>
			</h1>
			<p>test</p>
			<hr>
		</div>
		<div class="wrap articles">
			<a id="writeArticleButton" style="display: none;">새 글을 작성해주세요!</a>
			<article>
				<a class="article"><img src="https://cf-fpi.everytime.kr/0.png"
					class="picture large">
					<div class="profile">
						<h3 class="large">익명</h3>
						<time class="large">방금</time>
					</div>
					<ul class="status">
						<li class="setnotice">공지로 설정</li>
						<li class="update">수정</li>
						<li class="del">삭제</li>
					</ul>
					<hr>
					<h2 class="large">고양이</h2>
					<p class="large">고양이</p>
					<div class="attaches multiple">
						<figure class="attach">
							<img
								src="https://cf-ea.everytime.kr/attach_thumbnail/238/60470391/everytime-web-1690971453781.jpg?Expires=1690972365&amp;Key-Pair-Id=APKAICU6XZKH23IGASFA&amp;Signature=Siayd4jdkLdY8TNOgt5pNjS8nG5ye6K2Av~TzGfpYkZ5i1L47wKDI6e-Gp7s~G8DvfsSxApeHRi9mFIF7f8aNu0k5QG4VMDkLDghfG~ANQcixGs3OKpu-ppTh7QjufI16Kbu3zubWyuMnZhHThMwJoGDBT53A4lUE-Y2IozlVlrtXoISSApFkP4W-a9KeqRcuFZW4mhy8gXJP0CJ0rUB9xjmdjhNuu4KfMB54iRCSsyuzu9pOrMSO1W2r5eR0epOPC6Y2WmkJUG~DcDWsmLweDHfPMvV6lbo~z798qhETI~FLLjZqlwhCNVH1hxVS8Yy8-IbLZ32ziPeGsATND0CRg__">
						</figure>
						<figure class="attach">
							<img
								src="https://cf-ea.everytime.kr/attach_thumbnail/55/60470404/everytime-web-1690971466105.jpg?Expires=1690972365&amp;Key-Pair-Id=APKAICU6XZKH23IGASFA&amp;Signature=U2S9XMugd~uqXMnPF7coWybdat-~LyjbUBj-oyD24PUISzE0NgQdSdGKn4y4znUd5p8O2QvkyoGnAX3hrZBWD7RgYYo13v9paZMEAjjzGMJLvqPA-B28MNKXSR89KqVvpTw-x6FkDben7GvOFRgaZZsoDC-HWbc3A7CW2K7PFehSNUnJbQbDnRix3qPD9MNGF3CKiuN5X1W3wVgVxzv9Mcn-8jBmTRvtxGGiD5rztf0RnTD7lWeFKFfKEKudkOwBtvSH9HZfkNM8wRV8grs9AEvcoQvQc8M4fln~Q9mqbMtQv2MAOrw6nUbYz6yCxuVR9H6o8jHqBE1J4bnMAiSbSA__">
						</figure>
					</div>
					<hr>
					<ul class="status left">
						<li class="attach">2</li>
						<li title="공감" class="vote">0</li>
						<li title="댓글" class="comment">0</li>
						<li title="스크랩" class="scrap">0</li>
					</ul>
					<hr>
					<div class="buttons">
						<span class="posvote">공감</span><span class="scrap">스크랩</span>
					</div> <input type="hidden" name="311382964_comment_anonym" value="0"></a>
				<div class="comments" style="display: block;">
					<form class="writecomment">
						<input type="text" name="text" maxlength="300" autocomplete="off"
							placeholder="댓글을 입력하세요." class="text">
						<ul class="option">
							<li title="익명" class="anonym"></li>
							<li title="완료" class="submit"></li>
						</ul>
						<div class="clearBothOnly"></div>
					</form>
				</div>
			</article>
			<div class="clearBothOnly"></div>
			<div class="pagination">
				<a id="goListButton" class="list">글 목록</a>
			</div>
		</div>
		<hr>
		<jsp:include page="../rightside2.jsp" />
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
		<form id="attachThumbnailForm" class="modal"
			style="margin-left: -200px; margin-top: -117.5px; display: none;">
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