<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: phantruong
  Date: 03/05/2024
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h1>Student Management</h1>
    <h2><a href="/student?action=create">Add new student</a></h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List student</h2></caption>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Day of birth</th>
            <th>Address</th>
            <th>Mark</th>
            <c:forEach var="student" items="${studentList}">
        <tr>
            <td><c:out value="${student.id}"/></td>
            <td><c:out value="${student.firstname}"/></td>
            <td><c:out value="${student.lastname}"/></td>
            <td><c:out value="${student.dob}"/></td>
            <td><c:out value="${student.address}"/></td>
            <td><c:out value="${student.mark}"/></td>
            <td>
                <a href="/student?action=edit&id=${student.id}">Edit</a>
                <a href="/student?action=delete&id=${student.id}">Delete</a>
            </td>
        </tr>
            </c:forEach>

    </table>
</div>


</body>
</html>
