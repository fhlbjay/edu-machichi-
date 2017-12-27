<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="/static/js/defray.js"></script>
</head>
<body>
<div id="defray_tb">
    <div>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" id="edit" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" id="remove" data-options="iconCls:'icon-remove',plain:true"
           data-cmd="remove">删除</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
        <a class="easyui-linkbutton" id="look" data-options="iconCls:'icon-man',plain:true" data-cmd="look">查看</a>
        <a class="easyui-linkbutton" id="changeState" data-options="iconCls:'icon-fillup',plain:true"
           data-cmd="changeState">审核</a>
        <%--快速排序--%>
        <a href="#" class="easyui-menubutton" data-options="menu:'#mm',iconCls:'icon-select'">快速排序</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-download',plain:true" data-cmd="exportXls">导出支出表</a>
    </div>
    <div>
        <%--高级查询--%>
        模糊查询:<input id="keyword" type="text" name="keyword" prompt="支出摘要或者单据号" class="easyui-textbox"/>
        支出时间段:<input id="beginDate" type="text" name="beginDate" style="width: 145px" class="easyui-textbox easyui-datetimebox"/>~
        <input id="endDate" type="text" name="endDate"style="width: 145px" class="easyui-textbox easyui-datetimebox"/>
        班级:<input id="classgradeId" prompt="按班级搜索支出" type="text" name="classgradeId"
                  class="easyui-textbox easyui-combobox"
                  data-options="valueField:'id',textField:'name',url:'/classGrade/selectAll.do',panelHeight:'auto'"/>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">搜索</a>

    </div>
</div>
<%--<div id="mm" style="width:150px;">
    <div data-options="iconCls:'icon-upload'">学员退款</div>
    <div data-options="iconCls:'icon-download'">教学花费</div>
    &lt;%&ndash;换行曲线&ndash;%&gt;
    &lt;%&ndash;<div class="menu-sep"></div>&ndash;%&gt;
</div>--%>
<div id="mm" class="easyui-menu" style="width:120px;">
    <div data-options="iconCls:'icon-zhichu'">
        <span>支出类型</span>
        <div style="width:150px;">
            <div data-options="iconCls:'icon-tuikuan'">学员退款</div>
            <div data-options="iconCls:'icon-jiaoxue'">教学花费</div>
            <div data-options="iconCls:'icon-tuanjian'">团队建设</div>
            <div data-options="iconCls:'icon-yuangong'">员工工资</div>
            <div data-options="iconCls:'icon-qita'">其它类型</div>
        </div>
    </div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-state'">
        <span>审核状态</span>
        <div style="width:150px;">
            <div data-options="iconCls:'icon-shi'">已审核</div>
            <div data-options="iconCls:'icon-delete'">未审核</div>
        </div>
    </div>
</div>


<table id="defray_datagrid"></table>
<!--弹出框保存/取消-->
<div id="defray_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<div id="defray_edit_dialog">
    <form id="defray_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 10px" cellspacing="5px" cellpadding="5px">
            <tr id="defrayTime">
                <td>支出时间:</td>
                <td><input type="text" name="defrayTime" class="easyui-datetimebox"/></td>
            </tr>
            <tr>
                <td>支出金额:</td>
                <td><input type="text" name="defrayMoney" class="easyui-numberbox"/></td>
            </tr>
            <tr>
                <td>支出摘要:</td>
                <td><input type="text"
                           name="remark" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>申请人</td>
                <td><input class="easyui-combobox" name="applicant.id"
                           data-options="valueField:'id',textField:'username',url:'/employee/selectAll.do'"/>
                </td>
            </tr>
            <tr id="handMan_input">
                <td>经手人</td>
                <td><input class="easyui-combobox" name="handMan.id"
                           data-options="valueField:'id',textField:'username',url:'/employee/selectAll.do'"/>
                </td>
            </tr>
            <tr>
                <td>支付方式</td>
                <td><input class="easyui-combobox" name="paymentType.id"
                           data-options="valueField:'id',textField:'name',url:'/systemdictionaryitem/selectItemByParentSn.do?sn=size'"/>
                </td>
            </tr>
            <tr>
                <td>支出类型</td>
                <td><input class="easyui-combobox" name="defrayType.id"
                           data-options="valueField:'id',textField:'name',url:'/systemdictionaryitem/selectItemByParentSn.do?sn=defrayState'"/>
                </td>
            </tr>
            <tr>
                <td>所属班级</td>
                <td>
                    <input class="easyui-combobox" name="classGrade.id"
                           data-options="valueField:'id',textField:'name',url:'/classGrade/selectAll.do'"/>
                </td>
            </tr>
            <tr id="auditor_input">
                <td>审核人</td>
                <td><input class="easyui-combobox" name="auditor.id"
                           data-options="valueField:'id',textField:'username',url:'/employee/selectAll.do'"/>
                </td>
            </tr>
            <tr id="billnumber_input">
                <td>单据号:</td>
                <td><input type="text"
                           name="billnumber" class="easyui-textbox"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
