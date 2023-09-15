<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>내 정보 - 애니타임</title>
<link rel="stylesheet" type="text/css" href="/Anytime/css/total.css">
<link data-vue-meta="ssr" rel="stylesheet" href="/Anytime/css/body.css">
<style>
body {
	background-color: #EEEEF6 !important;
}

nav {
	border-bottom: 0 !important;
}
</style>
<jsp:include page="../header.jsp" />
</head>
<body id="my">
	<div data-v-05a6f4a4="">
		<div data-v-05a6f4a4="" class="container">
			<section data-v-05a6f4a4="">
				<div data-v-05a6f4a4="" class="title">
					<h1 data-v-05a6f4a4="">내 정보</h1>
					<a data-v-05a6f4a4="" href="logout.com" class="logout">로그아웃</a>
				</div>
				<div data-v-05a6f4a4="" class="profile">
					<img data-v-05a6f4a4="" src="https://cf-fpi.everytime.kr/0.png">
					<h3 data-v-05a6f4a4="">${member.userid}</h3>
					<p data-v-05a6f4a4="">
						<span data-v-05a6f4a4="">${member.nickname}</span>
					</p>
					<p data-v-05a6f4a4="">
						<span data-v-05a6f4a4="">${school.school_name}</span> <span
							data-v-05a6f4a4="">${member.admission_year}</span> <span>학번</span>
					</p>
				</div>
			</section>
			<section data-v-05a6f4a4="">
				<h2 data-v-05a6f4a4="">계정</h2>
				<a data-v-05a6f4a4="" href="certificate.com" class="item">학교 인증</a>
				<a data-v-05a6f4a4="" href="passwordUpdate.com" class="item">비밀번호
					변경</a> <a data-v-05a6f4a4="" href="memberUpdate.com" class="item">내정보
					변경</a>
			</section>
			<section data-v-05a6f4a4="">
				<h2 data-v-05a6f4a4="">커뮤니티</h2>
				<a data-v-05a6f4a4="" href="boardContol.com" class="item">게시판 관리</a>
			</section>

			<section data-v-05a6f4a4="">
				<h2 data-v-05a6f4a4="">기타</h2>
				<a data-v-05a6f4a4="" href="memberDelete.com" class="item">회원 탈퇴</a>
			</section>
		</div>
	</div>
	<jsp:include page="../footer2.jsp" />
	<script id="__INITIAL_STATE__" type="application/json">{"apiServerUrl":"https://api.everytime.kr","appInfo":{"appName":"","appVersion":"","osName":"","osVersion":""},"campusData":{"schoolId":12,"campusId":24,"communityName":"성신여대"},"isLogged":true,"lectureTalkServerUrl":"https://lecture-talk.everytime.kr","pageName":"pages/my/index","pageTitle":"내 정보","sentryDSN":"https://21c694d5302c47699d70d2c3ae22ab27@o4504002973597696.ingest.sentry.io/4504008836448256","requestUser":{"campusName":"성신여대","enterYear":2015,"name":"이윤희","nickname":"윤윤윤윤2","picture":"https://cf-fpi.everytime.kr/0.png","userid":"youni929"}}</script>
</body>
</html>