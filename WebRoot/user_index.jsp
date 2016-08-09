<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Login for user</title>
	<meta http-equiv="Expires" content="0">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-control" content="no-cache">
	<meta http-equiv="Cache" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.10.2.min.js"></script>
	<link href="<%=basePath%>css/index.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>js/artDialog/artDialog.js?skin=aero"></script>
    <script type="text/javascript" src="<%=basePath%>js/artDialog/jquery.artDialog.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/sys_dialog.js"></script>
</head>
<body>
	<table border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" class="log_c">
        <tbody>
            <tr>
                <td height="44px">
                    <div class="log_top">
                        <i class="lo_bg"></i><span>欢迎使用本管理系统</span>
                        <table style="float: right">
                            <tr>
                                <td>
                                    <a href="Sys_index.jsp" target="_blank">后台管理</a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </td>
                <td>
                </td>
            </tr>
            <tr>
                <td height="100%">
                    <div class="log_cent"><form action="<%=basePath%>/login.do" method="post">
                        <div class="log_centent">
                            <div>
                                <input name="userName" type="text" id="txtUserName" class="lo_text" autocomplete="off" maxlength="16" />
                            </div>
                            <div>
                                <input name="pwd" type="password" id="txtPwd" class="lo_text" maxlength="16" autocomplete="off" />
                            </div>
                            <div>
                                <input name="code" type="text" id="txtCode" style="width: 126px;" class="lo_text" maxlength="4" autocomplete="off" onkeydown="enterLogin(event);"/>
                                <img id="codeImage" border="0" src="<%=basePath%>/create.do" style="margin-left: 5px; height: 45px;
                                    width: 80px; float: left; cursor: pointer;" onclick="javascript:this.src='<%=basePath%>/create.do?'+new Date()" />
                                <input type="submit" name="btnLogin" value="登  录" id="btnLogin" class="lo_sub lo_bg" style="margin-left: 20px; margin-top: 5px" />
                            </div>
                        </div>
                    </div></form>
                </td>
            </tr>
            <tr>
                <td height="50px">
                    <div class="log_btn">
                        <span>Copyright © 2015 <strong>FMCQ</strong> Systems Incorporated. All rights reserved.
                        </span>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
</body>
</html>