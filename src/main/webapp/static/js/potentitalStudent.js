$(function () {
    //1.变量抽取
    var ps_form = $("#ps_form");
    var query_form = $("#query_form");
    var track_form = $("#track_form");
    var test_form = $("#test_form");
    var change2official_form = $("#change2official_form");
    var ps_datagrid = $("#ps_datagrid");
    var ps_dialog = $("#ps_dialog");
    var query_dialog = $("#query_dialog");
    var track_dialog = $("#track_dialog");
    var test_dialog = $("#test_dialog");
    var change2official_dialog = $("#change2official_dialog");
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //新增绑定事件
        add: function () {
            //增加页面显示密码框
            $("#password_tr").show();
            //清空表单数据
            ps_form.form('clear');
            //设置标题
            ps_dialog.dialog('setTitle', '新增员工');
            //打开弹出框
            ps_dialog.dialog('open');
        },
        //编辑按钮事件
        edit: function () {
            //判断用户是否选中数据
            var row = ps_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            console.log(row);
            //清空表单数据
            ps_form.form('clear');
            if (row['campus']) {
                row['campus.id'] = row.campus.id;
            }
            if (row['classGrade']) {
                row['classGrade.id'] = row.classGrade.id;
            }
            if (row['clientType']) {
                row['clientType.id'] = row.clientType.id;
            }
            if (row['education']) {
                row['education.id'] = row.education.id;
            }
            if (row['employee']) {
                row['employee.id'] = row.employee.id;
            }
            if (row['intention']) {
                row['intention.id'] = row.intention.id;
            }
            if(row['introducerStu']){
                row['introducerStu.id']=row.introducerStu.id;
            }
            if (row['source']) {
                row['source.id'] = row.source.id;
            }
            if (row['status']) {
                row['status.id'] = row.status.id;
            }
            if (row['subject']) {
                row['subject.id'] = row.subject.id;
            }
            if (row['bigCustomer']) {
                row['bigCustomer.id'] = row.bigCustomer.id;
            }
            //回显
            ps_form.form('load', row);
            //设置标题
            ps_dialog.dialog('setTitle', '编辑潜在学员');
            //打开弹出框
            ps_dialog.dialog('open');
            $("#ps_tbsc").show();
        },
        //考试登记按钮事件
        test: function () {
            //判断用户是否选中数据
            var row = ps_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            console.log(row);
            //清空表单数据
            test_form.form('clear');
            if (row['employee']) {
                row['employee.id'] = row.employee.id;
            }
            if (row['classGrade']) {
                row['classGrade.id'] = row.classGrade.id;
            }
            //回显
            test_form.form('load', row);
            //设置标题
            test_dialog.dialog('setTitle', '考试登记学员');
            //打开弹出框
            test_dialog.dialog('open');
        },
        //跟踪按钮事件
        track: function () {
            //判断用户是否选中数据
            var row = ps_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            console.log(row);
            //清空表单数据
            ps_form.form('clear');
            if (row['communicationMethod']) {
                row['communicationMethod.id'] = row.communicationMethod.id;
            }
            if (row['tracePurpose']) {
                row['tracePurpose.id'] = row.tracePurpose.id;
            }
            if (row['trackTimes']) {
                row['trackTimes'] = row.trackTimes + 1;
            }else{
                row['trackTimes']=1;
            }
            if (row['campus']) {
                row['campus.id'] = row.campus.id;
            }
            if (row['classGrade']) {
                row['classGrade.id'] = row.classGrade.id;
            }
            if (row['clientType']) {
                row['clientType.id'] = row.clientType.id;
            }
            if (row['education']) {
                row['education.id'] = row.education.id;
            }
            if (row['employee']) {
                row['employee.id'] = row.employee.id;
            }
            if (row['intention']) {
                row['intention.id'] = row.intention.id;
            }
            if(row['introducerStu']){
                row['introducerStu.id']=row.introducerStu.id;
            }
            if (row['source']) {
                row['source.id'] = row.source.id;
            }
            if (row['status']) {
                row['status.id'] = row.status.id;
            }
            if (row['subject']) {
                row['subject.id'] = row.subject.id;
            }
            if(row['bigCustomer']){
                row['bigCustomer.id']=row.bigCustomer.id;
            }
            //回显
            track_form.form('load', row);
            //设置标题
            track_dialog.dialog('setTitle', '跟踪潜在学员');
            //打开弹出框
            track_dialog.dialog('open');
        },
        //查看按钮事件
        look: function () {
            //判断用户是否选中数据
            var row = ps_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            console.log(row);
            //清空表单数据
            ps_form.form('clear');
            if (row['campus']) {
                row['campus.id'] = row.campus.id;
            }
            if (row['classGrade']) {
                row['classGrade.id'] = row.classGrade.id;
            }
            if (row['clientType']) {
                row['clientType.id'] = row.clientType.id;
            }
            if (row['education']) {
                row['education.id'] = row.education.id;
            }
            if (row['employee']) {
                row['employee.id'] = row.employee.id;
            }
            if (row['intention']) {
                row['intention.id'] = row.intention.id;
            }
            if(row['introducerStu']){
                row['introducerStu.id']=row.introducerStu.id;
            }
            if (row['source']) {
                row['source.id'] = row.source.id;
            }
            if (row['status']) {
                row['status.id'] = row.status.id;
            }
            if (row['subject']) {
                row['subject.id'] = row.subject.id;
            }
            if (row['bigCustomer']) {
                row['bigCustomer.id'] = row.bigCustomer.id;
            }
            //回显
            ps_form.form('load', row);
            //设置标题
            ps_dialog.dialog('setTitle', '查看潜在学员');
            //打开弹出框
            ps_dialog.dialog('open');
            $("#ps_tbsc").hide();
        },
        //抛入客户池
        pullPool: function () {
            //判断用户是否选中数据
            var row = ps_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            $.messager.confirm('确认', '您确认想要执行该操作吗？', function (r) {
                if (r) {
                    //发送一个ajax请求
                    $.get("/potentitalStudent/delete.do", {id: row.id}, function (data) {
                        if (data.success) {
                            //弹出提示框
                            $.messager.alert('温馨提示', '操作成功！', 'info', function () {

                                //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                                ps_datagrid.datagrid('reload');
                            });
                        } else {
                            //失败弹出提示
                            $.messager.alert('温馨提示', data.msg, 'error');
                        }
                    });
                }
            });
        },
        //转正
        change2official: function () {
            //判断用户是否选中数据
            var row = ps_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //先清空表
            change2official_form.form('clear');
            $(".autoCal").val("");
            //相关的潜在学员属性设置到row的属性上
            if (row.employee) {
                row['potentitalStudent.employee.username'] = row.employee.username;
            }
            //下拉框
            for (field in row) {
                row["potentitalStudent." + field] = row[field];
            }
            row['potentitalStudent.id'] = row.id;
            if (row.clientType) {
                row['potentitalStudent.clientType.id'] = row.clientType.id;
            }
            if (row.emplosourceyee) {
                row['potentitalStudent.source.id'] = row.source.id;
            }
            if (row.education) {
                row['potentitalStudent.education.id'] = row.education.id;
            }
            if (row.classGrade) {
                row['potentitalStudent.classGrade.id'] = row.classGrade.id;
            }
            if (row.campus) {
                row['potentitalStudent.campus.id'] = row.campus.id;
            }
            //回显
            change2official_form.form('load', row);
            //打开弹出框
            change2official_dialog.dialog('open');
        },
        //刷新按钮事件
        reloads: function () {
            ps_datagrid.datagrid('load',{
                stateId:null
            });
        },

        //保存操作
        save: function () {
            ps_form.form('submit', {
                url: "/potentitalStudent/saveOrUpdate.do",
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        ps_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            ps_datagrid.datagrid('reload');
                        });
                    } else {
                        //失败弹出提示
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }
            });
        },
        //考试提交操作
        putTest: function () {
            test_form.form('submit', {
                url: "/register/testRegister.do",
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        test_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            ps_datagrid.datagrid('reload');
                        });
                    } else {
                        //失败弹出提示
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }
            });
        },
        //保存操作
        save1: function () {
            track_form.form('submit', {
                url: "/potentitalStudent/track.do",
                success: function (data) {
                    console.log(data);
                    data = $.parseJSON(data);

                    //成功关闭窗口
                    track_dialog.dialog('close');
                    //弹出提示框
                    $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                        //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                        ps_datagrid.datagrid('load');
                    });
                }
            });
        },
        //提交操作
        put: function () {
            query_form.form('submit', {
                url: "/potentitalStudent/query.do",
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    //关闭窗口
                    query_dialog.dialog('close');
                    //弹出提示框
                    $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                        //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                        ps_datagrid.datagrid('loadData', data);
                    });
                }
            });
        },
        //转正保存操作
        putOfficial: function () {
            change2official_form.form('submit', {
                url: "/officialStudent/saveOrUpdate.do",
                onSubmit: function (param) {

                },
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        change2official_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            ps_datagrid.datagrid('reload');
                        });
                    } else {
                        //失败弹出提示
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }
            });
        },
        queryFor59: function () {
            ps_datagrid.datagrid('load', {
                stateId: 59
            });

        },
        //取消按钮事件
        cancel: function () {
            //关闭弹出框
            ps_dialog.dialog('close');
        },
        cancel1: function () {
            //关闭弹出框
            query_dialog.dialog('close');
        },
        cancelTest: function () {
            //关闭弹出框
            test_dialog.dialog('close');
        },
        cancel2: function () {
            //关闭弹出框
            track_dialog.dialog('close');
        },
        cancelOfficial: function () {
            //关闭弹出框
            change2official_dialog.dialog('close');
        },
        //高级查询
        query: function () {
            //设置标题
            query_dialog.dialog('setTitle', '高级查询潜在学员');
            //打开弹出框
            query_dialog.dialog('open');
        },
        //导出按钮事件
        exportXls: function () {
            window.location.href = "/potentitalStudent/export.do";
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
                url: "/psloyee/input.do",
                success: function (data) {
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        $("#fileInput").dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            ps_datagrid.datagrid('load');
                        });
                    } else {
                        //失败弹出提示
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }
            });
        }
    };
    //3.页面加载完毕统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该方法要执行的方法
        var method = $(this).data("cmd");
        methodObj[method]();
    });

    //1.获取到表格进行初始化,添加属性
    ps_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        pageSize:20,
        pageList:[15,20,25,30,35],
        url: "/potentitalStudent/query.do",
        columns: [[
            {field: 'name', title: '姓名', width: 100},
            {
                field: 'employee', title: '营销人员', width: 100, formatter: function (value, row, index) {
                    return value ? value.username : '';
                }
            },
            {field: 'trackTimes', title: '跟踪次数', width: 80},
            {field: 'prevDate', title: '最后跟踪时间', width: 117},
            {field: 'visitDate', title: '约访时间', width: 117},
            {field: 'nextDate', title: '下次跟进时间', width: 117},
            {field: 'qq', title: 'QQ', width: 120},
            {field: 'tel', title: '电话', width: 124},
            {field: 'school', title: '学校', width: 100},
            {
                field: 'intention', title: '意向程度', width: 100, formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            },
            {
                field: 'campus', title: '意向校区', width: 100, formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            },
            {
                field: 'classGrade', title: '意向班级', width: 120, formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            },
            {
                field: 'status', title: '状态', width: 100, formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            },
            {
                field: 'trackState', title: '是否跟踪', width: 100, formatter: function (value, row, index) {
                    return value ? "已跟踪" : "未跟踪";
                }
            },
            {field: 'remark', title: '备注', width: 100}

        ]],
        toolbar: "#ps_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true,

        onClickRow: function (index, row) {
            if (row.status) {
                if (row.status.id == 59) {
                    //正常
                    $("#change2official_button").linkbutton({
                        disabled: false
                    })
                } else if (row.status.id == 60) {
                    $("#change2official_button").linkbutton({
                        disabled: true
                    })
                }
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
    ps_dialog.dialog({
        title: '潜在学员编辑',
        width: 750,
        height: 420,
        buttons: '#ps_tbsc',
        closed: true
    })
    query_dialog.dialog({
        title: '潜在学员查询',
        width: 750,
        height: 220,
        buttons: '#ps_tbsc1',
        closed: true
    })
    track_dialog.dialog({
        title: '潜在学员跟踪',
        width: 750,
        height: 500,
        buttons: '#ps_tbsc2',
        closed: true
    })
    test_dialog.dialog({
        title: '考试登记',
        width: 230,
        height: 280,
        buttons: '#ps_tbsc3',
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
    /*初始转正弹出框*/
    change2official_dialog.dialog({
        title: '转正操作',
        width: 750,
        height: 420,
        buttons: '#c2o_tbsc',
        closed: true
    });

//学费动态计算
    $(".autoCal").change(function () {
        var planFee = $('[name="payment.planFee"]').val() || 0;//计划学费
        var reduceFee = $('[name="payment.reduceFee"]').val() || 0;//减少费用
        var otherDiscount = $('[name="payment.otherDiscount"]').val() || 0;//其他优惠
        var otherFee = $('[name="payment.otherFee"]').val() || 0;//其他费用
        var paidupFee = $('[name="payment.paidupFee"]').val() || 0;//已付学费
        // if(parseFloat(paidupFee))
        //总学费
        var total = parseFloat(planFee) + parseFloat(otherFee) - parseFloat(reduceFee) - parseFloat(otherDiscount);
        $('[name="payment.totalFee"]').val(total);
        var fee2pay = total - parseFloat(paidupFee);
        $('[name="payment.fee2pay"]').val(fee2pay);
    });
})

