<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>	    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member list</title>
<style type="text/css">
.container-fluid {
	display: flex;
	flex-direction: row;
}

.pagination{
justify-content: center;
}
</style>
</head>

<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />

	<jsp:include page="../common/l_side.jsp" />
	<div class="total">
		<div class="head">
			<h3>사원 목록</h3>
			<!-- 검색 라인 -->
			<div class="container-fluid">
				<form action="/member/list" class="d-flex" role="search"
					method="get">
					<c:set value="${ph.pgvo.type}" var="typed"></c:set>
					<div>
						<select name="type" class="form-select" id="inputGroupSelect01">
							<option ${typed eq null?'selected':''}>선택</option>
							<option value="n" ${typed eq 'n'?'selected':''}>사원명</option>
							<option value="i" ${typed eq 'i'?'selected':''}>아이디</option>
							<option value="a" ${typed eq 'a'?'selected':''}>주소</option>
							<option value="d" ${typed eq 'd'?'selected':''}>부서</option>
							<option value="c" ${typed eq 'c'?'selected':''}>동아리</option>
							<option value="niadc" ${typed eq 'en'?'selected':''}>전체</option>
						</select>
					</div>
					<div>
						<input class="form-control me-2" name="keyword"
							value="${ph.pgvo.keyword}" type="search" placeholder="Search"
							aria-label="Search">
					</div>
					<input type="hidden" name="pageNo" value="1"> <input
						type="hidden" name="qty" value="${ph.pgvo.qty}">
					<button class="btn btn-primary position-relative" type="submit">
						검색</button>
				</form>

			</div>
			<input type="hidden" name="pageNo" value="1"> <input
				type="hidden" name="qty" value="${ph.pgvo.qty}">
			<button class="btn btn-primary position-relative" type="submit">
				Search <span
					class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
					${ph.totalCount} <span class="visually-hidden">unread
						messages</span>
				</span>
			</button>
		</form>
	</div>







<div class="listBox">
		<table class="table">
			<thead>
				<tr>
					<th scope="col">email</th>
<!-- 					<th scope="col">pwd</th> -->
					<th scope="col">nickname</th>
					<th scope="col">reg_at</th>
					<th scope="col">last_login</th>
				
				</tr>
			</thead>
	<!-- MemberVO -->
<!-- 	private String email; -->
<!-- 	private String pwd; -->
<!-- 	private String nickName; -->
<!-- 	private String regAt; -->
<!-- 	private String lastLogin; -->
<!-- 	private List<AuthVO> authVOList; -->
			<tbody>

 				<c:forEach items="${list}" var="mvo"> 
 					<tr> 
 						<th><a href="/member/detail?email=${mvo.email}">${mvo.email }</a></th> 
<%--  						<td><a href="/member/detail?email=${mvo.email}">${mvo.pwd }</a></td>  --%>
<%--  						<td><a href="/member/detail?email=${mvo.email}">암호화됨</a></td> --%>
 						<td><a href="/member/detail?email=${mvo.email}">${mvo.nickName }</a></td> 
 						<td><a href="/member/detail?email=${mvo.email}">${mvo.regAt }</a></td> 
 						<td><a href="/member/detail?email=${mvo.email}">${mvo.lastLogin }</a></td>			 
 					</tr> 
				</c:forEach>
			</tbody>
		</table>

		<!-- 페이징 라인 -->
<!-- 		<div> -->
<!-- 			<nav aria-label="Page navigation example"> -->
<!-- 				<ul class="pagination"> -->
<!-- 					이전(prev) -->
<%-- 					<li class="page-item ${(ph.prev eq false)?'disabled':''}"><a --%>
<!-- 						class="page-link" -->
<%-- 						href="/member/list?pageNo=${ph.startPage -1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" --%>
<!-- 						aria-label="Previous"> <span aria-hidden="true">&laquo;</span> -->
<!-- 					</a></li> -->
<%-- 					<c:forEach begin="${ph.startPage }" end="${ph.endPage}" var="i"> --%>
<!-- 						<li class="page-item"><a class="page-link" -->
<%-- 							href="/member/list?pageNo=${i}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a></li> --%>
<%-- 					</c:forEach> --%>

<!-- 					다음(next) -->
<%-- 					<li class="page-item ${(ph.next eq false)?'disabled':''}"><a --%>
<!-- 						class="page-link" -->
<%-- 						href="/member/list?pageNo=${ph.endPage +1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" --%>
<!-- 						aria-label="Next"> <span aria-hidden="true">&raquo;</span> -->
<!-- 					</a></li> -->
<!-- 				</ul> -->
<!-- 			</nav> -->
<!-- 		</div> -->
<div>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <!-- 이전(prev) -->
            <li class="page-item ${(ph.prev eq false)?'disabled':''}"><a class="page-link"
                href="/board/list?pageNo=${ph.startPage -1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
                aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
            </a></li>
            <c:forEach begin="${ph.startPage}" end="${ph.endPage}" var="i">
                <li class="page-item ${i == ph.pgvo.pageNo ? 'active' : ''}"><a class="page-link"
                    href="/board/list?pageNo=${i}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a></li>
            </c:forEach>

            <!-- 다음(next) -->
            <li class="page-item ${(ph.next eq false)?'disabled':''}"><a class="page-link"
                href="/board/list?pageNo=${ph.endPage +1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
                aria-label="Next"> <span aria-hidden="true">&raquo;</span>
            </a></li>
        </ul>
    </nav>
</div>
	
	</div>







		<div class="listBox">
			<table class="table table-hover">
				<thead>
					<tr class="thead">
						<th></th>
						<th scope="col" class="col">사원명</th>
						<th scope="col" class="col">아이디</th>
						<th scope="col" class="col">전화번호</th>
						<th scope="col" class="col">주소</th>
						<th scope="col" class="col">부서</th>
						<th scope="col" class="col">동아리</th>
						<th class="btnTh"></th>
						<th></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${list}" var="dto">
						<tr>
							<td id="left"></td>
							<td><img alt="사진 없음" src="../resources/img/profile.jpg"
								class="profileImg">${dto.mvo.empNm }</td>
							<td>${dto.mvo.id }</td>
							<td>${dto.mvo.phone }</td>
							<td>${dto.mvo.addr }</td>
							<td>${dto.dvo.depNm }</td>
							<td>${dto.cvo.clubNm }</td>
							<td><button class="detailBtn">상세조회</button></td>
							<td id="right"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div>
			<nav aria-label="Page navigation example">
				<ul class="pagination">
					<!-- 이전(prev) -->
					<li class="page-item" ${(ph.prev eq false)?'hidden=""':''}><a
						class="page-link"
						href="/member/list?pageNo=${ph.startPage -1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
					<c:forEach begin="${ph.startPage}" end="${ph.endPage}" var="i">
						<li class="page-item ${i == ph.pgvo.pageNo ? 'active' : ''}"><a
							class="page-link"
							href="/member/list?pageNo=${i}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a></li>
					</c:forEach>

					<!-- 다음(next) -->
					<li class="page-item" ${(ph.next eq false)?'hidden=""':''}><a
						class="page-link"
						href="/member/list?pageNo=${ph.endPage +1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
			</nav>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp" />
</body>
</html>