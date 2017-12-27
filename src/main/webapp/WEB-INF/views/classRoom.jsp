<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="/static/js/classRoom.js"></script>
</head>
<body>
<div id="classRoom_tb">
    <div>
        <%-- <shiro:hasPermission name="classRoom:saveOrUpdate">--%>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
        <%--</shiro:hasPermission>--%>
        <a id="changeClassRoomState" class="easyui-linkbutton" data-options="iconCls:'icon-begin',plain:true" data-cmd="changeClassRoomState">设置启用</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-classroom',plain:true" data-cmd="searchClassGrade">查看教室所在班级信息</a>
        <%--高级查询--%>
        &ensp;&ensp;教室查询:<input id="keyword" type="text" name="keyword" class="easyui-textbox"/>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">搜索</a>
    </div>
</div>
<%--教室对应班级弹出窗口--%>
<div id="searchClassGrade_dialog">
    <%--教室中班级的数据--%>
    <table id="searchClassGrade_datagrid"></table>
</div>

<%--数据表格弹出窗口--%>
<table id="classRoom_datagrid"></table>
<!--弹出框保存/取消-->
<div id="classRoom_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<!--表单弹出框-->
<div id="classRoom_dialog">
    <!--弹出框的内容-->
    <form id="classRoom_form" method="post">
        <%--编辑隐藏id--%>
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 10px">
            <tbody>
            <tr>
                <td>教室名称:</td>
                <td>&nbsp;&nbsp;</td>
                <td><input type="text" name="name" class="easyui-textbox" /></td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td>教室地址:</td>
                <td>&nbsp;&nbsp;</td>
                <td><input type="text" name="address" class="easyui-textbox" /></td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td>教室座位数:</td>
                <td>&nbsp;&nbsp;</td>
                <td><input type="text" name="seats" class="easyui-textbox easyui-numberbox"/></td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td>教室标语:</td>
                <td>&nbsp;&nbsp;</td>
                <td><input class="easyui-textbox"  name="remark" data-options="multiline:true" ></td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;</td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
