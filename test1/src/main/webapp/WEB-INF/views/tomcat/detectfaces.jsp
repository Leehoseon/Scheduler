<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/resources/test.jsp"%>
<form id="regForm">
	<div class="form-group">
		<input type="text" name="title" id="title" value=""
			placeholder="제목을 입력하세요">
	</div>
	<div class="form-group">
		<input type="text" name="writer" id="writer" value="${uid}"
			readonly="readonly">
	</div>
	<div class="form-group">
		<textarea name="content" id="content" value="" placeholder="내용을 입력하세요"
			rows=9></textarea>
	</div>
	
</form>

<div class="form-group" id="uploadArea">
	<ul id="uploadUl">
	</ul>
</div>

<input type="hidden" id="uid" name="uid" value="${uid }" />
<div class="form-group" style="text-align: center;">
		<button class="button special" type="submit" id="regBtn">등록</button>
		<button class="button alt" type="button" id="listBtn">목록</button>
		<button class="btn btn-primary btn-block" type="button" id="resetBtn">파일
			초기화</button>
</div>


<script>
$(document).ready(function(){
	
	var formData = new FormData();
	var addFname ="";
	
	function changeLogin() {
		
		var uid = $("#uid").val();
		
		if(uid!==""){
			$("#login").text("log out");
			$("#login").attr("href","/tomcat/logout");
		}
	}changeLogin();
	
	$("#listBtn").on("click", function (e) {
		
		e.preventDefault();
		var con_test = confirm("목록으로 돌아가겠습니까?");
		if(con_test == true){
			self.location="/tomcat/list";
		}
		else if(con_test == false){
		  
		}
		
	});
		
	$("#uploadArea").on("dragenter dragover", function (e) {
		
		e.preventDefault();
		
	});
	
	$("#uploadArea").on('drop', function (e) {
		
		e.preventDefault();
				
		var files = e.originalEvent.dataTransfer.files;
		
		var length = files.length;
		
		for(i=0; i<length; i++){
			
			formData.append("file",files[i]);
			var fname = files[i].name;
		
			addFname = "<li id='"+ fname+"''>"+ fname +"</li>";
			
			var text = fname.substring(fname.lastIndexOf(".") + 1);
			
			var reg = ["jpg","mp4"];
			
			if (Boolean(text !="jpg" && text !="mp4")){
				
				alert("jpg/mp4파일만 업로드 가능합니다");
				return;
				
			}
			
			$("#uploadUl").append(addFname);
		}
	});
	
	$("#resetBtn").on("click", function (e) {
		
		var con_test = confirm("등록파일을 초기화하겠습니까?");
		if(con_test == true){
			$("#uploadUl").remove();
			
			formData.delete('file'); 
					
			var addUl = "<ul id='uploadUl'></ul>"
			$("#uploadArea").append(addUl);
		}
		
		else if(con_test == false){
		  
		}
		
	});
	
	$("#regBtn").click(function (e) {
		
		e.preventDefault();
	
		var title = $("#title").val();
		console.log(title);
		
		if(title==""){
			
			alert("제목을입력하세요!");
			
		} else{
			
			$.ajax({
		    	url: "/detectfaces/regfaces",
		        method: 'post',
		        data: formData,
		        dataType: 'json',
		        processData: false,
		        contentType: false,
		        success: function(arr) {
					str+= arr[i];
		        	
		        	$("#content").append(str);
		        }

			});
								
		
			function myFunction() {
			    myVar = setTimeout(alertFunc, 1000);
			}
			function alertFunc() {
				/* self.location="/tomcat/list"; */
			    alert("등록성공!");
			}myFunction();
			
		} 
						
	});
	
});

</script>

<%@ include file="/resources/footer.jsp"%>
