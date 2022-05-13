package com.sanmu.service.bill;

import com.sanmu.dao.BaseDao;
import com.sanmu.dao.bill.BillDao;
import com.sanmu.dao.bill.BillDaoImpl;
import com.sanmu.dao.provider.ProviderDao;
import com.sanmu.dao.provider.ProviderDaoImpl;
import com.sanmu.pojo.Bill;
import com.sanmu.pojo.Provider;
import com.sanmu.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.List;

/**
 * Author：三木
 * Date：2022-05-03 16:05
 * Description：<描述>
 */
public class BillServiceImpl implements BillService{
    BillDao billDao;
    public BillServiceImpl() {
        billDao=new BillDaoImpl();
    }

    //获取订单列表
    @Override
    public List<Bill> getBillList(String productName, int proName, int isPayment) {
        Connection connection=null;
        List<Bill> billList=new ArrayList<>();
        System.out.println("productName:"+productName);
        System.out.println("proName:"+proName);
        System.out.println("isPayment:"+isPayment);

        try {
            connection = BaseDao.getConnection();
             billList= billDao.getBillList(connection, productName, proName, isPayment);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return billList;
    }
    //添加订单
    @Override
    public boolean addBill(Bill bill)  {
        Connection connection=null;
        boolean flag=false;

        try {
            connection=BaseDao.getConnection();
            //connection.setAutoCommit(false);
            int i = billDao.addBill(connection, bill);
            //connection.commit();
            if(i>0){
                flag=true;
                System.out.println("添加成功");
            }else{
                System.out.println("添加失败");
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
            try {
                System.out.println("事务回滚。。。。");
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }
    //根据订单id删除订单
    @Override
    public boolean deleteBillId(Integer BillId) {
        Connection connection=null;
        boolean flag=false;
        connection=BaseDao.getConnection();
        int i = 0;
        try {
            i = billDao.deleteBillId(connection, BillId);
            if(i>0){
                flag=true;
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }

        return flag;
    }

    @Override
    public Bill getBillById(String id) {
        Bill bill = new Bill();
        Connection connection=null;
        try {
            connection=BaseDao.getConnection();
            bill = billDao.getBillById(connection, id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return bill;
    }

    //根据用户传递输入的值修改订单表
    @Override
    public boolean modify(Bill bill) {
        Boolean flag=false;
        int modifyNum=0;
        Connection connection=null;
        try {
            connection=BaseDao.getConnection();
            modifyNum=billDao.modify(connection,bill);
            if(modifyNum>0){
                flag=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

    @Test
    public void test(){
        BillService billService=new BillServiceImpl();
        List<Bill> list = billService.getBillList("", 2, 0);
        for(Bill b:list){
            System.out.println(b.getId());
            System.out.println(b.getBillCode());
            System.out.println(b.getProductName());
            System.out.println(b.getIsPayment());
            System.out.println(b.getProviderName());

        }
    }
}
