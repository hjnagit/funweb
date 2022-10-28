<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/subpage.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<jsp:include page="../inc/top.jsp" />
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 메인이미지 -->
<div id="sub_img_center"></div>
<!-- 메인이미지 -->

<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
<ul>
<li><a href="#">Notice</a></li>
<li><a href="#">Public News</a></li>
<li><a href="#">Driver Download</a></li>
<li><a href="#">Service Policy</a></li>
</ul>
</nav>
<!-- 왼쪽메뉴 -->

<!-- 게시판 -->
<article>
<h1>Notice Search</h1>
<table id="notice">
<tr><th class="tno">No.</th>
    <th class="ttitle">Title</th>
    <th class="twrite">Writer</th>
    <th class="tdate">Date</th>
    <th class="tread">Read</th></tr>
    <c:forEach var="dto" items="${boardList }">
    <tr><td>${dto.bno }</td>
    <td class="left"><a href="${pageContext.request.contextPath}/board/content?bno=${dto.bno }">${dto.subject }</a></td>
    <td>${dto.name }</td>
    <td><fmt:formatDate value="${dto.date }" pattern="yyyy.MM.dd"/> </td><td>${dto.readcount }</td></tr>
    </c:forEach>

</table>

<div id="table_search">
<c:if test="${!(empty sessionScope.loginID) }">
<input type="button" value="글쓰기" class="btn" onclick="location.href='${pageContext.request.contextPath}/board/write'">
</c:if>
</div>

<div id="table_search">
<form action="${pageContext.request.contextPath}/board/listSearch" name="fr" method="get">
<input type="text" name="search" class="input_box">
<input type="submit" value="search" class="btn">
</form>
</div>

<div class="clear"></div>
<div id="page_control">

<c:if test="${vo.startPage > vo.pageBlock }">
	<a href="${pageContext.request.contextPath}/board/listSearch?pageNum=${vo.startPage - vo.pageBlock}&search=${vo.search}">Prev</a>
</c:if>

<c:forEach var="i" begin="${vo.startPage }" end="${vo.endPage }" step="1">
	<a href="${pageContext.request.contextPath}/board/listSearch?pageNum=${i }&search=${vo.search}">${i }</a>
</c:forEach>

<c:if test="${vo.endPage < vo.pageCount }">
	<a href="${pageContext.request.contextPath}/board/listSearch?pageNum=${vo.startPage + vo.pageBlock}&search=${vo.search}">Next</a>
</c:if>

</div>
</article>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp" />
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>