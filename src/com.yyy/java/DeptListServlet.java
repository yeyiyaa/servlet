import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int i=0;
        String contextPath = request.getContextPath();
        out.print("        <!DOCTYPE html>");
out.print("<html lang='en'>");
out.print("<head>");
out.print("    <meta charset='UTF-8'>");
out.print("    <meta http-equiv='X-UA-Compatible' content='IE=edge'>");
out.print("    <meta name='viewport'' content='width=device-width, initial-scale=1.0'>");
out.print("    <title>部门列表页面</title>");

out.print("           <script type='text/javascript'>");
out.print("               function del(dno) {");
out.print("           var ok = window.confirm('亲，删了不可以恢复哦，是否删除');");
out.print("           if(ok){");
out.print("               document.location.href='"+contextPath+"/del?deptno='+dno");
out.print("           }");
out.print("       }");
out.print("</script>");


out.print("</head>");
out.print("<body>");
out.print("    <h1 align='center'>部门</h1>");
out.print("    <hr>");
out.print("    <table border='1px'' align='center'' width='50%'>");
out.print("        <tr>");
out.print("            <th>序号</th>");
out.print("            <th>部门编号</th>");
out.print("            <th>部门名称</th>");
out.print("            <th>操作</th>");
out.print("        </tr>");

        try {
            //获取连接
            conn = DBUtil.getConnection();
            //获取预编译的数据库操作对象
            String sql="select deptno,name,loc from dept";
            //执行sql语句
            ps = conn.prepareStatement(sql);
            //处理结果集
            rs=ps.executeQuery();
            while (rs.next()){
                String deptno=rs.getString("deptno");
                String name=rs.getString("name");
                String loc=rs.getString("loc");
           out.print("     <tr>");
           out.print("          <td>"+(++i)+"</td>");
           out.print("          <td>"+(deptno)+"</td>");
           out.print("          <td>"+(name)+"</td>");
           out.print("          <td>");
           out.print("              <a href='javascript:void(0)' onclick='del("+deptno+")'>删除</a>");
           out.print("              <a href='"+contextPath+"/edit?deptno="+deptno+"'>修改</a>");
           out.print("              <a href='"+(contextPath)+"/detail?deptno="+deptno+"'>详情</a>");
           out.print("          </td>");
           out.print("     </tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.close(conn,ps,rs);
        }
        out.print("   </table>");
        out.print("   <hr>");
        out.print("   <a href='add.html'>新增部门</a>");
        out.print("   </body>");
        out.print("   </html>");

    }
}
