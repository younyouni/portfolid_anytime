<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>비밀번호 찾기 - 애니타임</title>

<link data-vue-meta="ssr" rel="preload"
	href="/Anytime/css/forgot/79230e2021cfc26431e8.css">
<link data-vue-meta="ssr" rel="stylesheet"
	href="/Anytime/css/forgot/0a14fb2000808afce700.css">
<link rel="stylesheet" type="text/css"
	href="/Anytime/css/forgot/79230e2021cfc26431e8.css">
	
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function(){
	$('form').on('submit', function(event){
	  event.preventDefault();
	  
	  var userid = $('#userid').val();
	  
	  if(userid.trim()===""){
		  alert("가입된 아이디를 입력해주세요.");
	  }else{
		  this.submit();
	  }
	  
	});
});
</script>	
</head>
<body>
	<div data-v-2f68d331="">
		<div data-v-2f68d331="" class="container">
			<section data-v-2f68d331="">
				<div data-v-2f68d331="" class="menu">
					<a data-v-2f68d331="" href="forgotid.com"> <span
						data-v-2f68d331="">아이디 찾기</span></a> <a data-v-2f68d331=""
						class="active"> <span data-v-2f68d331="">비밀번호 찾기</span></a>
				</div>
				<form data-v-2f68d331="" action="forgotpassword_emailcheck.com"
					method="post">
					<input data-v-2f68d331="" type="text" name="userid" id ="userid"
						placeholder="가입된 아이디" autocomplete="off"> 
						<input data-v-2f68d331="" type="submit" value="이메일 본인 인증하기">
				</form>
			</section>
		</div>
	</div>
	<jsp:include page="../footer2.jsp" />
</body>
</html>