package com.sanmu.service.provider;

import com.sanmu.pojo.Provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author����ľ
 * Date��2022-05-03 16:44
 * Description��<����>
 */
public interface ProviderService {
    //��ȡ��Ӧ������
    public List<Provider> getProviderName();
    //��ȡ��Ӧ���б�
    public List<Provider> getProviderList(String queryProCode,String queryProName);
    //��ӹ�Ӧ��
    public boolean providerAdd(Provider provider) throws SQLException;
    //���ݶ���idɾ������
    public boolean deleteBillId(Integer BillId);
    //ͨ��proId��ȡProvider
    public Provider getProviderById( String id);
    //�޸��û���Ϣ
    public boolean modify(Provider provider);
}
