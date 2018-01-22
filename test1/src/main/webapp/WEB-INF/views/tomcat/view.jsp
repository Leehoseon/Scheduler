<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/resources/test.jsp"%>
<form action="/tomcat/modify" id="modifyFrm">
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
		<label for="editor">내용</label> <textarea class="form-control"
			name="content" id="content" rows="9" readonly="readonly"
			>${view.content }</textarea>
	</div>
	<input type="hidden" id="tno" name="tno" value="${view.tno }" />
</form>
<label for="editor">등록된 파일</label>
<div class="form-group" id="uploadArea">
	<ul id="uploadUl">
	</ul>
</div>
<br>
<label for="editor">댓글</label>

<div id="replyArea">
	<div style="width: 100%; margin-top: 25px;">
		<textarea id="replyContent" style="resize: none; height: 100px; float: left; width: 85%;"></textarea>
		<button id="replyRegBtn" class="button special" type="submit" style="float: right; height: 100px; width: 15%;">
		등록</button>
	</div>
	<div id="replyList"></div>
</div>
<h2 id='more'>&#10010;</h2>
<br>

<div class="form-group" style="text-align: center;">
	<button id="modifyBtn" class="button special" type="button">
		수정</button>
	<button id="listBtn" class="btn btn-default" type="button">
		목록</button>
</div>
<style>

	#replyList{
		margin-top: 10px;
	}

	#replyList div{
		
		width: 100%;
		float: left;
	}
	#replyTopDiv div{
		width:30%;
		min-height: 2em !important;
		margin-top: 2em;
		
	}
	
	#uploadUl{
		list-style: none;
	}
	
	
	#replyArea {
		/* border-radius: 10px !important;
		border-color: silver !important;
		border: 3px solid;
		min-height: auto; */
	}
	
	.uidDiv{
		margin-top: 2em;
		margin-left: 5%;
		width:10% !important;
	}
	.contentDiv{
		margin-top: 2em;
		float :left;
		margin-left: 5%;
		width:50% !important;
	}
	.contentChangeDiv{
		margin-top : 2%;
		float :left;
		margin-left: 5%;
		width:40% !important;
		resize: none;
		height: 45px;
	}
	#replyModBtn{
		margin-top : 3%;
		float :left;
		width:10% !important;
		text-align: center;
	}
	.regdateDiv{
		margin-top: 2em;
		width:20% !important;
	}
	.closeDiv{
		float: left;
		min-height: 2em !important;
		margin-top: 2em;
		width:5% !important;
	}
	
	.modifyDiv{
		float: left;
		margin-right: 2em !important;
		/* min-height: 2em !important;
		margin-top: 2em;
		width:5% !important; */
		
	} 
	.spaceDiv{
		float: left;
		min-height: 2em !important;
		margin-top: 2em;
		width:5% !important;
		margin-bottom: 3.4%;
	}

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
		background-color: silver;
	}
	
	#more{
		text-align: center;
	}
</style>

<script>
	$(document).ready(function() {
		
		var max = 0;
		var min = 0;
		
		var page = getQuerystring("page");
		
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
		
		$("#modifyBtn").on("click", function (e) {
			
			e.preventDefault();
			
			$("#modifyFrm").submit();
			
		});
		
		function getFlist() {
			
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
		
		/* function hideModBtn() {
			
			var writer = $(".uidDiv").text();
			var uid = $("#uid").val();
			
			console.log(writer+"writer");
			console.log(uid+"uid");
			
			if(writer !== uid){
				return false;
			}else{
				return true;
			}
		}; */
		
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
		
		function getRlist() {
			var str=""; 
			var tno = $("#tno").val(); 
			var uid = $("#uid").val();
			console.log(uid);
			
			min +1;
			max += 10;	
			
			console.log(min);
			console.log(max);
			
			$.ajax({
				url : "/reply/getrlist",
				method : 'post',
				dataType : 'json',
				data : {tno:tno, uid:uid, max:max, min:min},
				success : function(arr) {
					
					for (var i = 0; i < arr.length; i++) {
						
						str = "<div id='replyTopDiv'><div id='"+arr[i].uid+arr[i].rno+"' class='uidDiv' value='"+ arr[i].uid +"'>"+arr[i].uid+"</div>"
						+"<div id='content"+arr[i].rno+"' class='contentDiv'>"+arr[i].content 
						
						if(uid === arr[i].uid){
							str += "<p id='"+arr[i].rno+"' class='modifyDiv'>&#9997;</p>"						
						}
						
						str += "</div><div class='regdateDiv'>"+arr[i].regdate+"</div>";
						
						if(uid === arr[i].uid){
							str += "<p id='"+arr[i].rno+"' class='closeDiv'>&#10006;</p>";						
						}else if (uid !== arr[i].uid){
							str += "<div class='spaceDiv'>&nbsp;&nbsp;&nbsp;&nbsp;</div>"
						}
						
						str += "</div>";
						
						console.log(str);
						$("#replyList").append(str);
						
					}
					
					str="";
					min += 10;
				}
			});
		}getRlist();
		
		$("#replyRegBtn").on("click", function (e) {
			
			e.preventDefault();
			
			var content = $("#replyContent").val();
			
			var tno = $("#tno").val();
			
			var uid = $("#uid").val();
			
			var con_test = confirm("댓글을 등록하시겠습니까?");
			
			if(con_test == true){

				$.ajax({
			    	url: "/reply/register",
			        method: 'post',
			        data: {content:content,uid:uid,tno:tno},
			        dataType: 'text',
			        success: function(text) {
			        	
						var link = document.location.href;
						
						self.location=""+link;
			        	
					}
				});
				
				getRlist();
			}
			else if(con_test == false){
			  
			}
			
		});
		
		$("#replyTopDiv").on("click", function (e) {
			
			/* $(this).find("id") */
			
		});
		
		$("#replyArea").on("click","p", function (e) {
			
			e.preventDefault();
			
			var rno = $(this).attr("id");
			
			console.log(rno);
			
			var contentRno ="content"+ rno;
			
			var cls = $(this).attr("class");
			
			if(cls ==="modifyDiv"){
				
				$(this).remove();
				
				var text = $("#"+contentRno).text();
				
				console.log(cls);
				var str = "<textarea rows='1' id='"+rno+"' class='contentChangeDiv'>"+text+"</textarea><i id='replyModBtn' class='"+rno+"'>&#10004</i>"
				
				$("#"+contentRno).replaceWith(str);
				console.log(str);
				
				$("#"+rno).css({width:"30%"});
				
				
			}else{
				
				var con_test = confirm("댓글을 삭제 하시겠습니까?");
				if(con_test == true){
					$.ajax({
						url : "/reply/delete/" + rno + "",
				        method: 'get',
				        dataType: 'json',
				        success: function(data) {
				        	
						}
					});
					var link = document.location.href;
					
					self.location=""+link;
				}
				else if(con_test == false){
				  
				}
			}
		});
		
		$("#replyArea").on("click","i", function (e) {

			e.preventDefault();
			
			var rno = $(this).attr("class");
			
			var content = $("#"+rno).val();
			/* $(".contentChangeDiv").val(text); */
			console.log(rno);
			console.log(content);
			
			$.ajax({
				url : "/reply/modify/",
		        method: 'post',
		        data:{rno:rno,content:content},
		        dataType: 'json',
		        success: function(data) {
		        	
				}
			});
			var link = document.location.href;
			
			alert("댓글수정완료!")
			
			self.location=""+link;
		});
		
		$("#more").on("click", function (e) {
			
			getRlist();
			console.log("asdasd");
			
		});
		
		
});


</script>
<%@ include file="/resources/footer.jsp"%>