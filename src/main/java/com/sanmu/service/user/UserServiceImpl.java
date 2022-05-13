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
 * Author：三木
 * Date：2022-04-25 15:53
 * Description：<业务层实现类>
 */
public class UserServiceImpl implements UserService{
    //业务层都会调用dao层,所以要引用dao层
    private UserDao userDao;
    public UserServiceImpl(){
        userDao=new UserDaoImpl();
    }

    //用户登入
    @Override
    public User login(String userCode, String password) {
        Connection connection=null;
        User user=null;

        try {
            connection= BaseDao.getConnection();
            //通过业务层调用对应的具体的数据库操作
            user = userDao.getLoginUser(connection, userCode,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }

        return user;
    }

    //根据用户id修改密码
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

    //查询记录数
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

    //根据条件查询用户列表
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

    //添加用户
    @Override
    public boolean add(User user) {
        boolean flag=false;
        Connection connection=null;
        try {
            connection = BaseDao.getConnection();
            //connection.setAutoCommit(false);//开启jdbc事务
            int updateRows = userDao.add(connection, user);
            //connection.commit();//提交事务
            if(updateRows>0){
                flag=true;
                System.out.println("添加成功");
            }else{
                System.out.println("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("回滚事务....");
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

    //根据用户编码，判断用户是否存在
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
    //通过用户id删除用户信息
    @Override
    public boolean deleteUserId(Integer userId) {
        Connection connection=null;
        boolean flag=false;
        connection = BaseDao.getConnection();
        int i=0;
        i= userDao.deleteUserId(connection, userId);
        if(i>0){
            flag=true;
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }

        BaseDao.closeResource(connection,null,null);
        return flag;
    }
    //修改用户信息
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
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败");
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

    //测试
    @Test
    public void test() {
        UserServiceImpl userService = new UserServiceImpl();
        boolean b = userService.deleteUserId(30);
        if(b){
            System.out.println("成功");
        }
    }

}
