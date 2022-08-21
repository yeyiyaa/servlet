

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

public class DelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deptno = request.getParameter("deptno");
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int count=0;
        try {
            conn =DBUtil.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            String sql="delete from dept where deptno =? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            count = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            if(conn!=null){
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);

        }
        //转发
        if(count==1){
//            request.getRequestDispatcher("/list").forward(request,response);
            response.sendRedirect(request.getContextPath() + "/list");
        }else {
            //删除失败
//            request.getRequestDispatcher("/error.html").forward(request,response);
            response.sendRedirect("/error.html");
        }

    }
}
