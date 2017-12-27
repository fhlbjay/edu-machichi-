<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="/static/js/schedule.js"></script>
    <script>setInterval("timenow.innerHTML=new Date().toLocaleString()",1000);
    </script>
</head>
<body>
<%--使用layout布局--%>
<div id="cc" class="easyui-layout" fit="true">
    <div id="west" data-options="region:'west',split:true,title:''" style="width: 260px">
        <div id="cc1" class="easyui-layout" fit="true">
            <div id="north" data-options="region:'north',split:true,title:'日期',collapsible:false,minHeight:295,maxWidth:450">
                <div id="calendar" class="easyui-calendar" fit="true" style="width:222px;height:220px"></div>
            </div>
            <div id="south" data-options="region:'south',split:true,title:'今日课程信息',collapsible:false,minHeight:260,maxWidth:450">
               <div align="center" style="height: 35px;font-size: 16px;margin-top: auto;background-color: #afd9ee"><font id="timenow"></font></div>
                <table id="cg_datagrid"></table>
            </div>
        </div>
    </div>
    <div data-options="region:'center'" >
        <div id="schedule_tb">
            <div>
                <%--高级查询--%>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
                教室:<input id="classRoom" type="text" name="classRoomId" class="easyui-textbox easyui-combobox"
                          data-options="valueField:'id',textField:'name',url:'/classRoom/selectAll.do',panelHeight:'auto',width:100"/>
                班级:<input id="classGrade" type="text" name="classGradeId" class="easyui-textbox easyui-combobox"
                          data-options="valueField:'id',textField:'name',url:'/classGrade/selectAll.do',panelHeight:'auto'"/>
                老师:<input id="teacher" class="easyui-combobox" name="teacherId"
                          data-options="valueField:'id',textField:'username',url:'/employee/getTeacherByRoleId.do?sn=TEACHER',panelHeight:'auto',width:100"/>
                时间:<input id="beginDate" type="text" name="beginDate" class="easyui-textbox easyui-datebox"/>~
                <input id="endDate" type="text" name="endDate" class="easyui-textbox easyui-datebox"/>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">搜索</a>
           <%-- </div>
            <div align="left">--%>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-download',plain:true" data-cmd="exportXls">下载课表</a>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-upload',plain:true" data-cmd="inputXls">上传课表</a>
            </div>

        </div>
        <table id="schedule_datagrid"></table>
        <%--文件上传弹出框按钮--%>
        <div id="input_tbsc">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-upload',plain:true" data-cmd="undo">上传</a>
        </div>
        <%--文件上传--%>
        <%--弹出窗口--%>
        <div id="fileInput">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-download',plain:true" data-cmd="downModel">上传课表模板下载</a>
            <%--文件上传内容--%>
            <form id="input_form" method="post" enctype="multipart/form-data">
                上传文件: <input class="easyui-filebox" name="file" style="width:180px" data-options="buttonText:'选择文件'">
            </form>
        </div>
    </div>
</div>


</body>
</html>
