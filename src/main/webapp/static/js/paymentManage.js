/*页面加载完毕之后操作*/
$(function () {
    function deepGet(row, path) {
        if (row == null) {
            return;
        }
        var arr = path.split(".");
        var temp = "";
        for (var i = 0; i < arr.length; i++) {
            temp = temp + arr[i];
            if (eval(temp) == null) {
                return "";
            }
            temp = temp + "."
        }
        return eval(path);
    }

    $("#stuName").combobox({
        onChange: function () {
            //发送ajax请求
            $.get('/officialStudent/selectByPsId.do', {id: $(this).val()}, function (data) {
                if (data.id) {
                    $("#stuClass").textbox({
                        value: data.potentitalStudent.classGrade.name
                    });
                    $("#fee2pay").textbox({
                        value: data.payment.fee2pay
                    });
                    $("#saleMan").textbox({
                        value: data.potentitalStudent.employee.realname
                    });
                    $("#officialStudentId").val(data.id);
                    $.extend($.fn.validatebox.defaults.rules, {
                        less: {
                            validator: function (value) {
                                var fee2pay = $("#fee2pay").textbox("getValue");
                                return parseInt(value) <= parseInt(fee2pay);
                            },
                            message: '不能大于未交费用'
                        }
                    });
                }
            })
        }
    });


    //1.变量抽取
    var paymentManage_datagrid = $("#paymentManage_datagrid");
    var paymentManage_edit_dialog = $("#paymentManage_edit_dialog");
    var paymentManage_form = $("#paymentManage_form");
    var edit_button = $("#edit_button");
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //新增绑定事件
        add: function () {
            //清空表单数据
            paymentManage_form.form('clear');
            //设置标题
            paymentManage_edit_dialog.dialog('setTitle', '新增收款');
            //显示按钮
            $("#paymentManage_tbsc").show();
            //打开弹出框
            paymentManage_edit_dialog.dialog('open');
        },
        //编辑按钮事件
        edit: function () {
            //判断用户是否选中数据
            var row = paymentManage_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //先清空表
            paymentManage_form.form('clear');
            //相关的正式学员属性设置到row的属性上
            if (deepGet(row, "row.officialStudent.potentitalStudent") != "") {
                for (field in row.officialStudent.potentitalStudent) {
                    row["officialStudent.potentitalStudent." + field] = row.officialStudent.potentitalStudent[field];
                }
                //下拉框
                if (row.officialStudent.potentitalStudent.source) {
                    row['officialStudent.potentitalStudent.source.id'] = row.officialStudent.potentitalStudent.source.id;
                }
                if (row.officialStudent.potentitalStudent.employee) {
                    row['officialStudent.potentitalStudent.employee.id'] = row.officialStudent.potentitalStudent.employee.id;
                }
                if (row.officialStudent.potentitalStudent.classGrade) {
                    row['officialStudent.potentitalStudent.classGrade.id'] = row.officialStudent.potentitalStudent.classGrade.id;
                }
                if (row.officialStudent.potentitalStudent.employee) {
                    row['officialStudent.potentitalStudent.employee.realname'] = row.officialStudent.potentitalStudent.employee.realname;
                }
            }
            if (row.paymentType) {
                row["paymentType.id"] = row.paymentType.id;
            }
            if (row.payee) {
                row["payee.id"] = row.payee.id;
            }

            //回显
            paymentManage_form.form('load', row);
            //设置标题
            paymentManage_edit_dialog.dialog('setTitle', '编辑收款信息');
            //显示按钮
            $("#paymentManage_tbsc a").show();
            $("#changeDate").textbox({
                disabled: false
            });
            $("#afterClass").textbox({
                disabled: false
            });
            //打开弹出框
            paymentManage_edit_dialog.dialog('open');
        },
        view: function () {
            methodObj["edit"]();
            //设置标题
            paymentManage_edit_dialog.dialog('setTitle', '查看信息(无法编辑)');
            $("#paymentManage_tbsc a").hide();
            $("#changeDate").textbox({
                disabled: true
            });
            $("#afterClass").textbox({
                disabled: true
            });

        },
        //刷新按钮事件
        reloads: function () {
            $("#keyword").textbox("setValue", '');
            $("#beginChargeDate").textbox("setValue", '');
            $("#endChargeDate").textbox("setValue", '');
            $("#classgradeId").textbox("setValue", '');
            paymentManage_datagrid.datagrid('reload', {
                beginChargeDate: null,
                endChargeDate: null,
                keyword: null,
                classgradeId: null
            });

        },

        //保存操作
        save: function () {
            if ($("#paymentAmount").validatebox("isValid")) {
                paymentManage_form.form('submit', {
                    url: "/paymentManage/saveOrUpdate.do",
                    onSubmit: function (param) {

                    },
                    success: function (data) {
                        //alert(data)
                        //转成json对象
                        data = $.parseJSON(data);
                        if (data.success) {
                            //成功关闭窗口
                            paymentManage_edit_dialog.dialog('close');
                            //弹出提示框
                            $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                                //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                                paymentManage_datagrid.datagrid('reload');
                            });
                        } else {
                            //失败弹出提示
                            $.messager.alert('温馨提示', data.msg, 'error');
                        }
                    }
                });
            }
        }
        ,
        //取消按钮事件
        cancel: function () {
            //关闭弹出框
            paymentManage_edit_dialog.dialog('close');
        }
        ,
        //高级查询
        searchForm: function () {
            //获取关键字input
            var keyword = $("#keyword").textbox("getValue");
            var beginChargeDate = $("#beginChargeDate").textbox("getValue");
            var endChargeDate = $("#endChargeDate").textbox("getValue");
            var classgradeId = $("#classgradeId").textbox("getValue");
            //让数据表格重新加载比并将查询数据带过去
            paymentManage_datagrid.datagrid('load', {
                beginChargeDate: beginChargeDate,
                endChargeDate: endChargeDate,
                keyword: keyword,
                classgradeId: classgradeId
            });
        },
        //审核功能
        audit: function () {
            var row = paymentManage_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            $.messager.confirm('审核确认', '您确认想审核吗？', function (r) {
                if (r) {
                    //发送ajax请求
                    $.get('/paymentManage/audit.do', {id: row.id}, function (data) {
                        if (data.success) {
                            //弹出提示框
                            $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                                //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                                paymentManage_datagrid.datagrid('reload');
                            });
                        } else {
                            //失败弹出提示
                            $.messager.alert('温馨提示', data.msg, 'error');
                        }
                    })
                }
            });
        },
        //导出按钮事件
        exportXls: function () {
            window.location.href = "/paymentManage/export.do";
        }
        ,
    }
//3.页面加载完毕统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该方法要执行的方法
        var method = $(this).data("cmd");
        methodObj[method]();
    });

//1.获取到表格进行初始化,添加属性
    paymentManage_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: "/paymentManage/query.do",
        pageSize: 20,
        pageList: [15, 20, 25, 30, 35],
        columns: [[
            {
                field: 'officialStudent.potentitalStudent.name',
                title: '学员姓名',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.officialStudent.potentitalStudent.name");
                }
            },
            {
                field: 'officialStudent.potentitalStudent.classGrade.name',
                title: '班级名',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.officialStudent.potentitalStudent.classGrade.name");
                }
            },

            {
                field: "payDate",
                title: '收款时间',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.payDate");
                }
            },
            {
                field: 'paymentAmount',
                title: '收款金额',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.paymentAmount");
                }
            },
            {
                field: 'officialStudent.payment.fee2pay',
                title: '待缴学费',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.officialStudent.payment.fee2pay");
                }
            },
            {
                field: 'paymentType', title: '付款方式', width: 100, formatter: function (value, row, index) {
                    return deepGet(row, "row.paymentType.name");
                }
            },
            {
                field: 'payee',
                title: '收款人',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.payee.realname");
                }
            },
            // {
            //     field: 'billNum',
            //     title: '单据号',
            //     width: 100,
            //     formatter: function (value, row, index) {
            //         return deepGet(row, "row.billNum");
            //     }
            // },
            {
                field: 'officialStudent.potentitalStudent.employee',
                title: '营销人员',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.officialStudent.potentitalStudent.employee.username");
                }
            },
            {
                field: 'remark',
                title: '备注',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.remark");
                }
            },
            {
                field: "auditStatus",
                title: '审核状态',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.auditStatus") ? "<font color='#5f9ea0'>已审核</font>" : "<font color=#a52a2a>未审核</font>";
                }
            },
            {
                field: 'audit',
                title: '审核人员',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.audit.realname");
                }
            }
        ]],
        toolbar: "#paymentManage_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        onClickRow: function (index, row) {
            if (row.auditStatus == 1) {
                //修改编辑按钮
                edit_button.linkbutton({
                    disabled: true
                });
            } else {
                edit_button.linkbutton({
                    disabled: false
                });
            }
        }
    })
    /*初始化编辑弹出框*/
    paymentManage_edit_dialog.dialog({
        title: '转班操作',
        width: 300,
        height: 400,
        buttons: '#paymentManage_tbsc',
        closed: true
    });

})

