<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>确认授权页面</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <link rel="stylesheet" href="//i.gtimg.cn/vipstyle/frozenui/2.0.0/css/frozen.css"/>

    <style>

        html{
            padding: 0px;
            margin: 0px;
        }

        .title {
            background-color: #E9686B;
            height: 50px;
            padding-left: 20%;
            padding-right: 20%;
            color: white;
            line-height: 50px;
            font-size: 18px;
        }
        .title-left{
            float: right;
        }
        .title-right{
            float: left;
        }
        .title-left a{
            color: white;
        }
        .container{
            clear: both;
            text-align: center;
        }
        .btn {
            width: 350px;
            height: 35px;
            line-height: 35px;
            cursor: pointer;
            margin-top: 20px;
            border-radius: 3px;
            background-color: #E9686B;
            color: white;
            border: none;
            font-size: 15px;
        }
    </style>
</head>
<body style="margin: 0px">
<div class="title">
    <div class="title-right">OAUTH-BOOT 授权</div>
    <div class="title-left">
        <a href="#help">帮助</a>
    </div>
</div>
<div class="container">
    <h3 th:text="${clientId}+' 请求授权，该应用将获取你的以下信息'"></h3>
    <p>昵称，头像和性别</p>
    授权后表明你已同意 <a  href="#boot" style="color: #E9686B">OAUTH-BOOT 服务协议</a>
    <form id="confirmationForm" name="confirmationForm" method="post" action="/oauth/authorize">
        <input type="hidden" name="scope.all" value="true">
      <input type="hidden" name="user_oauth_approval" value="true">
     <%--   <input type="hidden" name="_csrf" th:value="${_csrf.getToken()}"/>--%>
        <button class="btn" type="submit"> 同意/授权</button>
    </form>
</div>
</body>

</html>
