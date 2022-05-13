package com.sanmu.service.role;

import com.sanmu.pojo.Role;

import java.sql.Connection;
import java.util.List;

/**
 * Author：三木
 * Date：2022-05-02 9:34
 * Description：<描述>
 */
public interface RoleService {
    //获取角色列表
    public List<Role> getRoleList();
}
