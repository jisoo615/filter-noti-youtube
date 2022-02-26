<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
table { width:100%;
}
<</style>
<title></title>
</head>
<body>
리스트에서 원하는 글을 보는 화면 (form없는 글 보는 페이지)
<table>
<tr><td>제목</td><td>작성일</td><td>작성자</td></tr>
<tr>
<td>${ feed.title }</td><td>${ feed.createDate }</td><td>${ feed.username }</td>
</tr>
<tr><td>${ feed.content }</td></tr>
</table>
<a href="edit?id=${ feed.id }">수정</a><span>  </span>
<a href="delete?id=${ feed.id }&username=${feed.username}">삭제</a>
</body>
</html>