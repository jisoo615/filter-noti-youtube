<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
	table{border:1px;}
	td {width: 2em;}
</style>
<meta charset="UTF-8">
<title>mypage</title>
</head>
<body>
<h1>마이페이지</h1>
<table>
<tr>
	<td>access token</td>
	<td>refresh token</td>
	<td>expires in</td>
</tr>
<tr>
	<td>${user.accessToken}</td>
	<td>${user.refreshToken}</td>
	<td>${user.expiresIn}</td>
</tr>
</table>

</body>
</html>