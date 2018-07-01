package org.xu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountDao {
	public int getCount() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtils.getConnection();
			sql = "SELECT * FROM counter";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
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
		return 0;
	}
	
	public int updateCount(int count) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = DBUtils.getConnection();
			sql = "UPDATE counter SET visitcount=?";
			ps.setInt(1, count);
			return ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e ) {
			e.printStackTrace();
		} finally {
			DBUtils.close(ps);
			DBUtils.close(conn);
		}
		return -1;
	}
}
