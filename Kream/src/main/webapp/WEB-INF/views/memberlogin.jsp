<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
a {
	text-decoration: none;
	color: black;
}

body {
	font-family: arial;
	font-size: 12pt;
	letter-spacing: 1px;
}

#header {
	height: 100px;
	width: 100%;
	border-bottom: 1px dotted black;
}

.name {
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

#low {
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

#nav {
	background-color: rgb(240, 240, 240);
	float: left;
	width: 150px;
	height: 760px;
	box-sizing: border-box;
	padding: 30px;
	font-size: 15pt;
	font-weight: bold;
	text-align: center;
}

#content {
	clear: none;
	height: 80%;
	width: 100%;
	box-sizing: border-box;
	padding: 20px;
	text-align: center;
	font-size: 20px;
	margin-left: 5%;
}

#footer {
	background-color: #d8d8d8;
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

ul {
   list-style:none;
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
</head>
<body>
	<div id="header">
		<div class="name">로그인 + memberlogin</div>
		<div id=top>
			<a href="noticepage">고객센터</a> |
			<!-- 비 로그인시 보이는 곳 -->
			<c:if test="${sessionScope.loginMember eq null}">
				<a href="memberjoinpage">회원가입</a> |
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
		<form action="memberlogin" method="post">
			<table>
				<tr>
					<th>작성 항목</th>
					<th>내용</th>
				</tr>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="mid"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input
				type="password" name="mpassword"></td>
				</tr>
			</table>
			<br>
			<input type="submit" value="로그인">
		</form>
	

		<ul>
			<li onclick="kakaoLogin();"><a href="javascript:void(0)"> <img
			src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg"
			width="200" />
			</a></li>
		</ul>
		<!-- 카카오 스크립트 -->
		<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
		<script>
	window.Kakao.init('ce1efc94d7563501501240bb5d61390f'); //발급받은 키 중 javascript키를 사용해준다.
	console.log(Kakao.isInitialized()); // sdk초기화여부판단
	//카카오로그인
	function kakaoLogin() {
	    window.Kakao.Auth.login({
	    	scope:'profile_nickname, account_email',
	      success: function (response) {
	        window.Kakao.API.request({
	          url: '/v2/user/me',
	          success: function (response) {
	        	  console.log("response :" , response)
	        	 
	        	  var data = {}
	        	  data.loginTime = response.connected_at;
	        	  data.email = response.kakao_account.email;
	        	  data.nickname = response.kakao_account.profile.nickname;
	        	  console.log("data :" , JSON.stringify(data));
	        	  
	        	  $.ajax({
	      			type: 'post', // 전송방식
	      			url: 'member/login/kakao', // 요청주소(컨트롤러에서 받는 주소)
	      			data: JSON.stringify(data), // 전송할 파라미터(데이터)
	      			contentType: 'application/json',
	      			dataType: 'json', // 리턴데이터형식(컨트롤러에서 다시 받아올 때)
	      			success: function(response){ // 성공시 처리할 함수
	      				if (response){
	      					location.href = "/kream"
	      				} else {
	      					alert("카카오 로그인 실패")
	      				}
	      			},
	      			error: function(error){
	      				console.log('제대로 안돌고 있음');
	      			}
	      		});	
	        	  console.log("success: ",)
	          },
	          fail: function (error) {
	            console.log("error: ",error)
	          },
	        })
	      },
	      fail: function (error) {
	        console.log("erorr: ", error)
	      },
	    })
	  }
	//카카오 로그아웃  
	function kakaoLogout() {
	    if (Kakao.Auth.getAccessToken()) {
	      window.Kakao.API.request({
	        url: '/v1/user/unlink',
	        success: function (response) {
	        	console.log(response)
	        },
	        fail: function (error) {
	          console.log(error)
	        },
	      })
	      window.Kakao.Auth.setAccessToken(undefined)
	    }
	  }  
	</script>



		<br> 
		<a href="memberjoinpage">회원가입</a> | 
		<a href="memberidfind">이메일아이디 찾기</a> | 
		<a href="memberpwfind">비밀번호 찾기</a>
	</div>

	<div id="footer">
		<p>개인 정보 처리 방침 _ 이용 약관</p>
		<p>주식 회사 : 대표 OOO _ 사업자등록 번호 _ 000-00-00000</p>
		<p>사업장소재지 : 인천광역시 남동구 학익동 인천 일보 아카데미</p>
	</div>
</body>
</html>