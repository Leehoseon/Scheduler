<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/resources/test.jsp"%>

<!-- Page Content -->

<div class="topArea">
	<div class="btnArea">
		<button class="button special" id="regBtn">Write</button>
	</div>
	<div class="searchArea">
		<div class="custom-select">
		  <select>
		    <option value="0">Select Sub:</option>
		    <option value="title">Title</option>
		    <option value="writer">Writer</option>
		  </select>
		</div>
		<form class="example" action="action_page.php">
			  <input type="text" placeholder="Search.." name="search">
			  <button type="submit"><i class="fa fa-search"></i></button>
		</form>
	</div>
	
</div>
<br>
<div class="table-responsive">
	<table class="table-wrapper" id="dataTable">
		<thead>
			<tr id="sortTable">
				<th id="No" scope="col">Tno</th>
				<th id="Title" scope="col">Title</th>
				<th id="Writer" scope="col">Writer</th>
				<th id="Writer" scope="col">RegDate</th>
			</tr>
		</thead>
		<!-- 본문 영역 -->
		<tbody>
			<c:forEach items="${list}" var="list">
				<tr>
					<td id="tno"></td>
					<td><a href="/tomcat/view?tno=${list.tno }">${list.title }</a></td>
					<td>${list.writer }</td>
					<td>${list.regdate }</td>
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

<style>

.btnArea{
	float:right;
}
.topArea{
	display: inline;
}
.searchArea{
	position: relative;
	width: 60%;
	
}
</style>

<style>

.custom-select {
  position: absolute;
  font-family: Arial;
  width: 24%;
  
}
.custom-select select {

  display: block; /*hide original SELECT element:*/
  height: 50px;
}

.select-selected {
  background-color: DodgerBlue;
}
/*style the arrow inside the select element:*/
.select-selected:after {
  position: absolute;
  content: "";
  top: 14px;
  right: 10px;
  width: 0;
  height: 0;
  border: 6px solid transparent;
  border-color: #fff transparent transparent transparent;
}
/*point the arrow upwards when the select box is open (active):*/
.select-selected.select-arrow-active:after {
  border-color: transparent transparent #fff transparent;
  top: 7px;
}
/*style the items (options), including the selected item:*/
.select-items div,.select-selected {
  color: #ffffff;
  padding: 8px 16px;
  border: 1px solid transparent;
  border-color: transparent transparent rgba(0, 0, 0, 0.1) transparent;
  cursor: pointer;
}
/*style items (options):*/
.select-items {
  position: absolute;
  background-color: DodgerBlue;
  top: 100%;
  left: 0;
  right: 0;
  z-index: 99;
}
/*hide the items when the select box is closed:*/
.select-hide {
  display: none;
}
.select-items div:hover {
  background-color: rgba(0, 0, 0, 0.1);
}

* {
  box-sizing: border-box;
}
.example{
	margin-left: 24%;
}
/* Style the search field */
form.example input[type=text] {
  height: 50px;
  padding: 10px;
  font-size: 17px;
  border: 1px solid transparent;
  float: left;
  width: 70%;
  background: #f1f1f1;
}

/* Style the submit button */
form.example button {
  height: 50px;
  float: left;
  width: 10%;
  padding: 10px;
  background: #2196F3;
  color: white;
  font-size: 17px;
  border: 1px solid grey;
  border-left: none; /* Prevent double borders */
  cursor: pointer;
}

form.example button:hover {
  background: #0b7dda;
}

/* Clear floats */
form.example::after {
  content: "";
  clear: both;
  display: table;
}

</style>

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
