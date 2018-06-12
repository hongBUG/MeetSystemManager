package org.xu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xu.bean.Employee;
import org.xu.bean.Meeting;
import org.xu.service.MeetingService;

/**
 * Servlet implementation class MyBookingServlet
 */
// @WebServlet("/MyBookingServlet")
public class MyBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MeetingService mService = new MeetingService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBookingServlet() {
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
    	int loginEmpId = ((Employee) request.getSession().getAttribute("loginUser")).getEmployeeid();
    	List<Meeting> mrs = mService.getMyMeeting(loginEmpId);
    	request.setAttribute("mrs", mrs);
    	request.getRequestDispatcher("/mybookings.jsp").forward(request, response);
	}

}
