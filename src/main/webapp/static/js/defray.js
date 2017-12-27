/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var defray_form = $("#defray_form");
    var defray_datagrid = $("#defray_datagrid");
    var defray_edit_dialog = $("#defray_edit_dialog");
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //编辑按钮事件
        edit: function () {
            //隐藏审核人input
            $("#auditor_input").hide();
            $("#handMan_input").hide();
            $("#billnumber_input").hide();
            $("#defrayTime").hide();
            //判断用户是否选中数据;
            var row = defray_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //先清空表
            defray_form.form('clear');
            if (row.applicant) {
                row['applicant.id'] = row.applicant.id;
            }
            if (row.handMan) {
                row['handMan.id'] = row.handMan.id;
            }
            if (row.paymentType) {
                row['paymentType.id'] = row.paymentType.id;
            }
            if (row.defrayType) {
                row['defrayType.id'] = row.defrayType.id;
            }
            if (row.classGrade) {
                row['classGrade.id'] = row.classGrade.id;
            }
            //回显
            defray_form.form('load', row);
            //设置标题
            defray_edit_dialog.dialog('setTitle', '编辑支出');
            //打开弹出框
            defray_edit_dialog.dialog('open');
            /*   //显示按钮
               $("#changeClass_tbsc a").show();*/
        },
        //新增支出
        add: function () {
            //隐藏审核人input
            $("#auditor_input").hide()
            $("#handMan_input").hide()
            $("#billnumber_input").hide()
            $("#defrayTime").hide()
            //先清空表
            defray_form.form('clear');
            //设置标题
            defray_edit_dialog.dialog('setTitle', '新增支出');
            //打开弹出框
            defray_edit_dialog.dialog('open');
        },
        //刷新按钮事件
        reloads: function () {
            $("#keyword").textbox("setValue","");
            $("#beginDate").textbox("setValue","");
            $("#endDate").textbox("setValue","");
            $("#classgradeId").textbox("setValue","");
            defray_datagrid.datagrid('reload', {
                paymentType: null,
                auditState: null,
                beginDate:null,
                endDate:null,
                keyword:null,
                classgradeId:null,
            });
        },

        //编辑保存操作
        save: function () {
            defray_form.form('submit', {
                url: "/defray/saveOrUpdate.do",
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        defray_edit_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            defray_datagrid.datagrid('reload');
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
            defray_edit_dialog.dialog('close');
        },
        //删除事件
        remove: function () {
            //判断用户是否选中数据
            var row = defray_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            $.messager.confirm('确认', '您确认想要删除此数据吗？', function (r) {
                if (r) {
                    $.get("/defray/delete.do", {id: row.id}, function (data) {
                        if (data.success) {
                            //弹出提示框
                            $.messager.alert('温馨提示', '删除成功！', 'info', function () {
                                //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                                defray_datagrid.datagrid('reload');
                            });
                        } else {
                            //失败弹出提示
                            $.messager.alert('温馨提示', data.msg, 'error');
                        }
                    })
                }
            })
        },
        //查看按钮事件
        look: function () {

            //判断用户是否选中数据
            var row = defray_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //先清空表
            defray_form.form('clear');
            if (row.applicant) {
                row['applicant.id'] = row.applicant.id;
            }
            if (row.handMan) {
                row['handMan.id'] = row.handMan.id;
            }
            if (row.paymentType) {
                row['paymentType.id'] = row.paymentType.id;
            }
            if (row.defrayType) {
                row['defrayType.id'] = row.defrayType.id;
            }
            if (row.classGrade) {
                row['classGrade.id'] = row.classGrade.id;
            }
            if (row.auditor) {
                row['auditor.id'] = row.auditor.id;
            }
            //回显
            defray_form.form('load', row);
            defray_form.prop("disabled", true);

            //设置标题
            defray_edit_dialog.dialog('setTitle', '查看员工');
            defray_form.prop("readonly", true);
            //显示审核人input
            $("#auditor_input").show()
            $("#handMan_input").show()
            $("#billnumber_input").show()
            $("#defrayTime").show()
            //打开弹出框
            defray_edit_dialog.dialog('open');
        },

        //导出按钮事件
        exportXls: function () {
            window.location.href = "/defray/export.do";
        },

        //审核
        changeState: function () {
            //获取当前行对象id
            //判断用户是否选中数据
            var row = defray_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            console.log(row)
            $.messager.confirm('确认', '您确认想要是审核此数据吗？', function (r) {
                if (r) {
                    $.get("/defray/changeState.do", {id: row.id}, function (data) {
                        if (data.success) {
                            //弹出提示框
                            $.messager.alert('温馨提示', '审核成功！', 'info', function () {
                                //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                                defray_datagrid.datagrid('reload');
                            });
                        } else {
                            //失败弹出提示
                            $.messager.alert('温馨提示', data.msg, 'error');
                        }
                    })
                }
            })
        },
        //高级查询
        searchForm: function () {
            //获取关键字input
            var keyword = $("#keyword").textbox("getValue");
            var beginDate = $("#beginDate").textbox("getValue");
            var endDate = $("#endDate").textbox("getValue");
            var classgradeId = $("#classgradeId").textbox("getValue");
            //让数据表格重新加载比并将查询数据带过去
            defray_datagrid.datagrid('load', {
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
    defray_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: "/defray/query.do",
        pageSize:20,
        pageList:[15,20,25,30,35],
        columns: [[
            {field: 'defrayTime', title: '支出时间', width: 170,align: 'center'},
            {field: 'defrayMoney', title: '支出金额', width: 100,align: 'center'},
            {field: 'remark', title: '支出摘要', width: 100,align: 'center'},
            {
                field: 'applicant.username', title: '申请人', width: 100, align: 'center',formatter: function (value, row, index) {
                    return row.applicant ? row.applicant.username : '';
                }
            },
            {
                field: 'handMan.username', title: '经手人', width: 100,align: 'center', formatter: function (value, row, index) {
                    return row.handMan ? row.handMan.username : '';
                }
            },
            {
                field: 'paymentType.name', title: '支付方式', width: 80,align: 'center', formatter: function (value, row, index) {
                    return row.paymentType ? row.paymentType.name : '';
                }
            },
            {field: 'billnumber', title: '单据号', align: 'center',width: 120},
            {
                field: 'defrayType.name', title: '支出类型', width: 100,align: 'center', formatter: function (value, row, index) {
                    return row.defrayType ? row.defrayType.name : '';
                }
            },
            {
                field: 'classGrade.name', title: '所属班级', width: 100,align: 'center', formatter: function (value, row, index) {
                    return row.classGrade ? row.classGrade.name : '';
                }
            },
            {
                field: 'auditor.username', title: '审核人', width: 100,align: 'center', formatter: function (value, row, index) {
                    return row.auditor ? row.auditor.username : '';
                }
            },
            {
                field: 'auditState', title: '审核状态', width: 100,align: 'center', formatter: function (value, row, index) {
                    return row.auditState ? "<font color='#5f9ea0'>已审核</font>" : "<font color=#a52a2a>未审核</font>";
                }
            }
        ]],
        toolbar: "#defray_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        onClickRow: function (index, row) {
            if (row.auditState) {
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
            if (row.auditState) {
                //禁用审核按钮
                $("#changeState").linkbutton("disable");
                //禁用编辑按钮和删除按钮,打开查看按钮
                $("#edit,#remove").linkbutton("disable");
                $("#look").linkbutton("enable");
                $("")
            } else {
                //打开按扭审核按钮
                $("#changeState").linkbutton("enable");
                //打开编辑按钮和删除按钮,禁用查看按钮
                $("#edit,#remove").linkbutton("enable");
                $("#look").linkbutton("disable");
            }

        }
    })
    /*初始化编辑弹出框*/
    defray_edit_dialog.dialog({
        title: '支出操作',
        width: 300,
        height: 400,
        buttons: '#defray_tbsc',
        closed: true
    });
    /*快速排序事件js*/
    $('#mm').menu({
        onClick: function (item) {
            var auditState = null;
            var paymentType = null;
            console.log(item.text);
            if (item.text == "学员退款") {
                paymentType = 100;
            }
            if (item.text == "教学花费") {
                paymentType = 101;
            }
            if (item.text == "团队建设") {
                paymentType = 102;
            }
            if (item.text == "员工工资") {
                paymentType = 110;
            }
            if (item.text == "其它类型") {
                paymentType = 103;
            }
            if (item.text == "已审核") {
                auditState = 1;
            }
            if (item.text == "未审核") {
                auditState = 0;
            }
            defray_datagrid.datagrid('load', {
                paymentType: paymentType,
                auditState: auditState
            });

        }
    });



})

