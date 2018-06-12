<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CoolMeeting会议管理系统</title>
    <link rel="stylesheet" href="style/common.css" />
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
    <div class="page-content">
        <div class="content-nav">会议预定 > 查看会议室</div>
        <table class="listtable">
            <caption>所有会议室：</caption>
            <tr class="listheader">
                <th>门牌编号</th>
                <th>会议室名称</th>
                <th>容纳人数</th>
                <th>当前状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${list }" var="mr">
                <tr>
                    <td>${mr.roomnum }</td>
                    <td>${mr.roomname }</td>
                    <td>${mr.capacity }</td>
                    <td>
                        <c:choose>
                            <c:when test="${mr.status==0 }">启用</c:when>
                            <c:when test="${mr.status==1 }">停用</c:when>
                        </c:choose>
                    </td>
                    <td>
                        <a class="clickbutton" href="/meeting/roomdetails?roomid=${mr.roomid }">查看详情</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>