<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp"%>
    <script src="/static/js/checkRecord.js"></script>
</head>
<body>
<div id="checkRecord_tb">
    <div>
        <%--高级查询(所属学校未实现)--%>
	        员工名称:	<input id="keyword" type="text" name="keyword" class="easyui-textbox" prompt="员工名称"/>
	        起止日期:	<input id="beginDate" type="text" name="beginDate" class="easyui-textbox easyui-datebox"/>~
	        	<input id="endDate" type="text" name="endDate" class="easyui-textbox easyui-datebox"/>
	        所属部门:	<input id="deptId" type="text" name="deptId" class="easyui-textbox easyui-combobox"
                  data-options="valueField:'id',textField:'name',url:'/department/selectAll.do',panelHeight:'auto'"/>
	        	<a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">搜索</a>
    </div>
    
    <a class="easyui-linkbutton" data-options="iconCls:'icon-sun',plain:true" data-cmd="checkIn">签到</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-moon',plain:true" data-cmd="checkOut">下班</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-fillup',plain:true" data-cmd="checkFillUp">补签</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
</div>

<table id="checkRecord_datagrid"></table>
</body>
</html>
