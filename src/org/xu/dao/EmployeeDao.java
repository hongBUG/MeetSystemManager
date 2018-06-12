package org.xu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.xu.bean.Employee;

public class EmployeeDao {
	private String sql = null;
	
    public int updateEmpStatusById(int id, int status) {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	try{
    		conn = DBUtils.getConnection();
    		sql = "UPDATE employee set status=? WHERE employeeid=?";
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, status);
    		ps.setInt(2, id);
    		return ps.executeUpdate();
    	} catch (ClassNotFoundException e) {
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(ps);
    		DBUtils.close(conn);
    	}
    	return -1;
    }
    
    public List<Employee> getUnApproveaccount() {
    	List<Employee> list = new ArrayList<>();
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
    		conn = DBUtils.getConnection();
    		sql = "SELECT * FROM employee WHERE status=0 AND role=2";
    		ps = conn.prepareStatement(sql);
    		rs = ps.executeQuery();
    		while (rs.next()) {
    			list.add(new Employee(rs.getInt("employeeid"), rs.getString("employeename"), rs.getString("username"), rs.getString("phone"), rs.getString("email"), rs.getInt("status"), rs.getInt("departmentid"), rs.getString("password"), rs.getInt("role")));
    		}
    		return list;
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(conn);
    		DBUtils.close(ps);
    		DBUtils.close(rs);
    	}
    	return list;
    }
    
    public List<Employee> getEmpByDepId(int depId) {
    	List<Employee> list = new ArrayList<>();
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
    		conn = DBUtils.getConnection();
    		sql = "SELECT * FROM employee WHERE status=1 AND departmentid=?";
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, depId);
    		rs = ps.executeQuery();
    		while (rs.next()) {
    			list.add(new Employee(rs.getInt("employeeid"), rs.getString("employeename"), rs.getString("username"), rs.getString("phone"), rs.getString("email"), rs.getInt("status"), rs.getInt("departmentid"), rs.getString("password"), rs.getInt("role")));
    		}
    		return list;
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(conn);
    		DBUtils.close(ps);
    		DBUtils.close(rs);
    	}
    	return list;
    }
    
    public List<Employee> getEmpByMeetingId(int mid) {
    	List<Employee> list = new ArrayList<>();
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
    		conn = DBUtils.getConnection();
    		sql = "SELECT * FROM employee WHERE employeeid IN(SELECT employeeid FROM meetingparticipants WHERE meetingid=?)";
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, mid);
    		rs = ps.executeQuery();
    		while(rs.next()) {
    			list.add(new Employee(rs.getInt("employeeid"), rs.getString("employeename"), rs.getString("username"), rs.getString("phone"), rs.getString("email"), rs.getInt("status"), rs.getInt("departmentid"), rs.getString("password"), rs.getInt("role")));
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(conn);
    		DBUtils.close(rs);
    		DBUtils.close(ps);
    	}
    	return list;
    }
    
    public int getCount(String employeename, String username, int status) {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	StringBuffer sb = new StringBuffer("SELECT count(*) FROM employee WHERE status=? AND role=?");
    	if(employeename != null && !"".equals(employeename))
    		sb.append(" and employeename=?");
    	if(username != null && !"".equals(username))
    		sb.append(" and username=?");
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sb.toString());
    		ps.setInt(1, status);
    		int index = 2;
        	if(employeename != null && !"".equals(employeename))
        		ps.setString(index++, employeename);
        	if(username != null && !"".equals(username))
        		ps.setString(index++, username);
        	rs = ps.executeQuery();
        	if(rs.next())
        		return rs.getInt(1);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(conn);
    		DBUtils.close(rs);
    		DBUtils.close(ps);
    	}
    	return -1;
    }
    
    public List<Employee> searchEmp(String employeename, String username, int status, int page, int count) {
    	List<Employee> list = new ArrayList<>();
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	StringBuffer sb = new StringBuffer("SELECT * FROM employee WHERE status=? AND role=2");
    	if(employeename != null && !"".equals(employeename))
    		sb.append(" and employeename=?");
    	if(username != null && !"".equals(username))
    		sb.append(" and username=?");
    	sb.append(" limit ?,?");
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, status);
    		int index = 2;
    		if(employeename != null && !"".equals(employeename))
        		ps.setString(index++, employeename);
        	if(username != null && !"".equals(username))
        		ps.setString(index++, username);
        	ps.setInt(index++, (page - 1) * count);
        	ps.setInt(index++, count);
    		rs = ps.executeQuery();
    		while(rs.next()) {
    			list.add(new Employee(rs.getInt("employeeid"), rs.getString("employeename"), rs.getString("username"), rs.getString("phone"), rs.getString("email"), rs.getInt("status"), rs.getInt("departmentid"), rs.getString("password"), rs.getInt("role")));
    		}
    		return list;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(conn);
    		DBUtils.close(rs);
    		DBUtils.close(ps);
    	}
    	return null;
    }
    
    public Employee login(String username, String password) {
    	 Connection conn = null;
         PreparedStatement ps = null;
         ResultSet rs = null;
         try {
             conn = DBUtils.getConnection();
             ps = conn.prepareStatement("SELECT * FROM employee WHERE username=? AND password=?");
             ps.setString(1, username);
             ps.setString(2, password);
             rs = ps.executeQuery();
             if (rs.next()) {
                 return new Employee(rs.getInt("employeeid"), rs.getString("employeename"), rs.getString("username"), rs.getString("phone"), rs.getString("email"), rs.getInt("status"), rs.getInt("departmentid"), rs.getString("password"), rs.getInt("role"));
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
         return null;
    }
    
    public int reg(Employee emp){
    	if(isUsernameExists(emp.getUsername()))
    		return -1;
    	Connection conn = null;
        PreparedStatement ps = null;
        try {
        	conn = DBUtils.getConnection();
        	sql = "INSERT INTO employee(employeename, username, phone, email, status, departmentid, password, role) VALUE (?,?,?,?,?,?,?,?)";
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, emp.getEmployeename());
        	ps.setString(2, emp.getUsername());
        	ps.setString(3, emp.getPhone());
        	ps.setString(4, emp.getEmail());
        	ps.setInt(5, 0);
        	ps.setInt(6, emp.getDepartmentid());
        	ps.setString(7, emp.getPassword());
        	ps.setInt(8, 2);
        	return ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(ps);
            DBUtils.close(conn);
        }
        return 0;
    }
    
    public boolean isUsernameExists(String username) {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
    		conn = DBUtils.getConnection();
    		sql = "SELECT * from employee WHERE username=?";
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, username);
    	    rs = ps.executeQuery();
    	    if(rs.next())
    	    	return true;
    	} catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(conn);
        }
    	return false;
    }
}
