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
		  <select id="selectOption">
		    <option value="Select Sub">Select Sub:</option>
		    <option id="searchOptionTitle" value="title">Title</option>
		    <option id="searchOptionWriter" value="writer">Writer</option>
		  </select>
		</div>
		<form class="example" action="/tomcat/list">
			  <input type="text" placeholder="Search.." name="searchText" value="${searchText }">
			  <input type="hidden" name="searchType" id="searchType" value="${searchType }">
			  <input type="hidden" name="sortType" id="sortType" value="${sortType }">
			  <input type="hidden" name="page" id="searchFormPage">
			  <input type="hidden" name="order" id="order" value="${order }">
			  <button type="submit" id="searchFormSubmit"><i class="fa fa-search"></i></button>
		</form>
	</div>
	
</div>
<br>
<div class="table-responsive">
	<table class="table-wrapper" id="dataTable">
		<thead>
			<tr id="sortTable">
				<th id="tno" scope="col">Tno</th>
				<th id="title" scope="col">Title</th>
				<th id="writer" scope="col">Writer</th>
				<th id="regDate" scope="col">RegDate</th>
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
		
		function selectedSearchType() {
			var searchValue = $("#searchType").val();
			console.log(searchValue);
			if(searchValue === "title" && "writer"){
				$("#selectOption").val(searchValue).attr("selected",searchValue);	
			}
			else if(searchValue === null){
				$("#selectOption").val("Select Sub:").attr("selected","true");
			}
		}selectedSearchType();
		
		function makeForm() {
			
			var count = ${count};
			
			if(page == null){
				page = 1;
			}
			if(count<= 10){
				page = 1;
			}
			
			$("#searchFormPage").val(page);
			
			$(".example").submit();
			
		};
		
		$("#searchFormSubmit").on("click", function (e) {
		
			e.preventDefault();
			
			makeForm();
			
		});
		
		$("#selectOption").on("click", function (e) {
			
			var optionValue = $(this).val();
			
			console.log(optionValue);
			
			$("#searchType").val(optionValue);
			
		})
		
		function changeLogin() {
			
			var uid = $("#uid").val();
			
			if(uid!==""){
				$("#login").text("log out");
				$("#login").attr("href","/tomcat/logout");
			}
		}changeLogin();
		
		function writeTno() {
			
			for (var i = 1; i < 12; i++) {
				$("#tno").attr("id","tno"+i);
			}
			
			var str ="";
			
			if(writePage===null){
				writePage = 1;
			}
			if (writePage >= 2 ){
				writePage = (writePage -1) * 10 +1;
			}
			var j=2;
			
			var target = (writePage * 10) + 1;
			
			for (var i = writePage; i < target; i++) {
				
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
			
			page = pageNum;
			
			$("#searchFormPage").val(pageNum);
			
			makeForm();
			
		});
		
		function selectColor() {
		
			$("#"+page).css("background-color", "#ddd");
			$("#"+page).css("color", "white");
			
		}selectColor();
		
		$("#sortTable").on("click","th", function (e) {
			
			var sortName = $(this).attr("id");
			
			$("#sortType").val(sortName);
			
			var orderValue = $("#order").val();
			
			console.log(orderValue);
			
			if(orderValue===""){
				$("#order").val("desc");
			}else if(orderValue === "desc"){
				$("#order").val("asc");
			}else if(orderValue=== "asc"){
				$("#order").val("desc");
			}
			makeForm();
		})
		
	});
</script>

<%@ include file="/resources/footer.jsp"%>
