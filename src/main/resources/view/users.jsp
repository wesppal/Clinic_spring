<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>

All users

<c:forEach items="${users}" var = "user">
<tr>
<br>
<td> ${user.id} </td>
<br>
<td> ${user.login} </td>
<br>
<td> ${user.email} </td>
<br>
</c:forEach>


</body>
</html>