/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var role_form = $("#role_form");
    var role_datagrid = $("#role_datagrid");
    var role_dialog = $("#role_dialog");
    var allPermission = $("#allPermission");
    var selectPermission = $("#selectPermission");
    //用来存储所有权限的缓冲变量
    var allPermission_data;
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //新增绑定事件
        add: function () {
            //清空已有权限的数据主要解决选择之后数据依然存在上次所选状态
            //第二个参数为本地数据只不过为空
            selectPermission.datagrid('loadData', []);
            //从新加载所有权限
            allPermission.datagrid('load');
            //清空表单数据
            role_form.form('clear');
            //设置标题
            role_dialog.dialog('setTitle', '新增角色');
            //打开弹出框
            role_dialog.dialog('open');
        },
        //编辑按钮事件
        edit: function () {
            //判断用户是否选中数据
            var row = role_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }

            //清空表单数据
            role_form.form('clear');

            //清空已有权限的数据主要解决选择之后数据依然存在上次所选状态
            //第二个参数为本地数据只不过为空
            selectPermission.datagrid('loadData', []);
            //从新加载所有权限
            //allPermission.datagrid('load');
            //为解决异步加载带来的问题我们采用第一次加载所有权限是将它存放在一个变量中,以后去直接到缓冲中取
            allPermission.datagrid('loadData', allPermission_data);

            //回显该角色已拥有的权限数据,要将此时角色的id带过去
            selectPermission.datagrid('load', {roleId: row.id});

            //回显
            role_form.form('load', row);
            //设置标题
            role_dialog.dialog('setTitle', '编辑角色');
            //打开弹出框
            role_dialog.dialog('open');
        },
        //删除按钮事件
        remove: function () {
            //判断用户是否选中数据
            var row = role_datagrid.datagrid('getSelected');
            if (!row) {
                $.messager.alert('温馨提示', "请选中一条数据!", 'info');
                return;
            }
            //发送ajax请求
            $.get('/role/delete.do', {id: row.id}, function (data) {
                if (data.success) {
                    //成功关闭窗口
                    role_dialog.dialog('close');
                    //弹出提示框
                    $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                        //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                        role_datagrid.datagrid('reload');
                    });
                } else {
                    //失败弹出提示
                    $.messager.alert('温馨提示', data.msg, 'error');
                }
            })
        },
        //刷新按钮事件
        reloads: function () {
            role_datagrid.datagrid('reload');
        },

        //保存操作
        save: function () {
            role_form.form('submit', {
                url: "/role/saveOrUpdate.do",
                onSubmit: function (param) {
                    //获取到已有权限的数据
                    var rows = selectPermission.datagrid('getRows');
                    //遍历出id,返回给对应格式的权限的permission[i].id=rows[i].id
                    for (var i = 0; i < rows.length; i++) {
                        //添加参数封装过去
                        param["permissions[" + i + "].id"] = rows[i].id;
                    }
                },
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        //成功关闭窗口
                        role_dialog.dialog('close');
                        //弹出提示框
                        $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                            //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                            role_datagrid.datagrid('reload');
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
            role_dialog.dialog('close');
        }
    }
    //3.页面加载完毕统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该方法要执行的方法
        var method = $(this).data("cmd");
        methodObj[method]();
    });

    //1.获取到表格进行初始化,添加属性
    role_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: "/role/query.do",
        columns: [[
            {field: 'sn', title: '角色编号', width: 100},
            {field: 'name', title: '角色名称', width: 100},
        ]],
        toolbar: "#role_tb",
        striped: true,
        pagination: true,
        singleSelect: true
    })
    /*初始化弹出框*/
    role_dialog.dialog({
        title: '角色编辑',
        width: 500,
        height: 400,
        buttons: '#role_tbsc',
        closed: true
    })

    //所有权限的数据表格
    allPermission.datagrid({
        width: 200,
        height: 250,
        title: '所有权限',
        fitColumns: true,
        url: "/permission/selectAll.do",
        columns: [[
            {field: 'name', title: '权限名称', width: 100, align: 'center'},
        ]],
        striped: true,
        rownumbers: true,
        onClickRow: function (index, row) {
            //判断该条数据是否已经选中,如果已经选中.设为选中状态
            //获取到所有已有权限,将其与此事要添加的数据的id做判断,如果相等代表已经存在就色织为选中状态
            /* var rows = selectPermission.datagrid('getRows');
             for (var i=0;i<rows.length;i++){
             if (rows[i].id == row.id){
             selectPermission.datagrid('selectRow');
             return;
             }
             }*/
            //将选中的行添加到已有权限当中
            selectPermission.datagrid('appendRow', row);
            //在所有权限中删除点击的数据(根据索引来删除)
            allPermission.datagrid('deleteRow', index);
        },
        onLoadSuccess: function (data) {
            //把后台返回的数据存放到变量中
            //引用了地址(不可以赋值)
            //allPermission_data = data;
            //深度克隆
            allPermission_data = $.extend(true, {}, data);
            console.log(allPermission_data);
        }
    })

    //已有权限的数据表格
    selectPermission.datagrid({
        width: 200,
        height: 250,
        title: '已有权限',
        fitColumns: true,
        pageSize:20,
        pageList:[15,20,25,30,35],
        url: "/permission/getPermissionByRoleId.do",
        columns: [[
            {field: 'name', title: '权限名称', width: 100, align: 'center'},
        ]],
        striped: true,
        rownumbers: true,
        onClickRow: function (index, row) {
            //将选中的行添加到所有权限当中
            allPermission.datagrid('appendRow', row);
            //在已有权限中删除点击的数据(根据索引来删除)
            selectPermission.datagrid('deleteRow', index);
        },
        //成功加载完成之后进行过滤操作
        onLoadSuccess: function (data) {
            //data会封装有rows信息
            //拿到已有权限
            //console.log(data.rows);
            //对所有权限作过滤操作
            //1.循环已有权限的集合,将已有权限的id放在一个ids数组中
            /*将一个数组中的元素转换到另一个数组中*/
            var ids = $.map(data.rows, function (permission) {
                //console.log(permission.id);
                return permission.id;
            });
            //console.log(ids);
            //2.循环所有权限判断已有权限的id是否存在已有权限的ids数组中
            var allRows = allPermission.datagrid('getRows');
            //console.log(allRows);
            for (var i = allRows.length - 1; i >= 0; i--) {
                /*确定第一个参数在数组中的位置，从0开始计数(如果没有找到则返回 -1 )*/
                if ($.inArray(allRows[i].id, ids) != -1) {
                    //3.如果存在将其删除
                    allPermission.datagrid('deleteRow', i);
                }
            }
        }
    })
})

