package org.xu.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xu.bean.Employee;
import org.xu.bean.Meeting;
import org.xu.service.MeetingService;

/**
 * Servlet implementation class DoBookMeetingServlet
 */
// @WebServlet("/DoBookMeetingServlet")
public class DoBookMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MeetingService meetingService = new MeetingService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoBookMeetingServlet() {
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
    	String meetingname = request.getParameter("meetingname");
    	String numberofParticipants = request.getParameter("numberofparticipants");
    	String starttime = request.getParameter("starttime");
    	String endtime = request.getParameter("endtime");
    	String roomid = request.getParameter("roomid");
    	String description = request.getParameter("description");
    	String[] selSelectedEmps = request.getParameterValues("selSelectedEmployees");
    	// 获取当前登录的用户对象
    	Employee loginUser = (Employee) request.getSession().getAttribute("loginUser");
    	Meeting meeting = new Meeting(meetingname, Integer.parseInt(roomid), loginUser.getEmployeeid(), Integer.parseInt(numberofParticipants), Timestamp.valueOf(starttime), Timestamp.valueOf(endtime), new Timestamp(System.currentTimeMillis()), description);
    	meetingService.insert(meeting, selSelectedEmps);
    	response.sendRedirect(request.getContextPath() + "/searmeetings.jsp");
	}

}
