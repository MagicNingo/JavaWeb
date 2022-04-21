package com.TestProgram;

import com.bus.entity.Category;
import com.bus.entity.Product;
import com.bus.entity.Provider;
import com.bus.service.CategoryServiceImp;
import com.bus.service.ProductServiceImp;
import com.bus.service.ProviderServiceImp;
import com.bus.util.DBHelper;
import com.bus.util.TimeUtil;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestOracle {

    public void one() throws SQLException {
        Connection con = DBHelper.getConnection();
        String sql = "{call empinfo(?,?,?,?)}";
        CallableStatement call = con.prepareCall(sql);
        //1号位置为入参，和之前一样设值
        call.setInt(1, 7839);
        //2，3，4号参数是out参数，要注册
        call.registerOutParameter(2, OracleTypes.VARCHAR);
        call.registerOutParameter(3, OracleTypes.VARCHAR);
        call.registerOutParameter(4, OracleTypes.VARCHAR);
        //执行
        call.execute();
        //得到out参数的数据
        String name = call.getString(2);
        String job = call.getString(3);
        double sal = call.getDouble(4);
        System.out.println(name+"---->"+job+"---->"+sal);
    }

    public void two() throws SQLException {
        Connection con = DBHelper.getConnection();
        String sql = "{ ? = call getAllSal(?) }";
        CallableStatement call = con.prepareCall(sql);
        call.setInt(2, 7839);
        call.registerOutParameter(1, OracleTypes.VARCHAR);
        call.execute();

        double sal = call.getDouble(1);
        System.out.println("员工的年薪："+sal);
    }

    public void three() throws SQLException {
        Connection con = DBHelper.getConnection();
        String sql = "{call mypackage.getEmpinfoById(?,?)}";
        CallableStatement call = con.prepareCall(sql);
        call.setInt(1, 10);
        call.registerOutParameter(2, OracleTypes.CURSOR);
        call.execute();

        ResultSet set = ((OracleCallableStatement) call).getCursor(2);
        while(set.next()) {
            System.out.println(set.getInt("empno")+"--->"
                    +set.getString("ename")+"--->"+set.getDouble("sal"));
        }

    }

    public static void main(String[] args) {
        /*ProductServiceImp psi = new ProductServiceImp();
        Product product = new Product("哈密瓜",5.9,new Provider(1),
                100,8.0,new Category(1),
                new Date());
        int i = psi.addProduct(product);
        System.out.println(i);*/
       /* CategoryServiceImp csi = new CategoryServiceImp();
        int i = csi.addCategory(new Category("芒果类", "很大的芒果"));
        System.out.println(i);*/
        /*ProviderServiceImp psi = new ProviderServiceImp();
        int i = psi.addProvider(new Provider("浦发杂货", "上海",
                "17689009038", "321451242132", "143678@qq.com"));
        System.out.println(i);*/


    }
}
