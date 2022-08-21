import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RequestTestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Set<String> keyS = map.keySet();
        Iterator<String> iterator = keyS.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            String[] values = map.get(key);
            System.out.print(key+"=");
            for (String value : values) {
                System.out.print(value+",");
            }
            System.out.println();
        }


    }
}
