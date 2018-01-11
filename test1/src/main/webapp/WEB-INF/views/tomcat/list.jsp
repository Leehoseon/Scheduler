<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/resources/test.jsp"%>
<!-- Page Content -->

<div class="topArea">

	<div class="btnArea">
		<button class="button special" id="regBtn">Write</button>
	</div>
</div>
<div class="table-responsive">


	<table class="table-wrapper" id="dataTable">
		<thead>
			<tr id="sortTable">
				<th id="No" scope="col">Tno</th>
				<th id="Title" scope="col">Title</th>
				<th id="Writer" scope="col">Writer</th>
			</tr>
		</thead>
		<!-- 본문 영역 -->
		<tbody>
			<c:forEach items="${list}" var="list">
				<tr>
					<td id="tno"></td>
					<td><a href="/tomcat/view?tno=${list.tno }">${list.title }</a></td>
					<td>${list.writer }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<input type="hidden" id="uid" name="uid" value="${uid }" />
<div class="form-group" style="text-align: center;">
	<span><div id="pagination"></div> </span>
</div>


<script type="text/javascript" src="/boot/paging.js"></script>

<script>
	$(document).ready(function() {
		
		var urlParams = new URLSearchParams(window.location.search);
		var page = urlParams.get('page');
		var writePage = page;
		
		function changeLogin() {
			
			var uid = $("#uid").val();
			
			if(uid!==""){
				$("#login").text("log out");
				$("#login").attr("href","/tomcat/logout");
			}
		}changeLogin();
		
		function writeTno() {
			
			for (var i = 1; i < 11; i++) {
				$("#tno").attr("id","tno"+i);
			}
			
			var str ="";
			
			if(writePage===null){
				writePage = 1;
			}
			if (writePage >= 2 ){
				writePage = (writePage -1) * 10;
			}
			var j=1;
			
			for (var i = writePage; i <= (writePage * 10); i++) {
				
				str += i ;
				
				$("#tno"+ j +":nth-child(1)").text(str);
				j++;
				str ="";
			} 
		}writeTno();
		
		$("#3").addClass("active");
		
		var pageStr = makePage({
			total:${count},
			current:page
		});
		
		console.log(pageStr);
		
		$("#pagination").html(pageStr);

		$("#regBtn").on("click", function(e) {

			e.preventDefault();

			self.location = "/tomcat/register";

		})
		
		$("#pagination").on("click","a", function (e) {
			
			var $this = $(this);
			var pageNum = $this.attr('id');
			console.log(pageNum);
			
			self.location="/tomcat/list?page="+pageNum;
			
		})
		
		function selectColor() {
		
			$("#"+page).css("background-color", "#ddd");
			$("#"+page).css("color", "white");
			
		}selectColor();
	});
</script>

<%@ include file="/resources/footer.jsp"%>
