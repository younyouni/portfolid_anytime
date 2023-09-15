<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>애니타임</title>
<link type="text/css" href="/Anytime/css/login/common.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/login/main.common.css"
	rel="stylesheet">
<link type="text/css" href="/Anytime/css/login/main.login.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.js"></script>	
	
<script>
	$(function() {
	
		const userid = '${userid}';
		if (userid) {
			$("#userid").val(userid);
			$("#autologin").prop('checked', true);
		}
	})
</script>
</head>
<body style="">
	<div id="container" class="login">
		<h1 class="logo">
			<a href="/">애니타임</a>
		</h1>
		<form action="loginProcess.com" method="post"
			data-gtm-form-interact-id="0">
			<p class="input">
				<input type="text" id ="userid" name="userid" maxlength="20" class="text"
					placeholder="아이디" data-gtm-form-interact-field-id="0">
			</p>
			<p class="input">
				<input type="password" name="password" maxlength="20" class="text"
					placeholder="비밀번호" data-gtm-form-interact-field-id="1">
			</p>
			<input type="hidden" name="redirect" value="/">
			<p class="submit">
				<input type="submit" value="로그인" class="text">
			</p>
			<label class="autologin">
			<input type="checkbox" name="autologin" id ="autologin" value="store"><span>아이디 기억하기</span></label>
			<p class="find">
				<a href="forgotid.com">아이디/비밀번호 찾기</a>
			</p>
			<p class="register">
				<span>애니타임에 처음이신가요?</span> <a href="register.com">회원가입</a>
			</p>
		</form>
	</div>
	<jsp:include page="../footer2.jsp" />
	
	</body>
</html>