/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var menu_form = $("#menu_form");
    var menu_datagrid = $("#menu_datagrid");
    var menu_dialog = $("#menu_dialog");
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //新增绑定事件
        add: function () {
            //清空表单数据
            menu_form.form('clear');
            //设置标题
            menu_dialog.dialog('setTitle', '新增菜单');
            //打开弹出框
            menu_dialog.dialog('open');
        },
        //编辑按钮事件
        edit: function () {
            //判断用户是否选中数据
            var row = menu_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }

            //清空表单数据
            menu_form.form('clear');
            //回显
            menu_form.form('load', row);
            //设置标题
            menu_dialog.dialog('setTitle', '编辑菜单');
            //打开弹出框
            menu_dialog.dialog('open');
        },
        //删除按钮事件
        remove: function () {
            //判断用户是否选中数据
            var row = menu_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //发送ajax请求
            $.get('/menu/delete.do', {id: row.id}, function (data) {
                if (data.success) {
                    //成功关闭窗口
                    menu_dialog.dialog('close');
                    //弹出提示框
                    $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                        //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                        menu_datagrid.datagrid('reload');
                    });
                } else {
                    //失败弹出提示
                    $.messager.alert('温馨提示', data.msg, 'error');
                }
            })
        },
        //刷新按钮事件
        reloads: function () {
            menu_datagrid.datagrid('reload');
        },

        //保存操作
        save: function () {
            menu_form.form('submit', {
                url: "/menu/saveOrUpdate.do",
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        menu_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            menu_datagrid.datagrid('reload');
                        });
                    } else {
                        //失败弹出提示
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }
            });
        },
        //取消按钮事件
        cancel: function () {
            //关闭弹出框
            menu_dialog.dialog('close');
        }
    }
    //3.页面加载完毕统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该方法要执行的方法
        var method = $(this).data("cmd");
        methodObj[method]();
    });

    //1.获取到表格进行初始化,添加属性
    menu_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: "/menu/query.do",
        pageSize:20,
        pageList:[15,20,25,30,35],
        columns: [[
            {field: 'id', title: '菜单ID', width: 100},
            {field: 'text', title: '菜单名称', width: 100},
            {field: 'url', title: 'URL', width: 100},
            {field: 'parent_id', title: '父级菜单', width: 100},
        ]],
        toolbar: "#menu_tb",
        striped: true,
        pagination: true,
        singleSelect: true
    })
    /*初始化弹出框*/
    menu_dialog.dialog({
        title: '菜单编辑',
        width: 250,
        height: 200,
        buttons: '#menu_tbsc',
        closed: true
    })

})

