<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addresswrite</title>
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
       <div class="name">주소 등록 + addresswrite</div>
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
		<form action="addresswrite">
			<input type="hidden" value="${sessionScope.loginMember}" name="id">
		<table>
			<tr>
				<th>작성 항목</th>
				<th>내용</th>
			</tr>
			<tr>
				<td>우편번호</td>
				<td><input type="text" id="sample4_postcode" placeholder="우편번호" name="postcode"></td>
			</tr>
			<tr>
				<td>도로명 주소</td>
				<td><input type="text" id="sample4_roadAddress" placeholder="도로명주소" name="road"></td>
			</tr>
			<tr>
				<td>지번 주소</td>
				<td><input type="text" id="sample4_jibunAddress" placeholder="지번주소" name="jibun"></td>
			</tr>
			<tr>
				<td>상세 주소</td>
				<td><input type="text" id="sample4_detailAddress" placeholder="상세주소" name="detail"></td>
			</tr>
			<tr>
				<td>참고 항목</td>
				<td><input type="text" id="sample4_extraAddress" placeholder="참고항목" name="extra"></td>
			</tr>
		</table>
			<span id="guide" style="color:#999;display:none"></span>
			<br>			
		<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기">	<input type="submit" value="주소 등록">
		</form>
			
			
			
			
			
	
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
	    function sample4_execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var roadAddr = data.roadAddress; // 도로명 주소 변수
	                var extraRoadAddr = ''; // 참고 항목 변수
	
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('sample4_postcode').value = data.zonecode;
	                document.getElementById("sample4_roadAddress").value = roadAddr;
	                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
	                
	                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
	                if(roadAddr !== ''){
	                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
	                } else {
	                    document.getElementById("sample4_extraAddress").value = '';
	                }
	
	                var guideTextBox = document.getElementById("guide");
	                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
	                if(data.autoRoadAddress) {
	                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
	                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
	                    guideTextBox.style.display = 'block';
	
	                } else if(data.autoJibunAddress) {
	                    var expJibunAddr = data.autoJibunAddress;
	                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
	                    guideTextBox.style.display = 'block';
	                } else {
	                    guideTextBox.innerHTML = '';
	                    guideTextBox.style.display = 'none';
	                }
	            }
	        }).open();
	    }
	</script>
	</div>
		<div id="footer">
		<p>개인 정보 처리 방침 _ 이용 약관</p>	
		<p>주식 회사 : 대표 OOO _ 사업자등록 번호 _ 000-00-00000</p>
		<p>사업장소재지 : 인천광역시 남동구 학익동 인천 일보 아카데미</p>
	</div>
</body>
</html>