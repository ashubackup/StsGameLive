<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="gen.*"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<!DOCTYPE html>
<%
	OnlineGames online = new OnlineGames();
	String gid = request.getParameter("gid");

%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Games</title>
</head>
<body>
	
	<iframe style="height: 100vh; width: 100%;"
		src="http://games.gamepix.com/play/<%=gid %>?sid=3B499"
		width="100%" height="100vh" frameborder="0" scrolling="no"></iframe>


</body>
</html>