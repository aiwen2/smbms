# 超市订单管理系统-SMBMS

## 1、项目结构

![](https://gitee.com/elvinw/img/raw/master/smbsm结构图.jpg)

![](https://gitee.com/elvinw/img/raw/master/smbms数据库结构.png)

## 2、搭建步骤

### 2.1、搭建一个maven web项目

### 2.2、配置Tomcat

### 2.3、导入项目中使用的jar包

```xml
<dependencies>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
    </dependency>
    <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>javax.servlet.jsp-api</artifactId>
      <version>2.3.1</version>
    </dependency>
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.23</version>
    </dependency>
    <dependency>
      <groupId>javax.servlet.jsp.jstl</groupId>
      <artifactId>jstl-api</artifactId>
      <version>1.2</version>
    </dependency>
    <dependency>
      <groupId>taglibs</groupId>
      <artifactId>standard</artifactId>
      <version>1.1.2</version>
    </dependency>
  </dependencies>
```

### 2.4、创建项目包结构

![](https://gitee.com/elvinw/img/raw/master/smbms项目包结构.png)

### 2.5、编写实体类

**ORM映射：表-类映射**

- Bill.java

  ```java
  package com.sanmu.pojo;
  
  import java.math.BigDecimal;
  import java.util.Date;
  
  /**
   * Author：三木
   * Date：2022-04-24 15:07
   * Description：<描述>
   */
  public class Bill {
      private Integer id;//id
      private String billCode;//账单编码
      private String productName;//商品名称
      private String productDesc;//商品描述
      private String productUnit;//商品单位
      private BigDecimal productCount;//商品数量
      private BigDecimal totalPrice;//总金额
      private Integer isPayment;//是否支付
      private Integer createdBy;//创建者
      private Date creationDate;//创建时间
      private Integer modifyBy;//更新者
      private Date modifyDate;//更新时间
      private Integer providerId;//供应商id
  
      private String providerName;//供应商名称
  
      public Integer getId() {
          return id;
      }
  
      public void setId(Integer id) {
          this.id = id;
      }
  
      public String getBillCode() {
          return billCode;
      }
  
      public void setBillCode(String billCode) {
          this.billCode = billCode;
      }
  
      public String getProductName() {
          return productName;
      }
  
      public void setProductName(String productName) {
          this.productName = productName;
      }
  
      public String getProductDesc() {
          return productDesc;
      }
  
      public void setProductDesc(String productDesc) {
          this.productDesc = productDesc;
      }
  
      public String getProductUnit() {
          return productUnit;
      }
  
      public void setProductUnit(String productUnit) {
          this.productUnit = productUnit;
      }
  
      public BigDecimal getProductCount() {
          return productCount;
      }
  
      public void setProductCount(BigDecimal productCount) {
          this.productCount = productCount;
      }
  
      public BigDecimal getTotalPrice() {
          return totalPrice;
      }
  
      public void setTotalPrice(BigDecimal totalPrice) {
          this.totalPrice = totalPrice;
      }
  
      public Integer getIsPayment() {
          return isPayment;
      }
  
      public void setIsPayment(Integer isPayment) {
          this.isPayment = isPayment;
      }
  
      public Integer getCreatedBy() {
          return createdBy;
      }
  
      public void setCreatedBy(Integer createdBy) {
          this.createdBy = createdBy;
      }
  
      public Date getCreationDate() {
          return creationDate;
      }
  
      public void setCreationDate(Date creationDate) {
          this.creationDate = creationDate;
      }
  
      public Integer getModifyBy() {
          return modifyBy;
      }
  
      public void setModifyBy(Integer modifyBy) {
          this.modifyBy = modifyBy;
      }
  
      public Date getModifyDate() {
          return modifyDate;
      }
  
      public void setModifyDate(Date modifyDate) {
          this.modifyDate = modifyDate;
      }
  
      public Integer getProviderId() {
          return providerId;
      }
  
      public void setProviderId(Integer providerId) {
          this.providerId = providerId;
      }
  
      public String getProviderName() {
          return providerName;
      }
  
      public void setProviderName(String providerName) {
          this.providerName = providerName;
      }
  }
  
  ```

- provider.java

  ```java
  package com.sanmu.pojo;
  
  import java.util.Date;
  
  /**
   * Author：三木
   * Date：2022-04-24 15:08
   * Description：<描述>
   */
  public class provider {
      private Integer id;//id
      private String proCode;//供应商编码
      private String proName;//供应商名称
      private String proDesc;//供应商描述
      private String proContact;//供应商联系人
      private String proPhone;//供应商电话
      private String proAddress;//供应商地址
      private String proFax;//供应商传真
      private Integer createdBy;//创建者
      private Date creationDate;//创建时间
      private Integer modifyBy;//更新者
      private Date modifyDate;//更新时间
  
      public Integer getId() {
          return id;
      }
  
      public void setId(Integer id) {
          this.id = id;
      }
  
      public String getProCode() {
          return proCode;
      }
  
      public void setProCode(String proCode) {
          this.proCode = proCode;
      }
  
      public String getProName() {
          return proName;
      }
  
      public void setProName(String proName) {
          this.proName = proName;
      }
  
      public String getProDesc() {
          return proDesc;
      }
  
      public void setProDesc(String proDesc) {
          this.proDesc = proDesc;
      }
  
      public String getProContact() {
          return proContact;
      }
  
      public void setProContact(String proContact) {
          this.proContact = proContact;
      }
  
      public String getProPhone() {
          return proPhone;
      }
  
      public void setProPhone(String proPhone) {
          this.proPhone = proPhone;
      }
  
      public String getProAddress() {
          return proAddress;
      }
  
      public void setProAddress(String proAddress) {
          this.proAddress = proAddress;
      }
  
      public String getProFax() {
          return proFax;
      }
  
      public void setProFax(String proFax) {
          this.proFax = proFax;
      }
  
      public Integer getCreatedBy() {
          return createdBy;
      }
  
      public void setCreatedBy(Integer createdBy) {
          this.createdBy = createdBy;
      }
  
      public Date getCreationDate() {
          return creationDate;
      }
  
      public void setCreationDate(Date creationDate) {
          this.creationDate = creationDate;
      }
  
      public Integer getModifyBy() {
          return modifyBy;
      }
  
      public void setModifyBy(Integer modifyBy) {
          this.modifyBy = modifyBy;
      }
  
      public Date getModifyDate() {
          return modifyDate;
      }
  
      public void setModifyDate(Date modifyDate) {
          this.modifyDate = modifyDate;
      }
  }
  
  ```

- Role.java

  ```java
  package com.sanmu.pojo;
  
  import java.util.Date;
  
  /**
   * Author：三木
   * Date：2022-04-24 15:08
   * Description：<角色类>
   */
  public class Role {
      private Integer id;//id
      private String roleCode;//角色编码
      private String roleName;//角色名称
      private Integer createdBy;//创造者
      private Date creationDate;//创建时间
      private Integer modifyBy;//更新者
      private Date modifyDate;//更新时间
  
      public Integer getId() {
          return id;
      }
  
      public void setId(Integer id) {
          this.id = id;
      }
  
      public String getRoleCode() {
          return roleCode;
      }
  
      public void setRoleCode(String roleCode) {
          this.roleCode = roleCode;
      }
  
      public String getRoleName() {
          return roleName;
      }
  
      public void setRoleName(String roleName) {
          this.roleName = roleName;
      }
  
      public Integer getCreatedBy() {
          return createdBy;
      }
  
      public void setCreatedBy(Integer createdBy) {
          this.createdBy = createdBy;
      }
  
      public Date getCreationDate() {
          return creationDate;
      }
  
      public void setCreationDate(Date creationDate) {
          this.creationDate = creationDate;
      }
  
      public Integer getModifyBy() {
          return modifyBy;
      }
  
      public void setModifyBy(Integer modifyBy) {
          this.modifyBy = modifyBy;
      }
  
      public Date getModifyDate() {
          return modifyDate;
      }
  
      public void setModifyDate(Date modifyDate) {
          this.modifyDate = modifyDate;
      }
  }
  
  ```

- User.java

  ```java
  package com.sanmu.pojo;
  
  import java.util.Date;
  
  /**
   * Author：三木
   * Date：2022-04-24 15:08
   * Description：<用户类>
   */
  public class User {
      private Integer id;//id
      private String userCode;//用户编码
      private String userName;//用户名称
      private String userPassword;//用户密码
      private Integer gender;//性别
      private Date birthday;//出生日期
      private String phone;//电话
      private String address;//地址
      private Integer userRole;//用户角色
      private Integer createdBy;//创建者
      private Date creationDate;//创建时间
      private Integer modifyBy;//更新者
      private Date modifyDate;//更新时间
  
      private Integer age;//年龄
      private String userRoleName;//用户角色名称
  
      public Integer getId() {
          return id;
      }
  
      public void setId(Integer id) {
          this.id = id;
      }
  
      public String getUserCode() {
          return userCode;
      }
  
      public void setUserCode(String userCode) {
          this.userCode = userCode;
      }
  
      public String getUserName() {
          return userName;
      }
  
      public void setUserName(String userName) {
          this.userName = userName;
      }
  
      public String getUserPassword() {
          return userPassword;
      }
  
      public void setUserPassword(String userPassword) {
          this.userPassword = userPassword;
      }
  
      public Integer getGender() {
          return gender;
      }
  
      public void setGender(Integer gender) {
          this.gender = gender;
      }
  
      public Date getBirthday() {
          return birthday;
      }
  
      public void setBirthday(Date birthday) {
          this.birthday = birthday;
      }
  
      public String getPhone() {
          return phone;
      }
  
      public void setPhone(String phone) {
          this.phone = phone;
      }
  
      public String getAddress() {
          return address;
      }
  
      public void setAddress(String address) {
          this.address = address;
      }
  
      public Integer getUserRole() {
          return userRole;
      }
  
      public void setUserRole(Integer userRole) {
          this.userRole = userRole;
      }
  
      public Integer getCreatedBy() {
          return createdBy;
      }
  
      public void setCreatedBy(Integer createdBy) {
          this.createdBy = createdBy;
      }
  
      public Date getCreationDate() {
          return creationDate;
      }
  
      public void setCreationDate(Date creationDate) {
          this.creationDate = creationDate;
      }
  
      public Integer getModifyBy() {
          return modifyBy;
      }
  
      public void setModifyBy(Integer modifyBy) {
          this.modifyBy = modifyBy;
      }
  
      public Date getModifyDate() {
          return modifyDate;
      }
  
      public void setModifyDate(Date modifyDate) {
          this.modifyDate = modifyDate;
      }
  
      public Integer getAge() {
          return age;
      }
  
      public void setAge(Integer age) {
          this.age = age;
      }
  
      public String getUserRoleName() {
          return userRoleName;
      }
  
      public void setUserRoleName(String userRoleName) {
          this.userRoleName = userRoleName;
      }
  }
  ```

### 2.6、编写基础公共类

- 数据库配置文件 db.properties

  ```properties
  driver=com.mysql.cj.jdbc.Driver
  url=jdbc:mysql://192.168.171.3:3306/smbms?useUnicode=true&charcterEncoding=utf-8
  username=root
  password=123456
  ```

- 编写数据库的公共类 BaseDao.java

  ```java
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
      public static ResultSet execute1(Connection connection,String sql,Object[] parms,PreparedStatement preparedStatement) throws SQLException {
          //预编译的sql，在后面直接执行就可以了
           preparedStatement = connection.prepareStatement(sql);
          for (int i = 0; i < parms.length; i++) {
              //setObject，占位符从1开始，但是数组从0开始
              preparedStatement.setObject(i+1,parms[i]);
          }
          ResultSet resultSet = preparedStatement.executeQuery();
          return resultSet;
      }
      //编写增删改公共方法
      public static int execute2(Connection connection,String sql,Object[] parms,PreparedStatement preparedStatement) throws SQLException {
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
  
  ```

- 编写字符编码过滤器

  ```java
  package com.sanmu.filter;
  
  import javax.servlet.*;
  import java.io.IOException;
  
  /**
   * Author：三木
   * Date：2022-04-24 20:07
   * Description：<描述>
   */
  public class CharacterEncodingFilter implements Filter {
      @Override
      public void init(FilterConfig filterConfig) throws ServletException {
          Filter.super.init(filterConfig);
      }
  
      @Override
      public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
          servletRequest.setCharacterEncoding("utf-8");
          servletResponse.setCharacterEncoding("utf-8");
          filterChain.doFilter(servletRequest, servletResponse);
      }
  
      @Override
      public void destroy() {
          Filter.super.destroy();
      }
  }
  ```

  ```xml
  <!-- 字符编码过滤器-->
      <filter>
          <filter-name>CharacterEncodingFilter</filter-name>
          <filter-class>com.sanmu.filter.CharacterEncodingFilter</filter-class>
      </filter>
      <filter-mapping>
          <filter-name>CharacterEncodingFilter</filter-name>
          <url-pattern>/*</url-pattern>
      </filter-mapping>
  ```

### 2.7、导入静态资源

![](https://gitee.com/elvinw/img/raw/master/smbms静态资源目录.png)

## 3、登入功能实现

![](https://gitee.com/elvinw/img/raw/master/smbms登入流程.png)

![](https://gitee.com/elvinw/img/raw/master/smbms登入类图.jpg)

### 3.1、设置欢迎页面-web.xml

```xml
<!--设置欢迎页面-->
<welcome-file-list>
    <welcome-file>login.jsp</welcome-file>
</welcome-file-list>
```

### 3.2、编写dao层-得到登入用户的接口

![](https://gitee.com/elvinw/img/raw/master/smbms-dao层.png)

```java
package com.sanmu.dao.user;

import com.sanmu.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author：三木
 * Date：2022-04-25 15:16
 * Description：<描述>
 */
public interface UserDao {
    //得到要登入的用户
    public User getLoginUser(Connection connection,String userCode,String password) throws SQLException;
}
```

```java
package com.sanmu.dao.user;

import com.sanmu.dao.BaseDao;
import com.sanmu.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            String sql="select * from smbms_user where userCode=?&&userPassword=?";//查询语句
            Object[] prams={userCode,password};
            //查询用户,返回结果集
            rs = BaseDao.execute(connection, sql, prams, rs, preparedStatement);
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
}
```

### 3.3、编写业务层接口

```java
package com.sanmu.service.user;

import com.sanmu.pojo.User;

/**
 * Author：三木
 * Date：2022-04-25 15:51
 * Description：<业务层接口>
 */
public interface UserService {
    //用户登入
    public User login(String userCode,String password);
}

```

### 3.4、编写业务层实现类

```java
package com.sanmu.service.user;

import com.sanmu.dao.BaseDao;
import com.sanmu.dao.user.UserDao;
import com.sanmu.dao.user.UserDaoImpl;
import com.sanmu.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author：三木
 * Date：2022-04-25 15:53
 * Description：<业务层实现类>
 */
public class UserServiceImpl implements UserService{
    //业务层都会调用dao层,所以要引用dao层
    private UserDao userDao;
    public UserServiceImpl(){
        userDao=new UserDaoImpl();
    }

    @Override
    public User login(String userCode, String password) {
        Connection connection=null;
        User user=null;

        try {
            connection= BaseDao.getConnection();
            //通过业务层调用对应的具体的数据库操作
            user = userDao.getLoginUser(connection, userCode);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }

        return user;
    }

    //测试
    @Test
    public void test() {
        UserServiceImpl userService=new UserServiceImpl();
        User admin=userService.login("admin","123456");
        System.out.println(admin.getUserPassword());
    }

}
```

### 3.5、编写常量类

```java
package com.sanmu.util;

/**
 * Author：三木
 * Date：2022-04-25 16:40
 * Description：<常量类>
 */
public class Constants {
    public final static String USER_SESSION="userSession";
}
```

### 3.6、编写servlet

```java
package com.sanmu.servlet.user;

import com.sanmu.pojo.User;
import com.sanmu.service.user.UserService;
import com.sanmu.service.user.UserServiceImpl;
import com.sanmu.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Author：三木
 * Date：2022-04-25 16:30
 * Description：<描述>
 */
public class LoginServlet extends HttpServlet {
    //Servlet:控制层,调用业务层代码
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("登入服务开始");
        //获取用户名和密码
        String username=req.getParameter("userCode");
        String password=req.getParameter("userPassword");
        //和数据库中的密码进行对比,调用业务层
        UserService userService=new UserServiceImpl();
        User login = userService.login(username, password);//把登入的用户查出
        if(login!=null){//查有此人
            //将用户的信息放到Session中
            req.getSession().setAttribute(Constants.USER_SESSION,login);
            //跳转到内部页面
            resp.sendRedirect("jsp/frame.jsp");
        }else{
            //转发回登入页面,并提示用户名或密码错误
            req.setAttribute("error","用户名或者密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
```

```xml
<!--Servlet-->
    <servlet>
        <servlet-name>LoginServlet</servlet-name>
        <servlet-class>com.sanmu.servlet.user.LoginServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>LoginServlet</servlet-name>
        <url-pattern>/login.do</url-pattern>
    </servlet-mapping>
```

### 3.7、测试成功

## 4、注销功能实现

> 思路：移除Session，返回登入页面

### 4.1、编写LogoutServlet类

```java
package com.sanmu.servlet.user;

import com.sanmu.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author：三木
 * Date：2022-04-26 10:59
 * Description：<注销Servlet>
 */
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //移除用户的Constants.USER_SESSION
        req.getSession().removeAttribute(Constants.USER_SESSION);
        resp.sendRedirect(req.getContextPath()+"/login.jsp");//返回登入页面
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

```xml
<!--注销Servlet-->
<servlet>
    <servlet-name>LogoutServlet</servlet-name>
    <servlet-class>com.sanmu.servlet.user.LogoutServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>LogoutServlet</servlet-name>
    <url-pattern>/jsp/logout.do</url-pattern>
</servlet-mapping>
```

### 4.2、编写过滤器

```java
package com.sanmu.filter;

import com.sanmu.pojo.User;
import com.sanmu.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author：三木
 * Date：2022-04-26 11:06
 * Description：<描述>
 */
public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //过滤器，从Session中获取用户
        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
        if(user==null){
            response.sendRedirect("/smbms/error.jsp");
        }else{
            filterChain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
```

```xml
<!--用户登入过滤器-->
<filter>
    <filter-name>SysFilter</filter-name>
    <filter-class>com.sanmu.filter.SysFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>SysFilter</filter-name>
    <url-pattern>/jsp/*</url-pattern>
</filter-mapping>
```

### 4.3、测试成功

## 5、密码修改页面实现

### 5.1、编写UserDao接口

```java
//修改当前用户密码
public int updatePwd(Connection connection,int id,String password);
```

### 5.2、编写UserDaoImpl类，实现UserDao接口

```java
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
```

### 5.3、编写UserService接口

```java
//根据用户id修改密码
public boolean updatePwd(int id,String password);
```

### 5.4、编写UserServiceImpl类，实现UserService接口

```java
//根据用户id修改密码
@Override
public boolean updatePwd(int id, String password) {
    Connection connection=null;
    boolean flag=false;
    connection = BaseDao.getConnection();
    if(userDao.updatePwd(connection, id, password)>0){
        flag=true;
    }
    BaseDao.closeResource(connection,null,null);
    return flag;
}
```

### 5.5、编写UserServlet，继承HttpServlet

> 通过Ajax验证旧密码

```java
package com.sanmu.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.JsonArray;
import com.sanmu.pojo.User;
import com.sanmu.service.user.UserServiceImpl;
import com.sanmu.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Author：三木
 * Date：2022-04-26 15:35
 * Description：<修改密码Servlet层>
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");
        if(method.equals("savepwd")&&method!=null){
            this.updatePwd(req,resp);
        }else if(method.equals("pwdmodify")&&method!=null){
            this.pwdModify(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    //Servlet复用，用户有多个功能，都要使用UserServlet
    //添加新密码
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从Session里面那id
        Object attribute = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");
        boolean flag=false;
        if(attribute!=null&&newpassword!=null){
            UserServiceImpl userService = new UserServiceImpl();
            userService.updatePwd(((User)attribute).getId(),newpassword);
            if(flag){
                req.setAttribute("method","密码修改成功，请退出重新登入");
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else{
                req.setAttribute("method","密码修改失败");
            }
        }else {
            req.setAttribute("method","新密码有问题");
        }
        req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
    }
    //验证旧密码，Session中有用户的密码
    public void pwdModify(HttpServletRequest req, HttpServletResponse resp){
        //从Session里面那id
        Object attribute = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");
        //万能的Map，resultMap结果集
        Map<String,String> resultMap = new HashMap<String,String>();
        if(attribute==null){//Session为空，Session过期
            resultMap.put("result","sessionerror");
        }else if(StringUtils.isNullOrEmpty(oldpassword)){//输入的密码为空
            resultMap.put("result","error");
        }else {
            String userPassword =((User)attribute).getUserPassword();//Session中用户的密码
            if(oldpassword.equals(userPassword)){
                resultMap.put("result","true");
            }else{
                resultMap.put("result","false");
            }
        }

        try {
            resp.setContentType("application/json");//响应方式为json
            PrintWriter writer = resp.getWriter();
            /*JSONArray 阿里巴巴的JSON工具类，转换格式
               resultMap=["result","sessionerror","result","true"]
               json格式={key,value}
             */
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();//刷新
            writer.close();//关闭
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
```

```xml
<!--修改密码-->
<servlet>
    <servlet-name>UserServlet</servlet-name>
    <servlet-class>com.sanmu.servlet.user.UserServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>UserServlet</servlet-name>
    <url-pattern>/jsp/user.do</url-pattern>
</servlet-mapping>
```

![](https://gitee.com/elvinw/img/raw/master/smbms修改密码.png)
![](https://gitee.com/elvinw/img/raw/master/smbms修改密码1.png)

### 5.6、测试成功

## 6、用户管理实现

**思路**

![](https://gitee.com/elvinw/img/raw/master/smbms用户管理.png)

**准备**

- 导入分页的工具类 PageSupport.java
- 用户列表页面导入  userlist.jsp rollpage.jsp

### 6.1、获取用户数量

- 编写UserDao接口

```java
//根据用户名或者角色查询用户总数
public int getUserCount(Connection connection,String username,int userRole) throws SQLException;
```

- 编写UserDaoImpl类，实现UserDao接口

```java
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
```

- 编写UserService接口

```java
//查询记录数
public int getUserCount(String username,int userRole);
```

- 编写UserServiceImpl类，实现UserService接口

```java
//查询记录数
@Override
public int getUserCount(String username, int userRole) {
    Connection connection=null;
    int count=0;

    try {
        connection = BaseDao.getConnection();
        count=userDao.getUserCount(connection,username,userRole);
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }finally {
        BaseDao.closeResource(connection,null,null);
    }
    return count;
}
```

### 6.2、获取用户列表

- 编写UserDao接口

  ```java
  //通过条件查询用户列表-serList
  public List<User> getUserList(Connection connection, String username, int userRole, int currentPageNo, int pageSize) throws SQLException;
  ```

- 编写UserDaoImpl类，实现UserDao接口

  ```java
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
          sql.append(" order by creationBate DESC limit ?,?");
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
              user.setUserName(rs.getString("username"));
              user.setGender(rs.getInt("gender"));
              user.setBirthday(rs.getDate("birthday"));
              user.setPhone(rs.getString("phone"));
              user.setUserRole(rs.getInt("userRole"));
              user.setUserRoleName(rs.getString("roleName"));
              userlist.add(user);
          }
          BaseDao.closeResource(connection,rs,pstm);
      }
      return userlist;
  }
  ```

- 编写UserService接口

  ```java
  //根据条件查询用户列表
  public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);
  ```

- 编写UserServiceImpl类，实现UserService接口

  ```java
  //根据条件查询用户列表
  @Override
  public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) {
      Connection connection=null;
  
      List<User> userList=new ArrayList<>();
      System.out.println("queryUserName:"+queryUserName);
      System.out.println("queryUserRole:"+queryUserRole);
      System.out.println("currentPageNo:"+currentPageNo);
      System.out.println("pageSize:"+pageSize);
      try {
          connection = BaseDao.getConnection();
          userList=userDao.getUserList(connection,queryUserName,queryUserRole,currentPageNo,pageSize);
      } catch (SQLException throwables) {
          throwables.printStackTrace();
      }finally {
          BaseDao.closeResource(connection,null,null);
      }
      return userList;
  
  }
  ```

### 6.3、获取角色操作

> 为了职责统一，可以把角色的操作单独放在一个包，和实体类对应

- 编写RoleDao接口

  ```java
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
  ```

- 编写RoleDaoImpl类，实现UserService接口

  ```java
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
  ```

- 编写RoleService接口

  ```java
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
  ```

- 编写RoleServiceImpl类，实现RoleService接口

  ```java
  package com.sanmu.service.role;
  
  import com.sanmu.dao.BaseDao;
  import com.sanmu.dao.role.RoleDao;
  import com.sanmu.dao.role.RoleDaoImpl;
  import com.sanmu.pojo.Role;
  
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
  }
  ```

### 6.4、用户显示的Servlet

**步骤**

- 获取用户前端的数据
- 判断请求是否需要执行，看参数的值判断
- 为了实现分页，需要计算出当前页面和总页面。要分几页=总页数/展示页数+1
- 用户列表展示
- 返回前端

```java
package com.sanmu.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.JsonArray;
import com.sanmu.pojo.Role;
import com.sanmu.pojo.User;
import com.sanmu.service.role.RoleServieImpl;
import com.sanmu.service.user.UserServiceImpl;
import com.sanmu.util.Constants;
import com.sanmu.util.PageSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author：三木
 * Date：2022-04-26 15:35
 * Description：<Servlet层>
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");
        if(method.equals("savepwd")&&method!=null){
            this.updatePwd(req,resp);
        }else if(method.equals("pwdmodify")&&method!=null){
            this.pwdModify(req,resp);
        }else if(method.equals("query")&&method!=null){
            this.query(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    //Servlet复用，用户有多个功能，都要使用UserServlet
    //添加新密码
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从Session里面那id
        Object attribute = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");
        boolean flag=false;
        if(attribute!=null&&newpassword!=null){
            UserServiceImpl userService = new UserServiceImpl();
            userService.updatePwd(((User)attribute).getId(),newpassword);
            if(flag){
                req.setAttribute("method","密码修改成功，请退出重新登入");
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else{
                req.setAttribute("method","密码修改失败");
            }
        }else {
            req.setAttribute("method","新密码有问题");
        }
        req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
    }
    //验证旧密码，Session中有用户的密码
    public void pwdModify(HttpServletRequest req, HttpServletResponse resp){
        //从Session里面那id
        Object attribute = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");
        //万能的Map，resultMap结果集
        Map<String,String> resultMap = new HashMap<String,String>();
        if(attribute==null){//Session为空，Session过期
            resultMap.put("result","sessionerror");
        }else if(StringUtils.isNullOrEmpty(oldpassword)){//输入的密码为空
            resultMap.put("result","error");
        }else {
            String userPassword =((User)attribute).getUserPassword();//Session中用户的密码
            if(oldpassword.equals(userPassword)){
                resultMap.put("result","true");
            }else{
                resultMap.put("result","false");
            }
        }

        try {
            resp.setContentType("application/json");//响应方式为json
            PrintWriter writer = resp.getWriter();
            /*JSONArray 阿里巴巴的JSON工具类，转换格式
               resultMap=["result","sessionerror","result","true"]
               json格式={key,value}
             */
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();//刷新
            writer.close();//关闭
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //用户管理
    public void query(HttpServletRequest req, HttpServletResponse resp){
        //从前端获取数据
        String queryname = req.getParameter("queryname");//用户名
        String temp = req.getParameter("queryUserRole");//用户角色
        String pageIndex = req.getParameter("pageIndex");
        int queryUserRole=0;
        //获取用户列表
        UserServiceImpl userService = new UserServiceImpl();
        List<User> userList=null;
        //第一次走这个请求，一定是第一页，页面大小固定
        int pageSize=5;//页面大小，可以放在配置文件，便于修改
        int currentPageNo=1;//记录条数，当前页码

        if(queryname==null){
            queryname="";
        }
        if(temp!=null&&!temp.equals("")){
            queryUserRole=Integer.parseInt(temp);//给查询赋值
        }
        if(pageIndex!=null){
            currentPageNo=Integer.parseInt(pageIndex);
        }
        //获取用户总数（分页：上一页，下一页）
        int totalCount = userService.getUserCount(queryname, queryUserRole);//总页数
        PageSupport pageSupport = new PageSupport();//分页工具类
        pageSupport.setPageSize(pageSize);
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setTotalCount(totalCount);

        int totalPageCount = pageSupport.getTotalPageCount();//可分几页
        //控制首页和尾页
        //如果页面要小于1，就显示第一页
        if(currentPageNo<1){
            currentPageNo=1;
        }else if(currentPageNo>totalPageCount){//当前页面大于最后一页
            currentPageNo=totalPageCount;
        }
        //获取用户列表展示
        userList = userService.getUserList(queryname, queryUserRole, currentPageNo, pageSize);
        req.setAttribute("userList",userList);

        //获取用户角色列表
        RoleServieImpl roleServie = new RoleServieImpl();
        List<Role> roleList = roleServie.getRoleList();
        req.setAttribute("roleList",roleList);
        req.setAttribute("totalCount",totalCount);
        req.setAttribute("currentPageNo",currentPageNo);
        req.setAttribute("totalPageCount",totalPageCount);

        try {
            req.getRequestDispatcher("userlist.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## 7、订单管理实现

**准备**

- 导入前端页面：billlist.jsp

### 7.1、查询订单列表

- 编写BillDao接口

  ```java
  package com.sanmu.dao.bill;
  
  import com.sanmu.pojo.Bill;
  
  import java.sql.Connection;
  import java.sql.SQLException;
  import java.util.List;
  
  /**
   * Author：三木
   * Date：2022-05-03 14:09
   * Description：<订单管理>
   */
  public interface BillDao {
      //通过条件查询订单列表
      public List<Bill> getBillList(Connection connection,String productName,int proName,int isPayment) throws SQLException;
  }
  ```

- 编写BillDaoImpl类，实现BillDao接口

  ```java
  package com.sanmu.dao.bill;
  
  import com.mysql.cj.util.StringUtils;
  import com.sanmu.dao.BaseDao;
  import com.sanmu.pojo.Bill;
  
  import java.sql.Connection;
  import java.sql.PreparedStatement;
  import java.sql.ResultSet;
  import java.sql.SQLException;
  import java.util.ArrayList;
  import java.util.List;
  
  /**
   * Author：三木
   * Date：2022-05-03 14:32
   * Description：<获取订单列表>
   */
  public class BillDaoImpl implements BillDao{
      @Override
      public List<Bill> getBillList(Connection connection, String productName, int proName, int isPayment) throws SQLException {
  
          PreparedStatement pstm=null;
          ResultSet rs=null;
          List<Bill> billlist=new ArrayList<>();
          if(connection!=null){
              StringBuffer sql=new StringBuffer();
              sql.append("SELECT b.*,p.proName as providerName FROM smbms_bill b,smbms_provider p WHERE b.providerId=p.id");
              List<Object> list=new ArrayList<>();
              if(!StringUtils.isNullOrEmpty(productName)){
                  sql.append(" and b.productName like ?");
                  list.add("%"+productName+"%");
              }
              if(proName>0){
                  sql.append(" and p.id = ?");
                  list.add(proName);
              }
              if(isPayment>0){
                  sql.append(" and b.isPayment = ?");
                  list.add(isPayment);
              }
              Object[] parms=list.toArray();
              System.out.println("sql语句："+sql.toString());
              rs = BaseDao.execute(connection, sql.toString(), parms, rs, pstm);
              while (rs.next()){
                  Bill bill=new Bill();
                  bill.setId(rs.getInt("id"));
                  bill.setBillCode(rs.getString("billCode"));
                  bill.setProductName(rs.getString("productName"));
                  bill.setProductDesc(rs.getString("productDesc"));
                  bill.setProductCount(rs.getBigDecimal("productCount"));
                  bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
                  bill.setIsPayment(rs.getInt("isPayment"));
                  bill.setCreatedBy(rs.getInt("createdBy"));
                  bill.setCreationDate(rs.getDate("creationDate"));
                  bill.setModifyBy(rs.getInt("modifyBy"));
                  bill.setModifyDate(rs.getDate("modifyDate"));
                  bill.setProviderId(rs.getInt("providerId"));
                  bill.setProviderName(rs.getString("providerName"));
                  billlist.add(bill);
              }
              BaseDao.closeResource(connection,rs,pstm);
          }
          return billlist;
      }
  }
  ```

- 编写BillService接口

  ```java
  package com.sanmu.service.bill;
  
  import com.sanmu.pojo.Bill;
  
  import java.sql.Connection;
  import java.util.List;
  
  /**
   * Author：三木
   * Date：2022-05-03 16:03
   * Description：<描述>
   */
  public interface BillService {
      //获取订单列表
      public List<Bill> getBillList(String productName, int proName, int isPayment);
  }
  ```

- 编写BillServiceImpl类，实现BillService接口

  ```java
  package com.sanmu.service.bill;
  
  import com.sanmu.dao.BaseDao;
  import com.sanmu.dao.bill.BillDao;
  import com.sanmu.dao.bill.BillDaoImpl;
  import com.sanmu.dao.provider.ProviderDao;
  import com.sanmu.dao.provider.ProviderDaoImpl;
  import com.sanmu.pojo.Bill;
  import com.sanmu.pojo.Provider;
  import com.sanmu.pojo.User;
  import org.junit.Test;
  
  import java.sql.Connection;
  import java.sql.SQLException;
  import java.text.Bidi;
  import java.util.ArrayList;
  import java.util.List;
  
  /**
   * Author：三木
   * Date：2022-05-03 16:05
   * Description：<描述>
   */
  public class BillServiceImpl implements BillService{
      BillDao billDao;
      public BillServiceImpl() {
          billDao=new BillDaoImpl();
      }
  
      //获取订单列表
      @Override
      public List<Bill> getBillList(String productName, int proName, int isPayment) {
          Connection connection=null;
          List<Bill> billList=new ArrayList<>();
          System.out.println("productName:"+productName);
          System.out.println("proName:"+proName);
          System.out.println("isPayment:"+isPayment);
  
          try {
              connection = BaseDao.getConnection();
               billList= billDao.getBillList(connection, productName, proName, isPayment);
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }finally {
              BaseDao.closeResource(connection,null,null);
          }
          return billList;
      }
  }
  ```

### 7.2、获取供应商名称

- 编写ProviderDao接口

  ```java
  package com.sanmu.dao.provider;
  
  import com.sanmu.pojo.Provider;
  
  import java.sql.Connection;
  import java.sql.SQLException;
  import java.util.List;
  
  /**
   * Author：三木
   * Date：2022-05-03 15:15
   * Description：<描述>
   */
  public interface ProviderDao {
      //获取供应商列表
      public List<Provider> getProviderName(Connection connection) throws SQLException;
  }
  ```

- 编写ProviderDaoImpl类，实现ProviderDao接口

  ```java
  package com.sanmu.dao.provider;
  
  import com.sanmu.dao.BaseDao;
  import com.sanmu.pojo.Provider;
  
  import java.sql.Connection;
  import java.sql.PreparedStatement;
  import java.sql.ResultSet;
  import java.sql.SQLException;
  import java.util.ArrayList;
  import java.util.List;
  
  /**
   * Author：三木
   * Date：2022-05-03 15:27
   * Description：<描述>
   */
  public class ProviderDaoImpl implements ProviderDao{
      //获取供应商名称
      @Override
      public List<Provider> getProviderName(Connection connection) throws SQLException {
          ResultSet rs=null;
          PreparedStatement pstm=null;
          List<Provider> prolist=new ArrayList<>();
          if(connection!=null){
              String sql="select * from smbms_provider";
              Object[] parms={};
              rs = BaseDao.execute(connection, sql, parms, rs, pstm);
              while (rs.next()){
                  Provider provider=new Provider();
                  provider.setId(rs.getInt("id"));
                  provider.setProCode(rs.getString("proCode"));
                  provider.setProName(rs.getString("proName"));
                  prolist.add(provider);
              }
              BaseDao.closeResource(connection,rs,pstm);
          }
          return prolist;
      }
  }
  ```

- 编写ProviderService接口

  ```java
  package com.sanmu.service.provider;
  
  import com.sanmu.pojo.Provider;
  
  import java.sql.Connection;
  import java.util.List;
  
  /**
   * Author：三木
   * Date：2022-05-03 16:44
   * Description：<描述>
   */
  public interface ProviderService {
      //获取供应商名称
      public List<Provider> getProviderName();
  }
  ```

- 编写ProviderServiceImpl类，实现ProviderService接口

  ```java
  package com.sanmu.service.provider;
  
  import com.sanmu.dao.BaseDao;
  import com.sanmu.dao.provider.ProviderDao;
  import com.sanmu.dao.provider.ProviderDaoImpl;
  import com.sanmu.pojo.Provider;
  import org.junit.Test;
  
  import java.sql.Connection;
  import java.sql.SQLException;
  import java.util.List;
  
  /**
   * Author：三木
   * Date：2022-05-03 16:45
   * Description：<描述>
   */
  public class ProviderServiceImpl implements ProviderService{
      ProviderDao providerDao;
  
      public ProviderServiceImpl() {
          providerDao=new ProviderDaoImpl();
      }
      //获取供应商名称
      @Override
      public List<Provider> getProviderName() {
          Connection connection =null;
          List<Provider> proList =null;
          try {
              connection = BaseDao.getConnection();
              proList = providerDao.getProviderName(connection);
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }finally {
              BaseDao.closeResource(connection,null,null);
          }
          return proList;
      }
      @Test
      public void test(){
          ProviderService providerService=new ProviderServiceImpl();
          List<Provider> providerList = providerService.getProviderList();
          for(Provider p:providerList){
              System.out.println(p.getProName());
          }
      }
  }
  ```

### 7.3、编写BillServlet类

```java
package com.sanmu.servlet.bill;

import com.sanmu.pojo.Bill;
import com.sanmu.pojo.Provider;
import com.sanmu.service.bill.BillServiceImpl;
import com.sanmu.service.provider.ProviderService;
import com.sanmu.service.provider.ProviderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Author：三木
 * Date：2022-05-03 16:22
 * Description：<订单>
 */
public class BillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");
        if(method.equals("query")&&method!=null){
            this.query(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    //订单管理
    public void query(HttpServletRequest req, HttpServletResponse resp){
        //从前端获取数据
        String queryProductName = req.getParameter("queryProductName");
        String tempId = req.getParameter("queryProviderId");
        String temp = req.getParameter("queryIsPayment");
        int queryProviderId=0;
        int queryIsPayment=0;
        if(queryProductName==null){
            queryProductName="";
        }
        if(tempId!=null){
            queryProviderId=Integer.parseInt(tempId);
        }
        if(temp!=null){
            queryIsPayment=Integer.parseInt(temp);
        }

        //获取订单列表
        BillServiceImpl billService = new BillServiceImpl();
        List<Bill> billlist=null;
        //获取订单列表展示
        billlist = billService.getBillList(queryProductName, queryProviderId, queryIsPayment);
        req.setAttribute("billList",billlist);
        //获取供应商列表
        ProviderService providerService=new ProviderServiceImpl();
        List<Provider> providerList =null;
        proList = providerService.getProviderName();
        req.setAttribute("providerList",proList);
        try {
            req.getRequestDispatcher("billlist.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
```

### 7.4、注册BillServlet

```xml
<!--web.xml-->
<!--订单Servlet-->
<servlet>
    <servlet-name>BillServlet</servlet-name>
    <servlet-class>com.sanmu.servlet.bill.BillServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>BillServlet</servlet-name>
    <url-pattern>/jsp/bill.do</url-pattern>
</servlet-mapping>
```

## 8、供应商管理实现

### 8.1、查询供应商列表

- 编写ProviderDao接口

  ```java
  //获取供应商列表
  public List<Provider> getProviderList(Connection connection,String queryProCode,String queryProName) throws SQLException;
  ```

- 编写ProviderDaoImpl类，实现ProviderDao接口

  ```java
  //获取供应商列表
  @Override
  public List<Provider> getProviderList(Connection connection,String queryProCode,String queryProName) throws SQLException {
      ResultSet rs=null;
      PreparedStatement pstm=null;
      List<Provider> providerlist=new ArrayList<>();
      if(connection!=null){
          StringBuffer sql=new StringBuffer();
          sql.append("select * from smbms_provider");
          List<Object> list=new ArrayList<>();
          if(!StringUtils.isNullOrEmpty(queryProCode)){
              sql.append(" where proCode like ?");
              list.add(queryProCode);
          }else if(!StringUtils.isNullOrEmpty(queryProName)){
              sql.append(" where proName like ?");
              list.add(queryProName);
          }
          Object[] parms=list.toArray();
          System.out.println("sql语句："+sql.toString());
          rs = BaseDao.execute(connection, sql.toString(), parms, rs, pstm);
          while (rs.next()){
              Provider provider=new Provider();
              provider.setId(rs.getInt("id"));
              provider.setProCode(rs.getString("proCode"));
              provider.setProName(rs.getString("proName"));
              provider.setProContact(rs.getString("proContact"));
              provider.setProPhone(rs.getString("proPhone"));
              provider.setProFax(rs.getString("proFax"));
              provider.setCreationDate(rs.getDate("creationDate"));
              providerlist.add(provider);
          }
          BaseDao.closeResource(connection,rs,pstm);
      }
      return providerlist;
  }
  ```

- 编写ProviderService接口

  ```java
  //获取供应商列表
  public List<Provider> getProviderList(String queryProCode,String queryProName);
  ```

- 编写ProviderServiceImpl类，实现ProviderService接口

  ```java
  //获取供应商列表
  @Override
  public List<Provider> getProviderList(String queryProCode,String queryProName) {
      Connection connection =null;
      List<Provider> providerList =null;
      try {
          connection = BaseDao.getConnection();
          providerList = providerDao.getProviderList(connection,queryProCode,queryProName);
      } catch (SQLException throwables) {
          throwables.printStackTrace();
      }finally {
          BaseDao.closeResource(connection,null,null);
      }
      return providerList;
  }
  ```

### 8.2、编写ProviderServlet类

```java
package com.sanmu.servlet.provider;

import com.sanmu.pojo.Provider;
import com.sanmu.service.provider.ProviderService;
import com.sanmu.service.provider.ProviderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Author：三木
 * Date：2022-05-05 15:35
 * Description：<供应商管理>
 */
public class ProviderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("method");
        if(method.equals("query")&&method!=null){
            this.query(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    //供应商管理
    public void query(HttpServletRequest req, HttpServletResponse resp){
        //获取数据
        String queryProCode=req.getParameter("queryProCode");
        String queryProName=req.getParameter("queryProName");
        if(queryProCode==null){
            queryProCode="";
        }
        if(queryProName==null){
            queryProName="";
        }
        //获取供应商列表
        ProviderServiceImpl providerService = new ProviderServiceImpl();
        List<Provider> providerList =null;
        //获取订单展示
        providerList = providerService.getProviderList(queryProCode, queryProName);
        req.setAttribute("providerList",providerList);

        try {
            req.getRequestDispatcher("providerlist.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
```

### 8.3、注册Servlet

```xml
//供应商Servlet
<servlet>
    <servlet-name>ProviderServlet</servlet-name>
    <servlet-class>com.sanmu.servlet.provider.ProviderServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>ProviderServlet</servlet-name>
    <url-pattern>/jsp/provider.do</url-pattern>
</servlet-mapping>
```

## 9、添加操作实现

**一切的增删改操作都需要处理事务！ACID原则：原子性、一致性、隔离性、持久性。在service层处理事务**

### 9.1、添加用户

- 编写UserDao接口

  ```java
  //添加用户
  public int add(Connection connection, User user) throws SQLException;
  ```

- 编写UserDaoImpl类，实现UserDao接口

  ```java
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
  ```

- 编写UserService接口

  ```java
  //添加用户
  public boolean add(User user);
  //根据用户编码，判断用户是否存在
  public User UserCodeExist(String userCode);
  ```

- 编写UserServiceImpl类，实现UserService接口

  ```java
  //添加用户
  @Override
  public boolean add(User user) {
      boolean flag=false;
      Connection connection=null;
      try {
          connection = BaseDao.getConnection();
          //connection.setAutoCommit(false);//开启jdbc事务
          int updateRows = userDao.add(connection, user);
          //connection.commit();//提交事务
          if(updateRows>0){
              flag=true;
              System.out.println("添加成功");
          }else{
              System.out.println("添加失败");
          }
      } catch (Exception e) {
          e.printStackTrace();
          try {
              System.out.println("回滚事务....");
              connection.rollback();
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }
      }finally {
          BaseDao.closeResource(connection,null,null);
      }
      return flag;
  }
  
  //根据用户编码，判断用户是否存在
  @Override
  public User UserCodeExist(String userCode) {
      Connection connection=null;
      User loginUser=null;
      try {
          connection = BaseDao.getConnection();
          loginUser= userDao.getLoginUser(connection, userCode, null);
      } catch (SQLException throwables) {
          throwables.printStackTrace();
      } finally {
          BaseDao.closeResource(connection,null,null);
      }
      return loginUser;
  }
  ```

- 编写UserServlet类

  ```java
  //添加用户
  private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //获取前端数据
      String userCode=req.getParameter("userCode");
      String userName=req.getParameter("userName");
      String userPassword=req.getParameter("userPassword");
      String gender=req.getParameter("gender");
      String birthday=req.getParameter("birthday");
      String phone=req.getParameter("phone");
      String address=req.getParameter("address");
      String userRole=req.getParameter("userRole");
  
      User user = new User();
      user.setUserCode(userCode);
      user.setUserName(userName);
      user.setUserPassword(userPassword);
      user.setGender(Integer.valueOf(gender));
      try {
          user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
      } catch (ParseException e) {
          e.printStackTrace();
      }
      user.setPhone(phone);
      user.setAddress(address);
      user.setUserRole(Integer.valueOf(userRole));
      user.setCreationDate(new Date());
      user.setCreatedBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
  
      UserServiceImpl userService = new UserServiceImpl();
      if(userService.add(user)){
          //req.getRequestDispatcher("/jsp/user.do").forward(req,resp);
          resp.sendRedirect(req.getContextPath()+"/jsp/user.do?method=query");
      }else{
          req.getRequestDispatcher("useradd.jsp").forward(req,resp);
      }
  }
  
  //得到用户角色表
  private void getRoleList(HttpServletRequest req, HttpServletResponse resp){
      List<Role> roleList = null;
      RoleServieImpl roleServie = new RoleServieImpl();
      roleList=roleServie.getRoleList();
      try {
          //把roleList转换成json对象输出
          resp.setContentType("application/json");//响应方式为json
          PrintWriter writer = resp.getWriter();
          writer.write(JSONArray.toJSONString(roleList));
          writer.flush();//刷新
          writer.close();//关闭
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  
  //根据用户编码，判断用户是否存在
  private void userCodeExist(HttpServletRequest req, HttpServletResponse resp){
      //从前端获取usercode
      String userCode=req.getParameter("userCode");
      //用一个hashmap，暂存现在所有的用户编码
      Map<String,String> resultmap=new HashMap<>();
      if(StringUtils.isNullOrEmpty(userCode)){
          //如果输入的用户编码为空或者不存在，说明可用
          resultmap.put("userCode","exist");
      }else{
          //如果输入的编码不为空，则需要判断是否存在
          UserServiceImpl userService = new UserServiceImpl();
          User user = userService.UserCodeExist(userCode);
          if(user!=null){
              resultmap.put("userCode","exist");
          }else{
              resultmap.put("userCode","notexist");
          }
      }
      try {
          //把roleList转换成json对象输出
          resp.setContentType("application/json");//响应方式为json
          PrintWriter writer = resp.getWriter();
          writer.write(JSONArray.toJSONString(resultmap));
          writer.flush();//刷新
          writer.close();//关闭
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  ```

### 9.2、添加订单

- 编写BillDao接口

  ```java
  //添加订单
  public int addBill(Connection connection,Bill bill);
  ```

- 编写BillDaoImpl类，实现BillDao接口

  ```java
  //添加订单
  @Override
  public int addBill(Connection connection, Bill bill) {
      PreparedStatement pstm=null;
      int rs=0;
      if(connection!=null){
          try {
              String sql="insert into smbms_bill (billCode,productName,productUnit," +
                  "productCount,totalPrice,providerId,isPayment,creationDate,createdBy) " +
                  "values(?,?,?,?,?,?,?,?,?)";
              Object[] parms={bill.getBillCode(),bill.getProductName(),bill.getProductUnit(),
                              bill.getProductCount(),bill.getTotalPrice(),bill.getProviderId(),bill.getIsPayment(),bill.getCreationDate(),bill.getCreatedBy()};
              System.out.println(sql);
              rs = BaseDao.execute(connection, sql, parms, pstm);
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }finally {
              BaseDao.closeResource(connection,null,pstm);
          }
      }
      return rs;
  }
  ```

- 编写BillService接口

  ```java
  //添加订单
  public boolean addBill(Bill bill);
  ```

- 编写BillServiceImpl类，实现BillService接口

  ```java
  //添加订单
  @Override
  public boolean addBill(Bill bill)  {
      Connection connection=null;
      boolean flag=false;
  
      try {
          connection=BaseDao.getConnection();
          //connection.setAutoCommit(false);
          int i = billDao.addBill(connection, bill);
          //connection.commit();
          if(i>0){
              flag=true;
              System.out.println("添加成功");
          }else{
              System.out.println("添加失败");
          }
  
      } catch (Exception throwables) {
          throwables.printStackTrace();
          try {
              System.out.println("事务回滚。。。。");
              connection.rollback();
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }finally {
          BaseDao.closeResource(connection,null,null);
      }
      return flag;
  }
  ```

- 编写BillServlet类

  ```java
  //添加订单
  private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
      //获取数据
      String billCode=req.getParameter("billCode");
      String productName=req.getParameter("productName");
      String productUnit=req.getParameter("productUnit");
      String productCount=req.getParameter("productCount");
      String totalPrice=req.getParameter("totalPrice");
      String providerId=req.getParameter("providerId");
      String isPayment=req.getParameter("isPayment");
  
      Bill bill=new Bill();
      bill.setBillCode(billCode);
      bill.setProductName(productName);
      bill.setProductUnit(productUnit);
      bill.setProductCount(new BigDecimal(productCount));
      bill.setTotalPrice(new BigDecimal(totalPrice));
      bill.setProviderId(Integer.valueOf(providerId));
      bill.setIsPayment(Integer.valueOf(isPayment));
      bill.setCreationDate(new Date());
      bill.setCreatedBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
  
      BillServiceImpl billService = new BillServiceImpl();
      if(billService.addBill(bill)){
          resp.sendRedirect(req.getContextPath()+"/jsp/bill.do?method=query");
      }else{
          req.setAttribute("error","添加失败");
          req.getRequestDispatcher("billadd.jsp").forward(req,resp);
      }
  }
  ```

### 9.3、添加供应商

- 编写ProviderDao接口

  ```java
  //添加供应商
  public int providerAdd(Connection connection,Provider provider) throws SQLException;
  ```

- 编写ProviderDaoImpl类，实现ProviderDao接口

  ```java
  //添加供应商
  @Override
  public int providerAdd(Connection connection, Provider provider) throws SQLException {
      PreparedStatement pstm=null;
      String sql="insert into smbms_provider(proCode,proName,proContact,proPhone,"+
          "proAddress,proFax,proDesc,createdBy,creationDate)"+
          "values(?,?,?,?,?,?,?,?,?)";
      Object[] parms={provider.getProCode(),provider.getProName(),provider.getProContact(),
                      provider.getProPhone(),provider.getProAddress(),provider.getProFax(),
                      provider.getProDesc(),provider.getCreatedBy(),provider.getCreationDate()};
      int execute = BaseDao.execute(connection, sql, parms, pstm);
      System.out.println(sql);
      BaseDao.closeResource(connection,null,pstm);
      return execute;
  }
  ```

- 编写ProviderService接口

  ```java
  //添加供应商
  public boolean providerAdd(Provider provider) throws SQLException;
  ```

- 编写ProviderServiceImpl类，实现ProviderService接口

  ```java
  //添加供应商
  @Override
  public boolean providerAdd(Provider provider)  {
      Connection connection =null;
      boolean flag=false;
      int i = 0;
      try {
          connection= BaseDao.getConnection();
          //connection.setAutoCommit(false);
          i = providerDao.providerAdd(connection, provider);
          //connection.commit();
          if(i>0){
              flag=true;
              System.out.println("添加成功");
          }else {
              System.out.println("添加失败");
          }
      } catch (SQLException throwables) {
          try {
              connection.rollback();
          } catch (SQLException e) {
              e.printStackTrace();
          }
          throwables.printStackTrace();
      }finally {
          BaseDao.closeResource(connection,null,null);
      }
      return flag;
  }
  ```

- 编写ProviderServlet类

  ```java
  //添加供应商
  private void providerAdd(HttpServletRequest req, HttpServletResponse resp) throws  IOException, ServletException {
      //获取前端数据
      String proCode=req.getParameter("proCode");
      String proName=req.getParameter("proName");
      String proContact=req.getParameter("proContact");
      String proPhone=req.getParameter("proPhone");
      String proAddress=req.getParameter("proAddress");
      String proFax=req.getParameter("proFax");
      String proDesc=req.getParameter("proDesc");
  
      Provider provider=new Provider();
      provider.setProCode(proCode);
      provider.setProName(proName);
      provider.setProContact(proContact);
      provider.setProPhone(proPhone);
      provider.setProAddress(proAddress);
      provider.setProFax(proFax);
      provider.setProDesc(proDesc);
      provider.setCreatedBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
      provider.setCreationDate(new Date());
  
      ProviderServiceImpl providerService = new ProviderServiceImpl();
      if(providerService.providerAdd(provider)){
          resp.sendRedirect(req.getContextPath()+"/jsp/provider.do?method=query");
      }else {
          req.getRequestDispatcher("provideradd.jsp").forward(req,resp);
      }
  }
  ```

## 10、查看操作实现

### 10.1、查看用户

- 编写UserDao接口

  ```java
  //通过id得到用户信息
  public User getUserById(Connection connection,String id) throws SQLException;
  ```

- 编写UserDaoImpl类，实现UserDao接口

  ```java
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
  ```

- 编写UserService接口

  ```java
  //通过id得到用户信息
  public User getUserById(String id);
  ```

- 编写UserServlet类

  ```java
  @Override
  public User getUserById(String id) {
      Connection connection=null;
      User userById = null;
      try {
          connection=BaseDao.getConnection();
          userById = userDao.getUserById(connection, id);
      } catch (SQLException throwables) {
          throwables.printStackTrace();
      }finally {
          BaseDao.closeResource(connection,null,null);
      }
  
      return userById;
  }
  
  //测试
  @Test
  public void test() {
      UserServiceImpl userService = new UserServiceImpl();
      boolean b = userService.deleteUserId(30);
      if(b){
          System.out.println("成功");
      }
  }
  ```

### 10.2、查看订单

- 编写BillDao接口

  ```java
  //通过订单id得到该订单的所有信息（正确）
  public Bill getBillById(Connection connection, String id) throws SQLException;
  ```

- 编写BillDaoImpl类，实现BillDao接口

  ```java
  @Override
  public Bill getBillById(Connection connection, String id) throws SQLException {
      Bill bill = null;
      PreparedStatement pstm = null;
      ResultSet rs = null;
      if(null != connection){
          String sql = "select b.*,p.proName as providerName from smbms_bill b, smbms_provider p " +
              "where b.providerId = p.id and b.id=?";
          Object[] params = {id};
          rs = BaseDao.execute(connection,sql, params,rs,pstm);
          if(rs.next()){
              bill = new Bill();
              bill.setId(rs.getInt("id"));
              bill.setBillCode(rs.getString("billCode"));
              bill.setProductName(rs.getString("productName"));
              bill.setProductDesc(rs.getString("productDesc"));
              bill.setProductUnit(rs.getString("productUnit"));
              bill.setProductCount(rs.getBigDecimal("productCount"));
              bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
              bill.setIsPayment(rs.getInt("isPayment"));
              bill.setProviderId(rs.getInt("providerId"));
              bill.setProviderName(rs.getString("providerName"));
              bill.setModifyBy(rs.getInt("modifyBy"));
              bill.setModifyDate(rs.getTimestamp("modifyDate"));
          }
          BaseDao.closeResource(null, rs, pstm);
      }
      return bill;
  }
  ```

- 编写BillService接口

  ```java
  //通过订单id得到该订单的所有信息（正确）
  public Bill getBillById(String id);
  ```

- 编写BillServiceImpl类，实现BillService接口

  ```java
  @Override
  public Bill getBillById(String id) {
      Bill bill = new Bill();
      Connection connection=null;
      try {
          connection=BaseDao.getConnection();
          bill = billDao.getBillById(connection, id);
      } catch (Exception e) {
          e.printStackTrace();
      }finally {
          BaseDao.closeResource(connection,null,null);
      }
      return bill;
  }
  ```

- 编写BillServlet类

  ```java
  private void getBillById(HttpServletRequest request, HttpServletResponse response,String url)
      throws ServletException, IOException {
      String id = request.getParameter("billid");
      if(!StringUtils.isNullOrEmpty(id)){
          BillService billService = new BillServiceImpl();
          Bill bill = null;
          bill = billService.getBillById(id);
          request.setAttribute("bill", bill);
          request.getRequestDispatcher(url).forward(request, response);
      }
  }
  ```

### 10.3、查看供应商

- 编写ProviderDao接口

  ```java
  //通过proId获取Provider
  public Provider getProviderById(Connection connection,String id) throws SQLException;
  ```

- 编写ProviderDaoImpl类，实现ProviderDao接口

  ```java
  //通过proId获取Provider
  public Provider getProviderById(Connection connection, String id) throws SQLException {
      Provider provider = null;
      PreparedStatement pstm = null;
      ResultSet rs = null;
      if(null != connection){
          String sql = "select * from smbms_provider where id=?";
          Object[] params = {id};
          rs = BaseDao.execute(connection, sql, params, rs, pstm);
          if(rs.next()){
              provider = new Provider();
              provider.setId(rs.getInt("id"));
              provider.setProCode(rs.getString("proCode"));
              provider.setProName(rs.getString("proName"));
              provider.setProDesc(rs.getString("proDesc"));
              provider.setProContact(rs.getString("proContact"));
              provider.setProPhone(rs.getString("proPhone"));
              provider.setProAddress(rs.getString("proAddress"));
              provider.setProFax(rs.getString("proFax"));
              provider.setCreatedBy(rs.getInt("createdBy"));
              provider.setCreationDate(rs.getTimestamp("creationDate"));
              provider.setModifyBy(rs.getInt("modifyBy"));
              provider.setModifyDate(rs.getTimestamp("modifyDate"));
          }
          BaseDao.closeResource(null, rs, pstm);
      }
      return provider;
  }
  ```

- 编写ProviderService接口

  ```java
  //通过proId获取Provider
  public Provider getProviderById( String id);
  ```

- 编写ProviderServiceImpl类，实现ProviderService接口

  ```java
  //通过proId获取Provider
  @Override
  public Provider getProviderById(String id) {
      Provider provider = null;
      Connection connection = null;
      try{
          connection = BaseDao.getConnection();
          provider = providerDao.getProviderById(connection, id);
      }catch (Exception e) {
          // TODO: handle exception
          e.printStackTrace();
          provider = null;
      }finally{
          BaseDao.closeResource(connection, null, null);
      }
      return provider;
  }
  ```

- 编写ProviderServlet类

  ```java
  private void getProviderById(HttpServletRequest request, HttpServletResponse response,String url)
      throws ServletException, IOException {
      String id = request.getParameter("proid");
      if(!StringUtils.isNullOrEmpty(id)){
          ProviderService providerService = new ProviderServiceImpl();
          Provider provider = null;
          provider = providerService.getProviderById(id);
          request.setAttribute("provider", provider);
          request.getRequestDispatcher(url).forward(request, response);
      }
  }
  ```

## 11、删除操作实现

### 11.1、删除用户

- 编写UserDao接口

  ```java
  //通过用户id删除用户信息
  public int deleteUserId(Connection connection,Integer userId);
  ```

- 编写UserDaoImpl类，实现UserDao接口

  ```java
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
  ```

- 编写UserService接口

  ```java
  //通过用户id删除用户信息
  public boolean deleteUserId(Integer userId);
  ```

- 编写UserServiceImpl类，实现UserService接口

  ```java
  //通过用户id删除用户信息
  @Override
  public boolean deleteUserId(Integer userId) {
      Connection connection=null;
      boolean flag=false;
      connection = BaseDao.getConnection();
      int i=0;
      i= userDao.deleteUserId(connection, userId);
      if(i>0){
          flag=true;
          System.out.println("删除成功");
      }else{
          System.out.println("删除失败");
      }
  
      BaseDao.closeResource(connection,null,null);
      return flag;
  }
  ```

- 编写UserServlet类

  ```java
  //通过用户id删除用户信息
  private void deleteUserId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      //获取前端数据
      String Id=req.getParameter("uid");
      Integer userId=0;
      userId=Integer.parseInt(Id);
      //判断是否删除成功
      Map<String,String> resultmap=new HashMap<>();
      if(userId<=0){
          resultmap.put("delResult","notexist");
          System.out.println("1");
      }else {
          UserServiceImpl userService = new UserServiceImpl();
          if(userService.deleteUserId(userId)){
              resultmap.put("delResult","true");
              System.out.println("2");
          }else {
              resultmap.put("delResult","false");
              System.out.println("3");
          }
      }
      //把resultMap转换成json对象输出
      resp.setContentType("application/json");
      PrintWriter outPrintWriter = resp.getWriter();
      outPrintWriter.write(JSONArray.toJSONString(resultmap));
      outPrintWriter.flush();
      outPrintWriter.close();
  }
  ```

### 11.2、删除订单

- 编写BillDao接口

  ```java
  //根据订单id删除订单
  public int deleteBillId(Connection connection,Integer BillId) throws SQLException;
  ```

- 编写BillDaoImpl类，实现BillDao接口

  ```java
  @Override
  public int deleteBillId(Connection connection, Integer BillId) throws SQLException {
      String sql="DELETE FROM smbms_bill WHERE id = ?";
      Object[] prams={BillId};
      int execute = BaseDao.execute(connection, sql, prams, null);
      BaseDao.closeResource(connection,null,null);
      return execute;
  }
  ```

- 编写BillService接口

  ```java
  //根据订单id删除订单
  public boolean deleteBillId(Integer BillId) throws SQLException;
  ```

- 编写BillServiceImpl类，实现BillService接口

  ```java
  //根据订单id删除订单
  @Override
  public boolean deleteBillId(Integer BillId) {
      Connection connection=null;
      boolean flag=false;
      connection=BaseDao.getConnection();
      int i = 0;
      try {
          i = billDao.deleteBillId(connection, BillId);
          if(i>0){
              flag=true;
              System.out.println("删除成功");
          }else {
              System.out.println("删除失败");
          }
      } catch (SQLException throwables) {
          throwables.printStackTrace();
      }finally {
          BaseDao.closeResource(connection,null,null);
      }
  
      return flag;
  }
  ```

- 编写BillServlet类

```java
//根据订单id删除订单
private void deleteBillId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    //获取前端数据
    String Id=req.getParameter("billid");
    Integer billid=0;
    billid=Integer.parseInt(Id);
    Map<String,String> resultmap=new HashMap<>();
    BillServiceImpl billService = new BillServiceImpl();
    if(billid<=0){
        resultmap.put("delResult","notexist");
    }else {
        if(billService.deleteBillId(billid)){
            resultmap.put("delResult","true");
        }else {
            resultmap.put("delResult","false");
        }
    }
    //把resultMap转换成json对象输出
    resp.setContentType("application/json");
    PrintWriter outPrintWriter = resp.getWriter();
    outPrintWriter.write(JSONArray.toJSONString(resultmap));
    outPrintWriter.flush();
    outPrintWriter.close();
}
```

### 11.3、删除供应商

- 编写ProviderDao接口

  ```java
  //根据供应商id删除供应商
  public int deleteproId(Connection connection,Integer proId);
  ```

- 编写ProviderDaoImpl类，实现ProviderDao接口

  ```java
  //根据供应商id删除供应商
  @Override
  public int deleteproId(Connection connection, Integer proId) {
      PreparedStatement pstm=null;
      int execute=0;
      try {
          String sql="DELETE FROM smbms_provider WHERE id = ?";
          Object[] parms={proId};
          execute= BaseDao.execute(connection, sql, parms, pstm);
      } catch (SQLException throwables) {
          throwables.printStackTrace();
      }finally {
          BaseDao.closeResource(connection,null,pstm);
      }
      return execute;
  }
  ```

- 编写ProviderService接口

  ```java
  //根据订单id删除订单
  public boolean deleteBillId(Integer BillId);
  ```

- 编写ProviderServiceImpl类，实现ProviderService接口

  ```java
  @Override
  public boolean deleteBillId(Integer BillId) {
      boolean flag=false;
      Connection connection=null;
      connection=BaseDao.getConnection();
      int i = providerDao.deleteproId(connection, BillId);
      if(i>0){
          flag=true;
          System.out.println("删除成功");
      }else {
          System.out.println("删除失败");
      }
      BaseDao.closeResource(connection,null,null);
      return flag;
  }
  ```

- 编写ProviderServlet类

  ```java
  //根据供应商id删除供应商
  private void deleteproId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      //获取前端数据
      String id=req.getParameter("proid");
      Integer proid=0;
      proid=Integer.parseInt(id);
      Map<String ,String> resultmap=new HashMap<>();
      ProviderServiceImpl providerService = new ProviderServiceImpl();
      if(proid<=0){
          resultmap.put("delResult","notexist");
      }else {
          if (providerService.deleteBillId(proid)) {
              resultmap.put("delResult", "true");
          } else {
              resultmap.put("delResult", "false");
          }
      }
      //把resultMap转换成json对象输出
      resp.setContentType("application/json");
      PrintWriter outPrintWriter = resp.getWriter();
      outPrintWriter.write(JSONArray.toJSONString(resultmap));
      outPrintWriter.flush();
      outPrintWriter.close();
  }
  ```

## 12、修改操作实现

### 12.1、修改用户

- 编写UserDao接口

  ```java
  //修改用户信息
  public int modify(Connection connection,User user);
  ```

- 编写UserDaoImpl类，实现UserDao接口

  ```java
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
  ```

- 编写UserService接口

  ```java
  //修改用户信息
  public boolean modify(User user);
  ```

- 编写UserServiceImpl类，实现UserService接口

  ```java
  //修改用户信息
  @Override
  public boolean modify(User user) {
      Connection connection=null;
      boolean flag=false;
      int modify=0;
      connection=BaseDao.getConnection();
      //connection.setAutoCommit(false);
      modify= userDao.modify(connection, user);
      //connection.commit();
      if(modify>0){
          flag=true;
          System.out.println("修改成功");
      }else{
          System.out.println("修改失败");
      }
      BaseDao.closeResource(connection,null,null);
      return flag;
  }
  ```

- 编写UserServlet类

  ```java
  //修改用户信息
  private void modify(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
      //获取前端数据
      String id=req.getParameter("uid");
      String userName=req.getParameter("userName");
      String gender=req.getParameter("gender");
      String birthday=req.getParameter("birthday");
      String phone=req.getParameter("phone");
      String address=req.getParameter("address");
      String userRole=req.getParameter("userRole");
  
      User user=new User();
      user.setId(Integer.valueOf(id));
      user.setUserName(userName);
      user.setGender(Integer.valueOf(gender));
      try {
          user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
      } catch (ParseException e) {
          e.printStackTrace();
      }
      user.setPhone(phone);
      user.setAddress(address);
      user.setUserRole(Integer.valueOf(userRole));
      user.setModifyBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
      user.setModifyDate(new Date());
  
      UserServiceImpl userService = new UserServiceImpl();
      boolean modify = userService.modify(user);
      if(modify){
          resp.sendRedirect(req.getContextPath()+"/jsp/user.do?method=query");
      }else {
          req.getRequestDispatcher("usermodify.jsp").forward(req, resp);
      }
  }
  ```

### 12.2、修改订单

- 编写BillDao接口

  ```java
  //根据用户传递输入的值修改订单表
  public int modify(Connection connection, Bill bill) throws SQLException;
  ```

- 编写BillDaoImpl类，实现BillDao接口

  ```java
  //根据用户传递输入的值修改订单表
  @Override
  public int modify(Connection connection, Bill bill) throws SQLException {
      int modifyNum=0;
      PreparedStatement pstm=null;
      if(connection!=null){
          String sql = "update smbms_bill set productName=?," +
              "productDesc=?,productUnit=?,productCount=?,totalPrice=?," +
              "isPayment=?,providerId=?,modifyBy=?,modifyDate=? where id = ? ";
          Object[] params = {bill.getProductName(),bill.getProductDesc(),
                             bill.getProductUnit(),bill.getProductCount(),bill.getTotalPrice(),bill.getIsPayment(),
                             bill.getProviderId(),bill.getModifyBy(),bill.getModifyDate(),bill.getId()};
          modifyNum=BaseDao.execute(connection,sql,params,pstm);
          BaseDao.closeResource(null,null,pstm);
      }
      return modifyNum;
  }
  ```

- 编写BillService接口

  ```java
  //根据用户传递输入的值修改订单表
  public boolean modify(Bill bill);
  ```

- 编写BillServiceImpl类，实现BillService接口

  ```java
  //根据用户传递输入的值修改订单表
  @Override
  public boolean modify(Bill bill) {
      Boolean flag=false;
      int modifyNum=0;
      Connection connection=null;
      try {
          connection=BaseDao.getConnection();
          modifyNum=billDao.modify(connection,bill);
          if(modifyNum>0){
              flag=true;
          }
      } catch (Exception e) {
          e.printStackTrace();
      }finally {
          BaseDao.closeResource(connection,null,null);
      }
      return flag;
  }
  ```

- 编写BillServlet类

  ```java
  private void modify(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      System.out.println("modify===============");
      String id = request.getParameter("id");
      String productName = request.getParameter("productName");
      String productDesc = request.getParameter("productDesc");
      String productUnit = request.getParameter("productUnit");
      String productCount = request.getParameter("productCount");
      String totalPrice = request.getParameter("totalPrice");
      String providerId = request.getParameter("providerId");
      String isPayment = request.getParameter("isPayment");
  
      Bill bill = new Bill();
      bill.setId(Integer.valueOf(id));
      bill.setProductName(productName);
      bill.setProductDesc(productDesc);
      bill.setProductUnit(productUnit);
      bill.setProductCount(new BigDecimal(productCount).setScale(2,BigDecimal.ROUND_DOWN));
      bill.setIsPayment(Integer.parseInt(isPayment));
      bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2,BigDecimal.ROUND_DOWN));
      bill.setProviderId(Integer.parseInt(providerId));
  
      bill.setModifyBy(((User)request.getSession().getAttribute(Constants.USER_SESSION)).getId());
      bill.setModifyDate(new Date());
      boolean flag = false;
      BillService billService = new BillServiceImpl();
      flag = billService.modify(bill);
      if(flag){//判断是否修改成功
          response.sendRedirect(request.getContextPath()+"/jsp/bill.do?method=query");
      }else{
          request.getRequestDispatcher("billmodify.jsp").forward(request, response);
      }
  }
  ```

### 12.3、修改供应商

- 编写ProviderDao接口

  ```java
  public int modify(Connection connection, Provider provider) throws SQLException;
  ```

- 编写ProviderDaoImpl类，实现ProviderDao接口

  ```java
  public int modify(Connection connection, Provider provider) throws SQLException {
      int flag = 0;
      PreparedStatement pstm = null;
      if(null != connection){
          String sql = "update smbms_provider set proName=?,proDesc=?,proContact=?," +
              "proPhone=?,proAddress=?,proFax=?,modifyBy=?,modifyDate=? where id = ? ";
          Object[] params = {provider.getProName(),provider.getProDesc(),provider.getProContact(),provider.getProPhone(),provider.getProAddress(),
                             provider.getProFax(),provider.getModifyBy(),provider.getModifyDate(),provider.getId()};
          flag = BaseDao.execute(connection, sql, params, pstm);
          BaseDao.closeResource(null, null, pstm);
      }
      return flag;
  }
  ```

- 编写ProviderService接口

  ```java
  //修改用户信息
  public boolean modify(Provider provider);
  ```

- 编写ProviderServiceImpl类，实现ProviderService接口

  ```java
  @Override
  public boolean modify(Provider provider) {
      Connection connection = null;
      boolean flag = false;
      try {
          connection = BaseDao.getConnection();
          if(providerDao.modify(connection,provider) > 0)
              flag = true;
      } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }finally{
          BaseDao.closeResource(connection, null, null);
      }
      return flag;
  }
  ```

- 编写ProviderServlet类

  ```java
  private void modify(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      String proName=request.getParameter("proName");
      String proContact = request.getParameter("proContact");
      String proPhone = request.getParameter("proPhone");
      String proAddress = request.getParameter("proAddress");
      String proFax = request.getParameter("proFax");
      String proDesc = request.getParameter("proDesc");
      String id = request.getParameter("id");
      Provider provider = new Provider();
      provider.setId(Integer.valueOf(id));
      provider.setProName(proName);
      provider.setProContact(proContact);
      provider.setProPhone(proPhone);
      provider.setProFax(proFax);
      provider.setProAddress(proAddress);
      provider.setProDesc(proDesc);
      provider.setModifyBy(((User)request.getSession().getAttribute(Constants.USER_SESSION)).getId());
      provider.setModifyDate(new Date());
      boolean flag = false;
      ProviderService providerService = new ProviderServiceImpl();
      flag = providerService.modify(provider);
      if(flag){
          response.sendRedirect(request.getContextPath()+"/jsp/provider.do?method=query");
      }else{
          request.getRequestDispatcher("providermodify.jsp").forward(request, response);
      }
  }
  ```

  
