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
            <div class="content-nav" >个人中心 > <a href="notifications">最新通知</a>
                <div style="display: inline; text-align:right">您是第${vc }位访客</div>
            </div>
            <table class="listtable">
            	<caption>未来7天我要参加的会议：</caption>
            	<tr class="listheader">
            	    <th style="width:300px">会议名称</th>
            	    <th>会议室</th>
            	    <th>起始时间</th>
            	    <th>结束时间</th>
            	    <th style="width: 100px">操作</th>
            	</tr>
            	<c:forEach items="${mt7 }" var="mt">
            	    <tr>
            	        <td>${mt.meetingname }</td>
            	        <td>${mt.roomname }</td>
            	        <td><fmt:formatDate value="${mt.starttime }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
            	        <td><fmt:formatDate value="${mt.endtime }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
            	        <td>
            	        	<a class="clickbutton" href="/meeting/meetingdetail?mid=${mt.meetingid }">会议详情</a>
            	        </td>
            	    </tr>
            	</c:forEach>
            </table>
            <table class="listtable">
            	<caption>已取消的会议：</caption>
            	<tr class="listheader">
            	    <th style="width:300px">会议名称</th>
            	    <th>会议室</th>
            	    <th>起始时间</th>
            	    <th>结束时间</th>
            	    <th>取消原因</th>
            	    <th style="width: 100px">操作</th>
            	</tr>
            	<c:forEach items="${cm }" var="mt">
            	    <tr>
            	        <td>${mt.meetingname }</td>
            	        <td>${mt.roomname }</td>
            	        <td><fmt:formatDate value="${mt.starttime }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
            	        <td><fmt:formatDate value="${mt.endtime }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
            	        <td>${mt.canceledreason }</td>
            	        <td>
            	        	<a class="clickbutton" href="/meeting/meetingdetail?mid=${mt.meetingid }">会议详情</a>
            	        </td>
            	    </tr>
            	</c:forEach>
            </table>
        </div>
    </div>
<jsp:include page="footer.jsp" />
</body>
</html>