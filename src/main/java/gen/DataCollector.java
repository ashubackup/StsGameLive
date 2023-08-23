// 
// Decompiled by Procyon v0.5.36
// 

package gen;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataCollector
{
    public ResultSet getCategories() {
        ResultSet res = null;
        try {
            final String query = "select * from tbl_game_cat where status = '1' ";
            final PreparedStatement ps = Loader.contentConn.prepareStatement(query);
            res = ps.executeQuery();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
    public ResultSet getGamesByCatID(final String _id) {
        ResultSet res = null;
        try {
            final String query = "select * from tbl_portal_game where status in( '1','99','98') and cat_id='" + _id + "' order by year(datetime) desc";
            final PreparedStatement ps = Loader.contentConn.prepareStatement(query);
            res = ps.executeQuery();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
    public ResultSet getTrendingGames() {
        ResultSet res = null;
        try {
            final String query = "select * from tbl_portal_game where status = '99'  order by year(datetime) desc limit 6";
            final PreparedStatement ps = Loader.contentConn.prepareStatement(query);
            res = ps.executeQuery();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
    public ResultSet getHighlightGames() {
        ResultSet res = null;
        try {
            final String query = "select * from tbl_portal_game where status = '98'  order by year(datetime) desc limit 6";
            final PreparedStatement ps = Loader.contentConn.prepareStatement(query);
            res = ps.executeQuery();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
    public ResultSet getGamesByCatName(final String category) {
        ResultSet res = null;
        try {
            final String query = "select * from tbl_portal_game where status not in ('0') and category='" + category + "'";
            final PreparedStatement ps = Loader.contentConn.prepareStatement(query);
            res = ps.executeQuery();
            System.out.println(query);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
    public Connection checkConn(Connection conn) {
        if (conn == null) {
            conn = new DbConnection().getDatabse();
        }
        return conn;
    }
    
    public String getServiceData(final Connection conn, final String field, final String data, final String checkData) {
        try {
            final String query = "select " + field + " from tbl_service_master where " + checkData + "='" + data + "'";
            final ResultSet res = this.getResultSet(conn, query);
            if (res.next()) {
                return res.getString(new StringBuilder().append(field).toString());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public ResultSet getResultSet(final Connection conn, final String query) {
        ResultSet res = null;
        try {
            final PreparedStatement ps = conn.prepareStatement(query);
            System.out.println("query ---- " + query);
            res = ps.executeQuery();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
    public boolean UpdateQuery(final Connection conn, final String query) {
        try {
            System.out.println(query);
            final PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public String getStatus(final String ani, final String servcie) {
        String State = "";
        try {
            final Statement stmt = Loader.contentConn.createStatement();
            final String chkqry = "select * from tbl_subscription where ani='" + ani + "' and service_type='" + servcie + "'  ";
            System.out.println(chkqry);
            final ResultSet rs = stmt.executeQuery(chkqry);
            if (rs.next()) {
                State = this.getUserState(ani, servcie);
            }
            else {
                State = "2";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return State;
    }
    
    public String getUserState(final String ani, final String service) {
        String State = "0";
        try {
            Statement stmt = null;
            stmt = Loader.contentConn.createStatement();
            int cnt = 0;
            final String subQry = "select count(1) as cnt from tbl_subscription where ani='" + ani + "' and service_type='" + service + "' " + "and date(next_billed_date)>= date(now())";
            System.out.println("subQry::::" + subQry);
            final ResultSet rssub = stmt.executeQuery(subQry);
            if (rssub.next()) {
                cnt = rssub.getInt(1);
                System.out.println("cnt~~" + cnt);
            }
            if (cnt > 0) {
                State = "1";
            }
            else {
                State = "0";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return State;
    }
}
