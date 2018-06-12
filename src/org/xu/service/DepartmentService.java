package org.xu.service;

import java.util.List;

import org.xu.bean.Department;
import org.xu.dao.DepartmentDao;

public class DepartmentService {
    private DepartmentDao departmentDao = new DepartmentDao();
    public int updateDepById(String name, int id) {
    	return departmentDao.updateDepById(name, id);
    }
    
    public int deleteDepById(int id) {
    	return departmentDao.deleteDepById(id);
    }
    
    public List<Department> getAllDepartments() {
    	return departmentDao.getAllDepartments();
    }
    
    public int insert(String name) {
    	return departmentDao.insert(name);
    }
}
