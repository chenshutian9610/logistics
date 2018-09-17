<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>信息</title>
    <script src="/resources/jquery3.3.1.js"></script>
    <script>
        var i = 0;

        function readable(enable) {
            if (enable == 'readonly')
                $('.enable').attr('readonly', true);
        }

        function release(object) {
            $('.enable').attr('readonly', false);
            $('.a').replaceWith("<input type='submit' value='提交'>");
        }
    </script>
</head>
<body onload="readable('${readonly}')">
<%@ include file="toolbar.jsp" %>
<c:set var="rows" value="10"/><br><br><br>
<form action="/${type}/publish?id=${param.id}" method="post">
    <table align="center" width="60%">
        <tr>