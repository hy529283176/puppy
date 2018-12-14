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
        <span>静默授权</span>
    </div>
</div>

<script>
    var url = window.location.href.split('#')[0];
    var urll = url.split("?")[0];
    var appId = "${appId}";
    var codeValue = getParam("code");
    if (codeValue == null || codeValue === '') {
        window.location.href = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=' + window.appId + '&redirect_uri=' + encodeURIComponent(window.urll) + '&response_type=code&scope=snsapi_base&state=1#wechat_redirect'
    } else {
        getOpenId(codeValue); //把code传给后台获取用户信息
    }

    function getOpenId(code) {
        $.ajax({
            url:"${pageContext.request.contextPath}/wechatWebService/getOpenId?code="+code,
            type:'get',
            dataType:'json',
            success:function (data) {
                console.log(data);
            }
        });
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
