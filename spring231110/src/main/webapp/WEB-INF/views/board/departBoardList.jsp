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
<title>DepartMent Board List Page</title>
</head>
<body>
<jsp:include page="../common/l_side.jsp" />

	<jsp:include page="../common/nav.jsp" />
	<div class="total">
		<div class="subject">부서 게시판</div>
		<div class="content">
		 <!-- DataTales Example -->
	   <div class	="box">
	     <div class="box2">
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">${depNm} 게시판</h6>
                        </div>
                        <div class="card-body">
                      
                            <div class="table-responsive">
                            <form action="/board/departBoardList">
                            	<input type="hidden" name="pageNo" value="1">
                            	<c:set value="${ph.pgvo.type}" var="typed" />
								<c:set value="${ph.pgvo.qty}" var="qtyed" />
                            	<div class="row">
                            	
                            		<div class="col-sm-12 col-md-6">
                            			<div class="dataTables_length fle dddddd" id="dataTable_length">
                            					Show 
                            				<label class="labelmargin">
                            					<select name="qty" class="custom-select custom-select-sm form-control form-control-sm">
													<option value="5" ${(qtyed == 5)?'selected':''}>5</option>
													<option value="10" ${(qtyed == 10)?'selected':''}>10</option>
													<option value="15" ${(qtyed == 15)?'selected':''}>15</option>
													<option value="20" ${(qtyed == 20)?'selected':''}>20</option>
												</select>
                            				</label>
                            					entries
                            			</div>
                            		</div>
                            		<div class="col-sm-12 col-md-6">
                            			<div id="dataTable_filter" class="dataTables_filter fle">
                            			<select name="type" class="custom-select custom-select-sm form-control form-control-sm dddd">
											<option ${typed eq null? 'selected':''}>choose</option>
											<option value='t' ${typed eq 't'? 'selected':''}>title</option>
											<option value='w' ${typed eq 'w'? 'selected':''}>writer</option>
											<option value='c' ${typed eq 'c'? 'selected':''}>content</option>
											<option value='twc' ${typed eq 'twc'? 'selected':''}>all</option>
										</select>                   			
                            			<label>
                            			<input type="search" name="keyword" value="${ph.pgvo.keyword}" class="form-control form-control-sm" placeholder="Search">
                            			</label>
                            			<button type="submit"class="btn btn-primary position-relative searchBtn mag0 smallBtn" type="button">
                            				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
  												<path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
											</svg>
											 <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
   												${ph.totalCount}
  											</span>
										</button>
                            			</div>
                            		</div>
                            	</div>
                            </form>
                            
                            
                            
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>제목</th>
                                            <th>작성자</th>
                                            <th>작성일</th>
                                            <th>댓글수</th> 
                                 		    <th>조회수</th>
                                 		    <th>좋아요</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${departBoardList}" var="bvo">
                                        <tr>
                                          <td>${bvo.bno}</td> 
                                            <td><a href="/board/boardDetail?bno=${bvo.bno}">${bvo.title}</a></td>
                                            <td>${bvo.id}(${bvo.empNo})</td>
                                            <td>${bvo.modDate}</td>   
                                            <td>${bvo.cmtQty}</td>                                         
                                            <td>${bvo.readQty}</td>  
                                            <td>${bvo.likeQty}</td>                                         
                                      </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                
                                
				<!-- 페이지네이션 라인 -->
				<nav aria-label="Page navigation example">
					<div id="navBox">
						<ul class="pagination">
							<li class="page-item ${(ph.prev ne true)? 'disabled':''}">
								<a class="page-link" href="/board/departBoardList?pageNo=${ph.startPage-1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous"> 
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
							<c:forEach begin="${ph.startPage}" end="${ph.endPage}" var="i">
								<li class="page-item">
									<a class="page-link" href="/board/departBoardList?pageNo=${i}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a>
								</li>
							</c:forEach>
							<li class="page-item ${(ph.next ne true)? 'disabled':''}">
								<a class="page-link" href="/board/departBoardList?pageNo=${ph.endPage+1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next"> 
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</ul>
					</div>
				</nav>
				<!-- 페이지네이션 라인 끝-->
                                
                                
                            </div>
                        </div>
                        <div class="btnContainer">
					<a href="/member/index"><button type="button"class="btn btn-outline-primary" type="button">HOME</button></a>
				</div>
                    </div>
                    </div>
             </div>
		</div>
	</div>
	  <jsp:include page="../common/footer.jsp" />
             
             
</body>
</html>