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

<div class="form-group" id="uploadArea">
	<ul id="uploadUl">
	</ul>
</div>

<input type="hidden" id="uid" name="uid" value="${uid }" />
<style>
	.plusImg {
	max-width: 100%;
	max-height: 100%;
	bottom: 0;
	left: 0;
	margin: auto;
	overflow: auto;
	position: fixed;
	right: 0;
	top: 0;
	}
</style>

<script>
	$(document).ready(function() {

		var urlParams = new URLSearchParams(window.location.search);

		function changeLogin() {
			
			var uid = $("#uid").val();
			
			if(uid!==""){
				$("#login").text("log out");
				$("#login").attr("href","/tomcat/logout");
			}
		}changeLogin();
		
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
							str += "<img class='' id='' src='/file/getThumb/"+arr[i].thumbname+arr[i].extension+"'>";
						}
						if(arr[i].p_thumbname!==null){
							str += "<video class='vd' src='/file/getPthumb/"+arr[i].p_thumbname+arr[i].extension+"'width='360' height='120' controls='controls' autoplay='autoplay'></li>";
						}
						/* str += "<button id='"+ arr[i].uploadname +"' class='button special' type='submit'>다운로드</button>" */
						$("#uploadUl").html(str);
					}
				}

			});

		}
		getFlist();
		
		$("#uploadUl").on("click","img", function () {
			
			var src = $(this).attr("src");
			
			var getClass = $(this).attr("class");
			console.log("class="+getClass)
			
			if(getClass===""){
					
				var start = src.lastIndexOf("B");
				var last = src.lastIndexOf("_");
				
				var newPlusSrc = src.substring(start,last +1) +"MAIN.jpg";
				
				var url = src.substring(0,start) + newPlusSrc;
				
				console.log(url);
				
				$(this).attr("src",url);
				
				$(this).attr("id","plus");
				
				$(this).attr("class","plusImg");
					
			};
			
			if(getClass==="plusImg"){
				
				minusImg(src);
				
			};
			
		});
		
		function minusImg(src) {
			var src = $("#plus").attr("src");
			
			console.log(src);
			var start = src.lastIndexOf("B");
			var last = src.lastIndexOf("_");
			
			var newMinusSrc = src.substring(start,last +1) +"THUM.jpg";
			
			var url = src.substring(0,start) + newMinusSrc;
			
			$("#plus").attr("src",url);
			
			$("#plus").attr("class","");
			
			$("#plus").attr("id","");
			
			console.log("ok??"+url)
		};
	
	});
</script>
<%@ include file="/resources/footer.jsp"%>