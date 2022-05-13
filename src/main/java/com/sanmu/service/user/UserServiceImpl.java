package com.sanmu.service.user;

import com.sanmu.dao.BaseDao;
import com.sanmu.dao.user.UserDao;
import com.sanmu.dao.user.UserDaoImpl;
import com.sanmu.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author����ľ
 * Date��2022-04-25 15:53
 * Description��<ҵ���ʵ����>
 */
public class UserServiceImpl implements UserService{
    //ҵ��㶼�����dao��,����Ҫ����dao��
    private UserDao userDao;
    public UserServiceImpl(){
        userDao=new UserDaoImpl();
    }

    //�û�����
    @Override
    public User login(String userCode, String password) {
        Connection connection=null;
        User user=null;

        try {
            connection= BaseDao.getConnection();
            //ͨ��ҵ�����ö�Ӧ�ľ�������ݿ����
            user = userDao.getLoginUser(connection, userCode,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }

        return user;
    }

    //�����û�id�޸�����
    @Override
    public boolean updatePwd(int id, String password) {
        Connection connection=null;
        boolean flag=false;
        connection = BaseDao.getConnection();
        if(userDao.updatePwd(connection, id, password)>0){
            flag=true;
        }
        BaseDao.closeResource(connection,null,null);
        return flag;
    }

    //��ѯ��¼��
    @Override
    public int getUserCount(String username, int userRole) {
        Connection connection=null;
        int count=0;

        try {
            connection = BaseDao.getConnection();
            count=userDao.getUserCount(connection,username,userRole);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return count;
    }

    //����������ѯ�û��б�
    @Override
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) {
        Connection connection=null;

        List<User> userList=new ArrayList<>();
        System.out.println("queryUserName:"+queryUserName);
        System.out.println("queryUserRole:"+queryUserRole);
        System.out.println("currentPageNo:"+currentPageNo);
        System.out.println("pageSize:"+pageSize);
        try {
            connection = BaseDao.getConnection();
            userList=userDao.getUserList(connection,queryUserName,queryUserRole,currentPageNo,pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return userList;

    }

    //����û�
    @Override
    public boolean add(User user) {
        boolean flag=false;
        Connection connection=null;
        try {
            connection = BaseDao.getConnection();
            //connection.setAutoCommit(false);//����jdbc����
            int updateRows = userDao.add(connection, user);
            //connection.commit();//�ύ����
            if(updateRows>0){
                flag=true;
                System.out.println("��ӳɹ�");
            }else{
                System.out.println("���ʧ��");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("�ع�����....");
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

    //�����û����룬�ж��û��Ƿ����
    @Override
    public User UserCodeExist(String userCode) {
        Connection connection=null;
        User loginUser=null;
        try {
            connection = BaseDao.getConnection();
            loginUser= userDao.getLoginUser(connection, userCode, null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(connection,null,null);
        }
        return loginUser;
    }
    //ͨ���û�idɾ���û���Ϣ
    @Override
    public boolean deleteUserId(Integer userId) {
        Connection connection=null;
        boolean flag=false;
        connection = BaseDao.getConnection();
        int i=0;
        i= userDao.deleteUserId(connection, userId);
        if(i>0){
            flag=true;
            System.out.println("ɾ���ɹ�");
        }else{
            System.out.println("ɾ��ʧ��");
        }

        BaseDao.closeResource(connection,null,null);
        return flag;
    }
    //�޸��û���Ϣ
    @Override
    public boolean modify(User user) {
        Connection connection=null;
        boolean flag=false;
        int modify=0;
        connection=BaseDao.getConnection();
        //connection.setAutoCommit(false);
        modify= userDao.modify(connection, user);
        //connection.commit();
        if(modify>0){
            flag=true;
            System.out.println("�޸ĳɹ�");
        }else{
            System.out.println("�޸�ʧ��");
        }
        BaseDao.closeResource(connection,null,null);
        return flag;
    }

    @Override
    public User getUserById(String id) {
        Connection connection=null;
        User userById = null;
        try {
            connection=BaseDao.getConnection();
            userById = userDao.getUserById(connection, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }

        return userById;
    }

    //����
    @Test
    public void test() {
        UserServiceImpl userService = new UserServiceImpl();
        boolean b = userService.deleteUserId(30);
        if(b){
            System.out.println("�ɹ�");
        }
    }

}
