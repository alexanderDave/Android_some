<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>image list</title>
</head>
<body><% String uri =  (String)request.getAttribute("uri");%>
	<dir>为了减轻服务器的压力，请您拖动图片到本地实现下载功能，给您带来不便敬请原谅</dir><br>
	<a href ="<%out.println(uri);%>">点击下载素材</a><br>
	<hr>	
	<%
		List<String> list = (List<String>)request.getAttribute("imgList");
		for(int i = 0; i < list.size();i++){
			String str = list.get(i).toString();
	 %>
		<img style="width: 60%;" alt="waiting" src="<%= str%>"><br><hr>
	<%} %>
</body>
</html>