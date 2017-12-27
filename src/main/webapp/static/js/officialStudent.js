/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var officialStudent_form = $("#officialStudent_form");
    var officialStudent_datagrid = $("#officialStudent_datagrid");
    var officialStudent_dialog = $("#officialStudent_dialog");
    var changeClass_button = $("#changeClass");
    var quitSchool_button = $("#quitSchool");
    var runOff_button = $("#runOff");
    var officialStudent_changeClass_dialog = $("#officialStudent_changeClass_dialog");
    var officialStudent_changeClass_form = $("#officialStudent_changeClass_form");
    var officialStudent_cutOff_dialog = $("#officialStudent_cutOff_dialog");
    var officialStudent_cutOff_form = $("#officialStudent_cutOff_form");


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
    };
    $("#afterClass").combobox({
        onChange: function () {
            if ($("#afterClass").val() == $("#currentClass").val()) {
                $.messager.alert('温馨提示', '转入班级不能和原班级相同', 'error');
                $("#officialStudent_changeClassBtn a:first").hide();
            } else {
                $("#officialStudent_changeClassBtn a:first").show();
            }
        }
    });
    $("[name='refundState']").change(function () {
        if ($(this).val() == 0) {
            // 影藏收款按钮
            $('#refundMoney_tr').hide();
        } else {
            $('#refundMoney_tr').show();
        }
    });


    //2.把所有方法放在一个变量里面
    var methodObj = {
        //新增绑定事件
        add: function () {
            //增加页面显示密码框
            $("#password_tr").show();
            //清空表单数据
            officialStudent_form.form('clear');
            //设置标题
            officialStudent_dialog.dialog('setTitle', '新增学员');
            //显示按钮
            $("#officialStudent_tbsc a").show();
            //打开弹出框
            officialStudent_dialog.dialog('open');
        },
        //编辑按钮事件
        edit: function () {
            //判断用户是否选中数据
            var row = officialStudent_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //先清空表
            officialStudent_form.form('clear');
            //相关的正式学员属性设置到row的属性上
            if (row.potentitalStudent) {
                //文字显示
                for (field in row.potentitalStudent) {
                    row["potentitalStudent." + field] = row.potentitalStudent[field];
                }
                if (row.potentitalStudent.employee) {
                    row['potentitalStudent.employee.username'] = row.potentitalStudent.employee.username;
                }
                //下拉框
                if (row.potentitalStudent.clientType) {
                    row['potentitalStudent.clientType.id'] = row.potentitalStudent.clientType.id;
                }
                if (row.potentitalStudent.source) {
                    row['potentitalStudent.source.id'] = row.potentitalStudent.source.id;
                }
                if (row.potentitalStudent.education) {
                    row['potentitalStudent.education.id'] = row.potentitalStudent.education.id;
                }
                if (row.potentitalStudent.classGrade) {
                    row['potentitalStudent.classGrade.id'] = row.potentitalStudent.classGrade.id;
                }
                if (row.potentitalStudent.campus) {
                    row['potentitalStudent.campus.id'] = row.potentitalStudent.campus.id;
                }
            }

            if (row.payment) {
                //payment相关信息回显
                for (field in row.payment) {
                    row["payment." + field] = row.payment[field];
                }
                //下拉框
                if (row.payment.paymentType) {
                    row['payment.paymentType.id'] = row.payment.paymentType.id;
                }
            }
            if (row.detailInfo) {
                //detailInfo相关信息回显
                row['detailInfo.id'] = row.detailInfo.id;
                row['detailInfo.idNo'] = row.detailInfo.id;
                row['detailInfo.emergencyContact'] = row.detailInfo.emergencyContact;
                row['detailInfo.emergencyTel'] = row.detailInfo.emergencyTel;
                row['detailInfo.workExperience'] = row.detailInfo.workExperience;
                //radio 回显
                // alert(row.detailInfo.workIntention)
                if (row.detailInfo.workIntention) {
                    var val = row.detailInfo.workIntention;
                    var checkbox = $("input[name='detailInfo.workIntention']")
                    checkbox.each(function (index, item) {
                        if (val == $(item).val()) {
                            $(item).prop("checked", true);
                        }
                    });
                }
            }
            //回显
            officialStudent_form.form('load', row);
            console.log($("#officialStudent_form [name*=payment]"));

            //设置标题
            officialStudent_dialog.dialog('setTitle', '学员编辑');
            //显示按钮
            $("#officialStudent_tbsc a").show();
            //修改为可编辑
            $("#officialStudent_form :input").prop("readonly", false);
            $("#paymentInfo :input").prop("readonly", true);
            $("#saleMan").textbox("readonly", true);
            $("#officialStudent_form [name='detailInfo.workIntention']").prop("disabled", false);
            $("#detailInfo :input").prop("readonly", false);
            //打开弹出框
            officialStudent_dialog.dialog('open');
        },
        view: function () {
            methodObj["edit"]();
            //设置标题
            officialStudent_dialog.dialog('setTitle', '查看信息(无法编辑)');
            $("#officialStudent_tbsc a").hide();
            $("#officialStudent_form :input").prop("readonly", true);
            $("#officialStudent_form [name='detailInfo.workIntention']").prop("disabled", true);
        },
        //正式学员转班事件
        changeClass: function () {
            var row = officialStudent_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            row['currentClassId'] = row.potentitalStudent.classGrade.id;
            officialStudent_changeClass_form.form('clear');
            //回显
            officialStudent_changeClass_form.form('load', row);
            officialStudent_changeClass_dialog.dialog('open');

        },
        //90 退学/92休学  /94正常/96转班
        //正式学员休学/复学事件
        quitSchool: function () {
            var row = officialStudent_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            var msg;
            if (row.studentStatus.id == 92) {
                msg = "正常"
            }
            if (row.studentStatus.id == 94) {
                msg = "休学"
            }
            $.messager.confirm('确认', '您确认想要设置为' + msg + '状态吗？', function (r) {
                if (r) {
                    //发送一个ajax请求
                    $.get("/officialStudent/quitSchool.do", {id: row.id}, function (data) {
                        if (data.success) {
                            //弹出提示框
                            $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                                //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                                officialStudent_datagrid.datagrid('reload');
                            });
                        } else {
                            //失败弹出提示
                            $.messager.alert('温馨提示', data.msg, 'error');
                        }
                    });
                }
            });
        },
        //90 退学/92休学  /94正常/96转班
        //正式学员休学/复学事件
        runOff: function () {
            var row = officialStudent_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            $.messager.confirm('确认', '您确认想要设置为流失状态吗？', function (r) {
                if (r) {
                    officialStudent_cutOff_dialog.dialog('setTitle', '流失编辑');
                    officialStudent_cutOff_form.form("clear");
                    officialStudent_cutOff_dialog.dialog("open");
                    $("#refundState").prop("checked", true);
                }
            });
        },
        //刷新按钮事件
        reloads: function () {
            $("#keyword").textbox("setValue", '');
            $("#beginEnrolDate").textbox("setValue", '');
            $("#endEnrolDate").textbox("setValue", '');
            $("#classgradeId").textbox("setValue", '');
            officialStudent_datagrid.datagrid('reload', {
                beginEnrolDate: null,
                endEnrolDate: null,
                keyword: null,
                classgradeId: null
            });

        },

        //保存操作
        save: function () {
            officialStudent_form.form('submit', {
                url: "/officialStudent/saveOrUpdate.do",
                onSubmit: function (param) {

                },
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        officialStudent_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            officialStudent_datagrid.datagrid('reload');
                        });
                    } else {
                        //失败弹出提示
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }
            });
        },
        //转班保存
        save_changeClass: function () {
            var row = officialStudent_datagrid.datagrid('getSelected');
            //发送一个ajax请求
            officialStudent_changeClass_form.form('submit', {
                url: "/changeClass/save.do",
                onSubmit: function (param) {
                    param.beforeClass = row.potentitalStudent.classGrade.name;
                },
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        officialStudent_changeClass_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            $.get("/officialStudent/changeClass.do", {id: row.id}, function () {
                                officialStudent_datagrid.datagrid('reload');
                            });

                        });
                    } else {
                        //失败弹出提示
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }
            });
        },
        //流失保存
        save_cutOff: function () { // 流失保存操作
            var row = officialStudent_datagrid.datagrid('getSelected');
            officialStudent_cutOff_form.form("submit", {
                url: '/wastageStudent/insert.do',
                onSubmit: function (param) {
                    param.studentId = row.id;
                },
                success: function (data) {
                    var data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", '操作成功', "info", function () {
                            officialStudent_cutOff_dialog.dialog("close");
                            officialStudent_datagrid.datagrid("reload");
                        })
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        cancel_cutOff: function () {
            officialStudent_cutOff_dialog.dialog("close");
        },
        //取消按钮事件
        cancel: function () {
            //关闭弹出框
            officialStudent_dialog.dialog('close');
        },
        cancel_changeClass: function () {
            //关闭弹出框
            officialStudent_changeClass_dialog.dialog('close');
        },
        //高级查询
        searchForm: function () {
            //获取关键字input
            var keyword = $("#keyword").textbox("getValue");
            var beginEnrolDate = $("#beginEnrolDate").textbox("getValue");
            var endEnrolDate = $("#endEnrolDate").textbox("getValue");
            var classgradeId = $("#classgradeId").textbox("getValue");
            //让数据表格重新加载比并将查询数据带过去
            officialStudent_datagrid.datagrid('load', {
                beginEnrolDate: beginEnrolDate,
                endEnrolDate: endEnrolDate,
                keyword: keyword,
                classgradeId: classgradeId
            });
        },
        //导出按钮事件
        exportXls: function () {
            window.location.href = "/officialStudent/export.do";
        },
        //文件上传按钮事件
        inputXls: function () {
            $("#fileInput").dialog('setTitle', '文件上传');
            //打开弹出框
            $("#fileInput").dialog('open');
        },
        //上传
        undo: function () {
            $("#input_form").form('submit', {
                url: "/officialStudent/input.do",
                success: function (data) {
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        $("#fileInput").dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            officialStudent_datagrid.datagrid('load');
                        });
                    } else {
                        //失败弹出提示
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
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
    officialStudent_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: "/officialStudent/query.do",
        pageSize: 20,
        pageList: [15, 20, 25, 30, 35],
        columns: [[
            {
                field: 'potentitalStudent.name', title: '学员姓名', width: 100, formatter: function (value, row, index) {
                    return deepGet(row, "row.potentitalStudent.name");
                }
            },
            {
                field: 'potentitalStudent.employee',
                title: '营销人员',
                width: 100,
                formatter: function (value, row, index) {

                    return deepGet(row, "row.potentitalStudent.employee.username");
                }
            },
            {
                field: 'payment.totalFee', title: '总学费', width: 100, formatter: function (value, row, index) {
                    return deepGet(row, "row.payment.totalFee");
                }
            },
            {
                field: 'payment.fee2pay', title: '待缴学费', width: 100, formatter: function (value, row, index) {
                    return deepGet(row, "row.payment.fee2pay");
                    // return row.payment.fee2pay;
                }
            },
            {
                field: 'payment.paidupFee', title: '已缴学费', width: 100, formatter: function (value, row, index) {
                    return deepGet(row, "row.payment.paidupFee");
                }
            },
            {
                field: 'payment.paidStatus', title: '缴费状态', width: 100, formatter: function (value, row, index) {
                    return deepGet(row, "row.payment.paidStatus") ? "<font color='#5f9ea0'>已缴清</font>" : "<font color=#a52a2a>未缴清</font>";
                }
            },
            {field: 'enrolTime', title: '入学时间', width: 100},
            {
                field: 'potentitalStudent.school', title: '学校', width: 100, formatter: function (value, row, index) {
                    return deepGet(row, "row.potentitalStudent.school");
                }
            },
            {
                field: 'potentitalStudent.tel', title: '电话', width: 100, formatter: function (value, row, index) {
                    return deepGet(row, "row.potentitalStudent.tel");
                }
            },
            {
                field: 'potentitalStudent.classgrade',
                title: '所在班级',
                width: 100,
                formatter: function (value, row, index) {
                    return deepGet(row, "row.potentitalStudent.classGrade.name");
                }
            },
            {
                field: 'payment.paymentType', title: '付款方式', width: 100, formatter: function (value, row, index) {
                    return deepGet(row, "row.payment.paymentType.name");
                }
            },
            {
                field: 'studentStatus.name', title: '状态', width: 100, formatter: function (value, row, index) {
                    return deepGet(row, "row.studentStatus.name");
                }
            }
        ]],
        toolbar: "#officialStudent_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        onClickRow: function (index, row) {
            if (row.studentStatus.id == 92) {
                //休学状态
                //修改按钮值
                quitSchool_button.linkbutton({
                    disabled: false,
                    text: "复学"
                });
                //修改转班按钮
                changeClass_button.linkbutton({
                    disabled: true
                });
            } else if (row.studentStatus.id == 94) {
                //正常状态
                //修改休学按钮值
                quitSchool_button.linkbutton({
                    disabled: false,
                    text: "休学"
                });
                //修改流失按钮
                runOff_button.linkbutton({
                    disabled: false
                });
                //修改转班按钮
                changeClass_button.linkbutton({
                    disabled: false
                });

            } else {
                //其他状态 流失/
                //修改流失按钮
                runOff_button.linkbutton({
                    disabled: true
                });
                quitSchool_button.linkbutton({
                    disabled: true
                });
                //修改转班按钮
                changeClass_button.linkbutton({
                    disabled: true
                });
            }
        }
    })
    /*初始化弹出框*/
    officialStudent_dialog.dialog({
        title: '正式学员编辑',
        width: 750,
        height: 450,
        buttons: '#officialStudent_tbsc',
        closed: true
    });
    /*初始化转班弹出框*/
    officialStudent_changeClass_dialog.dialog({
        title: '转班操作',
        width: 300,
        height: 200,
        buttons: '#officialStudent_changeClassBtn',
        closed: true
    });
    //学员退学dialog
    officialStudent_cutOff_dialog.dialog({
        width: 300,
        height: 350,
        buttons: "#officialStudent_cutOff_btns",
        closed: true
    });

})

