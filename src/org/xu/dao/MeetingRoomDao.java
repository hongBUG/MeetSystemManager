package org.xu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.xu.bean.MeetingRoom;

public class MeetingRoomDao {
    public List<MeetingRoom> getAllMeetingRoom() {
    	List<MeetingRoom> list = new ArrayList<>();
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String sql = null;
    	try {
    		conn = DBUtils.getConnection();
    		sql = "select * from meetingroom;";
    		ps = conn.prepareStatement(sql);
    		ps.executeQuery();
    		while (rs.next()) {
    			MeetingRoom mr = new MeetingRoom(rs.getInt("roomid"), rs.getInt("roomnum"), rs.getString("roomname"), rs.getInt("capacity"), rs.getInt("status"), rs.getString("description"));
    			list.add(mr);
    		}
    	} catch (ClassNotFoundException e ) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(ps);
    		DBUtils.close(rs);
    		DBUtils.close(conn);
    	}
    	return list;
    }
    
    public MeetingRoom getMeetingRoomById(int id) {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String sql = null;
    	try {
    		conn = DBUtils.getConnection();
    		sql = "select * from meetingroom WHERE roomid=?;";
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, id);
    		ps.executeQuery();
    		if (rs.next()) {
    			MeetingRoom mr = new MeetingRoom(rs.getInt("roomid"), rs.getInt("roomnum"), rs.getString("roomname"), rs.getInt("capacity"), rs.getInt("status"), rs.getString("description"));
    			return mr;
    		}
    	} catch (ClassNotFoundException e ) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(ps);
    		DBUtils.close(rs);
    		DBUtils.close(conn);
    	}
    	return null;
    }
    
    public int insert(MeetingRoom mr) {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	String sql = "INSERT INTO meetingroom(roomnum, roomname, capacity, status, description) VALUES(?,?,?,?,?)";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, mr.getRoomnum());
    		ps.setString(2, mr.getRoomname());
    		ps.setInt(3, mr.getCapacity());
    		ps.setInt(4, mr.getStatus());
    		ps.setString(5, mr.getDescription());
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
    
    public int update (MeetingRoom mr) {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	String sql = "UPDATE meetingroom set roomnum=?,roomname=?capacity=?,status=?,description=? WHERE roomid=?";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, mr.getRoomnum());
    		ps.setString(2, mr.getRoomname());
    		ps.setInt(3, mr.getCapacity());
    		ps.setInt(4, mr.getStatus());
    		ps.setString(5, mr.getDescription());
    		ps.setInt(6, mr.getRoomid());
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
    
    // this is a test, hah hah hah ~~~
    public static void main(String[] args) {
    	
    }
}
