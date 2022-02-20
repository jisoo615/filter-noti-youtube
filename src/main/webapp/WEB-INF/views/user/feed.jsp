<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<h2>글들</h2>
<table>
<tr><td>제목</td><td>작성일</td><td>작성자</td></tr>
<c:forEach var="feed" items="${ feeds }">
<tr>
<td>${ feed.title }</td><td>${ feed.createDate }</td><td>${ feed.writer }</td>
</tr>
<tr><td>${ feed.content }</td></tr>
</c:forEach>
</table>
</body>
</html>