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


<link href="<c:url value='/resources/css/index_all.css'/>"
	rel="stylesheet" type="text/css">



</head>


<body>
	<jsp:include page="common/l_side.jsp" />

	<jsp:include page="common/nav.jsp" />
	<div class="total">
		<div class="subject">주제입니다.</div>
		<div class="content">content 입니다.</div>
	</div>
	<jsp:include page="common/footer.jsp" />




</body>

</html>