/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var schedule_datagrid = $("#schedule_datagrid");
    //2.把所有方法放在一个变量里面
    var methodObj = {

        //刷新按钮事件
        reloads: function () {
            var classRoomId = $("#classRoom").textbox("setValue", '');
            var classGradeId = $("#classGrade").textbox("setValue", '');
            var teacherId = $("#teacher").textbox("setValue", '');
            var beginDate = $("#beginDate").textbox("setValue", '');
            var endDate = $("#endDate").textbox("setValue", '');
            schedule_datagrid.datagrid('load', {
                classRoomId: "",
                classGradeId: "",
                teacherId: "",
                beginDate: "",
                endDate: "",
            });
            $("#cg_datagrid").datagrid('load', {
                classRoomId: "",
                classGradeId: "",
                teacherId: "",
                beginDate: "",
                endDate: "",
            });
        },
        //高级查询
        searchForm: function () {
            //获取关键字input
            var classRoomId = $("#classRoom").textbox("getValue");
            var classGradeId = $("#classGrade").textbox("getValue");
            var teacherId = $("#teacher").textbox("getValue");
            var beginDate = $("#beginDate").textbox("getValue");
            var endDate = $("#endDate").textbox("getValue");
            //让数据表格重新加载比并将查询数据带过去
            schedule_datagrid.datagrid('load', {
                classRoomId: classRoomId,
                classGradeId: classGradeId,
                teacherId: teacherId,
                beginDate: beginDate,
                endDate: endDate,
            });
        },
        //下载模板事件
        downModel: function () {
            window.location.href = "/schedule/downScheduleModal.do";
        },
        //导出按钮事件
        exportXls: function () {
            window.location.href = "/schedule/export.do";
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
                url: "/schedule/input.do",
                success: function (data) {
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        $("#fileInput").dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            var classRoomId = $("#classRoom").textbox("setValue", '');
                            var classGradeId = $("#classGrade").textbox("setValue", '');
                            var teacherId = $("#teacher").textbox("setValue", '');
                            var beginDate = $("#beginDate").textbox("setValue", '');
                            var endDate = $("#endDate").textbox("setValue", '');
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            schedule_datagrid.datagrid('reload', {
                                classRoomId: "",
                                classGradeId: "",
                                teacherId: "",
                                beginDate: "",
                                endDate: "",
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
    schedule_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: "/schedule/query.do",
        columns: [[
            {field: 'subDate', title: '日期', width: 120, halign: 'center', align: 'center'},
            {
                field: 'week', title: '星期', width: 100, align: 'center', formatter: function (value, row, index) {
                var d = new Date(row.subDate);
                var weekday = new Array(7);
                weekday[0] = "星期天";
                weekday[1] = "星期一";
                weekday[2] = "星期二";
                weekday[3] = "星期三";
                weekday[4] = "星期四";
                weekday[5] = "星期五";
                weekday[6] = "星期六";
                return weekday[d.getDay()];
            }
            },
            {
                field: 'classGrade', title: '班级', width: 100, halign: 'center', align: 'center', formatter: function (value, row, index) {
                return row.classGrade ? row.classGrade.name : '';
            }
            },
            {field: 'subject', title: '课程', width: 100, halign: 'center', align: 'center'},

            {
                field: 'teacher', title: '上课老师', width: 100, halign: 'center', align: 'center', formatter: function (value, row, index) {
                return row.teacher ? row.teacher.username : '';
            }
            },
            {
                field: 'classTeacher', title: '班主任', width: 100, halign: 'center', align: 'center', formatter: function (value, row, index) {
                return row.teacher ? row.teacher.username : '';
            }
            },
            {
                field: 'classRoom', title: '教室', width: 100, halign: 'center', align: 'center', formatter: function (value, row, index) {
                return row.classRoom ? row.classRoom.name : '';
            }
            },
            {field: 'remark', title: '备注', width: 100, halign: 'center', align: 'center'},

            {
                field: 'state', title: '课表状态', width: 100, halign: 'center', align: 'center', formatter: function (value, row, index) {
                var Date1 = new Date(row.subDate);
                var Date2 = new Date();
                if (Date1.getTime() > Date2.getTime()) {
                    return "<font color='red'>未上</font>";
                } else {
                    return "<font color='blue'>已上</font>";
                }
            }
            },
        ]],
        toolbar: "#schedule_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        pageSize: 20,
        pageList: [15, 20, 25, 30, 35]
    })
    /*初始化文件上传弹出框*/
    $("#fileInput").dialog({
        title: '文件上传',
        width: 250,
        height: 150,
        buttons: '#input_tbsc',
        closed: true
    })

//初始化左下内容的datagrid滚动
    $("#cg_datagrid").datagrid({
        scrollbarSize: 5,
        fit: true,
        fitColumns: true,
        striped: true,

        url: "/schedule/selectcurrentDaySchedual.do?currentDayTime=" + new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate(),
        singleSelect: true,
        columns: [[
            {
                field: 'classGrade', title: '班级', width: 170, halign: 'center', align: 'center', formatter: function (value, row, index) {
                return row.classGrade ? row.classGrade.name : '';
            }
            },
            {
                field: 'classRoom', title: '教室', width: 100, halign: 'center', align: 'center', formatter: function (value, row, index) {
                return row.classRoom ? row.classRoom.name : '';
            }
            },
            {
                field: 'teacher', title: '老师', width: 100, halign: 'center', align: 'center', formatter: function (value, row, index) {
                return row.teacher ? row.teacher.username : '';
            }
            }
        ]],
        onDblClickRow: function (index, row) {
            var scheduleId = row.id;
            schedule_datagrid.datagrid("load", {
                scheduleId: scheduleId
            });
        }
    });
    //用户选择日期时触发
    $('#calendar').calendar({
        firstDay: 1,
        onSelect: function (date) {
            var data = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
            schedule_datagrid.datagrid("load", {
                beginDate: data,
                endDate: data
            });
        }
    });
})

