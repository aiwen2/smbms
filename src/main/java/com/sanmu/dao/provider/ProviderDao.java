package com.sanmu.dao.provider;

import com.sanmu.pojo.Provider;
import com.sanmu.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author����ľ
 * Date��2022-05-03 15:15
 * Description��<����>
 */
public interface ProviderDao {
    //��ȡ��Ӧ������
    public List<Provider> getProviderName(Connection connection) throws SQLException;
    //��ȡ��Ӧ���б�
    public List<Provider> getProviderList(Connection connection,String queryProCode,String queryProName) throws SQLException;
    //��ӹ�Ӧ��
    public int providerAdd(Connection connection,Provider provider) throws SQLException;
    //���ݹ�Ӧ��idɾ����Ӧ��
    public int deleteproId(Connection connection,Integer proId);
    //ͨ��proId��ȡProvider
    public Provider getProviderById(Connection connection,String id) throws SQLException;
    public int modify(Connection connection, Provider provider) throws SQLException;

}
