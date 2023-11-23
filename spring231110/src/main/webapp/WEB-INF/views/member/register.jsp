<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<link href="../resources/css/register.css" rel="stylesheet">
</head>
<body>
	<!-- 사원 등록 폼 -->
	<div class="jb-container">
		<form action="/member/register" method="post"
			enctype="multipart/form-data" class="form">
			<div id="test">
				<select class="selectInput" name="depCd"
					aria-label="Default select example">
					<option selected>부서 선택</option>
					<option value="sales">영업</option>
					<option value="human">인사</option>
					<option value="general">총무</option>
				</select>
				<div class="mb-3 input-group-lg">
					<input type="text" class="inputCss t2" name="empNm"
						placeholder="Name" id="empNm">
				</div>
			</div>
			<div class="mb-3 input-group-lg idInputBox">
				<input type="text" class="inputCss" name="id" placeholder="Id"
					id="id">
				<button type="button" id="checkId">중복 확인</button>
			</div>
			<div class="mb-3 input-group-lg">
				<input type="password" class="inputCss" name="pw"
					placeholder="Password" id="pw">
					<p class="passwordCheck">비밀번호는 최소 8 자, 문자와 숫자가 포함되어야 합니다.</p>
					<p class="passwordCheckOk">사용가능한 비밀번호입니다.</p>
			</div>
			<div class="mb-3 input-group-lg">
				<input type="text" class="inputCss" name="addr"
					placeholder="Address" id="addr">
			</div>
			<div class="mb-3 input-group-lg">
				<input type="text" class="inputCss" name="phone" placeholder="Phone" id="phone">
				<p class="phoneCheck">전화번호는 000-0000-0000의 형식으로 입력해주십시오.</p>
			</div>
			<div class="mb-3 input-group-lg">
				<input type="text" class="inputCss" name="empBirth" id="birth"
					placeholder="birth(6자리)">
					<p class="birthCheck">생년월일은 6자리 숫자로 입력해주십시오.</p>
			</div>
			<div class="mb-3">
				<label for="f" class="form-label">프로필 이미지</label> <input
					class="form-control" type="file" name="profile" id="file">
			</div>

			<div class="btnContainer">
				<button type="submit" class="joinBtn">사원 등록</button>
			</div>
		</form>
		
		<!-- 아이디 중복체크 확인용 -->
		<input type='text' id="checkIdResult" hidden="hidden">
		<!-- 비밀번호 정규표현식 확인용 -->
		<input type='text' id="checkPwResult" hidden="hidden">
		<!-- 전화번호 정규표현식 확인용 -->
		<input type='text' id="checkPhoneResult" hidden="hidden">
		<!-- 생년월일 정규표현식 확인용 -->
		<input type='text' id="checkBirthResult" hidden="hidden">
		<!-- 이미지 정규표현식 확인용 -->
		<input type='text' id="checkImgResult" hidden="hidden">
		
		<!-- 로그인 창으로 넘어가기 -->
		<a href="/member/login">이미 가입된 아이디가 있으신가요?</a>
		<div class="explanation">
			<p>로그인 페이지로 넘어가기</p>
		</div>
	</div>
	
	<script type="text/javascript" src="../resources/js/register.js"></script>

</body>
</html>
