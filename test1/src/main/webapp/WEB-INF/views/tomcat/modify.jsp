<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/resources/test.jsp"%>
<form id="modForm" action="/tomcat/modify" method="POST">
	<div class="form-group">
		<label for="editor">제목</label> <input class="form-control"
			name="title" id="title" type="text" value="${view.title }"></input>
	</div>
	<div class="form-group">
		<label for="author">작성자</label> <input class="form-control"
			name="writer" id="writer" type="text" value="${view.writer }" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="editor">내용</label> <input class="form-control"
			name="content" id="content" type="text" value="${view.content }"></input>
	</div>
	<input type="hidden" id="tno" name="tno" value="${view.tno }" />

	<div class="form-group" style="text-align: center;">
		<button id="modBtn" class="button special">수정</button>
		<button id="removeBtn" class="btn btn-default">삭제</button>
		<button name="listBtn" value="list" id="listBtn" class="button alt"
			type="button">리스트</button>
	</div>
</form>

<div id="uploadArea">
<label for="editor">기존파일</label>
	<ul id="uploadUl">
	</ul>
</div>
<div id="nuploadArea">
<label for="editor">새로운파일</label>
	<ul id="nuploadUl">
	</ul>
</div>
<div style="text-align: center;">
<button class="btn btn-default" type="button" id="resetBtn">파일
	초기화</button>
</div>
<input type="hidden" id="uid" name="uid" value="${uid }" />
<script>
$(document).ready(function(){
	var formData = new FormData();
	var urlParams = new URLSearchParams(window.location.search);
	
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
	
	$("#removeBtn").on("click", function (e) {
		
		e.preventDefault();
		
		var con_test = confirm("글을 삭제 하시겠습니까?");
		if(con_test == true){
			var tno = $("#tno").val();
			
			self.location="/tomcat/remove?tno="+tno+"";
		}
		else if(con_test == false){
		  
		}
		
	});
	
	function getFlist() {
		var tno = urlParams.get('tno');
		console.log(tno);
		var str="";
		
		$.ajax({
	    	url: "/file/getflist/"+tno+"",
	        method:'get',
	        data: tno,
	        dataType: 'json',
	        /* async: false, */
	        success: function(arr) {
	        	for(var i =0; i <arr.length; i++){
					
					str += "<li id='"+arr[i].uploadname+"'>"+ arr[i].originalname +"</li>"
					$("#uploadUl").html(str);
				}
	        }
		});
		
	}getFlist();
	
	$("#uploadUl").on("click","li", function (e) {
		
		var con_test = confirm("선택 파일을 삭제하시겠습니까?");
		if(con_test == true){
			
			var fname = $(this).text();
			
			var last = fname.length;
			
			var start = fname.lastIndexOf(".");
			
			var extension = fname.substring(start,last);
			
		    var uploadname = $(this).attr("id");
		    
		    var lastName = uploadname + extension ;
		    console.log(uploadname);
			
			$(this).remove();
					
			 $.ajax({
		    	url: "/file/delflist/",
		        method:'Delete',
		        data:JSON.stringify({uploadname:lastName}),
		        dataType: 'json',
		        processData: false,
				contentType: 'application/json; charset=utf-8',
		        success: function(arr) {
		        
		        }
			}); 
		}
		else if(con_test == false){
		  
		}
		
	});
	
	$("#nuploadArea").on("dragenter dragover", function (e) {
		
		e.preventDefault();
		
	});
	
	$("#nuploadArea").on('drop', function (e) {
		
		e.preventDefault();
		var fname = "";
		var files = e.originalEvent.dataTransfer.files;
		
		var length = files.length;
		
		for(i=0; i<length; i++){
			formData.append("file",files[i]);
			var fname = files[i].name;
			console.log(formData);
			console.log(fname);
			var addFname = "<li id='"+ fname+"''>"+ fname +"</li>";
			
			$("#nuploadUl").append(addFname);
		}
	});
	
	$("#resetBtn").on("click", function (e) {
		
		var con_test = confirm("등록파일을 초기화하겠습니까?");
		if(con_test == true){
			$("#nuploadUl").remove();
			
			formData.delete('file'); 
					
			var addUl = "<ul id='nuploadUl'></ul>"
			$("#nuploadArea").append(addUl);
		}
		else if(con_test == false){
		  
		}
			
	});
	
	$("#modBtn").click(function (e) {
		
		e.preventDefault();
		var tno = urlParams.get('tno');
		var url = "/file/addfile/"+ tno +"";
							
		$.ajax({
		    url: url,
		    method: 'post',
		    data: formData,
		    dataType: 'json',
		    processData: false,
		    contentType: false,
		    success: function(data) {
		    }
		});
		function myFunction() {
			myVar = setTimeout(alertFunc, 1000);
		}
		function alertFunc() {
			alert("수정성공!");
			$("#modForm").submit();		
		}myFunction();
	});
});
</script>

<%@ include file="/resources/footer.jsp"%>