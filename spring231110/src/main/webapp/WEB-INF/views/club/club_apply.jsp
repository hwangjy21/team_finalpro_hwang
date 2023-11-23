<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>메인 페이지</title>

<!-- css 관련 설정-->
<link href="<c:url value='/resources/css/club_apply.css'/>"
	rel="stylesheet" type="text/css">
<link href="<c:url value='/resources/css/index_all.css'/>"
	rel="stylesheet" type="text/css">
</head>

<body>
	<jsp:include page="../common/l_side.jsp" />
	<jsp:include page="../common/nav.jsp" />
	
<sec:authentication property="principal.mvo.id" var="id" />

	<div class="total">
		<div class="subject">동호회 신청하기</div>
		<div class="content">
			<div class="card_box">
				<ul class="cards">
					<c:forEach items="${list}" var="cvo">
						<li class="cards__item">
							<div class="card">
								
								<input type="hidden" value="${cvo.clubCd}" id="clubCd">
								<div class="card__image card__image--${cvo.clubCd}"></div>
								<div class="card__content">
									<div class="card__title">${cvo.clubNm}</div>
									<button class="btn btn--block card__btn_${cvo.clubCd}"
										id="modal-open" data-clubCd="${cvo.clubCd}" data-clubNm="${cvo.clubNm}" data-clubIntro="${cvo.clubIntro}" data-memberCnt="${cvo.memberCnt}" data-memberLimitCnt="${cvo.memberLimitCnt}">
										상세페이지</button>
								</div>
							</div>
						</li>
					</c:forEach>

				</ul>
			</div>
		</div>
	</div>

	<div class="popup-wrap" id="modal">
		<div class="popup">
			<h1 class="popup-head" id="clubName_m"></h1>
				

			<div class="popup-body">
				<div class="body-content">
					<input type="hidden" value="${id}" name="id" id="id" >
						
					<div class="body-contentbox_content" id="modalContent"></div>
					<div class="body-contentbox_member" id="modalMember"></div>
					<div class="body-contentbox_info" id="modalMember_info"></div>


				</div>
			</div>


			<div class="popup-foot">
				<span class="pop-btn confirm" id="confirm" data-id="${id}"  data-clubCd="${cvo.clubCd}">가입하기</span> <span
					class="pop-btn close" id="close">창 닫기</span>
			</div>
		</div>confirm_modal
	</div>
	<div class="">
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
</div>
	<jsp:include page="../common/footer.jsp" />
	<script type="text/javascript"
		src="../../../resources/js/club_apply.js"></script>
		

</body>

</html>

