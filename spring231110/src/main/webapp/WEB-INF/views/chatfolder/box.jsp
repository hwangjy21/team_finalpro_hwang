<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 

<!DOCTYPE html>
<html lang="ko">
<style>


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

    <link href="<c:url value='../resources/bootstrap337/css/custom.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='../resources/bootstrap337/css/bootstrap.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='../resources/css/r_side.css'/>" rel="stylesheet"
	type="text/css">

<link href="<c:url value='../resources/css/index_all.css'/>" rel="stylesheet"
	type="text/css">
</head>

<body >

<jsp:include page="../common/nav.jsp"/>
<jsp:include page="../common/l_side.jsp" />








<div class="total">
<!-- <div class="subject">채딩페이지</div> -->

<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">
   		<li><a href="/chaturl/chat">채팅메신저페이지</a> </li>
	    <li><a href="/member/index">메인</a></li>
	    <li><a href="/chaturl/find" class="find-link">사원찾기</a></li> <!-- 컨트롤러가 잡게 함 -->
	    <li class="active"><a href="/chaturl/box">메시지함<span id="unread" class="label label-info"></span></a></li> <!-- 컨트롤러가 잡게 함 -->
    </ul>
</div>




<!-- 231111_1전경환 채팅창관련해서 뭐 넣기 S -------------------------------------> 
<div>

<div>

    <div>

        <div>
            <div>
                <div class="portlet-heading">
                	<div class="portlet-title">
                    </div>	
                	<div class="clearfix"></div>
            	</div>
	            <div id="chat" >
	                <div id="chatList" style="overflow-y: auto; overflow-x: hidden; width: auto; height:550px;"> <!-- portlet-body chat widget -->

							
							
							    <div class="container">
									<table class="table" style="margin: 0 auto">
										<thead>
											<tr>
												<th><h4>주고받은 메시지 목록</h4></th>
											</tr>
										</thead>
										<div style="overflow-y: auto; width: 100%; max-height: 450px;">
										    <table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd; margin: 0 auto;">
										        <tbody id="boxTable">
										            <tr onclick="location.href='chat2.jsp?toID=<some_id>'">
										                <td style="width: 150px;">
										                    <h5>Test</h5>
										                </td>
										                <td>
										                    <h5>Test</h5>
										                </td>
										                <td>
										                    <div class="pull-right">Test</div>
										                </td>
										            </tr>
										        </tbody>         
										    </table>
										</div>
									</table>
								</div>
					
					
					

	                </div>
	                <div>    
	                    <div>
	                        <div>

	                            <input style="height: 40px; width: 20%;" type="text" id="chatName" class="form-control" value="${username}"  maxlength="8" readonly="readonly">
	                        </div>
	                    </div>
						<div style="height: 100px; display: flex; align-items: center;">

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


</div>
<!-- 231111_1전경환 채팅창관련해서 뭐 넣기 E ------------------------------------->
</div>






<script type="text/javascript" src="/resources/js/box.js"></script>


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