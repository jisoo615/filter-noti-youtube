<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<h1>로그인 페이지</h1>
<form action="/login" method="POST">
<input type="text" name="username" placeholder="UserName"/><br>
<input type="password" name="password" placeholder="PassWord"/><br>
<button>로그인</button>
</form>
<a href="/joinForm">아직 회원가입을 안하셨나요?</a>
</body>
</html>