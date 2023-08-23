// 
// Decompiled by Procyon v0.5.36
// 

package gen;

import javax.servlet.RequestDispatcher;
import org.apache.http.HttpResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/Login" })
public class Login extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private DataCollector dc;
    String url;
    
    public Login() {
        this.dc = new DataCollector();
        this.url = "";
    }
    
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession(true);
        final String s_id = "2";
        try {
        	
        	System.out.println("Login servlet");
            String ani = request.getParameter("msisdn");
            Loader.contentConn = this.dc.checkConn(Loader.contentConn);
            if (ani == null) {
            	// For local comment this lines 
                this.url = this.dc.getServiceData(Loader.contentConn, "guid_url", s_id, "id");
                response.sendRedirect(new StringBuilder().append(this.url).toString());
            }
            else {
                final String svc_id = request.getParameter("svc_id");
                final String type = request.getParameter("type");
                ani = this.checkAni(ani);
                if (type != null) {
                    final String ref = request.getParameter("ref");
                    String output = "";
                    String flag = "";
                    final String URL = String.valueOf(this.dc.getServiceData(Loader.contentConn, "sms_url", s_id, "id")) + "/27" + ani + "/?ref=" + ref;
                    System.out.println("Url :: " + URL);
                    final DefaultHttpClient httpClient = new DefaultHttpClient();
                    final HttpPost postRequest = new HttpPost(URL);
                    System.out.println(postRequest);
                    final HttpResponse resp = (HttpResponse)httpClient.execute((HttpUriRequest)postRequest);
                    final BufferedReader br = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
                    System.out.println("Output from Server ....");
                    while ((output = br.readLine()) != null) {
                        System.out.println("output::" + output);
                        flag = output;
                    }
                    if (flag.equalsIgnoreCase("TRUE")) {
                        response.sendRedirect("thanks?flag=TRUE&ref=" + ref + "&ani=" + ani);
                    }
                    else {
                        response.sendRedirect("thanks?flag=FALSE&ref=" + ref + "&ani=" + ani);
                    }
                }
                else {
                    System.out.println("Ani :" + ani);
                    final String status = new DataCollector().getStatus(ani, this.dc.getServiceData(Loader.contentConn, "svc_name", svc_id, "id"));
                    if (status.equalsIgnoreCase("1")) {
                        session.setAttribute("ani", (Object)ani);
                        response.sendRedirect("/Online");
                    }
                    else if (status.equalsIgnoreCase("0")) {
                        final RequestDispatcher dispatcher = request.getRequestDispatcher("/info.jsp?msisdn=" + ani + "&svc_id=" + svc_id);
                        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
                    }
                    else if (status.equalsIgnoreCase("2")) {
                        this.url = this.dc.getServiceData(Loader.contentConn, "guid_url", s_id, "id");
                        response.sendRedirect(new StringBuilder().append(this.url).toString());
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            this.url = this.dc.getServiceData(Loader.contentConn, "guid_url", s_id, "id");
            response.sendRedirect(new StringBuilder().append(this.url).toString());
        }
    }
    
    public String checkAni(String ani) {
        final String countyCode = "27";
        if (ani.startsWith("0")) {
            ani = ani.substring(1);
        }
        if (ani.startsWith("+")) {
            ani = ani.substring(1);
        }
        final int len = countyCode.length();
        if (ani.substring(0, len).equals(countyCode)) {
            ani = ani.substring(len);
        }
        if (ani.contains(" ")) {
            ani = ani.replace(" ", "");
        }
        return ani;
    }
}
