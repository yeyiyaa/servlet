import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class BServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object sysTime = request.getAttribute("sysTime");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //从请求域中取
        System.out.println("系统当前时间为:"+sysTime);
        out.print("系统当前时间为:"+sysTime);




    }
}
