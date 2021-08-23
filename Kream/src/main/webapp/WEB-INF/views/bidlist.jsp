<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bidlist</title>
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
    font-size: 15pt;
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
<script>
// 구매 입찰 취소
function bidDelete (number, bidid) {
	console.log(number);
	console.log(bidid);
	var check = confirm('입찰을 취소 하시겠습니까?');
	if (check) {
		console.log(number);
		location.href = 'biddelete?bidnumber=' + number + '&bidid=' + bidid;
		alert('최소 되었습니다.');
	} else {
		alert('삭제를 취소 합니다.');
	}
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
</head>
<body>
<div id="header">
        <div class="name">구매 입찰 리스트 + bidlist</div>
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
		<a href="memberupdate">회원정보수정</a> <br>
		<c:if test="${address.id eq null}">
		<br>
		<a href="addresswritepage">주소 등록</a>
		<br>
	</c:if>
		<br>
	<c:if test="${address.id ne null}">
		<a href="addressview?id=${sessionScope.loginMember}">주소 확인</a>
		<br>
	</c:if>
	<h5>입찰</h5>
	<a href="bidlistpage?bidid=${sessionScope.loginMember}">구매 입찰 내역</a>
	<br>
	<a href="asklistpage?askid=${sessionScope.loginMember}">판매 입찰 내역</a>
	<br>
	<h5>즉시</h5>
	<a href="bidhistory?dbidid=${sessionScope.loginMember}">구매 내역</a>
	<br>
	<a href="askhistory?daskid=${sessionScope.loginMember}">판매 내역</a>
	<br>
	</div>
	<div id="content">
		<!-- 페이징 -->
			<table>
				<tr>
					<th>구매 입찰 제품 번호</th>
					<th>구매 입찰 가격</th>
					<th>구매 입찰 사이즈</th>
					<th>구매 입찰 수정</th>
					<th>구매 입찰 취소</th>
				</tr>
				<c:forEach var="bid" items="${bidList}">
					<tr>
						<td><a href="productview?pnumber=${bid.bidpnumber}&page=${paging.page}">${bid.bidpnumber}</a></td>
						<td>${bid.bidprice}</td>
						<td>${bid.bidsize}</td>
						<td><a href="bidupdate?bidnumber=${bid.bidnumber}">수정</a></td>
						<td><button onclick="bidDelete('${bid.bidnumber}','${sessionScope.loginMember}')">취소</button></td>
					</tr>
				</c:forEach>
			</table>
	
			<c:choose>
				<c:when test="${paging.page<=1}">
					[이전]&nbsp;
				</c:when>
				<c:otherwise>
					<a href="bidlistpage?page=${paging.page-1}">[이전]</a>&nbsp;
				</c:otherwise>
			</c:choose>
	
			<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i"
				step="1">
				<c:choose>
					<c:when test="${i eq paging.page}">
						${i} <!-- 현재 클릭한 페이지는 클릭 못하게 -->
					</c:when>
					<c:otherwise>
						<a href="bidlistpage?page=${i}">${i}</a>
						<!-- 현재 클릭한 페이지를 제외하고 클릭 할수 있게 -->
					</c:otherwise>
				</c:choose>
			</c:forEach>
	
			<c:choose>
				<c:when test="${paging.page>=paging.maxPage}">
					[다음]
				</c:when>
				<c:otherwise>
					<a href="bidlistpage?page=${paging.page+1}">[다음]</a>
				</c:otherwise>
			</c:choose>
	</div>
	<div id="footer">
		<p>개인 정보 처리 방침 _ 이용 약관</p>	
		<p>주식 회사 : 대표 OOO _ 사업자등록 번호 _ 000-00-00000</p>
		<p>사업장소재지 : 인천광역시 남동구 학익동 인천 일보 아카데미</p>
	</div>

</body>
</html>