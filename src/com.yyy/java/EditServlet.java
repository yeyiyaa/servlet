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

public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      获取部门编号
        String deptno = request.getParameter("deptno");
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String contextPath = request.getContextPath();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print("        <!DOCTYPE html>");
out.print("<html lang='en'>");
out.print("<head>");
out.print("    <meta charset='UTF-8'>");
out.print("    <meta http-equiv='X-UA-Compatible' content='IE=edge'>");
out.print("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
out.print("    <title>修改部门</title>");
out.print("</head>");
out.print("<body>");
out.print("<h1>修改部门</h1>");
out.print("<hr>");
out.print("<form action='"+contextPath+"/modify' method='post'>");
        try {
            conn=DBUtil.getConnection();
            String sql="select name ,loc from dept where deptno=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,deptno);
            rs = ps.executeQuery();
            if(rs.next()){
                String name = rs.getString("name");
                String loc = rs.getString("loc");
                //输出动态网页
                out.print("                部门编号<input type='text' name='deptno' value='"+deptno+"' readonly><br>");
                out.print("                部门名称<input type='text' name='name' value='"+name+"'><br>");
                out.print("                部门位置<input type='text' name='loc' value='"+loc+"'><br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        out.print("    <input type='submit' value='修改'><br>");
        out.print("</form>");
        out.print("</body>");
        out.print("</html>");


    }
}