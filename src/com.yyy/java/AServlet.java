import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class AServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //获取系统当前时间
        Date nowtime = new Date();
        //将时间绑定到请求域当中
        request.setAttribute("sysTime",nowtime);
        //从请求域中将东西拿出来
        Object sysTime = request.getAttribute("sysTime");
        out.print("<h1>系统当前时间为1"+sysTime+"<h1>");
        out.print("<h1>系统当前时间为2"+sysTime+"<h1>");
        out.print("<a href=/servlet_war/b>B</a>");
        //转发
        //获取请求转发器对象
        //相当于把“/b”这个路径包装到转发器当中，实际上是把下一个跳转的资源路径告知给Tomcat服务器了
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/b");
        //转发的时候这两个参数很重要。request和response都是要给下一个资源的
//        requestDispatcher.forward(request,response);
        //也可以一行搞定
//        request.getRequestDispatcher("/b").forward(request,response);
        //转发可以是一个servlet也可以是一个html，只要Web容器当中的合法资源即可


        // 获取应用的根路径
        String contextPath = request.getContextPath();
        out.println(contextPath+"<br>");


        // 获取请求方式
        String method = request.getMethod();
        out.println(method+"<br>");

        // 获取请求的URI
        String uri = request.getRequestURI();  // /aaa/testRequest
        out.println(uri+"<br>");

        // 获取servlet path
        String servletPath = request.getServletPath(); //   /testRequest
        out.println(servletPath+"<br>");







    }
}
