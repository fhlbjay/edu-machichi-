<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="/static/js/potentitalStudent.js"></script>
    <style>
        #tab1 td input {
            width: 160px
        }
    </style>
</head>
<body>
<div id="ps_tb">
    <div>
        <%-- <shiro:hasPermission name="psloyee:saveOrUpdate">--%>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-man',plain:true" data-cmd="look">查看</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-filter',plain:true" data-cmd="query">高级查询</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-download',plain:true" data-cmd="exportXls">导出</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-foot',plain:true" data-cmd="track">跟踪</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-man',plain:true" data-cmd="queryFor59">只看非正式成员</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-kaoshi',plain:true" data-cmd="test">考试登记</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-chi',plain:true" data-cmd="pullPool">抛入资源池</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-shi',plain:true" data-cmd="change2official">转正按钮</a>
        <%--</shiro:hasPermission>--%>
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
<div id="ps_tbsc2">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save1">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel2">取消</a>
</div>
<div id="ps_tbsc3">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="putTest">提交</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancelTest">取消</a>
</div>
<div id="c2o_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="putOfficial">提交</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancelOfficial">取消</a>
</div>

<!--表单弹出框-->
<div id="ps_dialog">
    <!--弹出框的内容-->
    <form id="ps_form" method="post" style="width:700px;height:346px;">
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
        <table align="center" style="margin-top: 5px;font-size: 12px" cellspacing="5px" width="100%"
               cellpadding="5px">
            <tr align="right" style="height: 10px">
                <td><span style="font-size: 15px">姓名:</span></td>
                <td><input class="easyui-textbox" name="name" style="width: 110px;height: 22px;"/></td>
                <td><span style="font-size: 15px">年龄:</span></td>
                <td><input class="easyui-textbox" name="age" style="width: 120px;height: 22px;"/></td>
                <td><span style="font-size: 15px">性别:</span></td>
                <td><input class="easyui-combobox" name="gender"
                           data-options="valueField:'label',textField:'value',data:[{'label':'1',value:'男'},{label:'0',value:'女'}],panelHeight:'auto'"
                           style="width: 120px;height: 22px;"/></td>
            </tr>
            <tr align="right" style="height: 10px">
                <td><span style="font-size: 15px">电话:</span></td>
                <td><input class="easyui-textbox" name="tel" style="width: 110px;height: 22px;"/></td>
                <td><span style="font-size: 15px">QQ:</span></td>
                <td><input class="easyui-textbox" name="qq" style="width: 120px;height: 22px;"/></td>
                <td><span style="font-size: 15px">Email:</span></td>
                <td><input class="easyui-validatebox easyui-textbox" name="email" style="width: 120px;height: 22px;"
                           data-options="validType:'email'"
                /></td>
            </tr>
            <tr align="right" style="height: 10px">
                <td><span style="font-size: 15px">建档日期:</span></td>
                <td><input class="easyui-datetimebox" name="formBuildDate" style="width: 110px;height: 22px;"/></td>
                <td><span style="font-size: 15px">微信:</span></td>
                <td><input class="easyui-textbox" name="weChat" style="width: 120px;height: 22px;"/></td>
                <td><span style="font-size: 15px">学校:</span></td>
                <td><input class="easyui-textbox" name="school" style="width: 120px;height: 22px;"/></td>
            </tr>
            <tr align="right" style="height: 10px">
                <td><span style="font-size: 15px">录入时间:</span></td>
                <td><input class="easyui-datetimebox" name="inputTime" style="width: 110px;height: 22px;"/></td>
                <td><span style="font-size: 15px">地址:</span></td>
                <td><input class="easyui-textbox" name="address" style="width: 120px;height: 22px;"/></td>
                <td><span style="font-size: 15px">学历:</span></td>
                <td><input class="easyui-combobox" name="education.id" style="width: 120px;height: 22px;"
                           data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=education',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr align="right" style="height: 10px">
                <td><span style="font-size: 15px">下次跟进:</span></td>
                <td><input class="easyui-datetimebox" name="nextDate" style="width: 110px;height: 22px;"/></td>
                <td><span style="font-size: 15px">专业:</span></td>
                <td><input class="easyui-textbox" name="major" style="width: 120px;height: 22px;"/></td>
                <td><span style="font-size: 15px">来源:</span></td>
                <td><input class="easyui-combobox" name="source.id" style="width: 120px;height: 22px;"
                           data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=source',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr align="right" style="height: 10px">
                <td><span style="font-size: 15px">约访时间:</span></td>
                <td><input class="easyui-datetimebox" name="visitDate" style="width: 110px;height: 22px;"/></td>
                <td><span style="font-size: 15px">介绍学员:</span></td>
                <td><input class="easyui-combobox" name="introducerStu.id" style="width: 120px;height: 22px;"
                           data-options="url:'/potentitalStudent/selectAll.do',textField:'name',valueField:'id',panelHeight:'250'"/>
                <td><span style="font-size: 15px">介绍人:</span></td>
                <td><input class="easyui-textbox" name="introducer" style="width: 120px;height: 22px;"/></td>
            </tr>

            <tr align="right" style="height: 10px">
                <td><span style="font-size: 15px">客户类型:</span></td>
                <td><input class="easyui-combobox" name="clientType.id" style="width: 110px;height: 22px;"
                           data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=clientType',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
                <td><span style="font-size: 15px">意向程度:</span></td>
                <td><input class="easyui-combobox" name="intention.id" style="width: 120px;height: 22px;"
                           data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=intentionDegree',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
                <td><span style="font-size: 15px">当前状态:</span></td>
                <td><input class="easyui-combobox" name="status.id" style="width: 120px;height: 22px;"
                           data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=state',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr align="right" style="height: 10px">
                <td><span style="font-size: 15px">入学时间:</span></td>
                <td><input class="easyui-datetimebox" name="enrollUniversityDate" style="width: 110px;height: 22px;"/>
                </td>
                </td>
                <td><span style="font-size: 15px">意向班级:</span></td>
                <td><input class="easyui-combobox" name="classGrade.id" style="width: 120px;height: 22px;"
                           data-options="url:'/classGrade/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
                </td>
                <td><span style="font-size: 15px">学校客户:</span></td>
                <td><input class="easyui-combobox" name="bigCustomer.id" style="width: 120px;height: 22px;"
                           data-options="url:'/universitytrace/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr align="right" style="height: 10px">
                <td><span style="font-size: 15px">意向校区:</span></td>
                <td><input class="easyui-combobox" name="campus.id" style="width: 110px;height: 22px;"
                           data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=campus',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
                <td><span style="font-size: 15px">意向学科:</span></td>
                <td><input class="easyui-combobox" name="subject.id" style="width: 120px;height: 22px;"
                           data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=subject',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
                <td><span style="font-size: 15px">零付款:</span></td>
                <td align="left">&nbsp;&nbsp;&nbsp;<input type="radio" id="yes_radio" name="hasPaid" value="true"/>是&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" id="no_radio" name="hasPaid" value="false"/>否
                </td>
            </tr>
            <tr align="right" style="height: 70px">
                <td><span style="font-size: 15px">关注问题:</span></td>
                <td colspan="6" align="left"><input type="text" name="focusProblem"
                                                    style="height: 50px;width: 540px"/>
                </td>
            </tr>
            <tr align="right">
                <td><span style="font-size: 15px">备注:</span></td>
                <td colspan="6" align="left"><input type="text" name="remark"
                                                    style="height: 50px;width: 540px"/>
                </td>
            </tr>

        </table>
    </form>
</div>
<%--高级查询弹窗--%>
<div id="query_dialog">
    <form id="query_form" method="post" style="width:700px;height:110px;">
        <table align="center" style="margin-top: 10px;font-size: 12px" cellspacing="5px" width=100%
               cellpadding="5px">
            <tr align="right" style="height: 10px">
                <td><span style="font-size: 15px">姓名:</span></td>
                <td><input class="easyui-textbox" name="name" style="width: 120px;height: 22px;"/></td>
                <td><span style="font-size: 15px">年龄:</span></td>
                <td><input class="easyui-textbox" name="minAge" style="width: 50px;height: 22px;"/>&nbsp;～&nbsp;<input
                        class="easyui-textbox" name="maxAge" style="width: 50px;height: 22px;"/></td>
                </td>
                <td><span style="font-size: 15px">性别:</span></td>
                <td><input class="easyui-combobox" name="gender"
                           data-options="valueField:'label',textField:'value',data:[{'label':'男',value:'1'},{label:'女',value:'0'}],panelHeight:'auto'"
                           style="width: 120px;height: 22px;"/></td>
            </tr>
            <tr align="right" style="height: 10px">
                <td><span style="font-size: 15px">学历:</span></td>
                <td><input class="easyui-combobox" name="educationId" style="width: 120px;height: 22px;"
                           data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=education',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
                <td><span style="font-size: 15px">意向程度:</span></td>
                <td><input class="easyui-combobox" name="intentionId" style="width: 120px;height: 22px;"
                           data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=intentionDegree',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
                <td><span style="font-size: 15px">当前状态:</span></td>
                <td><input class="easyui-combobox" name="stateId" style="width: 120px;height: 22px;"
                           data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=state',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr align="right" style="height: 10px">
                </td>
                <td><span style="font-size: 15px">意向班级:</span></td>
                <td><input class="easyui-combobox" name="classGradeId" style="width: 120px;height: 22px;"
                           data-options="url:'/classGrade/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
                <td><span style="font-size: 15px">意向校区:</span></td>
                <td><input class="easyui-combobox" name="campusId" style="width: 120px;height: 22px;"
                           data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=campus',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
                <td><span style="font-size: 15px">意向学科:</span></td>
                <td><input class="easyui-combobox" name="intentionSubjectId" style="width: 120px;height: 22px;"
                           data-options="url:'/systemdictionaryitem/selectItemByParentSn.do?sn=subject',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<%--考试登记--%>
<div id="test_dialog">
    <form id="test_form" method="post" style="width:210px;height:120px;">
        <input type="hidden" name="employee.id">
        <table align="center" style="margin-top: 10px;font-size: 12px" cellspacing="2px" width=90% cellpadding="2px">
            <tr align="right" style="width: 200px; height: 5px">
                <td>姓名:<input class="easyui-textbox" name="name" style="width: 120px;height: 22px;"/></td>
            </tr>
            <tr align="right" style="width: 200px;  height: 5px">
                <td>QQ:<input class="easyui-textbox" name="qq" style="width: 120px;height: 22px;"/></td>
            </tr>
            <tr align="right" style="width: 200px;  height: 5px">
                <td>电话:<input class="easyui-textbox" name="tel" style="width: 120px;height: 22px;"/></td>
            </tr>
            <tr align="right" style="width: 200px;  height: 5px">
                <td>考试时间:<input class="easyui-datetimebox" name="testTime" style="width: 120px;height: 22px;"/></td>
            </tr>
            <tr align="right" style="width: 200px;  height: 5px">
                <td>意向班级:<input class="easyui-combobox" name="classGrade.id" style="width: 120px;height: 22px;"
                                data-options="url:'/classGrade/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr align="right" style="width: 100px; height: 5px">
                <td>备注:<input class="easyui-textbox" name="remark" style="width: 120px;height: 22px;"/></td>
            </tr>
        </table>
    </form>
</div>

<div id="track_dialog">
    <form id="track_form" method="post" style="width:700px;height:400px;">
        <input type="hidden" name="id">
        <input type="hidden" name="trackTimes">
        <input type="hidden" name="trackState">


        <table align="center" style="margin-top: 10px;font-size: 12px" cellspacing="10px"
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
            <tr align="right" style="height: 70px">
                <td><span style="font-size: 14px">摘要:</span></td>
                <td colspan="6" align="left"><input type="textarea" name="summary"
                                                    style="height: 50px;width: 540px"/>
                </td>
            </tr>
            <tr align="right">
                <td><span style="font-size: 14px">交流内容:</span></td>
                <td colspan="6" align="left"><input type="textarea" name="communicationContext"
                                                    style="height: 50px;width: 540px"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<!--编辑弹出框-->
<div id="change2official_dialog">
    <!--弹出框的内容-->
    <form id="change2official_form" method="post">
        <div id="change2official_tabs" class="easyui-tabs" style="width:700px;height:346px;"
             data-options="fit:true">
            <div id="tab1" title="基本信息" style="display:none;">
                <%--编辑隐藏id--%>
                <input type="hidden" name="potentitalStudent.id"/>
                <table style="border-top: 10px" cellpadding="0px" cellspacing="3px" width="100%">
                    <tbody>
                    <div id="baseInfo">
                        <tr>
                            <td><b style="color: #0ca3d2">基本信息</b></td>
                        </tr>
                        <tr>
                            <td>姓名:</td>
                            <td><input disabled type="text" name="potentitalStudent.name"
                                       class="easyui-validatebox easyui-textbox"
                                       prompt="请输入真实姓名"/>
                            </td>
                            <td>年龄:</td>
                            <td><input disabled type="text" name="potentitalStudent.age"
                                       class="easyui-validatebox easyui-textbox"/>
                            </td>
                            <td>性别:</td>
                            <td>
                                <select disabled class="easyui-combobox" name="gender"
                                        style="width: 120px;height: 21px;"
                                        data-options="panelHeight:'auto'">
                                    <option value="0">男</option>
                                    <option value="1">女</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>电话:</td>
                            <td><input type="text" disabled name="potentitalStudent.tel" class="easyui-textbox"/></td>
                            <td>QQ:</td>
                            <td><input type="text" disabled name="potentitalStudent.qq" class="easyui-textbox"/></td>
                            <td>Email:</td>
                            <td><input type="text" disabled name="potentitalStudent.email" class="easyui-textbox"/></td>
                        </tr>
                        <tr>
                            <td>学校:</td>
                            <td><input type="text" disabled name="potentitalStudent.school" class="easyui-textbox"/>
                            </td>
                            <td>专业:</td>
                            <td><input type="text" disabled name="potentitalStudent.major" class="easyui-textbox"/></td>
                            <td>学历:</td>
                            <td><input type="text" disabled class="easyui-textbox easyui-combobox"
                                       name="potentitalStudent.education.id"
                                       data-options="valueField:'id',textField:'name',
                                   url:'/systemdictionaryitem/selectItemByParentSn.do?sn=education',panelHeight:'auto'"/>
                            </td>
                        </tr>
                        <tr>
                            <td>地址:</td>
                            <td><input type="text" disabled name="potentitalStudent.address" class="easyui-textbox"/>
                            </td>
                            <td>来源:</td>
                            <td><input type="text" disabled class="easyui-textbox easyui-combobox"
                                       name="potentitalStudent.source.id"
                                       data-options="valueField:'id',textField:'name',
                                   url:'/systemdictionaryitem/selectItemByParentSn.do?sn=source',panelHeight:'auto'"/>
                            </td>
                            <td>客户类型:</td>
                            <td><input type="text" disabled class="easyui-textbox easyui-combobox"
                                       name="potentitalStudent.clientType.id"
                                       data-options="valueField:'id',textField:'name',
                                   url:'/systemdictionaryitem/selectItemByParentSn.do?sn=clientType',panelHeight:'auto'"/>
                            </td>
                        </tr>
                        <tr>
                            <td>所在班级:</td>
                            <td><input type="text" disabled class="easyui-textbox easyui-combobox"
                                       name="potentitalStudent.classGrade.id"
                                       data-options="valueField:'id',textField:'name',
                                   url:'/classGrade/selectAll.do',panelHeight:'auto'"/>
                            <td>营销人员:</td>
                            <td><input type="text" disabled name="potentitalStudent.employee.username"
                                       class="easyui-textbox"/></td>
                            <td>所在校区:</td>
                            <td><input type="text" disabled class="easyui-combobox"
                                       name="potentitalStudent.campus.id"
                                       data-options="valueField:'id',textField:'name',
                                   url:'/systemdictionaryitem/selectItemByParentSn.do?sn=campus',panelHeight:'auto'"/>
                            </td>
                        </tr>
                        <tr>
                    </div>
                    <td><b style="color: #0ca3d2">缴费信息</b></td>
                    </tr>
                    <tr>
                        <td>计划学费:</td>
                        <td><input type="number" name="payment.planFee" class="autoCal" required/></td>
                        <td>优惠金额:</td>
                        <td><input type="number" name="payment.reduceFee" class="autoCal"/></td>
                        <td>其他优惠:</td>
                        <td><input type="number" name="payment.otherDiscount" class="autoCal"/></td>
                    </tr>
                    <tr>
                        <td>其他费用:</td>
                        <td><input type="number" name="payment.otherFee" class="autoCal"/></td>
                        <td>优惠说明:</td>
                        <td><input type="text" name="payment.reduceExplanation" class="easyui-textbox"/></td>
                        <td>总费用:</td>
                        <td><input type="number" name="payment.totalFee" class="autoCal"/></td>
                    </tr>
                    <tr>
                        <td>已付学费:</td>
                        <td><input type="number" name="payment.paidupFee" class="autoCal"/></td>
                        <td>还欠学费:</td>
                        <td><input type="number" name="payment.fee2pay" class="autoCal"/></td>
                        <td>付款方式:</td>
                        <td><input type="text" class="easyui-combobox"
                                   name="payment.paymentType.id" required
                                   data-options="valueField:'id',textField:'name',
                                   url:'/systemdictionaryitem/selectItemByParentSn.do?sn=size',panelHeight:'auto'"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div title="私密信息" data-options="closable:true" style="overflow:auto;padding:5px;display:none;">
                <table align="center" style="border-collapse:separate; border-spacing:0px 10px;">
                    <tr>
                        <td><span style="font-size: 12px">身份证号:</span></td>
                        <td><input type="text" name="detailInfo.idNo" size="35"></td>
                    </tr>
                    <tr>

                        <td><span style="font-size: 12px">紧急联系人:</span></td>
                        <td><input type="text" name="detailInfo.emergencyContact" size="35"></td>
                    </tr>
                    <tr>

                        <td><span style="font-size: 12px">紧急联系电话:</span></td>
                        <td><input type="text" name="detailInfo.emergencyTel" size="35"></td>
                    </tr>
                </table>

                <table align="center" cellpadding="2px">
                    <tr>
                        <td align="right"><span style="font-size: 12px">就业意向</span>:</td>
                        <td><label>
                            <input type="radio" name="detailInfo.workIntention" value="1">
                            <span style="font-size: 12px">拥有一份软件工程师工作</span>
                        </label></td>
                        <td><label>
                            <input type="radio" name="detailInfo.workIntention" value="2">
                            <span style="font-size: 12px">搬砖</span>
                        </label></td>
                        <td><label>
                            <input type="radio" name="detailInfo.workIntention" value="3">
                            <span style="font-size: 12px">挖煤</span>
                        </label></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><label>
                            <input type="radio" name="detailInfo.workIntention" value="4">
                            <span style="font-size: 12px">开挖掘机</span>
                        </label></td>
                        <td><label>
                            <input type="radio" name="detailInfo.workIntention" value="5">
                            <span style="font-size: 12px">修电脑</span>
                        </label></td>
                    </tr>
                    <tr>
                        <td align="right"><span style="font-size: 12px">工作经历</span>:</td>
                        <td colspan="3"><textarea name="detailInfo.workExperience"
                                                  style="width: 450px;height:130px"></textarea></td>
                    </tr>
                </table>
            </div>
        </div>
    </form>
</div>
</body>
</html>
