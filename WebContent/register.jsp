<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CoolMeeting会议管理系统</title>
<link rel="stylesheet" href="styles/common.css" />
<style type="text/css"></style>
</head>
<body >
<jsp:include page="top.jsp"></jsp:include>
<div class="page-body">
    <jsp:include page="leftMenu.jsp" />
    <div class="page-content">
    <div class="content-nav">人员管理 > 员工注册</div>
    <form method="post" action="/meeting/doreg">
        <fieldset>
            <legend>员工信息</legend>
            <table class="formtable" style="width:50%">
                <c:if test="${error!=null }">
                    <tr> 
                        <td colspan="2">${error }</td>
                    </tr>
                </c:if>
                <tr>
                    <td>姓名：</td>
                    <td><input type="text" id="employeename" name="employeename" maxlength="20" /></td>
                </tr> 
                <tr>
                    <td>账户名：</td>
                    <td><input type="text" id="accountname" name="accountname" maxlength="20" /></td>
                </tr>
                <tr>
                    <td>密码：</td>
                    <td><input type="password" id="password" name="password" maxlength="20" placeholder="请输入6位数以上的密码"/></td>
                </tr>
                <tr>
                    <td>确认密码：</td>
                    <td><input type="password" id="confirm" maxlength="20" /></td>
                </tr>
                <tr>
                    <td>联系电话：</td>
                    <td><input type="text" id="phone" name="phone" maxlength="20" /></td>
                </tr>
                <tr>
                    <td>电子邮件</td>
                    <td><input type="text" id="email" name="email" maxlength="20" /></td>
                </tr>
                <tr>
                    <td> 所在部门</td>
                    <td>
                        <select name="deptid">
                            <c:forEach items="${list }" var="dep">
                                <option value="${dep.departmentid }">${dep.departmentname } </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" clss="command">
                        <input type="submit" class="clickbutton" value="注册" />
                        <input type="reset" class="clickbutton" valut="重置" />
                </tr>
            </table>
        </fieldset>
    </form>
    </div></div>
    <div class="page-footer">
         <hr/>
         更多问题欢迎联系<a href="mailto: 745146218@qq.com">管理员</a>
         <img src="images/footer.png" alt="CoolMeeting" />
    </div>
</body>
</html>