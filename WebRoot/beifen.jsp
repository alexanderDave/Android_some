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
<body>	
	<%
		List<String> list = (List<String>)request.getAttribute("imgList");
		for(int i = 0; i < list.size();i++){
			String str = list.get(i).toString();
	 %>
		<%=str %><a href="<%out.println(str);%>">下载</a>
	<%} %>
</body>
</html>