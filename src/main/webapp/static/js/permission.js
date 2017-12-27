/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var permission_datagrid = $("#permission_datagrid");
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //删除按钮事件
        remove: function () {
            //判断用户是否选中数据
            var row = permission_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //发送ajax请求
            $.get('/permission/delete.do', {id: row.id}, function (data) {
                if (data.success) {
                    //弹出提示框
                    $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                        //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                        permission_datagrid.datagrid('reload');
                    });
                } else {
                    //失败弹出提示
                    $.messager.alert('温馨提示', data.msg, 'error');
                }
            })
        },
        //加载权限按钮事件
        reloads: function () {
            $.messager.confirm('确认', '您确认要重新加载权限吗？', function (r) {
                if (r) {
                    //发送一个ajax请求
                    $.get("/permission/reload.do",function (data) {
                        if (data.success) {
                            //弹出提示框
                            $.messager.alert('温馨提示', '加载成功！', 'info', function () {
                                //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                                permission_datagrid.datagrid('reload');
                            });
                        } else {
                            //失败弹出提示
                            $.messager.alert('温馨提示', data.msg, 'error');
                        }
                    });
                }
            });
        }
    }
    //3.页面加载完毕统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该方法要执行的方法
        var method = $(this).data("cmd");
        methodObj[method]();
    });

    //1.获取到表格进行初始化,添加属性
    permission_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: "/permission/query.do",
        pageSize:20,
        pageList:[15,20,25,30,35],
        columns: [[
            {field: 'name', title: '权限名称', width: 100},
            {field: 'resource', title: '权限表达式', width: 100},
        ]],
        toolbar: "#permission_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true
    })
})

