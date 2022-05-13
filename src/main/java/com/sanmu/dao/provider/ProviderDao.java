package com.sanmu.dao.provider;

import com.sanmu.pojo.Provider;
import com.sanmu.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author：三木
 * Date：2022-05-03 15:15
 * Description：<描述>
 */
public interface ProviderDao {
    //获取供应商名称
    public List<Provider> getProviderName(Connection connection) throws SQLException;
    //获取供应商列表
    public List<Provider> getProviderList(Connection connection,String queryProCode,String queryProName) throws SQLException;
    //添加供应商
    public int providerAdd(Connection connection,Provider provider) throws SQLException;
    //根据供应商id删除供应商
    public int deleteproId(Connection connection,Integer proId);
    //通过proId获取Provider
    public Provider getProviderById(Connection connection,String id) throws SQLException;
    public int modify(Connection connection, Provider provider) throws SQLException;

}
