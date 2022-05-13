package com.sanmu.dao.bill;

import com.sanmu.pojo.Bill;
import com.sanmu.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author：三木
 * Date：2022-05-03 14:09
 * Description：<订单管理>
 */
public interface BillDao {
    //通过条件查询订单列表
    public List<Bill> getBillList(Connection connection,String productName,int proName,int isPayment) throws SQLException;
    //添加订单
    public int addBill(Connection connection,Bill bill);
    //根据订单id删除订单
    public int deleteBillId(Connection connection,Integer BillId) throws SQLException;

    //通过订单id得到该订单的所有信息（正确）
    public Bill getBillById(Connection connection, String id) throws SQLException;
    //根据用户传递输入的值修改订单表
    public int modify(Connection connection, Bill bill) throws SQLException;
}
