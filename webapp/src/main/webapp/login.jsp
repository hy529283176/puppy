<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="utf-8" />
    <title>登陆</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
  </head>
  <body>
        <div id="app_div">
        			<div class="app_header">
        				<div class="banner">

        				</div>
        			</div>
			<form action="${pageContext.request.contextPath}/dologin/toindex" method="post">
        			<div class="app_content">
        				<div class="app_content_login">
        					<div class="userico">
        						<img src="${pageContext.request.contextPath}/img/logo.png"/>
        					</div>
        					<div class="userCSS">
        						<img src="${pageContext.request.contextPath}/img/userico1.png"/>
        						<input type="text" id="username" name="username" value=""/>
        					</div>
        					<div class="userCSS">
        						<img src="${pageContext.request.contextPath}/img/passwd.png"/>
        						<input type="password" id="password" name="password" value=""/>
        					</div>
        					<div class="userCSS">
        							<input type="image" id="login_btn" alt="login"  src="${pageContext.request.contextPath}/img/login_btn.png" />
        					</div>
							<div style="margin: auto;width: 200px;padding-left: 15%;">
								<span style="text-align: center">${error}</span></div>
        				</div>
        			</div>
			</form>
        			<div class="app_footer">
        				<div class="copy_right">
        					<span>版权所有：Southgis</span>
        				</div>
        			</div>
        		</div>
  </body>
</html>
