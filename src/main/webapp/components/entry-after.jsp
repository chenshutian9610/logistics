<%@ page contentType="text/html;charset=utf-8" %>
<%@ page isELIgnored="false" %>
</table><br><br><br>
<c:choose>
    <c:when test="${inform==true}">
        <c:if test="${sessionScope.message=='管理员模式'}">
            <a href="/${type}-info.jsp">发布</a>
        </c:if>
    </c:when>
    <c:when test="${sessionScope.message!='管理员模式'}">
        <a href="/${type}-info.jsp">发布</a>
    </c:when>
</c:choose>
<br><br><br><br>
<red>${requestScope.message}</red>
</center>
</body>
</html>