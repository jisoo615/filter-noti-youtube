<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h1>인덱스 페이지 입니다</h1>
<table>
<tr>
<td>제목</td>
<td>작성자</td>
<td>작성시각</td>
</tr>
<c:forEach var="feed" items="${ feeds }">
<tr>
<td><a href="user/feed?id=${feed.id}">${ feed.title }</a></td>
<td>${ feed.username }</td>
<td><fmt:formatDate value="${feed.createDate}" type="both" dateStyle="short" timeStyle="short"/></td>
</tr>
</c:forEach>
</table>
</body>
</html>