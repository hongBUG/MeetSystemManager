package org.xu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xu.bean.Meeting;
import org.xu.service.MeetingService;

/**
 * Servlet implementation class SearchMeetingServlet
 */
// @WebServlet("/SearchMeetingServlet")
public class SearchMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MeetingService mService = new MeetingService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMeetingServlet() {
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
    	String roomname = request.getParameter("roomname");
    	String reservername= request.getParameter("reservername");
    	String reservefromdate = request.getParameter("reservefromdate");
    	String reservetodate = request.getParameter("reservetodate");
    	String meetingfromdate = request.getParameter("meetingfromdate");
    	String meetingtodate= request.getParameter("meetingtodate");
    	String page = request.getParameter("page");
    	String count = request.getParameter("count");
    	if (page == null || page.equals(""))
    		page = "1";
    	if (count == null || count.equals(""))
    		count = "10";
    	List<Meeting> list = mService.searchMeeting(meetingname, roomname, reservername, reservefromdate, reservetodate, meetingfromdate, meetingtodate, Integer.parseInt(page), Integer.parseInt(count));
    	int totalCount = mService.getCount(meetingname, roomname, reservername, reservefromdate, reservetodate, meetingfromdate, meetingtodate);
    	int totalPage = totalCount / Integer.parseInt("count") + 1;
    	request.setAttribute("list", list);
    	request.setAttribute("totalCount", totalCount);
    	request.setAttribute("totalPage", totalPage);
    	request.setAttribute("page", page);
    	request.setAttribute("meetingname", meetingname);
    	request.setAttribute("roomname", roomname);
    	request.setAttribute("reservername", reservername);
    	request.setAttribute("meetingfromdate", meetingfromdate);
    	request.setAttribute("meetingtodate", meetingtodate);
    	request.setAttribute("reservefromdate", reservefromdate);
    	request.setAttribute("reservetodate", reservetodate);
    	request.setAttribute("count", count);
    	request.getRequestDispatcher("/searchmeetings.jsp").forward(request, response);
	}

}
