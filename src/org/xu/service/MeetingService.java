package org.xu.service;

import java.util.List;

import org.xu.bean.Employee;
import org.xu.bean.Meeting;
import org.xu.dao.EmployeeDao;
import org.xu.dao.MeetingDao;
import org.xu.dao.MeetingParticipantsDao;

public class MeetingService {
    private MeetingDao mDao = new MeetingDao();
    private EmployeeDao employeeDao = new EmployeeDao();
    private List<Employee> emplist = null;
    private MeetingParticipantsDao meetingParticipantsDao = new MeetingParticipantsDao();
    
    public List<Employee> getEmplist() {
    	return emplist;
    }
    
    public void insert(Meeting meeting, String[] empids) {
    	int insert = mDao.insert(meeting);
    	meetingParticipantsDao.insert(insert, empids);
    }
    
    public List<Meeting> searchMeeting(String meetingname, String roomname, String reservername, String reservefromdate, String reservetodate, String meetingfromdate, String meetingtodate, int page, int count) {
    	return mDao.searchMeeting(meetingname, roomname, reservername, reservefromdate, reservetodate, meetingfromdate, meetingtodate, page, count);
    }
    
    public int getCount(String meetingname, String roomname, String reservername, String reservefromdate, String reservetodate, String meetingfromdate, String meetingtodate) {
    	return mDao.getCount(meetingname, roomname, reservername, reservefromdate, reservetodate, meetingfromdate, meetingtodate);
    }
    
    public Meeting getMeetingDetailsByMeetingId(int mid) {
    	Meeting meeting = mDao.getMeetingById(mid);
    	this.emplist = employeeDao.getEmpByMeetingId(meeting.getMeetingid());
    	return meeting;
    }
    
    public List<Meeting> getCanceledMeeting(int empid) {
    	return mDao.getCanceledMeeting(empid);
    }
    
    public List<Meeting> getMeeting7Days(int empid) {
    	return mDao.getMeeting7Days(empid);
    }
    
    public List<Meeting> getBookingMeeting(int empid) {
    	return mDao.getMyBookingMeeting(empid);
    }
    
    public List<Meeting> getMyMeeting(int empid) {
    	return mDao.getMyMeeting(empid);
    }
    
    public int cancelMeeting(int mid, String reason) {
    	return mDao.cancelMeeting(mid, reason);
    }
}
