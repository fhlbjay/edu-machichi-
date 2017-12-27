<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>easyUI</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="/static/js/menu.js"></script>
</head>
<body>
<div id="menu_tb">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" data-cmd="remove">删除</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reloads">刷新</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-up',plain:true" data-cmd="reloads">上级菜单</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-down',plain:true" data-cmd="reloads">下级菜单</a>
</div>

<table id="menu_datagrid"></table>
<!--弹出框保存/取消-->
<div id="menu_tbsc">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<!--表单弹出框-->
<div id="menu_dialog">
    <!--弹出框的内容-->
    <form id="menu_form" method="post">
        <%--编辑隐藏id--%>
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 10px">
            <tbody>
            <tr>
                <td>菜单名称:<input type="text" name="text" class="easyui-textbox"/></td>

            </tr>
            <tr>
                <td>URL&ensp;&ensp;&ensp;&ensp;:<input type="text" name="url" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>父级ID&ensp;&nbsp;:<input type="text" name="parent_id" class="easyui-textbox"/></td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
