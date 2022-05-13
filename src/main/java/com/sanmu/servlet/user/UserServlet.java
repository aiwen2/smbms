package com.sanmu.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.JsonArray;
import com.sanmu.pojo.Role;
import com.sanmu.pojo.User;
import com.sanmu.service.role.RoleServieImpl;
import com.sanmu.service.user.UserServiceImpl;
import com.sanmu.util.Constants;
import com.sanmu.util.PageSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author：三木
 * Date：2022-04-26 15:35
 * Description：<Servlet层>
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");
        if(method.equals("savepwd")&&method!=null){
            //更新用户密码,pwdmodify.jsp
            this.updatePwd(req,resp);
        }else if(method.equals("pwdmodify")&&method!=null){
            //验证用户密码,pwdmodify.js
            this.pwdModify(req,resp);
        }else if(method.equals("query")&&method!=null){
            //查询用户操作,userlist.jsp
            this.query(req,resp);
        }else if(method.equals("ucexist")&&method!=null){
            //根据用户编码，判断用户是否存在,这里引用了useradd.js
            this.userCodeExist(req,resp);
        }else if(method.equals("add")&&method!=null){
            //添加用户,useradd.jsp
            this.add(req,resp);
        }else if(method.equals("getrolelist")&&method!=null){
            //获取用户角色，usermodify.js
            this.getRoleList(req,resp);
        }else if(method.equals("deluser")&&method!=null){
            //删除用户,这里引用了userlist.js
            this.deleteUserId(req,resp);
        }else if(method.equals("modifyexe")&&method!=null){
            //修改用户
            this.modify(req,resp);
        }else if(method != null && method.equals("modify")){
            //通过用户id得到用户
            this.getUserByid(req, resp,"usermodify.jsp");
        }else if(method.equals("view")&&method!=null){
            this.getUserByid(req,resp,"userview.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    //Servlet复用，用户有多个功能，都要使用UserServlet
    //添加新密码
    private void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从Session里面那id
        Object attribute = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");
        boolean flag=false;
        if(attribute!=null&&newpassword!=null){
            UserServiceImpl userService = new UserServiceImpl();
            userService.updatePwd(((User)attribute).getId(),newpassword);
            if(flag){
                req.setAttribute("method","密码修改成功，请退出重新登入");
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else{
                req.setAttribute("method","密码修改失败");
            }
        }else {
            req.setAttribute("method","新密码有问题");
        }
        req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
    }
    //验证旧密码，Session中有用户的密码
    private void pwdModify(HttpServletRequest req, HttpServletResponse resp){
        //从Session里面那id
        Object attribute = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");
        //万能的Map，resultMap结果集
        Map<String,String> resultMap = new HashMap<String,String>();
        if(attribute==null){//Session为空，Session过期
            resultMap.put("result","sessionerror");
        }else if(StringUtils.isNullOrEmpty(oldpassword)){//输入的密码为空
            resultMap.put("result","error");
        }else {
            String userPassword =((User)attribute).getUserPassword();//Session中用户的密码
            if(oldpassword.equals(userPassword)){
                resultMap.put("result","true");
            }else{
                resultMap.put("result","false");
            }
        }

        try {
            resp.setContentType("application/json");//响应方式为json
            PrintWriter writer = resp.getWriter();
            /*JSONArray 阿里巴巴的JSON工具类，转换格式
               resultMap=["result","sessionerror","result","true"]
               json格式={key,value}
             */
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();//刷新
            writer.close();//关闭
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //用户管理
    private void query(HttpServletRequest req, HttpServletResponse resp){
        //从前端获取数据
        String queryname = req.getParameter("queryname");//用户名
        String temp = req.getParameter("queryUserRole");//用户角色
        String pageIndex = req.getParameter("pageIndex");
        int queryUserRole=0;
        //获取用户列表
        UserServiceImpl userService = new UserServiceImpl();
        List<User> userList=null;
        //第一次走这个请求，一定是第一页，页面大小固定
        int pageSize=5;//页面大小，可以放在配置文件，便于修改
        int currentPageNo=1;//记录条数，当前页码

        if(queryname==null){
            queryname="";
        }
        if(temp!=null&&!temp.equals("")){
            queryUserRole=Integer.parseInt(temp);//给查询赋值
        }
        if(pageIndex!=null){
            currentPageNo=Integer.parseInt(pageIndex);
        }
        //获取用户总数（分页：上一页，下一页）
        int totalCount = userService.getUserCount(queryname, queryUserRole);//总页数
        PageSupport pageSupport = new PageSupport();//分页工具类
        pageSupport.setPageSize(pageSize);
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setTotalCount(totalCount);

        int totalPageCount = pageSupport.getTotalPageCount();//可分几页
        //控制首页和尾页
        //如果页面要小于1，就显示第一页
        if(currentPageNo<1){
            currentPageNo=1;
        }else if(currentPageNo>totalPageCount){//当前页面大于最后一页
            currentPageNo=totalPageCount;
        }
        //获取用户列表展示
        userList = userService.getUserList(queryname, queryUserRole, currentPageNo, pageSize);
        req.setAttribute("userList",userList);

        //获取用户角色列表
        RoleServieImpl roleServie = new RoleServieImpl();
        List<Role> roleList = roleServie.getRoleList();
        req.setAttribute("roleList",roleList);
        req.setAttribute("totalCount",totalCount);
        req.setAttribute("currentPageNo",currentPageNo);
        req.setAttribute("totalPageCount",totalPageCount);

        try {
            req.getRequestDispatcher("userlist.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //添加用户
    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取前端数据
        String userCode=req.getParameter("userCode");
        String userName=req.getParameter("userName");
        String userPassword=req.getParameter("userPassword");
        String gender=req.getParameter("gender");
        String birthday=req.getParameter("birthday");
        String phone=req.getParameter("phone");
        String address=req.getParameter("address");
        String userRole=req.getParameter("userRole");

        User user = new User();
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setGender(Integer.valueOf(gender));
        try {
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.valueOf(userRole));
        user.setCreationDate(new Date());
        user.setCreatedBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());

        UserServiceImpl userService = new UserServiceImpl();
        if(userService.add(user)){
            //req.getRequestDispatcher("/jsp/user.do").forward(req,resp);
            resp.sendRedirect(req.getContextPath()+"/jsp/user.do?method=query");
        }else{
            req.getRequestDispatcher("useradd.jsp").forward(req,resp);
        }
    }

    //得到用户角色表
    private void getRoleList(HttpServletRequest req, HttpServletResponse resp){
        List<Role> roleList = null;
        RoleServieImpl roleServie = new RoleServieImpl();
        roleList=roleServie.getRoleList();
        try {
            //把roleList转换成json对象输出
            resp.setContentType("application/json");//响应方式为json
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(roleList));
            writer.flush();//刷新
            writer.close();//关闭
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //根据用户编码，判断用户是否存在
    private void userCodeExist(HttpServletRequest req, HttpServletResponse resp){
        //从前端获取usercode
        String userCode=req.getParameter("userCode");
        //用一个hashmap，暂存现在所有的用户编码
        Map<String,String> resultmap=new HashMap<>();
        if(StringUtils.isNullOrEmpty(userCode)){
            //如果输入的用户编码为空或者不存在，说明可用
            resultmap.put("userCode","exist");
        }else{
            //如果输入的编码不为空，则需要判断是否存在
            UserServiceImpl userService = new UserServiceImpl();
            User user = userService.UserCodeExist(userCode);
            if(user!=null){
                resultmap.put("userCode","exist");
            }else{
                resultmap.put("userCode","notexist");
            }
        }
        try {
            //把roleList转换成json对象输出
            resp.setContentType("application/json");//响应方式为json
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(resultmap));
            writer.flush();//刷新
            writer.close();//关闭
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //通过用户id删除用户信息
    private void deleteUserId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取前端数据
        String Id=req.getParameter("uid");
        Integer userId=0;
        userId=Integer.parseInt(Id);
        //判断是否删除成功
        Map<String,String> resultmap=new HashMap<>();
        if(userId<=0){
            resultmap.put("delResult","notexist");
            System.out.println("1");
        }else {
            UserServiceImpl userService = new UserServiceImpl();
            if(userService.deleteUserId(userId)){
                resultmap.put("delResult","true");
                System.out.println("2");
            }else {
                resultmap.put("delResult","false");
                System.out.println("3");
            }
        }
        //把resultMap转换成json对象输出
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultmap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    //修改用户信息
    private void modify(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //获取前端数据
        String id=req.getParameter("uid");
        String userName=req.getParameter("userName");
        String gender=req.getParameter("gender");
        String birthday=req.getParameter("birthday");
        String phone=req.getParameter("phone");
        String address=req.getParameter("address");
        String userRole=req.getParameter("userRole");

        User user=new User();
        user.setId(Integer.valueOf(id));
        user.setUserName(userName);
        user.setGender(Integer.valueOf(gender));
        try {
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.valueOf(userRole));
        user.setModifyBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        user.setModifyDate(new Date());

        UserServiceImpl userService = new UserServiceImpl();
        boolean modify = userService.modify(user);
        if(modify){
            resp.sendRedirect(req.getContextPath()+"/jsp/user.do?method=query");
        }else {
            req.getRequestDispatcher("usermodify.jsp").forward(req, resp);
        }
    }

    //通过id得到用户信息
    private void getUserByid(HttpServletRequest req, HttpServletResponse resp,String url) throws ServletException, IOException {
        String id=req.getParameter("uid");
        if(!StringUtils.isNullOrEmpty(id)){
            UserServiceImpl userService = new UserServiceImpl();
            User userById = userService.getUserById(id);
            req.setAttribute("user",userById);
            req.getRequestDispatcher(url).forward(req,resp);
        }
    }
}
