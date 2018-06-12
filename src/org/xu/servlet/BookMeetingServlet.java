package org.xu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xu.bean.Meeting;
import org.xu.bean.MeetingRoom;
import org.xu.service.MeetingRoomService;

/**
 * Servlet implementation class BookMeetingServlet
 */
// @WebServlet("/BookMeetingServlet")
public class BookMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MeetingRoomService mrService = new MeetingRoomService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookMeetingServlet() {
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
    	List<MeetingRoom> list = mrService.getAllMeetingRoom();
    	request.setAttribute("mrs", list);
    	request.getRequestDispatcher("/bookmeeting.jsp").forward(request, response);
	}

}
