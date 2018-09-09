<%@ page contentType="text/html;charset=utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="type" value="car"/>
<%@ include file="components/info-front.jsp" %>
    <td width="50%"><br>
        <p>标题:&emsp;&emsp;&emsp;<input type="text" name="name" value="${requestScope.result.name}" class="enable"></p>
        <p>货车数量:&emsp;<input type="text" name="count" value="${requestScope.result.count}" class="enable" pattern="[0-9]+">&nbsp;辆</p>
        <p>货车载重:&emsp;<input type="text" name="capacity" value="${requestScope.result.capacity}" class="enable" pattern="[0-9]+">&nbsp;吨</p>
        <p>发车地点:&emsp;<input type="text" name="start" value="${requestScope.result.start}" class="enable"></p>
    </td>
<%@ include file="components/info-after.jsp" %>