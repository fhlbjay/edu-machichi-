//页面加载完毕之后操作
$(function () {
    //初始化菜单树
    $("#menuTree").tree({
        url: '/menu/getRootMenu.do',
        onClick: function (node) {
            //判断是否有url属性
            if (node.url) {
                //判断选项卡是否已经存在,存在就设置为选中的状态
                var tag = $("#myTabs").tabs("exists", node.text);
                if (tag) {
                    //设置选中状态
                    $("#myTabs").tabs("select", node.text);
                } else {
                     //创建新的选项卡
                    $('#myTabs').tabs('add', {
                        title: node.text,
                        // href: node.url,//只能引入body里面的内容,引入是页面id可能会发生冲突
                        content: '<iframe src=' + node.url + ' width=100% height=100% frameborder="0"></iframe>',
                        //设置关闭选项卡属性
                        closable: true
                    });
                }
            } else {
                console.log(node.target);
                $("#menuTree").tree("toggle", node.target);
            }
        }
    })


    //初始化选项卡
    $("#myTabs").tabs({
        fit: true
    })
});