// 
// Decompiled by Procyon v0.5.36
// 

package gen;

import java.sql.DriverManager;
import java.sql.Connection;

public class DbConnection
{
    public Connection getDatabse() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://91.205.172.123:3306/ndotosts?useSSL=false&enabledTLSProtocols=TLSv1.2&serverTimezone=UTC", "root", "gloadmin123");
            System.out.println("ndoto Games DB connected");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
