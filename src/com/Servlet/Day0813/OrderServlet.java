package com.Servlet.Day0813;

import com.bus.entity.Customer;
import com.bus.entity.Employee;
import com.bus.entity.Product;
import com.bus.service.CustomerServiceImp;
import com.bus.service.EmployeeServiceImp;
import com.bus.service.OrderServiceImp;
import com.bus.service.ProductServiceImp;
import com.bus.util.MyJsonDateConvertor;
import com.bus.util.TimeUtil;
import net.sf.json.JsonConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/AdminManage/ShowOrders/OrderServlet")
public class OrderServlet extends HttpServlet {

    OrderServiceImp osi;
    CustomerServiceImp csi;
    EmployeeServiceImp esi;
    ProductServiceImp psi;
    JsonConfig config;
    MyJsonDateConvertor myJsonDateConvertor;

    public OrderServlet() {
        osi = new OrderServiceImp();
        csi = new CustomerServiceImp();
        esi = new EmployeeServiceImp();
        psi = new ProductServiceImp();
        config = new JsonConfig();
        myJsonDateConvertor = new MyJsonDateConvertor("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        if ("doShowOrder".equals(op)) {
            doShowOrder(req, resp);
        } else if ("doDeleteOrder".equals(op)) {
            doDeleteOrder(req, resp);
        } else if ("doUpdateOrder".equals(op)) {
            doUpdateOrder(req, resp);
        } else if ("doEdit".equals(op)) {
            doEdit(req,resp);
        } else if ("doAddOrder".equals(op)) {
            doAddOrder(req,resp);
        } else if ("doIncrease".equals(op)) {
            doIncrease(req,resp);
        } else {
            System.out.println("操作的产品参数"+op+"有异常！");
        }
    }

    private void doIncrease(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void doAddOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        需要准备的数据
            1.订单的编号
            2.得到所有的客户信息
            3.得到所有的员工信息
            4.得到所有的产品信息

        */
        String orderID = TimeUtil.getTime("yyyyMMddHHmmss") + UUID.randomUUID().toString().substring(0,5);
        List<Customer> cusList = csi.findAllCustomers();
        List<Employee> empList = esi.findAllEmployee();
        List<Product> pList = psi.findAllProduct();
        req.setAttribute("orderID", orderID);
        req.setAttribute("cusList", cusList);
        req.setAttribute("empList",empList );
        req.setAttribute("pList", pList);
        req.getRequestDispatcher("AddOrder.jsp").forward(req, resp);

    }

    private void doEdit(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void doUpdateOrder(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void doDeleteOrder(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void doShowOrder(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
