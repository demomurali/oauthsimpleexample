<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Spring Security - OAuth 2.0 Login</title>
	<meta charset="utf-8" />
</head>
<body>
<div style="float: right" th:fragment="logout" sec:authorize="isAuthenticated()">
	<div style="float:left">
		<span style="font-weight:bold">User: </span><span sec:authentication="name"></span>
	</div>
	<div style="float:none">&nbsp;</div>
	<div style="float:right">
		<form action="#" th:action="@{/logout}" method="post">
			<input type="submit" value="Logout" />
		</form>
	</div>
</div>
<h1>OAuth 2.0 Login with Spring Security</h1>
<div>
	You are successfully logged in <span style="font-weight:bold" th:text="${userName}"></span>
	via the OAuth 2.0 Client <span style="font-weight:bold" th:text="${clientName}"></span>
</div>
<div>&nbsp;</div>
<div>
	<a href="/userinfo" th:href="@{/userinfo}">Display User Info</a>
</div>
</body>
</html>
