<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
<script>
function makePage(param) {
	
	var total = param.total || 100;
	var page = param.page || 1;
	var tag = "<li>page</li>";
	var tempEnd = Math.ceil(total/10);
	
	for(var i=0; i < tempEnd; i++){
		
		tag +="<li>"+[i]+"</li>";
				
	}
	
	return tag;
}
</script>
</html>