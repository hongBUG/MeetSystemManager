package org.xu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xu.service.DepartmentService;

public class AddDepServlet extends HttpServlet {
    private DepartmentService depService = new DepartmentService();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String departmentname = req.getParameter("departmentname");
    	int insert = depService.insert(departmentname);
    	if (insert == 1) {
    		resp.sendRedirect(req.getContextPath() + "/departments");
    	} else {
    		req.setAttribute("error", "添加失败");
		req.getRequestDispatcher("/departments").forward(req, resp);
    	}
    }
}
