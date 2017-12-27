/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var dept_form = $("#dept_form");
    var dept_datagrid = $("#dept_datagrid");
    var dept_dialog = $("#dept_dialog");
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //新增绑定事件
        add: function () {
            //增加页面显示密码框
            $("#password_tr").show();
            //清空表单数据
            dept_form.form('clear');
            //设置标题
            dept_dialog.dialog('setTitle', '新增部门');
            //打开弹出框
            dept_dialog.dialog('open');
        },
        //编辑按钮事件
        edit: function () {
            //判断用户是否选中数据
            var row = dept_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //编辑页面隐藏密码框
            $("#password_tr").hide();
            //清空表单数据
            dept_form.form('clear');
            //把部门id设置到部门的dept.id属性上
            //row['dept.id'] = row.dept.id;
            //回显
            dept_form.form('load', row);
            //设置标题
            dept_dialog.dialog('setTitle', '编辑部门');
            //打开弹出框
            dept_dialog.dialog('open');
        },
        //部门状态事件
        changeState: function () {
            //判断用户是否选中数据
            var row = dept_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            $.messager.confirm('确认', '您确认想要执行该操作吗？', function (r) {
                if (r) {
                    //发送一个ajax请求
                    $.get("/department/changeState.do", {id: row.id}, function (data) {
                        if (data.success) {
                            //弹出提示框
                            $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                                //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                                dept_datagrid.datagrid('reload');
                            });
                        } else {
                            //失败弹出提示
                            $.messager.alert('温馨提示', data.msg, 'error');
                        }
                    });
                }
            });
        },
        //删除按钮事件
        remove:function () {
        //判断用户是否选中数据
        var row = dept_datagrid.datagrid('getSelected');
        if (!row) {
            $.messager.alert('温馨提示', "请选中一条数据!", 'info');
            return;
        }
        //发送ajax请求
        $.get('/department/delete.do', {id: row.id}, function (data) {
            if (data.success) {
                //成功关闭窗口
                dept_dialog.dialog('close');
                //弹出提示框
                $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                    //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                    dept_datagrid.datagrid('reload');
                });
            } else {
                //失败弹出提示
                $.messager.alert('温馨提示', data.msg, 'error');
            }
        })
    },
        //刷新按钮事件
        reloads: function () {
            dept_datagrid.datagrid('reload');
        },

        //保存操作
        save: function () {
            dept_form.form('submit', {
                url: "/department/saveOrUpdate.do",
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        dept_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            dept_datagrid.datagrid('reload');
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
            dept_dialog.dialog('close');
        }
    }
    //3.页面加载完毕统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该方法要执行的方法
        var method = $(this).data("cmd");
        methodObj[method]();
    });

    //1.获取到表格进行初始化,添加属性
    dept_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: "/department/query.do",
        pageSize:20,
        pageList:[15,20,25,30,35],
        columns: [[
            {field: 'sn', title: '编号', width: 100},
            {field: 'name', title: '部门名称', width: 100},
            {
                field: 'state', title: '状态', width: 100, formatter: function (value, row, index) {
                return value ? "存在" : "<font color='red'>撤销</font>";
            }
            }
        ]],
        toolbar: "#dept_tb",
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
            /*
             if(!row.state){
             //禁用离职按钮
             $("#changeState").linkbutton("disable");
             }else{
             //打开按
             $("#changeState").linkbutton("enable");
             }
             */
        }
    })
    /*初始化弹出框*/
    dept_dialog.dialog({
        title: '部门编辑',
        width: 300,
        height: 150,
        buttons: '#dept_tbsc',
        closed: true
    })
})

