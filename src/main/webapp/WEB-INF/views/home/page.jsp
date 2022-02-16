<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
table { border-collapse: collapse; width: 100%; }
table td { padding: 4px; border: 1px solid gray; }
table th { padding: 4px; border: 1px solid gray;  background-color: #eee; }

</style>
<meta charset="UTF-8">
<title>mypage</title>
</head>
<body>
<h1>마이페이지</h1>
<table>
<tr>
	<th>expires in</th>
	<th>로그인 계정 정보</th>
	<th>채녈 아이디</th>
	<th>내 글</th>
</tr>
<tr>
	<td>${user.expiresIn}</td>
	<td>${ user.title }님으로 로그인 되었습니다.</td>
	<td>${ user.channelId }</td>
	<td>글 개수</td>
</tr>
</table>

</body>
</html>