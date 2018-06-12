package org.xu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xu.bean.Employee;
import org.xu.service.EmployeeService;

/**
 * Servlet implementation class DoRegServlet
 */
//@WebServlet("/DoRegServlet")
public class DoRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EmployeeService employeeService = new EmployeeService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRegServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String employeename = request.getParameter("employeename");
	    String accountname = request.getParameter("accountname");
	    String password = request.getParameter("password");
	    String phone = request.getParameter("phone");
	    String email = request.getParameter("email");
	    String deptid = request.getParameter("deptid");
	    Employee employee = new Employee(employeename, accountname, phone, email, Integer.parseInt(deptid), password);
	    int reg = employeeService.reg(employee);
	    if (reg == 1) {
	    	response.sendRedirect(request.getContextPath() + "/login.jsp");
	    } else if( reg == -1) {
	    	// 用户名重复
	    	request.setAttribute("error", "用户名已存在");
	    	request.getRequestDispatcher("/reg").forward(request, response);
	    } else {
	    	request.setAttribute("error", "注册失败");
	    	request.getRequestDispatcher("/reg").forward(request, response);
	    }
	}

}
