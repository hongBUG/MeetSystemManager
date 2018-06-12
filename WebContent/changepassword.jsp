<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <jsp:include page="leftMenu.jsp" />
        <div class="page-content">
            <div class="content-nav">修改密码</div>
            <form>
                <fieldset>
                    <legent>修改密码信息</legent>
                    <table class="formtable" style="width:50%">
                        <tr>
                            <td>原密码：</td>
                            <td><input id="origin" type="password" /></td>
                        </tr>
                        <tr>
                            <td>新密码：</td>
                            <td><input id="new" type="password" /></td>
                        </tr>
                        <tr>
                            <td>确认新密码：</td>
                            <td><input id="confirm" type="password" /></td>
                        </tr>
                        <tr>
                            <td colspan="2" class="command">
                                <input type="submit" value="确认修改" class="clickbutton" />
                                <input type="button" value="返回" class="clickbutton" onclick="window.history.back();"/>
                                
                                
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div>
    </div>
    
    <div class="page-footer">
        <hr/>
        更多问题，欢迎联系<a href="mailto:745146218@qq.com">管理员</a>
        <img src="images/footer.png" alt="CoolMeeting" />
    </div>
</body>
</html>