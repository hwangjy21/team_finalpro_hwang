<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>navBar</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<link href="<c:url value='/resources/css/nav.css'/>" rel="stylesheet"
	type="text/css">
</head>
<body>
	<!-- nav -->
	<nav class="nav_header">
		<div class="icon_div" style="font-size: 30px;"><i class="bi bi-bell"></i></div>
	
		<div class="c_logo">
			<a href="index"> <img alt="회사로고 없음"
				src="../../resources/img/회사로고.jpg">
			</a>
		</div>
	</nav>
</body>
</html>
