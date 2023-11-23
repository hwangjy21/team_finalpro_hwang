<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Detail Page</title>
<link href="../resources/css/sb-admin-2.css" rel="stylesheet">

<style type="text/css">
.boardDetailtop {
	display: flex;
	justify-content: space-between;
}

.left1>img {
	width: 40px;
	height: 40px;
	border-radius: 50%;
}

img {
	overflow-clip-margin: content-box;
	overflow: clip;
}

.text-primary, .bm {
	margin-bottom: 10px;
}

.left1 {
	display: flex;
	align-items: center;
	margin-right: 10px;
}

.left2 {
	display: flex;
	align-items: flex-end;
}

.mag {
	margin: 0px;
	padding: 0px;
}

.contentBox {
	padding: 40px;
}

.leftBox, .rightBox {
	display: flex;
	align-items: flex-end;
}

.bubbleIcon, .cmtCnt, .heartIcon {
	display: flex;
	align-items: flex-end;
}

.bubbleIcon {
	margin-left: 10px;
}

.miniFont {
	font-size: 12px;
}

.jwh {
	padding: 15px;
}

.cmtPost {
	height: 100px;
}

.test222 {
	border: none;
	outline: none;
}

.postCntBox {
	display: flex;
	justify-content: space-between;
}
.inputBox{
width: 88%;
margin-right: 10px;
}

</style>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script>
</head>
<body>

	<div class="box">
		<div class="box2">

			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h3 class="m-0 font-weight-bold text-primary">${bvo.title}</h3>



					<div class="boardDetailtop">
						<div class="leftBox">
							<div class="left1">
								<img alt="프로필 사진 없음" src="../../../resources/img/profile.jpg">
							</div>
							<div class="left2">
								<div>
									<h5 class="mag">${bvo.id}(${bvo.empNo})</h5>
									<p class="miniFont mag">${bvo.regDate}</p>
								</div>
							</div>
						</div>
						<div class="rightBox">
							<div class="heartIcon">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
									fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
 									<path
										d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z" />
								</svg>
								<div class="likeCnt miniFont">좋아요</div>
							</div>
							<div class="bubbleIcon">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
									fill="currentColor" class="bi bi-chat-dots" viewBox="0 0 16 16">
  									<path
										d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" />
  									<path
										d="m2.165 15.803.02-.004c1.83-.363 2.948-.842 3.468-1.105A9.06 9.06 0 0 0 8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6a10.437 10.437 0 0 1-.524 2.318l-.003.011a10.722 10.722 0 0 1-.244.637c-.079.186.074.394.273.362a21.673 21.673 0 0 0 .693-.125zm.8-3.108a1 1 0 0 0-.287-.801C1.618 10.83 1 9.468 1 8c0-3.192 3.004-6 7-6s7 2.808 7 6c0 3.193-3.004 6-7 6a8.06 8.06 0 0 1-2.088-.272 1 1 0 0 0-.711.074c-.387.196-1.24.57-2.634.893a10.97 10.97 0 0 0 .398-2z" />
									</svg>
								<div class="cmtCnt miniFont">댓글수</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-body jwh">
					<div class="contentBox">내용 ${bvo.content}</div>
					<hr>

					<sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal.mvo.id" var="authId" />
						<sec:authentication property="principal.mvo.empNo" var="authEmpNo" />
							 ${authId}(${authEmpNo})님
						<div class="postCntBox">
							<div class="inputBox">
								<input type="text" class="form-control" id="cmtText">
							</div>
							<div>
								<button type="button" class="btn btn-outline-primary mag0" id="cmtPostBtn">등록</button>
							</div>

						</div>
					<hr>
					
						<!-- 댓글 표시 라인 -->
						<div id="cmtListArea">
						
						
						</div>
						
						<!-- 댓글 더보기 버튼 -->
						<div>
							<div>
								<!-- style="visibility: hidden" <= 숨김 -->
								<button type="button" id="moreBtn" data-page="1" class="btn btn-outline-dark" style="visibility: hidden">MORE+</button>
							</div>
						</div>
					
					</sec:authorize>

				</div>

			</div>

		</div>
	</div>	
	<script type="text/javascript">
		let bnoVal = `<c:out value="${bvo.bno}"/>`;
		console.log("bnoVal>>> " + bnoVal);
	</script>
	<script type="text/javascript" src="/resources/js/boardComment.js"></script>
	<script type="text/javascript">
		printCommentList(bnoVal);
	</script>
</body>
</html>