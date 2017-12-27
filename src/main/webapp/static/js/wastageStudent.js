/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var wastage_form = $("#wastage_form");
    var wastage_refund_form = $("#wastage_refund_form");
    var wastageStu_datagrid = $("#wastageStu_datagrid");
    var wastage_edit_dialog = $("#wastage_edit_dialog");
    var wastage_refund_dialog = $("#wastage_refund_dialog");
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //编辑按钮事件
        edit: function () {
            //判断用户是否选中数据
            var row = wastageStu_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //先清空表
            wastage_form.form('clear');
            if (row.saleman) {
                row['saleman.username'] = row.saleman.username;
            }
            if (row.classgrade) {
                row['classgrade.name'] = row.classgrade.name;
            }
            if (row.operator) {
                row['operator.username'] = row.operator.username;
            }
            //回显
            wastage_form.form('load', row);
            //设置标题
            wastage_edit_dialog.dialog('setTitle', '编辑员工');
            //打开弹出框
            wastage_edit_dialog.dialog('open');
            /*   //显示按钮
               $("#changeClass_tbsc a").show();*/
        },
        //刷新按钮事件
        reloads: function () {
            $("#keyword").textbox("setValue","");
            $("#beginDate").textbox("setValue","");
            $("#endDate").textbox("setValue","");
            $("#classgradeId").textbox("setValue","");
            wastageStu_datagrid.datagrid('reload', {
                beginDate:null,
                endDate:null,
                keyword:null,
                classgradeId:null,
            });
        },

        //编辑保存操作
        save: function () {
            wastage_form.form('submit', {
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
        //退款保存操作
        refund_save: function () {
            wastage_refund_form.form('submit', {
                url: "/wastageStudent/refund.do",
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        wastage_refund_dialog.dialog('close');
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
        //编辑取消按钮事件
        cancel: function () {
            //关闭弹出框
            wastage_edit_dialog.dialog('close');
        },
        //查看按钮事件
        look: function () {
            //判断用户是否选中数据
            var row = wastageStu_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //先清空表
            wastage_form.form('clear');
            if (row.saleman) {
                row['saleman.username'] = row.saleman.username;
            }
            if (row.classgrade) {
                row['classgrade.name'] = row.classgrade.name;
            }
            if (row.operator) {
                row['operator.username'] = row.operator.username;
            }
            //回显
            wastage_form.form('load', row);
            wastage_form.prop("disabled", true)
            //设置标题
            wastage_edit_dialog.dialog('setTitle', '查看员工');
            //打开弹出框
            wastage_edit_dialog.dialog('open');
        },
        //退款取消按钮事件
        refund_cancel: function () {
            //关闭弹出框
            wastage_refund_dialog.dialog('close');
        },
        //审核
        changeState: function () {
            //获取当前行对象id
            //判断用户是否选中数据
            var row = wastageStu_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            console.log(row)
            $.messager.confirm('确认', '您确认想要审核此数据吗？', function (r) {
                if (r) {
                    $.get("/wastageStudent/changeState.do", {id: row.id}, function (data) {
                        if (data.success) {
                            //弹出提示框
                            $.messager.alert('温馨提示', '审核成功！', 'info', function () {
                                //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                                wastageStu_datagrid.datagrid('reload');
                            });
                        } else {
                            //失败弹出提示
                            $.messager.alert('温馨提示', data.msg, 'error');
                        }
                    })
                }
            })
        },
        //退款
        refund: function () {
            //判断用户是否选中数据
            var row = wastageStu_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            row['applicant.username'] = row.name;
            row['applicant.id'] = row.id;
            $('#defrayTime').val(1);

            if (row.classgrade) {
                row['classGrade.name'] = row.classgrade.name;
                row['classGrade.id'] = row.classgrade.id;
            }
            //先清空表
            wastage_refund_form.form('clear');
            wastage_refund_form.form("load", row)
            //设置标题
            wastage_refund_dialog.dialog('setTitle', '退款');
            //打开弹出框
            wastage_refund_dialog.dialog('open');
        },

        //高级查询
        searchForm: function () {
            //获取关键字input
            var keyword = $("#keyword").textbox("getValue");
            var beginDate = $("#beginDate").textbox("getValue");
            var endDate = $("#endDate").textbox("getValue");
            var classgradeId = $("#classgradeId").textbox("getValue");
            //让数据表格重新加载比并将查询数据带过去
            wastageStu_datagrid.datagrid('load', {
                beginDate: beginDate,
                endDate: endDate,
                keyword: keyword,
                classgradeId: classgradeId
            });
        },
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
            {field: 'name', title: '学员姓名', align: 'center',width: 100},
            {
                field: 'saleman.username', title: '营销人员',align: 'center', width: 100, formatter: function (value, row, index) {
                    return row.saleman ? row.saleman.username : '';
                }
            },
            {field: 'tel', title: '电话', align: 'center',width: 125},
            {
                field: 'refundState', title: '退款状态', align: 'center',width: 100, formatter: function (value, row, index) {
                    return row.refundState ? "<font color='#5f9ea0'>已退款</font>" : "<font color=#a52a2a>退款中</font>";

                }
            },
            {field: 'reason', title: '流失原因',align: 'center', width: 150},
            {field: 'refundMoney', title: '未退款金额',align: 'center', width: 100},
            {
                field: 'classgrade.name', title: '班级',align: 'center', width: 100, formatter: function (value, row, index) {
                    return row.classgrade ? row.classgrade.name : '';
                }
            },
            {field: 'studyDaysNum', title: '已上课天数', align: 'center',width: 100},
            {field: 'wastageDate', title: '流失日期',align: 'center', width: 100},
            {field: 'enrolTime', title: '入学日期',align: 'center', width: 100},
            {
                field: 'auditState', title: '审核状态',align: 'center', width: 100, formatter: function (value, row, index) {
                    return row.auditState ? "<font color='#5f9ea0'>已审核</font>" : "<font color=#a52a2a>未审核</font>";
                }
            },
            {
                field: 'operator.username', title: '经办人', align: 'center',width: 100, formatter: function (value, row, index) {
                    return row.operator ? row.operator.username : '';
                }
            }
        ]],
        toolbar: "#wastageStu_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        onClickRow: function (index, row) {
            if (row.auditState == true) {
                //修改按钮值
                $("#changeState").linkbutton({
                    text: "已审核"
                })
            } else {
                //修改按钮值
                $("#changeState").linkbutton({
                    text: "审核"
                })
            }
            if (row.auditState == true) {
                //禁用审核按钮
                $("#changeState").linkbutton("disable");
                //禁用编辑按钮和删除按钮,打开查看按钮
                $("#refund").linkbutton("disable");
                $("#look").linkbutton("enable");
            } else {
                //打开按扭审核按钮
                $("#changeState").linkbutton("enable");
                //打开编辑按钮和删除按钮,禁用查看按钮
                $("#refund").linkbutton("enable");
                $("#look").linkbutton("disable");
            }
        }
    })
    /*初始化编辑弹出框*/
    wastage_edit_dialog.dialog({
        title: '转班操作',
        width: 300,
        height: 400,
        buttons: '#wastageStu_tbsc',
        closed: true
    });
    /*初始化退款弹出框*/
    wastage_refund_dialog.dialog({
        title: '退款操作',
        width: 300,
        height: 400,
        buttons: '#wastageStu_refund_tbsc',
        closed: true
    });

    //
    $("#defrayMoney").textbox({
        onChange: function () {
            var defrayMoney = $("#defrayMoney").textbox("getValue");
            var refundMoney = $("#refundMoney").textbox("getValue");
            if (parseInt(defrayMoney) > parseInt(refundMoney)) {
                $.messager.alert('温馨提示', '不能超过未退款金额！', 'error');
                $("#refund_save").linkbutton("disable")
            }
        }
    })
})

