<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.io.*,java.util.*" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>页面</title>
</head>
<body>
<h2>success页面</h2>
<%
    // 重定向到新地址
    response.sendRedirect("http://localhost:8080/oauth/authorize?response_type=token&client_id=demoApp&redirect_uri=http://localhost:1080/");
%>
</form>
</body>
</html>