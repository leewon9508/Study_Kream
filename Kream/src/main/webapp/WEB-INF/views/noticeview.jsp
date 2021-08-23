<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>noticeview</title>
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
    width: 100%;
    text-align: left;
    padding: 10px;
    font-size: 1px;
    height: 5%;
    bottom: 0;
    }

      table {
    width: 1200px;
    border-top: 1px solid #444444;
    border-collapse: collapse;
  font-size: 20px;
  margin-left: 20% ;
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
<script>
	// 공지사항 글 삭제 (관리자)
	function noticeDelete () {
		var check = confirm('공지사항 글을 삭제 하시겠습니까?');
		if (check) {
			console.log(${notice.nnumber});
			location.href = 'noticedelete?nnumber=' +${notice.nnumber};
			alert('삭제 되었습니다. 고객센터 페이지로 이동합니다.');
		} else {
			alert('삭제를 취소 합니다.');
		}
	}
</script>
</head>
<body>
	
 <div id="header">
        <div class="name">공지사항 상세 보기 + noticeview</div>
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
			<td>${notice.nnumber}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${notice.nwriter}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${notice.ntitle}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${notice.ncontents}</td>
		</tr>
		<tr>
			<td>작성시간</td>
			<td>${notice.ndate}</td>
		</tr>
	</table>
		<!-- 관리자 -->
		<c:if test="${sessionScope.loginMember eq 'admin'}"> 
			<h3>관리자 전용</h3>
			<button onclick="noticeDelete()">공지사항 글 삭제</button>
		</c:if>
		<br>
	</div>

	<div id="footer">
		<p>개인 정보 처리 방침 _ 이용 약관</p>	
		<p>주식 회사 : 대표 OOO _ 사업자등록 번호 _ 000-00-00000</p>
		<p>사업장소재지 : 인천광역시 남동구 학익동 인천 일보 아카데미</p>
	</div>
</body>
</html>