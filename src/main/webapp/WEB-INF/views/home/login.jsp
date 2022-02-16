<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
    div{
    	content-align : center;
    }
    .btn { padding: 0.4em 1em; border: 1px solid gray;           
       border-radius: 0.5em; background: linear-gradient(#fff, #ddd); 
       text-decoration: none; color: black;
       display: inline-block; }
    
    </style>
<body>
<div>
<h1>Filter Youtube</h1>
<h2>이제 원하는 채널의 컨텐츠만 알림을 받고, <br>컨텐츠별로 자유롭게 글을 작성해 보세요. <br>나만의 기록 혹은 공유하거나</h1>
<br>
<h2><a href="${googleOAuthUri}" class="btn">구글로 로그인하기</a></h2>
</div>
</body>
</html>