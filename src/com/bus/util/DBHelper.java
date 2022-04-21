package com.bus.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBHelper {
    private static  String url ;
    private static  String user ;
    private static  String password;
    private static  String driver;

    static {
        //方式一：读取db.properties里的相关信息
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        url= bundle.getString("jdbc.url");
        user= bundle.getString("jdbc.user");
        password= bundle.getString("jdbc.password");
        driver= bundle.getString("jdbc.driver");

        //方法二：读取db.properties里的相关信息
        /*
        Properties pro = new Properties();
        try {
            MyFile file = new MyFile("E:\\WorkSpace\\IEDAWorkplace\\src\\db.properties");
            pro.load(new FileReader(file));*/

            //方法三：
            /*InputStream stream = DBHelper.class.getClassLoader().getResourceAsStream("db.properties");
            pro.load(stream);

            url= pro.getProperty("jdbc.url");
            user= pro.getProperty("jdbc.user");
            password= pro.getProperty("jdbc.password");
            driver= pro.getProperty("jdbc.driver");

        } catch (IOException e) {
            e.printStackTrace();
        }
         */
        //方法四：使用XML进行配置
        /*ParseXml parse = new ParseXml("database.xml");
        List<Element> list = parse.getList();
        driver=list.get(0).getTextTrim();
        url=list.get(1).getTextTrim();
        user=list.get(2).getTextTrim();
        password=list.get(3).getTextTrim();

        System.out.println(url+"\n"+user+"\n"+password+"\n"+driver);*/

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {

        }

        System.out.println("驱动加载成功！");
    }

    public static Connection getConnection(){
        try {
            return  DriverManager.getConnection(url,
                        user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
