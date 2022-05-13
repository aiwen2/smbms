package com.sanmu.dao.role;

import com.sanmu.pojo.Role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author：三木
 * Date：2022-05-02 9:31
 * Description：<描述>
 */
public interface RoleDao {
    //获取角色列表
    public List<Role> getRoleList(Connection connection) throws SQLException;
}
