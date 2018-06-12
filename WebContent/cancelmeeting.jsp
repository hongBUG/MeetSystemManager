<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CoolMeeting会议管理系统</title>
    <link rel="stylesheet" href="styles/common.css" />
    <style type="text/css">
    	#divfrom {
    		float: left;
    		width: 200px;
    	}
    	#divto {
    		float: left;
    		width: 200px;
    	}
    	#divoperator {
    		float: left;
    		width: 50px;
    		padding: 60px 5px;
    	}
    	#divoperator input[type="button"] {
    		margin: 10px 0;
    	}
    	#selDepartments {
    		display: block;
    		width: 100%;
    	}
    	#selEmployees {
    		display: block;
    		width: 100%;
    		height: 200px;
    	}
    	#selSelectedEmployees {
    		display: block;
    		width: 100%;
    		height: 225px;
    	}
    </style>
</head>
<body>
<jsp:include page="top.jsp" />
    <div class="page-body">
        <jsp:include page="leftMenu.jsp" />
        <div class="page-content" >
            <div class="content-nav" >会议预定 > 撤销会议预定</div>
            <form action="/meeting/docanceled" method="post">
            	<fieldset>
            		<legend>撤销预定</legend>
            		<table class="formtable">
            			<tr>
            				<input type="hidden" name="mid" value="${m.meetingid }" />
            				<td>会议名称</td>
            				<td>${m.meetingname }</td>
            			</tr>
            			<tr>
            				<td>撤销理由</td>
            				<td><textarea id="description" rows="5" name="canceledreason"></textarea></td>
            			</tr>
            			<tr>
            				<td class="command" colspan="2">
            					<input type="submit" class="clickbutton" value="确认撤销" />
            					<input type="button" class="clickbutton" value="返回" onclick="window.history.back();" />
            				</td>
            			</tr>
            		</table>
            	</fieldset>
            </form>
        </div>
    </div>
<jsp:include page="footer.jsp" />
<script type="application/javascript">
function employee(employeeid, employeename) {
	this.employeeid = employeeid;
	this.employeename = employeename;
}

function department(departmentid, departmentname, employees) {
	this.departmentid = departmentid;
	this.departmentname = departmentname;
	this.employees = employeees;
}

var data = new Array(
	new department(1, "技术部", new Array( 
		new employee(1001, "a00"), new employee(1002, "a01"), new employee(1003, "a02"), new employee(1004, "a03"))),
    new department(2, "销售部", new Array( 
        new employee(2001, "b00"), new employee(2002, "b01"), new employee(2003, "b02"), new employee(1004, "b03"))),
    new department(3, "市场部", new Array( 
        new employee(3001, "c00"), new employee(3002, "c01"), new employee(3003, "c02"), new employee(1004, "c03"))),
    new department(4, "行政部", new Array( 
        new employee(4001, "d00"), new employee(4002, "d01"), new employee(4003, "d02"), new employee(1004, "d03")))
);

var selDepartments;
var selEmployees;
var selSelectedEmployees;

function body_load() {
	selDepartments = document.getElementById("selDepartments");
	selEmployees = document.getElementById("selEmployees");
	setSelectedEmployees = document.getElementById("selSelectedEmployees");
	
	for (var i = 0; i < data.length; i++ ) {
		var dep = document.createElement("option");
		dep.value = data[i].departmentid;
		dep.text = data[i].departmentname;
		selDepartments.appendChild(dep);
	}
	fillEmployees();
}

function fillEmployees() {
	clearList(selEmployees);
	var departmentid = selDepartments.options[selDepartments.selectedIndex].value;
	var employees;
	for (var i = 0; i < data.length; i++) {
		if (departmentid == data[i].departmentid) {
			employees = data[i].employees;
			break;
		}
	}
	for (var i = 0; i < employees.length; i++) {
		var emp = document.createElement("option");
		emp.value = employees[i].employeeid;
		emp.text = employees[i].employeename;
		selEmployees.appendChild(emp);
	}
}

function clearList(list) {
	while (list.childElementCount > 0)
		list.removeChild(list.lastChild);
}

function selectEmployees() {
	for (var i = 0; i < selEmployees.options.length; i++) {
		if (selEmployees.option[i].selected) {
			addEmployee(selEmployees.options[i]);
			selEmployees.options[i].selected = false;
		}
	}
}

function deSelectEmployees() {
	var elementsToRemoved = new Array();
	var options = selSelectedEmployees.options;
	for (var i = 0; i < options.length; i++) {
		if (options[i].selected)
			elementsToRemoved.push(options[i]);
	}
	for (var i = 0; i < elementsToRemoved.length; i++) {
		selSelectedEmployees.removeChild(elementsToRemoved[i]);
	}
}

function addEmployee(optEmployee) {
	var options = selSelectedEmployees.options;
	var i = 0;
	var insertIndex = -1;
	while (i < options.length) {
		if (optEmployee.value == options[i].value) {
			return;
		} else if (optEmployee.value < options[i].value) {
			insertIndex = i;
			break;
		}
		i++;
	}
	var opt = document.createElement("option");
	opt.value = optEmployee.value;
	opt.text = optEmployee.text;
	
	if (insertIndex == -1)
		selSelectedEmployees.appendChild(opt);
	else 
		selSelectedEmployees.insertBefore(opt, options[insertIndex]);
}
</script>
</body>
</html>