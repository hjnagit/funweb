<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/list.jsp</title>
<link href="${pageContext.request.contextPath}/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/subpage.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#btn').click(function(){
			//alert('클릭');
			//json 여러개 데이터를 가져와서 화면에 뿌려주는 작업...대박임...
			$.ajax({
				url:"${pageContext.request.contextPath}/member/listJson",
				dataType:'json',
				success:function(rdata){
					//rdata json데이터 배열 데이터-반복
					$.each(rdata, function(index, item){
$('#notice').append("<tr><td>"+item.userid+"</td><td>"+item.username+"</td><td>"+item.useremail+"</td></tr>");
					});
				}
			});//ajax
			//이벤트 한번만 하고 끝내기
			$(this).unbind();
		});//click
	});//jquery


</script>

</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<jsp:include page="../inc/top.jsp" />
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 본문메인이미지 -->
<div id="sub_img_member"></div>
<!-- 본문메인이미지 -->
<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
<ul>
<li><a href="#">Join us</a></li>
<li><a href="#">Privacy policy</a></li>
</ul>
</nav>
<!-- 왼쪽메뉴 -->
<!-- 본문내용 -->
<article>
<h1>Member List</h1>
<input type="button" value="회원목록" id="btn">
<table id="notice">
<tr><td>userid</td><td>username</td><td>useremail</td></tr>

</table>
</article>
<!-- 본문내용 -->
<!-- 본문들어가는 곳 -->

<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp" />
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>