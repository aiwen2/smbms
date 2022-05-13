package com.sanmu.servlet.user;

import com.sanmu.pojo.User;
import com.sanmu.service.user.UserService;
import com.sanmu.service.user.UserServiceImpl;
import com.sanmu.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Author：三木
 * Date：2022-04-25 16:30
 * Description：<登入Servlet>
 */
public class LoginServlet extends HttpServlet {
    //Servlet:控制层,调用业务层代码
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("登入服务开始");
        //获取用户名和密码
        String username=req.getParameter("userCode");
        String password=req.getParameter("userPassword");
        //和数据库中的密码进行对比,调用业务层
        UserService userService=new UserServiceImpl();
        User login = userService.login(username, password);//把登入的用户查出
        if(login!=null){//查有此人
            //将用户的信息放到Session中
            req.getSession().setAttribute(Constants.USER_SESSION,login);
            //跳转到内部页面
            resp.sendRedirect("jsp/frame.jsp");
        }else{
            //转发回登入页面,并提示用户名或密码错误
            req.setAttribute("error","用户名或者密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
