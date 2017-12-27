<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="/static/plugins/echarts/echarts-all.js"></script>
    <script src="/static/js/newStudentChart.js"></script>
</head>
<body>
<div id="chart_tb">

    <div>
        <%--高级查询--%>
        关键字查询:<input id="keyword" type="text" name="keyword" class="easyui-textbox" prompt="学员姓名/居住地址/毕业学校/电话"/>
        时间段:<input id="beginTime" type="text" name="beginTime" class="easyui-textbox easyui-datebox"/>~
        <input id="endTime" type="text" name="endTime" class="easyui-textbox easyui-datebox"/>
        分类依据:<select id="groupMssage" type="text" name="groupMssage" class="easyui-combobox" data-options="panelHeight:'auto'">
        <option value="year(s.prevDate)=year(NOW()) GROUP BY  s.prevDate">今年</option>
        <option value="month(s.prevDate)=month(NOW()) GROUP BY  e.username ">本月</option>
        <option value="month(s.prevDate)=(month(NOW())-1) GROUP BY  e.username ">上月</option>
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
