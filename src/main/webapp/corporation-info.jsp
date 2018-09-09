<%@ page contentType="text/html;charset=utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="type" value="corporation"/>
<%@ include file="components/info-front.jsp" %>
    <td width="50%"><br>
        <p>公司名称:&emsp;<input type="text" name="name" value="${requestScope.result.name}" class="enable"></p>
        <p>公司地址:&emsp;<input type="text" name="address" value="${requestScope.result.address}" class="enable"></p>
        <p>联系电话:&emsp;<input type="text" name="phone" value="${requestScope.result.phone}" class="enable"></p>
        <p>公司链接:&emsp;<input type="text" name="link" value="${requestScope.result.link}" class="enable"></p>
    </td>
<%@ include file="components/info-after.jsp" %>