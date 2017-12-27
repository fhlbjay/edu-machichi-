<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" class="wp-toolbar" lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
addLoadEvent = function(func){
	if(typeof jQuery!="undefined")jQuery(document).ready(func);
	else if(typeof wpOnload!='function'){wpOnload=func;}else{var oldonload=wpOnload;wpOnload=function(){oldonload();func();}}};
var ajaxurl = '',
	pagenow = 'dashboard',
	typenow = '',
	adminpage = 'index-php',
	thousandsSeparator = ',',
	decimalPoint = '.',
	isRtl = 0;
</script>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="dns-prefetch" href="http://s0.wp.com/">
<link rel="dns-prefetch" href="http://s.w.org/">
<style type="text/css">
img.wp-smiley, img.emoji {
	display: inline !important;
	border: none !important;
	box-shadow: none !important;
	height: 1em !important;
	width: 1em !important;
	margin: 0 .07em !important;
	vertical-align: -0.1em !important;
	background: none !important;
	padding: 0 !important;
}
</style>
<link rel="stylesheet" href="static/wel/load-styles.css" type="text/css" media="all">
<link rel="stylesheet" id="jetpack-dashboard-widget-css" href="static/wel/dashboard-widget.css" type="text/css" media="all">
<link rel="stylesheet" id="thickbox-css" href="static/wel/thickbox.css" type="text/css" media="all">
<link rel="stylesheet" id="jetpack-icons-css" href="static/wel/jetpack-icons.css" type="text/css" media="all">
<link rel="stylesheet" id="jetpack-jitm-css-css" href="static/wel/jetpack-admin-jitm.css" type="text/css" media="all">
<script src="static/wel/wp-emoji-release.js" type="text/javascript" defer="defer"></script>

<script type="text/javascript">
/* <![CDATA[ */
var userSettings = {"url":"\/","uid":"1","time":"1514257078","secure":""};/* ]]> */
</script>
<script type="text/javascript" src="static/wel/load-scripts_002.php"></script>
<script type="text/javascript">
/* <![CDATA[ */
	var wpNotesIsJetpackClient = true;
	var wpNotesIsJetpackClientV2 = true;
/* ]]> */
</script>
<!-- <script>
		if ( window.history.replaceState ) {
			window.history.replaceState( null, null, document.getElementById( 'wp-admin-canonical' ).href + window.location.hash );
		}
</script> -->
<script type="text/javascript">var _wpColorScheme = {"icons":{"base":"#82878c","focus":"#00a0d2","current":"#fff"}};</script>
<style type="text/css" media="print">
#wpadminbar {
	display: none;
}
</style>
<script type="text/javascript">
/* <![CDATA[ */
jQuery( function($) {
	var dashStats = jQuery( '#dashboard_stats div.inside' );

	if ( dashStats.find( '.dashboard-widget-control-form' ).length ) {
		return;
	}

	if ( ! dashStats.length ) {
		dashStats = jQuery( '#dashboard_stats div.dashboard-widget-content' );
		var h = parseInt( dashStats.parent().height() ) - parseInt( dashStats.prev().height() );
		var args = 'width=' + dashStats.width() + '&height=' + h.toString();
	} else {
		if ( jQuery('#dashboard_stats' ).hasClass('postbox') ) {
			var args = 'width=' + ( dashStats.prev().width() * 2 ).toString();
		} else {
			var args = 'width=' + ( dashStats.width() * 2 ).toString();
		}
	}

	dashStats
		.not( '.dashboard-widget-control' )
		.load( 'admin.php?page=stats&noheader&dashboard&' + args );

	jQuery( window ).one( 'resize', function() {
		jQuery( '#stat-chart' ).css( 'width', 'auto' );
	} );
} );
/* ]]> */
</script>
<style type="text/css">
/* <![CDATA[ */
#stat-chart {
	background: none !important;
}

#dashboard_stats .inside {
	margin: 10px 0 0 0 !important;
}

#dashboard_stats #stats-graph {
	margin: 0;
}

#stats-info {
	border-top: 1px solid #dfdfdf;
	margin: 7px -10px 0 -10px;
	padding: 10px;
	background: #fcfcfc;
	-moz-box-shadow: inset 0 1px 0 #fff;
	-webkit-box-shadow: inset 0 1px 0 #fff;
	box-shadow: inset 0 1px 0 #fff;
	overflow: hidden;
	border-radius: 0 0 2px 2px;
	-webkit-border-radius: 0 0 2px 2px;
	-moz-border-radius: 0 0 2px 2px;
	-khtml-border-radius: 0 0 2px 2px;
}

#stats-info #top-posts, #stats-info #top-search {
	float: left;
	width: 50%;
}

#top-posts .stats-section-inner p {
	white-space: nowrap;
	overflow: hidden;
}

#top-posts .stats-section-inner p a {
	overflow: hidden;
	text-overflow: ellipsis;
}

#stats-info div#active {
	border-top: 1px solid #dfdfdf;
	margin: 0 -10px;
	padding: 10px 10px 0 10px;
	-moz-box-shadow: inset 0 1px 0 #fff;
	-webkit-box-shadow: inset 0 1px 0 #fff;
	box-shadow: inset 0 1px 0 #fff;
	overflow: hidden;
}

#top-search p {
	color: #999;
}

#stats-info h3 {
	font-size: 1em;
	margin: 0 0 .5em 0 !important;
}

#stats-info p {
	margin: 0 0 .25em;
	color: #999;
}

#stats-info p.widget-loading {
	margin: 1em 0 0;
	color: #333;
}

#stats-info p a {
	display: block;
}

#stats-info p a.button {
	display: inline;
}
/* ]]> */
</style>

<!-- __________报表所需文件 -->

<script type="text/javascript" src="/static/plugins/echarts/echarts.common.min.js"></script>
<script type="text/javascript"></script>


</head>
<body class="wp-admin wp-core-ui js   jetpack-connected  index-php auto-fold admin-bar branch-4-8 version-4-8-4 admin-color-fresh locale-zh-cn customize-support sticky-menu svg">
	<script type="text/javascript">
	document.body.className = document.body.className.replace('no-js','js');
</script>

	<script type="text/javascript">
			(function() {
				var request, b = document.body, c = 'className', cs = 'customize-support', rcs = new RegExp('(^|\\s+)(no-)?'+cs+'(\\s+|$)');

						request = true;
		
				b[c] = b[c].replace( rcs, ' ' );
				// The customizer requires postMessage and CORS (if the site is cross domain)
				b[c] += ( window.postMessage && request ? ' ' : ' no-' ) + cs;
			}());
		</script>
	<!--<![endif]-->

	<div id="wpwrap">

		<div id="wpcontent">



			<div id="wpbody" role="main">

				<div id="wpbody-content" aria-label="主内容" tabindex="0" style="overflow: hidden;">
					<div id="screen-meta" class="metabox-prefs" style="display: none;">

						<div id="screen-options-wrap" class="hidden" tabindex="-1" aria-label="“显示选项”选项卡" style="display: none;">
							<form id="adv-settings" method="post">
								<fieldset class="metabox-prefs">
									<label for="dashboard_right_now-hide">
										<input class="hide-postbox-tog" name="dashboard_right_now-hide" id="dashboard_right_now-hide" value="dashboard_right_now" checked="checked" type="checkbox">
										概览
									</label>
									<label for="dashboard_activity-hide">
										<input class="hide-postbox-tog" name="dashboard_activity-hide" id="dashboard_activity-hide" value="dashboard_activity" checked="checked" type="checkbox">
										活动
									</label>
									<label for="dashboard_quick_press-hide">
										<input class="hide-postbox-tog" name="dashboard_quick_press-hide" id="dashboard_quick_press-hide" value="dashboard_quick_press" checked="checked" type="checkbox">
										<span class="hide-if-no-js">快速草稿</span>
										<span class="hide-if-js">草稿</span>
									</label>
									<label for="dashboard_primary-hide">
										<input class="hide-postbox-tog" name="dashboard_primary-hide" id="dashboard_primary-hide" value="dashboard_primary" checked="checked" type="checkbox">
										News
									</label>
									<label for="wp_welcome_panel-hide">
										<input id="wp_welcome_panel-hide" checked="checked" type="checkbox">
										Welcome
									</label>
									<label for="jetpack_summary_widget-hide">
										<input class="hide-postbox-tog" name="jetpack_summary_widget-hide" id="jetpack_summary_widget-hide" value="jetpack_summary_widget" checked="checked" type="checkbox">
										报表
									</label>
								</fieldset>

								<input id="screenoptionnonce" name="screenoptionnonce" value="994077f7d5" type="hidden">
							</form>
						</div>
					</div>
					<div id="screen-meta-links">
						<div id="screen-options-link-wrap" class="hide-if-no-js screen-meta-toggle">
							<button type="button" id="show-settings-link" class="button show-settings" aria-controls="screen-options-wrap" aria-expanded="false">显示选项</button>
						</div>
					</div>
					<div class="update-nag"><span style="font-size: 24px;">Happy Holidays ！</span><img alt="give me five ~" src="/static/img/happy.png"></div>
					<div class="jetpack-jitm-message" data-nonce="657f2f526c" data-message-path="wp:dashboard:admin_notices" data-query=""></div>

					<div class="wrap">
						<!-- <h1>主页</h1> -->


						<div id="welcome-panel" class="welcome-panel">
							<div class="welcome-panel-content">
								<h2>欢迎登录三人行教育后台！</h2>
								<div class="welcome-panel-column-container">
									<div class="welcome-panel-column">
										<h3>开始使用</h3>
										<a class="button button-primary button-hero load-customize hide-if-no-customize" href="/systemLog.do">查看系统日志</a>
									</div>
									<div class="welcome-panel-column">
										<h3>接下来</h3>
										<ul>
											<li><a href="#" class="welcome-icon welcome-write-blog">撰写一篇新闻</a></li>
											<li><a href="#" class="welcome-icon welcome-add-page">添加“关于”页面</a></li>
											<li><a href="http://www.wolfcode.cn/" target="_blank" class="welcome-icon welcome-view-site">查看公司首页</a></li>
										</ul>
									</div>
									
									
									<div class="welcome-panel-column welcome-panel-last">
										<h3>更多操作</h3>
										<ul>
											<li><a href="http://bbs.520it.com/portal.php?mod=topic&topicid=1" class="welcome-icon welcome-comments">去社区看看</a></li>
											<li><a href="http://www.wolfcode.cn/aboutus.html" class="welcome-icon welcome-learn-more">了解更多公司信息</a></li>
											<li><div class="welcome-icon welcome-widgets-menus">
													管理
													<a target="_blank" href="/employee/view.do">员工</a>
													和
													
													<a target="_blank;" data-ref="/department/view.do">部门管理</a>
												</div>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>

						<div id="dashboard-widgets-wrap">
							<div id="dashboard-widgets" class="metabox-holder">
								<div id="postbox-container-1" class="postbox-container">
									<div id="normal-sortables" class="meta-box-sortables ui-sortable">
										<div id="dashboard_right_now" class="postbox ">
											<button type="button" class="handlediv" aria-expanded="true">
												<span class="screen-reader-text">切换面板：概览</span>
												<span class="toggle-indicator" aria-hidden="true"></span>
											</button>
											<h2 class="hndle ui-sortable-handle">
												<span>概览</span>
											</h2>
											<div class="inside">
												<div class="main">
													<ul>
														<li class="post-count"><a href="#">2篇文章</a></li>
														<li class="comment-count"><a href="#">2条评论</a></li>
														<li class="comment-mod-count hidden"><a href="#" aria-label="0条评论待审">0条评论待审</a></li>
													</ul>
													<p id="wp-version-message">Xxxx</p>
												</div>
											</div>
										</div>
										<div id="dashboard_activity" class="postbox ">
											<button type="button" class="handlediv" aria-expanded="true">
												<span class="screen-reader-text">切换面板：活动</span>
												<span class="toggle-indicator" aria-hidden="true"></span>
											</button>
											<h2 class="hndle ui-sortable-handle">
												<span>Change</span>
											</h2>
											<div class="inside">
												<div id="activity-widget">
													<div id="published-posts" class="activity-block">
														<h3>最近发布</h3>
														<ul>
															<li><span>下午6:43 11月1日</span> <a href="#">Xxx</a></li>
															<li><span>上午12:29 11月1日</span> <a href="#">Zzz</a></li>
														</ul>
													</div>
													<div id="latest-comments" class="activity-block">
														<h3>近期</h3>
														<ul id="the-comment-list" data-wp-lists="list:comment">
															<li id="comment-2" class="comment byuser comment-author-moe bypostauthor even thread-even depth-1 comment-item approved"><img alt="" src="/static/img/moon_big.png"
																srcset="/static/img/linke.png" class="avatar avatar-50 photo" width="50" height="50">

																<div class="dashboard-comment-wrap has-row-actions">
																	<p class="comment-meta">
																		由<cite class="comment-author">员工A</cite> 添加了员工B
																		<span class="approve">[待审]</span>
																	</p>

																	<blockquote>
																		<p>你好！~</p>
																	</blockquote>
																	<p class="row-actions">
																		<span class="approve">
																			<a href="#" data-wp-lists="dim:the-comment-list:comment-2:unapproved:e7e7d3:e7e7d3:new=approved" class="vim-a" aria-label="批准这条评论">批准</a>
																		</span>
																		<span class="unapprove">
																			<a href="#" data-wp-lists="dim:the-comment-list:comment-2:unapproved:e7e7d3:e7e7d3:new=unapproved" class="vim-u" aria-label="驳回这条评论">驳回</a>
																		</span>
																		<span class="reply hide-if-no-js">
																			|
																			<a onclick="window.commentReply &amp;&amp; commentReply.open('2','13');return false;" class="vim-r hide-if-no-js" aria-label="回复这条评论" href="#">回复</a>
																		</span>
																		<span class="edit">
																			|
																			<a href="#" aria-label="编辑此评论">编辑</a>
																		</span>
																		<span class="spam">
																			|
																			<a href="#" data-wp-lists="delete:the-comment-list:comment-2::spam=1" class="vim-s vim-destructive" aria-label="将这条评论标记为垃圾评论">垃圾评论</a>
																		</span>
																		<span class="trash">
																			|
																			<a href="#" data-wp-lists="delete:the-comment-list:comment-2::trash=1" class="delete vim-d vim-destructive" aria-label="将此评论移至回收站">移至回收站</a>
																		</span>
																		<span class="view">
																			|
																			<a class="comment-link" href="#" aria-label="查看此评论">查看</a>
																		</span>
																	</p>
																</div></li>

															<li id="comment-1" class="comment odd alt thread-odd thread-alt depth-1 comment-item approved"><img alt="" src="/static/img/mario.png" class="avatar avatar-50 photo" width="50"
																height="50">

																<div class="dashboard-comment-wrap has-row-actions">
																	<p class="comment-meta">
																		由<cite class="comment-author">员工B</cite>发表在《
																		<a href="#">世界，您好！</a>
																		》
																		<span class="approve">[待审]</span>
																	</p>

																	<blockquote>
																		<p>XXXXXXXXXXxxxxxxxxxxxxxxxxxx</p>
																	</blockquote>
																	<p class="row-actions">
																		<span class="approve">
																			<a href="#" data-wp-lists="dim:the-comment-list:comment-1:unapproved:e7e7d3:e7e7d3:new=approved" class="vim-a" aria-label="批准这条评论">批准</a>
																		</span>
																		<span class="unapprove">
																			<a href="#" data-wp-lists="dim:the-comment-list:comment-1:unapproved:e7e7d3:e7e7d3:new=unapproved" class="vim-u" aria-label="驳回这条评论">驳回</a>
																		</span>
																		<span class="reply hide-if-no-js">
																			|
																			<a onclick="window.commentReply &amp;&amp; commentReply.open('1','1');return false;" class="vim-r hide-if-no-js" aria-label="回复这条评论" href="#">回复</a>
																		</span>
																		<span class="edit">
																			|
																			<a href="#" aria-label="编辑此评论">编辑</a>
																		</span>
																		<span class="spam">
																			|
																			<a href="#" data-wp-lists="delete:the-comment-list:comment-1::spam=1" class="vim-s vim-destructive" aria-label="将这条评论标记为垃圾评论">垃圾评论</a>
																		</span>
																		<span class="trash">
																			|
																			<a href="#" data-wp-lists="delete:the-comment-list:comment-1::trash=1" class="delete vim-d vim-destructive" aria-label="将此评论移至回收站">移至回收站</a>
																		</span>
																		<span class="view">
																			|
																			<a class="comment-link" href="#" aria-label="查看此评论">查看</a>
																		</span>
																	</p>
																</div></li>
														</ul>
														<h3 class="screen-reader-text">查看更多评论</h3>
														<ul class="subsubsub">
															<li class="all"><a href="#">
																	全部
																	<span class="count">
																		（
																		<span class="all-count">2</span>
																		）
																	</span>
																</a> |</li>
															<li class="moderated"><a href="#">
																	待审
																	<span class="count">
																		（
																		<span class="pending-count">0</span>
																		）
																	</span>
																</a> |</li>
															<li class="approved"><a href="#">
																	已批准
																	<span class="count">
																		（
																		<span class="approved-count">2</span>
																		）
																	</span>
																</a> |</li>
															<li class="spam"><a href="#">
																	垃圾
																	<span class="count">
																		（
																		<span class="spam-count">0</span>
																		）
																	</span>
																</a> |</li>
															<li class="trash"><a href="#">
																	回收站
																	<span class="count">
																		（
																		<span class="trash-count">0</span>
																		）
																	</span>
																</a></li>
														</ul>
														<form method="get">
															<div id="com-reply" style="display: none;">
																<div id="replyrow" style="display: none;">
																	<fieldset class="comment-reply">
																		<legend>
																			<span class="hidden" id="editlegend">编辑评论</span>
																			<span class="hidden" id="replyhead">回复评论</span>
																			<span class="hidden" id="addhead">添加新评论</span>
																		</legend>

																		<div id="replycontainer">
																			<label for="replycontent" class="screen-reader-text">评论</label>
																			<div id="wp-replycontent-wrap" class="wp-core-ui wp-editor-wrap html-active">
																				<link rel="stylesheet" id="editor-buttons-css" href="static/wel/editor.css" type="text/css" media="all">
																				<div id="wp-replycontent-editor-container" class="wp-editor-container">
																					<div id="qt_replycontent_toolbar" class="quicktags-toolbar">
																						<input id="qt_replycontent_strong" class="ed_button button button-small" aria-label="粗体" value="b" type="button">
																						<input id="qt_replycontent_em" class="ed_button button button-small" aria-label="斜体" value="i" type="button">
																						<input id="qt_replycontent_link" class="ed_button button button-small" aria-label="插入链接" value="link" type="button">
																						<input id="qt_replycontent_block" class="ed_button button button-small" aria-label="块引用" value="b-quote" type="button">
																						<input id="qt_replycontent_del" class="ed_button button button-small" aria-label="删除的文字（删除线）" value="del" type="button">
																						<input id="qt_replycontent_ins" class="ed_button button button-small" aria-label="插入的文字" value="ins" type="button">
																						<input id="qt_replycontent_img" class="ed_button button button-small" aria-label="插入图像" value="img" type="button">
																						<input id="qt_replycontent_ul" class="ed_button button button-small" aria-label="项目符号列表" value="ul" type="button">
																						<input id="qt_replycontent_ol" class="ed_button button button-small" aria-label="编号列表" value="ol" type="button">
																						<input id="qt_replycontent_li" class="ed_button button button-small" aria-label="列表项目" value="li" type="button">
																						<input id="qt_replycontent_code" class="ed_button button button-small" aria-label="代码" value="code" type="button">
																						<input id="qt_replycontent_close" class="ed_button button button-small" title="关闭所有打开的标签" value="关闭标签" type="button">
																					</div>
																					<textarea class="wp-editor-area" rows="20" cols="40" name="replycontent" id="replycontent"></textarea>
																				</div>
																			</div>

																		</div>

																		<div id="edithead" style="display: none;">
																			<div class="inside">
																				<label for="author-name">姓名</label>
																				<input name="newcomment_author" size="50" id="author-name" type="text">
																			</div>

																			<div class="inside">
																				<label for="author-email">电子邮件</label>
																				<input name="newcomment_author_email" size="50" id="author-email" type="text">
																			</div>

																			<div class="inside">
																				<label for="author-url">URL</label>
																				<input id="author-url" name="newcomment_author_url" class="code" size="103" type="text">
																			</div>
																		</div>

																		<p id="replysubmit" class="submit">
																			<a href="#comments-form" class="save button button-primary alignright">
																				<span id="addbtn" style="display: none;">添加评论</span>
																				<span id="savebtn" style="display: none;">更新评论</span>
																				<span id="replybtn" style="display: none;">提交回复</span>
																			</a>
																			<a href="#comments-form" class="cancel button alignleft">取消</a>
																			<span class="waiting spinner"></span>
																			<span class="error" style="display: none;"></span>
																		</p>

																		<input name="action" id="action" value="" type="hidden">
																		<input name="comment_ID" id="comment_ID" value="" type="hidden">
																		<input name="comment_post_ID" id="comment_post_ID" value="" type="hidden">
																		<input name="status" id="status" value="" type="hidden">
																		<input name="position" id="position" value="-1" type="hidden">
																		<input name="checkbox" id="checkbox" value="0" type="hidden">
																		<input name="mode" id="mode" value="dashboard" type="hidden">
																		<input id="_ajax_nonce-replyto-comment" name="_ajax_nonce-replyto-comment" value="66c19ec3f3" type="hidden">
																		<input id="_wp_unfiltered_html_comment" name="_wp_unfiltered_html_comment" value="886b55e70d" type="hidden">
																	</fieldset>
																</div>
															</div>
														</form>
														<div class="hidden" id="trash-undo-holder">
															<div class="trash-undo-inside">
																<strong></strong>的评论已被移动到回收站。
																<span class="undo untrash">
																	<a href="#">撤销</a>
																</span>
															</div>
														</div>
														<div class="hidden" id="spam-undo-holder">
															<div class="spam-undo-inside">
																<strong></strong>的评论已被标记为垃圾评论。
																<span class="undo unspam">
																	<a href="#">撤销</a>
																</span>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>

										<!-- _________ -->
										<div id="jetpack_summary_widget" class="postbox ">
											<button type="button" class="handlediv" aria-expanded="true">
												<span class="screen-reader-text">切换面板：报表</span>
												<span class="toggle-indicator" aria-hidden="true"></span>
											</button>
											<h2 class="hndle ui-sortable-handle">
												<span>报表</span>
											</h2>
											<div class="inside">
												<div id="community-events" class="community-events" aria-hidden="false"></div>

												<div class="wordpress-news hide-if-no-js" style="">
													<div class="rss-widget">
														<ul>
															<li></li>
														</ul>
													</div>

													<div id="myChart" style="width: 400px; height: 400px;"></div>
													<script type="text/javascript">
												        // 基于准备好的dom，初始化echarts实例
												        var myChart = echarts.init(document.getElementById('myChart'));
												
												        // 指定图表的配置项和数据
												        //	rows 不知怎么拿
											            var rows = "";
											            /* option = {
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
											            }; */
											            
											            var option = {
											                    title: {
											                        text: '学员兴趣爱好'
											                    },
											                    tooltip: {},
											                    legend: {
											                        data:['人数']
											                    },
											                    xAxis: {
											                        data: ["篮球","足球","乒乓球","赛车","骑行","看书"]
											                    },
											                    yAxis: {},
											                    series: [{
											                        name: '人数',
											                        type: 'bar',
											                        data: [5, 20, 36, 10, 10, 20]
											                    }]
											                };

											            myChart.setOption(option);
												    </script>

												</div>

												<p class="community-events-footer">
													<a href="https://make.wordpress.org/community/meetups-landing-page" target="_blank">
														饼图
														<span aria-hidden="true" class="dashicons dashicons-external"></span>
													</a>
													|
													<a href="#" target="_blank">
														折线图
														<span aria-hidden="true" class="dashicons dashicons-external"></span>
													</a>
												</p>

											</div>
										</div>
									</div>
								</div>
								<div id="postbox-container-2" class="postbox-container">
									<div id="side-sortables" class="meta-box-sortables ui-sortable">
										<!-- <div id="dashboard_quick_press" class="postbox ">
											<button type="button" class="handlediv" aria-expanded="true">
												<span class="screen-reader-text">
													切换面板：
													<span class="hide-if-no-js">快速草稿</span>
													<span class="hide-if-js">草稿</span>
												</span>
												<span class="toggle-indicator" aria-hidden="true"></span>
											</button>
											<h2 class="hndle ui-sortable-handle">
												<span>
													<span class="hide-if-no-js">快速草稿</span>
													<span class="hide-if-js">草稿</span>
												</span>
											</h2>
											<div class="inside">

												<form name="post" action="/news/saveOrUpdate.do" method="post" id="quick-press" class="initial-form hide-if-no-js">


													<div class="input-text-wrap" id="title-wrap">
														<label class="prompt" for="title" id="title-prompt-text"> 标题 </label>
														<input name="title" id="title" autocomplete="off" type="text">
													</div>

													<div class="textarea-wrap" id="description-wrap">
														<label class="prompt" for="content" id="content-prompt-text">在想些什么？</label>
														<textarea name="content" id="content" class="mceEditor" rows="3" cols="15" autocomplete="off"></textarea>
													</div>

													<p class="submit">
														<input name="save" id="save-post" class="button button-primary" value="保存草稿" type="submit">
														<br class="clear">
													</p>

												</form>
												<div class="drafts">
													<h2 class="hide-if-no-js">草稿</h2>
													<ul>
														<li>
															<div class="draft-title">
																<a href="#" aria-label="编辑“(无标题)”">Xxx</a>
																<time datetime="2017-11-11T02:19:06+00:00">2017年11月11日</time>
															</div>
															<p>hello word</p>
														</li>
														<li>
															<div class="draft-title">
																<a href="#" aria-label="编辑“(无标题)”">Yyy</a>
																<time datetime="2017-11-11T02:19:06+00:00">2017年11月11日</time>
															</div>
															<p>你好世界</p>
														</li>
													</ul>
												</div>
											</div>
										</div> -->

										<div id="dashboard_quick_press" class="postbox">
											<button type="button" class="handlediv" aria-expanded="true">
												<span class="screen-reader-text">
													切换面板：
													<span class="hide-if-no-js">快速草稿</span>
													<span class="hide-if-js">草稿</span>
												</span>
												<span class="toggle-indicator" aria-hidden="true"></span>
											</button>
											<h2 class="hndle ui-sortable-handle">
												<span>
													<span class="hide-if-no-js">快速草稿</span>
													<span class="hide-if-js">草稿</span>
												</span>
											</h2>
											<div class="inside">
												<form name="post" action="/news/saveOrUpdate.do" method="post" id="quick-press" class="hide-if-no-js">


													<div class="input-text-wrap" id="title-wrap">
														<label class="prompt" for="title" id="title-prompt-text"> 标题 </label>
														<input name="title" id="title" autocomplete="off" type="text">
													</div>

													<div class="textarea-wrap" id="description-wrap">
														<label class="prompt" for="content" id="content-prompt-text">在想些什么？</label>
														<textarea name="content" id="content" class="mceEditor" rows="3" cols="15" autocomplete="off" style="overflow-y: auto; overflow-x: hidden; height: 34px;"></textarea>
													</div>

													<p class="submit">
														<input name="action" id="quickpost-action" value="post-quickdraft-save" type="hidden">
														<input name="post_ID" value="75" type="hidden">
														<input name="post_type" value="post" type="hidden">
														<input id="_wpnonce" name="_wpnonce" value="2e1fff14f7" type="hidden">
														<input name="_wp_http_referer" value="/wp-admin/post.php" type="hidden">
														<input name="save" id="save-post" class="button button-primary" value="保存草稿" type="submit">
														<br class="clear">
													</p>

												</form>
												<div class="drafts">
													<h2 class="hide-if-no-js">草稿</h2>
													<ul>
														<li style="background: rgba(0, 0, 0, 0) none repeat scroll 0% 0%;">
															<div class="draft-title">
																<a href="" aria-label="编辑“q”">q</a>
																<time datetime="2017-12-26T22:29:31+00:00">2017年12月26日</time>
															</div>
															<p>q</p>
														</li>
														<li>
															<div class="draft-title">
																<a href="" aria-label="编辑“code”">code</a>
																<time datetime="2017-11-18T17:59:01+00:00">2017年11月18日</time>
															</div>
														</li>
														<li>
															<div class="draft-title">
																<a href="" aria-label="编辑“(无标题)”">(无标题)</a>
																<time datetime="2017-11-11T02:19:06+00:00">2017年11月11日</time>
															</div>
															<p>hello word</p>
														</li>
													</ul>
												</div>
											</div>
										</div>




										<!-- ________ -->
										<div id="dashboard_primary" class="postbox ">
											<button type="button" class="handlediv" aria-expanded="true">
												<span class="screen-reader-text">切换面板：News</span>
												<span class="toggle-indicator" aria-hidden="true"></span>
											</button>
											<h2 class="hndle ui-sortable-handle">
												<span>News</span>
											</h2>
											<div class="inside">
												<div id="community-events" class="community-events" aria-hidden="false"></div>
												<div class="wordpress-news hide-if-no-js" style="">
													<div class="rss-widget">
														<ul>
															<li><a target="_blank" class="rsswidget" href="http://store.steampowered.com/app/374320/DARK_SOULS_III/">赞美太阳 ！</a></li>
															<li><a target="_blank" class="rsswidget" href="http://store.steampowered.com/app/374320/DARK_SOULS_III/">林克！</a></li>
															<li><a target="_blank" class="rsswidget" href="http://store.steampowered.com/app/374320/DARK_SOULS_III/">马里奥 ！</a></li>
														</ul>
													</div>
												</div>

												<p class="community-events-footer">
													<a href="https://make.wordpress.org/community/meetups-landing-page" target="_blank">
														发起聚会
														<span aria-hidden="true" class="dashicons dashicons-external"></span>
													</a>
													|
													<a href="http://www.520it.com/obtain" target="_blank">
														公司近期新闻
														<span aria-hidden="true" class="dashicons dashicons-external"></span>
													</a>
												</p>

											</div>
										</div>

										<!-- __________报表________ -->

									</div>
								</div>
								<div id="postbox-container-3" class="postbox-container">
									<div id="column3-sortables" class="meta-box-sortables ui-sortable empty-container" data-emptystring="拖动方块至此"></div>
								</div>
								<div id="postbox-container-4" class="postbox-container">
									<div id="column4-sortables" class="meta-box-sortables ui-sortable empty-container" data-emptystring="拖动方块至此"></div>
								</div>
							</div>

							<input id="closedpostboxesnonce" name="closedpostboxesnonce" value="c59b8f460f" type="hidden">
							<input id="meta-box-order-nonce" name="meta-box-order-nonce" value="89b1be1636" type="hidden">
						</div>
						<!-- dashboard-widgets-wrap -->

					</div>
					<!-- wrap -->



					<div class="clear"></div>
				</div>
				<!-- wpbody-content -->
				<div class="clear"></div>
			</div>
			<!-- wpbody -->
			<div class="clear"></div>
		</div>
		<!-- wpcontent -->

		<script type="text/javascript">list_args = {"class":"WP_Comments_List_Table","screen":{"id":"dashboard","base":"dashboard"}};</script>
		<script type="text/javascript">list_args = {"class":"WP_Comments_List_Table","screen":{"id":"dashboard","base":"dashboard"}};</script>

		<script type="text/javascript">
/* <![CDATA[ */
var commonL10n = {"warnDelete":"\u60a8\u5373\u5c06\u4ece\u60a8\u7684\u7ad9\u70b9\u6c38\u4e45\u5220\u9664\u8fd9\u4e9b\u9879\u76ee\u3002\n\u6b64\u64cd\u4f5c\u65e0\u6cd5\u64a4\u6d88\u3002\n\u6309\u201c\u53d6\u6d88\u201d\u53ef\u53d6\u6d88\uff0c\u6309\u201c\u786e\u5b9a\u201d\u53ef\u786e\u8ba4\u5220\u9664\u3002","dismiss":"\u5ffd\u7565\u6b64\u901a\u77e5\u3002","collapseMenu":"\u6298\u53e0\u4e3b\u83dc\u5355","expandMenu":"\u5c55\u5f00\u4e3b\u83dc\u5355"};
var wpAjax = {"noPerm":"\u62b1\u6b49\uff0c\u60a8\u4e0d\u80fd\u505a\u90a3\u4e2a\u3002","broken":"\u53d1\u751f\u4e86\u65e0\u6cd5\u8bc6\u522b\u7684\u9519\u8bef\u3002"};
var quicktagsL10n = {"closeAllOpenTags":"\u5173\u95ed\u6240\u6709\u6253\u5f00\u7684\u6807\u7b7e","closeTags":"\u5173\u95ed\u6807\u7b7e","enterURL":"\u8f93\u5165URL","enterImageURL":"\u8f93\u5165\u56fe\u50cfURL","enterImageDescription":"\u4e3a\u56fe\u50cf\u8f93\u5165\u63cf\u8ff0","textdirection":"\u6587\u672c\u65b9\u5411","toggleTextdirection":"\u5207\u6362\u7f16\u8f91\u5668\u6587\u672c\u4e66\u5199\u65b9\u5411","dfw":"\u514d\u6253\u6270\u5199\u4f5c\u6a21\u5f0f","strong":"\u7c97\u4f53","strongClose":"\u5173\u95ed\u7c97\u4f53\u6807\u7b7e","em":"\u659c\u4f53","emClose":"\u5173\u95ed\u659c\u4f53\u6807\u7b7e","link":"\u63d2\u5165\u94fe\u63a5","blockquote":"\u5757\u5f15\u7528","blockquoteClose":"\u5173\u95ed\u5757\u5f15\u7528\u6807\u7b7e","del":"\u5220\u9664\u7684\u6587\u5b57\uff08\u5220\u9664\u7ebf\uff09","delClose":"\u5173\u95ed\u5220\u9664\u7ebf\u6807\u7b7e","ins":"\u63d2\u5165\u7684\u6587\u5b57","insClose":"\u5173\u95ed\u63d2\u5165\u7684\u6587\u5b57\u6807\u7b7e","image":"\u63d2\u5165\u56fe\u50cf","ul":"\u9879\u76ee\u7b26\u53f7\u5217\u8868","ulClose":"\u5173\u95ed\u9879\u76ee\u7b26\u53f7\u5217\u8868\u6807\u7b7e","ol":"\u7f16\u53f7\u5217\u8868","olClose":"\u5173\u95ed\u7f16\u53f7\u5217\u8868\u6807\u7b7e","li":"\u5217\u8868\u9879\u76ee","liClose":"\u5173\u95ed\u5217\u8868\u9879\u76ee\u6807\u7b7e","code":"\u4ee3\u7801","codeClose":"\u5173\u95ed\u4ee3\u7801\u6807\u7b7e","more":"\u63d2\u5165\u201cMore\u201d\u6807\u7b7e"};
var adminCommentsL10n = {"hotkeys_highlight_first":"","hotkeys_highlight_last":"","replyApprove":"\u6279\u51c6\u5e76\u56de\u590d","reply":"\u56de\u590d","warnQuickEdit":"\u60a8\u786e\u5b9a\u8981\u7f16\u8f91\u8fd9\u6761\u8bc4\u8bba\u5417\uff1f\n\u60a8\u505a\u51fa\u7684\u4fee\u6539\u5c06\u4f1a\u4e22\u5931\u3002","warnCommentChanges":"\u60a8\u786e\u5b9a\u8981\u7ee7\u7eed\u5417\uff1f\n\u60a8\u5bf9\u8bc4\u8bba\u8fdb\u884c\u7684\u4fee\u6539\u5c06\u4f1a\u4e22\u5931\u3002","docTitleComments":"\u8bc4\u8bba","docTitleCommentsCount":"\u8bc4\u8bba\uff08%s\uff09"};
var postBoxL10n = {"postBoxEmptyString":"\u62d6\u52a8\u65b9\u5757\u81f3\u6b64"};
var communityEventsData = {"nonce":"91eca7e263","cache":"","l10n":{"enter_closest_city":"\u952e\u5165\u8ddd\u79bb\u60a8\u6700\u8fd1\u7684\u57ce\u5e02\u6765\u67e5\u627e\u9644\u8fd1\u7684\u6d3b\u52a8\u3002","error_occurred_please_try_again":"\u53d1\u751f\u4e86\u4e00\u4e2a\u9519\u8bef\uff0c\u8bf7\u91cd\u8bd5\u3002","attend_event_near_generic":"\u53c2\u52a0\u4e00\u573a\u60a8\u9644\u8fd1\u7684\u6d3b\u52a8\u3002","could_not_locate_city":"\u4e0d\u80fd\u8bc6\u522b\u57ce\u5e02\u540d\uff1a%s\u3002\u8bf7\u66f4\u6362\u4e00\u4e2a\u9644\u8fd1\u7684\u57ce\u5e02\u540d\u8bd5\u8bd5\uff0c\u6bd4\u5982\u5317\u4eac\u3001\u676d\u5dde\u3001\u53a6\u95e8\u3001\u6210\u90fd\u3001\u91cd\u5e86\u3002","city_updated":"\u57ce\u5e02\u5df2\u66f4\u65b0\u3002\u5217\u51fa\u5728%s\u9644\u8fd1\u7684\u6d3b\u52a8\u3002"}};
var _wpCustomizeLoaderSettings = {"url":"","isCrossDomain":false,"browser":{"mobile":false,"ios":false},"l10n":{"saveAlert":"\u79bb\u5f00\u8fd9\u4e2a\u9875\u9762\uff0c\u60a8\u6240\u505a\u7684\u66f4\u6539\u5c06\u4e22\u5931\u3002","mainIframeTitle":"\u5b9a\u5236\u5668"}};
var thickboxL10n = {"next":"\u4e0b\u4e00\u9875 >","prev":"< \u4e0a\u4e00\u9875","image":"\u56fe\u50cf","of":"\/","close":"\u5173\u95ed","noiframes":"\u8fd9\u4e2a\u529f\u80fd\u9700\u8981iframe\u7684\u652f\u6301\u3002\u60a8\u53ef\u80fd\u7981\u6b62\u4e86iframe\u7684\u663e\u793a\uff0c\u6216\u60a8\u7684\u6d4f\u89c8\u5668\u4e0d\u652f\u6301\u6b64\u529f\u80fd\u3002","loadingAnimation":""};
var plugininstallL10n = {"plugin_information":"\u63d2\u4ef6\uff1a","plugin_modal_label":"\u63d2\u4ef6\u8be6\u60c5","ays":"\u60a8\u786e\u8ba4\u8981\u5b89\u88c5\u8be5\u63d2\u4ef6\u5417\uff1f"};
var _wpUpdatesSettings = {"ajax_nonce":"29c6744fb2","l10n":{"searchResults":"\u201c%s\u201d\u7684\u641c\u7d22\u7ed3\u679c","searchResultsLabel":"\u641c\u7d22\u7ed3\u679c","noPlugins":"\u770b\u8d77\u6765\u73b0\u5728\u5e76\u6ca1\u6709\u53ef\u7528\u7684\u63d2\u4ef6\u3002","noItemsSelected":"\u8bf7\u4e3a\u6b64\u64cd\u4f5c\u9009\u62e9\u81f3\u5c11\u4e00\u4e2a\u9879\u76ee\u3002","updating":"\u66f4\u65b0\u4e2d\u2026","pluginUpdated":"\u5df2\u66f4\u65b0\uff01","themeUpdated":"\u5df2\u66f4\u65b0\uff01","update":"\u66f4\u65b0","updateNow":"\u73b0\u5728\u66f4\u65b0","pluginUpdateNowLabel":"\u7acb\u5373\u66f4\u65b0%s","updateFailedShort":"\u66f4\u65b0\u5931\u8d25\uff01","updateFailed":"\u66f4\u65b0\u5931\u8d25\uff1a%s","pluginUpdatingLabel":"\u6b63\u5728\u66f4\u65b0%s...","pluginUpdatedLabel":"%s\u5df2\u66f4\u65b0\uff01","pluginUpdateFailedLabel":"%s\u66f4\u65b0\u5931\u8d25","updatingMsg":"\u66f4\u65b0\u4e2d\u2026\u8bf7\u7a0d\u7b49\u3002","updatedMsg":"\u66f4\u65b0\u6210\u529f\u5b8c\u6210\u3002","updateCancel":"\u66f4\u65b0\u5df2\u53d6\u6d88\u3002","beforeunload":"\u5982\u679c\u60a8\u79bb\u5f00\u6b64\u9875\u9762\uff0c\u66f4\u65b0\u53ef\u80fd\u672a\u80fd\u5b8c\u6210\u3002","installNow":"\u73b0\u5728\u5b89\u88c5","pluginInstallNowLabel":"\u7acb\u5373\u5b89\u88c5%s","installing":"\u6b63\u5728\u5b89\u88c5\u2026","pluginInstalled":"\u5df2\u5b89\u88c5\uff01","themeInstalled":"\u5df2\u5b89\u88c5\uff01","installFailedShort":"\u5b89\u88c5\u5931\u8d25\uff01","installFailed":"\u5b89\u88c5\u5931\u8d25\uff1a%s","pluginInstallingLabel":"\u6b63\u5728\u5b89\u88c5%s\u2026","themeInstallingLabel":"\u6b63\u5728\u5b89\u88c5%s\u2026","pluginInstalledLabel":"%s\u5df2\u5b89\u88c5\uff01","themeInstalledLabel":"%s\u5df2\u5b89\u88c5\uff01","pluginInstallFailedLabel":"%s\u5b89\u88c5\u5931\u8d25","themeInstallFailedLabel":"%s\u5b89\u88c5\u5931\u8d25","installingMsg":"\u6b63\u5728\u5b89\u88c5\u2026\u8bf7\u7a0d\u7b49\u3002","installedMsg":"\u5b89\u88c5\u6210\u529f\u5b8c\u6210\u3002","importerInstalledMsg":"\u5bfc\u5165\u5668\u5b89\u88c5\u6210\u529f\u3002<a href=\"%s\">\u8fd0\u884c\u5bfc\u5165\u5668<\/a>","aysDelete":"\u60a8\u786e\u5b9a\u8981\u5220\u9664%s\uff1f","aysDeleteUninstall":"\u60a8\u786e\u5b9a\u8981\u5220\u9664%s\u53ca\u5176\u6570\u636e\uff1f","aysBulkDelete":"\u60a8\u786e\u5b9a\u8981\u5220\u9664\u9009\u4e2d\u7684\u63d2\u4ef6\u53ca\u5176\u6570\u636e\uff1f","aysBulkDeleteThemes":"\u6ce8\u610f\uff1a\u8fd9\u4e9b\u4e3b\u9898\u53ef\u80fd\u5728\u7f51\u7edc\u4e0a\u7684\u5176\u4ed6\u7ad9\u70b9\u4e0a\u542f\u7528\uff0c\u60a8\u786e\u5b9a\u8981\u7ee7\u7eed\uff1f","deleting":"\u6b63\u5728\u5220\u9664\u2026","deleteFailed":"\u5220\u9664\u5931\u8d25\uff1a%s","pluginDeleted":"\u5df2\u5220\u9664\uff01","themeDeleted":"\u5df2\u5220\u9664\uff01","livePreview":"\u5b9e\u65f6\u9884\u89c8","activatePlugin":"\u542f\u7528","activateTheme":"\u542f\u7528","activatePluginLabel":"\u6fc0\u6d3b%s","activateThemeLabel":"\u6fc0\u6d3b%s","activateImporter":"\u8fd0\u884c\u5bfc\u5165\u5668","activateImporterLabel":"\u8fd0\u884c%s","unknownError":"\u53d1\u751f\u4e86\u672a\u77e5\u9519\u8bef","connectionError":"\u8fde\u63a5\u4e22\u5931\u6216\u670d\u52a1\u5668\u5fd9\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\u3002","nonceError":"\u53d1\u751f\u4e86\u9519\u8bef\uff0c\u8bf7\u5237\u65b0\u6b64\u9875\u9762\u5e76\u91cd\u8bd5\u3002","pluginsFound":"\u627e\u5230\u7684\u63d2\u4ef6\uff1a%d","noPluginsFound":"\u672a\u627e\u5230\u63d2\u4ef6\uff0c\u8bd5\u8bd5\u5176\u4ed6\u641c\u7d22\u6761\u4ef6\u3002"}};
var heartbeatSettings = {"nonce":"aafe8278ba"};
var authcheckL10n = {"beforeunload":"\u60a8\u7684\u767b\u5f55\u4f1a\u8bdd\u5df2\u8fc7\u671f\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55\u3002","interval":"180"};
var wpLinkL10n = {"title":"\u63d2\u5165\u6216\u7f16\u8f91\u94fe\u63a5","update":"\u66f4\u65b0","save":"\u6dfb\u52a0\u94fe\u63a5","noTitle":"(\u65e0\u6807\u9898)","noMatchesFound":"\u672a\u627e\u5230\u7ed3\u679c\u3002","linkSelected":"\u94fe\u63a5\u5df2\u9009\u62e9\u3002","linkInserted":"\u94fe\u63a5\u5df2\u63d2\u5165\u3002"};
var uiAutocompleteL10n = {"noResults":"\u672a\u627e\u5230\u7ed3\u679c\u3002","oneResult":"\u627e\u52301\u4e2a\u7ed3\u679c\u3002\u4f7f\u7528\u4e0a\u4e0b\u65b9\u5411\u952e\u6765\u5bfc\u822a\u3002","manyResults":"\u627e\u5230%d\u4e2a\u7ed3\u679c\u3002\u4f7f\u7528\u4e0a\u4e0b\u65b9\u5411\u952e\u6765\u5bfc\u822a\u3002","itemSelected":"\u5df2\u9009\u62e9\u9879\u76ee\u3002"};/* ]]> */
</script>
		<script type="text/javascript" src="static/wel/load-scripts.php"></script>
		<script type="text/javascript" src="static/wel/devicepx-jetpack.js"></script>
		<script type="text/javascript" src="static/wel/tracks-ajax.js"></script>
		<script type="text/javascript" src="static/wel/jetpack-jitm.js"></script>
		<script type="text/javascript" src="static/wel/mustache.js"></script>
		<script type="text/javascript" src="static/wel/notes-common-v2.js"></script>
		<script type="text/javascript" src="static/wel/admin-bar-v2.js"></script>

		<div id="wp-link-backdrop" style="display: none"></div>
		<div id="wp-link-wrap" class="wp-core-ui" style="display: none" role="dialog" aria-labelledby="link-modal-title">
			<form id="wp-link" tabindex="-1">
				<input id="_ajax_linking_nonce" name="_ajax_linking_nonce" value="7a36fe4db2" type="hidden">
				<h1 id="link-modal-title">插入或编辑链接</h1>
				<button type="button" id="wp-link-close">
					<span class="screen-reader-text">关闭</span>
				</button>
				<div id="link-selector">
					<div id="link-options">
						<p class="howto" id="wplink-enter-url">输入目标URL</p>
						<div>
							<label>
								<span>URL</span>
								<input id="wp-link-url" aria-describedby="wplink-enter-url" type="text">
							</label>
						</div>
						<div class="wp-link-text-field">
							<label>
								<span>链接文本</span>
								<input id="wp-link-text" type="text">
							</label>
						</div>
						<div class="link-target">
							<label>
								<span></span>
								<input id="wp-link-target" type="checkbox">
								在新标签页中打开链接
							</label>
						</div>
					</div>
					<p class="howto" id="wplink-link-existing-content">或链接到站点中的内容</p>
					<div id="search-panel">
						<div class="link-search-wrapper">
							<label>
								<span class="search-label">搜索</span>
								<input id="wp-link-search" class="link-search-field" autocomplete="off" aria-describedby="wplink-link-existing-content" type="search">
								<span class="spinner"></span>
							</label>
						</div>
						<div id="search-results" class="query-results" tabindex="0">
							<ul></ul>
							<div class="river-waiting">
								<span class="spinner"></span>
							</div>
						</div>
						<div id="most-recent-results" class="query-results" tabindex="0">
							<div class="query-notice" id="query-notice-message">
								<em class="query-notice-default">未指定搜索条件。自动显示近期条目。</em> <em class="query-notice-hint screen-reader-text">搜索或使用上下方向键来选择一项。</em>
							</div>
							<ul></ul>
							<div class="river-waiting">
								<span class="spinner"></span>
							</div>
						</div>
					</div>
				</div>
				<div class="submitbox">
					<div id="wp-link-cancel">
						<button type="button" class="button">取消</button>
					</div>
					<div id="wp-link-update">
						<input value="添加链接" class="button button-primary" id="wp-link-submit" name="wp-link-submit" type="submit">
					</div>
				</div>
			</form>
		</div>

		<div class="clear"></div>
	</div>
	<!-- wpwrap -->
	<script type="text/javascript">if(typeof wpOnload=='function')wpOnload();</script>


	<div id="wp-a11y-speak-polite" aria-live="polite" aria-relevant="additions text" aria-atomic="true" class="screen-reader-text wp-a11y-speak-region"></div>
	<div id="wp-a11y-speak-assertive" aria-live="assertive" aria-relevant="additions text" aria-atomic="true" class="screen-reader-text wp-a11y-speak-region"></div>
	<div class="quick-draft-textarea-clone"
		style="display: none; font-family: -apple-system, BlinkMacSystemFont,&amp; amp; amp; amp; amp; amp; quot; Segoe UI&amp;amp; amp; amp; amp; amp; quot; , Roboto , Oxygen-Sans, Ubuntu, Cantarell, &amp;amp; amp; amp; amp; amp; quot; Helvetica Neue&amp;amp; amp; amp; amp; amp; quot; , sans-serif; font-size: 14px; line-height: 19.6px; padding: 6px 7px; white-space: pre-wrap; overflow-wrap: break-word;"></div>
	<div id="customize-container"></div>
</body>
</html>