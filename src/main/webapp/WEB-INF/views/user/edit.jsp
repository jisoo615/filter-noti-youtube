<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기/수정</title>
</head>
<body>
<form:form method="post" action="edit" modelAttribute="feed">
<table>
<tr>
<td>글id</td><td><form:hidden path="id"></form:hidden></td>
</tr>
<tr>
<td>제목: </td><td><form:input path="title"></form:input></td>
</tr>
<tr>
<td>내용 :</td><td><form:textarea rows="30" cols="50" path="content"></form:textarea></td>
</tr>
</table>
<button type="submit">저장</button>
</form:form>
</body>
</html>