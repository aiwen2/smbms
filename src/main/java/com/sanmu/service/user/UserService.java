package com.sanmu.service.user;

import com.sanmu.pojo.User;

import java.sql.Connection;
import java.util.List;

/**
 * Author����ľ
 * Date��2022-04-25 15:51
 * Description��<ҵ���ӿ�>
 */
public interface UserService {
    //�û�����
    public User login(String userCode,String password);
    //�����û�id�޸�����
    public boolean updatePwd(int id,String password);
    //��ѯ��¼��
    public int getUserCount(String username,int userRole);
    //����������ѯ�û��б�
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);
    //����û�
    public boolean add(User user);
    //�����û����룬�ж��û��Ƿ����
    public User UserCodeExist(String userCode);
    //ͨ���û�idɾ���û���Ϣ
    public boolean deleteUserId(Integer userId);
    //�޸��û���Ϣ
    public boolean modify(User user);
    //ͨ��id�õ��û���Ϣ
    public User getUserById(String id);

}
