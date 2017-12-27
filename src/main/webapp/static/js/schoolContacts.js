/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var schoolContacts_form = $("#schoolContacts_form");
    var schoolContacts_datagrid = $("#schoolContacts_datagrid");
    var schoolContacts_dialog = $("#schoolContacts_dialog");
    
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //新增绑定事件
        add: function () {
            //清空表单数据
            schoolContacts_form.form('clear');
            //设置标题
            schoolContacts_dialog.dialog('setTitle', '新增学校联系人');
            //打开弹出框
            schoolContacts_dialog.dialog('open');
        },
        //编辑按钮事件
        edit: function () {
            //判断用户是否选中数据
            var row = schoolContacts_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //清空表单数据
            schoolContacts_form.form('clear');
           /* if(row['bigCustomer']){
                row['schoolId']=row.bigCustomer.id;
            }*/
            if(row['bigCustomer']){
                row['bigCustomer.id']=row.bigCustomer.id;
            }
            schoolContacts_form.form('load', row);
            //设置标题
            schoolContacts_dialog.dialog('setTitle', '编辑学校联系人');
            //打开弹出框
            schoolContacts_dialog.dialog('open');
        },
        //	学校联系人查看事件
        singleSelect: function () {
            //判断用户是否选中数据
            var row = schoolContacts_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
	       /* if(row['bigCustomer']){
                row['schoolId']=row.bigCustomer.id;
            }*/
	        if(row['bigCustomer']){
                row['bigCustomer.id']=row.bigCustomer.id;
            }
            schoolContacts_form.form('load', row);

	        //设置标题,打开弹出框
	        schoolContacts_dialog.dialog('setTitle', '查看学校联系人');
	        schoolContacts_dialog.dialog('open');
	        $("#schoolContacts_form :input").attr("readonly",true);
        },
        //删除按钮事件
        remove:function () {
	        //判断用户是否选中数据
	        var row = schoolContacts_datagrid.datagrid('getSelected');
	        if (!row) {
	            $.messager.alert('温馨提示', "请选中一条数据!", 'info');
	            return;
	        }
	        $.messager.confirm('删除确认', '您确认想要删除此数据吗？', function (r) {
	        	if (r) {
			        //发送ajax请求
			        $.get('/schoolContacts/delete.do', {id: row.id}, function (data) {
			            if (data.success) {
			                //成功关闭窗口
			                schoolContacts_dialog.dialog('close');
			                //弹出提示框
			                $.messager.alert('温馨提示', '操作成功！', 'info', function () {
			                    //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
			                    schoolContacts_datagrid.datagrid('reload');
			                });
			            } else {
			                //失败弹出提示
			                $.messager.alert('温馨提示', data.msg, 'error');
			            }
			        })
	        	}
            });
	    },
        //刷新按钮事件
        reloads: function () {
            $("#keyword").textbox("setValue","");
            $("#beginDate").textbox("setValue","");
            $("#endDate").textbox("setValue","");
            $("#schoolId").textbox("setValue","");
            schoolContacts_datagrid.datagrid('reload', {
                beginDate:null,
                endDate:null,
                keyword:null,
                schoolId:null,
            });
        },
      //高级查询
        searchForm: function () {
            //获取关键字input
            var keyword = $("#keyword").textbox("getValue");
            var beginDate = $("#beginDate").textbox("getValue");
            var endDate = $("#endDate").textbox("getValue");
            var schoolId = $("#schoolId").textbox("getValue");
            //让数据表格重新加载比并将查询数据带过去
            schoolContacts_datagrid.datagrid('load', {
                beginDate: beginDate,
                endDate: endDate,
                keyword: keyword,
                schoolId: schoolId
            });
        },
        
        
        //保存操作
        save: function () {
            schoolContacts_form.form('submit', {
                url: "/schoolContacts/saveOrUpdate.do",
                success: function (data) {
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功后关闭窗口,弹出提示框
                        schoolContacts_dialog.dialog('close');
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            schoolContacts_datagrid.datagrid('reload');
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }
            });
        },
        //	save窗口的重置按钮
        reset:function(){
            schoolContacts_form.form('clear');
            schoolContacts_dialog.dialog('open');
        },
        //	cancel按钮事件,关闭弹出框
        cancel: function () {
	        schoolContacts_dialog.dialog('close');
        }
    }
    //3.页面加载完毕统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该方法要执行的方法
        var method = $(this).data("cmd");
        methodObj[method]();
    });

    //1.获取到表格进行初始化,添加属性
    schoolContacts_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        pageSize:20,
        pageList:[15,20,25,30,35],
        url: "/schoolContacts/query.do",
        columns: [[
            {field: 'id', title: '编号', width: 100},
            {field: 'contactsName', title: '姓名', width: 100},
            {field: 'contactsGender', title: '性别', width: 100, formatter: function (value, row, index) {
                	return value ? "男" : "女";
            	}
            },
            {field: 'contactsBirthday', title: '生日', width: 100},
            {field: 'bigCustomer', title: '所属学校', width: 100,formatter:function(value,row,index){
                    return value ? value.name:'';
            }},
            {field: 'contactsDepartment', title: '所属部门', width: 100},
            {field: 'contactsPosition', title: '职务', width: 100},
            {field: 'contactsTel', title: '电话', width: 100},
            {field: 'contactsQQ', title: 'QQ', width: 100},
            {field: 'contactsEmail', title: '邮箱', width: 100},
            {field: 'contactsMain', title: '主要联系人', width: 100, formatter: function (value, row, index) {
	            	return value ? "是" : "否";
	        	}
            }
        ]],
        toolbar: "#schoolContacts_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true
    })
    /*初始化弹出框*/
    schoolContacts_dialog.dialog({
        title: '学校联系人编辑',
        width: 800,
        height: 420,
        buttons: '#schoolContacts_tbsc',
        closed: true
    })
})

