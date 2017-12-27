/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var chart_form = $("#chart_form");
    var chart_datagrid = $("#chart_datagrid");
    var chart_dialog = $("#studentChart_dialog");

    //取某一列的所有数据
    function getColumn(rows, column) {
        var content = new Array;
        for (var index = 0;  index < rows.length; index++) {
            content[index] = '' + rows[index][column] + '';
            if (rows[index][column] == 16) {
                content[index] = "信用卡";
            } else if (rows[index][column] == 17) {
                content[index] = "贷款";
            } else if (rows[index][column] == 18) {
                content[index] = "银行卡";
            } else if (rows[index][column] == 19) {
                content[index]= "支付宝";
            }  else {

            }
        }
        return content;
    }
    function getNames(rows) {
        var name ="按" + $("#groupType").combobox("getText");
        return name;
    }

    function content() {
        var value;
        var name;
    }

    function getData(rows, column1, column2) {
        var date = new Array;
        for (var index = 0; index < rows.length; index++) {
            date[index] = new content();
                date[index].value = rows[index][column1];
            if (rows[index][column2] == 16) {
                date[index].name = '信用卡';
            } else if (rows[index][column2] == 17) {
                date[index].name =  "贷款";
            } else if (rows[index][column2] == 18) {
                date[index].name = "银行卡";
            } else if (rows[index][column2] == 19) {
                date[index].name = "支付宝";
            }else {
                date[index].name =rows[index][column2];
            }
        }
        return date;
    }



    //2.把所有方法放在一个变量里面
    var methodObj = {

        //刷新按钮事件
        reloads: function () {
            chart_datagrid.datagrid('reload');
        },


        //高级查询
        searchForm: function () {

            //获取关键字input
            var keyword = $("#keyword").textbox("getValue");
            var beginTime = $("#beginTime").textbox("getValue");
            var endTime = $("#endTime").textbox("getValue");
            var groupType = $("#groupType").textbox("getValue");
            //让数据表格重新加载比并将查询数据带过去
            chart_datagrid.datagrid('load', {
                beginTime: beginTime,
                endTime: endTime,
                keyword: keyword,
                groupType: groupType
            });
        },
        //饼图
        openPie: function () {
            chart_dialog.dialog("open");
            // 初始化echarts图表
            var myChart = echarts.init(document.getElementById('main'));
            var rows = chart_datagrid.datagrid("getRows");
            option = {
                title: {
                    text: '学员已付清人数统计图',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: [getNames('groupType')],
                    selected: {
                        '营销人员': false,
                        '已付清人数': false
                    },
                    selectedMod: 'false'
                },
                toolbox: {
                    show: true,
                    feature: {
                        dataView: {show: true, readOnly: false},
                        magicType: {
                            type: ['pie']
                        },
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                calculable: true,
                series: [

                    {
                        name: '已付清总款',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: getData(rows, "totalPayFinished", "groupType")
                    }
                ]
            };
            // 为echarts对象加载数据
            myChart.setOption(option);

        },
        openBar: function () {
            $("#studentChart_dialog").dialog("open");
            var myChart = echarts.init(document.getElementById('main'));
            var rows = chart_datagrid.datagrid("getRows");
            option = {
                title: {
                    text: "按" + $("#groupType").combobox("getText") + "分组"
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: [ /*'营销人员', */'已付清人数']
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: {show: false},
                        dataView: {show: false, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                calculable: true,
                xAxis: [
                    {
                        type: 'category',
                        data: getColumn(rows, 'groupType')

                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: '已付清人数',
                        type: 'bar',
                        data: getColumn(rows, "totalPayFinished")
                    }
                ]
            };

            myChart.setOption(option);
        },
        //导出按钮事件
        exportXls: function () {
            window.location.href = "/chart/export.do";
        }

    }
    //3.页面加载完毕统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该方法要执行的方法
        var method = $(this).data("cmd");
        methodObj[method]();
    });

    //1.获取到表格进行初始化,添加属性
    chart_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: "/chart/query.do",
        columns: [[
            {
                field: 'groupType', title: '分组查询', width: 100, formatter: function (value, row, index) {
                if (value == 16) {
                    return "信用卡";
                } else if (value == 17) {
                    return "贷款";
                } else if (value == 18) {
                    return "银行卡";
                } else if (value == 19) {
                    return "支付宝";
                } else {
                    return value;
                }
            }
            },
            {field: 'saleMan', title: '营销人员', width: 100},
            {field: 'totalPayFinished', title: '已付清人数', width: 100}
        ]],
        toolbar: "#chart_tb",
        striped: true,
        pagination: true,
        pageSize:20,
        pageList:[15,20,25,30,35],
        rownumbers: true,
        singleSelect: true,
    })
    /*初始化弹出框*/
    chart_dialog.dialog({
        title: '图表',
        width: 620,
        height: 460,
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
})

