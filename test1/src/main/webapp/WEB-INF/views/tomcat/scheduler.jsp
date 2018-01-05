<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/resources/test.jsp"%>

	<input type="hidden" id="year" value="2018">
	<input type="hidden" id="month" value="4">
	<input type="hidden" id="day" value="1">
	
<div class="scheduler">
		<ul class="year">
			<li id="prev">&laquo;</li>
			<li id="yeartext">2017.8</li>
			<li id="next">&raquo;</li>
		</ul>
	<br>
	
	<ul class="s">
	</ul>
</div>
<input type="hidden" id="uid" name="uid" value="${uid }" />
<!-- The Modal -->
<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">x</span>
    <input type="text" name="text" id="modalText">
    <br>
    <button class="button special" id="scheduleSub">일정 추가</button>
  </div>

</div>

<style>

#yeartext{
	margin-left:auto;
	font-size:50px;
	text-align: center;
	float:left;
	width: 82%;
	
}

#prev{
	margin-top:4%;
	margin-left:5%;
	float: left;
	border: 1px solid;
	border-color: black;
	border-radius: 50px;
	font-weight: 900;
	width: 30px;
	
}

#next{
	margin-right:5%;
	margin-top:4%;
	float: left;
	border: 1px solid;
	border-color: black;
	border-radius: 50px;
	font-weight: 900;
	width: 30px;
	
}

.year {
	margin-top:0;
	text-align:center;
    display: inline-block;
    list-style:none;
    padding: 0;
    margin: 0;
    /* background-color:silver; */
    height:100px;
	width:780px;
}


.scheduler{
	margin:auto;
	border-radius: 3px !important;
	border-color: black !important;
	border: 1.5px solid;
	height:780px;
	width:780px;
}
.s{
	
}
.s li{
	text-align:center;
	margin:0px;
	padding:0px;
	border-color: black !important;
	height:100px;
	width:100px;
	display: inline-block;
	
}
#day{
	height:50px !important;
	
}

/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
	text-align:center;
    background-color: #fefefe;
    margin: 15% auto; /* 15% from the top and centered */
    padding: 20px;
    border: 1px solid #888;
    width: 50%; /* Could be more or less, depending on screen size */
}

/* The Close Button */
.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}


</style>


<script>

	$(document).ready(function() {
		
		
		var Now = new Date();

		var NowTime = Now.getFullYear();
		var NowMonth = Now.getMonth()+1;
		var NowDate = Now.getDate();
		var inputYear = NowTime;
		var inputMonth = NowMonth; 
		var inputDay = NowDate;
		
		$("#year").val(inputYear);
		$("#month").val(inputMonth);
		
		var title = $("#year").val();
		title+= "."+$("#month").val();
		$("#yeartext").text(title);
		
		/* console.log(inputYear);
		console.log(inputMonth);
		console.log(inputDay); */
		var aa = new Date(inputYear,inputMonth-1,inputDay);
		var year = aa.getFullYear();
		var month = aa.getMonth()+1;
		var date = aa.getDate();
		var dayLabel = aa.getDay();
		var day =["일요일","월요일","화요일","수요일","목요일","금요일","토요일",]
		var lastDate ="";
		
		/* console.log(aa);
		console.log(year);
		console.log(month);
		console.log(date);
		console.log(dayLabel); */
				
		var j=0;
		
		function drwaingTable() {
			var str ="";
			
			for(i=0; i<=41; i++){
				
				if(i==0){
					for(k=0; k<=6; k++){
						str += "<li id='"+ "day" +"'>"+day[j]+"</li>";
						j++;
					}
					j=0;
				}
				
				str += "<li id='"+ i +"'>"+day[j]+"</li>"
				
				j++;
			
				if(j>6){
					j=0;
				}
			}
			$(".s").html(str);
			
		}drwaingTable();
		
		function writeDate() {
			
			firstDate = new Date($("#year").val(),$("#month").val(),1).getDate();
			lastDate = new Date($("#year").val(),$("#month").val(),0).getDate();
			StartDay = new Date($("#year").val(),$("#month").val()-1,1).getDay();
			beforelastDate = new Date($("#year").val(),$("#month").val()-1,0).getDate();

			var j=1;
			var k=StartDay-1;;
			var n=1;
			var end = lastDate+StartDay-1;
			
			for(i=StartDay; i<=end; i++){
				
				if(StartDay>=0){
					
					$("#"+k).text(beforelastDate);
					$("#"+k).css({color:"silver"}); 
					beforelastDate--;
					k--;
				}
					
				$("#"+i).text(j);
				$("#"+i).css({color:"black"});
				j++;
																
			}
			for(i=end+1; i<=41; i++){
				$("#"+i).text(n);
				$("#"+i).css({color:"silver"}); 
				n++;
			}
			for (i=1; i<=end; i++){
				if(i%7==0){
					$("#"+i).css({color:"red"});
				}
			}
			
		}writeDate();
		
		$("#"+inputDay).css("background-color", "silver");
		
		$("#next").on("click", function (e) {
			
			$(".s").empty();
			
			var year = $("#yeartext").text();
			
			var month = year.substring(year.lastIndexOf(".") + 1);
			var cyear = year.substring(0,4);
			
			var nextMonth = parseInt(month) + 1;
			
			if(nextMonth >= 13){
				
				cyear = parseInt(cyear) + 1;
				nextMonth = 1;
				$("#yeartext").text(cyear+"."+nextMonth);
				
			}
			
			$("#month").val(parseInt(month)+1);
			
			$("#year").val(cyear);
			
			$("#yeartext").text(cyear+"."+nextMonth);
			console.log(cyear);
			
			console.log($("#month").val());
			
			drwaingTable();
			writeDate();
			
		});
		
		$("#prev").on("click", function (e) {
			
			$(".s").empty();
			
			var year = $("#yeartext").text();
			
			var month = year.substring(year.lastIndexOf(".") + 1);
			var cyear = year.substring(0,4);
			
			var beforeMonth = parseInt(month) - 1;
			
			if(beforeMonth <= 0){
				
				cyear = parseInt(cyear) - 1;
				beforeMonth = 12;
				$("#yeartext").text(cyear+"."+beforeMonth);
				
			}
			
			$("#month").val(parseInt(month)-1);
			$("#year").val(parseInt(cyear));
			
			$("#yeartext").text(cyear+"."+beforeMonth);
			console.log(cyear);
			
			console.log($("#month").val());
			
			drwaingTable();
			writeDate();
			
		});
		
		$(".s").on("click","li", function (e) {
			
			e.preventDefault();
			
			if(!$(this).css("background-color", "olive")){
				console.log("dont...")
				
			}
			
			var id = $(this).attr("id");
			var day = $(this).text();
			var title = $("#yeartext").text();
			var str = "<p id='today'>"+title+"."+day+"</p>"
			
			$("#modalText").before(str);
			
		 	// Get the modal
			var modal = document.getElementById('myModal');

			// Get the button that opens the modal
			var btn = document.getElementById("#"+id);
			modal.style.display = "block";
			
			$(".close").on("click", function (e) {
				
				modal.style.display = "none";
				$(".modal-content").find("p").remove();
				$("#modalText").val("");
				
			})
			
			/* // Get the <span> element that closes the modal
			var span = document.getElementsByClassName("close")[0];

			// When the user clicks on the button, open the modal 
			btn.onclick = function() {
			    modal.style.display = "block";
			}

			// When the user clicks on <span> (x), close the modal
			span.onclick = function() {
			    modal.style.display = "none";
			}

			// When the user clicks anywhere outside of the modal, close it
			window.onclick = function(event) {
			    if (event.target == modal) {
			        modal.style.display = "none";
			    }
			}
			  */
			
		});
		$("#scheduleSub").on("click", function (e) {
			
			var uid = $("#uid").val();
			var content = $("#modalText").val();
			var day = $("#today").text();
			
			$.ajax({
				url : "/schedule/regist",
				method : 'post',
				data : JSON.stringify({uid:uid,content:content,sdate:day}),
				dataType : 'json',
				processData: false,
		        contentType: "application/json",
				success : function() {
					
				}
				
			});
			alert("일정등록성공!");
			var modal = document.getElementById('myModal');
			modal.style.display = "none";
		})
		
		function getScheudle() {
			
			var uid = $("#uid").val();
			
			StartDay = new Date($("#year").val(),$("#month").val()-1,1).getDay();
			
			var month = "";
			var str = "";
			var target = "";
			var content = "";
			
			$.ajax({
				url : "/schedule/getSchedule/"+ uid + "",
				method : 'get',
				dataType : 'json',
				success : function(arr) {
					for (var i = 0; i < arr.length; i++) {
						
						target = arr[i].sdate;
						content = arr[i].content;
						var targetYear = target.substring(0,4);
						var targetDay = target.substring(target.lastIndexOf(".") + 1);
						var id = targetDay - StartDay + 1;
						console.log(targetDay);
						$("#"+id).css("background-color", "olive");
					}
				}
			});
			
		}getScheudle();
				
	});
</script>

<%@ include file="/resources/footer.jsp"%>
