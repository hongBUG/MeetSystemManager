package org.xu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xu.service.DepartmentService;

/**
 * Servlet implementation class DeleteDepServlet
 */
 // @WebServlet("/DeleteDepServlet")
public class DeleteDepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DepartmentService depService = new DepartmentService();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDepServlet() {
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
		String depid = request.getParameter("depid");
		int i = depService.deleteDepById(Integer.parseInt(depid));
		if(i == 1) {
			response.sendRedirect(request.getContextPath() + "/departments");
		} else {
			request.setAttribute("error", "删除失败");
			request.getRequestDispatcher("/departments").forward(request, response);
		}
	}

}
