/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var classRoom_form = $("#classRoom_form");
    var classRoom_datagrid = $("#classRoom_datagrid");
    var classRoom_dialog = $("#classRoom_dialog");
    var searchClassGrade_dialog = $("#searchClassGrade_dialog");
    var searchClassGrade_datagrid = $("#searchClassGrade_datagrid");

    //2.把所有方法放在一个变量里面
    var methodObj = {
        //新增绑定事件
        add: function () {
            //清空表单数据
            classRoom_form.form('clear');
            //设置标题
            classRoom_dialog.dialog('setTitle', '新增教室');
            //打开弹出框
            classRoom_dialog.dialog('open');
        },
        //编辑按钮事件
        edit: function () {
            //判断用户是否选中数据
            var row = classRoom_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //编辑页面隐藏密码框
            $("#password_tr").hide();
            //清空表单数据
            classRoom_form.form('clear');
            //回显
            classRoom_form.form('load', row);
            //设置标题
            classRoom_dialog.dialog('setTitle', '编辑教室');
            //打开弹出框
            classRoom_dialog.dialog('open');
        },
        //教室状态事件
        changeClassRoomState: function () {
            //判断用户是否选中数据
            var row = classRoom_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            $.messager.confirm('确认', '您确认想要执行该操作吗？', function (r) {
                if (r) {
                    //发送一个ajax请求
                    $.get("/classRoom/changeClassRoomState.do", {id: row.id}, function (data) {
                        if (data.success) {
                            //弹出提示框
                            $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                                //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                                classRoom_datagrid.datagrid('reload');
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
            var keyword = $("#keyword").textbox("setValue",'');
            classRoom_datagrid.datagrid('reload',{
                keyword:null
            });
        },

        //保存操作
        save: function () {
            classRoom_form.form('submit', {
                url: "/classRoom/saveOrUpdate.do",
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        classRoom_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            classRoom_datagrid.datagrid('reload');
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
            classRoom_dialog.dialog('close');
        },
        //高级查询
        searchForm: function () {
            //获取关键字input
            var keyword = $("#keyword").textbox("getValue");
            //让数据表格重新加载比并将查询数据带过去
            classRoom_datagrid.datagrid('load', {
                keyword: keyword,
            });
        },
        //查看教室中的班级信息
        searchClassGrade: function () {
            //判断用户是否选中数据
            var row = classRoom_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            console.log(row)
            if(row.state){
                //发送一个ajax请求判断,当前教室是否有班级存在,没有给予提示信息即可
                $.get("/classGrade/judgeClassRoomByClassGradeIdExtend.do",{id:row.id},function (data) {
                    if(data.success){
                        //1.获取到表格进行初始化,添加属性
                        searchClassGrade_datagrid.datagrid({
                            fit: true,
                            fitColumns: true,

                            url: "/classGrade/classRoomByClassGradeId.do?classRoomId="+row.id,
                            columns: [[
                                {field: 'id', title: '班级编号', width: 100,halign:'center',align:'center'},
                                {field: 'name', title: '班级名称', width: 120,halign:'center',align:'center'},
                                {field: 'stuNumber', title: '学生数量', width: 100,halign:'center',align:'center'},
                                {
                                    field: 'college', title: '所在学院', width: 100, halign:'center',align:'center',formatter: function (value, row, index) {
                                    return row.college ? row.college.name : '';
                                }
                                },
                                {
                                    field: 'state', title: '班级状态', width: 100, halign:'center',align:'center',formatter: function (value, row, index) {

                                    return value ? "已开班" : "<font color='red'>未开班</font>";
                                }
                                },
                                {
                                    field: 'classroom', title: '所在教室', width: 100,halign:'center',align:'center', formatter: function (value, row, index) {
                                    return row.classroom ? row.classroom.name : '';
                                }
                                },
                                {
                                    field: 'teacher', title: '班主任', width: 100, halign:'center',align:'center',formatter: function (value, row, index) {
                                    return row.teacher ? row.teacher.username : '';
                                }
                                }
                            ]],
                            striped: true,
                            singleSelect: true,
                            scrollbarSize:0
                        })
                        //设置标题
                        searchClassGrade_dialog.dialog('setTitle', '查看教室中的班级信息');
                        //打开弹出框
                        searchClassGrade_dialog.dialog('open');
                    }else{
                        $.messager.alert('温馨提示',data.msg,'info');
                    }
                });

            }else{
                $.messager.alert('温馨提示','此教室未启用,没有班级信息,请启用!','info');
            }
        },
    }

    //3.页面加载完毕统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该方法要执行的方法
        var method = $(this).data("cmd");
        methodObj[method]();
    });

    //1.获取到表格进行初始化,添加属性
    classRoom_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: "/classRoom/query.do",
        columns: [[
            {field: 'name', title: '教室名称', width: 100},
            {field: 'address', title: '教室地址', width: 100},
            {field: 'seats', title: '桌位数', width: 100},
            {field: 'remark', title: '教室标语', width: 100},
            {
                field: 'state', title: '教室状态', width: 100, formatter: function (value, row, index) {
                return value ? "已启用" : "<font color='red'>未启用</font>";
            }
            }
        ]],
        toolbar: "#classRoom_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        pageSize:20,
        pageList:[15,20,25,30,35],
        onClickRow: function (index, row) {
            if (!row.state) {
                //修改按钮值
                $("#changeClassRoomState").linkbutton({
                    text: "设置启用"
                })
            } else {
                //修改按钮值
                $("#changeClassRoomState").linkbutton({
                    text: "设置停用"
                })
            }
        }
    })
    /*初始化弹出框*/
    classRoom_dialog.dialog({
        title: '教室编辑',
        width: 300,
        height: 360,
        buttons: '#classRoom_tbsc',
        closed: true
    })
    /*初始化查看教室班级信息弹出窗口*/
    searchClassGrade_dialog.dialog({
        title: '查看教室中的班级信息',
        width: 600,
        height: 90,
        closed: true
    })
})

