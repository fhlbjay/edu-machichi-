<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="/static/js/trackStudent.js"></script>
</head>
<body>
<div id="ps_tb">
    <div>
       <%-- <shiro:hasPermission name="psloyee:saveOrUpdate">--%>
           <a class="easyui-linkbutton" data-options="iconCls:'icon-foot',plain:true" data-cmd="track">编辑</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-man',plain:true" data-cmd="look">查看</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-filter',plain:true" data-cmd="audit">审核</a>
    </div>
</div>

<table id="ps_datagrid"></table>
<!--弹出框保存/取消-->
<div id="ps_tbsc">
    <a  class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<div id="ps_tbsc1">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save1">提交</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel1">取消</a>
</div>



<%--审核--%>
<div id="test_dialog">
    <form id="test_form" method="post" style="width:150px;height:100px;">
        <input type="hidden" name="communicationMethod.id">
        <input type="hidden" name="employee.id">
        <input type="hidden" name="id">
        <input type="hidden" name="auditResult">
        <table align="center" style="margin-top: 10px;font-size: 12px" cellspacing="5px"
               cellpadding="5px">
            <tr align="left" style="height: 5px">
                <td>学员:<input class="easyui-textbox" name="name" style="width: 120px;height: 22px;"/></td>
            </tr>

            <tr align="left" style="height: 5px">
                <td>咨询人员:<input class="easyui-textbox" name="employee.username" style="width: 120px;height: 22px;"/></td>
            </tr>
            <tr align="left" style="height: 5px">
                <td>交流方式:<input class="easyui-textbox" name="communicationMethod.name" style="width: 120px;height: 22px;"/></td>
            </tr>
            <tr  align="left" style="height: 5px">
            <td>评分:<input class="easyui-combobox" name="score.id" style="width: 120px;height: 22px;"
                       data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=score',textField:'name',valueField:'id',panelHeight:'auto'"/>
            </td>
            </tr>
            <tr align="left" style="height: 5px">
                <td>审核说明:<input class="easyui-textbox" name="auditContext" style="width: 120px;height: 22px;"/></td>
            </tr>
        </table>
    </form>
</div>
<%--编辑查看页面--%>
<div id="track_dialog">
    <form id="track_form" method="post" style="width:700px;height:346px;">
        <input type="hidden" name="id">
        <input type="hidden" name="trackTimes">

        <table align="center" style="margin-top: 10px;font-size: 12px;widows: 100%;" cellspacing="10px"
               cellpadding="5px">

            <tr align="right" style="height: 30px">
                <td><span style="font-size: 14px">学员:</span></td>
                <td><input class="easyui-combobox" name="name" style="width: 120px;height: 22px;"
                    /></td>
                <td><span style="font-size: 14px">QQ:</span></td>
                <td><input class="easyui-textbox" name="qq" style="width: 120px;height: 22px;"/></td>
                <td><span style="font-size: 14px">Email:</span></td>
                <td><input class="easyui-validatebox easyui-textbox" name="email" style="width: 120px;height: 22px;"
                           data-options="validType:'email'"
                /></td>
            </tr>
            <tr align="right" style="height: 30px">
                <td><span style="font-size: 14px">电话:</span></td>
                <td><input class="easyui-textbox" name="tel" style="width: 130px;height: 22px;"/></td>
                <td><span style="font-size: 14px">当前状态:</span></td>
                <td><input class="easyui-combobox" name="status.id" style="width: 120px;height: 22px;"
                           data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=state',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
                <td><span style="font-size: 14px">营销人员:</span></td>
                <td><input class="easyui-combobox" name="employee.id"
                           data-options="valueField:'id',textField:'username',url:'/employee/selectAll.do',panelHeight:'auto'"
                           style="width: 120px;height: 22px;"/></td>
            </tr>
            <tr align="right" style="height: 30px">
                <td><span style="font-size: 14px">上次跟进:</span></td>
                <td><input class="easyui-datetimebox" name="prevDate" style="width: 130px;height: 22px;"/></td>
                <td><span style="font-size: 14px">所属学校:</span></td>
                <td><input class="easyui-combobox" name="school"
                           style="width: 120px;height: 22px;"/></td>
                <td><span style="font-size: 14px">意向班级:</span></td>
                <td><input class="easyui-combobox" name="classGrade.id" style="width: 120px;height: 22px;"
                           data-options="url:'/classGrade/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr align="right" style="height: 30px">
                <td><span style="font-size: 14px">约访日期:</span></td>
                <td><input id="appointTime" class="easyui-datetimebox" name="visitDate"
                           style="width: 130px;height: 22px;"/></td>
                <td><span style="font-size: 14px">交流方式:</span></td>
                <td><input class="easyui-combobox" name="communicationMethod.id"
                           data-options="valueField:'id',textField:'name',url:'/systemdictionaryitem/selectItemByParentSn.do?sn=communicationMethod',panelHeight:'auto'"
                           style="width: 120px;height: 22px;"/></td>
                <td><span style="font-size: 14px">意向程度:</span></td>
                <td><input class="easyui-combobox" name="intention.id" style="width: 120px;height: 22px;"
                           data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=intentionDegree',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr align="right" style="height: 30px">
                <td><span style="font-size: 14px">下次跟进:</span></td>
                <td><input id="nextTraceTime" class="easyui-datetimebox" name="nextDate"
                           style="width: 130px;height: 22px;"/></td>
                <td><span style="font-size: 14px">跟进目的:</span></td>
                <td><input class="easyui-combobox" name="tracePurpose.id" style="width: 120px;height: 22px;"
                           data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=tracePurpose',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
                <td><span style="font-size: 14px">咨询时长:</span></td>
                <td><input class="easyui-textbox" data-options="value:'分钟'" name="consultationTime"
                           style="width: 120px;height: 22px;"/></td>
            </tr>
            <tr align="right" style="height: 80px">
                <td><span style="font-size: 14px">摘要:</span></td>
                <td colspan="6" align="left"><input type="textarea" name="summary"
                                                    style="height: 50px;width: 540px"/>
                </td>
            </tr>
            <tr align="right">
                <td><span style="font-size: 14px">交流内容:</span></td>
                <td colspan="6" align="left"><input type="textarea" name="communicationContext" style="height: 50px;width: 540px"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
