<%@ page language="java" contentType="text/html; charset=UTF-8"

     pageEncoding="UTF-8"%> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 

<!DOCTYPE html>
<html lang="ko">
<style>
/* .total {
    margin: 20px;
    padding: 20px;
    border: 1px solid #ddd;
}
 */
/* .subject {
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 15px;
} */

/* .content {
    background-color: #f9f9f9;
    padding: 15px;
    border-radius: 5px;
} */

/* 채팅 관련 스타일 */
.media {
    padding: 10px;
    border-bottom: 1px solid #eee;
    height : 70px;
}

.media img {
    border-radius: 50%;
}

.media-heading {
    font-weight: bold;
}
.subject {
    display: flex; /* Flexbox를 사용하여 아이템들을 가로로 배열 */
    justify-content: space-between; /* 아이템들 사이에 공간을 균등하게 분배 */
}

.find-link {
    margin-left: 20px; /* 원하는 간격을 설정 */
}
/* 추가적인 스타일링 필요 */

</style>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

    <title>실시간 채팅 메신저 서비스</title>


    <link href="<c:url value='../resources/bootstrap337/css/custom.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='../resources/bootstrap337/css/bootstrap.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='/resources/css/r_side.css'/>" rel="stylesheet"	type="text/css">
 
	<link href="<c:url value='/resources/css/index_all.css'/>" rel="stylesheet" 	type="text/css">
	
	
 	<script src="/resources/bootstrap337/js/bootstrap.js"></script>
	
</head>

<body >
<%-- <jsp:include page="../common/header.jsp"/> --%>


<jsp:include page="../common/nav.jsp"/>
<jsp:include page="../common/l_side.jsp" />

<div class="total">



	<div class="container">
		<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd;">
			<thead>
				<tr>
					<th colspan="2" style="background-color: skyblue;"><h4 style="color: black;">검색으로 친구 찾기</h4></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td style="width: 110px;"><h5>친구 아이디</h5></td>
					<td><input class="form-control" type="text" id="findID" maxlength="20" placeholder="찾을 아이디를 입력하세요"> </td>
				</tr>
				<tr>
					
					<td colspan="2">
					<input type="hidden" style="height: 40px; width: 20%;" type="text" id="chatName" class="form-control" value="${username}"  maxlength="8" readonly="readonly">
					<button id ="findFcBtn" class="btn btn-primary pull-right"  >검색</button> 
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="container">
		<table id="friendResult" class="table table-borderd table-hover" style="text-align: center; border: 1px solid #dddddd;">
			<%--여기가 반복되면서 친구 검색 결과를 알수 있는 부분임 
				보통은 1명이나 이름으로 검색할 경우 여러명이 보일수도 있는 위치 --%>
<!-- 				<thead> -->
<!--                 <tr> -->
<!--                 <th><h4>검색결과</h4></th> -->
<!--                 </tr> -->
<!--                 </head> -->
<!--                 <tbody> -->
<!--                 <tr> -->
<!--                 <td style="text-align: center;"> -->
<%--                 <h3>${findID} testID</h3> --%>
<!--                 <a href="http://www.naver.com" class="btn btn-primary pull-right"> -->
<!--                 메시지보내기 -->
<!--                 </td> -->
<!--                 </tr> -->
<!--                 </tbody> -->
		</table>
	</div>



</div><!-- <div class="total"> 끝 -->








			<!-- 모달창 뭐 언젠가 쓸거 같아서 일단 박아둠231117전경환 S-->
			<div class="modal" id="myModal" tabindex="-1">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Writer</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>

						<div class="modal-body">
							<div class="input-group mb-3">
								<input type="text" class="form-control" id="cmtTextModal"
									placeholder="Test Comment">
								<button class="btn btn-primary" id="cmtModBtn" type="button">댓글수정</button>
							</div>

							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">모달창닫기Close</button>

							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 모달창 뭐 언젠가 쓸거 같아서 일단 박아둠231117전경환 E-->












<script type="text/javascript" src="/resources/js/find.js"></script>

<!-- <script type="text/javascript" src="/resources/js/chat.js"></script> -->
<script type="text/javascript">
	printChatList();
</script>

    <script type="text/javascript">
        // 문서가 준비되면 실행될 함수를 정의합니다.
        function setup() {
            // input 필드에 keypress 이벤트 리스너를 추가합니다.
            document.getElementById('chatContent').addEventListener('keypress', function(e) {
                // 엔터 키의 키 코드는 13입니다.
                if (e.keyCode === 13) {
                    // 엔터 키가 눌렸다면, chatSubmitBtn의 클릭 이벤트를 트리거합니다.
                    document.getElementById('chatSubmitBtn').click();
                }
            });
        }

        // 페이지 로딩 후 setup 함수를 호출합니다.
        window.onload = setup;
    </script>


<jsp:include page="../common/footer.jsp"/>

</body>


</html>