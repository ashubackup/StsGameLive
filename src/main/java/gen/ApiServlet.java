// 
// Decompiled by Procyon v0.5.36
// 

package gen;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/ApiServlet" })
public class ApiServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String result = "{\"status\":\"0\",\"error\":\"action not defined\"}";
        try {
            final PrintWriter out = response.getWriter();
            if (request.getParameter("action").equalsIgnoreCase("1")) {
                result = new Conversion().insertLogs(request.getParameter("cid"), Loader.contentConn);
            }
            System.out.println("This is result :: " + result);
            out.print(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
