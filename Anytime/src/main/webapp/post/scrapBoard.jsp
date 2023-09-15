<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>애니타임</title>

<jsp:include page="../header.jsp" />

<meta charset="utf-8">
<meta name="referrer" content="origin">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta property="fb:app_id" content="258676027581965">
<meta property="og:type" content="website">
<meta property="og:image"
	content="https://everytime.kr/images/og_image.png">
<meta property="og:url" content="https://everytime.kr/myscrap">
<meta property="og:site_name" content="에브리타임">
<meta property="og:title" content="에브리타임">
<meta property="og:description"
	content="전국 400개 대학을 지원하는 대학교 커뮤니티 및 시간표 서비스. 시간표 작성 및 학업 관리, 학교 생활 정보, 학교별 익명 커뮤니티 기능을 제공합니다.">
<meta name="description"
	content="전국 400개 대학을 지원하는 대학교 커뮤니티 및 시간표 서비스. 시간표 작성 및 학업 관리, 학교 생활 정보, 학교별 익명 커뮤니티 기능을 제공합니다.">
<meta name="keywords"
	content="에브리타임, 에타, everytime, 시간표, 수강신청, 강의평가, 학점계산기, 학식, 오늘의학식, 책방, 중고책, 대학생, 대학교, 대학, 대학생 시간표, 대학교 시간표, 대학생 커뮤니티, 대학교 커뮤니티, 시간표 앱, 시간표 어플">
<meta name="naver-site-verification"
	content="7366738375e320e44bd1c743b364db13086a7b0e">
<meta name="robots" content="noindex">

<link type="text/css" href="../css/common.css" rel="stylesheet">
<link type="text/css" href="../css/common.partial.css" rel="stylesheet">
<link type="text/css" href="../css/container.article.css"
	rel="stylesheet">
<link type="text/css" href="../css/container.community.css"
	rel="stylesheet">
<link type="text/css" href="../css/container.modal.css" rel="stylesheet">
<link type="text/css" href="../css/anytime_boardlist.css"
	rel="stylesheet">
<link href="/favicon.ico" rel="shortcut icon">
</head>
<body style="">



	<div id="submenu">
		<div class="wrap">
			<div class="divider"></div>
			<div class="group">
				<ul>
					<li><a href="/370455" data-id="370455" class="new">자유게시판</a></li>
					<li><a href="/257667" data-id="257667" class="new">비밀게시판</a></li>
					<li><a href="/385967" data-id="385967" class="new">졸업생게시판</a></li>
					<li><a href="/374637" data-id="374637" class="new">새내기게시판</a></li>
					<li><a href="/482583" data-id="482583" class="new">시사·이슈</a></li>
					<li><a href="/370476" data-id="370476" class="new">장터게시판</a></li>
					<li><a href="/258610" data-id="258610" class="new">정보게시판</a></li>
					<li><a href="/367437" data-id="367437" class="new">홍보게시판</a></li>
				</ul>
			</div>
			<div class="group">
				<ul>
					<li><a href="/418775" data-id="418775" class="new">동아리·학회</a></li>
				</ul>
			</div>
			<div class="divider"></div>
			<div class="group">
				<ul>
					<li><a href="/370506" data-id="370506" class="new">취업·진로</a></li>
					<li><a href="/408938" data-id="408938" class="new">회계 동아리
							in 숭실</a></li>
					<li><a href="/429747" data-id="429747">☆대학원 가자☆</a></li>
				</ul>
			</div>
			<div class="divider"></div>
			<div class="group">
				<ul>
					<li><a href="/410136" data-id="410136">숭대시보</a></li>
					<li><a href="/374150" data-id="374150">인터넷방송국</a></li>
					<li><a href="/451992" data-id="451992">방송국</a></li>
					<li><a href="/431825" data-id="431825" class="new">중앙감사위원회</a></li>
					<li><a href="/483370" data-id="483370">영자신문사</a></li>
					<li><a href="/502854" data-id="502854">학생복지위원회</a></li>
				</ul>
			</div>
			<div class="divider"></div>
			<div class="group">
				<ul>
					<li><a href="/454735" data-id="454735" class="new">교환학생가자~~</a></li>
					<li><a href="/456387" data-id="456387" class="new">난힘들때코딩을해</a></li>
					<li><a href="/377445" data-id="377445" class="new">LGBTQ+</a></li>
					<li><a href="/388627" data-id="388627" class="new">게임 게시판</a></li>
					<li><a href="/511972" data-id="511972" class="new">kbo 야구
							게시판 ⚾️</a></li>
					<li><a href="/382899" data-id="382899">헬스/다이어트 게시판</a></li>
					<li><a href="/409246" data-id="409246">음악게시판</a></li>
					<li><a href="/380089" data-id="380089">네거티브 동아리</a></li>
				</ul>
			</div>
			<div class="group">
				<ul>
					<li><a href="/372693" data-id="372693" class="new">덕사리를
							찾아서</a></li>
					<li><a href="/424431" data-id="424431">짜증날때 소리지르는 곳</a></li>
					<li><a href="/374993" data-id="374993">망한 숭실대 상권이라도 밥은 먹자</a></li>
					<li><a href="/378826" data-id="378826" class="new">혼술회</a></li>
					<li><a href="/519386" data-id="519386">숭신소(셀프소개팅)</a></li>
					<li><a href="/community/search" class="search">게시판 찾기</a></li>
				</ul>
			</div>
			<div class="divider"></div>
			<hr>
		</div>
		<input type="hidden" id="communityCampusId" value="37">
	</div>
	<div id="container" class="article">
		<input type="hidden" id="isUser" value="1"> <input
			type="hidden" id="boardId" value="myscrap">
		<aside class="none">
			<div class="title">
				<a class="hamburger"></a>
				<h1>
					<a href="/myscrap">내 스크랩</a>
				</h1>
			</div>
		</aside>
		<div class="wrap title">
			<h1>
				<a href="/myscrap">내 스크랩</a>
			</h1>
			<hr>
		</div>
		<div class="wrap articles">
			<article>
				<a class="article" href="/370455/v/310075482"><img
					src="https://cf-fpi.everytime.kr/0.png" class="picture medium">
					<h3 class="medium">익명</h3> <time class="medium">07/17 17:15</time>
					<hr>
					<h2 class="medium bold">방학근로 출근부</h2>
					<p class="medium">실수로 잊고 출근버튼 늦게 눌렀는데 근로지 담당자님이 너무 바빠서 안고쳐주면 정상
						출근했어도 늦은만큼 근로비 걍 못받는거야..?</p> <a href="/370455" class="boardname">자유게시판</a>
					<ul class="status">
						<li class="removescrap">스크랩 취소</li>
						<li title="공감" class="vote">0</li>
						<li title="댓글" class="comment">8</li>
					</ul>
					<hr> <input type="hidden" name="310075482_comment_anonym"
					value="0"></a>
				<div class="comments"></div>
			</article>
			<article>
				<a class="article" href="/370455/v/309911863"><img
					src="https://cf-fpi.everytime.kr/5684523/1645347131.jpg"
					class="picture medium">
					<h3 class="medium">영웅좌</h3> <time class="medium">07/15 14:15</time>
					<hr>
					<h2 class="medium bold">미국 고냥이</h2>
					<p class="medium">🐱</p> <a href="/370455" class="boardname">자유게시판</a>
					<ul class="status">
						<li class="removescrap">스크랩 취소</li>
						<li class="attach">7</li>
						<li title="공감" class="vote">5</li>
						<li title="댓글" class="comment">8</li>
					</ul>
					<hr> <input type="hidden" name="309911863_comment_anonym"
					value="0"></a>
				<div class="comments"></div>
			</article>
			<article>
				<a class="article" href="/370506/v/163011393"><img
					src="https://cf-fpi.everytime.kr/0.png" class="picture medium">
					<h3 class="medium">익명</h3> <time class="medium">21/01/21
						17:38</time>
					<hr>
					<h2 class="medium bold">자기소개서 합격률을 2배로 올려줄 간단한 방법(펌)</h2>
					<p class="medium">
						유튜브 봉봉tv님이 독취사에 작성한 글인데 허락받고 퍼왔습니다.<br>유튜브링크<br>https://www.youtube.com/channel/UCfG4XzuQzpnvS1hMje6sXsw<br>
						<br>---------------------------------------
					</p> <a href="/370506" class="boardname">취업·진로</a>
					<ul class="status">
						<li class="removescrap">스크랩 취소</li>
						<li title="공감" class="vote">271</li>
						<li title="댓글" class="comment">34</li>
					</ul>
					<hr> <input type="hidden" name="163011393_comment_anonym"
					value="0"></a>
				<div class="comments"></div>
			</article>
			<article>
				<a class="article" href="/258610/v/159436406"><img
					src="https://cf-fpi.everytime.kr/0.png" class="picture medium">
					<h3 class="medium">익명</h3> <time class="medium">20/12/28
						12:04</time>
					<hr>
					<h2 class="medium bold">심심해서 써보는 노트북+태블릿 구매 팁</h2>
					<p class="medium">
						이번에 새내기들도 슬슬 들어올 타이밍이라 노트북+태블릿 관련 정리글 하나 쓰고 감.<br>노트북은 데탑보다
						가격은 비싼데 수명은 짦음. <br>정말 필요해서 사는건지 지름신이 불러서 사는건지 명확하게 했으면 좋겠음.<br>컴학같은
						코딩 많이 할거같은 과도 1학년때는 성능좋은
					</p> <a href="/258610" class="boardname">정보게시판</a>
					<ul class="status">
						<li class="removescrap">스크랩 취소</li>
						<li title="공감" class="vote">328</li>
						<li title="댓글" class="comment">130</li>
					</ul>
					<hr> <input type="hidden" name="159436406_comment_anonym"
					value="0"></a>
				<div class="comments"></div>
			</article>
			<article>
				<a class="article" href="/370455/v/136594843"><img
					src="https://cf-fpi.everytime.kr/0.png" class="picture medium">
					<h3 class="medium">익명</h3> <time class="medium">20/08/13
						20:09</time>
					<hr>
					<h2 class="medium bold">수강신청, 학점 얻는 법, 재수강, 계절학기 등에 대한 잡다한
						정보글(2023.2.17 업데이트)</h2>
					<p class="medium">
						Kmooc<br>http://www.kmooc.kr/ <br>여기 무료 인강사이트인데 들어가서
						강좌찾기-분야별강좌에서 10주차 이상인 인강을 듣고 합격하면 이수증을 받고, 그걸 학교에 내면 1학점 교양학점으로
						쳐줌.<br>학교 다니면서 총 3개 이수증을 낼수있음. 그러니까 총
					</p> <a href="/370455" class="boardname">자유게시판</a>
					<ul class="status">
						<li class="removescrap">스크랩 취소</li>
						<li class="attach">7</li>
						<li title="공감" class="vote">312</li>
						<li title="댓글" class="comment">213</li>
					</ul>
					<hr> <input type="hidden" name="136594843_comment_anonym"
					value="0"></a>
				<div class="comments"></div>
			</article>
			<article>
				<a class="article" href="/367437/v/119271408"><img
					src="https://cf-fpi.everytime.kr/0.png" class="picture medium">
					<h3 class="medium">익명</h3> <time class="medium">20/05/03
						16:21</time>
					<hr>
					<h2 class="medium bold">🌏숭실대 국제동아리 ICY 준비위원을 모집합니다!!🌏</h2>
					<p class="medium">
						⭐️국제청년센터는 2010년 미국에서 처음 설립되어 서울, 부산, 홍콩, 북경, 상하이에 지부를 두고 있는 국제NGO로
						국제청년의 나눔과 교류, 인권신장을 위해 활동하고 있습니다.<br>⭐️국제청년센터에서는 국내대학 최초로 숭실대
						ICY(International Club for Yout
					</p> <a href="/367437" class="boardname">홍보게시판</a>
					<ul class="status">
						<li class="removescrap">스크랩 취소</li>
						<li class="attach">1</li>
						<li title="공감" class="vote">1</li>
						<li title="댓글" class="comment">4</li>
					</ul>
					<hr> <input type="hidden" name="119271408_comment_anonym"
					value="0"></a>
				<div class="comments"></div>
			</article>
			<article>
				<a class="article" href="/370455/v/114932803"><img
					src="https://cf-fpi.everytime.kr/0.png" class="picture medium">
					<h3 class="medium">익명</h3> <time class="medium">20/04/09
						06:06</time>
					<hr>
					<h2 class="medium bold">핫게 간 강의 다운로드법 보충해줄게</h2>
					<p class="medium">
						능력자분께서 관리자도구 활용해서 강의 다운받는거 올려주셔서 나도 잘 썼고 활용하는 사람 많을 것 같은데,<br>강의들
						중 audio class어쩌고 뜨는건 다운받으려고 하면 오디오파일만 있는 강의가 있더라구ㅠㅠ<br>반디캠쓴다는
						사람도 봤지만 평가판은 10분 까지만 녹화가 되고
					</p> <a href="/370455" class="boardname">자유게시판</a>
					<ul class="status">
						<li class="removescrap">스크랩 취소</li>
						<li class="attach">3</li>
						<li title="공감" class="vote">113</li>
						<li title="댓글" class="comment">31</li>
					</ul>
					<hr> <input type="hidden" name="114932803_comment_anonym"
					value="0"></a>
				<div class="comments"></div>
			</article>
			<article>
				<a class="article" href="/370455/v/110819840"><img
					src="https://cf-fpi.everytime.kr/0.png" class="picture medium">
					<h3 class="medium">익명</h3> <time class="medium">20/03/19
						17:22</time>
					<hr>
					<h2 class="medium bold">동영상 강의 다운 받아서 보는 방법</h2>
					<p class="medium">
						스트리밍 방식이 너무 끊겨서 강의에 집중을 할 수 없어서<br>동영상 다운 받아 보게 되었는데 유용하여
						공유하고자 합니다.<br>1. 크롬에서 동영상 강의를 틀어 둔 상태에서 F12 로 개발자 도구에 들어갑니다.<br>2.
						좌측 상단 Elements 왼쪽 커서 모양 아이콘 클
					</p> <a href="/370455" class="boardname">자유게시판</a>
					<ul class="status">
						<li class="removescrap">스크랩 취소</li>
						<li class="attach">3</li>
						<li title="공감" class="vote">488</li>
						<li title="댓글" class="comment">77</li>
					</ul>
					<hr> <input type="hidden" name="110819840_comment_anonym"
					value="0"></a>
				<div class="comments"></div>
			</article>
			<article>
				<a class="article" href="/418775/v/103291129"><img
					src="https://cf-fpi.everytime.kr/0.png" class="picture medium">
					<h3 class="medium">익명</h3> <time class="medium">20/02/17
						00:15</time>
					<hr>
					<h2 class="medium bold">[~03.15]금융동아리 SFA 28기 모집</h2>
					<p class="medium">
						SFA에서 2020년도 1학기 28기 신입회원을 모집합니다!!<br>SFA(Soongsil Finance
						Association)는 금융권 취업에 관심있는 학우, 경제 관련 지식을 쌓고 싶은 학우들을 위한 동아리입니다.<br>SFA
						활동을 통하여 전공/교양에서 배우기 힘든 실전적인
					</p> <a href="/418775" class="boardname">동아리·학회</a>
					<ul class="status">
						<li class="removescrap">스크랩 취소</li>
						<li class="attach">1</li>
						<li title="공감" class="vote">0</li>
						<li title="댓글" class="comment">0</li>
					</ul>
					<hr> <input type="hidden" name="103291129_comment_anonym"
					value="0"></a>
				<div class="comments"></div>
			</article>
			<article>
				<a class="article" href="/258610/v/99250592"><img
					src="https://cf-fpi.everytime.kr/0.png" class="picture medium">
					<h3 class="disabled medium">(알수없음)</h3> <time class="medium">20/01/09
						15:15</time>
					<hr>
					<h2 class="medium bold">수강신청 개꿀팁 알려드립니다</h2>
					<p class="medium">수강신청 너두 할수있어!</p> <a href="/258610"
					class="boardname">정보게시판</a>
					<ul class="status">
						<li class="removescrap">스크랩 취소</li>
						<li class="attach">10</li>
						<li title="공감" class="vote">108</li>
						<li title="댓글" class="comment">33</li>
					</ul>
					<hr> <input type="hidden" name="99250592_comment_anonym"
					value="0"></a>
				<div class="comments"></div>
			</article>
			<article>
				<a class="article" href="/418775/v/79479255"><img
					src="https://cf-fpi.everytime.kr/0.png" class="picture medium">
					<h3 class="disabled medium">(알수없음)</h3> <time class="medium">19/06/16
						14:14</time>
					<hr>
					<h2 class="medium bold">외국인 관광봉사 대학생단체 SeoulMate21기 모집!</h2>
					<p class="medium">
						제목<br>💛외국인 관광봉사 대학생단체 SeoulMate 21기 모집💛<br>본문<br>💛🌍SeoulMate(서울메이트)
						21기를 모집합니다🌎💛<br>서울 살면서 외국인들에게 소개할만한 서울 관광지는 가본 적이 없다.🙅🏻<br>외국인
						친구를 사귀고 싶다! 글로벌한 인맥을
					</p> <span class="more">... 더 보기</span><a href="/418775"
					class="boardname">동아리·학회</a>
					<ul class="status">
						<li class="removescrap">스크랩 취소</li>
						<li class="attach">10</li>
						<li title="공감" class="vote">3</li>
						<li title="댓글" class="comment">6</li>
					</ul>
					<hr> <input type="hidden" name="79479255_comment_anonym"
					value="0"></a>
				<div class="comments"></div>
			</article>
			<article>
				<a class="article" href="/370455/v/50827177"><img
					src="https://cf-fpi.everytime.kr/0.png" class="picture medium">
					<h3 class="disabled medium">(알수없음)</h3> <time class="medium">18/08/22
						07:39</time>
					<hr>
					<h2 class="medium bold">아이린님 자료 모음</h2>
					<p class="medium">
						사이버강의 족보링크(pc)<br>https://drive.google.com/file/d/11-0G52vcb9S3_G2io-7DTpqCcTu4g5NE/view<br>절대평가,
						PF 교양과목 리스트 <br>3 PF / 한반도와평화선교 <br>2 PF / 생활안전및대처방법
						&lt;
					</p> <a href="/370455" class="boardname">자유게시판</a>
					<ul class="status">
						<li class="removescrap">스크랩 취소</li>
						<li title="공감" class="vote">94</li>
						<li title="댓글" class="comment">269</li>
					</ul>
					<hr> <input type="hidden" name="50827177_comment_anonym"
					value="0"></a>
				<div class="comments"></div>
			</article>
			<div class="clearBothOnly"></div>
			<div class="pagination">
				<a href="/myscrap/p/2" class="next">다음</a>
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