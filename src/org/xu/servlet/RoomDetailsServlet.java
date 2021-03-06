package org.xu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xu.bean.MeetingRoom;
import org.xu.service.MeetingRoomService;

/**
 * Servlet implementation class RoomDetailsServlet
 */
// @WebServlet("/RoomDetailsServlet")
public class RoomDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MeetingRoomService mrService = new MeetingRoomService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomDetailsServlet() {
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
    	String roomid = request.getParameter("roomid");
    	MeetingRoom mr = mrService.getMeetingRoomById(Integer.parseInt(roomid));
    	request.setAttribute("mr", mr);
    	request.getRequestDispatcher("/roomdetails.jsp").forward(request, response);
	}

}
