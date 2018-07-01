<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CoolMeeting会议管理系统</title>
    <link rel="stylesheet" href="styles/common.css" />
    
</head>
<body>
<jsp:include page="top.jsp" />
    <div class="page-body">
        <jsp:include page="leftMenu.jsp" />
        <div class="page-content" >
            <div class="content-nav" >登录</div>
        </div>
        <form method="post" action="/meeting/login">
        	<fieldset>
        		<legend>登录信息</legend>
        		<table class="formtable" style="width:50%">
        			<c:if test="${error != null }">
        				<tr>
        					<td colspan="2">${error }</td>
        				</tr>
        			</c:if>
        			<tr>
        				<td>账号名</td>
        				<td>
        					<input id="accountname" name="accountname" type="text"/>
        				</td>
        			</tr>
        			<tr>
        				<td>密码</td>
        				<td>
        					<input id="accountpassword" name="accountpassword" type="password"/>
        				</td>
        			</tr>
        			<tr>
        				<td colspan="2" class="command">
        					<input type="submit" value="登录" class="clickbutton" />
        					<input type="button" value="返回" class="clickbutton" onclick="window.history.back();"/>
        				</td>
        			</tr>
        		</table>
        	</fieldset>
        </form>
    </div>
<jsp:include page="footer.jsp" />
</body>
</html>