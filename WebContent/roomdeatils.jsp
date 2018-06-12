<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CoolMeeting会议管理系统</title>
    <link href="styles/common.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
    <div class="page-body" >
    <jsp:include page="leftMenu.jsp"></jsp:include>
        <div class="page-content">
            <div class="content-nav">会议预定 > 修改会议信息</div>
            <form actio="/meeting/addmr" method="post">
                <fieldset>
                    <legend>会议室信息</legend>
                    <table class="formtable">
                        <tr>
                            <td>门牌号：</td>
                            <td>
                                <input name="roomid" value="${mr.roomid }" type="hidden" />
                                <input id="roomnumber" name="roomnum" type="text" value="${mr.roomnum }" maxlength="20"/>
                            </td>
                        </tr>
                        <tr>
                            <td>会议室名称：</td>
                            <td>
                                <input id="capacity" name="roomname" type="text" value="${mr.roomname }" maxlength="20"/>
                            </td>
                        </tr>
                        <tr>
                            <td>最多容纳人数：</td>
                            <td>
                                <input id="roomcapacity" name="capacity" type="text" value="${mr.capcity }" maxlength="20"/>
                            </td>
                        </tr>
                        <tr>
                            <td>当前状态：</td>
                            <td>
                                <c:choose>
                                    <c:when test="${mr.status==0 }">
                                        <input checked="checked" id="status" name="status" type="radio" value="0" maxlength="20"/><label for="status">启用</label>
                                        <input id="status" name="status" type="radio" value="1" maxlength="20"/><label for="status" value="0">停用</label>
                                    </c:when>
                                    <c:otherwise>
                                        <input id="status" name="status" type="radio" value="0" maxlength="20"/><label for="status" status="1">启用</label>
                                        <input checked="checked" id="status" name="status" type="radio" value="1" maxlength="20"/><label for="status" >停用</label>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td>备注：</td>
                            <td>
                                <textarea id="description" name="description" maxlength="200" cols="60" >${mr.description } </textarea>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="command">
                                <input type="submit" value="确认修改" class="clickbutton" />
                                <input type="button" class="clickbutton" value="返回" onclick="window.history.back();" />
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div>
    </div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>