<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Register Page</title>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script>
<link href="../resources/css/sb-admin-2.css" rel="stylesheet">  

<!-- 서머노트를 위해 추가해야할 부분 -->
 <script src="/resources/js/summernote/summernote-lite.js"></script>
<script src="/resources/js/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="/resources/css/summernote/summernote-lite.css">
  <!--  -->
</head>
<body>
   <div class="box">
   <div class="box2">
	   
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
                        </div>
                        <div class="card-body">
			<form action="/board/register" method="post">

				  <sec:authorize access="isAuthenticated()">
					<sec:authentication property="principal.mvo.empNo" var="authEmpNo"/> 
					<sec:authentication property="principal.mvo.id" var="authId"/> 
					<sec:authentication property="principal.mvo.depCd" var="authDepCd"/>
					<sec:authentication property="principal.mvo.clubCd" var="authClubCd"/>

				<input type="hidden" name="empNo" value="${authEmpNo}">
				<input type="hidden" name="id" value="${authId}">

				 <div class="input-group mb-3">
					<select name="boardType" class="form-select" id="inputGroupSelect03"
						aria-label="Example select with button addon">
						<option selected>게시판을 선택해주세요.</option>
						<option value="anony">익명</option>
						<option value="depart">부서</option>
						<option value="club">동호회</option>
					</select>
				</div> 
				</sec:authorize> 
				
				<div class="mb-3">
					<input type="text" class="form-control" name="title"
						placeholder="제목을 입력해 주세요.">
				</div>
				

<!-- 썸머노트 시작 -->
<div class="container">
  <textarea class="summernote" name="content"></textarea>    
</div>
<script>
$('.summernote').summernote({

	placeholder:'글 내용을 입력해 주세요.',
	height: 450,
	  lang: "ko-KR"
	/*   onImageUpload : function(files, editor,welEditable){
		  console.log(files);
		  console.log(files[0]);
		  
		  data = new FormData();
		  data.append("file",diles[0]);
		  
		  var $note = $(this);
		  $.ajax({
			data : data,
			type : "POST",
			url : "/board/imageupload",
			cache : false,
			contentType : false,
			processData : false,
			success : function(url){
				alert(url);
				$note.summernote('insertImage',url);
			}
		  });
	  } */
	});
</script>
<!-- 썸머노트 끝 -->


				<div class="btnContainer">
					<button type="submit" class="btn btn-outline-primary" id="regBtn">등록</button>
					<a href="/member/index"><button type="button"class="btn btn-outline-primary" type="button">HOME</button></a>
				</div>
			</form>
	</div></div>
	</div></div>
	
</body>
</html>