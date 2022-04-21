package com.bus.util;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
/*
        MyPool类用来完成自定义连接池：
        1.创建容器，用来存放数据库连接对象
        2.初始化容器
        3.提供getConnection(),对外提供数据库连接
        4.returnConnection(),还回池里
*
*/

public class MyPool implements DataSource {

//    1.创建容器，用来存放数据库连接对象
       static List<Connection> list = new LinkedList<Connection>();
//    2.初始化容器
        static {
            for (int i = 0; i < 3; i++) {
                Connection con = DBHelper.getConnection();
                //把连接放入池中
                list.add(con);
            }
        }



//       3.提供getConnection(),对外提供数据库连接
    @Override
    public Connection getConnection() throws SQLException {
        Connection con = list.remove(0);
        System.out.println("连接被拿走1个，还剩"+list.size());
        return con;
    }

//     4.returnConnection(),还回池里
    public void returnConnection(Connection con){
        try {
            if (con != null&&!con.isClosed()) {
                list.add(con);
                System.out.println("l连接被换回，还有"+list.size());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
