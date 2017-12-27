/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var sydt_datagrid = $("#sydt_datagrid");
    //3.页面加载完毕统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该方法要执行的方法
        var method = $(this).data("cmd");
        methodObj[method]();
    });

    //1.获取到表格进行初始化,添加属性
    sydt_datagrid.datagrid({
        fit: true,
        fitColumns:true,
        url: "/systemdictionary/query.do",
        onSelect: function (index, row) {
            var sn = row.sn;
            sydtitem_datagrid.datagrid(
                {url: "/systemdictionaryitem/selectItemByParentSn.do?sn=" + sn}
                )
        },
        columns: [[
            {field: 'sn', title: '数据字典编码', width: 100},
            {field: 'name', title: '数据字典名称', width: 100},
            {field: 'intro', title: '字典目录简介', width: 100}
        ]],
        striped: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        onClickRow: function (index, row) {
            if (!row.state) {
                //修改按钮值
                $("#changeState").linkbutton({
                    text: "存在"
                })
            } else {
                //修改按钮值
                $("#changeState").linkbutton({
                    text: "撤销"
                })
            }
        }
    })

    //1.变量抽取
    var sydtitem_form = $("#sydtitem_form");
    var sydtitem_datagrid = $("#sydtitem_datagrid");
    var sydtitem_dialog = $("#sydtitem_dialog");
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //新增绑定事件
        additem: function () {
            //增加页面显示密码框
            $("#password_tr").show();
            //清空表单数据
            sydtitem_form.form('clear');
            //设置标题
            sydtitem_dialog.dialog('setTitle', '新增数据字典');
            //打开弹出框
            sydtitem_dialog.dialog('open');
        },
        //编辑按钮事件
        edititem: function () {
            //判断用户是否选中数据
            var row = sydtitem_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //清空表单数据
            sydtitem_form.form('clear');
            //回显
            sydtitem_form.form('load', row);
            //设置标题
            sydtitem_dialog.dialog('setTitle', '编辑数据字典');
            //打开弹出框
            sydtitem_dialog.dialog('open');
        },
        //删除按钮事件
        removeitem: function () {
            //判断用户是否选中数据
            var row = sydtitem_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //发送ajax请求
            $.get('/systemdictionaryitem/delete.do', {id: row.id}, function (data) {
                if (data.success) {
                    //成功关闭窗口
                    sydtitem_dialog.dialog('close');
                    //弹出提示框
                    $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                        //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                        sydtitem_datagrid.datagrid('reload');
                    });
                } else {
                    //失败弹出提示
                    $.messager.alert('温馨提示', data.msg, 'error');
                }
            })
        },
        //刷新按钮事件
        reloadsitem: function () {
            sydtitem_datagrid.datagrid('reload');
        },

        //保存操作
        saveitem: function () {
            sydtitem_form.form('submit', {
                url: "/systemdictionaryitem/saveOrUpdate.do",
                onSubmit: function (param) {
                    //获取到下拉框所选的值
                    var row = sydt_datagrid.datagrid('getSelected');
                        //添加参数封装过去
                        param["parent.id"] = row.id;
                },
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        sydtitem_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            sydtitem_datagrid.datagrid('reload');
                        });
                    } else {
                        //失败弹出的提示
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }
            });
        },
        //取消按钮事件
        cancelitem: function () {
            //关闭弹出框
            sydtitem_dialog.dialog('close');
        }
    }
    //3.页面加载完毕统一绑定事件


    //1.获取到表格进行初始化,添加属性
    sydtitem_datagrid.datagrid({
        fit: true,
        fitColumns:true,
        //url: "/systemdictionaryitem/query.do",
        columns: [[
            {field: 'parent', title: '字典条目', width: 100,formatter: function (value, row, index) {
                return value ? value.name:"";}},
            {field: 'name', title: '字典明细名称', width: 100},
            {field: 'intro', title: '字典明细简介', width: 100}
        ]],
        toolbar: "#sydtitem_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        pageSize:20,
        pageList:[15,20,25,30,35],
        singleSelect: true,
        onClickRow: function (index, row) {
            if (!row.state) {
                //修改按钮值
                $("#changeState").linkbutton({
                    text: "存在"
                })
            } else {
                //修改按钮值
                $("#changeState").linkbutton({
                    text: "撤销"
                })
            }
        }
    })
    /*初始化弹出框*/
    sydtitem_dialog.dialog({
        title: '数据字典编辑',
        width: 300,
        height: 250,
        buttons: '#sydtitem_tbsc',
        closed: true
    })

})

