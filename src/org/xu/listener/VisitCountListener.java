package org.xu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.xu.dao.CountDao;

public class VisitCountListener implements ServletContextListener{
	
	private CountDao countDao = new CountDao();
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		Object visitcount = sce.getServletContext().getAttribute("vc");
		countDao.updateCount(Integer.parseInt(visitcount.toString()));
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		int  count = countDao.getCount();
		sce.getServletContext().setAttribute("vc", count);
	}
	
}
