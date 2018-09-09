<%@ page contentType="text/html;charset=utf-8" %>
        <td rowspan="5">
            <p>备注:</p><textarea rows="${rows}" cols="60" name="info" class="enable">${requestScope.result.info}</textarea>
        </td>
    </tr>
</table>
<center><br><br>
    <c:choose>
        <c:when test="${sessionScope.login!='true'}">
            <p>登录后才能发布信息</p>
        </c:when>
        <c:when test="${requestScope.readonly=='readonly'}">
            <p>
                <c:if test="${requestScope.inform!=true}">
                    发布单位:${requestScope.object.name}&emsp;&emsp;
                    联系电话:${requestScope.object.phone}&emsp;&emsp;
                </c:if>
                发布时间:${requestScope.result.date}
            </p>
            <c:choose>
                <c:when test="${requestScope.holder=='true'}">
                    <a class="a" onclick="release()">修改</a>&emsp;&emsp;
                    <a href="${type}Action_delete?id=${param.id}">删除</a>&emsp;&emsp;
                </c:when>
                <c:when test="${sessionScope.message=='管理员模式'}">
                    <c:if test="${requestScope.inform==true}">
                        <a class="a" onclick="release()">修改</a>&emsp;&emsp;
                    </c:if>
                    <a href="${type}Action_delete?id=${param.id}">删除</a>&emsp;&emsp;
                </c:when>
            </c:choose>
        </c:when>
        <c:otherwise>
            <input type="submit" value="发布">&emsp;&emsp;
        </c:otherwise>
    </c:choose>
    <a href="${type}Action_entry">返回</a>
</center>
</form>
</body>
</html>