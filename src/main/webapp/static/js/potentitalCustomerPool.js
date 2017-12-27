$(function () {
    //1.变量抽取
    var ps_form = $("#ps_form");
    var pool_form = $("#pool_form");
    var ps_datagrid = $("#ps_datagrid");
    var ps_dialog = $("#ps_dialog");
    var pool_dialog = $("#pool_dialog");
    //2.把所有方法放在一个变量里面
    var methodObj = {

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
            if (row['sn']) {
                row['introducerStu.id'] = row.sn;
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
            $("input").prop('disabled', 'true')
        },

        //刷新按钮事件
        reloads: function () {
            ps_datagrid.datagrid('reload');
        },

        //移交给我
        pull: function () {
            //判断用户是否选中数据
            var row = ps_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            $.messager.confirm('确认', '您确认想要执行该操作吗？', function (r) {
                if (r) {
                    //发送一个ajax请求
                    $.get("/potentitalCustomerPool/delete.do", {id: row.id, eId: 1}, function (data) {
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
        //指派给
        put: function () {
            //判断用户是否选中数据
            var row = ps_datagrid.datagrid('getSelected');
            var employeeId = $("#employeeId").textbox("getValue");
            $.messager.confirm('确认', '您确认想要执行该操作吗？', function (r) {
                if (r) {
                    //发送一个ajax请求
                    $.get("/potentitalCustomerPool/delete.do", {id: row.id, eId: employeeId}, function (data) {
                        if (data.success) {
                            //弹出提示框
                            $.messager.alert('温馨提示', '操作成功！', 'info', function () {

                                //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                                pool_dialog.dialog('close');
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

        //取消按钮事件
        cancel: function () {
            //关闭弹出框
            ps_dialog.dialog('close');
        },
        cancel1: function () {
            //关闭弹出框
            pool_dialog.dialog('close');
        },
        //抓拍给我
        push: function () {
            //判断用户是否选中数据
            var row = ps_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //回显
            pool_form.form('load', row);
            //设置标题
            pool_dialog.dialog('setTitle', '指派给');
            //打开弹出框
            pool_dialog.dialog('open');
        },
    }
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
        url: "/potentitalCustomerPool/query.do",
        pageSize:20,
        pageList:[15,20,25,30,35],
        columns: [[
            {field: 'name', title: '姓名', width: 100},
            {field: 'trackTimes', title: '跟踪次数', width: 100},
            {field: 'prevDate', title: '最后跟踪时间', width: 100},
            {field: 'qq', title: 'QQ', width: 100},
            {field: 'tel', title: '电话', width: 100},
            {field: 'school', title: '学校', width: 100},
            {field: 'intention', title: '意向程度', width: 100,formatter:function (value,row,index) {
                return value?value.name:'';
            }},
            {field: 'campus', title: '意向校区', width: 100,formatter:function (value,row,index) {
                return  value?value.name:'';
            }},
            {field: 'classGrade', title: '意向班级', width: 100,formatter:function (value,row,index) {
                return  value?value.name:'';
            }},
            {field: 'remark', title: '摘要', width: 100}

        ]],
        toolbar: "#ps_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        onClickRow: function (index, row) {
            if (!row.state) {
                //修改按钮值
                $("#changeState").linkbutton({
                   text: "设置在职"
                })
            } else {
                //修改按钮值
                $("#changeState").linkbutton({
                   text: "设置离职"
                })
            }

        }
    })
    /*初始化弹出框*/
    ps_dialog.dialog({
        title: '潜在学员编辑',
        width: 700,
        height: 500,
        closed: true
    })
    pool_dialog.dialog({
        title: '指派给',
        width: 200,
        height: 300,
        buttons: '#ps_tbsc1',
        closed: true
    })

})

