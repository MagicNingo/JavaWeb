package com.Servlet.Day0813;

import com.bus.entity.Provider;
import com.bus.service.ProviderServiceImp;
import com.bus.util.BootStrapPage;
import com.bus.util.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/AdminManage/ShowProviders/ProviderServlet")
public class ProviderServlet extends HttpServlet {
    ProviderServiceImp ps;

    public ProviderServlet() {
        ps = new ProviderServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        if ("doShowProvider".equals(op)) {
            doShowProvider(req, resp);
        } else if ("doDeleteProvider".equals(op)) {
            doDeleteProvider(req, resp);
        } else if ("doUpdateProvider".equals(op)) {
            doUpdateProvider(req, resp);
        } else if ("doEdit".equals(op)) {
            doEdit(req, resp);
        } else if ("doAddProvider".equals(op)) {
            doAddProvider(req, resp);
        } else if ("doIncrease".equals(op)) {
            doIncrease(req, resp);
        }
    }

    private void doIncrease(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String provider_name = req.getParameter("provider_name");
        String provider_add = req.getParameter("provider_add");
        String provider_tel = req.getParameter("provider_tel");
        String account = req.getParameter("account");
        String email = req.getParameter("email");
        Provider provider = new Provider(provider_name, provider_add, provider_tel, account, email);
        PrintWriter out = resp.getWriter();
        int i = ps.addProvider(provider);
        if (i == 1) {
            out.write("<script>alert('添加成功！');location.href='ProviderServlet?op=doShowProvider&currentPage=1&pageSize=6'</script>");
        } else if (i == 0) {
            out.write("<script>alert('添加失败！');history.back()</script>");
        } else if (i == -1) {
            out.write("<script>alert('已存在该供应商，无法继续添加！');history.back()</script>");
        }
    }

    private void doAddProvider(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int providerID = Integer.parseInt(req.getParameter("ProviderID"));
        Provider pro = ps.findProviderById(providerID);

        req.setAttribute("pro", pro);
        req.getRequestDispatcher("AddProvider.jsp").forward(req, resp);
    }

    private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int providerID = Integer.parseInt(req.getParameter("providerID"));
        String provider_name = req.getParameter("provider_name");
        String provider_add = req.getParameter("provider_add");
        String provider_tel = req.getParameter("provider_tel");
        String account = req.getParameter("account");
        String email = req.getParameter("email");
        Provider provider = new Provider(providerID, provider_name, provider_add, provider_tel, account, email);
        int i = ps.updateProviderById(provider);
        if (i != 0) {
            resp.getWriter().print("ok");
        } else {
            resp.getWriter().print("no");
        }
    }

    private void doUpdateProvider(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int providerID = Integer.parseInt(req.getParameter("providerID"));
        Provider pro = ps.findProviderById(providerID);
//        req.setAttribute("pro", pro);
//        req.getRequestDispatcher("UpdateProvider.jsp").forward(req, resp);
        resp.getWriter().print(JSONObject.fromObject(pro));
    }

    private void doDeleteProvider(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* int currentPage = Integer.parseInt(req.getParameter("currentPage"));*/
        int providerID = Integer.parseInt(req.getParameter("providerID"));
        int i = ps.removeProviderByID(providerID);
        if (i == 1) {
//            out.write("<script>alert('删除成功！'); location.href='ProviderServlet?op=doShowProvider&currentPage="+currentPage+"&pageSize=6'</script>");
            resp.getWriter().print("1");//删除成功
        } else if (i == 0){
            resp.getWriter().print("0");//删除失败
        } else if (i == -1){
            resp.getWriter().print("-1");//无法删除，被引用
        }
    }

    private void doShowProvider(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int offset = Integer.parseInt(req.getParameter("offset"));
        int pageSize = Integer.parseInt(req.getParameter("limit"));
        String search_name = req.getParameter("search_name");//拿到前端页面搜索框输入的供应商名
        int currentPage = offset / pageSize + 1;
       // Page<Provider> list = ps.findProviderByPage(new Page<>(currentPage, pageSize));
        Page<Provider> page = ps.findCategoryByCondition(search_name, new Page<>(currentPage, pageSize));
        /*req.setAttribute("list", list);
        req.getRequestDispatcher("ShowAllProvider.jsp").forward(req, resp);*/
        BootStrapPage<Provider> bootPage = new BootStrapPage<>();
        bootPage.setTotal(page.getTotalRecord());
        bootPage.setRows (page.getList());

        resp.getWriter().print(JSONObject.fromObject(bootPage));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
