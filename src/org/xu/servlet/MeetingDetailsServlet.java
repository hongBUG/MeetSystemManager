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
 * Servlet implementation class MeetingDetailsServlet
 */
// @WebServlet("/MeetingDetailsServlet")
public class MeetingDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MeetingService mService = new MeetingService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MeetingDetailsServlet() {
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
		String mid = request.getParameter("mid");
		String type = request.getParameter("type");
		Meeting meeting = mService.getMeetingDetailsByMeetingId(Integer.parseInt(mid));
		List<Employee> emps = mService.getEmplist();
		request.setAttribute("meeting", meeting);
		request.setAttribute("emps", emps);
		request.setAttribute("type", type);
		request.getRequestDispatcher("/meetingdetails.jsp").forward(request, response);
	}

}
