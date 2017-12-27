/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var sydtitem_form = $("#sydtitem_form");
    var sydtitem_datagrid = $("#sydtitem_datagrid");
    var sydtitem_dialog = $("#sydtitem_dialog");
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //新增绑定事件
        additem: function () {
            //增加页面显示密码框
            $("#password_tr").show();
            //清空表单数据
            sydtitem_form.form('clear');
            //设置标题
            sydtitem_dialog.dialog('setTitle', '新增数据字典明细');
            //打开弹出框
            sydtitem_dialog.dialog('open');
        },
        //编辑按钮事件
        edititem: function () {
            //判断用户是否选中数据
            var row = sydtitem_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //编辑页面隐藏密码框
            $("#password_tr").hide();
            //清空表单数据
            sydtitem_form.form('clear');
            //把数据字典明细id设置到数据字典明细的sydtitem.id属性上
            //row['sydtitem.id'] = row.sydtitem.id;
            //回显
            sydtitem_form.form('load', row);
            //设置标题
            sydtitem_dialog.dialog('setTitle', '编辑数据字典明细');
            //打开弹出框
            sydtitem_dialog.dialog('open');
        },
        //删除按钮事件
        removeitem: function () {
            //判断用户是否选中数据
            var row = sydtitem_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //发送ajax请求
            $.get('/systemdictionaryitem/delete.do', {id: row.id}, function (data) {
                if (data.success) {
                    //成功关闭窗口
                    sydtitem_dialog.dialog('close');
                    //弹出提示框
                    $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                        //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                        sydtitem_datagrid.datagrid('reload');
                    });
                } else {
                    //失败弹出提示
                    $.messager.alert('温馨提示', data.msg, 'error');
                }
            })
        },
        //刷新按钮事件
        reloadsitem: function () {
            sydtitem_datagrid.datagrid('reload');
        },

        //保存操作
        saveitem: function () {
            sydtitem_form.form('submit', {
                url: "/systemdictionaryitem/saveOrUpdate.do",
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        sydtitem_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            sydtitem_datagrid.datagrid('reload');
                        });
                    } else {
                        //失败弹出提示
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }
            });
        },
        //取消按钮事件
        cancelitem: function () {
            //关闭弹出框
            sydtitem_dialog.dialog('close');
        }
    }
    //3.页面加载完毕统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该方法要执行的方法
        var method = $(this).data("cmd");
        methodObj[method]();
    });
//增加一个无用的注释
    //1.获取到表格进行初始化,添加属性
    sydtitem_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        pageSize:20,
        pageList:[15,20,25,30,35],
        url: "/systemdictionaryitem/query.do",
        columns: [[
            {
                field: 'parent', title: '字典条目', width: 100, formatter: function (value, row, index) {
                return value ? value.name : "";
            }
            },
            {field: 'name', title: '字典明细名称', width: 100},
            {field: 'intro', title: '字典明细简介', width: 100}
        ]],
        toolbar: "#sydtitem_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        onClickRow: function (index, row) {
            if (!row.state) {
                //修改按钮值
                $("#changeState").linkbutton({
                    text: "存在"
                })
            } else {
                //修改按钮值
                $("#changeState").linkbutton({
                    text: "撤销"
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
    sydtitem_dialog.dialog({
        title: '数据字典明细编辑',
        width: 300,
        height: 250,
        buttons: '#sydtitem_tbsc',
        closed: true
    })
})

