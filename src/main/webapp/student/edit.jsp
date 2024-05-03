<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="student?action=student">List All Student</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Edit student
                </h2>
            </caption>
            <c:if test="${student != null}">
                <input type="hidden" name="id" value="<c:out value='${student.id}' />"/>
            </c:if>
            <tr>
                <th>First Name:</th>
                <td>
                    <input type="text" name="firstname" size="45"
                           value="<c:out value='${student.firstname}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Last Name:</th>
                <td>
                    <input type="text" name="lastname" size="45"
                           value="<c:out value='${student.lastname}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Day of Birth:</th>
                <td>
                    <input type="text" name="dob" size="45"
                           value="<c:out value='${student.dob}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Address:</th>
                <td>
                    <input type="text" name="address" size="45"
                           value="<c:out value='${student.address}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Mark:</th>
                <td>
                    <input type="text" name="mark" size="45"
                           value="<c:out value='${student.mark}' />"
                    />
                </td>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>