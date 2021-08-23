<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
    width: 150px;
    height : 760px;
    box-sizing: border-box;
    padding: 30px;
    font-size: 15pt;
    font-weight: bold;
    text-align: center;
}

#content{ 
    clear: none;
    height: 80%;
    width:100%;
    box-sizing: border-box;
  	padding: 20px;
  	text-align: center;
  	font-size: 18px;
  	margin-left: 5%;
}

#footer{ background-color: #d8d8d8;
    float: left;
    clear: none;
    position: absolute;
    left: 0;
    
    width: 100%;
    text-align: left;
    padding: 10px;
    font-size: 1px;
    }

    table {
    width: 1500px;
    border-top: 1px solid #444444;
    border-collapse: collapse;
    text-align: center;
  }
  th, td {
    border-bottom: 1px solid #444444;
    padding: 10px;
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
	
	function jjim() {
		var kid = '${sessionScope.loginMember}';
		var kpnumber = '${product.pnumber}';
		console.log("jjim");
		if ('${sessionScope.loginMember}' != null) {
			location.href = "jjim?kid=" + kid + "&kpnumber=" + kpnumber;
			alert('찜등록완료');
		} else {
			alert('찜등록실패');
		}
	}
	
</script>
</head>
<body>
	<div id="header">
        <div class="name">상품 뷰 + productview</div>
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
	<div id="content">
	<table>
		<tr>
			<th>항목</th>
			<th>내용</th>
		</tr>
		<tr>
			<td>제품번호</td>
			<td>${product.pnumber}</td>
		</tr>
		<tr>
			<td>제품명</td>
			<td>${product.pname}</td>
		</tr>
		<tr>
			<td>제품 브랜드</td>
			<td>${product.pbrand}</td>
		</tr>
		<tr>
			<td>발매가</td>
			<td>${product.pretail}</td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td>${product.pfilename}</td>
		</tr>
		<tr>
			<td>이미지</td>
			<td><img src="resources/ProductImage/${product.pfilename}" height="150" width="115"></td>
		</tr>
	</table>
	
	<c:if test="${sessionScope.loginMember ne null}">
		<c:if test="${sessionScope.loginMember == 'admin'}">
				<a href="productupdate?pnumber=${product.pnumber}">제품 수정</a>
				<a href="productdelete?pnumber=${product.pnumber}">제품 삭제</a><br>
		</c:if>
				<div id ="order">
				<button onclick="jjim()">관심상품등록</button>
					<h3>구매</h3>
					<a href="bidpage?bidpnumber=${product.pnumber}&pnumber=${product.pnumber}">구매 입찰</a><br>
					<a href="bidimmediatelypage?&askpnumber=${product.pnumber}&pnumber=${product.pnumber}">즉시 구매</a><br>
					<h3>판매</h3>
					<a href="askpage?askpnumber=${product.pnumber}&pnumber=${product.pnumber}">판매 입찰</a><br>
					<a href="askimmediatelypage?&bidpnumber=${product.pnumber}&pnumber=${product.pnumber}">즉시 판매</a><br>
				</div>
	</c:if>
	</div>
	
    <div id="footer">
		<p>개인 정보 처리 방침 _ 이용 약관</p>	
		<p>주식 회사 : 대표 OOO _ 사업자등록 번호 _ 000-00-00000</p>
		<p>사업장소재지 : 인천광역시 남동구 학익동 인천 일보 아카데미</p>
	</div>
</body>
</html>