<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<!-- 본인 확인 폼 -->
	<div class="jb-container">
		<form action="/member/checkMemberInfo" method="post" class="form">
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
			<div class="mb-3 input-group-lg">
				<input type="text" class="inputCss" name="id" placeholder="Id" id="id">
			</div>
			<div class="mb-3 input-group-lg">
				<input type="text" class="inputCss" name="empBirth" id="birth"
					placeholder="birth(6자리)">
				<p class="birthCheck">생년월일은 6자리 숫자로 입력해주십시오.</p>
			</div>

			<div class="btnContainer">
				<button type="submit" class="joinBtn">본인 확인</button>
			</div>
		</form>

		<!-- 로그인 창으로 넘어가기 -->
		<a href="/member/login">로그인 창으로 돌아가기</a>
		<div class="explanationModify">
			<p>로그인 페이지로 넘어가기</p>
		</div>
	</div>

	<!-- 생년월일 정규표현식 확인용 -->
	<input type='text' id="checkBirthResult" hidden="hidden">
	
	<script type="text/javascript" src="../resources/js/checkMemberInfo.js"></script>
</body>
</html>