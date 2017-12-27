<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp"%>
    <script src="/static/js/department.js"></script>
</head>
<body>
	<div id="dept_tb">
	    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
	    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
	    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-cmd="remove">删除</a>
	    <a id="changeState" class="easyui-linkbutton" data-options="iconCls:'icon-fillup',plain:true" data-cmd="changeState">存在</a>
	    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
	</div>
	
	<table id="dept_datagrid"></table>
	<!--弹出框保存/取消-->
	<div id="dept_tbsc">
	    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
	    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
	</div>
	<!--表单弹出框-->
	<div id="dept_dialog">
	    <!--弹出框的内容-->
	    <form id="dept_form" method="post">
	        <%--编辑隐藏id--%>
	        <input type="hidden" name="id"/>
	        <table align="center" style="margin-top: 10px">
	            <tbody>
	            <tr>
	                <td>编号:</td>
	                <td><input type="text" name="sn" class="easyui-textbox" /></td>
	            </tr>
	            <tr>
	                <td>部门名称:</td>
	                <td><input type="text" name="name" class="easyui-textbox"/></td>
	            </tr>
	            </tbody>
	        </table>
	    </form>
	</div>
</body>
</html>
