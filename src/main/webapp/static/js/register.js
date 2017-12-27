/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var register_form = $("#register_form");
    var register_datagrid = $("#register_datagrid");
    var register_dialog = $("#register_dialog");
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //新增绑定事件
        add: function () {
            //清空表单数据
            register_form.form('clear');
            //设置标题
            register_dialog.dialog('setTitle', '新增考试');
            //打开弹出框
            register_dialog.dialog('open');
        },
        //编辑按钮事件
        edit: function () {
            //判断用户是否选中数据
            var row = register_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //清空表单数据
            register_form.form('clear');
            row['classGrade.id']=row.classGrade.id;
            row['employee.id']=row.employee.id;
            //回显
            register_form.form('load', row);
            //设置标题
            register_dialog.dialog('setTitle', '编辑考试');
            //打开弹出框
            register_dialog.dialog('open');
        },
        //考试状态事件
        changeState: function () {
            //判断用户是否选中数据
            var row = register_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            $.messager.confirm('确认', '您确认想要执行该操作吗？', function (r) {
                if (r) {
                    //发送一个ajax请求
                    $.get("/register/changeState.do", {id: row.id}, function (data) {
                        if (data.success) {
                            //弹出提示框
                            $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                                //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                                register_datagrid.datagrid('reload');
                            });
                        } else {
                            //失败弹出提示
                            $.messager.alert('温馨提示', data.msg, 'error');
                        }
                    });
                }
            });
        },
        //刷新按钮事件
        reloads: function () {
            $("#potentitalStudent").textbox("setValue","");
            register_datagrid.datagrid('reload', {
                name:null,
            });
        },

        //保存操作
        save: function () {
            register_form.form('submit', {
                url: "/register/saveOrUpdate.do",
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        register_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            register_datagrid.datagrid('reload');
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
            register_dialog.dialog('close');
        },
        //高级查询
        searchForm: function () {
            //获取关键字input
            var name = $("#potentitalStudent").textbox("getValue");
            //让数据表格重新加载比并将查询数据带过去
            register_datagrid.datagrid('load', {
                name: name,
            });
        },
        //删除按钮事件
        remove:function () {
            //判断用户是否选中数据
            var row = register_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //发送ajax请求
            $.get('/register/delete.do', {id: row.id}, function (data) {
                if (data.success) {
                    //成功关闭窗口
                   register_dialog.dialog('close');
                    //弹出提示框
                    $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                        //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                        register_datagrid.datagrid('reload');
                    });
                } else {
                    //失败弹出提示
                    $.messager.alert('温馨提示', data.msg, 'error');
                }
            })
        },
        //导出按钮事件
        exportXls: function () {
            window.location.href = "/register/export.do";
        },
        //文件上传按钮事件
        inputXls: function () {
            $("#fileInput").dialog('setTitle', '文件上传');
            //打开弹出框
            $("#fileInput").dialog('open');
        },

    }
    //3.页面加载完毕统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该方法要执行的方法
        var method = $(this).data("cmd");
        methodObj[method]();
    });

    //1.获取到表格进行初始化,添加属性
    register_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: "/register/query.do",
        pageSize:20,
        pageList:[15,20,25,30,35],
        columns: [[
            {field: 'name', title: '姓名', width: 100},
            {field: 'employee', title: '营销人员', width: 100, formatter: function (value, row, index) {
                return value.username;
            }
            },
            {field: 'qq', title: 'qq', width: 100},
            {field: 'tel', title: '电话', width: 100},
            {field: 'classGrade', title: '意向班级', width: 100, formatter: function (value, row, index) {
                return value.name;
            }
            },
            {field: 'testTime', title: '考试时间', width: 100},
            {
                field: 'testResult', title: '考试结果', width: 100, formatter: function (value, row, index) {
                return value ? "通过" : "未通过";
            }
            },
            {
                field: 'state', title: '审核结果', width: 100, formatter: function (value, row, index) {
                return value ? "已审核" : "未审核";
            }
            },
            {field: 'remark', title: '备注', width: 100}
        ]],
        toolbar: "#register_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        onClickRow: function (index, row) {
            if (!row.state) {
                //修改按钮值
                $("#changeState").linkbutton({
                   text: "审核通过"
                })
            } else {
                //修改按钮值
                $("#changeState").linkbutton({
                   text: "审核不通过"
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
    register_dialog.dialog({
        title: '考试编辑',
        width: 300,
        height: 360,
        buttons: '#register_tbsc',
        closed: true
    })

})

