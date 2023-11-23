<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix = "sec" uri="http://www.springframework.org/security/tags" %> 
<link href="../resources/css/sb-admin-2.css" rel="stylesheet">  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Anonymous Board List Page</title>
</head>
<body>
	   <!-- DataTales Example -->
	   <div class	="box">
	     <div class="box2">
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">익명 게시판</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>제목</th>
                                            <th>작성자</th>
                                            <th>작성일</th>
                                          <!--   <th>조회수</th>
                                            <th>댓글수</th> -->
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${departBoardList}" var="bvo">
                                        <tr>
                                          <td>${bvo.bno}</td> 
                                            <td><a href="/member/index">${bvo.title}</a></td>
                                            <td>${bvo.id}(${bvo.empNo})</td>
                                            <td>${bvo.modDate}</td>                                          
                                      </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                         <div class="btnContainer">
					<a href="/member/index"><button type="button"class="btn btn-outline-primary" type="button">HOME</button></a>
				</div>
                    </div>
                    </div>
             </div>
</body>
</html>