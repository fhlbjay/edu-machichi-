<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="/static/js/changeClass.js"></script>
    <style>
        #tab1 td input {
            width: 160px

        }
    </style>
</head>
<body>
<div id="changeClass_tb">
    <div>
        <%-- <shiro:hasPermission name="changeClass:saveOrUpdate">--%>
        <a id="edit_button" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-user',plain:true" data-cmd="view">查看</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-fillup',plain:true" data-cmd="audit">审核</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
        <%--</shiro:hasPermission>--%>
        <%--高级查询--%>
        班级:<input id="classgradeId" type="text" name="classgradeId" class="easyui-textbox easyui-combobox"
                  data-options="valueField:'id',textField:'name',url:'/classGrade/selectAll.do',panelHeight:'auto'"/>
        转学时间:<input id="beginChangeDate" type="text" class="easyui-textbox easyui-datebox"/>~
        <input id="endChangeDate" type="text" class="easyui-textbox easyui-datebox"/>
        <input id="keyword" type="text" name="keyword" class="easyui-textbox" prompt="关键字"/>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-download',plain:true" data-cmd="exportXls">导出</a>

    </div>
</div>

<table id="changeClass_datagrid"></table>
<!--弹出框保存/取消-->
<div id="changeClass_tbsc">
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
    <%-- <form action="/changeClass/input.do" method="post" enctype="multipart/form-data">
         文件:<input type="file" name="file"/>
         <input type="submit" value="上传"/>
     </form>--%>
    <form id="input_form" method="post" enctype="multipart/form-data">
        <%--文件:<input type="file" name="file"/>--%>
        上传文件: <input class="easyui-filebox" name="file" style="width:180px" data-options="buttonText:'选择文件'">
    </form>
</div>
<div id="changeClass_edit_dialog">
    <form id="changeClass_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 10px" cellspacing="5px" cellpadding="5px">
            <tr>
                <td>姓名:</td>
                <td><input type="text" name="officialStudent.potentitalStudent.name"
                           disabled class="easyui-validatebox easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input type="text" disabled
                           name="officialStudent.potentitalStudent.tel" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>QQ:</td>
                <td><input type="text" disabled
                           name="officialStudent.potentitalStudent.qq" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>之前班级</td>
                <td><input id="beforeClass" type="text" class="easyui-textbox easyui-combobox"
                           name="officialStudent.potentitalStudent.classGrade.id"
                           disabled  data-options="valueField:'id',textField:'name',
                                   url:'/classGrade/selectAll.do',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td>转入班级</td>
                <td><input id="afterClass" type="text" class="easyui-combobox "
                           name="afterClass.id"
                           data-options="valueField:'id',textField:'name',
                                   url:'/classGrade/selectAll.do',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td>转班时间:</td>
                <td><input id="changeDate" type="text" name="changeDate" class="easyui-textbox easyui-datebox noedit"/></td>
            </tr>
            <tr>
                <td>营销人员:</td>
                <td><input type="text" disabled name="officialStudent.potentitalStudent.employee.realname"
                           class="easyui-textbox"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
