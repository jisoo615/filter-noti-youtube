<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<h1>회원가입 페이지</h1>
<form action="/join" method="POST">
<input type="text" name="username" placeholder="UserName"/><br>
<input type="email" name="email" placeholder="email@email.com"/><br>
<input type="password" name="password" placeholder="PassWord"/><br>
<button>회원가입</button>
</form>
<a href="/loginForm">이미 회원가입을 하셨나요?</a>
</body>
</html>