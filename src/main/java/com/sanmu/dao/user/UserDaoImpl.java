package com.sanmu.dao.user;

import com.mysql.cj.util.StringUtils;
import com.sanmu.dao.BaseDao;
import com.sanmu.pojo.Role;
import com.sanmu.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author：三木
 * Date：2022-04-25 15:19
 * Description：<数据连接>
 */
public class UserDaoImpl implements UserDao{
    @Override
    //得到要登入的用户
    public User getLoginUser(Connection connection, String userCode,String password) throws SQLException {
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        User user=null;

        if(connection!=null){
            StringBuffer sql=new StringBuffer();
            sql.append("select * from smbms_user");//查询语句
            ArrayList<Object> list=new ArrayList<>();//存放参数
            if(!StringUtils.isNullOrEmpty(userCode)){
                sql.append(" where userCode=?");
                list.add(userCode);
            }
            if(!StringUtils.isNullOrEmpty(password)){
                sql.append(" and userPassword = ?");
                list.add(password);
            }
            //将list转换为数组
            Object[] params = list.toArray();
            System.out.println(sql.toString());
            //查询用户,返回结果集
            rs = BaseDao.execute(connection, sql.toString(), params, rs, preparedStatement);
            //遍历用户
            if(rs.next()){
                user=new User();
                user.setId((rs.getInt("id")));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getDate("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getDate("modifyDate"));
            }
            //释放资源
            BaseDao.closeResource(null,rs,preparedStatement);
        }
        return user;
    }

    //修改当前用户密码
    @Override
    public int updatePwd(Connection connection, int id, String password) {
        PreparedStatement preparedStatement=null;
        int execute=0;
        if(connection!=null){
            String sql="update smbms_user set userPassword=? where id=?";
            Object[] parms={password,id};
            try {
                execute= BaseDao.execute(connection, sql, parms, preparedStatement);
                BaseDao.closeResource(null,null,preparedStatement);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return execute;
    }
    //根据用户名或者角色查询用户总数
    @Override
    public int getUserCount(Connection connection, String username, int userRole) throws SQLException {
        PreparedStatement pstm=null;
        int count=0;
        ResultSet rs=null;
        if(connection!=null){
            StringBuffer sql=new StringBuffer();
            sql.append("select count(1) as count from smbms_user u,smbms_role r where u.userRole=r.id");
            ArrayList<Object> list=new ArrayList<>();//存放参数
            if (!StringUtils.isNullOrEmpty(username)) {
                sql.append(" and u.username like ?");
                list.add("%"+username+"%");
            }
            if(userRole>0){
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }
            //将list转换为数组
            Object[] params = list.toArray();
            System.out.println("sql语句："+sql.toString());
            rs= BaseDao.execute(connection, sql.toString(), params, rs, pstm);
            if(rs.next()){
                count=rs.getInt("count");//从结果集中获取数量
            }
            BaseDao.closeResource(null,rs,pstm);
        }
        return count;
    }

    //通过条件查询用户列表-serList
    @Override
    public List<User> getUserList(Connection connection, String username, int userRole, int currentPageNo, int pageSize) throws SQLException {
        PreparedStatement pstm=null;
        ResultSet rs=null;
        List<User> userlist=new ArrayList<>();
        if(connection!=null){
            StringBuffer sql = new StringBuffer();
            sql.append("select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole=r.id");
            List<Object> list=new ArrayList<>();
            if(!StringUtils.isNullOrEmpty(username)){
                sql.append(" and u.userName like ?");
                list.add("%"+username+"%");
            }
            if(userRole>0){
                sql.append(" and u.userRole=?");
                list.add(userRole);
            }
            sql.append(" order by creationDate DESC limit ?,?");
            currentPageNo=(currentPageNo-1)*pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            Object[] parms=list.toArray();
            System.out.println("sql语句："+sql.toString());
            rs=BaseDao.execute(connection,sql.toString(),parms,rs,pstm);
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setUserRole(rs.getInt("userRole"));
                user.setUserRoleName(rs.getString("userRoleName"));
                userlist.add(user);
            }
            BaseDao.closeResource(connection,rs,pstm);
        }
        return userlist;
    }

    //添加用户
    @Override
    public int add(Connection connection, User user) throws SQLException {
        PreparedStatement pstm=null;
        int rs=0;
        if(connection!=null){
            String sql="insert into smbms_user (userCode,userName,userPassword," +
                    "userRole,gender,birthday,phone,address,creationDate,createdBy) " +
                    "values(?,?,?,?,?,?,?,?,?,?)";
            Object[] params={user.getUserCode(), user.getUserName(), user.getUserPassword(),
                    user.getUserRole(), user.getGender(), user.getBirthday(),
                    user.getPhone(), user.getAddress(), user.getCreationDate(), user.getCreatedBy()};
            System.out.println(sql);
            rs = BaseDao.execute(connection, sql, params, pstm);
            BaseDao.closeResource(connection,null,pstm);
        }
        return rs;
    }

    //通过用户id删除用户信息
    @Override
    public int deleteUserId(Connection connection, Integer userId) {
        PreparedStatement pstm=null;
        int execute=0;
        try {
            String sql="DELETE FROM smbms_user WHERE id = ?";
            Object[] parms={userId};
            execute= BaseDao.execute(connection, sql, parms, pstm);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return execute;
    }

    //修改用户信息
    @Override
    public int modify(Connection connection, User user) {
        PreparedStatement pstm=null;

        int execute = 0;
        if(connection!=null){
            try {
                String sql="update smbms_user set userName=?,gender=?,birthday=?,"+
                        "phone=?,address=?,userRole=?,modifyBy=?,modifyDate=? where id=?";
                Object[] parms={user.getUserName(),user.getGender(),user.getBirthday(),
                        user.getPhone(),user.getAddress(),user.getUserRole(),user.getModifyBy(),user.getModifyDate(),user.getId()};
                execute = BaseDao.execute(connection, sql, parms, pstm);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                BaseDao.closeResource(connection,null,pstm);
            }
        }

        return execute;
    }

    @Override
    public User getUserById(Connection connection, String id) throws SQLException {
        PreparedStatement pstm=null;
        ResultSet rs=null;
        User user = new User();
        if(connection!=null){
            String sql="select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.id=? and u.userRole = r.id";
            Object[] params={id};
            rs = BaseDao.execute(connection, sql, params, rs, pstm);
            while(rs.next()){
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getTimestamp("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getTimestamp("modifyDate"));
                user.setUserRoleName(rs.getString("userRoleName"));
            }
            BaseDao.closeResource(null,rs,pstm);
        }
        return user;
    }
}
