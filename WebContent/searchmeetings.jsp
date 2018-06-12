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
            <div class="content-nav" >会议预定 > 搜索会议</div>
            <form action="/meeting/searchmeeting" method="post" >
                <fieldset>
                    <legend>搜索会议</legend>
                    <table class="formtable">
                        <tr>
                           <td>会议名称：</td>
                           <td>
                               <input type="text" id="meetingname" name="meetingname" value="${meetingname }" maxlength="20" />
                           </td>
                           <td>会议室名称：</td>
                           <td>
                               <input type="text" id="roomname" name="roomname" value="${roomname }" maxlength="20" />
                           </td>
                           <td>预订者名称：</td>
                           <td>
                               <input type="text" id="reservename" name="reservername" value="${reservename }" maxlength="20" />
                           </td>
                        </tr>
                        <tr>
                            <td>预定日期：</td>
                            <td colspan>从&nbsp;
                                <input class="Wdate" type="text" id="starttime" name="reservefromdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />到&nbsp;
                                <input class="Wdate" type="text" id="endtime" name="reservetodate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 
                            </td>
                        </tr>
                        <tr>
                            <td>会议日期：</td>
                            <td colspan>从&nbsp;
                                <input class="Wdate" type="text" name="meetingfromdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />到&nbsp;
                                <input class="Wdate" type="text" name="meetingtodate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 
                            </td>
                        </tr>
                        <tr>
                            <td colspan="6" class="command">
                                <input type="submit" class="clickbutton" value="查询" />
                                <input type="reset" class="clickbutton" value="重置" />
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </form>
            <div>
                <h3 style="text-align:center; color:black" >查询结果</h3>
                <div class="pager-header">
                    <div class="header-info">
                        共<span class="info-number">${totalCount }</span>条结果，
                        分成<span class="info-number">${totalPage }</span>页展示
                        当前第<span class="info-number">${page }</span>页
                    </div>
                    <div class="header-nav">
                        <a type="button" class="clickbutton" href="/meeting/searchmeeting?page=1&meetingname=${meetingname }&roomname=${roomname}&reservename=${reservename }&reservefromdate=${reservefromdate}&reservetodate=${reservetodate }&meetingfromdate=${meetingfromdate }&meetingtodate=${meetingtodate}">首页</a>
                        <c:choose>
                            <c:when test="${page>1 }">
                                <a type="button" class="clickbutton" href="/meeting/searchmeeting?page=${page-1 }&meetingname=${meetingname }&roomname=${roomname}&reservename=${reservename }&reservefromdate=${reservefromdate}&reservetodate=${reservetodate }&meetingfromdate=${meetingfromdate }&meetingtodate=${meetingtodate}">上一页</a>
                            </c:when>
                            <c:otherwise>
                                                                                 上页
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${page < totalPage }">
                                <a type="button" class="clickbutton" href="/meeting/searchmeeting?page=${page+1 }&meetingname=${meetingname }&roomname=${roomname}&reservename=${reservename }&reservefromdate=${reservefromdate}&reservetodate=${reservetodate }&meetingfromdate=${meetingfromdate }&meetingtodate=${meetingtodate}">下一页</a>
                            </c:when>
                            <c:otherwise>
                                                                                     下一页
                            </c:otherwise>
                        </c:choose>
                        <a type="button" class="clickbutton" href="/meeting/searchmeeting?page=${totalPage }&meetingname=${meetingname }&roomname=${roomname}&reservename=${reservename }&reservefromdate=${reservefromdate}&reservetodate=${reservetodate }&meetingfromdate=${meetingfromdate }&meetingtodate=${meetingtodate}">末页</a>
                        <form action="/meeting/searchmeeting" style="display:inline" method="post" >
                            <input type="hidden" value="${meetingname }" name="meetingname" maxlength="20" />
                            <input type="hidden" value="${roomname }" name="roomname" maxlength="20" />
                            <input type="hidden" value="${reservename }" name="reservename" maxlength="20" />
                            <input type="hidden" value="${reservefromdate }" name="reservefromdate" maxlength="20" />
                            <input type="hidden" value="${reservetodate}" name="reservetodate" maxlength="20" />
                            <input type="hidden" value="${meetingfromdate }" name="meetingfromdate" maxlength="20" />
                            <input type="hidden" value="${meetingtodate }" name="meetingtodate" maxlength="20" />
                            跳到第<input type="text" id="pagenum" name="page" class="nav-number" />页
                            <input type="submit" class="clickbutton" value="跳转" /> 
                        </form>
                    </div>
                </div>
            </div>
            <table class="listtable">
                <tr class="listheader">
                    <th>会议名称</th>
                    <th>会议室名称</th>
                    <th>会议开始时间</th>
                    <th>会议结束时间</th>
                    <th>会议预定时间</th>
                    <th>会议预定人</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${list }" var="m">
                    <tr>
                        <td></td>
                        <td>${m.meetingname }</td>
                        <td>${m.roomname }</td>
                        <td>${m.starttime }</td>
                        <td>${m.endtime }</td>
                        <td>${m.reservationtime }</td>
                        <td>${m.empname }</td>
                        <td>
                            <a class="clickbutton" href="/meeting/meetingdetail?mid=${m.meetingid }" >查看详情</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
<jsp:include page="footer.jsp" />
</body>
</html>