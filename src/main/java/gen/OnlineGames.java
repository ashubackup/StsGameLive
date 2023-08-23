// 
// Decompiled by Procyon v0.5.36
// 

package gen;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OnlineGames
{
    public String getCategories() {
        final String https_url = "https://games.gamepix.com/categories?sid=3B499";
        try {
            final URL url = new URL(https_url);
            final HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader br = null;
            String Fininput = "";
            final int result = con.getResponseCode();
            if (result == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String input;
                while ((input = br.readLine()) != null) {
                    System.out.println(input);
                    Fininput = String.valueOf(Fininput) + input;
                }
                return Fininput;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public String getgameByCatid(final String catid) {
        final String https_url = "https://games.gamepix.com/games?sid=3B499&category=" + catid + "&order=d";
        try {
            final URL url = new URL(https_url);
            final HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader br = null;
            String Fininput = "";
            final int result = con.getResponseCode();
            if (result == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String input;
                while ((input = br.readLine()) != null) {
                    Fininput = String.valueOf(Fininput) + input;
                }
                return Fininput;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public String getSinglegameByid(final String gid) {
        final String https_url = "http://games.gamepix.com/game?sid=3B499&gid=" + gid;
        System.out.println(https_url);
        try {
            final URL url = new URL(https_url);
            final HttpURLConnection con = (HttpURLConnection)url.openConnection();
            System.out.println("****** Content of the URL ********");
            con.setRequestMethod("POST");
            BufferedReader br = null;
            String Fininput = "";
            final int result = con.getResponseCode();
            System.out.println("-------------" + result);
            if (result == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String input;
                while ((input = br.readLine()) != null) {
                    System.out.println(input);
                    Fininput = String.valueOf(Fininput) + input;
                }
                return Fininput;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
