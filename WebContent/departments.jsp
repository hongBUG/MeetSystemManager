<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CoolMeeting会议管理系统</title>
    <link rel="stylesheet" href="style/common.css" />
    <script src="js/jquery-3.2.1.js" ></script>
</head>
<body>
<jsp:include page="top.jsp" />
    <div class="patge-body">
    <jsp:include page="leftMenu.jsp" />
        <div class="content-nav" >人员管理 > 部门管理</div>
        <form method="post" action="/meeting/addep">
            <fieldset>
                <legend>添加部门</legend>
                ${error }
                <span>部门名称:</span>
                <input type="text" id="departmentname" name="departmentname" maxlength="20" />
                <input type="submit" class="clickbutton" value="添加" />
                
            </fieldset>
        </form>
        <table class="listtable" >
            <caption>所有部门：</caption>
            <tr class="listheader">
                <th>部门编号</th>
                <th>部门名称 </th>
                <th>操作 </th>
            </tr>
            <c:if test="${error != null }" >
                <tr>
                    <td colspan="3">${error } </td>
                </tr>
            </c:if>
            <c:forEach items="${list }" var="dep">
                <tr>
                    <td>${dep.departmentid } </td>
                    <td id="depname${dep.departmentid }">${dep.departmentname } </td>
                    <td>
                        <a class="clickbutton" href="#" id="edit${dep.departmentid }"
                           onclick="editDep(${dep.departmentid})">编辑</a>
                        <a class="clickbutton" href="#" style="display: none" id="cancel${dep.departmentid }"
                           onclick="cancelEdit(${dep.departmentid }, '${dep.departmentname }')">取消</a>
                        <a class="clickbutton" href="/meeting/deletedep?depid=${dep.departmentid }">删除</a>  
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
<jsp:include page="footer.jsp" />
<script>
    function cancelEdit(depid, depName) {
    	var cancelBtn = ${"#cancel" + depid};
    	var editBtn = ${"#edit" + depid};
    	var ele = ${"#depname" + depid};
    	cancelBtn.css("display", "none");
    	editBtn.html("编辑");
    	ele.html(depName);
    }
    
    function editDep(depid) {
    	var cancelBtn = ${"#cancel" + depid};
    	var editBtn = ${"#edit" + depid};
    	var ele = ${"#depname" + depid};
    	if(cancelBtn.css("display") == "none") {
    		cancelBtn.css("display", "inline");
    		editBtn.html("确定");
    		var depName = ele.text();
    		ele.html("<input type='text' value='" + depName + "'/>");
    	} else {
    		var children = ele.children("input");
    		var val = children.val();
    		$.post("/meeting/updatedep", {id: depid, depName: val}, function(msg) {
    			alter(msg);
    			cancelBtn.css("display", "nonoe");
    			editBtn.html("编辑");
    			ele.html(val);
    		});
    	}
    }
</script>
</body>
</html>