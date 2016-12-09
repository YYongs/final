<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>login.jsp</title>
</head>
<body>
	<h3>Login with UserID and Password</h3>
	<%-- <c:url var="loginUrl" value="/j_spring_security_check"></c:url> <!-- security를 통해 검증  보안 필터를 반드시 거치도록 한다 --> --%>
	<form action="login" method="post">
		<table>
			<tr>
				<td>User ID :</td>
				<td><input type="text" name="id" id="id"/></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><input type="password" name="pw" id="pw"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="submit"
					value="login" /></td>
			</tr>
		</table>
	</form>
</body>
</html>