package org.xu.bean;

public class Department {
    private int departmentid;
    private String departmentname;
    
    public Department() {
    }
    public Department(int depid, String depname) {
    	this.departmentid = depid;
    	this.departmentname = depname;
    }
	public int getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(int departmentid) {
		this.departmentid = departmentid;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
    
}
