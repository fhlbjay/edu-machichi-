$(function () {
    //1.变量抽取
    var ps_form = $("#ps_form");
    var query_form = $("#query_form");
    var ps_datagrid = $("#ps_datagrid");
    var ps_dialog = $("#ps_dialog");
    var query_dialog = $("#query_dialog");
    //2.把所有方法放在一个变量里面
    var methodObj = {
        //刷新按钮事件
        reloads: function () {
            ps_datagrid.datagrid('reload');

        },
        //提交操作
        put: function () {
            query_form.form('submit', {
                url: "/studentTrack/query.do",
                success: function (data) {
                    //alert(data)
                    //转成json对象
                    data = $.parseJSON(data);
                    //关闭窗口
                    query_dialog.dialog('close');
                    //弹出提示框
                    $.messager.alert('温馨提示', '操作成功！', 'info', function () {
                        //从新加载数据表格:等同于'load'方法，但是它将保持在当前页。
                        ps_datagrid.datagrid('loadData', data);
                    });
                }
            });
        },
        //取消按钮事件
        cancel: function () {
            //关闭弹出框
            ps_dialog.dialog('close');
        },
        //高级查询
        query: function () {
            //设置标题
            query_dialog.dialog('setTitle', '高级查询移交历史');
            //打开弹出框
            query_dialog.dialog('open');
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
        url: "/studentTrack/query.do",
        columns: [[
            {field: 'name', title: '姓名', width: 100},
            {field: 'qq', title: 'QQ', width: 100},
            {field: 'tel', title: '电话', width: 100},
            {field: 'currentTime', title: '日期', width: 100},
            {field: 'prevEmployee', title: '原拥有者', width: 100,formatter:function (value,row,index) {
                return value?value.username:'';
            }},
            {field: 'nowEmployee', title: '移交目标', width: 100,formatter:function (value,row,index) {
                return value?value.username:'';
            }},

        ]],
        toolbar: "#ps_tb",
        striped: true,
        pagination: true,
        pageSize:20,
        pageList:[15,20,25,30,35],
        rownumbers: true,
        singleSelect: true,
    })

    query_dialog.dialog({
        title: '潜在学员查询',
        width: 250,
        height: 250,
        buttons: '#ps_tbsc',
        closed: true
    })

})

