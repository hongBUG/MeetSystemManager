package org.xu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.xu.bean.Meeting;

public class MeetingDao {
    public int getCount(String meetingname, String roomname, String reservername, String reservefromdate, String reservetodate, String meetingfromdate, String meetingtodate) {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	StringBuffer sb = new StringBuffer("SELECT COUNT(*) FROM meeting m, employee e, meetingroom mr WHERE m.roomid=mr.roomid AND m.reservationistid=e.employeeid");
    	if (meetingname != null && !meetingname.equals(""))
    		sb.append(" and meetingname=?");
    	if (roomname != null && !roomname.equals(""))
    		sb.append(" and roomname=?");
    	if (reservername != null && !reservername.equals(""))
    		sb.append(" and reservername=?" );
    	if (reservefromdate != null && !reservefromdate.equals("") && reservetodate != null && !reservetodate.equals(""))
    		sb.append(" and reservationtime>? && reservationtime<?");
    	if (meetingfromdate != null & meetingfromdate.equals("") && meetingtodate != null && meetingtodate.equals(""))
    		sb.append(" and starttime>? and endtime<?");
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sb.toString());
    		int index = 1;
        	if (meetingname != null && !meetingname.equals(""))
        		ps.setString(index++, meetingname);
        	if (roomname != null && !roomname.equals(""))
        		ps.setString(index++, roomname);
        	if (reservername != null && !reservername.equals(""))
        		ps.setString(index++, reservername);
        	if (reservefromdate != null && !reservefromdate.equals("") && reservetodate != null && !reservetodate.equals("")){
        		ps.setTimestamp(index++, Timestamp.valueOf(reservefromdate));
        		ps.setTimestamp(index++, Timestamp.valueOf(reservetodate));
        	}
        	if (meetingfromdate != null && !meetingfromdate.equals("") && meetingtodate != null && !meetingtodate.equals("")){
        		ps.setTimestamp(index++, Timestamp.valueOf(meetingfromdate));
        		ps.setTimestamp(index++, Timestamp.valueOf(meetingtodate));
        	}
        	rs = ps.executeQuery();
        	if(rs.next()) {
        		return rs.getInt(1);
        	}
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(rs);
    		DBUtils.close(ps);
    		DBUtils.close(conn);
    	}
    	return -1;
    }
    
    public List<Meeting> searchMeeting(String meetingname, String roomname, String reservername, String reservefromdate, String reservetodate, String meetingfromdate, String meetingtodate, int page, int count) {
    	List<Meeting> list = new ArrayList<>();
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	StringBuffer sb = new StringBuffer("SELECT m.*, e.employeename, AS employeename, mr.roomname AS roomname FROM meeting m, employee e, meetingroom mr WHERE m.roomid=mr.roomid AND m.reservationistid=e.employeeid");
    	if (meetingname != null && !meetingname.equals(""))
    		sb.append(" and meetingname=?");
    	if (roomname != null && !roomname.equals(""))
    		sb.append(" and roomname=?");
    	if (reservername != null && !reservername.equals(""))
    		sb.append(" and reservername=?" );
    	if (reservefromdate != null && !reservefromdate.equals("") && reservetodate != null && !reservetodate.equals(""))
    		sb.append(" and reservationtime>? && reservationtime<?");
    	if (meetingfromdate != null & meetingfromdate.equals("") && meetingtodate != null && meetingtodate.equals(""))
    		sb.append(" and starttime>? and endtime<?");
    	sb.append(" limit ?,?");
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sb.toString());
    		int index = 1;
        	if (meetingname != null && !meetingname.equals(""))
        		ps.setString(index++, meetingname);
        	if (roomname != null && !roomname.equals(""))
        		ps.setString(index++, roomname);
        	if (reservername != null && !reservername.equals(""))
        		ps.setString(index++, reservername);
        	if (reservefromdate != null && !reservefromdate.equals("") && reservetodate != null && !reservetodate.equals("")){
        		ps.setTimestamp(index++, Timestamp.valueOf(reservefromdate));
        		ps.setTimestamp(index++, Timestamp.valueOf(reservetodate));
        	}
        	if (meetingfromdate != null && !meetingfromdate.equals("") && meetingtodate != null && !meetingtodate.equals("")){
        		ps.setTimestamp(index++, Timestamp.valueOf(meetingfromdate));
        		ps.setTimestamp(index++, Timestamp.valueOf(meetingtodate));
        	}
        	ps.setInt(index++, (page-1) * count);
        	ps.setInt(index++, count);
        	rs = ps.executeQuery();
        	while(rs.next()) {
        		int meetingid = rs.getInt("meetingid");
        		String meetingname1 = rs.getString("meetingname");
        		int roomid = rs.getInt("roomid");
        		int reservationistid = rs.getInt("reservatioinistid");
        		int numberofparticipants = rs.getInt("numberofparticipants");
        		Timestamp starttime = rs.getTimestamp("starttime");
        		Timestamp endtime = rs.getTimestamp("endtime");
        		Timestamp reservationtime = rs.getTimestamp("reservationtime");
        		Timestamp canceledtime = rs.getTimestamp("canceltime");
        		String description = rs.getString("description");
        		int status = rs.getInt("status");
        		String employeename = rs.getString("employeename");
        		String roomname1 = rs.getString("roomname");
        		Meeting meeting = new Meeting(meetingid, meetingname1, roomid, reservationistid, numberofparticipants, starttime, endtime, reservationtime, canceledtime, description, status);
        		meeting.setEmpname(employeename);
        		meeting.setRoomname(roomname1);
        		list.add(meeting);
        	}
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(rs);
    		DBUtils.close(ps);
    		DBUtils.close(conn);
    	}
    	return list;
    }
    // 根据用户id获取未来七天内用户的会议安排
    public List<Meeting> getMeeting7Days(int empid) {
    	List<Meeting> list = new ArrayList<>();
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String sql = "SELECT m.*, mr.roomname AS roomname FROM meeting m, meetingroom mr WHERE meetingid IN (SELECT meeting FROM meetingparticipants WHERE employeeid=?) AND m.roomid=mr.roomid AND starttime<? AND starttime>?";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, empid);
    		ps.setTimestamp(2, new Timestamp(System.currentTimeMillis() + 7*24*60*1000));
    		ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
    		rs = ps.executeQuery();
    		while (rs.next()) {
    			int meetingid = rs.getInt("meetingid");
    			String meetingname1 = rs.getString("meetingname");
        		int roomid = rs.getInt("roomid");
        		int reservationistid = rs.getInt("reservatioinistid");
        		int numberofparticipants = rs.getInt("numberofparticipants");
        		Timestamp starttime = rs.getTimestamp("starttime");
        		Timestamp endtime = rs.getTimestamp("endtime");
        		Timestamp reservationtime = rs.getTimestamp("reservationtime");
        		Timestamp canceledtime = rs.getTimestamp("canceltime");
        		String description = rs.getString("description");
        		int status = rs.getInt("status");
        		String roomname1 = rs.getString("roomname");
        		Meeting meeting = new Meeting(meetingid, meetingname1, roomid, reservationistid, numberofparticipants, starttime, endtime, reservationtime, canceledtime, description, status);
        		meeting.setRoomname(roomname1);
        		list.add(meeting);
        	}
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(rs);
    		DBUtils.close(ps);
    		DBUtils.close(conn);
    	}
    	return list;
    }
    
    public List<Meeting> getMyBookingMeeting(int empid) {
    	List<Meeting> list = new ArrayList<>();
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String sql = "SELECT m.*, mr.roomname AS roomname FROM meeting m, meetingroom mr WHERE m.reservationistid=? AND m.status=0 AND m.roomid=mr.roomid";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, empid);
    		rs = ps.executeQuery();
    		while (rs.next()) {
    			int meetingid = rs.getInt("meetingid");
    			String meetingname1 = rs.getString("meetingname");
        		int roomid = rs.getInt("roomid");
        		int reservationistid = rs.getInt("reservatioinistid");
        		int numberofparticipants = rs.getInt("numberofparticipants");
        		Timestamp starttime = rs.getTimestamp("starttime");
        		Timestamp endtime = rs.getTimestamp("endtime");
        		Timestamp reservationtime = rs.getTimestamp("reservationtime");
        		Timestamp canceledtime = rs.getTimestamp("canceltime");
        		String description = rs.getString("description");
        		int status = rs.getInt("status");
        		String roomname1 = rs.getString("roomname");
        		Meeting meeting = new Meeting(meetingid, meetingname1, roomid, reservationistid, numberofparticipants, starttime, endtime, reservationtime, canceledtime, description, status);
        		meeting.setRoomname(roomname1);
        		list.add(meeting);
        	}
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(rs);
    		DBUtils.close(ps);
    		DBUtils.close(conn);
    	}
    	return list;
    }
    
    public List<Meeting> getCanceledMeeting(int empid) {
    	List<Meeting> list = new ArrayList<>();
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String sql = "SELECT m.*, mr.roomname AS roomname FROM meeting m, meetingroom mr WHERE meetingid IN (SELECT meetingid FROM meetingparticipants WHERE employeeid=?) AND m.roomid=mr.roomid AND m.status=1";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, empid);
    		rs = ps.executeQuery();
    		while (rs.next()) {
    			int meetingid = rs.getInt("meetingid");
    			String meetingname1 = rs.getString("meetingname");
        		int roomid = rs.getInt("roomid");
        		int reservationistid = rs.getInt("reservatioinistid");
        		int numberofparticipants = rs.getInt("numberofparticipants");
        		Timestamp starttime = rs.getTimestamp("starttime");
        		Timestamp endtime = rs.getTimestamp("endtime");
        		Timestamp reservationtime = rs.getTimestamp("reservationtime");
        		Timestamp canceledtime = rs.getTimestamp("canceltime");
        		String description = rs.getString("description");
        		int status = rs.getInt("status");
        		String roomname1 = rs.getString("roomname");
        		Meeting meeting = new Meeting(meetingid, meetingname1, roomid, reservationistid, numberofparticipants, starttime, endtime, reservationtime, canceledtime, description, status);
        		meeting.setRoomname(roomname1);
        		list.add(meeting);
        	}
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(rs);
    		DBUtils.close(ps);
    		DBUtils.close(conn);
    	}
    	return list;
    }
    
    public List<Meeting> getMyMeeting(int empid) {
    	List<Meeting> list = new ArrayList<>();
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String sql = "SELECT m.*, e.employeename, mr.roomname AS roomname FROM meeting m, employee e, meetingroom mr WHERE m.meetingid IN (SELECT mp.meetingid FROM meetingparticipans mp WHERE mp.employeeid=?) AND m.roomid=mr.roomid AND m.reservationistid=e.employeeid AND m.status=0 AND m.starttime>?";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, empid);
    		ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
    		rs = ps.executeQuery();
    		while (rs.next()) {
    			int meetingid = rs.getInt("meetingid");
    			String meetingname1 = rs.getString("meetingname");
        		int roomid = rs.getInt("roomid");
        		int reservationistid = rs.getInt("reservatioinistid");
        		int numberofparticipants = rs.getInt("numberofparticipants");
        		Timestamp starttime = rs.getTimestamp("starttime");
        		Timestamp endtime = rs.getTimestamp("endtime");
        		Timestamp reservationtime = rs.getTimestamp("reservationtime");
        		Timestamp canceledtime = rs.getTimestamp("canceltime");
        		String description = rs.getString("description");
        		int status = rs.getInt("status");
        		String roomname1 = rs.getString("roomname");
        		Meeting meeting = new Meeting(meetingid, meetingname1, roomid, reservationistid, numberofparticipants, starttime, endtime, reservationtime, canceledtime, description, status);
        		meeting.setRoomname(roomname1);
        		list.add(meeting);
        	}
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(rs);
    		DBUtils.close(ps);
    		DBUtils.close(conn);
    	}
    	return list;
    }
    /**
     * 添加会议记录
     * 
     * @param meeting
     * @return 插入记录id
     */
    public int insert(Meeting meeting) {
        Connection conn = null;
        String sql = "INSERT INTO meeting (meetingname, roomid, reservationistid, numberofparticipations, starttime, endtime, reservationtime, description, status) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = DBUtils.getConnection();
        	ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        	ps.setString(1, meeting.getMeetingname());
        	ps.setInt(2, meeting.getRoomid());
        	ps.setInt(3, meeting.getReservationistid());
        	ps.setInt(4, meeting.getNumberofparticipants());
        	ps.setTimestamp(5, meeting.getStarttime());
        	ps.setTimestamp(6, meeting.getEndtime());
        	ps.setTimestamp(7, meeting.getReservationtime());
        	ps.setString(8, meeting.getDescription());
        	ps.setInt(9, meeting.getStatus());
        	// 返回值为id
        	rs = ps.getGeneratedKeys();
        	if (rs.next()) 
        		return rs.getInt(1); 
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
        return -1;
    }
    
    public Meeting getMeetingById(int mid) {
    	Meeting meeting = null;
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String sql = "SELECT * FROM meeting WHERE meetingid=?";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
    		rs = ps.executeQuery();
    		if (rs.next()) {
    			int meetingid = rs.getInt("meetingid");
    			String meetingname1 = rs.getString("meetingname");
    			int roomid = rs.getInt("roomid");
    			int reservationistid = rs.getInt("reservationistid");
                int numberofparticipants = rs.getInt("numberofparticipants");
                Timestamp starttime = rs.getTimestamp("starttime");
                Timestamp endtime = rs.getTimestamp("endtime");
                Timestamp reservationtime = rs.getTimestamp("reservationtime");
                Timestamp canceledtime = rs.getTimestamp("canceledtime");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                meeting = new Meeting(meetingid, meetingname1, roomid, reservationistid, numberofparticipants, starttime, endtime, reservationtime, canceledtime, description, status);
                return meeting;
    		}
    	} catch (ClassNotFoundException e) {
        	e.printStackTrace();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
        return null;
    }
    
    public int cancelMeeting(int mid, String reason) {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	String sql = "UPDATE meeting SET status=1, canceledtime=?, canceledreason=? WHERE meetingid=?";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
    		ps.setString(2, reason);
    		ps.setInt(3, mid);
    		return ps.executeUpdate();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(ps);
    		DBUtils.close(conn);
    	}
    	return -1;
    }
    
    public static void main(String[] args) {
    	Meeting meeting = new MeetingDao().getMeetingById(25);
    	System.out.println(meeting.toString());
    }
}
