<%--
  Created by IntelliJ IDEA.
  User: Southgis_Puppy
  Date: 2018/12/5
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Puppy首页</title>
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js" ></script>
    <script type="text/javascript" src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js" ></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript">
        var aid = "";
        var time = "";
        var nonce = "";
        var sign = "";
        var url = window.location.href.split('#')[0];
        $.ajax({
            url:"${pageContext.request.contextPath}/wechatWebService/getWechatConfig?url="+url,
            type:'get',
            async:false,
            dataType:'json',
            success:function (data) {
                aid = data.appId;
                time = data.timestamp;
                nonce = data.nonceStr;
                sign = data.signature;
            }
        });

        //通过config接口注入权限验证配置
        wx.config({
            debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: aid, // 必填，公众号的唯一标识
            timestamp: time, // 必填，生成签名的时间戳
            nonceStr: nonce, // 必填，生成签名的随机串
            signature: sign, // 必填，签名
            jsApiList: [ // 必填，需要使用的JS接口列表
                'checkJsApi',
                'onMenuShareTimeline',
                'onMenuShareAppMessage',
                'onMenuShareQQ',
                'onMenuShareWeibo',
                'onMenuShareQZone',
                'hideMenuItems',
                'showMenuItems',
                'hideAllNonBaseMenuItem',
                'showAllNonBaseMenuItem',
                'translateVoice',
                'startRecord',
                'stopRecord',
                'onVoiceRecordEnd',
                'playVoice',
                'onVoicePlayEnd',
                'pauseVoice',
                'stopVoice',
                'uploadVoice',
                'downloadVoice',
                'chooseImage',
                'previewImage',
                'uploadImage',
                'downloadImage',
                'getNetworkType',
                'openLocation',
                'getLocation',
                'hideOptionMenu',
                'showOptionMenu',
                'closeWindow',
                'scanQRCode',
                'chooseWXPay',
                'openProductSpecificView',
                'addCard',
                'chooseCard',
                'openCard'
            ]
        });

        //通过ready接口处理成功验证
        wx.ready(function() {
            // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后
            // 1 判断当前版本是否支持指定 JS 接口，支持批量判断
            document.querySelector('#checkJsApi').onclick = function () {
                wx.checkJsApi({
                    jsApiList: [
                        'getNetworkType',
                        'previewImage'
                    ],
                    success: function (res) {
                        alert(JSON.stringify(res));
                    }
                });
            };

        });
    </script>
</head>

<body style="margin: 0;padding: 0;">
<div id="pageOne" data-role="page" style="height: 100%;width: 100%;">
    <div id="header" data-role="header" style="width: 100%;">
        <div style="width: 100%;">
					<span style="margin-left: 2%;">
						Puppy
					</span>
            <span style="margin-left: 45%;">
						<a href="#" class="ui-btn ui-icon-search ui-btn-icon-left">搜索</a>
						<a href="#">登陆</a>
					</span>
        </div>

        <div data-role="navbar">
            <ul>
                <li><a href="#anylink">首页</a></li>
                <li><a href="#anylink">狗狗</a></li>
                <li><a href="#anylink">猫咪</a></li>
                <li><a href="#anylink">鱼类</a></li>
                <li><a href="#anylink">其他</a></li>
            </ul>
        </div>
    </div>

    <div id="content" data-role="content">
        <input type="button" id="checkJsApi" value="检查JS接口">
    </div>
</div>

</body>

</html>
