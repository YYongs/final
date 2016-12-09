<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<c:choose>
			<c:when test="${sessionScope.uid==null }">
				<li><a href="loginform">로그인 예제</a></li>
			</c:when>
			<c:otherwise>
				<li>${sessionScope.uid }님반가워용^^<a href="logout">로그아웃 예제</a></li>
			</c:otherwise>
		</c:choose>
</body>
</html>