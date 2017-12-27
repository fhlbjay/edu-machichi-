<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="/static/plugins/echarts/echarts-all.js"></script>
    <script src="/static/js/studentChart.js"></script>
</head>
<body>
<div id="chart_tb">

    <div>
        <%--高级查询--%>
        关键字查询:<input id="keyword" type="text" name="keyword" class="easyui-textbox" prompt="学员姓名/居住地址/毕业学校/电话"/>
        成为正式学员的时间:<input id="beginTime" type="text" name="beginTime" class="easyui-textbox easyui-datebox"/>~
        <input id="endTime" type="text" name="endTime" class="easyui-textbox easyui-datebox"/>
        分类依据:<select id="groupType" type="text" name="groupType" class="easyui-combobox" data-options="panelHeight:'auto'">
        <option value="p.paymentType_id">付款方式</option>
        <option value="s.address">地址</option>
        <option value="s.major">学科</option>
    </select>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">搜索</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-download',plain:true" data-cmd="exportXls">下载文件</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="openBar">柱状图</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="openPie">饼状图</a>
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
<%--文件上传弹出框按钮--%>
<div id="input_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" data-cmd="undo">上传</a>
</div>
<%--文件上传--%>
<%--弹出窗口--%>
<div id="fileInput">
</div>
</body>
</html>
