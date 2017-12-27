<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="/static/js/studentTrack.js"></script>
</head>
<body>
<div id="ps_tb">
    <div>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-filter',plain:true" data-cmd="query">高级查询</a>

        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
    </div>
</div>

<table id="ps_datagrid"></table>
<!--弹出框保存/取消-->
<div id="ps_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="put">提交</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>




<%--高级查询弹窗--%>
<div id="query_dialog">
    <form id="query_form" method="post">
        <table align="center" style="margin-top: 10px;font-size: 12px" cellspacing="10px" width="250px"
               cellpadding="5px">
            <tr align="left" style="height: 10px">
                <td>姓名:<input class="easyui-textbox" name="name" style="width: 120px;height: 22px;"/></td>
            </tr>
            <tr align="left" style="height: 10px">
                <td>原拥有者:<input class="easyui-combobox" name="prevId" style="width: 120px;height: 22px;"
                           data-options="url:'/employee/selectAll.do',textField:'username',valueField:'id',panelHeight:'auto'"/>
            </tr>
            <tr align="left" style="height: 10px">
                <td>移交目标:<input class="easyui-combobox" name="nowId" style="width: 120px;height: 22px;"
                           data-options="url:'/employee/selectAll.do',textField:'username',valueField:'id',panelHeight:'auto'"/>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
