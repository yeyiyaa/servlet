import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModifyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Connection conn=null;
        PreparedStatement ps=null;
        String deptno = request.getParameter("deptno");
        String name = request.getParameter("name");
        String loc=request.getParameter("loc");
        int count=0;
        try {
            conn=DBUtil.getConnection();
            String sql="UPDATE dept set name=?,loc=? where deptno=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,loc);
            ps.setString(3,deptno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,null);
        }
        if (count==1) {
            request.getRequestDispatcher("/list").forward(request,response);
        }else {
            request.getRequestDispatcher("/error.html").forward(request,response);
        }

    }
}
