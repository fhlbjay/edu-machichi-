<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>计划</title>
    <link rel="stylesheet" href="../../static/plugins/plan/style/documentation.css" type="text/css" />
    <link rel="stylesheet" href="../../static/plugins/plan/style/jalendar.css" type="text/css" />
    <script type="text/javascript" src="../../static/plugins/plan/js/jquery-1.10.2.min.js"></script><!--jQuery-->
    <script type="text/javascript" src="../../static/plugins/plan/js/jalendar.js"></script>
    <script type="text/javascript" src="../../static/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#myId').jalendar({
                customDay: '2017/12/01',  // Format: Year/Month/Day
                color: '#ed145a', // Unlimited Colors
                lang: 'EN' // Format: English — 'EN', Türkçe — 'TR'
            });
            $('#myId2').jalendar({
                customDay: '2016/02/29',
                color: '#023447',
                lang: 'ES'
            });
            $('#myId3').jalendar({
                customDay: '2017/12/01',  // Format: Year/Month/Day
//                color: '#ed145a', // Unlimited Colors
                lang: 'EN' // Format: English — 'EN', Türkçe — 'TR'
            });
        });
    </script>

</head>

<body>

<article>

   <!-- <div id="myId" class="jalendar">
        <div class="added-event" data-date="14/12/2017" data-time="Tüm Gün" data-title="WWDC 13 on San Francisco, LA"></div>
        <div class="added-event" data-date="16/12/2017" data-time="20:45" data-title="Tarkan İstanbul Concert on Harbiye Açık Hava Tiyatrosu"></div>
        <div class="added-event" data-date="17/12/2017" data-time="21:00" data-title="CodeCanyon İstanbul Meeting on Starbucks, Kadıköy"></div>
        <div class="added-event" data-date="2017/12/17" data-time="22:00" data-title="Front-End Design and Javascript Conferance on Haliç Kongre Merkezi"></div>
        <div class="added-event" data-date="2017/12/20" data-time="22:00" data-title="Lorem ipsum dolor sit amet"></div>
    </div>

    <div id="myId2" class="jalendar"></div>-->

    <div id="myId3" class="jalendar mid">
        <div class="added-event" data-date="9/12/2017" data-time="20:45" data-title="抽空收购一下BAT"></div>
        <div class="added-event" data-date="16/12/2017" data-time="20:45" data-title="明天给大家带早饭"></div>
        <div class="added-event" data-date="17/12/2017" data-time="21:20" data-title="快来五黑,吾单三路而打野"></div>
        <div class="added-event" data-date="17/12/2017" data-time="22:20" data-title="给哥哥唱个小曲儿"></div>
        <div class="added-event" data-date="17/12/2017" data-time="21:00" data-title="给你半个月把公司所有美女的资料整理出来"></div>
        <div class="added-event" data-date="18/12/2017" data-time="22:00" data-title="跟比尔盖茨谈笑风生"></div>
    </div>
</article>
<div style="text-align:center;clear:both">
    <%--<p>你好啊</p>--%>
   <%-- <p>发多少<a href="http://sc.chinaz.com/" target="_blank">站长素材</a></p>--%>
</div>
</body>
</html>