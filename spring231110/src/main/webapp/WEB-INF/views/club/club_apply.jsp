<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	<div class="total">
		<div class="subject">동호회 신청하기</div>
		<div class="content">
			<div class="card_box">
				<ul class="cards">
					<c:forEach items="${list}" var="cvo">
						<li class="cards__item">
							<div class="card">
								<div class="card__image card__image--${cvo.clubCd } "></div>
								<div class="card__content">
									<div class="card__title">${cvo.clubNm }</div>

									<button class="btn btn--block card__btn_${cvo.clubCd }" id="modal-open" data-club-nm="${cvo.clubNm}">상세페이지</button>
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
				<div class="popup-head">
					<span class="head-title">z</span>
				</div>
				<div class="popup-body">
					<div class="body-content">
						<div class="body-titlebox">
							<h1 id="model_title">Confirm Modal</h1>
						</div>
						<div class="body-contentbox" id="modalContent">
							
							
						</div>


					</div>
				</div>


				<div class="popup-foot">
					<span class="pop-btn confirm" id="confirm">확인</span> <span
						class="pop-btn close" id="close">창 닫기</span>
				</div>
			</div>
		</div>

			<jsp:include page="../common/footer.jsp" />
			<script type="text/javascript"
				src="../../../resources/js/club_apply.js"></script>
</body>

</html>