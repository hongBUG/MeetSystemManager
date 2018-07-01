package org.xu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xu.service.MeetingService;

/**
 * Servlet implementation class DoCancelMeetingServlet
 */
@WebServlet("/DoCancelMeetingServlet")
public class DoCancelMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MeetingService meetingService = new MeetingService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    public DoCancelMeetingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mid = request.getParameter("mid");
		String canceledreason = request.getParameter("canceledreason");
		int i = meetingService.cancelMeeting(Integer.parseInt(mid), canceledreason);
		if (i == 1) {
			response.sendRedirect(request.getContextPath() + "/mybooking" );
		}
	}

}
