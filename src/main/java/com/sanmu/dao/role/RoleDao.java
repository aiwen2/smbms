package com.sanmu.dao.role;

import com.sanmu.pojo.Role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author����ľ
 * Date��2022-05-02 9:31
 * Description��<����>
 */
public interface RoleDao {
    //��ȡ��ɫ�б�
    public List<Role> getRoleList(Connection connection) throws SQLException;
}
