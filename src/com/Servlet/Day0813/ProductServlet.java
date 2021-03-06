package com.Servlet.Day0813;

import com.bus.entity.Category;
import com.bus.entity.Product;
import com.bus.entity.Provider;
import com.bus.service.CategoryServiceImp;
import com.bus.service.ProductServiceImp;
import com.bus.service.ProviderServiceImp;
import com.bus.util.BootStrapPage;
import com.bus.util.MyJsonDateConvertor;
import com.bus.util.Page;
import com.bus.util.TimeUtil;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@WebServlet("/AdminManage/ShowProducts/ProductServlet")
public class ProductServlet extends HttpServlet {
    ProductServiceImp ps;
    ProviderServiceImp psi;
    CategoryServiceImp csi;
    JsonConfig config;
    MyJsonDateConvertor myJsonDateConvertor;
    public ProductServlet() {
        ps = new ProductServiceImp();
        psi = new ProviderServiceImp();
        csi = new CategoryServiceImp();
        config = new JsonConfig();
        myJsonDateConvertor = new MyJsonDateConvertor("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");*/
        String op = req.getParameter("op");
        if ("doShowProduct".equals(op)) {
            doShowProduct(req, resp);
        } else if ("doDeleteProduct".equals(op)) {
            doDeleteProduct(req, resp);
        } else if ("doUpdateProduct".equals(op)) {
            doUpdateProduct(req, resp);
        } else if ("doEdit".equals(op)) {
            doEdit(req,resp);
        } else if ("doAddProduct".equals(op)) {
            doAddProduct(req,resp);
        } else if ("doIncrease".equals(op)) {
            doIncrease(req,resp);
        } else {
            System.out.println("?????????????????????"+op+"????????????");
        }
    }

    protected void doAddProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //????????????????????????ID
        int productID = Integer.parseInt(req.getParameter("ProductID"));
        //????????????????????????
        Product p = ps.findProductByID(productID);
        //?????????????????????????????????
        List<Provider> allProvider = psi.findAllProvider();
        List<Category> allCategory = csi.findAllCategory();
        //???????????????request??????
        req.setAttribute("p", p);
        req.setAttribute("allProvider",allProvider);
        req.setAttribute("allCategory",allCategory);
        req.getRequestDispatcher("AddProduct.jsp").forward(req, resp);
    }
    protected void doIncrease(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String product_name = req.getParameter("product_name");
        double income_price = Double.parseDouble(req.getParameter("income_price"));
        int providerID = Integer.parseInt(req.getParameter("providerID"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        double sales_price = Double.parseDouble(req.getParameter("sales_price"));
        int categoryID = Integer.parseInt(req.getParameter("categoryID"));
        String income_time = req.getParameter("income_time");
        Date date = TimeUtil.stringToDate(income_time, "yyyy-MM-dd");
        Product product = new Product(product_name, income_price, new Provider(providerID),
                quantity, sales_price, new Category(categoryID), date);
        PrintWriter out = resp.getWriter();
        int i = ps.addProduct(product);
        if (i == 1) {
            out.write("<script>alert('???????????????');location.href='ProductServlet?op=doShowProduct&currentPage=1&pageSize=6'</script>");
        } else if (i == 0) {
            out.write("<script>alert('???????????????');history.back()</script>");
        } else if (i == -1){
            out.write("<script>alert('??????????????????????????????????????????');history.back()</script>");
        }
    }
    protected void doEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int productID = Integer.parseInt(req.getParameter("productID"));
        String product_name = req.getParameter("product_name");
        double income_price = Double.parseDouble(req.getParameter("income_price"));
        int providerID = Integer.parseInt(req.getParameter("providerID"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        double sales_price = Double.parseDouble(req.getParameter("sales_price"));
        int categoryID = Integer.parseInt(req.getParameter("categoryID"));
        String income_time = req.getParameter("income_time");
        Date date = TimeUtil.stringToDate(income_time, "yyyy-MM-dd");
        Product product = new Product(productID, product_name, income_price, new Provider(providerID),
                quantity, sales_price, new Category(categoryID), date);

        int i = ps.updateProductByID(product);
        if (i != 0) {
            resp.getWriter().print("ok");
        } else {
            resp.getWriter().print("no");
        }

    }

    protected void doShowProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int offset = Integer.parseInt(req.getParameter("offset"));
        int pageSize = Integer.parseInt(req.getParameter("limit"));
        String search_name = req.getParameter("search_name");//?????????????????????????????????????????????
        int currentPage = offset / pageSize + 1;

        //Page<Product> list = ps.findProductByPage(new Page<>(currentPage, pageSize));
        Page<Product> page = ps.findProductByCondition(search_name, new Page<>(currentPage, pageSize));
        BootStrapPage<Product> bootPage = new BootStrapPage<>();
        bootPage.setTotal(page.getTotalRecord());
        bootPage.setRows (page.getList());

        //??????????????????json???????????????
        config.registerJsonValueProcessor(Date.class, myJsonDateConvertor);
        //???????????????????????????????????????????????????
        JSONObject json = JSONObject.fromObject(bootPage,config);

        resp.getWriter().print(json);
    }

    protected void doDeleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int productID = Integer.parseInt(req.getParameter("productID"));
        System.out.println("?????????????????????"+productID);
        int i = ps.removeProductByID(productID);
        if (i == 1) {
            resp.getWriter().print("ok");
        } else {
            resp.getWriter().print("no");
        }

    }
    protected void doUpdateProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       //????????????????????????????????????????????????????????????????????????????????????
        int productID = Integer.parseInt(req.getParameter("productID"));
        Product p = ps.findProductByID(productID);
        //??????????????????json???????????????
        config.registerJsonValueProcessor(Date.class, myJsonDateConvertor);
        //???????????????????????????????????????????????????
        JSONObject json = JSONObject.fromObject(p,config);
        /*List<Provider> allProvider = psi.findAllProvider();
        List<Category> allCategory = csi.findAllCategory();*/
        resp.getWriter().print(JSONObject.fromObject(json));

    }//        req.setAttribute("p", p);
//        req.setAttribute("allProvider",allProvider);
//        req.setAttribute("allCategory",allCategory);
//        req.getRequestDispatcher("UpdateProduct-New.jsp").forward(req, resp);



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
