<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
		<div class="subject">승인하기</div>
		<div class="content">
			<table class="table">
				<thead>

					<tr>

						<th>승인번호</th>
						<th>동호회이름</th>
						<th>회원사번</th>
						<th>승인 신청 여부</th>
						
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${list_c}" var="cvo">
						<tr>
							<td>${cvo.apprNo}</td>
							<td>${cvo.clubNm}</td>
						
							
						</tr>
					</c:forEach>

				</tbody>
			</table>

			<!-- 페이징 라인  -->
			<nav aria-label="Page navigation example" class="paging_nav">
				<ul class="pagination">

					<!-- 이전 -->

					<li class="page-item ${(ph_c.prev eq false)? 'disabled':''} }"><a
						class="page-link"
						href="/approval/list?pageNo=${ph_c.startPage - 1}&qty=${ph_c.pgvo.qty}&type=${ph_c.pgvo.type}&keyword=${ph_c.pgvo.keyword}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
					<c:forEach begin="${ph_c.startPage}" end="${ph_c.endPage }" var="i">
						<li class="page-item"><a class="page-link"
							href="/board/list?pageNo=${i } &qty=${ph_c.pgvo.qty}&type=${ph_c.pgvo.type}&keyword=${ph_c.pgvo.keyword}">${ i}</a></li>
					</c:forEach>
					<!-- 다음 -->
					<li class="page-item ${(ph_c.next eq false)? 'disabled':''} }"><a
						class="page-link"
						href="/board/list?pageNo=${ph_c.endPage + 1}&qty=${ph_c.pgvo.qty}&type=${ph_c.pgvo.type}&keyword=${ph_c.pgvo.keyword}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
			</nav>
</div>
</div>

			<jsp:include page="../common/footer.jsp" />
</body>



<jsp:include page="../common/footer.jsp" />



</body>

</html>

