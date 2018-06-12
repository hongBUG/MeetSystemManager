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

import com.google.gson.Gson;

/**
 * Servlet implementation class GetAllDepJsonServlet
 */
// @WebServlet("/GetAllDepJsonServlet")
public class GetAllDepJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DepartmentService depService = new DepartmentService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllDepJsonServlet() {
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
		// TODO Auto-generated method stub
    	List<Department> list = depService.getAllDepartments();
    	response.setContentType("application/json;charset=utf-8");
    	response.getWriter().write(new Gson().toJson(list));
	}

}
