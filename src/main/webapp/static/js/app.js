/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var wastages_form = $("#wastages_form");
    var wastageStu_datagrid = $("#wastageStu_datagrid");
    var wastage_edit_dialog = $("#wastage_edit_dialog");
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //新增绑定事件
        /*   add: function () {
               //增加页面显示密码框
               $("#password_tr").show();
               //清空表单数据
               dept_form.form('clear');
               //设置标题
               wastageStu_datagrid.dialog('setTitle', '新增部门');
               //打开弹出框
               dept_dialog.dialog('open');
           },*/
        //编辑按钮事件
        edit: function () {
            //判断用户是否选中数据
            var row = wastageStu_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!",'info');
                return;
            }
            //先清空表
            wastages_form.form('clear');
            if (row.dept) {
                row['dept.id'] = row.dept.id;
            }
            //回显
            wastages_form.form('load', row);
            //设置标题
            wastage_edit_dialog.dialog('setTitle', '编辑员工');
            //打开弹出框
            wastage_edit_dialog.dialog('open');
            /*   //显示按钮
               $("#changeClass_tbsc a").show();*/
        },
        //部门状态事件
        /*    changeState: function () {
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
            },*/
        //刷新按钮事件
        reloads: function () {
            wastageStu_datagrid.datagrid('reload');
        },

        //保存操作
        save: function () {
            wastages_form.form('submit', {
                url: "/wastageStudent/saveOrUpdate.do",
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        wastage_edit_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            wastageStu_datagrid.datagrid('reload');
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
            wastage_edit_dialog.dialog('close');
        }
    }
    //3.页面加载完毕统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该方法要执行的方法
        var method = $(this).data("cmd");
        methodObj[method]();
    });

    //1.获取到表格进行初始化,添加属性
    wastageStu_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: "/wastageStudent/query.do",
        pageSize:20,
        pageList:[15,20,25,30,35],
        columns: [[
            {field: 'name', title: '学员姓名', width: 100},
            {
                field: 'saleman.username', title: '营销人员', width: 100, formatter: function (value, row, index) {
                    return row.saleman ? row.saleman.username : '';
                }
            },
            {field: 'tel', title: '电话', width: 100},
            {
                field: 'refundState', title: '退款状态', width: 100, formatter: function (value, row, index) {
                    if (row.refundState) {
                        if (row.refundState == 1) {
                            return "<font color='#32cd32'>已退款</font>"
                        } else if (row.refundState == 0) {
                            return "<font color='#ff4500'>退款中</font>"
                        } else if (row.refundState == -1) {
                            return "无需退款"
                        }
                    } else {
                        return "未办理手续"
                    }
                }
            },
            {field: 'reason', title: '流失原因', width: 100},
            {
                field: 'classgrade.name', title: '班级', width: 100, formatter: function (value, row, index) {
                    return row.classgrade ? row.classgrade.name : '';
                }
            },
            {field: 'studyDaysNum', title: '已上课天数', width: 100},
            {field: 'wastageDate', title: '流失日期', width: 100},
            {
                field: 'auditState', title: '审核状态', width: 100, formatter: function (value, row, index) {
                    return row.auditState ? "<font color='#5f9ea0'>已审核</font>" : "<font color=#a52a2a>未审核</font>";
                }
            },
            {
                field: 'operator.username', title: '营销人员', width: 100, formatter: function (value, row, index) {
                    return row.operator ? row.operator.username : '';
                }
            }
        ]],
        toolbar: "#wastageStu_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true/*,
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
            /!*
             if(!row.state){
             //禁用离职按钮
             $("#changeState").linkbutton("disable");
             }else{
             //打开按
             $("#changeState").linkbutton("enable");
             }
             *!/
        }*/
    })
    /*初始化编辑弹出框*/
    wastage_edit_dialog.dialog({
        title: '转班操作',
        width: 300,
        height: 400,
        // buttons: '#wastageStu_tbsc',
        closed: true
    });
})

