/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var emp_form = $("#emp_form");
    var emp_datagrid = $("#emp_datagrid");
    var emp_dialog = $("#emp_dialog");
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //新增绑定事件
        add: function () {
            //增加页面显示密码框
            $("#password_tr").show();
            //清空表单数据
            emp_form.form('clear');
            //设置标题
            emp_dialog.dialog('setTitle', '新增员工');
            //打开弹出框
            emp_dialog.dialog('open');
        },
        //编辑按钮事件
        edit: function () {
            //判断用户是否选中数据
            var row = emp_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //编辑页面隐藏密码框
            $("#password_tr").hide();
            //清空表单数据
            emp_form.form('clear');
            //用jQuery请求回显角色数据
            $.get("/employee/getRolesByempId.do", {empId: row.id}, function (data) {
                $("#roles_combobox").combobox('setValues', data);
            }, 'json')

            //把部门id设置到员工的dept.id属性上
            if (row.dept) {
                row['dept.id'] = row.dept.id;
            }
            //回显
            emp_form.form('load', row);
            //设置标题
            emp_dialog.dialog('setTitle', '编辑员工');
            //打开弹出框
            emp_dialog.dialog('open');
        },
        //员工状态事件
        changeState: function () {
            //判断用户是否选中数据
            var row = emp_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            $.messager.confirm('确认', '您确认想要执行该操作吗？', function (r) {
                if (r) {
                    //发送一个ajax请求
                    $.get("/employee/changeState.do", {id: row.id}, function (data) {
                        if (data.success) {
                            //弹出提示框
                            $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                                //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                                emp_datagrid.datagrid('reload');
                            });
                        } else {
                            //失败弹出提示
                            $.messager.alert('温馨提示', data.msg, 'error');
                        }
                    });
                }
            });
        },
        //刷新按钮事件
        reloads: function () {
            emp_datagrid.datagrid('reload');
        },

        //保存操作
        save: function () {
            emp_form.form('submit', {
                url: "/employee/saveOrUpdate.do",
                onSubmit: function (param) {
                    //获取到下拉框所选的值
                    var roleIds = $("#roles_combobox").combobox('getValues');
                    for (var i = 0; i < roleIds.length; i++) {
                        //添加参数封装过去
                        param["roles[" + i + "].id"] = roleIds[i];
                    }
                },
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        emp_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            emp_datagrid.datagrid('reload');
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
            emp_dialog.dialog('close');
        },
        //高级查询
        searchForm: function () {
            //获取关键字input
            var keyword = $("#keyword").textbox("getValue");
            var beginDate = $("#beginDate").textbox("getValue");
            var endDate = $("#endDate").textbox("getValue");
            var deptId = $("#deptId").textbox("getValue");
            //让数据表格重新加载比并将查询数据带过去
            emp_datagrid.datagrid('load', {
                beginDate: beginDate,
                endDate: endDate,
                keyword: keyword,
                deptId: deptId
            });
        },
        //导出按钮事件
        exportXls: function () {
            window.location.href = "/employee/export.do";
        },
        //文件上传按钮事件
        inputXls: function () {
            $("#fileInput").dialog('setTitle', '文件上传');
            //打开弹出框
            $("#fileInput").dialog('open');
        },
        //上传
        undo:function () {
            $("#input_form").form('submit', {
                url: "/employee/input.do",
                success: function (data) {
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        $("#fileInput").dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            emp_datagrid.datagrid('load');
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
    emp_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: "/employee/query.do",
        pageSize:20,
        pageList:[15,20,25,30,35],
        columns: [[
            {field: 'username', title: '姓名', width: 100},
            {field: 'realname', title: '真实姓名', width: 100},
            {field: 'tel', title: '电话', width: 100},
            {field: 'email', title: '邮箱', width: 100},
            {
                field: 'dept', title: '部门', width: 100, formatter: function (value, row, index) {
	                return row.dept ? row.dept.name : '';
	            }
            },
            {field: 'inputtime', title: '入职时间', width: 100},
            {
                field: 'admin', title: '超级管理员', width: 100, formatter: function (value, row, index) {
	                return value ? "是" : "否";
	            }
            },
            {
                field: 'state', title: '状态', width: 100, formatter: function (value, row, index) {
	                return value ? "在职" : "<font color='red'>离职</font>";
	            }
            }
        ]],
        toolbar: "#emp_tb",
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
    emp_dialog.dialog({
        title: '员工编辑',
        width: 300,
        height: 360,
        buttons: '#emp_tbsc',
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
})

