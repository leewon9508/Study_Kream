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
  	font-size: 20px;
  	margin-left: 5%;
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
</script>
<script>
	// ID 정규식 검사(이메일주소 형식의 ID)
	function idCheck() {
		var mid = document.getElementById('mid').value;
		var idResult = document.getElementById('idresult');
		var exp = /^[0-9A-Z]([-_\.]?[0-9A-Z])*@[0-9A-Z]([-_\.]?[0-9A-Z])*\.[A-Z]{2,6}$/i;
		
		if(mid.length == 0) {
	    	idResult.style.color = 'red';
	    	idResult.innerHTML = '필수 입력 정보입니다.';
		} else if(mid.match(exp)) {
	 		idResult.style.color = 'green';
	 		idResult.innerHTML = '유효한 형식입니다.';
			// (ajax)ID 중복확인
			$.ajax({
				type: 'post', // 전송방식
				url: 'idcheck', // 요청주소(컨트롤러에서 받는 주소)
				data: {'mid': mid}, // 전송할 파라미터(데이터)
				dataType: 'text', // 리턴데이터형식(컨트롤러에서 다시 받아올 때)
				success: function(midcheck){ // 성공시 처리할 함수
					if(midcheck=="ok") {
						idResult.style.color = 'green';
						idResult.innerHTML = '사용가능한 이메일입니다.';
					} else {
						idResult.style.color = 'red';
						idResult.innerHTML = '이미 사용중인 이메일입니다.';
					}
				},
				error: function(error){
					console.log('제대로 안돌고 있음');
				}
			});
		} else {
			idResult.style.color = 'orange';
			idResult.innerHTML = '이메일 주소를 정확히 입력해주세요.';
		}
	}
	
	// 비밀번호 정규식 검사(8~16자리, 영문&숫자 조합)
	function pwCheck() {
	    var pw = document.getElementById('mpassword').value;
	    var pwResult = document.getElementById('pwresult');
	    var exp = /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d]{8,16}$/;
	    
	    if(pw.length == 0) {
	        pwResult.style.color = 'red';
	        pwResult.innerHTML = '필수 입력 정보입니다.';
	    } else if(pw.match(exp)) {
	        pwResult.style.color = 'green';
	        pwResult.innerHTML = '유효한 형식입니다.';
	    } else {
	        pwResult.style.color = 'orange';
	        pwResult.innerHTML = '영문, 숫자 조합 8~16자리를 사용.';
	    }
	}

	// 휴대폰번호 정규식 검사(010-111(1)-1111)
	function phoneCheck() {
		var phone = document.getElementById('mphone').value;
		var phoneResult = document.getElementById('phoneresult');
		var exp = /^01([0|1|6|7|8|9]?)*-([0-9]{3,4})*-([0-9]{4})$/;
		if(phone.length == 0) {
			phoneResult.style.color = 'red';
			phoneResult.innerHTML = '필수 입력 정보입니다.';
		} else if(phone.match(exp)) {
			phoneResult.style.color = 'green';
			phoneResult.innerHTML = '유효한 형식입니다.';
	    } else {
	    	phoneResult.style.color = 'orange';
	    	phoneResult.innerHTML = '예)010-123(4)-5678';
	    }	
	}
</script>
</head>
<body>
	<div id="header">
        <div class="name">회원 가입 + memberjoin</div>
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
		<form action="memberjoin" method="post">
		이메일 아이디: <input type="text" name="mid" id="mid" onkeyup="idCheck()" placeholder="예)kream@kream.co.kr"> <br>
					<span id="idresult"></span><br>
		비밀번호: <input type="password" name="mpassword" id="mpassword" onkeyup="pwCheck()" placeholder="영문,숫자 조합 8-16자"> <br>
				<span id="pwresult"></span><br>
		이름: <input type="text" name="mname"> <br>
		전화번호: <input type="text" name="mphone" id="mphone" onkeyup="phoneCheck()" placeholder="예)010-1234-5678"> <br>
				<span id="phoneresult"></span><br>
				
		<input type="submit" value="회원가입">
	</form>
	</div>

	<div id="footer">
		<p>개인 정보 처리 방침 _ 이용 약관</p>	
		<p>주식 회사 : 대표 OOO _ 사업자등록 번호 _ 000-00-00000</p>
		<p>사업장소재지 : 인천광역시 남동구 학익동 인천 일보 아카데미</p>
	</div>
</body>
</html>