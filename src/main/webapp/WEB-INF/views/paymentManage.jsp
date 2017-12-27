<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="/static/js/paymentManage.js"></script>
    <style>
        #tab1 td input {
            width: 160px

        }
    </style>
</head>
<body>
<div id="paymentManage_tb">
    <div>
        <%-- <shiro:hasPermission name="paymentManage:saveOrUpdate">--%>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
        <a id="edit_button" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"
           data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-fillup',plain:true" data-cmd="audit">审核</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
        <%--</shiro:hasPermission>--%>
        <%--高级查询--%>
        班级:<input id="classgradeId" type="text" name="classgradeId" class="easyui-textbox easyui-combobox"
                  data-options="valueField:'id',textField:'name',url:'/classGrade/selectAll.do',panelHeight:'auto'"/>
        收款时间:<input id="beginChargeDate" type="text" name="beginEnrolDate" class="easyui-textbox easyui-datebox"/>~
        <input id="endChargeDate" type="text" name="endChargeDate" class="easyui-textbox easyui-datebox"/>
        <input id="keyword" type="text" name="keyword" class="easyui-textbox" prompt="姓名/班级"/>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>
        <%--<a class="easyui-linkbutton" data-options="iconCls:'icon-download',plain:true" data-cmd="exportXls">导出</a>--%>

    </div>
</div>

<table id="paymentManage_datagrid"></table>
<!--弹出框保存/取消-->
<div id="paymentManage_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<%--弹出窗口--%>
<div id="paymentManage_edit_dialog">
    <form id="paymentManage_form" method="post">
        <table align="center" style="margin-top: 10px" cellspacing="4px" cellpadding="4px">
            <input id="officialStudentId" type="hidden" name="officialStudent.id">
            <tr>
                <td>姓名:</td>
                <td><input id="stuName" type="text" name="officialStudent.potentitalStudent.id"
                           class="easyui-combobox" data-options="valueField:'id',textField:'name',
                                   url:'/officialStudent/selectUnpaid.do',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td>班级</td>
                <td><input id="stuClass" type="text" class="easyui-textbox" readonly/>
                </td>
            </tr>
            <%--<tr>--%>
            <%--<td>收款时间:</td>--%>
            <%--<td><input id="payDate" type="text" name="payDate" class="easyui-datebox"/></td>--%>
            <%--</tr>--%>
            <tr>
                <td>收款金额</td>
                <td><input id="paymentAmount" type="text" class="easyui-validatebox easyui-textbox"
                           name="paymentAmount" data-options='required:true, validType:"less"'/>
                </td>
            </tr>
            <tr>
                <td>未缴学费</td>
                <td><input id="fee2pay" type="text" class="easyui-textbox" readonly
                           name="fee2pay"/>
                </td>
            </tr>
            <tr>
                <td>收款类型:</td>
                <td><input type="text" class="easyui-textbox easyui-combobox"
                           name="paymentType.id" required
                           data-options="valueField:'id',textField:'name',
                                   url:'/systemdictionaryitem/selectItemByParentSn.do?sn=size',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td>收款人:</td>
                <td><input type="text" class="easyui-textbox easyui-combobox" required
                           name="payee.id"
                           data-options="valueField:'id',textField:'username',
                                   url:'/employee/selectAll.do',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td>营销人员:</td>
                <td><input id="saleMan" type="text" readonly
                           class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>备注</td>
                <td><input type="text" class="easyui-textbox"
                           name="remark"/>
                </td>
            </tr>

        </table>
    </form>
</div>
</body>
</html>
