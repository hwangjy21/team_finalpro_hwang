<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<link href="../resources/css/login.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="jb-container">
		<form action="/member/login" method="post">
			<div class="mb-3">
				<input type="text" class="form-control inputCss" name="id" id="e"
					placeholder="아이디">
			</div>
			<div class="mb-3">
				<input type="password" class="form-control inputCss" name="pw"
					id="p" placeholder="비밀번호">
			</div>
			<%-- 		${param.errMsg} --%>
			<c:if test="${not empty param.errMsg }">
				<script type="text/javascript">
					console.log("errMsg메시지가 들어온듯");
				</script>
				<div class="text-danger mb-3">

					<c:choose>
						<c:when test="${param.errMsg eq 'Bad credentials'}">
							<!-- BadCredentialsException 관련 231030-->
							<c:set var="errText" value="이메일 & 비밀번호가 일치하지 않습니다." />
						</c:when>
						<c:otherwise>
							<c:set var="errText" value="관리자에게 문의해주세요. " />
						</c:otherwise>
					</c:choose>
					${errText}
				</div>
			</c:if>
			<div class="btnContainer">
				<button class="w-100 btn btn-primary btn-lg my-5 loginBtn"
					type="submit">로그인</button>
			</div>
		</form>
		<!-- 회원가입 창으로 넘어가기 -->

		<div class="aTagDiv">
			<a href="/member/register" class="aTag" id="registerATag">가입된 아이디가 없으신가요?</a> <a
				href="/member/checkMemberInfo" class="aTag" id="modifyPwdATag">비밀번호가 기억나지 않으신가요?</a>
		</div>

		<div class="explanation" id="registerEx">
			<p>회원가입 페이지로 넘어가기</p>
		</div>
		<div class="explanation" id="modifyEx">
			<p>비밀번호 변경 페이지로 넘어가기</p>
		</div>
	</div>
	<script type="text/javascript" src="../resources/js/login.js"></script>
</body>
</html>