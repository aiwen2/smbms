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
 * Author����ľ
 * Date��2022-04-25 16:30
 * Description��<����Servlet>
 */
public class LoginServlet extends HttpServlet {
    //Servlet:���Ʋ�,����ҵ������
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("�������ʼ");
        //��ȡ�û���������
        String username=req.getParameter("userCode");
        String password=req.getParameter("userPassword");
        //�����ݿ��е�������жԱ�,����ҵ���
        UserService userService=new UserServiceImpl();
        User login = userService.login(username, password);//�ѵ�����û����
        if(login!=null){//���д���
            //���û�����Ϣ�ŵ�Session��
            req.getSession().setAttribute(Constants.USER_SESSION,login);
            //��ת���ڲ�ҳ��
            resp.sendRedirect("jsp/frame.jsp");
        }else{
            //ת���ص���ҳ��,����ʾ�û������������
            req.setAttribute("error","�û����������벻��ȷ");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
