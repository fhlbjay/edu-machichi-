<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="/static/js/officialStudent.js"></script>
    <style>
        #tab1 td input {
            width: 160px
        }
    </style>
</head>
<body>
<div id="officialStudent_tb">
    <div>
        <%-- <shiro:hasPermission name="officialStudent:Update">--%>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-user',plain:true" data-cmd="view">查看</a>
        <%--</shiro:hasPermission>--%>
        <a id="changeClass" class="easyui-linkbutton" data-options="iconCls:'icon-classroom',plain:true"
           data-cmd="changeClass">转班</a>
        <a id="quitSchool" class="easyui-linkbutton" data-options="iconCls:'icon-pause',plain:true"
           data-cmd="quitSchool">休学</a>
        <a id="runOff" class="easyui-linkbutton" data-options="iconCls:'icon-leave',plain:true" data-cmd="runOff">流失</a>
        <%--高级查询--%>
        班级:<input id="classgradeId" type="text" name="classgradeId" class="easyui-textbox easyui-combobox"
                  data-options="valueField:'id',textField:'name',url:'/classGrade/selectAll.do',panelHeight:'auto'"/>
        入学时间段:<input id="beginEnrolDate" type="text" name="beginEnrolDate" class="easyui-textbox easyui-datebox"/>~
        <input id="endEnrolDate" type="text" name="endEnrolDate" class="easyui-textbox easyui-datebox"/>
        <input id="keyword" type="text" name="keyword" class="easyui-textbox" prompt="关键字"/>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>
        <%--<a class="easyui-linkbutton" data-options="iconCls:'icon-download',plain:true" data-cmd="exportXls">导出</a>--%>
    </div>
</div>

<table id="officialStudent_datagrid"></table>
<!--弹出框保存/取消-->
<div id="officialStudent_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<!--弹出框保存/取消-->
<div id="officialStudent_changeClassBtn">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save_changeClass">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel_changeClass">取消</a>
</div>
<div id="officialStudent_cutOff_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save_cutOff">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel_cutOff">取消</a>
</div>
<%--文件上传弹出框按钮--%>
<div id="input_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" data-cmd="undo">上传</a>
</div>
<%--文件上传--%>
<%--弹出窗口--%>
<div id="fileInput">
    <%--文件上传内容--%>
    <%-- <form action="/officialStudent/input.do" method="post" enctype="multipart/form-data">
         文件:<input type="file" name="file"/>
         <input type="submit" value="上传"/>
     </form>--%>
    <form id="input_form" method="post" enctype="multipart/form-data">
        <%--文件:<input type="file" name="file"/>--%>
        上传文件: <input class="easyui-filebox" name="file" style="width:180px" data-options="buttonText:'选择文件'">
    </form>
</div>

<%--流失弹出框--%>
<div id="officialStudent_cutOff_dialog">
    <form id="officialStudent_cutOff_form" method="post">
        <table align="center" style="margin-top: 20px" cellspacing="5px" cellpadding="5px">
            <tr>
                <td>是否退款</td>
                <td>
                    <label>
                        <input id="refundState" type="radio" name="refundState" value="1">
                        <span style="font-size: 12px">是</span>
                    </label>&nbsp;&nbsp;&nbsp;
                    <label>
                        <input type="radio" name="refundState" value="0">
                        <span style="font-size: 12px">否</span>
                    </label>
                </td>
            </tr>
            <tr style="height: 20px;"></tr>
            <tr>
                <td>流失阶段</td>
                <td>
                    <select class="easyui-combobox" style="width: 150px; height: 28px;" name="cutOffPhase"
                            data-options="panelHeight:'auto'">
                        <option value="基础班">基础班</option>
                        <option value="大神班">大神班</option>
                    </select>
                </td>
            </tr>
            <tr id="refundMoney_tr">
                <td>退款金额</td>
                <td><input class="easyui-numberbox" name="refundMoney" style="width: 150px; height: 28px;"/>
                </td>
            </tr>
            <tr>
                <td>退学原因</td>
                <td>
                    <textarea name="reason" style="width: 150px;height: 60px"></textarea>
                </td>
            </tr>
        </table>
    </form>
</div>
<%--转班弹框--%>
<div id="officialStudent_changeClass_dialog">
    <form id="officialStudent_changeClass_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 20px" cellspacing="10px" cellpadding="5px">
            <tr>
                <td>当前班级</td>
                <td><input id="currentClass" type="text" class="easyui-textbox easyui-combobox"
                           name="currentClassId" readonly
                           data-options="valueField:'id',textField:'name',
                                   url:'/classGrade/selectAll.do',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td>转入班级</td>
                <td><input id="afterClass" type="text" class="easyui-textbox easyui-combobox easyui-validatebox"
                           name="afterClassId" required="required"
                           data-options="valueField:'id',textField:'name',
                                   url:'/classGrade/selectAll.do?norepeat=true&',panelHeight:'auto'"/>
                </td>

            </tr>
        </table>
    </form>
</div>
<!--编辑弹出框-->
<div id="officialStudent_dialog">
    <!--弹出框的内容-->
    <form id="officialStudent_form" method="post">
        <div id="officialStudent_tabs" class="easyui-tabs" style="width:700px;height:375px;"
             data-options="fit:true">
            <div id="tab1" title="基本信息" style="display:none;">
                <%--编辑隐藏id--%>
                <input type="hidden" name="id"/>
                <input type="hidden" name="payment.id"/>
                <input type="hidden" name="potentitalStudent.id"/>
                <input type="hidden" name="detailInfo.id"/>
                <table style="border-top: 10px" cellpadding="0px" cellspacing="3px" width="100%">
                    <tbody>
                    <div id="baseInfo">
                        <tr>
                            <td><b style="color: #0ca3d2">基本信息</b></td>
                        </tr>
                        <tr>
                            <td>姓名:</td>
                            <td><input type="text" name="potentitalStudent.name"
                                       class="easyui-validatebox easyui-textbox"
                                       prompt="请输入真实姓名"/>
                            </td>
                            <td>年龄:</td>
                            <td><input type="text" name="potentitalStudent.age"
                                       class="easyui-validatebox easyui-textbox"/>
                            </td>
                            <td>性别:</td>
                            <td>
                                <select class="easyui-combobox" name="potentitalStudent.gender"
                                        style="width: 120px;height: 21px;"
                                        data-options="panelHeight:'auto'">
                                    <option value="0">男</option>
                                    <option value="1">女</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>电话:</td>
                            <td><input type="text" name="potentitalStudent.tel" class="easyui-textbox"/></td>
                            <td>QQ:</td>
                            <td><input type="text" name="potentitalStudent.qq" class="easyui-textbox"/></td>
                            <td>Email:</td>
                            <td><input type="text" name="potentitalStudent.email" class="easyui-textbox"/></td>
                        </tr>
                        <tr>
                            <td>学校:</td>
                            <td><input type="text" name="potentitalStudent.school" class="easyui-textbox"/></td>
                            <td>专业:</td>
                            <td><input type="text" name="potentitalStudent.major" class="easyui-textbox"/></td>
                            <td>学历:</td>
                            <td><input type="text" class="easyui-textbox easyui-combobox"
                                       name="potentitalStudent.education.id"
                                       data-options="valueField:'id',textField:'name',
                                   url:'/systemdictionaryitem/selectItemByParentSn.do?sn=education',panelHeight:'auto'"/>
                            </td>
                        </tr>
                        <tr>
                            <td>地址:</td>
                            <td><input type="text" name="potentitalStudent.address" class="easyui-textbox"/></td>
                            <td>来源:</td>
                            <td><input type="text" class="easyui-textbox easyui-combobox"
                                       name="potentitalStudent.source.id"
                                       data-options="valueField:'id',textField:'name',
                                   url:'/systemdictionaryitem/selectItemByParentSn.do?sn=source',panelHeight:'auto'"/>
                            </td>
                            <td>客户类型:</td>
                            <td><input type="text" class="easyui-textbox easyui-combobox"
                                       name="potentitalStudent.clientType.id"
                                       data-options="valueField:'id',textField:'name',
                                   url:'/systemdictionaryitem/selectItemByParentSn.do?sn=clientType',panelHeight:'auto'"/>
                            </td>
                        </tr>
                        <tr>
                            <td>所在班级:</td>
                            <td><input type="text" class="easyui-textbox easyui-combobox"
                                       name="potentitalStudent.classGrade.id" readonly
                                       data-options="valueField:'id',textField:'name',
                                   url:'/classGrade/selectAll.do',panelHeight:'auto'"/>
                            </td>
                            <td>营销人员:</td>
                            <td><input id="saleMan" type="text" readonly name="potentitalStudent.employee.username"
                                       class="easyui-textbox"/></td>
                            <td>所在校区:</td>
                            <td><input type="text" class="easyui-textbox easyui-combobox"
                                       name="potentitalStudent.campus.id" readonly
                                       data-options="valueField:'id',textField:'name',
                                   url:'/systemdictionaryitem/selectItemByParentSn.do?sn=campus',panelHeight:'auto'"/>
                            </td>
                        </tr>
                        <tr>
                            <td>入学时间:</td>
                            <td><input type="date" name="enrolTime" class="easyui-datebox easyui-validatebox"/></td>
                        </tr>
                    </div>
                    <div id="paymentInfo">
                        <tr>
                            <td><b style="color: #0ca3d2">缴费信息</b></td>
                        </tr>
                        <tr>
                            <td>计划学费:</td>
                            <td><input type="number" disabled name="payment.planFee" class="easyui-textbox"/></td>
                            <td>优惠金额:</td>
                            <td><input type="number" disabled name="payment.reduceFee" class="easyui-textbox"/></td>
                            <td>其他优惠:</td>
                            <td><input type="number" disabled name="payment.otherDiscount" class="easyui-textbox"/></td>
                        </tr>
                        <tr>
                            <td>其他费用:</td>
                            <td><input type="number" disabled name="payment.otherFee" class="easyui-textbox"/></td>
                            <td>优惠说明:</td>
                            <td><input type="text" disabled name="payment.reduceExplanation" class="easyui-textbox"/>
                            </td>
                            <td>总费用:</td>
                            <td><input type="number" disabled name="payment.totalFee" class="easyui-textbox"/></td>
                        </tr>
                        <tr>
                            <td>已付学费:</td>
                            <td><input type="number" disabled name="payment.paidupFee" class="easyui-textbox"/></td>
                            <td>还欠学费:</td>
                            <td><input type="number" disabled name="payment.fee2pay" class="easyui-textbox"/></td>
                            <td>付款方式:</td>
                            <td><input type="text" disabled class="easyui-textbox easyui-combobox"
                                       name="payment.paymentType.id"
                                       data-options="valueField:'id',textField:'name',
                                   url:'/systemdictionaryitem/selectItemByParentSn.do?sn=size',panelHeight:'auto'"/>
                            </td>
                        </tr>
                    </div>
                    </tbody>
                </table>
            </div>
            <div id="detailInfo" title="私密信息" data-options="closable:true"
                 style="overflow:auto;padding:5px;display:none;">
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
                <HR width="100%" color="white" SIZE=2>
                <table align="center" cellpadding="5px">
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
                        <td colspan="3"><textarea name="detailInfo.workExperience"/></td>
                    </tr>
                </table>
            </div>
        </div>
    </form>
</div>
</body>
</html>
