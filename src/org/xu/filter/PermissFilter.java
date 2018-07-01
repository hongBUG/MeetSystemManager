package org.xu.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class PermissFilter
 */
@WebFilter("/PermissFilter")
public class PermissFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PermissFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
    @Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest req = (HttpServletRequest) request;
    	String servletPath = req.getServletPath();
    	if ("/login.jsp".equals(servletPath) || "/reg".equals(servletPath)) {
    		chain.doFilter(req, response);
    	} else {
    		Object loginUser = req.getSession().getAttribute("loginUser");
    		if (loginUser == null) {
    			((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login.jsp");
    		} else {
    			chain.doFilter(req, response);
    		}
    	}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
    @Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
