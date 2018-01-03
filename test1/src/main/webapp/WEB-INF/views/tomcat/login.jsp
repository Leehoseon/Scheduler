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
		<form action="/tomcat/login" method="post">
			<h1>Login Form</h1>
			<div>
				<input type="text" placeholder="Username" required="" id="username" name="uid" />
			</div>
			<div>
				<input type="password" placeholder="Password" required="" id="password" name="upw" />
			</div>
			<div>
				<input type="submit" value="Log in" />
				<input type="checkbox" name="auto">
				<a href="#">Lost your password?</a>
				<a href="/tomcat/userregister">Register</a>
			</div>
		</form><!-- form -->
		<div class="button">
			
		</div><!-- button -->
	</section><!-- content -->
</div><!-- container -->

</body>
</html>