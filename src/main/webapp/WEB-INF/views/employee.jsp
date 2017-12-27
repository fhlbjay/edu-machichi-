<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="/static/js/employee.js"></script>
</head>
<body>
<div id="emp_tb">
    <div>
       <%-- <shiro:hasPermission name="employee:saveOrUpdate">--%>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
        <%--</shiro:hasPermission>--%>
        <a id="changeState" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-cmd="changeState">设置离职</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-download',plain:true" data-cmd="exportXls">下载文件</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-upload',plain:true" data-cmd="inputXls">上传文件</a>
    </div>
    <div>
        <%--高级查询--%>
        模糊查询:<input id="keyword" type="text" name="keyword" class="easyui-textbox"/>
        入职时间段:<input id="beginDate" type="text" name="beginDate" class="easyui-textbox easyui-datebox"/>~
        <input id="endDate" type="text" name="endDate" class="easyui-textbox easyui-datebox"/>
        部门:<input id="deptId" type="text" name="deptId" class="easyui-textbox easyui-combobox"
                  data-options="valueField:'id',textField:'name',url:'/department/selectAll.do',panelHeight:'auto'"/>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">搜索</a>
    </div>
</div>

<table id="emp_datagrid"></table>
<!--弹出框保存/取消-->
<div id="emp_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<%--文件上传弹出框按钮--%>
<div id="input_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" data-cmd="undo">上传</a>
</div>
<%--文件上传--%>
<%--弹出窗口--%>
<div id="fileInput">
    <%--文件上传内容--%>
    <%-- <form action="/employee/input.do" method="post" enctype="multipart/form-data">
         文件:<input type="file" name="file"/>
         <input type="submit" value="上传"/>
     </form>--%>
    <form id="input_form" method="post" enctype="multipart/form-data">
        <%--文件:<input type="file" name="file"/>--%>
        上传文件: <input class="easyui-filebox" name="file" style="width:180px" data-options="buttonText:'选择文件'">
    </form>
</div>
<!--表单弹出框-->
<div id="emp_dialog">
    <!--弹出框的内容-->
    <form id="emp_form" method="post">
        <%--编辑隐藏id--%>
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 10px">
            <tbody>
            <tr>
                <td>用户姓名:</td>
                <td><input type="text" name="username" class="easyui-textbox" prompt="请输入姓名"/></td>
            </tr>
            <tr>
                <td>真实姓名:</td>
                <td><input type="text" name="realname" class="easyui-textbox" prompt="请输入真实姓名" required="true"/></td>
            </tr>
            <tr id="password_tr">
                <td>密码:</td>
                <td><input type="text" name="password" class="easyui-passwordbox"/></td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input type="text" name="tel" class="easyui-textbox easyui-numberbox"/></td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td><input type="text" name="email" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>部门:</td>
                <td><input type="text" name="dept.id" class="easyui-textbox easyui-combobox"
                           data-options="valueField:'id',textField:'name',url:'/department/selectAll.do',panelHeight:'auto'"/></td>
            </tr>
            <tr>
                <td>入职时间:</td>
                <td><input type="text" name="inputtime" class="easyui-textbox easyui-datebox"/></td>
            </tr>
            <tr>
                <td>超级管理员:</td>
                <td>
                    <select class="easyui-combobox" name="admin" panelHeight='auto' style="width:143px;">
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>角色:</td>
                <td><input type="text" id="roles_combobox" class="easyui-textbox easyui-combobox"
                           data-options="valueField:'id',textField:'name',url:'/role/selectAll.do',panelHeight:'auto',multiple:true"/></td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
