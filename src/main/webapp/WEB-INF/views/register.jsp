<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="/static/js/register.js"></script>
</head>
<body>
<div id="register_tb">
    <div>
       <%-- <shiro:hasPermission name="registerloyee:saveOrUpdate">--%>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" data-cmd="remove">删除</a>
        <%--</shiro:hasPermission>--%>
        <a id="changeState" class="easyui-linkbutton" data-options="iconCls:'icon-fillup',plain:true" data-cmd="changeState">审核</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-download',plain:true" data-cmd="exportXls">下载文件</a>
        <%--高级查询--%>
        潜在学员:<input id="potentitalStudent" type="text" name="name" class="easyui-textbox easyui-combobox"
                  data-options="valueField:'name',textField:'name',url:'/potentitalStudent/selectAll.do',panelHeight:'auto'"/>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">搜索</a>
    </div>
</div>

<table id="register_datagrid"></table>
<!--弹出框保存/取消-->
<div id="register_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<%--文件上传弹出框按钮--%>
<div id="input_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" data-cmd="undo">上传</a>
</div>
<%--文件上传--%>
<%--弹出窗口--%>
<!--表单弹出框-->
<div id="register_dialog">
    <!--弹出框的内容-->
    <form id="register_form" method="post">
        <%--编辑隐藏id--%>
        <input type="hidden" name="id"/>
        <input type="hidden" name="employee.id">
        <table align="center" style="margin-top: 10px">
            <tbody>
            <tr>

            <tr>
                <td>潜在学员:</td>
                <td><input type="text" name="name" class="easyui-textbox" /></td>
            </tr>
            <tr>
                <td>qq:</td>
                <td><input type="text" name="qq" class="easyui-textbox" /></td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input type="text" name="tel" class="easyui-textbox easyui-numberbox"/></td>
            </tr>
            <tr>
                <td>考试时间:</td>
                <td><input type="text" name="testTime" class="easyui-textbox easyui-datetimebox"/></td>
            </tr>
            <tr>
                <td>考试结果:</td>
                <td>
                    <select class="easyui-combobox" name="testResult" panelHeight='auto' style="width:143px;">
                        <option value="1">通过</option>
                        <option value="0">未通过</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>状态:</td>
                <td>
                    <select class="easyui-combobox" name="state" panelHeight='auto' style="width:143px;">
                        <option value="1">补考</option>
                        <option value="0">未补考</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>备注:</td>
                <td><input type="text" name="remark" class="easyui-textbox "/></td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
