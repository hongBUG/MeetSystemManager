package org.xu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xu.service.DepartmentService;

/**
 * Servlet implementation class UpdateDepServlet
 */
// @WebServlet("/UpdateDepServlet")
public class UpdateDepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DepartmentService depService = new DepartmentService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDepServlet() {
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
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		String depName = request.getParameter("depName");
		int i = depService.updateDepById(depName, Integer.parseInt(id));
		PrintWriter out = response.getWriter();
		if (i == 1) {
			out.write("修改成功");
		} else {
			out.write("修改失败");
		}
	}

}
