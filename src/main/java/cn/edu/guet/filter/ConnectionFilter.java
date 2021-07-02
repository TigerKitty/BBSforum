package cn.edu.guet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接直接getConn就可以得到
 */
@WebFilter(filterName = "ControllerFilter")
public class ConnectionFilter implements Filter {
    public static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Connection conn = getConn();
        try{
            String url = "jdbc:oracle:thin:@112.74.59.255:1521:orcl";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            if (conn==null){
                conn = DriverManager.getConnection(url,"forum","123456");
                threadLocal.set(conn);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
        chain.doFilter(req,resp);//访问目标资源
        //先关闭再移除
        try{
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        threadLocal.remove();
    }

    public static Connection getConn(){
        return threadLocal.get();
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
