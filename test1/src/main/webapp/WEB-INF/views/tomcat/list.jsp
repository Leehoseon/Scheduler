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
					<td>${list.tno }</td>
					<td><a href="/tomcat/view?tno=${list.tno }">${list.title }</a></td>
					<td>${list.writer }</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div class="form-group" style="text-align: center;">
	<span><div id="pagination"></div> </span>
</div>


<script type="text/javascript" src="/boot/paging.js"></script>

<script>
	$(document).ready(function() {
		var urlParams = new URLSearchParams(window.location.search);
		var page = urlParams.get('page');
		
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
	});
</script>

<%@ include file="/resources/footer.jsp"%>
