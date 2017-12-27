/*页面加载完毕之后操作*/
$(function () {
    function deepGet(row, path) {
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

    //1.变量抽取
    var changeClass_datagrid = $("#changeClass_datagrid");
    var changeClass_edit_dialog = $("#changeClass_edit_dialog");
    var changeClass_form = $("#changeClass_form");
    var edit_button = $("#edit_button");
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //编辑按钮事件
        edit: function () {
            //判断用户是否选中数据
            var row = changeClass_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //先清空表
            changeClass_form.form('clear');

            //相关的正式学员属性设置到row的属性上
            if (deepGet(row, "row.officialStudent.potentitalStudent")) {
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
                    row['officialStudent.potentitalStudent.employee.username'] = row.officialStudent.potentitalStudent.employee.username;

                }
            }
            row["afterClass.id"] = row.afterClass.id;

            //回显
            changeClass_form.form('load', row);
            //设置标题
            changeClass_edit_dialog.dialog('setTitle', '转班信息编辑');
            //显示按钮
            $("#changeClass_tbsc a").show();
            $("#changeDate").textbox({
                disabled: false
            });
            $("#afterClass").textbox({
                disabled: false
            });
            //打开弹出框
            changeClass_edit_dialog.dialog('open');
        },
        view: function () {
            methodObj["edit"]();
            //设置标题
            changeClass_edit_dialog.dialog('setTitle', '查看信息(无法编辑)');
            $("#changeClass_tbsc a").hide();
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
            $("#beginChangeDate").textbox("setValue", '');
            $("#endChangeDate").textbox("setValue", '');
            $("#classgradeId").textbox("setValue", '');
            changeClass_datagrid.datagrid('reload', {
                beginChangeDate: null,
                endChangeDate: null,
                keyword: null,
                classgradeId: null
            });

        },

        //保存操作
        save: function () {
            changeClass_form.form('submit', {
                url: "/changeClass/update.do",
                onSubmit: function (param) {
                },
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        changeClass_edit_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            changeClass_datagrid.datagrid('reload');
                        });
                    } else {
                        //失败弹出提示
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }
            });
        }
        ,
        //取消按钮事件
        cancel: function () {
            //关闭弹出框
            changeClass_edit_dialog.dialog('close');
        }
        ,
        //高级查询
        searchForm: function () {
            //获取关键字input
            var keyword = $("#keyword").textbox("getValue");
            var beginChangeDate = $("#beginChangeDate").textbox("getValue");
            var endChangeDate = $("#endChangeDate").textbox("getValue");
            var classgradeId = $("#classgradeId").textbox("getValue");
            //让数据表格重新加载比并将查询数据带过去
            changeClass_datagrid.datagrid('load', {
                beginChangeDate: beginChangeDate,
                endChangeDate: endChangeDate,
                keyword: keyword,
                classgradeId: classgradeId
            });
        },
        //审核功能
        audit: function () {
            var row = changeClass_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            $.messager.confirm('审核确认', '您确认想审核吗？', function (r) {
                if (r) {
                    //发送ajax请求
                    $.get('/changeClass/audit.do', {id: row.id}, function (data) {
                        if (data.success) {
                            //弹出提示框
                            $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                                //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                                changeClass_datagrid.datagrid('reload');
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
            window.location.href = "/changeClass/export.do";
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
    changeClass_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        pageSize:20,
        pageList:[15,20,25,30,35],
        url: "/changeClass/query.do",
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
                field: 'officialStudent.potentitalStudent.employee',
                title: '营销人员',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.officialStudent.potentitalStudent.employee.username");
                }
            },
            {
                field: 'officialStudent.payment.totalFee',
                title: '总学费',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.officialStudent.payment.totalFee");
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
                field: 'officialStudent.payment.paidupFee',
                title: '已缴学费',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.officialStudent.payment.paidupFee");
                }
            },
            {
                field: 'officialStudent.payment.paidStatus',
                title: '缴费状态',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.officialStudent.payment.paidStatus") ?
                        "<font color='#5f9ea0'>已缴清</font>" : "<font color=#a52a2a>未缴清</font>"
                }
            },
            {
                field: 'beforeClass',
                title: '以前班级',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.beforeClass");
                }
            },
            {
                field: "afterClass",
                title: '转入班级',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.afterClass.name");
                }
            },
            {
                field: "changeDate",
                title: '转班时间',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.changeDate");
                }
            },
            {
                field: "auditStatus",
                title: '审核状态',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.auditStatus") ? "<font color='#5f9ea0'>已审核</font>" : "<font color=#a52a2a>未审核</font>";
                }
            }
        ]],
        toolbar: "#changeClass_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        pageSize: 20,
        pageList: [15, 20, 25, 30, 35],
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
    changeClass_edit_dialog.dialog({
        title: '转班操作',
        width: 300,
        height: 400,
        buttons: '#changeClass_tbsc',
        closed: true
    });
    $("#afterClass").combobox({
        onChange: function () {
            if ($("#afterClass").val()) {
                if ($("#afterClass").val() == $("#beforeClass").val()) {
                    $.messager.alert('温馨提示', '转入班级不能和原班级相同', 'error');
                    $("#changeClass_tbsc a:first").hide();
                } else {
                    $("#changeClass_tbsc a:first").show();
                }

            }
        }
    });

})

