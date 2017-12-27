/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var payroll_form = $("#payroll_form");
    var payroll_datagrid = $("#payroll_datagrid");
    var payroll_dialog = $("#payroll_dialog");
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //新增绑定事件
        add: function () {
            //增加页面显示密码框
            $("#password_tr").show();
            //清空表单数据
            payroll_form.form('clear');
            //设置标题
            payroll_dialog.dialog('setTitle', '新增工资');
            //打开弹出框
            payroll_dialog.dialog('open');
        },
        //编辑按钮事件
        edit: function () {
            //判断用户是否选中数据
            var row = payroll_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //清空表单数据
            payroll_form.form('clear');
            //设置标题
            payroll_dialog.dialog('setTitle', '管理员登陆');
            //打开弹出框
            payroll_dialog.dialog('open');
        },
        //删除按钮事件
        remove: function () {
            //判断用户是否选中数据
            var row = payroll_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //发送ajax请求
            $.get('/payroll/delete.do', {id: row.id}, function (data) {
                if (data.success) {
                    //成功关闭窗口
                    payroll_dialog.dialog('close');
                    //弹出提示框
                    $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                        //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                        payroll_datagrid.datagrid('reload');
                    });
                } else {
                    //失败弹出提示
                    $.messager.alert('温馨提示', data.msg, 'error');
                }
            })
        },
        //管理员登陆操作
        save: function () {
            payroll_form.form('submit', {
                url: "/payroll/reviseEmpSalaryInformation.do",
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        payroll_dialog.dialog('close');
                        //判断用户是否选中数据
                        var row = payroll_datagrid.datagrid('getSelected');
                        if (!row) {
                            $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                            return;
                        }
                        //清空表单数据
                        $("#payrollemp_form").form('clear');
                        if (row.employee) {
                            row['employee.username'] = row.employee.username;
                            row['employee.id'] = row.employee.id;
                        }
                        //回显
                        $("#payrollemp_form").form('load', row);
                        //设置标题
                        $("#payrollemp_dialog").dialog('setTitle', '员工工资基本信息修改');
                        //打开弹出框
                        $("#payrollemp_dialog").dialog('open');

                    } else {
                        //失败弹出提示
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }
            });
        },
        //员工基本工资信息修改操作
        saveRevise: function () {
            //清空表单数据
            $("#payrollemp_form").form('submit', {
                url: "/payroll/saveOrUpdate.do",
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        $("#payrollemp_dialog").dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                           /* var row = payroll_datagrid.datagrid('getSelected');
                            //发送一个ajax请求
                            $.get("/payroll/checkAccount.do", {
                                'employee.id': row.employee.id,
                                'workDay': row.workDay,
                                'afterDay': row.afterDay,
                                'befterDay': row.befterDay,
                                'basicSalary': row.basicSalary,
                                'reword': row.reword,
                            }, function (data) {
                                if (data.success) {
                                    //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                                    payroll_datagrid.datagrid('reload');
                                } else {
                                    //失败弹出提示
                                    $.messager.alert('温馨提示', "重新核算失败!", 'error');
                                }
                            });*/
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            payroll_datagrid.datagrid('reload');
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
            payroll_dialog.dialog('close');
        },
        //取消按钮事件
        cancelRevise: function () {
            //关闭弹出框
            $("#payrollemp_dialog").dialog('close');
        },
        //工资状态事件
        checkAccount: function () {
            //判断用户是否选中数据
            var row = payroll_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            if (row.payTime == null ) {
                if(row.employee!=null) {
                    $.messager.confirm('确认', '您确认想要执行' + '员工' + row.employee.username + '的工资核算操作吗？', function (r) {
                        if (r) {
                            //发送一个ajax请求
                            $.get("/payroll/checkAccount.do", {
                                'employee.id': row.employee.id,
                                'workDay': row.workDay,
                                'afterDay': row.afterDay,
                                'befterDay': row.befterDay,
                                'basicSalary': row.basicSalary,
                                'reword': row.reword,
                            }, function (data) {
                                if (data.success) {
                                    console.log(data)
                                    //给支出发送一条信息
                                    $.get("/defray/saveOrUpdate.do",{
                                        defrayTime:data.defrayTime,
                                        defrayMoney:data.defrayMoney,
                                        remark:data.defrayMoney,
                                        "applicant.id":data.applicant.id,
                                        "handMan.id":data.handMan.id,
                                        "paymentType.id":data.paymentType.id,
                                       "defrayType.id":data.defrayType.id
                                    },function (data2) {
                                        if (data2.success) {
                                            //弹出提示框
                                            $.messager.alert('温馨提示', '核算成功！', 'info', function () {
                                                //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                                                payroll_datagrid.datagrid('reload');
                                            });
                                        }else{
                                            //失败弹出提示
                                            $.messager.alert('温馨提示', "核算失败", 'error');
                                        }
                                    })
                                } else {
                                    //失败弹出提示
                                    $.messager.alert('温馨提示', "核算失败", 'error');
                                }
                            });
                        }
                    });
                }else{
                    //失败弹出提示
                    $.messager.alert('温馨提示', "没有!此员工,删除了吧!", 'error');
                }
            } else {
                $.messager.alert('温馨提示', '员工' + row.employee.username + '工资已经结算', 'info')
            }
        },
        //刷新按钮事件
        reloads: function () {
            var keyword = $("#keyword").textbox("setValue",'');
            var minSalary = $("#minSalary").textbox("setValue",'');
            var maxSalary = $("#maxSalary").textbox("setValue",'');
            var date = $("#date").textbox("setValue",'');
            var deptId = $("#deptId").textbox("setValue",'');
            payroll_datagrid.datagrid('reload',{
                orderBy:null,
                date: null,
                minSalary: null,
                maxSalary: null,
                keyword: null,
                deptId: null
            });
        },
        //取消按钮事件
        cancel: function () {
            //关闭弹出框
            payroll_dialog.dialog('close');
        },
        //高级查询
        searchForm: function () {
            //获取关键字input
            var keyword = $("#keyword").textbox("getValue");
            var minSalary = $("#minSalary").textbox("getValue");
            var maxSalary = $("#maxSalary").textbox("getValue");
            var date = $("#date").textbox("getValue");
            var deptId = $("#deptId").textbox("getValue");
            //让数据表格重新加载比并将查询数据带过去
            payroll_datagrid.datagrid('load', {
                date: date,
                minSalary: minSalary,
                maxSalary: maxSalary,
                keyword: keyword,
                deptId: deptId
            });
        },
        //下载工资模板事件
        downModel: function () {
            window.location.href = "/payroll/downPayrollModal.do";
        },
        //导出按钮事件
        exportXls: function () {
            window.location.href = "/payroll/export.do";
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
                url: "/payroll/input.do",
                success: function (data) {
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        $("#fileInput").dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            var keyword = $("#keyword").textbox("setValue",'');
                            var minSalary = $("#minSalary").textbox("setValue",'');
                            var maxSalary = $("#maxSalary").textbox("setValue",'');
                            var date = $("#date").textbox("setValue",'');
                            var deptId = $("#deptId").textbox("setValue",'');
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            payroll_datagrid.datagrid('load',{
                                orderBy:null,
                                date: null,
                                minSalary: null,
                                maxSalary: null,
                                keyword: null,
                                deptId: null
                            });

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
    payroll_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: "/payroll/query.do",
        columns: [[
            {
                field: 'employee1', title: '员工编号', width: 100, align: 'center', formatter: function (value, row, index) {
                return row.employee ? row.employee.id : '';
            }
            },
            {
                field: 'employee2', title: '员工名称', width: 100, align: 'center', formatter: function (value, row, index) {
                return row.employee ? row.employee.username : '';
            }
            },
            {
                field: 'department', title: '所在部门', width: 100, align: 'center', formatter: function (value, row, index) {
                return value ? value.name : '';
            }
            },
            {field: 'month', title: '工资月份', width: 100, align: 'center'},
            {field: 'workDay', title: '应工作天数', width: 100, align: 'center'},
            {field: 'afterDay', title: '迟到天数', width: 100, align: 'center'},
            {field: 'befterDay', title: '早退天数', width: 100, align: 'center'},
            {field: 'actualWorkDay', title: '实际工作天数', width: 100, align: 'center'},
            {field: 'basicSalary', title: '基本薪水', width: 100, align: 'center'},
            {field: 'reword', title: '奖金', width: 100, align: 'center'},
            {field: 'salary', title: '实际薪水', width: 100, align: 'center'},
            {field: 'endSalary', title: '应发薪水', width: 100, align: 'center'},
            {
                field: 'payTime', title: '支付时间', width: 222, align: 'center', formatter: function (value, row, index) {
                return value ? value : "<font color='red'>未核算</font>";
            }
            },

        ]],
        toolbar: "#payroll_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        pageSize:20,
        pageList:[15,20,25,30,35],
        singleSelect: true
    })
    /*初始化管理员登陆弹出框*/
    payroll_dialog.dialog({
        title: '管理员登陆',
        width: 250,
        height: 180,
        buttons: '#payroll_tbsc',
        closed: true
    })
    /*初始修改弹出框*/
    $("#payrollemp_dialog").dialog({
        title: '修改员工工资表信息',
        width: 280,
        height: 330,
        buttons: '#payrollEmp_tbsc',
        closed: true
    })
    /*初始化文件上传弹出框*/
    $("#fileInput").dialog({
        title: '文件上传',
        width: 250,
        height: 150,
        buttons: '#input_tbsc',
        closed: true
    })
    /*快速排序事件*/
    $('#mm').menu({
        onClick:function(item){
            console.log(item.text);
            if(item.text=="按工资升序"){
                order='ASC';
            }
            if(item.text=="按工资降序"){
                order='DESC';
            }
            payroll_datagrid.datagrid('load', {
                orderBy:order
            });
        }
    });
})

