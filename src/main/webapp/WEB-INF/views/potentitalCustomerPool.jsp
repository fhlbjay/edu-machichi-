<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="/static/js/potentitalCustomerPool.js"></script>
</head>
<body>
<div id="ps_tb">
    <div>
       <%-- <shiro:hasPermission name="psloyee:saveOrUpdate">--%>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-man',plain:true" data-cmd="look">查看</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-man',plain:true" data-cmd="pull">移交给我</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-man',plain:true" data-cmd="push">指派给</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
    </div>
</div>

<table id="ps_datagrid"></table>
<!--弹出框保存/取消-->
<div id="ps_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<div id="ps_tbsc1">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="put">提交</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel1">取消</a>
</div>

<!--表单弹出框-->
<div id="ps_dialog">
    <!--弹出框的内容-->
    <form id="ps_form" method="post" style="width:670px;height:450px;">
        <%--编辑隐藏id--%>
        <input type="hidden" name="id"/>
        <input type="hidden" name="employee.id"/>
        <input type="hidden" name="prevDate"/>
        <input type="hidden" name="trackTimes"/>
        <input type="hidden" name="trackState"/>
        <input type="hidden" name="communicationMethod.id"/>
        <input type="hidden" name="tracePurpose.id"/>
        <input type="hidden" name="consultationTime"/>
        <input type="hidden" name="summary"/>
        <input type="hidden" name="communicationContext"/>
        <input type="hidden" name="campus.id"/>
        <input type="hidden" name="trackState"/>

            <table align="center" style="margin-top: 10px;font-size: 13px" cellspacing="5px" width="100%"
                   cellpadding="5px">
                <tr align="right" style="height: 10px">
                    <td><span>姓名:</span></td>
                    <td><input class="easyui-textbox" name="name" style="width: 110px;height: 22px;"/></td>
                    <td><span >年龄:</span></td>
                    <td><input class="easyui-textbox" name="age" style="width: 120px;height: 22px;"/></td>
                    <td><span >性别:</span></td>
                    <td><input class="easyui-combobox" name="gender"
                               data-options="valueField:'label',textField:'value',data:[{'label':'男',value:'男'},{label:'女',value:'女'}],panelHeight:'auto'"
                               style="width: 120px;height: 22px;"/></td>
                </tr>
                <tr align="right">
                    <td><span >电话:</span></td>
                    <td><input class="easyui-textbox" name="tel" style="width: 110px;height: 22px;"/></td>
                    <td><span >QQ:</span></td>
                    <td><input class="easyui-textbox" name="qq" style="width: 120px;height: 22px;"/></td>
                    <td><span >Email:</span></td>
                    <td><input class="easyui-validatebox easyui-textbox" name="email" style="width: 120px;height: 22px;"
                               data-options="validType:'email'"
                    /></td>
                </tr>
                <tr align="right" >
                    <td><span >建档日期:</span></td>
                    <td><input class="easyui-datetimebox" name="formBuildDate" style="width: 110px;height: 22px;"/></td>
                    <td><span >微信:</span></td>
                    <td><input class="easyui-textbox" name="weChat" style="width: 120px;height: 22px;"/></td>
                    <td><span >学校:</span></td>
                    <td><input class="easyui-textbox" name="school" style="width: 120px;height: 22px;"/></td>
                </tr>
                <tr align="right" style="height: 10px">
                    <td><span >录入时间:</span></td>
                    <td><input class="easyui-datetimebox" name="inputTime" style="width: 110px;height: 22px;"/></td>
                    <td><span >地址:</span></td>
                    <td><input class="easyui-textbox" name="address" style="width: 120px;height: 22px;"/></td>
                    <td><span >学历:</span></td>
                    <td><input class="easyui-combobox" name="education.id" style="width: 120px;height: 22px;"
                               data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=education',textField:'name',valueField:'id',panelHeight:'auto'"/>
                    </td>
                </tr>
                <tr align="right" style="height: 10px">
                    <td><span >下次跟进:</span></td>
                    <td><input class="easyui-datetimebox" name="nextDate" style="width: 110px;height: 22px;"/></td>
                    <td><span >专业:</span></td>
                    <td><input class="easyui-textbox" name="major" style="width: 120px;height: 22px;"/></td>
                    <td><span >来源:</span></td>
                    <td><input class="easyui-combobox" name="source.id" style="width: 120px;height: 22px;"
                               data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=source',textField:'name',valueField:'id',panelHeight:'auto'"/>
                    </td>
                </tr>
                <tr align="right" style="height: 10px">
                    <td><span >约访时间:</span></td>
                    <td><input class="easyui-datetimebox" name="visitDate" style="width: 110px;height: 22px;"/></td>
                    <td><span >介绍学员:</span></td>
                    <td><input class="easyui-combobox" name="introducerStu.id" style="width: 120px;height: 22px;"
                               data-options="url:'/potentitalStudent/selectAllSn.do',textField:'name',valueField:'sn',panelHeight:'250'"/>
                    <td><span >介绍人:</span></td>
                    <td><input class="easyui-textbox" name="introducer" style="width: 120px;height: 22px;"/></td>
                </tr>

                <tr align="right" style="height: 10px">
                    <td><span >客户类型:</span></td>
                    <td><input class="easyui-combobox" name="clientType.id" style="width: 110px;height: 22px;"
                               data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=clientType',textField:'name',valueField:'id',panelHeight:'auto'"/>
                    </td>
                    <td><span >意向程度:</span></td>
                    <td><input class="easyui-combobox" name="intention.id" style="width: 120px;height: 22px;"
                               data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=intentionDegree',textField:'name',valueField:'id',panelHeight:'auto'"/>
                    </td>
                    <td><span >当前状态:</span></td>
                    <td><input class="easyui-combobox" name="status.id" style="width: 120px;height: 22px;"
                               data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=state',textField:'name',valueField:'id',panelHeight:'auto'"/>
                    </td>
                </tr>
                <tr align="right" style="height: 10px">
                    <td><span >入学时间:</span></td>
                    <td><input class="easyui-datetimebox" name="enrollUniversityDate" style="width: 110px;height: 22px;"/>
                    </td>
                    </td>
                    <td><span >意向班级:</span></td>
                    <td><input class="easyui-combobox" name="classGrade.id" style="width: 120px;height: 22px;"
                               data-options="url:'/classGrade/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto'"/>
                    </td>
                    </td>
                    <td><span >学校客户:</span></td>
                    <td><input class="easyui-combobox" name="bigCustomer.id" style="width: 120px;height: 22px;"
                               data-options="url:'/universitytrace/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto'"/>
                    </td>
                </tr>
                <tr align="right" style="height: 10px">
                    <td><span >意向校区:</span></td>
                    <td><input class="easyui-combobox" name="campus.id" style="width: 110px;height: 22px;"
                               data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=campus',textField:'name',valueField:'id',panelHeight:'auto'"/>
                    </td>
                    <td><span >意向学科:</span></td>
                    <td><input class="easyui-combobox" name="subject.id" style="width: 120px;height: 22px;"
                               data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=subject',textField:'name',valueField:'id',panelHeight:'auto'"/>
                    </td>
                    <td><span >零付款:</span></td>
                    <td align="left">&nbsp;&nbsp;&nbsp;<input type="radio" id="yes_radio" name="hasPaid" value="true"/>是&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" id="no_radio" name="hasPaid" value="false"/>否
                    </td>
                </tr>
                <tr align="right" style="height: 80px">
                    <td><span >关注问题:</span></td>
                    <td colspan="6" align="left"><input type="text" name="focusProblem"
                                                        style="height: 50px;width: 540px"/>
                    </td>
                </tr>
                <tr align="right">
                    <td><span >备注:</span></td>
                    <td colspan="6" align="left"><input type="text" name="remark"
                                                        style="height: 50px;width: 540px"/>
                    </td>
                </tr>

            </table>
    </form>
</div>
<%--指定给--%>
<div id="pool_dialog">
    <form id="pool_form" method="post">
        <table align="left" style="margin-top: 10px;font-size: 12px" cellspacing="10px" width="200px"
               cellpadding="5px">
            <tr align="left" style="height: 10px">
                <td>姓名:<input class="easyui-textbox" name="name" style="width: 120px;height: 22px;"/></td>
            </tr>
            </td>
            <tr align="left" style="height: 10px">
            <td>营销人员<input id="employeeId" class="easyui-combobox" name="employee.id" style="width: 120px;height: 22px;"
                       data-options="url:'/employee/selectAll.do',textField:'username',valueField:'id',panelHeight:'auto'"/>
            </td>
            </tr>
        </table>
    </form>
</div>




</body>
</html>
