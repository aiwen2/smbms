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
 * Author����ľ
 * Date��2022-04-26 15:35
 * Description��<Servlet��>
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");
        if(method.equals("savepwd")&&method!=null){
            //�����û�����,pwdmodify.jsp
            this.updatePwd(req,resp);
        }else if(method.equals("pwdmodify")&&method!=null){
            //��֤�û�����,pwdmodify.js
            this.pwdModify(req,resp);
        }else if(method.equals("query")&&method!=null){
            //��ѯ�û�����,userlist.jsp
            this.query(req,resp);
        }else if(method.equals("ucexist")&&method!=null){
            //�����û����룬�ж��û��Ƿ����,����������useradd.js
            this.userCodeExist(req,resp);
        }else if(method.equals("add")&&method!=null){
            //����û�,useradd.jsp
            this.add(req,resp);
        }else if(method.equals("getrolelist")&&method!=null){
            //��ȡ�û���ɫ��usermodify.js
            this.getRoleList(req,resp);
        }else if(method.equals("deluser")&&method!=null){
            //ɾ���û�,����������userlist.js
            this.deleteUserId(req,resp);
        }else if(method.equals("modifyexe")&&method!=null){
            //�޸��û�
            this.modify(req,resp);
        }else if(method != null && method.equals("modify")){
            //ͨ���û�id�õ��û�
            this.getUserByid(req, resp,"usermodify.jsp");
        }else if(method.equals("view")&&method!=null){
            this.getUserByid(req,resp,"userview.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    //Servlet���ã��û��ж�����ܣ���Ҫʹ��UserServlet
    //���������
    private void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��Session������id
        Object attribute = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");
        boolean flag=false;
        if(attribute!=null&&newpassword!=null){
            UserServiceImpl userService = new UserServiceImpl();
            userService.updatePwd(((User)attribute).getId(),newpassword);
            if(flag){
                req.setAttribute("method","�����޸ĳɹ������˳����µ���");
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else{
                req.setAttribute("method","�����޸�ʧ��");
            }
        }else {
            req.setAttribute("method","������������");
        }
        req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
    }
    //��֤�����룬Session�����û�������
    private void pwdModify(HttpServletRequest req, HttpServletResponse resp){
        //��Session������id
        Object attribute = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");
        //���ܵ�Map��resultMap�����
        Map<String,String> resultMap = new HashMap<String,String>();
        if(attribute==null){//SessionΪ�գ�Session����
            resultMap.put("result","sessionerror");
        }else if(StringUtils.isNullOrEmpty(oldpassword)){//���������Ϊ��
            resultMap.put("result","error");
        }else {
            String userPassword =((User)attribute).getUserPassword();//Session���û�������
            if(oldpassword.equals(userPassword)){
                resultMap.put("result","true");
            }else{
                resultMap.put("result","false");
            }
        }

        try {
            resp.setContentType("application/json");//��Ӧ��ʽΪjson
            PrintWriter writer = resp.getWriter();
            /*JSONArray ����Ͱ͵�JSON�����࣬ת����ʽ
               resultMap=["result","sessionerror","result","true"]
               json��ʽ={key,value}
             */
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();//ˢ��
            writer.close();//�ر�
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //�û�����
    private void query(HttpServletRequest req, HttpServletResponse resp){
        //��ǰ�˻�ȡ����
        String queryname = req.getParameter("queryname");//�û���
        String temp = req.getParameter("queryUserRole");//�û���ɫ
        String pageIndex = req.getParameter("pageIndex");
        int queryUserRole=0;
        //��ȡ�û��б�
        UserServiceImpl userService = new UserServiceImpl();
        List<User> userList=null;
        //��һ�����������һ���ǵ�һҳ��ҳ���С�̶�
        int pageSize=5;//ҳ���С�����Է��������ļ��������޸�
        int currentPageNo=1;//��¼��������ǰҳ��

        if(queryname==null){
            queryname="";
        }
        if(temp!=null&&!temp.equals("")){
            queryUserRole=Integer.parseInt(temp);//����ѯ��ֵ
        }
        if(pageIndex!=null){
            currentPageNo=Integer.parseInt(pageIndex);
        }
        //��ȡ�û���������ҳ����һҳ����һҳ��
        int totalCount = userService.getUserCount(queryname, queryUserRole);//��ҳ��
        PageSupport pageSupport = new PageSupport();//��ҳ������
        pageSupport.setPageSize(pageSize);
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setTotalCount(totalCount);

        int totalPageCount = pageSupport.getTotalPageCount();//�ɷּ�ҳ
        //������ҳ��βҳ
        //���ҳ��ҪС��1������ʾ��һҳ
        if(currentPageNo<1){
            currentPageNo=1;
        }else if(currentPageNo>totalPageCount){//��ǰҳ��������һҳ
            currentPageNo=totalPageCount;
        }
        //��ȡ�û��б�չʾ
        userList = userService.getUserList(queryname, queryUserRole, currentPageNo, pageSize);
        req.setAttribute("userList",userList);

        //��ȡ�û���ɫ�б�
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

    //����û�
    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��ȡǰ������
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

    //�õ��û���ɫ��
    private void getRoleList(HttpServletRequest req, HttpServletResponse resp){
        List<Role> roleList = null;
        RoleServieImpl roleServie = new RoleServieImpl();
        roleList=roleServie.getRoleList();
        try {
            //��roleListת����json�������
            resp.setContentType("application/json");//��Ӧ��ʽΪjson
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(roleList));
            writer.flush();//ˢ��
            writer.close();//�ر�
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //�����û����룬�ж��û��Ƿ����
    private void userCodeExist(HttpServletRequest req, HttpServletResponse resp){
        //��ǰ�˻�ȡusercode
        String userCode=req.getParameter("userCode");
        //��һ��hashmap���ݴ��������е��û�����
        Map<String,String> resultmap=new HashMap<>();
        if(StringUtils.isNullOrEmpty(userCode)){
            //���������û�����Ϊ�ջ��߲����ڣ�˵������
            resultmap.put("userCode","exist");
        }else{
            //�������ı��벻Ϊ�գ�����Ҫ�ж��Ƿ����
            UserServiceImpl userService = new UserServiceImpl();
            User user = userService.UserCodeExist(userCode);
            if(user!=null){
                resultmap.put("userCode","exist");
            }else{
                resultmap.put("userCode","notexist");
            }
        }
        try {
            //��roleListת����json�������
            resp.setContentType("application/json");//��Ӧ��ʽΪjson
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(resultmap));
            writer.flush();//ˢ��
            writer.close();//�ر�
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //ͨ���û�idɾ���û���Ϣ
    private void deleteUserId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //��ȡǰ������
        String Id=req.getParameter("uid");
        Integer userId=0;
        userId=Integer.parseInt(Id);
        //�ж��Ƿ�ɾ���ɹ�
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
        //��resultMapת����json�������
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultmap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    //�޸��û���Ϣ
    private void modify(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //��ȡǰ������
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

    //ͨ��id�õ��û���Ϣ
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
