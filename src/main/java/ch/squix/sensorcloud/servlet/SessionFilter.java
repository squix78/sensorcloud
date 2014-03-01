package ch.squix.sensorcloud.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
        
        HttpSession session = request.getSession();
        String incomingUrl=request.getRequestURI();
        logger.info("Arrived in session filter for request " + incomingUrl);

        response.setHeader("Access-Control-Allow-Origin", "*");
		chain.doFilter(request, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}