<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage</title>
</head>
<body>
<h2>마이 페이지</h2>
내 아이디 : ${ username }<br>
<a href="create">글쓰기</a>
<h3>팔로우</h3>
<table>
<c:forEach var="follow" items="${follows}">
<tr>
<td><a href="lesson?teacherId=${ follow.teacherId }">${follow.teacherId}</a></td>
</tr>
</c:forEach>
</table>
</body>
</html>