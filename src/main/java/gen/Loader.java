// 
// Decompiled by Procyon v0.5.36
// 

package gen;

import javax.servlet.ServletException;
import java.sql.Connection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/Loader" })
public class Loader extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    public static Connection contentConn;
    
    static {
        Loader.contentConn = null;
    }
    
    public void init() throws ServletException {
        final DbConnection db = new DbConnection();
        Loader.contentConn = db.getDatabse();
        System.out.println("Db Connected in init first time");
    }
}
