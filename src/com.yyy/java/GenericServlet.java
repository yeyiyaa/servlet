import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public   class GenericServlet extends jakarta.servlet.GenericServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html");
        PrintWriter out = servletResponse.getWriter();
        //获取ServletContest对象
        ServletContext servletContext = this.getServletContext();
        out.print("servletContest"+servletContext+"<br>");

        //获取上下文的初始化参数
        Enumeration<String> initParameterNames = servletContext.getInitParameterNames();
        while (initParameterNames.hasMoreElements()){
            String name = initParameterNames.nextElement();
            String value = servletContext.getInitParameter(name);
            out.print(name+"="+value+"<br>");
        }
            //获取文件的绝对路径
            String contextPath = servletContext.getContextPath();
            out.print("contextPath:"+contextPath+"<br>");

            //获取文件的绝对路径
            String realPath = servletContext.getRealPath("student.html");
            out.print("realPath"+realPath);




    }
}
