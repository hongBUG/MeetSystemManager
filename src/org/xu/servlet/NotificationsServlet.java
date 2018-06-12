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
 * Servlet implementation class NotificationsServlet
 */
// @WebServlet("/NotificationsServlet")
public class NotificationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MeetingService mService = new MeetingService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificationsServlet() {
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
    	List<Meeting> mt7 = mService.getMeeting7Days(loginEmpId);   // 近7天会议
    	List<Meeting> cm = mService.getCanceledMeeting(loginEmpId);  // 已取消会议
    	request.setAttribute("mt7", mt7);
    	request.setAttribute("cm", cm);
    	request.getRequestDispatcher("/notifications.jsp").forward(request, response);
	}

}
