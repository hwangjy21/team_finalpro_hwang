<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 


<!-- <html> -->
<!-- <head> -->
<!-- 	<title>Index Page</title> -->
<!-- </head> -->
<!-- <body> -->
<%-- <jsp:include page="common/header.jsp"/> --%>
<%-- <jsp:include page="common/nav.jsp"/> --%>

<!-- <h3> -->
<!-- 메인입니다. -->
<!-- </h3> -->

<%-- <jsp:include page="common/footer.jsp"/> --%>
<!-- </body> -->
<!-- </html> -->
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




    <title>실시간 채팅 메신저 서비스</title>

    <!-- Custom fonts for this template-->

<%--     <link href="<c:url value='../resources/vendor/fontawesome-free/css/all.min.css'/>" rel="stylesheet" type="text/css"> --%>
<!--     <link -->
<!--         href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" -->
<!--         rel="stylesheet"> -->

    <!-- Custom styles for this template-->
<%--     <link href="<c:url value='../resources/css/sb-admin-2.min.css'/>" rel="stylesheet" type="text/css"> --%>

<%--     <link href="<c:url value='../resources/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css"> --%>
    <link href="<c:url value='../resources/css/custom.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='../resources/css/bootstrap.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='/resources/css/r_side.css'/>" rel="stylesheet"
	type="text/css">

<link href="<c:url value='/resources/css/index_all.css'/>" rel="stylesheet"
	type="text/css">
</head>


<body >
<%-- <jsp:include page="../common/header.jsp"/> --%>
<jsp:include page="../common/nav.jsp"/>
<jsp:include page="../common/l_side.jsp" />








<div class="total">
<!-- <div class="subject">채딩페이지</div> -->
<div class="subject">
    <span>채팅 페이지</span>
<%--     <input type="hidden" style="height: 40px; width: 20%;" type="text" id="chatName" class="form-control" value="${username}"  maxlength="8" readonly="readonly"> --%>
    <a href="/chaturl/find" class="find-link">사원찾기</a> <!-- 컨트롤러가 잡게 함 -->
</div>

<div ><!-- 231111_1전경환 채팅창관련해서 뭐 넣기 S -------------------------------------> <!-- class="content" -->
<!-- https://www.youtube.com/watch?v=8s98IhtgwZ0&list=PLRx0vPvlEmdAlUbX_TGDxaSxKCvfl2isa&index=6 -->
<div >
    <div >
        <div >
            <div >
                <div class="portlet-heading">
                	<div class="portlet-title">
<!--                     	<h4><i class="fa fa-circle text-green"></i>실시간 채팅창</h4> -->
                    </div>	
                	<div class="clearfix"></div>
            	</div>
	            <div id="chat" >
	                <div id="chatList" style="overflow-y: auto; overflow-x: hidden; width: auto; height:550px;"> <!-- portlet-body chat widget -->

	                <!--------------- 여기에 뭔가 주루루륵 반복해서 들어가는 부분 같음 S------------------------------------------------------------------->
					<div class="row">
						<div class="col-lg-12">
							<div class="media">
								<a class="pull-left" href="#">
									<img class="media-object img-circle" style="width: 30px; height:30px;" src="/resources/img/anoyicon.png" alt="">
								</a>
							</div>

							<div class="media-body">
								<h4 class="media-heading">
									chatName<span class="small pull-rigth">chatTime </span>
								</h4>
								<p>chatContent</p>
							</div>
							

						</div>
					</div>
					
					<div class="row">
						<div class="col-lg-12">
							<div class="media">
								<a class="pull-left" href="#">

									<img class="media-object img-circle" style="width: 30px; height:30px;" src="/resources/img/anoyicon.png" alt="">
								</a>
							<div class="media-body">
								<h4 class="media-heading">
									chatName<span class="small pull-rigth"; style="margin-left: 10px;">     chatTime </span>

								</h4>
								<p>chatContent</p>
							</div>
							</div>
						</div>
					</div>
	                <!--------------- 여기에 뭔가 주루루륵 반복해서 들어가는 부분 같음 E------------------------------------------------------------------->
	                </div>

	                <div >    
	                    <div >
	                        <div >
<%-- 	                        <input type="text" id="chatName" value="${username}" /> --%>
	                            <input style="height: 40px; width: 20%;" type="text" id="chatName" class="form-control" value="${username}"  maxlength="8" readonly="readonly">
	                        </div>
	                    </div>
						<div style="height: 100px; display: flex; align-items: center;">
						    <div style="flex:0.5;">
						        <textarea style="height: 80px; width: 90%;" id="chatContent" class="form-control" placeholder="메시지를 입력하세요" maxlength="100"></textarea>
						    </div>
						    <div>
						        <button id="chatSubmitBtn" type="button" class="btn btn-default" >전송</button>
						    </div>
						</div>


	                </div>
	            </div>
           </div>
        </div>
    </div>
</div>
<div class="alert alert-success" id="successMessage" style="display: none;">
	<strong>메세지 전송에 성공했습니다.</strong>
</div>
<div class="alert alert-danger" id="dangerMessage" style="display: none;">
	<strong>이름과 내용을 모두 입력하세요</strong>
</div>
<div class="alert alert-warning" id="warningMessage" style="display: none;">
	<strong>데이터베이스 오류 발생</strong>
</div>



<!-- 231111_1전경환 채팅창관련해서 뭐 넣기 E -------------------------------------></div>
</div>







<script type="text/javascript" src="/resources/js/chat.js"></script>
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