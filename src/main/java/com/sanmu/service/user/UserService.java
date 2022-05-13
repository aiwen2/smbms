package com.sanmu.service.user;

import com.sanmu.pojo.User;

import java.sql.Connection;
import java.util.List;

/**
 * Author：三木
 * Date：2022-04-25 15:51
 * Description：<业务层接口>
 */
public interface UserService {
    //用户登入
    public User login(String userCode,String password);
    //根据用户id修改密码
    public boolean updatePwd(int id,String password);
    //查询记录数
    public int getUserCount(String username,int userRole);
    //根据条件查询用户列表
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);
    //添加用户
    public boolean add(User user);
    //根据用户编码，判断用户是否存在
    public User UserCodeExist(String userCode);
    //通过用户id删除用户信息
    public boolean deleteUserId(Integer userId);
    //修改用户信息
    public boolean modify(User user);
    //通过id得到用户信息
    public User getUserById(String id);

}
