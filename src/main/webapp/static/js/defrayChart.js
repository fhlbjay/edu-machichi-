/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var chart_form = $("#chart_form");
    var chart_datagrid = $("#chart_datagrid");
    var chart_dialog = $("#studentChart_dialog");

//取某一列的所有数据
    /*function getColumn(rows, column) {
        var content = new Array;
        for (var index = 0; index < rows.length; index++) {
            content[index] = '' + rows[index][column] + '';
            if (rows[index][column] == 16) {
                content[index] = "信用卡";
            } else if (rows[index][column] == 17) {
                content[index] = "贷款";
            } else if (rows[index][column] == 18) {
                content[index] = "银行卡";
            } else if (rows[index][column] == 19) {
                content[index] = "支付宝";
            } else {

            }
        }
        return content;
    }*/

    function getData(column) {
        var rows = chart_datagrid.datagrid("getRows");
        var data = new Array();
        for (var index = 0; index < rows.length; index++) {
            data[index] = rows[index][column];
        }
        return data;
    }

    function getNames(rows) {
        var name = "按" + $("#groupType").combobox("getText");
        return name;
    }

    function content() {
        var value;
        var name;
    }

    function getDataxy(rows, column1, column2) {
        var date = new Array;
        for (var index = 0; index < rows.length; index++) {
            date[index] = new content();
            date[index].value = rows[index][column1];
            date[index].name = rows[index][column2];
        }
        return date;
    }


    //2.把所有方法放在一个变量里面
    var methodObj = {

        //刷新按钮事件
        reloads: function () {

            chart_datagrid.datagrid('reload');
        },
        //导出按钮事件
        exportXls: function () {
            window.location.href = "/defrayChart/export.do";
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
        openBar: function () {
            $("#studentChart_dialog").dialog("open");
            var myChart = echarts.init(document.getElementById('main'));
            var rows = chart_datagrid.datagrid("getRows");
            option = {
                title: {
                    text: "支出统计"
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: [/*'营销人员', */'支出金额']
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
                        data: getData('groupType')
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    /*{
                        name: '营销人员',
                        type: 'bar',
                        data: getNames(rows, "saleMan",'totalPayFinished')
                    }
                    ,*/
                    {
                        name: '支出金额',
                        type: 'bar',
                        data: getData('defrayMoney')
                    }
                ]
            };

            myChart.setOption(option);
        },
        openOjbk: function () {
            $("#studentChart_dialog").dialog("open");
            var myChart = echarts.init(document.getElementById('main'));
            var rows = chart_datagrid.datagrid("getRows");
            option = {
                title: {
                    text: '支出统计',
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['支出金额']
                },
                toolbox: {
                    show: true,
                    feature: {
                        dataZoom: {
                            yAxisIndex: 'none'
                        },
                        dataView: {readOnly: false},
                        magicType: {type: ['line', 'bar']},
                        restore: {},
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: getData('groupType')
                },
                yAxis: {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value}'
                    }
                },
                series: [
                    {
                        name: '支出金额',
                        type: 'line',
                        data: getData('defrayMoney'),
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        },
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    }
                ]
            };

            myChart.setOption(option);
        },

        openPie: function () {
            $("#studentChart_dialog").dialog("open");
            var myChart = echarts.init(document.getElementById('main'));
            var rows = chart_datagrid.datagrid("getRows");
            title = '环形图';
            option = {
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: getData('groupType')
                },
                series: [
                    {
                        name: '支出金额',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: true,
                                textStyle: {
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data: getDataxy(rows, "defrayMoney", "groupType")

                    }
                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

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
        url: "/defrayChart/query.do",
        columns: [[
            {field: 'defrayMoney', title: '支出金额', width: 100},
            {field: 'groupType', title: '分组结果', width: 100}
        ]],
        toolbar: "#chart_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true
    })

    /*初始化弹出框*/
    chart_dialog.dialog({
        title: '图表',
        width: 650,
        height: 500,
        closed: true
    })
})

