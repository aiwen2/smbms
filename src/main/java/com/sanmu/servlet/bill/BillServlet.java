package com.sanmu.servlet.bill;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.sanmu.pojo.Bill;
import com.sanmu.pojo.Provider;
import com.sanmu.pojo.User;
import com.sanmu.service.bill.BillService;
import com.sanmu.service.bill.BillServiceImpl;
import com.sanmu.service.provider.ProviderService;
import com.sanmu.service.provider.ProviderServiceImpl;
import com.sanmu.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author����ľ
 * Date��2022-05-03 16:22
 * Description��<��������>
 */
public class BillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");
        if(method.equals("query")&&method!=null){
            this.query(req,resp);
        }else if(method.equals("add")&&method!=null){
            this.add(req,resp);
        }else if(method.equals("getproviderlist")&&method!=null){
            this.getproviderlist(req,resp);
        }else if(method.equals("delbill")&&method!=null){
            this.deleteBillId(req,resp);
        }else if(method != null && method.equals("modify")){
            this.getBillById(req,resp,"billmodify.jsp");
        }else if(method != null && method.equals("modifysave")){
            this.modify(req,resp);
        }else if(method != null && method.equals("view")){
            this.getBillById(req,resp,"billview.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    //��������
    private void query(HttpServletRequest req, HttpServletResponse resp){
        //��ǰ�˻�ȡ����
        String queryProductName = req.getParameter("queryProductName");
        String tempId = req.getParameter("queryProviderId");
        String temp = req.getParameter("queryIsPayment");
        int queryProviderId=0;
        int queryIsPayment=0;
        if(queryProductName==null){
            queryProductName="";
        }
        if(tempId!=null){
            queryProviderId=Integer.parseInt(tempId);
        }
        if(temp!=null){
            queryIsPayment=Integer.parseInt(temp);
        }

        //��ȡ�����б�
        BillServiceImpl billService = new BillServiceImpl();
        List<Bill> billlist=null;
        //��ȡ�����б�չʾ
        billlist = billService.getBillList(queryProductName, queryProviderId, queryIsPayment);
        req.setAttribute("billList",billlist);
        //��ȡ��Ӧ���б�
        ProviderService providerService=new ProviderServiceImpl();
        List<Provider> proList =null;
        proList = providerService.getProviderName();
        req.setAttribute("providerList",proList);
        try {
            req.getRequestDispatcher("billlist.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //��Ӷ���
    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //��ȡ����
        String billCode=req.getParameter("billCode");
        String productName=req.getParameter("productName");
        String productUnit=req.getParameter("productUnit");
        String productCount=req.getParameter("productCount");
        String totalPrice=req.getParameter("totalPrice");
        String providerId=req.getParameter("providerId");
        String isPayment=req.getParameter("isPayment");

        Bill bill=new Bill();
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductUnit(productUnit);
        bill.setProductCount(new BigDecimal(productCount));
        bill.setTotalPrice(new BigDecimal(totalPrice));
        bill.setProviderId(Integer.valueOf(providerId));
        bill.setIsPayment(Integer.valueOf(isPayment));
        bill.setCreationDate(new Date());
        bill.setCreatedBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());

        BillServiceImpl billService = new BillServiceImpl();
        if(billService.addBill(bill)){
            resp.sendRedirect(req.getContextPath()+"/jsp/bill.do?method=query");
        }else{
            req.setAttribute("error","���ʧ��");
            req.getRequestDispatcher("billadd.jsp").forward(req,resp);
        }
    }
    //�õ���Ӧ���б�
    private void getproviderlist(HttpServletRequest req, HttpServletResponse resp){
        ProviderServiceImpl providerService=new ProviderServiceImpl();
        List<Provider> providerName = providerService.getProviderName();
        try {
            //��roleListת����json�������
            resp.setContentType("application/json");//��Ӧ��ʽΪjson
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(providerName));
            writer.flush();//ˢ��
            writer.close();//�ر�
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //���ݶ���idɾ������
    private void deleteBillId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //��ȡǰ������
        String Id=req.getParameter("billid");
        Integer billid=0;
        billid=Integer.parseInt(Id);
        Map<String,String> resultmap=new HashMap<>();
        BillServiceImpl billService = new BillServiceImpl();
        if(billid<=0){
            resultmap.put("delResult","notexist");
        }else {
            if(billService.deleteBillId(billid)){
                resultmap.put("delResult","true");
            }else {
                resultmap.put("delResult","false");
            }
        }
        //��resultMapת����json�������
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultmap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    private void getBillById(HttpServletRequest request, HttpServletResponse response,String url)
            throws ServletException, IOException {
        String id = request.getParameter("billid");
        if(!StringUtils.isNullOrEmpty(id)){
            BillService billService = new BillServiceImpl();
            Bill bill = null;
            bill = billService.getBillById(id);
            request.setAttribute("bill", bill);
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    private void modify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("modify===============");
        String id = request.getParameter("id");
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        String productUnit = request.getParameter("productUnit");
        String productCount = request.getParameter("productCount");
        String totalPrice = request.getParameter("totalPrice");
        String providerId = request.getParameter("providerId");
        String isPayment = request.getParameter("isPayment");

        Bill bill = new Bill();
        bill.setId(Integer.valueOf(id));
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductUnit(productUnit);
        bill.setProductCount(new BigDecimal(productCount).setScale(2,BigDecimal.ROUND_DOWN));
        bill.setIsPayment(Integer.parseInt(isPayment));
        bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2,BigDecimal.ROUND_DOWN));
        bill.setProviderId(Integer.parseInt(providerId));

        bill.setModifyBy(((User)request.getSession().getAttribute(Constants.USER_SESSION)).getId());
        bill.setModifyDate(new Date());
        boolean flag = false;
        BillService billService = new BillServiceImpl();
        flag = billService.modify(bill);
        if(flag){//�ж��Ƿ��޸ĳɹ�
            response.sendRedirect(request.getContextPath()+"/jsp/bill.do?method=query");
        }else{
            request.getRequestDispatcher("billmodify.jsp").forward(request, response);
        }
    }
}
