/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var classGrade_form = $("#classGrade_form");
    var classGrade_datagrid = $("#classGrade_datagrid");
    var classGrade_dialog = $("#classGrade_dialog");
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //新增绑定事件
        add: function () {
            //增加页面显示密码框
            $("#password_tr").show();
            //清空表单数据
            classGrade_form.form('clear');
            //设置标题
            classGrade_dialog.dialog('setTitle', '新增班级');
            //打开弹出框
            classGrade_dialog.dialog('open');
        },
        //编辑按钮事件
        edit: function () {
            //判断用户是否选中数据
            var row = classGrade_datagrid.datagrid('getSelected');
            console.log(row);
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //清空表单数据
            classGrade_form.form('clear');

            //把部门id设置到班级的classGrade.id属性上
            if (row.college) {
                row['college.id'] = row.college.id;
            }
            if (row.classroom) {
                row['classroom.id'] = row.classroom.id;
            }
            if (row.teacher) {
                row['teacher.id'] = row.teacher.id;
            }
            //回显
            classGrade_form.form('load', row);
            //设置标题
            classGrade_dialog.dialog('setTitle', '编辑班级');
            //打开弹出框
            classGrade_dialog.dialog('open');
        },
        //删除按钮事件
        remove: function () {
            //判断用户是否选中数据
            var row = classGrade_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //发送ajax请求
            $.get('/classGrade/delete.do', {id: row.id}, function (data) {
                if (data.success) {
                    //成功关闭窗口
                    classGrade_dialog.dialog('close');
                    //弹出提示框
                    $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                        //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                        classGrade_datagrid.datagrid('reload');
                    });
                } else {
                    //失败弹出提示
                    $.messager.alert('温馨提示', data.msg, 'error');
                }
            })
        },
        //刷新按钮事件
        reloads: function () {
            classGrade_datagrid.datagrid('reload');
        },
        //打开弹出框选择班主任
        changeTeacher: function () {
            //判断用户是否选中数据
            var row = classGrade_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //清空表单数据
            $("#changeTeacher_form").form('clear');
            $("#changeTeacher_dialog").dialog('setTitle', '选择班主任');
            //打开弹出框
            $("#changeTeacher_dialog").dialog('open');
        }
        ,
        //班主任事件
        confirm: function () {

            $("#changeTeacher_form").form('submit', {

                url: "/classGrade/changeTeacher.do",
                onSubmit: function (param) {
                    //获取到下拉框所选的值
                    var row = classGrade_datagrid.datagrid('getSelected');
                        //添加参数封装过去
                        param['id'] = row.id;
                },
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        $("#changeTeacher_dialog").dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            classGrade_datagrid.datagrid('reload');
                        });
                    } else {
                        //失败弹出提示
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }
            });
        },
        //班级状态事件
        changeClassGradeState: function () {
            //判断用户是否选中数据
            var row = classGrade_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            $.messager.confirm('确认', '您确认想要执行该操作吗？', function (r) {
                if (r) {
                    //发送一个ajax请求
                    $.get("/classGrade/changeClassGradeState.do", {id: row.id}, function (data) {
                        if (data.success) {
                            //弹出提示框
                            $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                                //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                                classGrade_datagrid.datagrid('reload');
                            });
                        } else {
                            //失败弹出提示
                            $.messager.alert('温馨提示', data.msg, 'error');
                        }
                    });
                }
            });
        },
        //保存操作
        save: function () {
            classGrade_form.form('submit', {
                url: "/classGrade/saveOrUpdate.do",
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        classGrade_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            classGrade_datagrid.datagrid('reload');
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
            classGrade_dialog.dialog('close');
        }
        ,
        //导出按钮事件
        exportXls: function () {
            window.location.href = "/classGrade/export.do";
        }
        ,
        //文件上传按钮事件
        inputXls: function () {
            $("#fileInput").dialog('setTitle', '文件上传');
            //打开弹出框
            $("#fileInput").dialog('open');
        }
        ,
        //上传
        undo: function () {
            $("#input_form").form('submit', {
                url: "/classGrade/input.do",
                success: function (data) {
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        $("#fileInput").dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            classGrade_datagrid.datagrid('load');
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
    classGrade_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: "/classGrade/query.do",
        columns: [[
            {field: 'id', title: '班级编号', width: 100},
            {field: 'name', title: '班级名称', width: 100},
            {field: 'stuNumber', title: '学生数量', width: 100},
            {
                field: 'college', title: '所在学院', width: 100, formatter: function (value, row, index) {
                return row.college ? row.college.name : '';
            }
            },
            {
                field: 'state', title: '班级状态', width: 100, formatter: function (value, row, index) {
                return value ? "已开班" : "<font color='red'>未开班</font>";
            }
            },
            {
                field: 'classroom', title: '所在教室', width: 100, formatter: function (value, row, index) {
                return row.classroom ? row.classroom.name : '';
            }
            },
            {
                field: 'teacher', title: '班主任', width: 100, formatter: function (value, row, index) {
                return row.teacher ? row.teacher.username : '';
            }
            }
        ]],
        toolbar: "#classGrade_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        pageSize:20,
        pageList:[15,20,25,30,35],
        onClickRow: function (index, row) {
            if (!row.teacher) {
                //修改按钮值
                $("#changeTeacher").linkbutton({
                    text: "分配班主任"
                })
            } else {
                //修改按钮值
                $("#changeTeacher").linkbutton({
                    text: "更换班主任"
                })
            }
            if (!row.state) {
                //修改按钮值
                $("#changeClassGradeState").linkbutton({
                    text: "设置开班"
                })
            } else {
                //修改按钮值
                $("#changeClassGradeState").linkbutton({
                    text: "设置未开班"
                })
            }
        }
    })
    /*初始化弹出框*/
    classGrade_dialog.dialog({
        title: '班级编辑',
        width: 300,
        height: 360,
        buttons: '#classGrade_tbsc',
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
    /*初始化分配班主任弹出窗口*/
    $("#changeTeacher_dialog").dialog({
        title: '选择班主任',
        width: 250,
        height: 150,
        buttons: '#changeTeacher_tbsc',
        closed: true
    })




})

