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
 * Servlet implementation class MyMeetingServlet
 */
@WebServlet("/MyMeetingServlet")
public class MyMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MeetingService meetingService = new MeetingService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyMeetingServlet() {
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
    	List<Meeting> list = meetingService.getMyMeeting(loginEmpId);
    	request.setAttribute("mrs", list);
    	request.getRequestDispatcher("/mymeetings.jsp").forward(request, response);
	}

}
