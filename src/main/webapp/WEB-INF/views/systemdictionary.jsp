<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp"%>
    <script src="/static/js/systemdictionary.js"></script>
    <%--<script src="/static/js/systemdictionaryitem.js"></script>--%>
</head>
<body>

<div id="sydtitem_tb">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="additem">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edititem">编辑</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-cmd="removeitem">删除</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloadsitem">刷新</a>
</div>

<%--<table id="sydt_datagrid"></table>
<table id="sydtitem_datagrid"></table>--%>
<div id="cc" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west',title:'字典目录',split:true" style="width:600px;">
        <table id="sydt_datagrid" class="easyui-datagrid"></table>
    </div>

    <div data-options="region:'center',title:'字典目录明细'" style="padding:5px;background:#eee;">
        <table id="sydtitem_datagrid" class="easyui-datagrid"></table>
    </div>
</div>
<!--弹出框保存/取消-->

<div id="sydtitem_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="saveitem">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancelitem">取消</a>
</div>

<!--表单明细弹出框-->
<div id="sydtitem_dialog">
    <!--弹出框的内容-->
    <form id="sydtitem_form" method="post">
        <%--编辑隐藏id--%>
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 10px">
            <tbody>
            <tr>
                <td>字典明细名称:</td>
                <td><input type="text" name="name" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>字典明细简介:</td>
                <td><input type="text" name="intro" class="easyui-textbox"/></td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
