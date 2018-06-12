package org.xu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.xu.bean.Department;

public class DepartmentDao {
    public int updateDepById(String name, int id) {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	String sql = null;
    	try {
    		conn = DBUtils.getConnection();
    		sql = "UPDATE department SET departmentname=? WHERE departmentid=?";
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, name);
    		ps.setInt(2, id);
    		return ps.executeUpdate();
    	} catch (ClassNotFoundException e) {
    		System.out.println("class not found");
    		System.out.println("SQL exception");
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(conn);
    		DBUtils.close(ps);
    	}
    	return -1;
    }
    
    public int insert(String name) {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	String sql = null;
    	try {
    		conn = DBUtils.getConnection();
    		sql = "INSERT INTO department (departmentname) VALUES(?)";
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, name);
    		return ps.executeUpdate();
    	} catch (ClassNotFoundException e) {
    		System.out.println("class not found");
    		e.printStackTrace();
    	} catch (SQLException e) {
    		System.out.println("SQL Exception");
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(conn);
    		DBUtils.close(ps);
    	}
    	return -1;
    }
    
    public int deleteDepById(int id) {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	String sql = null;
    	try {
    		conn = DBUtils.getConnection();
    		sql = "DELETE FROM department WHERE departmentid=?";
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, id);
    		return ps.executeUpdate();
    	} catch (ClassNotFoundException e) {
    		System.out.println("class not found");
    		e.printStackTrace();
    	} catch (SQLException e) {
    		System.out.println("SQL Exception");
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(conn);
    		DBUtils.close(ps);
    	}
    	return -1;
    }
    
    public List<Department> getAllDepartments() {
    	List<Department> list = new ArrayList<>();
    	Connection conn = null;
    	PreparedStatement ps = null;
    	String sql = null;
    	ResultSet rs = null;
    	try {
    		conn = DBUtils.getConnection();
    		sql = "SELECT * FROM department";
    		ps = conn.prepareStatement(sql);
    		rs =  ps.executeQuery();
    		while(rs.next()) {
    			list.add(new Department(rs.getInt("departmentid"), rs.getString("departmentname")));
    		}
    	} catch (ClassNotFoundException e) {
    		System.out.println("class not found");
    		e.printStackTrace();
    	} catch (SQLException e) {
    		System.out.println("SQL Exception");
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(conn);
    		DBUtils.close(ps);
    	}
    	return list;
    }
}
