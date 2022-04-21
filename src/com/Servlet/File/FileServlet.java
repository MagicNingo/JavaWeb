package com.Servlet.File;

import com.bus.entity.MyFile;
import com.bus.service.MyFileServiceImp;
import com.bus.util.BootStrapPage;
import com.bus.util.Page;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@WebServlet("/MyFile/FileServlet")
public class FileServlet extends HttpServlet {

    private MyFileServiceImp msi;

    public FileServlet() {
        msi = new MyFileServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        if ("doUpLoad".equals(op)) {
            doUpLoad(req, resp);
        } else if ("doDeleteFile".equals(op)) {
            doDeleteFile(req, resp);
        } else if ("doShowAllFile".equals(op)) {
            doShowAllFile(req, resp);
        } else if ("doDownLoad".equals(op)) {
            doDownLoad(req, resp);
        } else {
            System.out.println("操作的产品参数" + op + "有异常！");
        }
    }

    private void doDownLoad(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        MyFile myFile = msi.findFileById(id);
        //得到文件在服务器的地址
        String autoName = myFile.getAutoName();
        String realPath = req.getServletContext().getRealPath("/upLoad/");
        //得到扩展名
        String ext = autoName.substring(autoName.lastIndexOf("."));
        //下载后的名字
        String fileName = myFile.getFileName();
        fileName += ext;
        //处理可能出现代乱码
        fileName = URLEncoder.encode(fileName, "UTF-8");
        //把文件读入内存
        FileInputStream input = new FileInputStream(realPath + autoName);
        //清空response里面可能有的数据
        resp.reset();
        //设置头信息
        resp.setHeader("Content-Disposition", "attachment;filename="+fileName);
        //从输入流得到数据,再由response输出到客户端
        ServletOutputStream out = resp.getOutputStream();
        byte[] buf = new byte[10240];
        int len = 0;
        while ((len = input.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        input.close();
        out.flush();
        out.close();
        System.out.println("下载完成!");
    }


    protected void doUpLoad(HttpServletRequest req, HttpServletResponse resp) {
        String file_name = null;
        String auto_name = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        String file_desc = null;
        String user_name = (String) req.getSession().getAttribute("loginName");
        //使用Apache的文件上传组件
        //1:得到一个文件处理的工场
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //2:文件解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //3:判断提交上来的数据是否是多部件传输
        if (ServletFileUpload.isMultipartContent(req)) {
            //使用解析器来解析
            try {
                List<FileItem> list = upload.parseRequest(req);
                for (FileItem item : list) {
                    //item分两种情况，一种是form的普通字段，一种是文件
                    if (item.isFormField()) {
                        //如果是普通字段，就得到字段名，和相应的值
                        String fieldName = item.getFieldName();
                        String value = item.getString("UTF-8");
                        System.out.println(fieldName + "---->" + value);
                        if ("fileName".equals(fieldName)) {
                            file_name = value;
                        } else if ("fileDesc".equals(fieldName)) {
                            file_desc = value;
                        }

                    } else {
                        //否则就是上传过来的文件

                        //传过来的文件名
                        String name = item.getName();
                        System.out.println("上传过来的文件名:" + name);
                        String ext = name.substring(name.lastIndexOf("."));
                        System.out.println("扩展名:" + ext);
                        auto_name += ext;
                        System.out.println("写道磁盘的文件名:" + auto_name);
                        String realPath = getServletContext().getRealPath("/upLoad");
                        System.out.println("文件的真实路径:" + realPath);
                        File file = new File(realPath, auto_name);
                        file.createNewFile();
                        //把文件从客户端读到内存,在输出到磁盘
                        InputStream input = item.getInputStream();
                        FileOutputStream out = new FileOutputStream(file);
                        byte[] buf = new byte[10240];
                        int len = 0;
                        while ((len = input.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        input.close();
                        out.flush();
                    }
                }
                MyFile myFile = new MyFile(file_name, auto_name, file_desc, user_name);
                int i = msi.addFile(myFile);
                System.out.println(i+"________++++++");
                if (i > 0) {
                    resp.getWriter().write("<script>alert('上传成功！');location.href='ShowAllFile.jsp'</script>");
                } else {
                    resp.getWriter().write("<script>alert('上传失败! ');location.href='FileServlet?op=doShowAllFile'</script>");
                }

            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void doShowAllFile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int offset = Integer.parseInt(req.getParameter("offset"));//offset为bootstraptable的忽略数
        int pageSize = Integer.parseInt(req.getParameter("limit"));//limit为bootstraptable的偏移量
        int currentPage = offset / pageSize + 1;
        Page<MyFile> page = msi.findMyFileByPage(new Page<>(currentPage, pageSize));
        BootStrapPage<MyFile> bootPage = new BootStrapPage<>();
        bootPage.setTotal(page.getTotalRecord());
        bootPage.setRows(page.getList());
        resp.getWriter().print(JSONObject.fromObject(bootPage));
    }

    private void doDeleteFile(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("删除文件");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
