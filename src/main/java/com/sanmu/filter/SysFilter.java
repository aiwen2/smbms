package com.sanmu.filter;

import com.sanmu.pojo.User;
import com.sanmu.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author：三木
 * Date：2022-04-26 11:06
 * Description：<描述>
 */
public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //过滤器，从Session中获取用户
        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
        if(user==null){
            response.sendRedirect("/smbms_war/error.jsp");
        }else{
            filterChain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
