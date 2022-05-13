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
 * Author����ľ
 * Date��2022-05-03 16:05
 * Description��<����>
 */
public class BillServiceImpl implements BillService{
    BillDao billDao;
    public BillServiceImpl() {
        billDao=new BillDaoImpl();
    }

    //��ȡ�����б�
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
    //��Ӷ���
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
                System.out.println("��ӳɹ�");
            }else{
                System.out.println("���ʧ��");
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
            try {
                System.out.println("����ع���������");
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }
    //���ݶ���idɾ������
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
                System.out.println("ɾ���ɹ�");
            }else {
                System.out.println("ɾ��ʧ��");
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

    //�����û����������ֵ�޸Ķ�����
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
