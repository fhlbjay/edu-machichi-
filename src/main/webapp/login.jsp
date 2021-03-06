<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- bootstrap所需的三个meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- bootstrap所需的css -->
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="static/css/login.css">
<script type="text/javascript" src="/static/plugins/jquery-easyui/jquery.min.js"></script>
<script src="/static/plugins/jquery-easyui/plugins/jquery.ripples-min.js"></script>
<title>教育</title>
<%--点击登陆提交表单--%>
<script type="text/javascript">
	function login() {
		//使用ajax来进行异步请求,防止整个页面都重新加载
		$.post("/login.do", $("form").serialize(), function(data) {
			if (data.success) {
				window.parent.location.href = "/main.do";
			} else {
				alert(data.msg);
			}
		}, 'json');
	};
	function register() {
		//使用ajax来进行异步请求,防止整个页面都重新加载
		window.location.href = "/register.do";
	};
</script>
</head>
<body>
<main>
			<form class="form-signin" method="post">
				<h3 class="form-signin-heading">用户登录</h3>
				<input type="text" name="username" value="admin" placeholder="用户名" class="form-control" required autofocus>
					<input type="password" name="password" value="1111" id="inputPassword" class="form-control" placeholder="密码" required>
				<div class="checkbox">
					<label>
						<input type="checkbox" value="rememberme">记住密码
					</label>
				</div>
				<p class="submit">
					<input type="button" value="登录" onclick="login()" class="btn btn-lg btn-primary btn-block">
					<!-- <input type="button" value="注册" onclick="register()" class="btn btn-lg btn-primary btn-block">
 -->				</p>
				<div style="text-align: center;" class="login-help">
					<p>Copyright ©2017  六人行</p>
				</div>
			</form>
</main>
<script>
$(document).ready(function() {
	try {
 		$('body').ripples({
			resolution: 512,
			dropRadius: 20, //px
			perturbance: 0.08,
		});
		$('main').ripples({
			resolution: 128,
			dropRadius: 10, //px
			perturbance: 0.08,
			interactive: false
		});
	}
	catch (e) {
		$('.error').show().text(e);
	}

	// Automatic drops
	setInterval(function() {
		var $el = $('main');
		var x = Math.random() * $el.outerWidth();
		var y = Math.random() * $el.outerHeight();
		var dropRadius = 50;
		var strength = 0.01 + Math.random() * 0.01;

		$el.ripples('drop', x, y, dropRadius, strength);
	}, 4000);
});
</script>

</body>
</html>
