<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>教育</title>
    <%@include file="/static/common/common.jsp"%>
    <script src="/static/js/schoolContacts.js"></script>
</head>
<body>
<div id="schoolContacts_tb" >
	<!-- 待增加权限 -->
	<shiro:hasPermission name="schoolContacts:saveOrUptate">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
    </shiro:hasPermission>
	<shiro:hasPermission name="schoolContacts:delete">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-cmd="remove">删除</a>
    </shiro:hasPermission>
    <a id="singleSelect" class="easyui-linkbutton" data-options="iconCls:'icon-man',plain:true" data-cmd="singleSelect">查看</a>

    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>

    <div>
        <%--高级查询(所属学校未实现)--%>
	        模糊查询:	<input id="keyword" type="text" name="keyword" class="easyui-textbox" prompt="支持多关键词查询,以空格分隔.例如:关键词1 关键词2" style="width: 300px;"/>
	        生日:		<input id="beginDate" type="text" name="beginDate" class="easyui-textbox easyui-datebox"/>~
	        	<input id="endDate" type="text" name="endDate" class="easyui-textbox easyui-datebox"/>
	        所属学校:	<input id="schoolId" type="text" name="schoolId" class="easyui-textbox easyui-combobox"
	                  data-options="valueField:'id',textField:'name',url:'/universitytrace/selectAll.do',panelHeight:'auto'"/>
	        	<a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">搜索</a>
    </div>
</div>
<table id="schoolContacts_datagrid" ></table>
<!--弹出框保存/取消-->
<div id="schoolContacts_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reset',plain:true" data-cmd="reset">重置</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<!--表单弹出框-->
<div id="schoolContacts_dialog">
    <!--弹出框的内容-->
    <form id="schoolContacts_form" method="post"  style="width:780px;height:320px;">
    	<!-- 编辑隐藏id -->
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 10px; width:90%">
        	<tbody>
        		<tr>
	            	<td><span style="font-size: 20px;">基本信息</span></td>
	            </tr>
        		<tr>
	                <td>姓名:</td>
	                <td><input type="text" name="contactsName" class="easyui-textbox" prompt="姓名"/></td>
	                <!-- 合作院校/大客户的学校名称(所属学校应是从大客户/合作院校查询出来的) -->
	                <td>所属学校:</td>
	                <td><input type="text" name="bigCustomer.id" class="easyui-textbox easyui-combobox"
	                		data-options="valueField:'id', textField:'name', url:'/universitytrace/selectAll.do', panelHeight:'auto'"/></td>
	                <td>称呼:</td>
	                <td><input type="text" name="contactsCallName" class="easyui-textbox" placeholder="称呼"/></td>
        		</tr>

	            <tr>
	            	<td>联系电话:</td>
	                <td><input type="text" name="contactsTel" class="easyui-textbox"/></td>
	                <td>QQ:</td>
	                <td><input type="text" name="contactsQQ" class="easyui-textbox"/></td>
	            	<!-- 未添加Email判断 -->
	                <td>联系邮箱:</td>
                    <td><input class="easyui-validatebox easyui-textbox" name="contactsEmail"
                               data-options="validType:'email'"
                    /></td>

	            </tr>
	            <tr>
	                <td>主联系人:</td>
	                <td>
                    	<input type="radio" checked="checked" name="contactsMain" value="true" />是
						<input type="radio" name="contactsMain" value="false" />否
					</td>
	            </tr>
	            <tr>
	            	<td><span style="font-size: 20px;">其他信息</span></td>
	            </tr>
	            <tr>
	                <td>职务:</td>
	                <td><input type="text" name="contactsPosition" class="easyui-textbox"/></td>
	                <td>所属院系:</td>
	                <td><input type="text" name="contactsCollege" class="easyui-textbox"/></td>
	                <td>所属部门:</td>
	                <td><input type="text" name="contactsDepartment" class="easyui-textbox"/></td>
	            </tr>
        		<tr>
        			<td>性别:</td>
	                <td>
	                	<select class="easyui-combobox" name="contactsGender" promp="请选择" panelHeight="auto" style="width: 147px;" >
	                		<option value="1">男</option>
	                		<option value="0">女</option>
	                	</select>
	                </td>
	                <td>生日:</td>
	                <td><input type="text" name="contactsBirthday" class="easyui-textbox easyui-datebox"/></td>
	                <td>地址:</td>
	                <td><input type="text" name="contactsAddress" class="easyui-textbox"/></td>
        		</tr>
        		<!-- 跨3列,为添加这个  -->
        		<tr >
	                <td>简介:</td>
	                <td colspan="5"><textarea placeholder="简要说明" name="contactsIntro"></textarea>
	            </tr>
	        </tbody>
        </table>
    </form>
</div>
</body>
</html>
