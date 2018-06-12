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
<jsp:include page="top.jsp"></jsp:include>
    <div class="page-body">
    <jsp:include page="leftMenu.jsp"></jsp:include>
        <div class="page-content" >
            <div class="content-nav">会议预定 > 搜索员工</div>
            <form action="/meeting/searchemp" method="post">
                <fieldset>
                    <legend>会议搜索</legend>
                    <table class="formtable">
                        <tr>
                            <td>姓名：</td>
                            <td>
                                <input type="text" id="employeename" name="employeename" value="${employeename }" maxlength="20"/>
                            </td>
                            <td>账号名：</td>
                            <td>
                                <input type="text" id="accountname" name="accountname" value="${username }" maxlength="20"/>
                            </td>
                            <td>状态：</td>
                            <td>
                                <c:choose>
                                    <c:when test="${status==0 }">
                                        <input type="radio" id="status" name="status" value="1" />
                                        <label>已批准</label>
                                        <input type="radio" checked="checked" id="status" name="status" value="0" />
                                        <label>待审批</label>
                                        <input type="radio" id="status" name="status" value="-1" />
                                        <label>已关闭</label>
                                    </c:when>
                                    <c:when test="${status==1 }">
                                        <input type="radio" checked="checked" id="status" name="status" value="1" />
                                        <label>已批准</label>
                                        <input type="radio" id="status" name="status" value="0" />
                                        <label>待审批</label>
                                        <input type="radio" id="status" name="status" value="-1" />
                                        <label>已关闭</label>
                                    </c:when>
                                    <c:when test="${status==-1 }">
                                        <input type="radio" id="status" name="status" value="1" />
                                        <label>已批准</label>
                                        <input type="radio" id="status" name="status" value="0" />
                                        <label>待审批</label>
                                        <input type="radio" checked="checked" id="status" name="status" value="-1" />
                                        <label>已关闭</label>
                                    </c:when>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="6" clss="command">
                                <input type="submit" class="clickbutton" value="查询" />
                                <input type="reset" class="clickbutton" value="重置" />
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </form>
            <div>
                <h3 style="text-align:center; color:black">查询结果 </h3>
                <div clas="pager-header">
                    <div class="header-info">
                                                        共<span class="info-number">${totalCount }</span>条结果，
                                                        分成<span class="info-number">${totalPage }</span>页显示，
                                                        当前第<span class="info-number">${page }</span>页
                    </div>
                    <div class="header-nav">
                        <a type="button" class="clickbutton" href="/meeting/searchemp?page=1&status=${status }&employeename=${employeename}&username=${username }">首页</a>
                        <c:choose>
                            <c:when test="${page>1 }">
                                <a type="button" class="clickbutton" href="/meeetiing/searchemp?page=${page-1 }&status=${status}&employeename=${employeename}&username=${username}">上一页</a>
                            </c:when>
                            <c:otherwise>
                                                                                 上页
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${page < totalPage }">
                                <a type="button" class="clickbutton" href="/meeetiing/searchemp?page=${page+1 }&status=${status}&employeename=${employeename}&username=${username}">下一页</a>
                            </c:when>
                            <c:otherwise>
                                                                                     下一页
                            </c:otherwise>
                        </c:choose>
                        <a type="button" class="clickbutton" href="/meeting/searchemp?page=${totalPage}&status=${status }&employeename=${employeename }&username=${username }">末页</a>
                        <form action="/meeting/searchemp" method="post" style="display: inline" >
                            <input type="hidden" name="employeename" value="${employee }" />
                            <input type="hidden" name="username" value="${username }" />
                            <input type="hidden" name="status" value="${status }" />
                                                                                 跳到第<input type="text" id="pagenum" name="page" class="nav-number" /> 页
                            <input type="submit" class="clickbutton" value="跳转" />
                        </form>
                    </div>
                </div>
                <table class="=listtable">
        <tr class="listheader">
            <th>姓名</th>
            <th>账号名</th>
            <th>联系电话</th>
            <th>电子邮件</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${list }" var="emp">
            <tr>
                <td>${emp.employeename }</td>
                <td>${emp.username }</td>
                <td>${emp.phone }</td>
                <td>${emp.email }</td>
                <td>
                    <form method="post" action="/meeting/searchemp">
                        <input type="hidden" name="employeename" value="${employeename }" />
                        <input type="hidden" name="username" value="${username }" />
                        <input type="hidden" name="status" value="${status }" />
                        <input type="hidden" name="updateStatus" value="-1" />
                        <input type="hidden" name="empid" value="${emp.employeeid}" />
                        <input class="clickbutton" value="关闭账号" type="submit" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
            </div>
        </div>
    </div>
    
<jsp:include page="footer.jsp" />
</body>
</html>