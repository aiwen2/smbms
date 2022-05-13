package com.sanmu.service.role;

import com.sanmu.dao.BaseDao;
import com.sanmu.dao.role.RoleDao;
import com.sanmu.dao.role.RoleDaoImpl;
import com.sanmu.pojo.Role;
import com.sanmu.service.user.UserServiceImpl;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author：三木
 * Date：2022-05-02 9:36
 * Description：<描述>
 */
public class RoleServieImpl implements RoleService{
    //引入Dao
    private RoleDao roleDao;
    public RoleServieImpl() {
        roleDao=new RoleDaoImpl();
    }
    //获取角色列表
    @Override
    public List<Role> getRoleList() {
        Connection connection=null;
        List<Role> roleList=null;
        try {
            connection = BaseDao.getConnection();
            roleList= roleDao.getRoleList(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return roleList;
    }
    //测试
    @Test
    public void test() {
        RoleService roleService=new RoleServieImpl();
        List<Role> roleList = roleService.getRoleList();
        for(Role r:roleList){
            System.out.println(r.getRoleName());
        }
    }
}
