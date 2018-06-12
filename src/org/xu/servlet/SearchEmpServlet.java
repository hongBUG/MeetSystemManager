package org.xu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xu.bean.Employee;
import org.xu.service.EmployeeService;

/**
 * Servlet implementation class SearchEmpServlet
 */
// @WebServlet("/SearchEmpServlet")
public class SearchEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EmployeeService empService = new EmployeeService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String updateStatus = request.getParameter("updateStatus");
		if (updateStatus != null && updateStatus.equals("-1")){
			String empid = request.getParameter("empid");
			empService.updateEmpStatusById(Integer.parseInt(empid), Integer.parseInt(updateStatus));
		}
		String empname = request.getParameter("employeename");
		String username = request.getParameter("username");
		String status = request.getParameter("status");
		String page = request.getParameter("page");
		String count = request.getParameter("count");
		if (status == null && "".equals(status))
			status = "1";
		if (page == null && "".equals(page))
			page = "1";
		if (count == null && "".equals(count))
			count = "10";
		List<Employee> list = empService.searchEmp(empname, username, Integer.parseInt(status), Integer.parseInt(page),Integer.parseInt(count));
		int totalCount = empService.getCount(empname, username, Integer.parseInt(status));
		int totalPage = totalCount / Integer.parseInt(count) + 1;
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("page", page);
		request.setAttribute("employeenaem", empname);
		request.setAttribute("usernaem", username);
		request.setAttribute("status", status);
		request.getRequestDispatcher("/searchemployees.jsp").forward(request, response);
	}

}
