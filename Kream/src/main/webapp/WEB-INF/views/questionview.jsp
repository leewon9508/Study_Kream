<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>questionview</title>

<style>

html, body {
    height: 100%;

}


a{text-decoration:none;
	color: black;
}

body {   
    font-family: arial;
    font-size: 12pt;
    letter-spacing: 1px;
}

#header{
    height: 100px;
    width: 100%;
    border-bottom: 1px dotted black;
}
.name{
    padding: 10px;
    text-align: left;
    float: left;
}

#top {  
    float: right;
    clear: none;
    width: 70%;
    box-sizing: border-box;
    padding: 10px;
    text-align: right;
}

#low{ 
    color: #FFFFFF;
    text-align: right;
    float: left;
    clear: none;
    width: 100%;
    box-sizing: border-box;
    padding: 10px;

}

#low a {
    text-align: left;
    float: left;
    line-height: 25px;
}

#nav{background-color: rgb(240,240,240);
    float: left;
    width: 13%;
    height : 1000px;
    box-sizing: border-box;
    padding: 30px;
    font-size: 15pt;
    font-weight: bold;
    text-align: center;
    left: 0;
}

#content{ 
    clear: none;
    width:100%;
    box-sizing: border-box;
  	padding: 20px;
  	text-align: center;
  	font-size: 30px;
}

#footer{ background-color: #d8d8d8;
    float: left;
    bottom: 0;
    width: 100%;
    text-align: left;
    padding: 10px;
    font-size: 1px;
    }

      table {
    width: 1000px;
    border-top: 1px solid #444444;
    border-collapse: collapse;
    text-align: center;
    font-size: 20px;
    margin-left: 30% ;
  }
  th, td {
    border-bottom: 1px solid #444444;
    padding: 10px;
  }
  
  #comment {
  	margin-left: 10%; 
  	font-size: 15px; 
  }
  #comment-list{
   font-size: 20px;
  }
</style>
<script>
<!-- 로그아웃 -->
	function logout() {
		var check = confirm('로그아웃 하시겠습니까?');
		if (check) {
			location.href = "logout";
			alert('로그아웃 성공, 초기 화면으로 돌아갑니다.');
		} else {
			alert('로그아웃 취소');
		}
	}
</script>

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>

	// 1대1 문의 글 삭제
	function questionDelete () {
		var check = confirm('1대1 문의 글을 삭제 하시겠습니까?');
		if (check) {
			console.log(${question.qnumber});
			location.href = 'questiondelete?qnumber=' +${question.qnumber};
			alert('삭제 되었습니다. 1대1 문의로 이동합니다.');
		} else {
			alert('삭제를 취소 합니다.');
		}
	}
	
	//댓글 내용
	$(document).ready(function(){
		$("#cwrite-btn").click(function(){
			var cwriter = document.getElementById('cwriter').value;
			var ccontents = document.getElementById('ccontents').value;
			var cbnumber = '${question.qnumber}';
			var loginUser = '<c:out value = "${sessionScope.loginMember}" />';
			console.log(cwriter);
			console.log(ccontents);
			console.log(cbnumber);
			console.log(loginUser);
			if (ccontents.length == 0) {
				alert('댓글을 입력 하세요.');
				} else {
					$.ajax({
						type: 'post',
						url: 'commentwrite',
						data:{
							'cwriter': cwriter,
							'ccontents': ccontents,
							'cbnumber': cbnumber},
						dataType: 'json',
						success: function(list){
							console.log(list);
							var output = "<h5>댓글 목록</h5><table>";
							output += "<tr><th>작성자</th>";
							output += "<th>내용</th>";
							output += "<th>날짜</th>";
							output += "<th>삭제</th></tr>";
								console.log(cwriter);
								console.log(loginUser);
							for(var i in list){
								console.log(list[i].cwriter);
									output += "<tr>";
									output += "<td>"+list[i].cwriter+"</td>";
									output += "<td>"+list[i].ccontents+"</td>";
									output += "<td>"+list[i].cdate+"</td>";
									if (list[i].cwriter == loginUser) {
										output += "<td><button onclick=commentDelete("+"'"+list[i].cnumber+"'"+")>삭제</button></td>";										
									}
									output += "</tr>";
							}
							output += "</table>";
							document.getElementById('comment-list').innerHTML = output;
							document.getElementById('cwriter').value='${sessionScope.loginMember}';
							document.getElementById('ccontents').value='';
						},
						error: function(){
							console.log('문제있음.');
						}
					});
				}
			});
		});
	
	// 댓글 삭제
	function commentDelete(cnumber) {
		var cbnumber = '${question.qnumber}';
		var loginUser = '<c:out value = "${sessionScope.loginMember}" />';
		console.log(cnumber);
		console.log(cbnumber);
		if(confirm('댓글을 삭제 하시겠습니까?')){
		$.ajax({
			type: 'post',
			url: 'commentDelete',
			data:{
				'cnumber': cnumber,					
				'cbnumber': cbnumber
				},
			dataType: 'json',
			success: function(list){
				console.log(list);
				var output = "<h5>댓글 목록</h5> <table>";
				output += "<tr><th>작성자</th>";
				output += "<th>내용</th>";
				output += "<th>날짜</th>";
				output += "<th>삭제</th></tr>";
				for(var i in list){
					output += "<tr>";
					output += "<td>"+list[i].cwriter+"</td>";
					output += "<td>"+list[i].ccontents+"</td>";
					output += "<td>"+list[i].cdate+"</td>";
					if (list[i].cwriter == loginUser) {
						output += "<td><button onclick=commentDelete("+"'"+list[i].cnumber+"'"+")>삭제</button></td>";										
					}
					output += "</tr>";
				}
				output += "</table>";
				document.getElementById('comment-list').innerHTML = output;
				document.getElementById('cwriter').value='${sessionScope.loginMember}';
				document.getElementById('ccontents').value='';
			},
			error: function(){
				console.log('문제있음.');
				console.log('삭제 실패.');
				alert('삭제하지 못했습니다.');
			}
		});
	   }
	}
	
</script>
</head>
<body>
 <div id="header">
        <div class="name">1:1 문의 상세 보기 + questionview</div>
        <div id=top>
			<a href="noticepage">고객센터</a> 
			<!-- 비 로그인시 보이는 곳 -->
			<c:if test="${sessionScope.loginMember eq null}">
				<a href="memberjoinpage">회원가입</a>
				<a href="memberloginpage">로그인</a>
				</c:if>

			<!-- 로그인 시 보여지는  곳 -->
			<c:if test="${sessionScope.loginMember ne null}">
				<!-- 회원에게만 보이는 -->
				<c:if test="${sessionScope.loginMember ne 'admin'}">
					<a href="mypage?id=${sessionScope.loginMember}">마이페이지</a>
				
					<a href="pointpage?mid=${sessionScope.loginMember}">포인트</a>
				</c:if>
				
				<!-- 관리자에게 보여지는 곳 -->
				<c:if test="${sessionScope.loginMember eq 'admin'}">
					<input type="button" value="제품등록(관리자전용)"
						onClick="location.href='productregistrationpage'">
					<input type="button" value="회원목록조회(관리자전용)"
						onClick="location.href='memberlist'">
				</c:if>

				<!-- 모두에게 보이는 -->
				${sessionScope.loginMemberName}님 반갑습니다!
                <button onclick="logout()">로그아웃</button>
                </c:if>
		</div>
		<div id=low>
			<a href="./"><img src="resources\\ProductImage\\kream.png" width="100" height="35"></a>
			<form action="search" method="get">
				<select name="searchtype">
					<option value="pname">제품명</option>
					<option value="pbrand">제품브랜드</option>
				</select> <input type="text" name="keyword" placeholder="검색어입력"> <input
					type="submit" value="검색">
			</form>
		</div>
	</div>
	
	<div id="nav">
		<a href="noticepage">공지사항</a> <br> <br>
		<a href="questionpage">1:1 문의</a>
	</div>
	
	<div id="content">
		<table>
		<tr>
			<th>항목</th>
			<th>내용</th>
		</tr>
		<tr>
			<td>글번호</td>
			<td>${question.qnumber}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${question.qwriter}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${question.qtitle}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${question.qcontents}</td>
		</tr>
		<tr>
			<td>작성시간</td>
			<td>${question.qdate}</td>
		</tr>
	</table>
		<div id="comment">
			<h5>댓글 작성</h5>
			<div id="comment-write">
				작성자 : <input type="text" id="cwriter" readonly="readonly" value="${sessionScope.loginMember}"> <br> 
				내용 : <input type="text" id="ccontents" maxlength="100"> <br>
				<button id="cwrite-btn">댓글 등록</button>
			</div>
		</div>
		<br>
		<div id="comment-list">
		<h5>댓글 목록</h5>
			<table>
				<tr>
					<th>댓글 작성자</th>
					<th>댓글 내용</th>
					<th>날짜</th>
					<th>삭제</th>
				</tr>
				<c:forEach var="comment" items="${commentList}">
					<tr>
						<td>${comment.cwriter}</td>
						<td>${comment.ccontents}</td>
						<td>${comment.cdate}</td>
						<td>
							<!-- 작성자전용 --> 
							<c:if test="${sessionScope.loginMember == comment.cwriter}">
								<button onclick="commentDelete('${comment.cnumber}')">삭제</button>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<!-- 작성자전용 -->
		<c:if test="${sessionScope.loginMember == question.qwriter}">
			<button onclick="questionDelete()">1대1 문의 글 삭제</button>
			<a href="questionupdate?qnumber=${question.qnumber}">1대1 문의 글 수정</a>
		</c:if>
	</div>

	<div id="footer">
		<p>개인 정보 처리 방침 _ 이용 약관</p>	
		<p>주식 회사 : 대표 OOO _ 사업자등록 번호 _ 000-00-00000</p>
		<p>사업장소재지 : 인천광역시 남동구 학익동 인천 일보 아카데미</p>
	</div>
</body>
</html>