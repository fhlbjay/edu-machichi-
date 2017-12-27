<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>news</title>
    <%@include file="/static/common/common.jsp"%>
    <script src="/static/js/news.js"></script>
</head>
<body>
	<div id="news_tb">
	    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
	    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
	    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-cmd="remove">删除</a>
	    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
	    &emsp;
	        模糊查询:	<input id="keyword" type="text" name="keyword" class="easyui-textbox" prompt="支持多关键词查询,以空格分隔.例如:关键词1 关键词2" style="width: 300px;"/>
	</div>
	
	<table id="news_datagrid"></table>
	<!--弹出框保存/取消-->
	<div id="news_tbsc">
	    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
	    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
	</div>
	<!--表单弹出框-->
	<div id="news_dialog">
	    <!--弹出框的内容-->
	    <form id="news_form" method="post">
	        <%--编辑隐藏id--%>
	        <input type="hidden" name="id"/>
	        <table align="center" style="margin-top: 10px">
	            <tbody>
	            <tr>
	                <td>标题:</td>
	                <td><input type="text" name="title" class="easyui-textbox"/></td>
	            </tr>
	            <tr>
	                <td>内容:</td>
	                <td><textarea rows="2" cols="14" name="content" ></textarea></td>
	            </tr>
	            </tbody>
	        </table>
	    </form>
	</div>
</body>
</html>
