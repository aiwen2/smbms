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
 * Author����ľ
 * Date��2022-05-02 9:36
 * Description��<����>
 */
public class RoleServieImpl implements RoleService{
    //����Dao
    private RoleDao roleDao;
    public RoleServieImpl() {
        roleDao=new RoleDaoImpl();
    }
    //��ȡ��ɫ�б�
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
    //����
    @Test
    public void test() {
        RoleService roleService=new RoleServieImpl();
        List<Role> roleList = roleService.getRoleList();
        for(Role r:roleList){
            System.out.println(r.getRoleName());
        }
    }
}
