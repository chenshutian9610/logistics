<%@ page contentType="text/html;charset=utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="type" value="goods"/>
<%@ include file="components/info-front.jsp" %>
    <td width="50%"><br>
        <p>货物名称:&emsp;<input type="text" name="name" value="${requestScope.result.name}" class="enable"></p>
        <p>货物类型:&emsp;<input type="text" name="type" value="${requestScope.result.type}" class="enable"></p>
        <p>起始地:&emsp;&emsp;<input type="text" name="start" value="${requestScope.result.start}" class="enable"></p>
        <p>目的地:&emsp;&emsp;<input type="text" name="end" value="${requestScope.result.end}" class="enable"></p>
        <p>重量:&emsp;&emsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="weight" value="${requestScope.result.weight}" pattern="[0-9]+" class="enable">&nbsp;吨
        </p>
    </td>
<%@ include file="components/info-after.jsp" %>