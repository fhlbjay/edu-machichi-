<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5shiv.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <![endif]-->
<link rel="stylesheet" type="text/css" href="/static/plugins/H-ui.admin/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="/static/plugins/H-ui.admin/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="/static/plugins/H-ui.admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/static/plugins/H-ui.admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/static/plugins/H-ui.admin/static/h-ui.admin/css/style.css" />
<%@include file="/static/common/common.jsp"%>
<!--[if IE 6]>
    <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->

<!-- 签到/下班的js -->
<%--jQuery核心库--%>
<script src="/static/plugins/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript">
	function checkIn() {
		//判断用户是否选中数据
		$.post("/checkRecord/checkIn.do", function(data) {
			console.log(data);
			if (data.success) {
				alert(data.msg);
			} else {
				alert(data.msg);
			}
		});
	};
	function checkOut() {
		//判断用户是否选中数据
		$.post("/checkRecord/checkOut.do", function(data) {
			console.log(data);
			if (data.success) {
				alert(data.msg);
			} else {
				alert(data.msg);
			}
		});
	};
</script>
<title>管理后台</title>
</head>
<body>
	<header>

		<div class="navbar navbar-fixed-top">
			<div class="container-fluid cl">
				<a class="logo navbar-logo f-l mr-10 hidden-xs" href="#">控制面板</a>
				<nav class="nav navbar-nav">
					<ul class="cl">
						<li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A">
								 快速链接 <i class="Hui-iconfont">&#xe6d5;</i>
							</a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;" onclick="article_add('文章','/news/view.do','','510')">
										<i class="Hui-iconfont">&#xe616;</i> 文章
									</a></li>
								<li><a href="javascript:;" onclick="product_add('课程','/schedule/view.do','','510')">
										<i class="Hui-iconfont">&#xe620;</i> 课程
									</a></li>
								<li><a href="javascript:;" onclick="picture_add('潜在学员','/potentitalStudent/view.do','','510')">
										<i class="Hui-iconfont">&#xe613;</i> 潜在学员
									</a></li>
								<li><a href="javascript:;" onclick="member_add('正式员工','/officialStudent/view.do','','510')">
										<i class="Hui-iconfont">&#xe60d;</i> 正式员工
									</a></li>
							</ul></li>
					</ul>
				</nav>
				<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
					<ul class="cl">
						<li class="dropDown dropDown_hover"><a href="#" class="dropDown_A">
								您好：<shiro:principal property="username"/> <i class="Hui-iconfont">&#xe6d5;</i>
							</a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;" onClick="myselfinfo()">系统信息</a></li>
								<li><a href="/logOut">切换账户</a></li>
								<li><a href="/logOut">退出</a></li>
							</ul></li>
						<li id="Hui-msg"><a href="#" title="消息">
								<span class="badge badge-danger">1</span>
								<i class="Hui-iconfont" style="font-size: 18px">&#xe68a;</i>
							</a></li>
						<li id="Hui-skin" class="dropDown right dropDown_hover"><a href="javascript:;" class="dropDown_A" title="换肤">
								<i class="Hui-iconfont" style="font-size: 18px">&#xe62a;</i>
							</a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
								<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
								<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
								<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
								<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
								<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
							</ul></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	<aside class="Hui-aside">

    <div class="menu_dropdown bk_2">
        <dl id="menu-article">
            <dt><i class="Hui-iconfont">&#xe616;</i> 文章<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li hidden="true" class="mjl"><a data-href="" data-title="moban" href="javascript:void(0)">moban</a>
                    <li><a data-href="/news/view.do" data-title="新闻信息" href="javascript:void(0)">新闻信息</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-charts">
            <dt  pid="19"><i class="Hui-iconfont">&#xe613;</i> 报表<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd id="chartsparent">
                <ul>
                </ul>
            </dd>
        </dl>
        <dl id="menu-product">
            <dt><i class="Hui-iconfont">&#xe620;</i> 计划<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="/plan/view.do" data-title="日程计划" href="javascript:void(0)">日程计划</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-costume">
            <dt pid="1"><i class="Hui-iconfont">&#xe622;</i> 客户服务<i
                    class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd id="costumeparent">
                <ul>
                    <%-- <li><a data-href="/potentitalStudent/view.do" data-title="潜在学员管理" href="javascript:;">潜在学员管理</a>
                     </li>
                     <li><a data-href="feedback-list.html" data-title="学员跟踪" href="javascript:;">学员跟踪</a></li>
                     <li><a data-href="feedback-list.html" data-title="潜在客户池" href="javascript:void(0)">潜在客户池</a></li>
                     <li><a data-href="feedback-list.html" data-title="移交历史" href="javascript:void(0)">移交历史</a></li>
                     <li><a data-href="feedback-list.html" data-title="大客户" href="javascript:void(0)">大客户</a></li>
                     <li><a data-href="feedback-list.html" data-title="学校联系人" href="javascript:void(0)">学校联系人</a></li>
                     <li><a data-href="feedback-list.html" data-title="考试管理" href="javascript:void(0)">考试管理</a></li>--%>
					</ul>
				</dd>
			</dl>
			<dl id="menu-student">
				<dt pid="2">
					<i class="Hui-iconfont">&#xe60d;</i> 学员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd id="stuparent">
					<ul>
						<%-- <li><a data-href="/officialStudent/view.do" data-title="正式学员管理" href="javascript:;">正式学员管理</a></li>
                     <li><a data-href="member-del.html" data-title="学员升班留级" href="javascript:;">学员升班留级</a></li>
                     <li><a data-href="member-level.html" data-title="收款管理" href="javascript:;">收款管理</a></li>
                     <li><a data-href="member-scoreoperation.html" data-title="学员流失" href="javascript:;">学员流失</a></li>--%>
					</ul>
				</dd>
			</dl>
			<dl id="menu-manage">
				<dt pid="3">
					<i class="Hui-iconfont">&#xe62d;</i> 教务管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd id="manageparent">
					<ul>
						<%--<li><a data-href="/classGrade/view.do" data-title="班级管理" href="javascript:void(0)">班级管理</a></li>
                    <li><a data-href="/classRoom/view.do" data-title="教室管理" href="javascript:void(0)">教室管理</a></li>
                    <li><a data-href="/schedule/view.do" data-title="课程表" href="javascript:void(0)">课程表</a></li>--%>
					</ul>
				</dd>
			</dl>
			<dl id="menu-office">
				<dt pid="4">
					<i class="Hui-iconfont">&#xe62d;</i> 日常管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd id="officeparent">
					<ul>
					</ul>
				</dd>
			</dl>
			<%--  <dl id="menu-tongji">
            <dt><i class="Hui-iconfont">&#xe61a;</i> 报表<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a data-href="charts-1.html" data-title="折线图" href="javascript:void(0)">折线图</a></li>
                    <li><a data-href="charts-2.html" data-title="时间轴折线图" href="javascript:void(0)">时间轴折线图</a></li>
                    <li><a data-href="charts-3.html" data-title="区域图" href="javascript:void(0)">区域图</a></li>
                    <li><a data-href="charts-4.html" data-title="柱状图" href="javascript:void(0)">柱状图</a></li>
                    <li><a data-href="charts-5.html" data-title="饼状图" href="javascript:void(0)">饼状图</a></li>
                    <li><a data-href="charts-6.html" data-title="3D柱状图" href="javascript:void(0)">3D柱状图</a></li>
                    <li><a data-href="charts-7.html" data-title="3D饼状图" href="javascript:void(0)">3D饼状图</a></li>
                </ul>
            </dd>
        </dl>--%>
			<dl id="menu-system">
				<dt pid="5">
					<i class="Hui-iconfont">&#xe62e;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd id="sysparent">
					<ul>

						<%--  <li><a data-href="/employee/view.do" data-title="员工管理" href="javascript:void(0)">员工管理</a></li>
                      <li><a data-href="/department/view.do" data-title="部门管理" href="javascript:void(0)">部门管理</a></li>
                      <li><a data-href="/permission/view.do" data-title="权限管理" href="javascript:void(0)">系统权限管理</a></li>
                      <li><a data-href="/role/view.do" data-title="角色管理" href="javascript:void(0)">系统角色管理</a></li>
                      <li><a data-href="/systemdictionary/view.do" data-title="数据字典" href="javascript:void(0)">数据字典</a>
                      </li>
                      <li><a data-href="system-log.html" data-title="系统日志" href="javascript:void(0)">系统日志</a></li>--%>
					</ul>
				</dd>
			</dl>

			<!--	____________ 签到/下班		 -->
			<div style="position: absolute; bottom: 30px; left: 24px;">
				<img title="签到" src="/static/img/sun_big.png" onclick="checkIn()" style="cursor: pointer;"> &emsp; <img title="下班" src="/static/img/moon_big.png" onclick="checkOut();"
					style="cursor: pointer;">
			</div>
		</div>
	</aside>
	<div class="dislpayArrow hidden-xs">
		<a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a>
	</div>
	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active"><span title="主页" data-href="welcome.jsp">主页</span> <em></em></li>
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group">
				<a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;">
					<i class="Hui-iconfont">&#xe6d4;</i>
				</a>
				<a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;">
					<i class="Hui-iconfont">&#xe6d7;</i>
				</a>
			</div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display: none" class="loading"></div>
				<iframe scrolling="yes" frameborder="0" src="welcome.jsp"></iframe>
			</div>
		</div>
	</section>

	<div class="contextMenu" id="Huiadminmenu">
		<ul>
			<li id="closethis">关闭当前</li>
			<li id="closeall">关闭全部</li>
		</ul>
	</div>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="/static/plugins/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="/static/plugins/H-ui.admin/lib/jquery/layer.js"></script>
	<script type="text/javascript" src="/static/plugins/H-ui.admin/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript" src="/static/plugins/H-ui.admin/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->


	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript" src="/static/plugins/H-ui.admin/lib/jquery/jquery.contextmenu.r2.js"></script>
	<script type="text/javascript">
		$(function() {
			//动态拼接菜单栏

			var sysparent = $("#menu-system").find("dt");
			var parentId = sysparent.attr("pid");
			console.log(parentId)
			$.get('/menu/getRootMenu.do', {
				parentId : parentId
			}, function(data) {
				$.each(data, function(index, item) {
					var clone = $(".mjl").first().clone("true");
					clone.prop("hidden", false);
					var a = clone.find("a");
					a.attr('data-href', item.url);
					a.attr('data-title', item.text);
					a.text(item.text)
					var parentdd = $("#sysparent")
					var ui = $(parentdd).find("ul")
					clone.appendTo(ui);
					//console.log(ui)
				});
			});
			//
			var stuparent = $("#menu-student").find("dt");
			var stuparentId = stuparent.attr("pid");
			$.get('/menu/getRootMenu.do', {
				parentId : stuparentId
			}, function(data2) {
				$.each(data2, function(index, item) {
					var clone = $(".mjl").first().clone("true");
					clone.prop("hidden", false);
					var a = clone.find("a");
					a.attr('data-href', item.url);
					a.attr('data-title', item.text);
					a.text(item.text)
					var parentdd = $("#stuparent")
					var ui = $(parentdd).find("ul")
					clone.appendTo(ui);
					//console.log(ui)
				});
			});
			//

			var costumeparent = $("#menu-costume").find("dt");
			var cosparentId = costumeparent.attr("pid");
			$.get('/menu/getRootMenu.do', {
				parentId : cosparentId
			}, function(data3) {
				$.each(data3, function(index, item) {
					var clone = $(".mjl").first().clone("true");
					clone.prop("hidden", false);
					var a = clone.find("a");
					a.attr('data-href', item.url);
					a.attr('data-title', item.text);
					a.text(item.text)
					var parentdd = $("#costumeparent")
					var ui = $(parentdd).find("ul")
					clone.appendTo(ui);
					//console.log(ui)
				});
			});
			//
			var manageparent = $("#menu-manage").find("dt");
			var managerparentId = manageparent.attr("pid");
			$.get('/menu/getRootMenu.do', {
				parentId : managerparentId
			}, function(data4) {
				$.each(data4, function(index, item) {
					var clone = $(".mjl").first().clone("true");
					clone.prop("hidden", false);
					var a = clone.find("a");
					a.attr('data-href', item.url);
					a.attr('data-title', item.text);
					a.text(item.text)
					var parentdd = $("#manageparent")
					var ui = $(parentdd).find("ul")
					clone.appendTo(ui);
					//console.log(ui)
				});
			});
			//
			var officeparent = $("#menu-office").find("dt");
			var officeparentId = officeparent.attr("pid");
			$.get('/menu/getRootMenu.do', {
				parentId : officeparentId
			}, function(data5) {
				$.each(data5, function(index, item) {
					var clone = $(".mjl").first().clone("true");
					clone.prop("hidden", false);
					var a = clone.find("a");
					a.attr('data-href', item.url);
					a.attr('data-title', item.text);
					a.text(item.text)
					var parentdd = $("#officeparent")
					var ui = $(parentdd).find("ul")
					clone.appendTo(ui);
					//console.log(ui)
				});
			});
			//
			var chartsparent = $("#menu-charts").find("dt");
			var chartsparentId = chartsparent.attr("pid");
			$.get('/menu/getRootMenu.do', {
				parentId : chartsparentId
			}, function(data5) {
				$.each(data5, function(index, item) {
					var clone = $(".mjl").first().clone("true");
					clone.prop("hidden", false);
					var a = clone.find("a");
					a.attr('data-href', item.url);
					a.attr('data-title', item.text);
					a.text(item.text)
					var parentdd = $("#chartsparent")
					var ui = $(parentdd).find("ul")
					clone.appendTo(ui);
					//console.log(ui)
				});
			});

		})
		/*$("#min_title_list li").contextMenu('Huiadminmenu', {
		    bindings: {
		        'closethis': function(t) {
		            console.log(t);
		            if(t.find("i")){
		                t.find("i").trigger("click");
		            }
		        },
		        'closeall': function(t) {
		            alert('Trigger was '+t.id+'\nAction was Email');
		        },
		    }
		});*/

		;

		/*个人信息*/
		function myselfinfo() {
			layer.open({
				type : 1,
				area : [ '300px', '300px' ],
				fix : false, //不固定
				maxmin : true,
				shade : 0.4,
				title : '系统信息',
				content : '<div><br>&emsp;   操作系统<br>&emsp;CentOS 7.1<br><br><hr><br>&emsp;   硬件信息<br>&emsp;Intel® Xeon® E5-2609 v3 1.9GHz</div>'
			});
		}

		/*文章-添加*/
		function article_add(title, url, w, h) {
			layer_show(title, url, w, h);
			/* var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index); */
		}

		/*图片-添加*/
		function picture_add(title, url, w, h) {
			layer_show(title, url, w, h);
			/* var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index); */
		}

		/*产品-添加*/
		function product_add(title, url, w, h) {
			layer_show(title, url, w, h);
			/* var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index); */
		}

		/*用户-添加*/
		function member_add(title, url, w, h) {
			fix : false, //不固定
			layer_show(title, url, w, h);
		}
	</script>

</body>
</html>
