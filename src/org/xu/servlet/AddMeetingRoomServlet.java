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
 * Servlet implementation class AddMeetingRoomServlet
 */
// @WebServlet("/AddMeetingRoomServlet")
public class AddMeetingRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MeetingRoomService mrService = new MeetingRoomService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMeetingRoomServlet() {
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
    	String roomnum = request.getParameter("roomnum");
    	String roomname = request.getParameter("roomname");
    	String capacity = request.getParameter("capacity");
    	String status = request.getParameter("status");
    	String description = request.getParameter("description");
    	MeetingRoom mr = new MeetingRoom(Integer.parseInt(roomnum), roomname, Integer.parseInt(capacity), Integer.parseInt(status), description);
    	if (roomid == null || roomid.equals("")) {
    		// 添加会议室
    		int result = mrService.insert(mr);
    		if (result == 1) {
    			// 添加成功，查看会议室页面
    			response.sendRedirect(request.getContextPath() + "/getallmr");
    		} else {
    			request.setAttribute("error", "添加失败");
    			request.getRequestDispatcher("/addmeetingroom.jsp").forward(request, response);
    		}
    	} else {
    		// 修改会议室
    		mr.setRoomid(Integer.parseInt(roomid));
    		int update = mrService.update(mr);
    		if (update == 1) {
    			response.sendRedirect(request.getContextPath() + "/getallmr"); 
    		} else {
    			request.setAttribute("error", "更新失败");
    			request.getRequestDispatcher("/addmeetingroom.jsp").forward(request, response);
    		}
    	}
	}

}
