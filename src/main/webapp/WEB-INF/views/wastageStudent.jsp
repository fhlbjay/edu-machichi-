<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="/static/js/wastageStudent.js"></script>
</head>
<body>
<div id="wastageStu_tb">
    <div>

        <%--<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>--%>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
        <a id="look" class="easyui-linkbutton" data-options="iconCls:'icon-user',plain:true" data-cmd="look">查看</a>
        <a  id="refund" class="easyui-linkbutton" data-options="iconCls:'icon-money',plain:true"
           data-cmd="refund">退款</a>
        <a id="changeState" class="easyui-linkbutton" data-options="iconCls:'icon-fillup',plain:true" data-cmd="changeState">审核</a>

        <%--高级查询--%>
        模糊查询:<input id="keyword" type="text" name="keyword" prompt="流失学员姓名或者电话" class="easyui-textbox"/>
        流失时间段:<input id="beginDate"  type="text" name="beginDate" class="easyui-textbox easyui-datebox"/>~
        <input id="endDate" type="text" name="endDate" class="easyui-textbox easyui-datebox"/>
        班级:<input id="classgradeId" prompt="流失学员之前所在班级" type="text" name="classgradeId" class="easyui-textbox easyui-combobox"
                  data-options="valueField:'id',textField:'name',url:'/classGrade/selectAll.do',panelHeight:'auto'"/>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">搜索</a>

    </div>
</div>

<table id="wastageStu_datagrid"></table>
<!--弹出框保存/取消-->
<div id="wastageStu_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<div id="wastageStu_refund_tbsc">
    <a class="easyui-linkbutton" id="refund_save" data-options="iconCls:'icon-save',plain:true" data-cmd="refund_save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="refund_cancel">取消</a>
</div>
<div id="wastage_edit_dialog">
    <form id="wastage_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 10px" cellspacing="5px" cellpadding="5px">
            <tr>
                <td>学员姓名:</td>
                <td><input type="text" readonly name="name" readonly
                           readonly class="easyui-validatebox easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input type="text" readonly
                           name="tel" class="easyui-textbox"/></td>
            </tr>
             <tr>
                  <td>营销人员:</td>
                  <td><input type="text" readonly name="saleman.username" readonly class="easyui-textbox"/></td>
              </tr>
              <tr>
                  <td>流失日期:</td>
                  <td><input type="text" name="wastageDate" class="easyui-datebox"/></td>
              </tr>
              <tr>
                  <td>入学日期:</td>
                  <td><input type="text" name="enrolTime" class="easyui-datebox"/></td>
              </tr>
            <tr>
                  <td>流失班级</td>
                  <td><input type="text" class="easyui-textbox"
                             name="classgrade.name"
                             readonly />
                  </td>
              </tr>
               <tr>
                  <td>经办人:</td>
                  <td><input type="text" readonly name="operator.username" class="easyui-textbox"/></td>
              </tr>
              <tr>
                  <td>流失原因:</td>
                  <td><input type="text"  name="reason" class="easyui-textbox"/></td>
              </tr>
              <tr>
                  <td>已上课天数:</td>
                  <td><input type="text"  name="studyDaysNum" class="easyui-textbox"/></td>
              </tr>
              <tr>
                  <td>未退款金额:</td>
                  <td><input type="text" readonly name="refundMoney" class="easyui-numberbox"/></td>
              </tr>
              <tr>
                  <td>退款状态:</td>
                  <td><select readonly class="easyui-combobox" name="refundState" panelHeight='auto' >
                      <option value="1" >已退款</option>
                      <option value="0">退款中</option>
                  </select>
                  </td>
              </tr>
        </table>
    </form>
</div>
<%--退款表格--%>
<div id="wastage_refund_dialog">
    <form id="wastage_refund_form" method="post">
        <input type="hidden"  name="applicant.id"/>
        <input type="hidden"  name="classGrade.id"/>
        <table align="center" style="margin-top: 10px" cellspacing="5px" cellpadding="5px">
            <tr>
                <td>退款金额:</td>
                <td><input type="text" id="defrayMoney" name="defrayMoney" class="easyui-numberbox"/></td>
            </tr>
            <tr>
                <td>未退款金额:</td>
                <td><input type="text" id="refundMoney" name="refundMoney" class="easyui-numberbox"/></td>
            </tr>
            <tr>
                <td>退款摘要:</td>
                <td><input type="text"
                           name="remark" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>退款学员</td>
                <td>
                    <input class="easyui-textbox" name="applicant.username"/>
                </td>
            </tr>
            <tr>
                <td>支付方式</td>
                <td><input class="easyui-combobox" name="paymentType.id"
                           data-options="valueField:'id',textField:'name',url:'/systemdictionaryitem/selectItemByParentSn.do?sn=size'"/>
                </td>
            </tr>
            <tr>
                <td>所属班级</td>
                <td>
                    <input class="easyui-textbox" name="classGrade.name"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
