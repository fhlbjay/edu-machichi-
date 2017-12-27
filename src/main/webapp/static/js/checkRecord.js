/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var checkRecord_datagrid = $("#checkRecord_datagrid");
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //	签到
    	checkIn: function () {
        	//判断用户是否选中数据
            var row = checkRecord_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中签到人员!", 'info');
                return;
            };
            $.post("/checkRecord/checkIn.do", function (data) {
            	console.log(data);
                if (data.success) {
                	$.messager.alert('温馨提示', data.msg, 'info');
                } else {
                	$.messager.alert('温馨提示', data.msg, 'info');
                }
                checkRecord_datagrid.datagrid('reload');
            });
        },
        //	下班
        checkOut: function () {
        	var row = checkRecord_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中下班人员!", 'info');
                return;
            };
            $.post("/checkRecord/checkOut.do", function (data) {
            	console.log(data);
                if (data.success) {
                	$.messager.alert('温馨提示', data.msg, 'info');
                } else {
                	$.messager.alert('温馨提示', data.msg, 'info');
                }
                checkRecord_datagrid.datagrid('reload');
            });
        },
        //	补签
        checkFillUp: function () {
        	var row = checkRecord_datagrid.datagrid('getSelected');
        	if (!row) {
        		$.messager.alert('温馨提示', "请选中补签人员!", 'info');
        		return;
        	};
        	console.log(row.checkDate);
        	$.post("/checkRecord/checkFillUp.do",{checkDate:row.checkDate}, function (data) {
        		console.log(data);
        		if (data.success) {
        			$.messager.alert('温馨提示', data.msg, 'info');
        			checkRecord_datagrid.datagrid('reload');
        		} else {
        			$.messager.alert('温馨提示', data.msg, 'info');
        		}
        	});
        },
        //	高级查询
        searchForm: function () {
            //获取关键字input
            var keyword = $("#keyword").textbox("getValue");
            var beginDate = $("#beginDate").textbox("getValue");
            var endDate = $("#endDate").textbox("getValue");
            var deptId = $("#deptId").textbox("getValue");
            //让数据表格重新加载比并将查询数据带过去
            checkRecord_datagrid.datagrid('load', {
                beginDate: beginDate,
                endDate: endDate,
                keyword: keyword,
                deptId: deptId
            });
        },
        //删除按钮事件
        remove:function () {
	        //判断用户是否选中数据
	        var row = checkRecord_datagrid.datagrid('getSelected');
	        if (!row) {
	            $.messager.alert('温馨提示', "请选中一条数据!", 'info');
	            return;
	        }
	        //发送ajax请求
	        $.get('/checkRecord/delete.do', {id: row.id}, function (data) {
	            if (data.success) {
	                //成功关闭窗口
	                checkRecord_dialog.dialog('close');
	                //弹出提示框
	                $.messager.alert('温馨提示', '操作成功！', 'info', function () {
	                    //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
	                    checkRecord_datagrid.datagrid('reload');
	                });
	            } else {
	                //失败弹出提示
	                $.messager.alert('温馨提示', data.msg, 'error');
	            }
	        })
	    },
        //刷新按钮事件
        reloads: function () {
            checkRecord_datagrid.datagrid('reload');
        },

        //保存操作
        save: function () {
            checkRecord_form.form('submit', {
                url: "/checkRecord/saveOrUpdate.do",
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        checkRecord_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            checkRecord_datagrid.datagrid('reload');
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
            checkRecord_dialog.dialog('close');
        }
    }
    //3.页面加载完毕统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该方法要执行的方法
        var method = $(this).data("cmd");
        methodObj[method]();
    });

    //1.获取到表格进行初始化,添加属性
    checkRecord_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: "/checkRecord/query.do",
        scrollbarSize:0,
        pageSize:20,
        pageList:[15,20,25,30,35],
        columns: [[
            {field: 'id', title: '编号', width: 50, align: 'center'},
            {
            	field: 'employee', title: '员工名称', width: 60, align: 'center', formatter: function (value, row, index) {
	                return row.employee ? row.employee.username : '';
	            }
            },
            {field: 'checkDate', title: '考勤日期', width: 100, align: 'center'},
            {field: 'checkInTime', title: '签到时间', width: 100, align: 'center'},
            {field: 'checkOutTime', title: '下班时间', width: 100, align: 'center'},
            {field: 'checkInState', title: '签到状态', width: 60, align: 'center', formatter: function (value, row, index) {
            	if (row.checkInState == 0) {
					return "无";
				} else if (row.checkInState == 1) {
					return "正常";
				} else if (row.checkInState == 2) {
					return "<font color='red'>迟到</font>";
				} else if (row.checkInState == 3){
					return "<span background-color='yellow'>已补签</span>";
				}
            }},
            {field: 'checkOutState', title: '下班状态', width: 60, align: 'center', formatter: function (value, row, index) {
            	if (row.checkOutState == 0) {
					return "无";
				} else if (row.checkOutState == 1) {
					return "正常";
				} else if (row.checkOutState == 2) {
					return "<font color='red'>早退</font>";
				} else if (row.checkOutState == 3){
					return "<span background-color='yellow'>已补签</span>";
				}
            }},
            {field: 'checkFillUp', title: '补签日期和时间', width: 150, align: 'center'}
        ]],
        toolbar: "#checkRecord_tb",
        striped: true,
        pagination: true,
        singleSelect: true
    })
})

