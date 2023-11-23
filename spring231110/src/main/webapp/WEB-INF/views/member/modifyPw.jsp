<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join Page</title>
<link href="../resources/css/register.css" rel="stylesheet">
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
		<form action="/member/modifyPw" method="post"
			enctype="multipart/form-data" class="form">

			<div class="mb-3 input-group-lg">
				<input type="password" class="inputCss" name="pw"
					placeholder="새로운 Password" id="pw">
					<p class="passwordCheck">비밀번호는 최소 8 자, 문자와 숫자가 포함되어야 합니다.</p>
					<p class="passwordCheckOk">사용가능한 비밀번호입니다.</p>
			</div>
			<div class="mb-3 input-group-lg">
				<input type="password" class="inputCss" name="pw2" id="pw2"
					placeholder="Password 확인">
					<p class="passwordCheck2">비밀번호가 일치하지 않습니다.</p>
					<p class="passwordCheckOk2">비밀번호가 일치합니다.</p>
			</div>

			<!-- id 넘기기용 hidden input -->
			<div class="mb-3 input-group-lg" hidden="hidden">
				<input type="text" class="inputCss" name="id" value="${id }">
			</div>

			<div class="btnContainer">
				<button type="submit" class="joinBtn">비밀번호 변경</button>
			</div>
		</form>
	</div>
	<!-- 비밀번호 정규표현식 확인용 -->
	<input type='text' id="checkPwResult" hidden="hidden">
	
	<script type="text/javascript" src="../resources/js/modifyPw.js"></script>
</body>
</html>
