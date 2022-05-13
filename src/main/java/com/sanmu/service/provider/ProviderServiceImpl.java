package com.sanmu.service.provider;

import com.mysql.cj.util.StringUtils;
import com.sanmu.dao.BaseDao;
import com.sanmu.dao.provider.ProviderDao;
import com.sanmu.dao.provider.ProviderDaoImpl;
import com.sanmu.pojo.Provider;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Author����ľ
 * Date��2022-05-03 16:45
 * Description��<����>
 */
public class ProviderServiceImpl implements ProviderService{
    ProviderDao providerDao;

    public ProviderServiceImpl() {
        providerDao=new ProviderDaoImpl();
    }
    //��ȡ��Ӧ������
    @Override
    public List<Provider> getProviderName() {
        Connection connection =null;
        List<Provider> proList =null;
        try {
            connection = BaseDao.getConnection();
            proList = providerDao.getProviderName(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return proList;
    }

    //��ȡ��Ӧ���б�
    @Override
    public List<Provider> getProviderList(String queryProCode,String queryProName) {
        Connection connection =null;
        List<Provider> providerList =null;
        try {
            connection = BaseDao.getConnection();
            providerList = providerDao.getProviderList(connection,queryProCode,queryProName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return providerList;
    }

    //��ӹ�Ӧ��
    @Override
    public boolean providerAdd(Provider provider)  {
        Connection connection =null;
        boolean flag=false;
        int i = 0;
        try {
            connection= BaseDao.getConnection();
            //connection.setAutoCommit(false);
            i = providerDao.providerAdd(connection, provider);
            //connection.commit();
            if(i>0){
                flag=true;
                System.out.println("��ӳɹ�");
            }else {
                System.out.println("���ʧ��");
            }
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

    @Override
    public boolean deleteBillId(Integer BillId) {
        boolean flag=false;
        Connection connection=null;
        connection=BaseDao.getConnection();
        int i = providerDao.deleteproId(connection, BillId);
        if(i>0){
            flag=true;
            System.out.println("ɾ���ɹ�");
        }else {
            System.out.println("ɾ��ʧ��");
        }
        BaseDao.closeResource(connection,null,null);
        return flag;
    }
    //ͨ��proId��ȡProvider
    @Override
    public Provider getProviderById(String id) {
        Provider provider = null;
        Connection connection = null;
        try{
            connection = BaseDao.getConnection();
            provider = providerDao.getProviderById(connection, id);
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            provider = null;
        }finally{
            BaseDao.closeResource(connection, null, null);
        }
        return provider;
    }

    @Override
    public boolean modify(Provider provider) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if(providerDao.modify(connection,provider) > 0)
                flag = true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }


    @Test
    public void test(){
        ProviderService providerService=new ProviderServiceImpl();
        List<Provider> providerList = providerService.getProviderList("HB_GYS001","ʯ��ׯ˧��ʳƷó�����޹�˾");
        for(Provider p:providerList){
            System.out.println(p.getProFax());
        }
    }
}
