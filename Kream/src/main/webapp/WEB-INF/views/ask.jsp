<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ask</title>
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
  	font-size: 20px;
  	margin-left: 5%;
}

#footer{ background-color: #d8d8d8;
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
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
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
	
	// 판매 입찰 아이디 중복 확인
	function check(){
		var askid = '${sessionScope.loginMember}';
		var askpnumber = '${product.pnumber}';
		console.log(askid);
		console.log(askpnumber);
			$.ajax({
				type: 'post', 
				url: 'askidcheck',
				data: {'askid': askid,
						'askpnumber': askpnumber}, 
				dataType: 'text',
				success: function (result) { 
					if(result =="ok"){
						askintputform.submit();
					}else {
						alert('이미 판매입찰을 하셨습니다');
					}
				},
				error: function () {
					console.log('제대로 안돌고 있음');
				}
			});
		}
	
</script>

</head>
<body>
	<div id="header">
        <div class="name">판매 입찰 + ask</div>
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
		
			<!-- 페이징 -->
			<table>
				<tr>
					<th>판매 입찰 가격</th>
					<th>판매 입찰 사이즈</th>
				</tr>
				<c:forEach var="ask" items="${askList}">
					<tr>
						<td>${ask.askprice}</td>
						<td>${ask.asksize}</td>
					</tr>
				</c:forEach>
			</table>
	
			<c:choose>
				<c:when test="${paging.page<=1}">
					[이전]&nbsp;
				</c:when>
				<c:otherwise>
					<a href="askpage?page=${paging.page-1}&askpnumber=${product.pnumber}&pnumber=${product.pnumber}">[이전]</a>&nbsp;
				</c:otherwise>
			</c:choose>
	
			<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i"
				step="1">
				<c:choose>
					<c:when test="${i eq paging.page}">
						${i} <!-- 현재 클릭한 페이지는 클릭 못하게 -->
					</c:when>
					<c:otherwise>
						<a href="askpage?page=${i}&askpnumber=${product.pnumber}&pnumber=${product.pnumber}">${i}</a>
						<!-- 현재 클릭한 페이지를 제외하고 클릭 할수 있게 -->
					</c:otherwise>
				</c:choose>
			</c:forEach>
	
			<c:choose>
				<c:when test="${paging.page>=paging.maxPage}">
					[다음]
				</c:when>
				<c:otherwise>
					<a href="askpage?page=${paging.page+1}&askpnumber=${product.pnumber}&pnumber=${product.pnumber}">[다음]</a>
				</c:otherwise>
			</c:choose>
			
			<div id="input">
			<c:if test="${sessionScope.loginMember != 'admin'}">
				<h5>판매 입찰 금액, 사이즈 입력</h5>
					<form action="askinput" method="post" name="askintputform">
						<input type="hidden" name="pnumber" value="${product.pnumber}" >
						<input type="hidden" name="askpnumber" value="${product.pnumber}" >
						<input type="hidden" name="askid" value="${sessionScope.loginMember}" >
						입찰 금액<input type="text" name="askprice" id="askprice" placeholder="숫자로 금액을 입력하세요" />원 <br>
						240<input type="radio" id="asksize" name="asksize" value="240">
						250<input type="radio" id="asksize" name="asksize" value="250">
						260<input type="radio" id="asksize" name="asksize" value="260">
						270<input type="radio" id="asksize" name="asksize" value="270">
						<br>
						<input type="button" value="판매 입찰 등록" onclick="check()">
					</form>
			</c:if>
		</div>
	</div>
		<div id="footer">
		<p>개인 정보 처리 방침 _ 이용 약관</p>	
		<p>주식 회사 : 대표 OOO _ 사업자등록 번호 _ 000-00-00000</p>
		<p>사업장소재지 : 인천광역시 남동구 학익동 인천 일보 아카데미</p>
	</div>
</body>
</html>