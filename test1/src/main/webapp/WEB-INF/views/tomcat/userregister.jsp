<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/boot/login/css/style.css">
</head>
<body>
<div class="container">
	<section id="content">
		<form action="/tomcat/userregister" id="uregForm" method="post">
			<h1>Register Form</h1>
			<div>
				<span><input type="text" placeholder="Userid" required="required" id="userid" name="uid" />
				<input type="checkbox" value="Check" id="chkBtn"/></span>
			</div>
			<div>
				<input type="password" placeholder="Password" required="required" id="password" name="upw"/>
			</div>
			<div>
				<input type="text" placeholder="Name" required="required" id="name" name="uname"/>
			</div>
			<div>
				<input type="email" placeholder="Email" required="required" id="email" name="uemail"/>
			</div>
			<div>
				<input type="submit" value="Register" id="usubBtn"/>
				<a href="/tomcat/login">LoginHome</a>
			</div>
		</form><!-- form -->
		<div class="button">
			
		</div><!-- button -->
	</section><!-- content -->
</div><!-- container -->

<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>
	
<script>

	$("#usubBtn").on("click", function (e) {
	
		e.preventDefault();
		
		var con_test = confirm("회원가입을하시겠습니까?");
		if(con_test == true){
			$("#uregForm").submit();
		}
		else if(con_test == false){
		  
		}
	});
	
	$("#chkBtn").on("click", function (e) {
		
		var uid = $("#userid").val();
		console.log(uid);
			
		$.ajax({
			url : "/tomcat/chkuid/"+ uid + "",
			method : 'get',
			data : uid,
			dataType : "text",
			/* async: false, */
							
			success : function(data) {
				console.log(data);
				if(data === uid){
					$('#chkBtn').prop('checked', false);
					alert("사용불가능한아이디입니다.");
				}else if(data !== uid){
					$('#chkBtn').prop('checked', true);
					alert("사용가능한아이디입니다.");
				} 
			}

		});
			
	});
</script>

</body>
</html>