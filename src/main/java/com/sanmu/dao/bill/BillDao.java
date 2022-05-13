package com.sanmu.dao.bill;

import com.sanmu.pojo.Bill;
import com.sanmu.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author����ľ
 * Date��2022-05-03 14:09
 * Description��<��������>
 */
public interface BillDao {
    //ͨ��������ѯ�����б�
    public List<Bill> getBillList(Connection connection,String productName,int proName,int isPayment) throws SQLException;
    //��Ӷ���
    public int addBill(Connection connection,Bill bill);
    //���ݶ���idɾ������
    public int deleteBillId(Connection connection,Integer BillId) throws SQLException;

    //ͨ������id�õ��ö�����������Ϣ����ȷ��
    public Bill getBillById(Connection connection, String id) throws SQLException;
    //�����û����������ֵ�޸Ķ�����
    public int modify(Connection connection, Bill bill) throws SQLException;
}
