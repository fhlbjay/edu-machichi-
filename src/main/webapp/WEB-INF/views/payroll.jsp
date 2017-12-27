<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="/static/js/payroll.js"></script>
</head>
<body>
<div id="payroll_tb">
    <div>
        <%-- <shiro:hasPermission name="payroll:saveOrUpdate">--%>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">纠正数据</a>
        <%--</shiro:hasPermission>--%>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" data-cmd="remove">删除</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
        <%--高级查询--%>
        部门:<input id="deptId" type="text" name="deptId" class="easyui-textbox easyui-combobox"
                  data-options="valueField:'id',textField:'name',url:'/department/selectAll.do',panelHeight:'auto',width:88"/>
        <input id="keyword" type="text" name="keyword" class="easyui-textbox" prompt="员工姓名" style="width:100px"/>
        <input id="minSalary" type="text" name="minSalary" class="easyui-textbox" prompt="最低薪水" style="width:80px"/>~
        <input id="maxSalary" type="text" name="maxSalary" class="easyui-textbox" prompt="最高薪水" style="width:80px"/>
        <input id="date" type="text" name="beginDate" class="easyui-textbox easyui-datebox" prompt="日期查询"/>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">搜索</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-sum',plain:true" data-cmd="checkAccount">核算</a>
        <%--快速排序--%>
        <a href="#" class="easyui-menubutton" data-options="menu:'#mm',iconCls:'icon-select'">快速排序</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-download',plain:true" data-cmd="exportXls">导出工资表</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-upload',plain:true" data-cmd="inputXls">导入工资表</a>

    </div>
</div>


<div id="mm" style="width:150px;">
    <div data-options="iconCls:'icon-upload'">按工资升序</div>
    <div data-options="iconCls:'icon-download'">按工资降序</div>
    <%--换行曲线--%>
    <%--<div class="menu-sep"></div>--%>
</div>



<table id="payroll_datagrid"></table>
<!--管理员登陆弹出框保存/取消-->
<div id="payroll_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">登陆</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<%--员工工资基本信息修改--%>
<div id="payrollEmp_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="saveRevise">确定修改并核算</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancelRevise">取消</a>
</div>
<%--文件上传弹出框按钮--%>
<div id="input_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-upload',plain:true" data-cmd="undo">导入工资表</a>
</div>
<%--文件上传--%>
<%--弹出窗口--%>
<div id="fileInput">
    <%--文件上传内容--%>
    <%-- <form action="/payroll/input.do" method="post" enctype="multipart/form-data">
         文件:<input type="file" name="file"/>
         <input type="submit" value="上传"/>
     </form>--%>
    <form id="input_form" method="post" enctype="multipart/form-data">
        <div><a class="easyui-linkbutton" data-options="iconCls:'icon-download',plain:true" data-cmd="downModel">工资表模板下载</a></div>
        <%--文件:<input type="file" name="file"/>--%>
        <div><input class="easyui-filebox" name="file" style="width:180px" data-options="buttonText:'选择文件'"></div>
    </form>
</div>
<!--表单弹出框-->
<div id="payroll_dialog">
    <!--弹出框的内容-->
    <form id="payroll_form" method="post">
        <table align="center">
            <tbody>
            <tr>
                <span><td style="text-align: center;font-size: 15px">请输入管理员身份证认</td></span>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <span><td>账号:<input type="text" name="managerName" class="easyui-textbox" prompt="请输入账号" required="true"/></td></span>
            </tr>
            <tr>
                <span><td>密码:<input type="text" name="managerPassword" class="easyui-passwordbox"/></td></span>
            </tr>
            </tbody>
        </table>
    </form>
</div>
<div id="payrollemp_dialog">
    <!--弹出框的内容-->
    <form id="payrollemp_form" method="post">
        <input type="hidden" name="employee.id"/>
        <table align="center">
            <tbody>
            <tr>
                <td style="text-align: center;font-size: 15px">可修改的信息</td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;</td>
            </tr>
            <tr>
                <td align="right">员工姓名:</td>
                <td><input type="text" name="employee.username" class="easyui-textbox" readonly="true"/></td>
            </tr>
            <tr>
                <td align="right">工资月份:</td>
                <td><input type="text" name="month" class="easyui-textbox easyui-datebox"/></td>
            </tr>
            <tr>
                <td align="right">应工作天数:</td>
                <td><input type="text" name="workDay" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td align="right">迟到天数:</td>
                <td><input type="text" name="afterDay" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td align="right">早退天数:</td>
                <td><input type="text" name="befterDay" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td align="right">基本薪水:</td>
                <td><input type="text" name="basicSalary" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td align="right">奖金:</td>
                <td><input type="text" name="reword" class="easyui-textbox"/></td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
