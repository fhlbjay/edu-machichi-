/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var news_datagrid = $("#news_datagrid");
    var news_form = $("#news_form");
    var news_dialog = $("#news_dialog");
    //2.把所有方法放在一个变量里面
    var methodObj = {
    		
    		
            //新增绑定事件
            add: function () {
            	console.log(1);
                //清空表单数据
                news_form.form('clear');
                //设置标题
                news_dialog.dialog('setTitle', '新增一篇文章');
                //打开弹出框
                news_dialog.dialog('open');
            },
            //编辑按钮事件
            edit: function () {
                //判断用户是否选中数据
                var row = news_datagrid.datagrid('getSelected');
                if (!row) {
                    $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                    return;
                }
                //清空表单数据
                news_form.form('clear');
                news_form.form('load', row);
                
                news_dialog.dialog('setTitle', '编辑文章');
                news_dialog.dialog('open');
            },
            
            
        //	高级查询
        searchForm: function () {
            //获取关键字input
            var keyword = $("#keyword").textbox("getValue");
            var beginDate = $("#beginDate").textbox("getValue");
            var endDate = $("#endDate").textbox("getValue");
            var deptId = $("#deptId").textbox("getValue");
            //让数据表格重新加载比并将查询数据带过去
            news_datagrid.datagrid('load', {
                beginDate: beginDate,
                endDate: endDate,
                keyword: keyword,
                deptId: deptId
            });
        },
        //删除按钮事件
        remove:function () {
	        //判断用户是否选中数据
	        var row = news_datagrid.datagrid('getSelected');
	        if (!row) {
	            $.messager.alert('温馨提示', "请选中一条数据!", 'info');
	            return;
	        }
	        //发送ajax请求
	        $.get('/news/delete.do', {id: row.id}, function (data) {
	            if (data.success) {
	                //成功关闭窗口
	                news_dialog.dialog('close');
	                //弹出提示框
	                $.messager.alert('温馨提示', '操作成功！', 'info', function () {
	                    //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
	                    news_datagrid.datagrid('reload');
	                });
	            } else {
	                //失败弹出提示
	                $.messager.alert('温馨提示', data.msg, 'error');
	            }
	        })
	    },
        //刷新按钮事件
        reloads: function () {
            news_datagrid.datagrid('reload');
        },

        //保存操作
        save: function () {
            news_form.form('submit', {
                url: "/news/saveOrUpdate.do",
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        news_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            news_datagrid.datagrid('reload');
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
            news_dialog.dialog('close');
        }
    }
    //3.页面加载完毕统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该方法要执行的方法
        var method = $(this).data("cmd");
        methodObj[method]();
    });

    //1.获取到表格进行初始化,添加属性
    news_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: "/news/query.do",
        scrollbarSize:0,
        columns: [[
            {field: 'id', title: '编号', width: 50, align: 'center'},
            {
            	field: 'employee', title: '作者', width: 60, align: 'center', formatter: function (value, row, index) {
            		console.log(row);
	                return row.employee ? row.employee.username : '';
	            }
            },
            {field: 'title', title: '标题', width: 100, align: 'center'},
            {field: 'content', title: '内容', width: 100, align: 'center'},
            {field: 'writeTime', title: '时间', width: 100, align: 'center'}
        ]],
        toolbar: "#news_tb",
        striped: true,
        pagination: true,
        singleSelect: true
    });
    
    /*初始化弹出框*/
    news_dialog.dialog({
        title: '文章编辑',
        width: 300,
        height: 150,
        buttons: '#schoolContacts_tbsc',
        closed: true
    })
})

