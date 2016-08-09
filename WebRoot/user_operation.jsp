<%@ page language="java" contentType="text/html; charset=UTF-8"
    isELIgnored="false" pageEncoding="UTF-8"%>
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
	<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.10.2.min.js"></script>
	<link href="<%=basePath%>css/index.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		var i= 1;
			function add() {
				var form = document.getElementById("div_input");
				var childInput = document.createElement("input");
				var childBr = document.createElement("br");
				childInput.type = "file";
				childInput.id=childInput.name = "file"+ i++;
				form.appendChild(childInput);
				form.appendChild(childBr);

				}
	
	</script>
</head>
<body>
	<div><div>欢迎您:<%=request.getSession().getAttribute("uname") %>
		<hr>
	</div>
	<div>图片上传，请保持所传图片格式一致(宽度必须一致否则无法解析！)</div><br />
	<div style="border: 1px;">
<form id="pci_upload" action="<%=basePath %>/upload.do" method="post" enctype="multipart/form-data">
			<div id = "div_input">
			<input type="file" name="upfile" />&nbsp;&nbsp;&nbsp;&nbsp;<a id="ads" href="javascript:;" onclick="add()">添加文件</a><br />
			</div>

			<input type="submit" value="submit"/>
		</form>
	</div>
	
	
		<hr>
		
	<div>
	
	
	</div>
	
</body>
</html>