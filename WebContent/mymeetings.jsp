<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <div class="content-nav" >个人中心  > 我的会议</div>
        </div>
        <table class="listtable">
        	<caption>我将参加的会议</caption>
        	<tr class="listheader">
        		<th>会议名称</th>
        		<th>会议室名称</th>
        		<th>会议开始时间</th>
        		<th>会议结束时间</th>
        		<th>会议预定时间</th>
        		<th>预订者</th>
        		<th>操作</th>
        	</tr>
        	<c:forEach items="${mrs }" var="mr" >
        		<tr>
        			<td>${mr.meetingname }</td>
        			<td>${mr.roomname }</td>
        			<td><fmt:formatDate value="${mr.starttime }" pattern="yyyy-MMM-dd HH:mm:ss" /></td>
        			<td><fmt:formatDate value="${mr.endtime }" patter="yyyy-MM-dd HH:mm:ss" /></td>
        			<td><fmt:formatDate value="${mr.reservationtime }" patter="yyyy-MM-dd HH:mm:ss" /></td>
        			<td>${mr.empname }</td>
        			<td>
        				<a class="clickbutton" href="/meeting/meetingdetail?mi=${mr.meetingid }">查看详情</a>
        			</td>
        		</tr>
        	</c:forEach>
        </table>
    </div>
<jsp:include page="footer.jsp" />
</body>
</html>