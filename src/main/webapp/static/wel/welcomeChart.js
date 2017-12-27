/*页面加载完毕之后操作*/
$(function () {
    //1.变量抽取
    var chart_dialog = $("#studentChart_dialog");

//取某一列的所有数据
    function getColumn(rows, column) {
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
    }

    function getNumbers(column) {
        var rows = chart_datagrid.datagrid("getRows");
        var number = 0;
        for (var index = 0; index < rows.length; index++) {

            if (rows[index]['groupDate'] == column) {
                number = number + rows[index]['newStudentNumber'];
            }
        }
        return number;
    }

    function getNames(rows) {
        var name = "按" + $("#groupType").combobox("getText");
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
            window.location.href = "/newChart/export.do";
        },
        //高级查询
        searchForm: function () {

            //获取关键字input
            var keyword = $("#keyword").textbox("getValue");
            var beginTime = $("#beginTime").textbox("getValue");
            var endTime = $("#endTime").textbox("getValue");
            var groupMssage = $("#groupMssage").textbox("getValue");
            //让数据表格重新加载比并将查询数据带过去
            chart_datagrid.datagrid('load', {
                beginTime: beginTime,
                endTime: endTime,
                keyword: keyword,
                groupMssage: groupMssage
            });
        },
        openBar: function () {
            $("#studentChart_dialog").dialog("open");
            var myChart = echarts.init(document.getElementById('main'));
            var rows = chart_datagrid.datagrid("getRows");
            option = {
                title: {
                    text: "每月新增潜在学员数"
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: [/*'营销人员', */'新增潜在学员']
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
                        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']

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
                        name: '学员数',
                        type: 'bar',
                        data: [getNumbers( '2017-01'), getNumbers( '2017-02'),getNumbers( '2017-03'), getNumbers( '2017-04'),getNumbers( '2017-05'), getNumbers( '2017-06'), getNumbers( '2017-07'),getNumbers( '2017-08'),getNumbers( '2017-09'),getNumbers( '2017-10'),getNumbers( '2017-11'),getNumbers( '2017-12')]
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
                    text: '每月新增潜在学员数',
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['新增学员数']
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
                    data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
                },
                yAxis: {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value}'
                    }
                },
                series: [
                    {
                        name: '新增数',
                        type: 'line',
                        data: [getNumbers( '2017-01'), getNumbers( '2017-02'),getNumbers( '2017-03'), getNumbers( '2017-04'),getNumbers( '2017-05'), getNumbers( '2017-06'), getNumbers( '2017-07'),getNumbers( '2017-08'),getNumbers( '2017-09'),getNumbers( '2017-10'),getNumbers( '2017-11'),getNumbers( '2017-12')],
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
                    data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
                },
                series: [
                    {
                        name: '新增潜在学员',
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
                        data: [
                            {value: getNumbers( '2017-01'), name: '1月'},
                            {value: getNumbers( '2017-02'), name: '2月'},
                            {value: getNumbers( '2017-03'), name: '3月'},
                            {value: getNumbers( '2017-04'), name: '4月'},
                            {value: getNumbers( '2017-05'), name: '5月'},
                            {value: getNumbers( '2017-06'), name: '6月'},
                            {value: getNumbers( '2017-07'), name: '7月'},
                            {value: getNumbers( '2017-08'), name: '8月'},
                            {value: getNumbers( '2017-09'), name: '9月'},
                            {value: getNumbers( '2017-10'), name: '10月'},
                            {value: getNumbers( '2017-11'), name: '11月'},
                            {value: getNumbers( '2017-12'), name: '12月'}
                        ]
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
        url: "/newChart/query.do",
        columns: [[
            {field: 'groupDate', title: '录入时间', width: 100},
            {
                field: 'saleMan', title: '营销人员', width: 100, formatter: function (value, row, index) {
                return value.username;
            }
            },
            {field: 'newStudentNumber', title: '新增学员人数', width: 100}
        ]],
        toolbar: "#chart_tb",
        striped: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true
    })
})

