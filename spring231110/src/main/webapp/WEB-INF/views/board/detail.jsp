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
.cmtRow1{
display: flex;
justify-content: space-between;
}
.cmtRow2{
display: flex;
justify-content: space-between;
}
.cmtRow3{
display: flex;
justify-content: flex-end;

}
.trigger{
border: none;
background-color: white;
}
.modPostBtn{
width:50px; 
height:25px; 
font-size:10px;
padding:0px;
}
.modinput{
background-color: white; 
border:none; 
width:90%; 
margin-right:5px;
}
.colorRed{
color:red;
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
<button style="display: none;"></button>
	<jsp:include page="../common/l_side.jsp" />

	<jsp:include page="../common/nav.jsp" />
	<div class="total">
		<div class="subject">글 상세</div>
		<div class="content">
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
							<div class="heartIcon likeCnt miniFont">
								<div id="iconContainer">
								<c:choose>
								<c:when test="${bvo.likeCheck}">
									<svg id="heart" class="colorRed" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
           							 <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
          							</svg>
								</c:when>
								<c:otherwise>
									<svg id="heart" xmlns="http://www.w3.org/2000/svg" width="16" height="16"
									fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
 									<path
										d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z" />
									</svg>
								</c:otherwise>
							</c:choose>
								</div>
								좋아요
								<div id="likeQtyArea">
								${bvo.likeQty}
								</div>
							</div>
							<div class="bubbleIcon likeCnt miniFont">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
									fill="currentColor" class="bi bi-chat-dots" viewBox="0 0 16 16">
  									<path
										d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" />
  									<path
										d="m2.165 15.803.02-.004c1.83-.363 2.948-.842 3.468-1.105A9.06 9.06 0 0 0 8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6a10.437 10.437 0 0 1-.524 2.318l-.003.011a10.722 10.722 0 0 1-.244.637c-.079.186.074.394.273.362a21.673 21.673 0 0 0 .693-.125zm.8-3.108a1 1 0 0 0-.287-.801C1.618 10.83 1 9.468 1 8c0-3.192 3.004-6 7-6s7 2.808 7 6c0 3.193-3.004 6-7 6a8.06 8.06 0 0 1-2.088-.272 1 1 0 0 0-.711.074c-.387.196-1.24.57-2.634.893a10.97 10.97 0 0 0 .398-2z" />
									</svg>
									댓글
									<div id="cmtQtyArea">
									${bvo.cmtQty}
									</div>
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
							 <input type="hidden" id="authId" value="${authId}">
							 <input type="hidden" id="authEmpNo" value="${authEmpNo}">
						<div class="postCntBox">
							<div class="inputBox">
								<input type="text" class="form-control" id="cmtText">
							</div>
							<div>
								<button type="button" class="btn btn-outline-primary mag0" id="cmtPostBtn">등록</button>
							</div>
						</div>
					<hr>
						<%-- <!-- 댓글 정렬 기준 -->
					 <div class="input-group mb-3">
						<select name="order" class="form-select" id="inputGroupSelect03"
							aria-label="Example select with button addon">
						<option value="latest" selected>최신순</option>
						<option value="old">등록순</option>
						<option value="like">좋아요순</option>
						</select>
					</div>
					
				<c:set value="${ph.pgvo.type}" var="typed"></c:set>
				<div>
				<select name="type" class="form-select" id="inputGroupSelect01">
					<option ${typed eq null?'selected':''}>Choose...</option>
					<option value="t" ${typed eq 't'?'selected':''}>title</option>
					<option value="w" ${typed eq 'w'?'selected':''}>writer</option>
					<option value="c" ${typed eq 'c'?'selected':''}>content</option>
					<option value="tw" ${typed eq 'tw'?'selected':''}>title or
						writer</option>
					<option value="tc" ${typed eq 'tc'?'selected':''}>title or
						content</option>
					<option value="cw" ${typed eq 'cw'?'selected':''}>content
						or writer</option>
					<option value="twc" ${typed eq 'twc'?'selected':''}>all</option>
				</select>
				</div> --%>
					
					
						<!-- 댓글 표시 라인 -->
						<div id="cmtListArea">	
						 	<ul>
								<li>					
								<div class="cmtRow1">
									<div class="left1">
										<img alt="프로필 사진 없음" src="../../../resources/img/profile.jpg">
										댓글작성자id(사번),댓글작성일
									</div>													
									<div>
										동그라미3개 아이콘								
									</div>								
								</div>
										
								<div class="cmtRow2">
									<div>
									댓글 내용
									</div>							
								</div>
								<div class="cmtRow3">
									<div>
									좋아요 아이콘
									</div>							
								</div> 
								</li>
							</ul> 																		
						</div>
						
						<!-- 댓글 더보기 버튼 -->
						<div>
							<div class="d-grid gap-2">
								<!-- style="visibility: hidden" <= 숨김 -->
								<button type="button" id="moreBtn" data-page="1" class="btn btn-outline-primary mag0" style="visibility: hidden">MORE+</button>
							</div>
						</div>
					
					</sec:authorize>

				</div>

			</div>

		</div>
	</div>
		</div>
	</div>
	

	

<jsp:include page="../common/footer.jsp" />
		
	<script type="text/javascript">
		let bnoVal = `<c:out value="${bvo.bno}"/>`;
		let boardWriter = `<c:out value="${bvo.id}"/>`;
		console.log("bnoVal>>> " + bnoVal);
	</script>
	<script type="text/javascript" src="/resources/js/boardComment.js"></script>
	<script type="text/javascript">
		printCommentList(bnoVal);
	</script>
</body>
</html>