<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/boot/login/css/style.css">
</head>
<body>
<div class="container">
	<section id="content">
		<form action="/tomcat/findpassword" method="post">
			<h1>Find Password</h1>
			<h3>이메일 확인 후 입력하신 이메일로 비밀번호를 안내 해드립니다.</h3>
			<div>
				<input type="email" placeholder="Email" required="required" id="email" name="uemail"/>
			</div>
			<div>
				<input type="submit" value="OK" />
				<a href="/tomcat/login">Login</a>
			</div>
		</form><!-- form -->
		<div class="button">
			
		</div><!-- button -->
	</section><!-- content -->
</div><!-- container -->

</body>
</html>