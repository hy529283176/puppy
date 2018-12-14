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
        <span>非静默授权</span>
    </div>
</div>

<script>
    var url = window.location.href.split('#')[0];
    var openid = getCookies("openid");
    console.log(openid);

    $.ajax({
        url:"${pageContext.request.contextPath}/wechatWebService/getWechatConfig?url=" + encodeURIComponent(url),
        type:'get',
        dataType:'json',
        success:function (data) {
            console.log(data);
            //通过config接口注入权限验证配置
            wx.config({
                debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: data.appId, // 必填，公众号的唯一标识
                timestamp: data.timestamp, // 必填，生成签名的时间戳
                nonceStr: data.nonceStr, // 必填，生成签名的随机串
                signature: data.signature, // 必填，签名
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
        }
    });
    //通过ready接口处理成功验证
    wx.ready(function() {
        // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后
        // 1 判断当前版本是否支持指定 JS 接口，支持批量判断
        document.querySelector('#checkJsApi').onclick = function () {
            wx.checkJsApi({
                jsApiList: [
                    'getNetworkType',
                    'previewImage',
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
                    'uploadImage',
                    'downloadImage',
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
                ],
                success: function (res) {
                    alert(JSON.stringify(res));
                }
            });
        };
    });

    function getCookies(cookiename){
        var value = document.cookie.match(new RegExp("(^| )" + cookiename + "=([^;]*)(;|$)"));
        return null != value ? decodeURIComponent(value[2]) : null;

    }

    /**
     * 获取指定的URL参数值
     * URL:http://www.quwan.com/index?name=tyler
     * 参数：paramName URL参数
     * 调用方法:getParam("name")
     * 返回值:tyler
     */
    function getParam(paramName) {
        paramValue = "", isFound = !1;
        if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
            arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;
            while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++
        }
        return paramValue == "" && (paramValue = null), paramValue
    }
</script>

</body>

</html>
