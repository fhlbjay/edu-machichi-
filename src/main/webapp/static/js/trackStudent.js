$(function () {
    //1.变量抽取
    var ps_form = $("#ps_form");
    var track_form = $("#track_form");
    var test_form = $("#test_form");
    var ps_datagrid = $("#ps_datagrid");
    var ps_dialog = $("#ps_dialog");
    var track_dialog = $("#track_dialog");
    var test_dialog = $("#test_dialog");
    //2.把所有方法放在一个变量里面
    var methodObj = {
        audit:function () {
            //判断用户是否选中数据
            var row = ps_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            if (row['communicationMethod']) {
                row['communicationMethod.name'] = row.communicationMethod.name;
                row['communicationMethod.id'] = row.communicationMethod.id;
            }
            if (row['employee']) {
                row['employee.username'] = row.employee.username;
                row['employee.id'] = row.employee.id;
            }
            if(row['score']){
                row['score.id']=row.score.id;
            }
            //清空表单数据
            test_form.form('clear');
            //回显
            test_form.form('load', row);
            //设置标题
            test_dialog.dialog('setTitle', '审核');
            //打开弹出框
            test_dialog.dialog('open');
        },
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
            if (row['introducerStu']) {
                row['introducerStu.id'] = row.introducerStu.id;
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
            track_form.form('load', row);
            //设置标题
            track_dialog.dialog('setTitle', '编辑跟踪学员');
            //打开弹出框
            track_dialog.dialog('open');
            $("#ps_tbsc").show();
        },

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
            if (row['communicationMethod']) {
                row['communicationMethod.id'] = row.communicationMethod.id;
            }
            if (row['tracePurpose']) {
                row['tracePurpose.id'] = row.tracePurpose.id;
            }
            if (row['trackTimes']) {
                row['trackTimes'] = row.trackTimes + 1;
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
            if (row['introducerStu']) {
                row['introducerStu.id'] = row.introducerStu.id;
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
            track_form.form('load', row);
            //设置标题
            track_dialog.dialog('setTitle', '查看跟踪学员');
            //打开弹出框
            track_dialog.dialog('open');
            $("#ps_tbsc").hide();
        },


        //刷新按钮事件
        reloads: function () {
            ps_datagrid.datagrid('reload');
        },

        //保存操作
        save1: function () {
            test_form.form('submit', {
                url: "/trackStudent/update.do",
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
        save: function () {
            track_form.form('submit', {
                url: "/trackStudent/track.do",
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

        cancel1: function () {
            //关闭弹出框
            test_dialog.dialog('close');
        },
        cancel: function () {
            //关闭弹出框
            track_dialog.dialog('close');
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
        pageSize:20,
        pageList:[15,20,25,30,35],
        url: "/trackStudent/query.do",
        columns: [[
            {field: 'name', title: '姓名', width: 100},
            {field: 'employee', title: '咨询人员', width: 100,formatter:function (value,row,index) {
                return value?value.username:'';
            }},
            {field: 'tracePurpose', title: '跟进目的', width: 100,formatter:function (value,row,index) {
                return value?value.name:'';
            }},
            {field: 'prevDate', title: '本次跟进时间', width: 100},
            {field: 'nextDate', title: '下次跟进时间', width: 100},
            {field: 'qq', title: 'QQ', width: 100},
            {field: 'tel', title: '电话', width: 100},
            {field: 'intention', title: '意向程度', width: 100,formatter:function (value,row,index) {
                return value?value.name:'';
            }},

            {field: 'auditContext', title: '审核说明', width: 100},
            {field: 'auditResult', title: '审核状态', width: 100,formatter:function (value,row,index) {
                return value ? "已审核":"未审核";
            }}

        ]],
        toolbar: "#ps_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true

    })
    track_dialog.dialog({
        title: '潜在学员跟踪',
        width: 720,
        height: 500,
        buttons: '#ps_tbsc',
        closed: true
    })
    test_dialog.dialog({
        title: '考试登记',
        width: 165,
        height: 400,
        buttons: '#ps_tbsc1',
        closed: true
    })
})

