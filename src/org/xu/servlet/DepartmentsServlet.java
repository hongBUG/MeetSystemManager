package org.xu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xu.bean.Department;
import org.xu.service.DepartmentService;

// @WebServlet("/DepartmentsServlet")
public class DepartmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DepartmentService depService = new DepartmentService();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Department> list = depService.getAllDepartments();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/departments.jsp").forward(request, response);
	}

}
