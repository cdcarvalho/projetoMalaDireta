package br.com.malaDiretaService.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;

import br.com.malaDiretaService.util.JPAUtil;


/**
 * Servlet Filter implementation class TransactionFilter
 */
@WebFilter(urlPatterns = "/*")
public class TransactionFilter implements Filter {
	
	private static final Logger logger = Logger.getLogger(TransactionFilter.class);

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			JPAUtil.beginTransaction();
			request.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
			JPAUtil.commitTransaction();
		} catch (Exception e) {
			JPAUtil.rollbackTransaction();
			logger.error(e);
			chain.doFilter(request, response);
		} 
	}

	public void destroy() {
		
	}

}
