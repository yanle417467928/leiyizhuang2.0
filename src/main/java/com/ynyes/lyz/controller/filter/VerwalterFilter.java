package com.ynyes.lyz.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class VerwalterFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("<h2 style=\"color:red;\">后台系统已经升级，请访问：http://manage.leyizhuang.com.cn</h2>");
	}

	@Override
	public void destroy() {
		
	}

}
