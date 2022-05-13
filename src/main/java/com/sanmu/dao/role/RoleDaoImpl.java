package com.sanmu.dao.role;

import com.sanmu.dao.BaseDao;
import com.sanmu.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author：三木
 * Date：2022-05-02 9:31
 * Description：<描述>
 */
public class RoleDaoImpl implements RoleDao{
    //获取角色列表
    @Override
    public List<Role> getRoleList(Connection connection) throws SQLException {
        PreparedStatement pstm=null;
        ResultSet rs=null;
        List<Role> rolelist = new ArrayList<>();
        if(connection!=null){
            String sql="select * from smbms_role";
            Object[] parms={};
            rs= BaseDao.execute(connection, sql, parms, rs, pstm);
            while(rs.next()){
                Role role=new Role();
                role.setId(rs.getInt("id"));
                role.setRoleCode( rs.getString("roleCode"));
                role.setRoleName( rs.getString("roleName"));
                rolelist.add(role);
            }
            BaseDao.closeResource(connection,rs,pstm);
        }
        return rolelist;
    }
}
