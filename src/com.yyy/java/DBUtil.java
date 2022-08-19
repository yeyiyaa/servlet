import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;
public class DBUtil {
    //静态变量
    private static ResourceBundle bundle= ResourceBundle.getBundle("jdbc");
    private static String driver=bundle.getString("driver");
    private static String url=bundle.getString("url");
    private static String user=bundle.getString("user");
    private static String password=bundle.getString("password");

    static {
        try {
            //注册驱动(注册驱动只需要注册一次，放在静态代码块中。DBUtil类加载的时候执行。)
            //com.mysql.jdbc.Driver是连接数据库的驱动，不嫩写死。应为可能会连接别的数据库比如Oracle
            //如果连接Oracle数据库的时候，还需要修改java代码。显然是违背了OCP开闭原则。
            //OCP开闭原则：对扩展开放，对修改关闭。（什么是符合OCP原则呢？在进行扩展功能的时候，不需要修改javad代码）
            //Class.forName("com.mysql.jdbc.Driver")
            Class.forName(driver);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    /**
     * 获取数据库连接对象
     * @return conn连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    /**
     * 释放资源
     * @param conn 连接对象
     * @param ps 数据库操作对象
     * @param rs   结果集对象
     */
    public static void close(Connection conn , Statement ps,ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
