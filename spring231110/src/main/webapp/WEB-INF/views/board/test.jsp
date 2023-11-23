<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Board Register Page</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<link href="../resources/css/sb-admin-2.css" rel="stylesheet">  
    <!-- TUI 에디터 CSS CDN -->
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />

</head>
<body>
    
<jsp:include page="../common/l_side.jsp" />

<jsp:include page="../common/nav.jsp" />
	
<div class="total">
		<div class="subject">게시글 등록</div>
		
		
		<div class="content">
		
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
				   
    <!-- 에디터를 적용할 요소 (컨테이너) -->
    <div id="content">

    </div>

    <!-- TUI 에디터 JS CDN -->
    <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
    <script>
        const editor = new toastui.Editor({
            el: document.querySelector('#content'), // 에디터를 적용할 요소 (컨테이너)
            height: '500px',                        // 에디터 영역의 높이 값 (OOOpx || auto)
            initialEditType: 'markdown',            // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
            initialValue: '내용을 입력해 주세요.',     // 내용의 초기 값으로, 반드시 마크다운 문자열 형태여야 함
            previewStyle: 'vertical'                // 마크다운 프리뷰 스타일 (tab || vertical)
        });
    </script>
</body>
</html>