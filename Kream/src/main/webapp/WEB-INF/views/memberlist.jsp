<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	function memberviewAjax(mid) {
		$.ajax({
			type: 'post',
			url: 'memberviewajax',
			data: {'mid': mid},
			dataType: 'json',
			success: function(result){
				var output = "<table>";
				output += "<tr><th>ID</th> <th>PASSWORD</th> <th>NAME</th>";
				output += "<th>PHONE</th></tr>";
				output += "<tr>";
				output += "<td>"+result.mid+"</td>";
				output += "<td>"+result.mpassword+"</td>";
				output += "<td>"+result.mname+"</td>";
				output += "<td>"+result.mphone+"</td>";
				output += "</tr>";
				output += "</table>";
				
				document.getElementById('memberviewdiv').innerHTML = output; 
				
			},
			error: function(){
				console.log('에러');
			}
			
			
			
		});
	}
	
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

<style>

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
    width: 200px;
    height : 780px;
    box-sizing: border-box;
    padding: 30px;
    font-size: 10pt;
    font-weight: bold;
    text-align: center;
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
    clear: none;
    position: absolute;
    left: 0;
    bottom: 0;
    width: 100%;
    text-align: left;
    padding: 10px;
    font-size: 1px;
    }

      table {
    width: 1300px;
    border-top: 1px solid #444444;
    border-collapse: collapse;
    text-align: center;
    margin-left: 15%;
  }
  th, td {
    border-bottom: 1px solid #444444;
    padding: 10px;
  }
</style>
</head>
<body>
	<div id="header">
        <div class="name">멤버 리스트 + memberlist</div>
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
	

	<table>
		<tr>
			<th>이메일 아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>상세조회</th>
			<th>삭제</th>
			<th>상세조회(ajax)</th>
		</tr>
		<c:forEach var="member" items="${memberList}">
			<tr>
				<td>${member.mid}</td>
				<td>${member.mpassword}</td>
				<td>${member.mname}</td>
				<td>${member.mphone}</td>
				
				<td><a href="memberview?mid=${member.mid}">회원조회</a>
				<td><button onclick="deletefn('${member.mid}')">회원삭제</button>
				<td><button onclick="memberviewAjax('${member.mid}')">회원조회(ajax)</button>
			</tr>
		</c:forEach>
	</table>

	<div id="memberviewdiv"></div>
	
	<script>
		function deletefn(id){
			location.href="memberdelete?mid="+id;
		}
	</script>
	

	
	<div id="footer">
		<p>개인 정보 처리 방침 _ 이용 약관</p>	
		<p>주식 회사 : 대표 OOO _ 사업자등록 번호 _ 000-00-00000</p>
		<p>사업장소재지 : 인천광역시 남동구 학익동 인천 일보 아카데미</p>
	</div>
</body>
</html>