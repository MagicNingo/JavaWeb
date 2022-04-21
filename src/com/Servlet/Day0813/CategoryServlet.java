package com.Servlet.Day0813;

import com.bus.entity.Category;
import com.bus.entity.Provider;
import com.bus.service.CategoryServiceImp;
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


@WebServlet("/AdminManage/ShowCategories/CategoryServlet")
public class CategoryServlet extends HttpServlet {
    CategoryServiceImp cs;

    public CategoryServlet() {
        cs = new CategoryServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String op = req.getParameter("op");
        if ("doShowCategory".equals(op)) {
            doShowCategory(req, resp);
        } else if ("doDeleteCategory".equals(op)) {
            doDeleteCategory(req, resp);
        } else if ("doUpdateCategory".equals(op)) {
            doUpdateCategory(req, resp);
        } else if ("doEdit".equals(op)) {
            doEdit(req, resp);
        } else if ("doAddCategory".equals(op)) {
            doAddCategory(req, resp);
        } else if ("doIncrease".equals(op)) {
            doIncrease(req, resp);
        } else if ("checkName".equals(op)) {
            checkName(req, resp);
        } else if ("checkNameByUpdate".equals(op)) {
            checkNameByUpdate(req, resp);
        } else {
            System.out.println("操作的产品参数" + op + "有异常！");
        }
    }


    protected void checkNameByUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Category category = cs.findCategoryById(id);
        if (category.getCategory_name().equals(name)) {
            resp.getWriter().print("目录名已存在");
        } else {
            resp.getWriter().print("目录名可以使用");
        }

    }

    protected void checkName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        System.out.println("接收到的name:" + name);
        int i = cs.findAllCategoryName(name);
        if (i == 0) {
            //针对ajax请求，只要返回字符串即可
            resp.getWriter().print("ok");
        } else if (i == 1) {
            resp.getWriter().print("no");
        }
    }

    private void doIncrease(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String category_name = req.getParameter("category_name");
        String category_desc = req.getParameter("category_desc");
        Category category = new Category(category_name, category_desc);
        int i = cs.addCategory(category);
        if (i == 1) {
            resp.getWriter().print("1");
        } else if (i == 0) {
            resp.getWriter().print("0");
        } else if (i == -1) {
            resp.getWriter().print("-1");//已存在该目录
        }
    }

    private void doAddCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryID = Integer.parseInt(req.getParameter("categoryID"));
        Category cate = cs.findCategoryById(categoryID);

        req.setAttribute("cate", cate);
        req.getRequestDispatcher("AddCategory.jsp").forward(req, resp);
    }

    private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int categoryID = Integer.parseInt(req.getParameter("categoryID"));
        String category_name = req.getParameter("category_name");
        String category_desc = req.getParameter("category_desc");
        Category category = new Category(categoryID, category_name, category_desc);
        int i = cs.updateCategoryByID(category);
        if (i != 0) {
            resp.getWriter().print("ok");
        } else {
            resp.getWriter().print("no");
        }
    }

    private void doUpdateCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int categoryID = Integer.parseInt(req.getParameter("categoryID"));
        Category cate = cs.findCategoryById(categoryID);
        resp.getWriter().print(JSONObject.fromObject(cate));
    }

    private void doDeleteCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int categoryID = Integer.parseInt(req.getParameter("categoryID"));
        System.out.println("删除的目录号：" + categoryID);
        int i = cs.deleteCategoryByID(categoryID);
        if (i == 1) {
            resp.getWriter().print("1");//删除成功
        } else if (i == 0) {
            resp.getWriter().print("0");//删除失败
        } else if (i == -1) {
            resp.getWriter().print("-1");//无法删除，被引用
        }
    }

    private void doShowCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
//        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
//        req.setAttribute("list", list);
//        req.getRequestDispatcher("ShowAllCategory.jsp").forward(req, resp);

        int offset = Integer.parseInt(req.getParameter("offset"));//offset为bootstraptable的忽略数
        int pageSize = Integer.parseInt(req.getParameter("limit"));//limit为bootstraptable的偏移量
        String search_name = req.getParameter("search_name");//拿到前端页面搜索框输入的目录名
        int currentPage = offset / pageSize + 1;

        //Page<Category> page = cs.findCategoryByPage(new Page<>(currentPage, pageSize));
        Page<Category> page = cs.findCategoryByCondition(search_name, new Page<>(currentPage, pageSize));//根据目录名去搜索目录
        //要改成bootstraptable的格式，不能用我们自己设计的page格式
        BootStrapPage<Category> bootPage = new BootStrapPage<>();
        bootPage.setTotal(page.getTotalRecord());
        bootPage.setRows(page.getList());

        resp.getWriter().print(JSONObject.fromObject(bootPage));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
