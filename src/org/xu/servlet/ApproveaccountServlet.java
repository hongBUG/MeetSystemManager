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
 * Servlet implementation class ApproveaxccountServlet
 */
// @WebServlet("/ApproveaxccountServlet")
public class ApproveaccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EmployeeService empService = new EmployeeService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveaccountServlet() {
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
		List<Employee> list = empService.getUnApproveaccount();
		request.setAttribute("list", list);
		request.getRequestDispatcher("approveaccount.jsp").forward(request, response);
	}

}
