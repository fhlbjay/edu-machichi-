$(function () {
    //1.变量抽取
    var universitytrace_form = $("#universitytrace_form");
    var universitytrace_datagrid = $("#universitytrace_datagrid");
    var universitytraceTraceAll_dialog = $("#universitytraceTraceAll_dialog");

    //2.把方法放进来(放到一个对象中统一管理方法)
    var methodObj = {
        remove: function () {
            //判断用户是否选中数据
            var row = universitytrace_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //发送ajax请求
            $.get('/universitytrace/delete.do', {id: row.id}, function (data) {
                if (data.success) {
                    //成功关闭窗口
                    universitytraceTraceAll_dialog.dialog('close');
                    //弹出提示框
                    $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                        //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                        universitytrace_datagrid.datagrid('reload');
                    });
                } else {
                    //失败弹出提示
                    $.messager.alert('温馨提示', data.msg, 'error');
                }
            })
        },
        add: function () {

            //清空表单
            universitytrace_form.form("clear");

         /*   //设置一些字段选中
            $("#un_customerStatus").combobox("setValue", 1);
            $("#un_traceState").combobox("setValue", 1);*/

            //设置标题
            universitytraceTraceAll_dialog.dialog("setTitle", "新增部门");
            //打开弹出框
            universitytraceTraceAll_dialog.dialog("open");
        },
        reload: function () {
            universitytrace_datagrid.datagrid('reload');
        },
        edit: function () {
            var row = universitytrace_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', "请选择一条数据!", 'warning');
                return;
            }

            //清空表单
            universitytrace_form.form("clear");

            //处理数据回显
            if (row["importance"]) {
                row["importance.id"] = row.importance.id;
            }
            if (row["wantedlevel"]) {
                row["wantedlevel.id"] = row.wantedlevel.id;
            }
            if (row["college"]) {
                row["college.id"] = row.college.id;
            }
            if (row["subject"]) {
                row["subject.id"] = row.subject.id;
            }
            if (row["contact"]) {
                row["contact.id"] = row.contact.id;
            }
            if (row["marketer"]) {
                row["marketer.id"] = row.marketer.id;
            }
            if (row["tracer"]) {
                row["tracer.id"] = row.tracer.id;
            }
            if (row["type"]) {
                row["type.id"] = row.type.id;
            }
            if (row["college"]) {
                row["college.id"] = row.college.id;
            }
            //回显数据
            universitytrace_form.form("load", row);

            //设置标题
            universitytraceTraceAll_dialog.dialog("setTitle", "大客户编辑");
            //打开弹出框
            universitytraceTraceAll_dialog.dialog("open");
        },

        cancel: function () {
            //关闭弹出框
            universitytraceTraceAll_dialog.dialog("close");
        },

        save: function () {
            universitytrace_form.form("submit", {
                url: '/universitytrace/saveOrUpdate.do',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //提示信息
                        $.messager.alert('温馨提示', '操作成功', 'info', function () {
                            //关闭弹出框
                            universitytraceTraceAll_dialog.dialog("close");
                            //重新加载数据表格
                            universitytrace_datagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }

            })
        },
        load: function () {
            $("#keyword").textbox("setValue","");
            $("#subjectId").textbox("setValue","");
            universitytrace_datagrid.datagrid('reload', {
                beginDate:null,
                endDate:null,
                keyword:null,
                classgradeId:null,
            });
        },
        changeState: function () {
            var row = universitytrace_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', "请选择一条数据!", 'warning');
                return;
            }

            $.messager.confirm('确认', '您确认想要执行该操作吗？', function (r) {
                if (r) {
                    //发送请求修改状态
                    $.get("/universitytrace/changeState.do", {id: row.id,eId:row.customerstatus}, function (data) {
                        if (data.success) {
                            $.messager.alert('温馨提示', "操作成功!", 'info', function () {
                                //重新加载数据表格
                                universitytrace_datagrid.datagrid("reload");
                            });
                        } else {
                            $.messager.alert('温馨提示', data.msg, 'error');
                        }
                    }, "json")
                }
            });
        },
       searchForm: function () {
            //获取关键字input
            var keyword = $("#keyword").textbox("getValue");
            var subjectId = $("#subjectId").textbox("getValue");

            //让数据表格重新加载,并且带上查询的参数(带参数)
            universitytrace_datagrid.datagrid("load", {
                keyword: keyword,
                subjectId: subjectId
            });
        }
    };

//3.页面加载完后统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该按钮要执行的方法(cmd)
        var method = $(this).data("cmd");
        //调用方法
        methodObj[method]();
    })

    universitytrace_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        toolbar: '#universitytrace_toolbar',
        striped: true,
        url: '/universitytrace/query.do',
        pagination: true,
        pageSize:20,
        pageList:[15,20,25,30,35],
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'id', title: "编号", width: 100},
            {field: 'name', title: '学校', width: 100},
            {field: 'address', title: '地址', width: 100},

            {
                field: 'importance', title: '重要性', width: 100, formatter: function (value, row, index) {
                    return row.importance ? value.name : "";
                }
            },
            {
                field: 'wantedlevel', title: '意向度', width: 100, formatter: function (value, row, index) {
                    return row.wantedlevel ? value.name : "";
                }
            },
            {field: 'schooltel', title: '学校电话', width: 100},
            {
                field: 'subject', title: '意向学科', width: 100, formatter: function (value, row, index) {
                    return value ? value.name : "";
                }
            },
            {
                field: 'contact', title: '联系人', width: 100, formatter: function (value, row, index) {
                    return row.contact ? value.contactsName : "";
                }
            },
            {
                field: 'marketer', title: '营销人员', width: 100, formatter: function (value, row, index) {
                    return row.marketer ? value.realname : "";
                }
            },
            {
                field: 'tracer', title: '跟进人员', width: 100, formatter: function (value, row, index) {
                    return row.tracer ? value.realname : "";
                }
            },
            {field: 'prevtrancetime', title: '上次跟进时间', width: 100},
            {field: 'nexttrancetime', title: '下次跟进时间', width: 100},
            {
                field: 'tracestate', title: '跟进状态', width: 100, formatter: function (value, row, index) {
                    return value ? "已跟踪" : "<font color='red'>未跟踪</font>";
                }
            },
            {
                field: 'customerstatus', title: '客户状态', width: 100, formatter: function (value, row, index) {
                    return value ? "已签约" : "<font color='red'>未签约</font>";
                }
            }
        ]],

        onClickRow: function (index, row) {
            if (row.state) {
                //修改按钮
                $('#changState_btn').linkbutton({
                    text: '离职'
                })

            } else {
                //修改按钮
                $('#changState_btn').linkbutton({
                    text: '复职'
                })
            }
        }
    })


    universitytraceTraceAll_dialog.dialog({
        width: '900px',
        height: '600px',
        buttons: '#universitytrace_buttons',
        closed: true
    })
})
/*修改*/
