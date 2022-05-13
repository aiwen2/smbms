package com.sanmu.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Author����ľ
 * Date��2022-04-24 15:48
 * Description��<�������ݿ�>
 */
public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    //��̬����飬����ص�ʱ��ͳ�ʼ����
    static {
        Properties properties = new Properties();
        //ͨ�����������ȡ��Ӧ����Դ
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
    //��ȡ���ݿ������
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
    //��д��ѯ��������
    public static ResultSet execute(Connection connection,String sql,Object[] parms,ResultSet resultSet,PreparedStatement preparedStatement) throws SQLException {
        //Ԥ�����sql���ں���ֱ��ִ�оͿ�����
         preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < parms.length; i++) {
            //setObject��ռλ����1��ʼ�����������0��ʼ
            preparedStatement.setObject(i+1,parms[i]);
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
    //��д��ɾ�Ĺ�������
    public static int execute(Connection connection,String sql,Object[] parms,PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i <parms.length ; i++) {
            preparedStatement.setObject(i+1,parms[i]);
        }
        int updateRows = preparedStatement.executeUpdate();
        return updateRows;
    }
    //�ͷ���Դ
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
