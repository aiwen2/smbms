package com.sanmu.servlet.provider;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.sanmu.dao.BaseDao;
import com.sanmu.pojo.Provider;
import com.sanmu.pojo.User;
import com.sanmu.service.provider.ProviderService;
import com.sanmu.service.provider.ProviderServiceImpl;
import com.sanmu.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author：三木
 * Date：2022-05-05 15:35
 * Description：<供应商管理>
 */
public class ProviderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");
        if(method.equals("query")&&method!=null){
            this.query(req,resp);
        }else if (method.equals("add")&&method!=null){
            this.providerAdd(req,resp);
        }else if(method.equals("delprovider")&&method!=null){
            this.deleteproId(req,resp);
        }else if(method != null && method.equals("view")){
            this.getProviderById(req,resp,"providerview.jsp");
        }else if(method != null && method.equals("modify")){
            this.getProviderById(req,resp,"providermodify.jsp");
        }else if(method != null && method.equals("modifysave")){
            this.modify(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    //供应商管理
    private void query(HttpServletRequest req, HttpServletResponse resp){
        //获取数据
        String queryProCode=req.getParameter("queryProCode");
        String queryProName=req.getParameter("queryProName");
        if(queryProCode==null){
            queryProCode="";
        }
        if(queryProName==null){
            queryProName="";
        }
        //获取供应商列表
        ProviderServiceImpl providerService = new ProviderServiceImpl();
        List<Provider> providerList =null;
        //获取订单展示
        providerList = providerService.getProviderList(queryProCode, queryProName);
        req.setAttribute("providerList",providerList);

        try {
            req.getRequestDispatcher("providerlist.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //添加供应商
    private void providerAdd(HttpServletRequest req, HttpServletResponse resp) throws  IOException, ServletException {
        //获取前端数据
        String proCode=req.getParameter("proCode");
        String proName=req.getParameter("proName");
        String proContact=req.getParameter("proContact");
        String proPhone=req.getParameter("proPhone");
        String proAddress=req.getParameter("proAddress");
        String proFax=req.getParameter("proFax");
        String proDesc=req.getParameter("proDesc");

        Provider provider=new Provider();
        provider.setProCode(proCode);
        provider.setProName(proName);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProAddress(proAddress);
        provider.setProFax(proFax);
        provider.setProDesc(proDesc);
        provider.setCreatedBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        provider.setCreationDate(new Date());

        ProviderServiceImpl providerService = new ProviderServiceImpl();
        if(providerService.providerAdd(provider)){
            resp.sendRedirect(req.getContextPath()+"/jsp/provider.do?method=query");
        }else {
            req.getRequestDispatcher("provideradd.jsp").forward(req,resp);
        }
    }
    //根据供应商id删除供应商
    private void deleteproId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取前端数据
        String id=req.getParameter("proid");
        Integer proid=0;
        proid=Integer.parseInt(id);
        Map<String ,String> resultmap=new HashMap<>();
        ProviderServiceImpl providerService = new ProviderServiceImpl();
        if(proid<=0){
            resultmap.put("delResult","notexist");
        }else {
            if (providerService.deleteBillId(proid)) {
                resultmap.put("delResult", "true");
            } else {
                resultmap.put("delResult", "false");
            }
        }
        //把resultMap转换成json对象输出
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultmap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    private void getProviderById(HttpServletRequest request, HttpServletResponse response,String url)
            throws ServletException, IOException {
        String id = request.getParameter("proid");
        if(!StringUtils.isNullOrEmpty(id)){
            ProviderService providerService = new ProviderServiceImpl();
            Provider provider = null;
            provider = providerService.getProviderById(id);
            request.setAttribute("provider", provider);
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    private void modify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String proName=request.getParameter("proName");
        String proContact = request.getParameter("proContact");
        String proPhone = request.getParameter("proPhone");
        String proAddress = request.getParameter("proAddress");
        String proFax = request.getParameter("proFax");
        String proDesc = request.getParameter("proDesc");
        String id = request.getParameter("id");
        Provider provider = new Provider();
        provider.setId(Integer.valueOf(id));
        provider.setProName(proName);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProFax(proFax);
        provider.setProAddress(proAddress);
        provider.setProDesc(proDesc);
        provider.setModifyBy(((User)request.getSession().getAttribute(Constants.USER_SESSION)).getId());
        provider.setModifyDate(new Date());
        boolean flag = false;
        ProviderService providerService = new ProviderServiceImpl();
        flag = providerService.modify(provider);
        if(flag){
            response.sendRedirect(request.getContextPath()+"/jsp/provider.do?method=query");
        }else{
            request.getRequestDispatcher("providermodify.jsp").forward(request, response);
        }
    }
}
