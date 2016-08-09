<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    isELIgnored="false" pageEncoding="UTF-8"%>
    <%@page import="daxigua.Dao.baseDao"%>
<%@page import="daxigua.factoy.daoFactoy"%>
<%@page import="daxigua.Bean.userBean"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Welcome</title>
	<meta http-equiv="Expires" content="0">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-control" content="no-cache">
	<meta http-equiv="Cache" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.2.min.js"></script>
	<link href="<%=basePath%>css/index.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		  function jump(){
		  	   var page = $("#page").val();
		  	   
		  		if(page<0||page>${totalPages}){
		    		return;
		  		}else{
		    		location.href="<%=basePath%>/page.do?currentPage="+page;
		    	}
		  }
		  function show(){
		  		document.getElementById('div_insert').style.display = "";
		  }
  	</script>
</head>
<body>
	<div>
		<div>欢迎您:<%=request.getSession().getAttribute("uname") %></div>
		<hr>
	</div>
	
	<div style="margin-left: 40px;margin-right: 40px; font-size: 24px;">
		<div>
			<div style="text-align: left;display: inline;"><button onclick="show()">新增用户</button></div>
			<div style="text-align: right;float:right; ">
				<input type="text" name="search"><input type="button" value="搜索" onclick="">
			</div>
		</div>
		<div id = "div_insert" style="display: none;"><form action="<%=basePath%>/newuser.act" method="post">
		新建用户:<br>
		用户名:<input name = "uid" type="text"><br />
		密&nbsp;码:<input name = "upswd" type="password"><br />
		用户级别:<select><option selected="selected" value="0">0</option><option value="1">1</option></select>
		<input type="submit" value="提交" style="margin-left: 40px;">
		</form>
		
		</div>
		
        			
		<table style="border: 1px;">
			<tr><td>编号</td><td>用户名</td><td>密码</td><td>状态</td><td>级别</td><td colspan="2">操作</td></tr>
			<% 
				List<userBean> list =(List<userBean>)request.getAttribute("userList");
				for(int i =0;i<list.size();i++ ){
					userBean user = list.get(i);
			%>
				<tr><td><%=user.getId() %></td>
				<td><%=user.getUid()%></td>
				<td><%=user.getUpswd() %></td>
				<td><%=user.getUstatus() %></td>
				<td><%=user.getUgrade() %></td>
				<td><a href="<%=basePath%>/del.act?id=<%=user.getId() %>" onclick="return confirm('确定删除么？')">删除</a></td>
				<td><a href="<%=basePath%>/modify.act?id=<%=user.getId() %>">修改</a></td>							
				</tr>
			<% }%>
				</table>
		<div style="display: inline; float: inherit;">
		 	首页
                <c:if test="${currentPage==1}">
                     
                </c:if>
                <c:if test="${currentPage>1}">
                    	<a href="<%=basePath%>/page.do?currentPage=${currentPage-1}">上一页</a>
                </c:if>
                	第${currentPage}页
                <c:if test="${currentPage==totalPages}">
                     
                </c:if>
                <c:if test="${currentPage<totalPages}">
                	<a href="<%=basePath%>/page.do?currentPage=${currentPage+1}">下一页</a>
                </c:if>
                	尾页
                	到第<input id="page" name = "page"type="text" maxlength="3" style="width: 20px">页<input id="go" type="button" value="go" onclick="jump()">
   </div>
	</div>
	
</body>
</html>