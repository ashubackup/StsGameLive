// 
// Decompiled by Procyon v0.5.36
// 

package gen;

import java.sql.Statement;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/Logger_PW" })
public class Logger_PW extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter out = response.getWriter();
        DbConnection db1 = null;
        Statement stmt = null;
        final String ani = request.getParameter("ani");
        String gameurl = request.getParameter("url");
        final String type = request.getParameter("type");
        final String nick_name = request.getParameter("nick_name");
        final String game_id = request.getParameter("id");
        final String cli_type = request.getParameter("cli_type");
        System.out.println("type:" + type);
        gameurl = URLDecoder.decode(gameurl);
        final String mainUrl = String.valueOf(gameurl) + "?phone_no=" + ani + "&nick_name=" + nick_name + "&game_id=" + game_id + "&type=" + cli_type;
        try {
            db1 = new DbConnection();
            stmt = Loader.contentConn.createStatement();
            final String qry = "insert into tbl_online_play (ani,url,DATETIME,type,gameid) values ('" + ani + "','" + gameurl + "',now(),'" + cli_type + "','" + game_id + "')";
            System.out.println(qry);
            stmt.executeUpdate(qry);
            response.sendRedirect(mainUrl);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        out.flush();
        out.close();
    }
}
