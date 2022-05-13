package com.sanmu.service.bill;

import com.sanmu.pojo.Bill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author����ľ
 * Date��2022-05-03 16:03
 * Description��<����>
 */
public interface BillService {
    //��ȡ�����б�
    public List<Bill> getBillList(String productName, int proName, int isPayment);
    //��Ӷ���
    public boolean addBill(Bill bill);
    //���ݶ���idɾ������
    public boolean deleteBillId(Integer BillId) throws SQLException;

    //ͨ������id�õ��ö�����������Ϣ����ȷ��
    public Bill getBillById(String id);
    //�����û����������ֵ�޸Ķ�����
    public boolean modify(Bill bill);

}
