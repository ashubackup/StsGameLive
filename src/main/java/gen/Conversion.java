// 
// Decompiled by Procyon v0.5.36
// 

package gen;

import java.io.IOException;
import java.net.MalformedURLException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.json.JSONObject;
import java.sql.Connection;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Conversion
{
    String resp;
    
    public Conversion() {
        this.resp = "{status:\"0\",newid;\"0\"}";
    }
    
    private static String get_Time() {
        final SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(sdf.format(timestamp));
        return sdf.format(timestamp);
    }
    
    private String get_rand() {
        int check = 0;
        int rand_int1 = 0;
        String rand = "";
        while (check == 0) {
            rand_int1 = Math.abs(ThreadLocalRandom.current().nextInt());
            rand = String.valueOf(rand_int1);
            final String get = this.getClikid(rand);
            System.out.println(rand.length());
            System.out.println("getValue" + get);
            if (rand.length() <= 10 && get.equalsIgnoreCase("0")) {
                ++check;
            }
        }
        return rand;
    }
    
    public static void main(final String[] args) {
        System.out.println(new Conversion().get_rand());
    }
    
    public String insertLogs(final String clickid, final Connection conn) {
        final String newid = "1";
        final JSONObject obj = new JSONObject();
        final String message = "";
        final ResultSet rs = null;
        try {
            final String instQry = "insert into tbl_conv_logs(clickid,createddatetime,modifieddatetime,provider,service) values (?,now(),now(),'CPA','games')";
            final PreparedStatement statement = conn.prepareStatement(instQry, 1);
            statement.setString(1, clickid);
            statement.executeUpdate();
            Throwable t = null;
            try {
                final ResultSet generatedKeys = statement.getGeneratedKeys();
                try {
                    if (generatedKeys.next()) {
                        System.out.println("Genereated " + generatedKeys.getLong(1));
                        obj.put("status", (Object)"1");
                        obj.put("newid", generatedKeys.getLong(1));
                    }
                    else {
                        obj.put("status", (Object)"0");
                        obj.put("newid", (Object)"0");
                    }
                }
                finally {
                    if (generatedKeys != null) {
                        generatedKeys.close();
                    }
                }
            }
            finally {
                if (t == null) {
                    final Throwable exception=null;
                    t = exception;
                }
                else {
                    final Throwable exception=null;
                    if (t != exception) {
                        t.addSuppressed(exception);
                    }
                }
            }
            this.resp = obj.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.resp;
    }
    
    public void updateLogs(final String id, final String ani, final String json, final String service, final String service_status, final String url, final String url_hit_status) {
        try {
            final String instQry = "update tbl_conv_logs set svc_name='" + service + "',service_status='" + service_status + "',url_hit_status = '" + url_hit_status + "',modifieddatetime=now(),url='" + url + "',json = '" + json + "',status ='1',ani='" + ani + "' where newid='" + id + "' and status = '0'";
            final PreparedStatement statement = Loader.contentConn.prepareStatement(instQry);
            statement.executeUpdate();
            System.out.println(instQry);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getClikid(final String id) {
        String new_id = "0";
        try {
            final String instQry = "select * from  tbl_conv_logs where newid = '" + id + "'";
            final PreparedStatement statement = Loader.contentConn.prepareStatement(instQry);
            final ResultSet res = statement.executeQuery();
            if (res.next()) {
                new_id = res.getString("clickid");
            }
            else {
                new_id = "0";
            }
            System.out.println(instQry);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new_id;
    }
    
    public void sendSMS(final String id, final String ani, final String jsonObj, final String service, final String service_status) {
        final String getid = this.getClikid(id);
        final String https_url = "http://track.opticks.io/conversion?key=BB9A442EB351F96FF557185B8DB35A7373A87B388A5B562AF2D647FCC40C7B29&click_id=" + getid;
        System.out.println(https_url);
        try {
            final URL url = new URL(https_url);
            final HttpURLConnection con = (HttpURLConnection)url.openConnection();
            System.out.println("****** Content of the URL ********");
            con.setRequestMethod("GET");
            BufferedReader br = null;
            final String Fininput = "";
            final int result = con.getResponseCode();
            System.out.println("THis is result" + result);
            if (result == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            this.updateLogs(id, ani, jsonObj, service, service_status, https_url, Integer.toString(result));
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
