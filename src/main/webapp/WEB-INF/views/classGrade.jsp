<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="/static/js/classGrade.js"></script>
</head>
<body>
<div id="classGrade_tb">
    <div>
        <%-- <shiro:hasPermission name="classGrade:saveOrUpdate">--%>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" data-cmd="remove">删除</a>
        <%--</shiro:hasPermission>--%>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
        <a id="changeClassGradeState" class="easyui-linkbutton" data-options="iconCls:'icon-begin',plain:true" data-cmd="changeClassGradeState">设置开班</a>
        <a id="changeTeacher" class="easyui-linkbutton" data-options="iconCls:'icon-teacher2',plain:true" data-cmd="changeTeacher">分配班主任</a>
    </div>

</div>

<table id="classGrade_datagrid"></table>
<!--弹出框保存/取消-->
<div id="classGrade_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<%--分配班主任按钮--%>
<div id="changeTeacher_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" data-cmd="confirm">确定选择</a>
</div>
<%--分配班主任弹出框--%>
<div id="changeTeacher_dialog">
    <form id="changeTeacher_form" method="post">
        <tr>
            <td>选择班主任:</td>
            <td><input class="easyui-combobox" name="teacher.id"
                       data-options="valueField:'id',textField:'username',url:'/employee/getTeacherByRoleId.do?sn=BZR',panelHeight:'auto'"/>
            </td>
        </tr>
    </form>
</div>
<%--文件上传弹出框按钮--%>
<div id="input_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" data-cmd="undo">上传</a>
</div>
<%--文件上传--%>
<%--弹出窗口--%>
<div id="fileInput">
    <%--文件上传内容--%>
    <%-- <form action="/classGrade/input.do" method="post" enctype="multipart/form-data">
         文件:<input type="file" name="file"/>
         <input type="submit" value="上传"/>
     </form>--%>
    <form id="input_form" method="post" enctype="multipart/form-data">
        <%--文件:<input type="file" name="file"/>--%>
        上传文件: <input class="easyui-filebox" name="file" style="width:180px" data-options="buttonText:'选择文件'">
    </form>
</div>
<!--表单弹出框-->
<div id="classGrade_dialog">
    <!--弹出框的内容-->
    <form id="classGrade_form" method="post">
        <%--编辑隐藏id--%>
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 10px">
            <tbody>
            <tr>
                <td>班级名称</td>
                <td>&nbsp;&nbsp;</td>
                <td><input type="text" class="easyui-textbox" name="name"/></td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td>学生数量</td>
                <td>&nbsp;&nbsp;</td>
                <td><input type="text" class="easyui-textbox" name="stuNumber"/></td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td>所属学院</td>
                <td>&nbsp;&nbsp;</td>
                <td><input class="easyui-combobox"
                           data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=college',valueField:'id',textField:'name',panelHeight:'auto'"
                           name="college.id"></td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td>所在教室</td>
                <td>&nbsp;&nbsp;</td>
                <td><input class="easyui-combobox" name="classroom.id"
                           data-options="valueField:'id',textField:'name',url:'/classRoom/selectAll.do',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td>班主任</td>
                <td>&nbsp;&nbsp;</td>
                <td><input class="easyui-combobox" name="teacher.id"
                           data-options="valueField:'id',textField:'username',url:'/employee/getTeacherByRoleId.do?sn=BZR',panelHeight:'auto'"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
