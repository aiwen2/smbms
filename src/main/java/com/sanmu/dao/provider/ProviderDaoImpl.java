package com.sanmu.dao.provider;

import com.mysql.cj.util.StringUtils;
import com.sanmu.dao.BaseDao;
import com.sanmu.pojo.Provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author：三木
 * Date：2022-05-03 15:27
 * Description：<描述>
 */
public class ProviderDaoImpl implements ProviderDao{
    //获取供应商名称
    @Override
    public List<Provider> getProviderName(Connection connection) throws SQLException {
        ResultSet rs=null;
        PreparedStatement pstm=null;
        List<Provider> prolist=new ArrayList<>();
        if(connection!=null){
            String sql="select * from smbms_provider";
            Object[] parms={};
            rs = BaseDao.execute(connection, sql, parms, rs, pstm);
            while (rs.next()){
                Provider provider=new Provider();
                provider.setId(rs.getInt("id"));
                provider.setProCode(rs.getString("proCode"));
                provider.setProName(rs.getString("proName"));
                prolist.add(provider);
            }
            BaseDao.closeResource(connection,rs,pstm);
        }
        return prolist;
    }

    //获取供应商列表
    @Override
    public List<Provider> getProviderList(Connection connection,String queryProCode,String queryProName) throws SQLException {
        ResultSet rs=null;
        PreparedStatement pstm=null;
        List<Provider> providerlist=new ArrayList<>();
        if(connection!=null){
            StringBuffer sql=new StringBuffer();
            sql.append("select * from smbms_provider");
            List<Object> list=new ArrayList<>();
            if(!StringUtils.isNullOrEmpty(queryProCode)){
                sql.append(" where proCode like ?");
                list.add(queryProCode);
            }else if(!StringUtils.isNullOrEmpty(queryProName)){
                sql.append(" where proName like ?");
                list.add(queryProName);
            }
            Object[] parms=list.toArray();
            System.out.println("sql语句："+sql.toString());
            rs = BaseDao.execute(connection, sql.toString(), parms, rs, pstm);
            while (rs.next()){
                Provider provider=new Provider();
                provider.setId(rs.getInt("id"));
                provider.setProCode(rs.getString("proCode"));
                provider.setProName(rs.getString("proName"));
                provider.setProContact(rs.getString("proContact"));
                provider.setProPhone(rs.getString("proPhone"));
                provider.setProFax(rs.getString("proFax"));
                provider.setCreationDate(rs.getDate("creationDate"));
                providerlist.add(provider);
            }
            BaseDao.closeResource(connection,rs,pstm);
        }
        return providerlist;
    }

    //添加供应商
    @Override
    public int providerAdd(Connection connection, Provider provider) throws SQLException {
        PreparedStatement pstm=null;
        String sql="insert into smbms_provider(proCode,proName,proContact,proPhone,"+
                "proAddress,proFax,proDesc,createdBy,creationDate)"+
                "values(?,?,?,?,?,?,?,?,?)";
        Object[] parms={provider.getProCode(),provider.getProName(),provider.getProContact(),
                provider.getProPhone(),provider.getProAddress(),provider.getProFax(),
                provider.getProDesc(),provider.getCreatedBy(),provider.getCreationDate()};
        int execute = BaseDao.execute(connection, sql, parms, pstm);
        System.out.println(sql);
        BaseDao.closeResource(connection,null,pstm);
        return execute;
    }

    //根据供应商id删除供应商
    @Override
    public int deleteproId(Connection connection, Integer proId) {
        PreparedStatement pstm=null;
        int execute=0;
        try {
            String sql="DELETE FROM smbms_provider WHERE id = ?";
            Object[] parms={proId};
            execute= BaseDao.execute(connection, sql, parms, pstm);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,pstm);
        }
        return execute;
    }
    //通过proId获取Provider
    public Provider getProviderById(Connection connection, String id) throws SQLException {
        Provider provider = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if(null != connection){
            String sql = "select * from smbms_provider where id=?";
            Object[] params = {id};
            rs = BaseDao.execute(connection, sql, params, rs, pstm);
            if(rs.next()){
                provider = new Provider();
                provider.setId(rs.getInt("id"));
                provider.setProCode(rs.getString("proCode"));
                provider.setProName(rs.getString("proName"));
                provider.setProDesc(rs.getString("proDesc"));
                provider.setProContact(rs.getString("proContact"));
                provider.setProPhone(rs.getString("proPhone"));
                provider.setProAddress(rs.getString("proAddress"));
                provider.setProFax(rs.getString("proFax"));
                provider.setCreatedBy(rs.getInt("createdBy"));
                provider.setCreationDate(rs.getTimestamp("creationDate"));
                provider.setModifyBy(rs.getInt("modifyBy"));
                provider.setModifyDate(rs.getTimestamp("modifyDate"));
            }
            BaseDao.closeResource(null, rs, pstm);
        }
        return provider;
    }
    public int modify(Connection connection, Provider provider) throws SQLException {
        int flag = 0;
        PreparedStatement pstm = null;
        if(null != connection){
            String sql = "update smbms_provider set proName=?,proDesc=?,proContact=?," +
                    "proPhone=?,proAddress=?,proFax=?,modifyBy=?,modifyDate=? where id = ? ";
            Object[] params = {provider.getProName(),provider.getProDesc(),provider.getProContact(),provider.getProPhone(),provider.getProAddress(),
                    provider.getProFax(),provider.getModifyBy(),provider.getModifyDate(),provider.getId()};
            flag = BaseDao.execute(connection, sql, params, pstm);
            BaseDao.closeResource(null, null, pstm);
        }
        return flag;
    }
}
