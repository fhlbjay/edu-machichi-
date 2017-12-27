<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp"%>
    <script src="/static/js/permission.js"></script>
</head>
<body>
<div id="permission_tb">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" data-cmd="remove">删除</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">加载权限</a>
</div>
<table id="permission_datagrid"></table>
</body>
</html>
