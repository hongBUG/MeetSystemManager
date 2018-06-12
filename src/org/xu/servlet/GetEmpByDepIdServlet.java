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

import com.google.gson.Gson;

/**
 * Servlet implementation class GetEmpByDepIdServlet
 */
// @WebServlet("/GetEmpByDepIdServlet")
public class GetEmpByDepIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EmployeeService empService = new EmployeeService(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmpByDepIdServlet() {
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
    	String depid = request.getParameter("depid");
    	List<Employee> list = empService.getEmpByDepId(Integer.parseInt(depid));
    	response.setContentType("application/json;charset=utf-8");
    	response.getWriter().write(new Gson().toJson(list));
	}

}
