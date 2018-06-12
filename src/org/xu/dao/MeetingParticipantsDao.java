package org.xu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MeetingParticipantsDao {
    public void insert(int meetingid, String[] empids) {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	String sql = "INSERT INTO meetingparticipants (meetingid, employid) value (?,?)";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
    		for (String empid : empids) {
        		ps.setInt(1, meetingid);
        		ps.setInt(2, Integer.parseInt(empid));
        		ps.addBatch();
        	}
    		ps.executeBatch();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(ps);
    		DBUtils.close(conn);
    	}
    }
}
