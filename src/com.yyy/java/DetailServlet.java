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

public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String deptno = request.getParameter("deptno");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int deptno2 = Integer.parseInt(deptno);
        out.print("        <!DOCTYPE html>");
out.print("<html lang='en'>");
out.print("<head>");
out.print("    <meta charset='UTF-8'>");
out.print("    <meta http-equiv='X-UA-Compatible' content='IE=edge'>");
out.print("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
out.print("    <title>部门详情</title>");
out.print("</head>");
out.print("<body>");
out.print("                <h1>部门详情</h1>");
out.print("<hr>");
        try {
            conn = DBUtil.getConnection();
            String sql = "select name,loc from dept where deptno="+deptno +"";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String loc = rs.getString("loc");
                out.print("                       部门编号:"+deptno+" <br>");
                out.print("                       部门名称:"+name+"<br>");
                out.print("               部门位置:"+loc+" <br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        out.print("<input type='button' value='后退' onclick='window.history.back()'>");
        out.print("</body>");
        out.print("</html>");
    }
}