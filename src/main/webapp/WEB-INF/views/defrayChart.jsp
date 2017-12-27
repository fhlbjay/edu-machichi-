<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="/static/plugins/echarts/echarts-all.js"></script>
    <script src="/static/js/defrayChart.js"></script>
</head>
<body>
<div id="chart_tb">

    <div>
        <%--高级查询--%>
        关键字查询:<input id="keyword" type="text" name="keyword" class="easyui-textbox" prompt="经办人/处理人姓名"/>
        时间段:<input id="beginTime" type="text" name="beginTime" class="easyui-textbox easyui-datebox"/>~
        <input id="endTime" type="text" name="endTime" class="easyui-textbox easyui-datebox"/>
        分类依据:<select id="groupType" type="text" name="groupType" class="easyui-combobox" data-options="panelHeight:'auto'">
        <option value="paymentType.name">支出方式</option>
        <option value="defrayType.name">支出类型</option>
    </select>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">搜索</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" data-cmd="exportXls">下载文件</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="openBar">柱状图</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="openPie">饼状图</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="openOjbk">折线图</a>
    </div>
</div>
<table id="chart_datagrid"></table>
<div id="studentChart_dialog">
    <div id="main" style="width:600px;height: 400px;"></div>
</div>
<!--弹出框保存/取消-->
<div id="chart_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>


</body>
</html>
