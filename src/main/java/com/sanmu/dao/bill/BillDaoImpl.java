package com.sanmu.dao.bill;

import com.mysql.cj.util.StringUtils;
import com.sanmu.dao.BaseDao;
import com.sanmu.pojo.Bill;
import com.sanmu.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author：三木
 * Date：2022-05-03 14:32
 * Description：<获取订单列表>
 */
public class BillDaoImpl implements BillDao{
    @Override
    public List<Bill> getBillList(Connection connection, String productName, int proName, int isPayment) throws SQLException {

        PreparedStatement pstm=null;
        ResultSet rs=null;
        List<Bill> billlist=new ArrayList<>();
        if(connection!=null){
            StringBuffer sql=new StringBuffer();
            sql.append("SELECT b.*,p.proName as providerName FROM smbms_bill b,smbms_provider p WHERE b.providerId=p.id");
            List<Object> list=new ArrayList<>();
            if(!StringUtils.isNullOrEmpty(productName)){
                sql.append(" and b.productName like ?");
                list.add("%"+productName+"%");
            }
            if(proName>0){
                sql.append(" and p.id = ?");
                list.add(proName);
            }
            if(isPayment>0){
                sql.append(" and b.isPayment = ?");
                list.add(isPayment);
            }
            Object[] parms=list.toArray();
            System.out.println("sql语句："+sql.toString());
            rs = BaseDao.execute(connection, sql.toString(), parms, rs, pstm);
            while (rs.next()){
                Bill bill=new Bill();
                bill.setId(rs.getInt("id"));
                bill.setBillCode(rs.getString("billCode"));
                bill.setProductName(rs.getString("productName"));
                bill.setProductDesc(rs.getString("productDesc"));
                bill.setProductCount(rs.getBigDecimal("productCount"));
                bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
                bill.setIsPayment(rs.getInt("isPayment"));
                bill.setCreatedBy(rs.getInt("createdBy"));
                bill.setCreationDate(rs.getDate("creationDate"));
                bill.setModifyBy(rs.getInt("modifyBy"));
                bill.setModifyDate(rs.getDate("modifyDate"));
                bill.setProviderId(rs.getInt("providerId"));
                bill.setProviderName(rs.getString("providerName"));
                billlist.add(bill);
            }
            BaseDao.closeResource(connection,rs,pstm);
        }
        return billlist;
    }

    //添加订单
    @Override
    public int addBill(Connection connection, Bill bill) {
        PreparedStatement pstm=null;
        int rs=0;
        if(connection!=null){
            try {
                String sql="insert into smbms_bill (billCode,productName,productUnit," +
                        "productCount,totalPrice,providerId,isPayment,creationDate,createdBy) " +
                        "values(?,?,?,?,?,?,?,?,?)";
                Object[] parms={bill.getBillCode(),bill.getProductName(),bill.getProductUnit(),
                bill.getProductCount(),bill.getTotalPrice(),bill.getProviderId(),bill.getIsPayment(),bill.getCreationDate(),bill.getCreatedBy()};
                System.out.println(sql);
                rs = BaseDao.execute(connection, sql, parms, pstm);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                BaseDao.closeResource(connection,null,pstm);
            }
        }
        return rs;
    }

    @Override
    public int deleteBillId(Connection connection, Integer BillId) throws SQLException {
        String sql="DELETE FROM smbms_bill WHERE id = ?";
        Object[] prams={BillId};
        int execute = BaseDao.execute(connection, sql, prams, null);
        BaseDao.closeResource(connection,null,null);
        return execute;
    }

    @Override
    public Bill getBillById(Connection connection, String id) throws SQLException {
        Bill bill = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if(null != connection){
            String sql = "select b.*,p.proName as providerName from smbms_bill b, smbms_provider p " +
                    "where b.providerId = p.id and b.id=?";
            Object[] params = {id};
            rs = BaseDao.execute(connection,sql, params,rs,pstm);
            if(rs.next()){
                bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setBillCode(rs.getString("billCode"));
                bill.setProductName(rs.getString("productName"));
                bill.setProductDesc(rs.getString("productDesc"));
                bill.setProductUnit(rs.getString("productUnit"));
                bill.setProductCount(rs.getBigDecimal("productCount"));
                bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
                bill.setIsPayment(rs.getInt("isPayment"));
                bill.setProviderId(rs.getInt("providerId"));
                bill.setProviderName(rs.getString("providerName"));
                bill.setModifyBy(rs.getInt("modifyBy"));
                bill.setModifyDate(rs.getTimestamp("modifyDate"));
            }
            BaseDao.closeResource(null, rs, pstm);
        }
        return bill;
    }

    //根据用户传递输入的值修改订单表
    @Override
    public int modify(Connection connection, Bill bill) throws SQLException {
        int modifyNum=0;
        PreparedStatement pstm=null;
        if(connection!=null){
            String sql = "update smbms_bill set productName=?," +
                    "productDesc=?,productUnit=?,productCount=?,totalPrice=?," +
                    "isPayment=?,providerId=?,modifyBy=?,modifyDate=? where id = ? ";
            Object[] params = {bill.getProductName(),bill.getProductDesc(),
                    bill.getProductUnit(),bill.getProductCount(),bill.getTotalPrice(),bill.getIsPayment(),
                    bill.getProviderId(),bill.getModifyBy(),bill.getModifyDate(),bill.getId()};
            modifyNum=BaseDao.execute(connection,sql,params,pstm);
            BaseDao.closeResource(null,null,pstm);
        }
        return modifyNum;
    }
}
