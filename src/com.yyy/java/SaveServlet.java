import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取部门信息
        //注意乱码问题
//        request.setCharacterEncoding("UFT-8");
        String deptno=request.getParameter("deptno");
        String name = request.getParameter("name");
        String loc = request.getParameter("loc");
        String contextPath = request.getContextPath();
        //连接数据库执行insert语句
        //保存成功跳转列表页面
        //保存失败跳转错误页面
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int flag=0;
        int count=0;
        try {
            conn = DBUtil.getConnection();
            String sql="select deptno from dept where deptno="+deptno+"";
             ps = conn.prepareStatement(sql);
             rs= ps.executeQuery();
//             if(rs.getFetchSize()!=0){
//                 flag++;
//                 request.getRequestDispatcher("error.html").forward(request,response);
//             }else {
                 String sql2="insert into dept(deptno,name,loc) value (?,?,?)";
                 ps = conn.prepareStatement(sql2);
                 ps.setString(1,deptno);
                 ps.setString(2,name);
                 ps.setString(3,loc);
                 count= ps.executeUpdate();
//             }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        if(count==1&&flag==0){
            request.getRequestDispatcher("/list").forward(request,response);
        }
//        &&flag!=0
        if(count==0){
            request.getRequestDispatcher("error.html").forward(request,response);
        }

    }
}
