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
<title>페이지 제목</title>
<link href="<c:url value='/resources/css/l_side.css'/>" rel="stylesheet"
	type="text/css">
</head>

<body class="body">
	<!-- 왼쪽 사이드 메뉴 -->
	<div class="left_sidemenu_div">
		<div class="c_logo">
			<a href="index"> <img alt="회사로고 없음"
				src="../../resources/img/회사로고.jpg">
			</a> <a href="index">EZEN</a>
		</div>
		<ul class="left_sidemenu">
			<li id="mainmenu"><a href="#" data-hover="예약"><i
					class="bi bi-bookmarks"></i>예약</a>
				<ul class="submenu">
					<li><a href="#">주차등록</a></li>
					<li><a href="#">회의실예약</a></li>
					<li><a href="#">휴가신청</a></li>
					<li><a href="/board/register">게시판등록</a></li>
				</ul></li>
			<li id="mainmenu"><a href="#" data-hover="게시판"><i class="bi bi-card-text"></i>게시판</a>
				<ul class="submenu">
					<li><a href="#">공지사항</a></li>
					<li><a href="/board/departBoardList">부서게시판</a></li>
					<li><a href="/board/anonymousBoardList">익명게시판</a></li>
				</ul></li>
			<li id="mainmenu"><a href="#"><i class="bi bi-airplane"></i>동호회</a>
				<ul class="submenu">
					<li><a href="#">동호회신청</a></li>
					<li><a href="/board/clubBoardList">동호회게시판</a></li>
				</ul></li>
			<li id="mainmenu"><a href="#"><i class="bi bi-check2-square"></i>승인</a>
				<ul class="submenu">
					<li><a href="/approval/clubApproval">동호회 등록</a></li>
					<li><a href="#">차량 등록</a></li>
					<li><a href="#">사원 등록</a></li>
					<li><a href="#">사원 목록</a></li>

				</ul></li>
			<li id="mainmenu"><a href="#"><i class="bi bi-door-open"></i>멤버확인용</a>
				<ul class="submenu">
					<li><a href="/member/register">회원가입</a></li>
					<li><a href="#">동호회 설정</a></li>
					<li><a href="/member/login">로그인</a></li>
					<li><a href="/member/logout">로그아웃</a></li>
					<li><a href="/member/list">회원 리스트</a></li>
				</ul></li>
		</ul>
	</div>
</body>
</html>