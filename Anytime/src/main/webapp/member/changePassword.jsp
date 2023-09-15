<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>비밀번호 변경 - 애니타임</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.js"></script>
<link data-vue-meta="ssr" rel="stylesheet"
	href="/Anytime/css/myInfo/0a14fb2000808afce700.css">
<link rel="stylesheet" type="text/css"
	href="/Anytime/css/myInfo/6d3fc370cf9211b2cad3.css">
<link rel="stylesheet" type="text/css"
	href="/Anytime/css/myInfo/13659cd4ee3a59b7dba9.css">
<style>
body {
	background-color: #EEEEF6 !important;
}
nav {
	border-bottom: 0 !important;
}
</style>
</head>
<script>
	$(function() {
		let checkpassword = false;
		let checkpasswordmatch = false;

		//비밀번호 유효성 검사
		$("input[name=password], input[name=password2]")
				.on(
						'keyup',
						function() {
							const password = $("input[name=password]").val();
							const password2 = $("input[name=password2]").val();

							// 영문자, 숫자, 특수문자 각각 1개 이상 포함, 8~20자
							const pattern = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/;

							if (!pattern.test(password)) {
								$("#password_message1")
										.css('color', '#e54787')
										.html(
												"&nbsp;&nbsp;&nbsp;영문, 숫자, 특문이 각각 1개 이상 포함된 8~20자여야 합니다.");
								checkpassword = false;
							} else {
								$("#password_message1")
										.css('color', '#624cff')
										.html(
												"&nbsp;&nbsp;&nbsp;유효한 비밀번호 형식입니다.");
								checkpassword = true;
							}

							if (password2.length >= 1) { // 두 번째 비밀번호 입력부터 검사
								checkPasswordMatch();
							} else {
								$("#password_message2").css('color',
										'transparent').html(""); // 메시지 감춤
								checkpasswordmatch = false;
							}
						});

		// 비밀번호 두 개가 일치하는지 확인하는 함수
		function checkPasswordMatch() {
			const password = $("input[name=password]").val();
			const password2 = $("input[name=password2]").val();

			if (password === password2) {
				$("#password_message2").css('color', '#624cff').html(
						"&nbsp;&nbsp;&nbsp;비밀번호가 일치합니다.");
				checkpasswordmatch = true;
			} else {
				$("#password_message2").css('color', '#e54787').html(
						"&nbsp;&nbsp;&nbsp;비밀번호가 일치하지 않습니다.");
				checkpasswordmatch = false;
			}
		}

		$("#updatepassword").on('click', function(event) {
			if (!checkpassword) {
				alert("&nbsp;&nbsp;&nbsp;비밀번호는 영문, 숫자, 특수문자를 2종류 이상 조합해주세요.");
				$("input[name='password']").val('').focus();
				return false;
			}

			if (!checkpasswordmatch) {
				alert("&nbsp;&nbsp;&nbsp;입력하신 두 비밀번호가 일치하지 않습니다.");
				$("input[name='password2']").val('').focus();
				return false;
			}

			if ($("input[name='oldpassword']").val() == "") {
				alert("&nbsp;&nbsp;&nbsp;현재 비밀번호를 입력하세요");
				$("input[name='oldpassword']").focus();
				return false;

			} else {
				const password = $("input[name='oldpassword']").val();
				const newpassword = $("input[name='password']").val();

				$.ajax({
					url : "checkpassword.com",
					data : {
						"password" : password,
						result : 0
					},
					success : function(resp) {
						if (resp == 0) {
							alert("현재 비밀번호와 일치하지 않습니다.");
							event.preventDefault();
						}
					}
				}); // ajax end
			}
		});
	});
</script>
<jsp:include page="../header.jsp" />
<body id="my">
	<div data-v-3e962932="">
		<form data-v-3e962932="" action="passwordUpdateProcess.com"
			class="container" name="updatePassword" method="post">
			<section data-v-3e962932="">
				<h1 data-v-3e962932="">비밀번호 변경</h1>
				<div data-v-3e962932="" class="input">
					<div data-v-3e962932="" class="label">
						<label data-v-3e962932="">새 비밀번호</label>
						<p data-v-3e962932="">영문, 숫자, 특문이 2종류 이상 조합된 8~20자</p>
					</div>
					<input data-v-3e962932="" type="password" maxlength="20"
						placeholder="새 비밀번호" class="" name="password"> <span
						id="password_message1" style="font-size: 12px; color: #e54787;"></span>
					<!---->
					<input data-v-3e962932="" type="password" name="password2"
						maxlength="20" placeholder="새 비밀번호 확인" class=""> <span
						id="password_message2"
						style="font-size: 12px; color: transparent;"></span>

					<!---->
				</div>
				<div data-v-3e962932="" class="input">
					<div data-v-3e962932="" class="label">
						<label data-v-3e962932="">현재 비밀번호</label>
					</div>
					<input data-v-3e962932="" type="password" name="oldpassword"
						maxlength="20" placeholder="현재 비밀번호" class="">
				</div>
				<div data-v-3e962932="" class="rules">
					<p data-v-3e962932="">
						<strong data-v-3e962932="">※ 타인에 의한 계정 사용이 의심되시나요?</strong><br
							data-v-3e962932=""> 개인정보 보호를 위해 비밀번호를 변경하여 주시기 바랍니다. 비밀번호를
						변경하면 <span data-v-3e962932="" class="caution">모든 디바이스(앱,
							브라우저 등)에서 즉시 로그아웃 처리됩니다.</span>
					</p>
				</div>
				<input data-v-3e962932="" type="submit" value="비밀번호 변경"
					id="updatepassword">
			</section>
		</form>
	</div>
	<jsp:include page="../footer2.jsp" />
	<script id="__INITIAL_STATE__" type="application/json">{"apiServerUrl":"https://api.everytime.kr","appInfo":{"appName":"","appVersion":"","osName":"","osVersion":""},"campusData":{"schoolId":12,"campusId":24,"communityName":"성신여대"},"isLogged":true,"lectureTalkServerUrl":"https://lecture-talk.everytime.kr","pageName":"pages/my/password","pageTitle":"비밀번호 변경","sentryDSN":"https://21c694d5302c47699d70d2c3ae22ab27@o4504002973597696.ingest.sentry.io/4504008836448256"}</script>
</body>
</html>