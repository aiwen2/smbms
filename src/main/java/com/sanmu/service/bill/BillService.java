package com.sanmu.service.bill;

import com.sanmu.pojo.Bill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author：三木
 * Date：2022-05-03 16:03
 * Description：<描述>
 */
public interface BillService {
    //获取订单列表
    public List<Bill> getBillList(String productName, int proName, int isPayment);
    //添加订单
    public boolean addBill(Bill bill);
    //根据订单id删除订单
    public boolean deleteBillId(Integer BillId) throws SQLException;

    //通过订单id得到该订单的所有信息（正确）
    public Bill getBillById(String id);
    //根据用户传递输入的值修改订单表
    public boolean modify(Bill bill);

}
