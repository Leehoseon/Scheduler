<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/resources/test.jsp"%>
<form action="/tomcat/modify" id="submitFrm">
	<div class="form-group">
		<label for="editor">제목</label> <input class="form-control"
			name="content" id="title" type="text" readonly="readonly"
			value="${view.title }"></input>
	</div>
	<div class="form-group">
		<label for="author">작성자</label> <input class="form-control"
			name="writer" id="writer" type="text" readonly="readonly"
			value="${view.writer }">
	</div>
	<div class="form-group">
		<label for="editor">내용</label> <input class="form-control"
			name="content" id="content" type="text" readonly="readonly"
			value="${view.content }"></input>
	</div>
	<input type="hidden" id="tno" name="tno" value="${view.tno }" />

	<div class="form-group" style="text-align: center;">

		<button id="writeBtn" class="button special" type="submit">
			수정</button>

		<button id="listBtn" class="btn btn-default" type="button">
			목록</button>
	</div>
</form>
<input type="hidden" id="uid" name="uid" value="${uid }" />
<div class="form-group" id="uploadArea">
	<ul id="uploadUl">
	</ul>
</div>

<script>
	$(document).ready(function() {

		var urlParams = new URLSearchParams(window.location.search);

		function hideBtn() {
			
			var writer = $("#writer").val();
			var uid = $("#uid").val();
			console.log(uid);
			console.log(writer);
			if(writer !== uid){
				$("#writeBtn").remove();
			}
		}hideBtn();
		
		$("#listBtn").on("click", function(e) {

			e.preventDefault();

			self.location = "/tomcat/list";

		});

		function getFlist() {
			var tno = urlParams.get('tno');
			console.log(tno);
			var str="";
			$.ajax({
				url : "/file/getflist/" + tno + "",
				method : 'get',
				dataType : 'json',
				/* async: false, */
				success : function(arr) {
										
					for (var i = 0; i < arr.length; i++) {

						str += "<li><a href='/file/download/"+arr[i].uploadname+arr[i].extension+"' download='"+arr[i].originalname+"'>" + arr[i].originalname + "</a>"
						
						if(arr[i].thumbname!==null){
							str += "<img src='/file/getThumb/"+arr[i].thumbname+"'>";
						}
						if(arr[i].p_thumbname!==null){
							str += "<video class='vd' src='/file/getPthumb/"+arr[i].p_thumbname+"'width='360' height='120' controls='controls' autoplay='autoplay'></li>";
						}
						/* str += "<button id='"+ arr[i].uploadname +"' class='button special' type='submit'>다운로드</button>" */
						$("#uploadUl").html(str);
					}
				}

			});

			/* $.getJSON("/getThumb/{thumbName}",function(arr){
				console.log(arr);
				
				for(var i =0; i <arr.length; i++){
					
					str += "<img src="">"+ arr[i].originalname +"</img>"
					$("#uploadUl").html(str);
				}
			});  */
		}
		getFlist();
	
	});
</script>
<%@ include file="/resources/footer.jsp"%>