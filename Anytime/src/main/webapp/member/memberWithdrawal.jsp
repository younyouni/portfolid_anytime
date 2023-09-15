<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp" />
<head>
<title>회원 탈퇴 - 에브리타임</title>
<link data-vue-meta="ssr" rel="preload"
	href="/Anytime/css/myInfo/13659cd4ee3a59b7dba9.css">
<link data-vue-meta="ssr" rel="preload"
	href="/Anytime/css/myInfo/c712f1f68ad60ee23285.css">
<link data-vue-meta="ssr" rel="stylesheet"
	href="/Anytime/css/myInfo/0a14fb2000808afce700.css">
<link rel="stylesheet" type="text/css"
	href="/Anytime/css/myInfo/c712f1f68ad60ee23285.css">
<link rel="stylesheet" type="text/css"
	href="/Anytime/css/myInfo/13659cd4ee3a59b7dba9.css">
</head>
<style>
nav {
	border-bottom: 0 !important;
}
</style> 
<body>
	<div data-v-75203738="">
		<div data-v-ffaea544="" class="navbar">
			<a data-v-ffaea544="" class="hamburger">메뉴 열기</a>
			<h1 data-v-ffaea544="">회원 탈퇴</h1>
		</div>
		<form data-v-75203738="" action="memberDeleteProcess.com" class="container">
			<section data-v-75203738="">
				<h1 data-v-75203738="">회원 탈퇴</h1>
				<div data-v-75203738="" class="input">
					<div data-v-75203738="" class="label">
						<label data-v-75203738="">계정 비밀번호</label>
					</div>
					<input data-v-75203738="" type="password" name="password" maxlength="20"
						placeholder="계정 비밀번호">
				</div>
				<div data-v-75203738="" class="rules">
					<!---->
					<p data-v-75203738="">
						※ 탈퇴 후 개인 정보, 시간표 등의 데이터가 삭제되며, 복구할 수 없습니다.<br data-v-75203738="">
						※ 다시 가입하여도, 게시판 등 이용 제한 기록은 초기화되지 않습니다.<br data-v-75203738="">
						※ 작성한 게시물은 삭제되지 않으며, (알수없음)으로 닉네임이 표시됩니다.<br data-v-75203738="">
					</p>
				</div>
				<input data-v-75203738="" type="submit" value="회원 탈퇴"
					id="withdrawButton">
			</section>
		</form>
		<jsp:include page="../footer2.jsp" />
	</div>
	<script id="__INITIAL_STATE__" type="application/json">{"apiServerUrl":"https://api.everytime.kr","appInfo":{"appName":"","appVersion":"","osName":"","osVersion":""},"campusData":{"schoolId":12,"campusId":24,"communityName":"성신여대"},"isLogged":true,"lectureTalkServerUrl":"https://lecture-talk.everytime.kr","pageName":"pages/my/withdrawal","pageTitle":"회원 탈퇴","sentryDSN":"https://21c694d5302c47699d70d2c3ae22ab27@o4504002973597696.ingest.sentry.io/4504008836448256"}</script>
</body>
</html>