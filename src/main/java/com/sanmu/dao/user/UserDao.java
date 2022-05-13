package com.sanmu.dao.user;

import com.sanmu.pojo.Role;
import com.sanmu.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author：三木
 * Date：2022-04-25 15:16
 * Description：<描述>
 */
public interface UserDao {
    //得到要登入的用户
    public User getLoginUser(Connection connection,String userCode,String password) throws SQLException;

    //修改当前用户密码
    public int updatePwd(Connection connection,int id,String password);

    //根据用户名或者角色查询用户总数
    public int getUserCount(Connection connection,String username,int userRole) throws SQLException;

    //通过条件查询用户列表-serList
    public List<User> getUserList(Connection connection, String username, int userRole, int currentPageNo, int pageSize) throws SQLException;

    //添加用户
    public int add(Connection connection, User user) throws SQLException;

    //通过用户id删除用户信息
    public int deleteUserId(Connection connection,Integer userId);

    //修改用户信息
    public int modify(Connection connection,User user);
    //通过id得到用户信息
    public User getUserById(Connection connection,String id) throws SQLException;
}
