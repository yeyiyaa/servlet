import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class StudentServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        //设置相应内容类型
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //连接数据库(JDBC)
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;

        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            String url="jdbc:mysql://localhost:3306/studentlist";
            String user="root";
            String password="123456";
            //获取预编译的数据库操作对象
            //执行SQL
            //处理结果集
            conn= DriverManager.getConnection(url,user,password);
            //编写预编译发的数据库操作对象
            String sql="select studentid,name from student ";
            ps =conn.prepareStatement(sql);
            rs=ps.executeQuery();
            //处理结果集
            while (rs.next()){
                String id=rs.getString("studentid");
                String name=rs.getString("name");
                out.print(id+","+name+"<br>");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }



    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
