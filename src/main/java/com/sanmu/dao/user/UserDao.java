package com.sanmu.dao.user;

import com.sanmu.pojo.Role;
import com.sanmu.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author����ľ
 * Date��2022-04-25 15:16
 * Description��<����>
 */
public interface UserDao {
    //�õ�Ҫ������û�
    public User getLoginUser(Connection connection,String userCode,String password) throws SQLException;

    //�޸ĵ�ǰ�û�����
    public int updatePwd(Connection connection,int id,String password);

    //�����û������߽�ɫ��ѯ�û�����
    public int getUserCount(Connection connection,String username,int userRole) throws SQLException;

    //ͨ��������ѯ�û��б�-serList
    public List<User> getUserList(Connection connection, String username, int userRole, int currentPageNo, int pageSize) throws SQLException;

    //����û�
    public int add(Connection connection, User user) throws SQLException;

    //ͨ���û�idɾ���û���Ϣ
    public int deleteUserId(Connection connection,Integer userId);

    //�޸��û���Ϣ
    public int modify(Connection connection,User user);
    //ͨ��id�õ��û���Ϣ
    public User getUserById(Connection connection,String id) throws SQLException;
}
