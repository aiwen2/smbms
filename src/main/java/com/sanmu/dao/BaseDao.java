package com.sanmu.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Author：三木
 * Date：2022-04-24 15:48
 * Description：<操作数据库>
 */
public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    //静态代码块，类加载的时候就初始化了
    static {
        Properties properties = new Properties();
        //通过类加载器读取对应的资源
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver=properties.getProperty("driver");
        url=properties.getProperty("url");
        username=properties.getProperty("username");
        password=properties.getProperty("password");
    }
    //读取数据库的连接
    public static Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    //编写查询公共方法
    public static ResultSet execute(Connection connection,String sql,Object[] parms,ResultSet resultSet,PreparedStatement preparedStatement) throws SQLException {
        //预编译的sql，在后面直接执行就可以了
         preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < parms.length; i++) {
            //setObject，占位符从1开始，但是数组从0开始
            preparedStatement.setObject(i+1,parms[i]);
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
    //编写增删改公共方法
    public static int execute(Connection connection,String sql,Object[] parms,PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i <parms.length ; i++) {
            preparedStatement.setObject(i+1,parms[i]);
        }
        int updateRows = preparedStatement.executeUpdate();
        return updateRows;
    }
    //释放资源
    public static Boolean closeResource(Connection connection, ResultSet resultSet,PreparedStatement preparedStatement){
        boolean flag=true;
        if(connection!=null){
            try {
                connection.close();
                connection=null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag=false;
            }
        }
        if(resultSet!=null){
            try {
                resultSet.close();
                resultSet=null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag=false;
            }
        }
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
                preparedStatement=null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag=false;
            }
        }
        return flag;
    }

}
