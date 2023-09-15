<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>애니타임</title>
<link data-vue-meta="ssr" rel="preload"
	href="/Anytime/css/forgot/13659cd4ee3a59b7dba9.css">
<link data-vue-meta="ssr" rel="preload"
	href="/Anytime/css/forgot/d7ec1914a4608cfbd829.css">
<link data-vue-meta="ssr" rel="stylesheet"
	href="/Anytime/css/forgot/0a14fb2000808afce700.css">
<link rel="stylesheet" type="text/css"
	href="/Anytime/css/forgot/d7ec1914a4608cfbd829.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.js"></script>
<script>
$(document).ready(function () {
    $('form').submit(function(e) {
        e.preventDefault();

        const password = $("input[name=password]").val();
        const password2 = $("input[name=password2]").val();

        // 영문자, 숫자, 특수문자 각각 1개 이상 포함, 8~20자
        const pattern = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/;

        if (!password || !password2) {
            alert("비밀번호를 입력해주세요.");
            $("input[name=password]").val('').focus();
            return false;
        }

        if (!pattern.test(password)) {
            alert("영문, 숫자, 특수문자를 각각 1개이상 포함한 8~20자의 비밀번호를 입력하세요.");
            $("input[name=password]").val('').focus();
            return false;
        }

        if (password !== password2) {
            alert("비밀번호가 일치하지 않습니다.");
            $("input[name=password2]").val('').focus();
            return false;
        }

        // 형식 검사가 모두 완료되면, 이 부분에서 폼 제출 처리를 해주세요.
       this.submit();
    });
});
</script>
</head>
<body>
	<div data-v-00518b35="">
		<div data-v-00518b35="" class="container">
			<section data-v-00518b35="">
				<form data-v-00518b35= "" action="forgotpasswordresultProcess.com" method="post">
					<h1 data-v-00518b35="">에브리타임 비밀번호 변경</h1>
					<div data-v-00518b35="" class="description">
						<p data-v-00518b35="">※ 영문, 숫자, 특문이 각각 1개 이상 조합된 8~20자</p>
					</div>
					<input data-v-00518b35="" type="password" name ="password" maxlength="20"
						placeholder="새 비밀번호">
				
				  <input data-v-00518b35=""
						type="password" maxlength="20" name="password2" placeholder="새 비밀번호 확인"> 
				
						<input data-v-00518b35="" type="submit" value="비밀번호 변경">
				</form>
			</section>
		</div>
	</div>
<jsp:include page="../footer2.jsp" />
</body>
</html>