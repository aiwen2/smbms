package com.sanmu.service.provider;

import com.sanmu.pojo.Provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author：三木
 * Date：2022-05-03 16:44
 * Description：<描述>
 */
public interface ProviderService {
    //获取供应商名称
    public List<Provider> getProviderName();
    //获取供应商列表
    public List<Provider> getProviderList(String queryProCode,String queryProName);
    //添加供应商
    public boolean providerAdd(Provider provider) throws SQLException;
    //根据订单id删除订单
    public boolean deleteBillId(Integer BillId);
    //通过proId获取Provider
    public Provider getProviderById( String id);
    //修改用户信息
    public boolean modify(Provider provider);
}
