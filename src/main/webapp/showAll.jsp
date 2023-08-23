<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="gen.*"%>
<%@page import="java.sql.*"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<!DOCTYPE HTML>

<%
String ani = (String) session.getAttribute("ani");
String catid = request.getParameter("_id");
String catergory = request.getParameter("nm");
%>
<head>
<jsp:include page="head.jsp" />
</head>
<%
DataCollector coll = new DataCollector();
OnlineGames online = new OnlineGames();
%>
<body>
	<div id="page-transitions">

		<div class="page-preloader page-preloader-dark">
			<div class="spinner"></div>
		</div>

		<jsp:include page="nav.jsp" />


		<!-- Main Small Icon Sidebar -->
		<div class="sidebar-menu sidebar-dark">
			<jsp:include page="siderbar.jsp" />
		</div>


		<div id="page-content" class="header-clear-large">
			<div id="page-content-scroll">
				<!--Enables this element to be scrolled -->


				<div class="staff-slider">
					<div class="cus-heading">Games</div>
					<div class="swiper-wrapper">
						<%
						ResultSet res = coll.getGamesByCatName(catergory);
						if (res.next()) {
							res.beforeFirst();
							while (res.next()) {
								String gameid = res.getString("gameid");
								String cat_id = res.getString("cat_id");
						%>
						<div class="swiper-slide">
							<a
								href="<%="/Logger?ani=" + ani + "&url=" + res.getString("gameurl") + "&type=" + res.getString("status") + "&gameid="
		+ gameid + "&cat_id=" + cat_id + ""%>"
								class="column-center-image"> <img class="col-img-2 img"
								src="<%=res.getString("imgurl")%>" alt="img">
							</a>
							<p class="video_title"><%=res.getString("gamename")%></p>
						</div>
						<%
						}
						} else {
						System.out.println("Checked .........");
						%>
						<p>New Games Comming Soon...............</p>
						<%
						}
						%>
						<%
						String getGames = online.getgameByCatid(catid);
						JSONObject jsonObjNew = new JSONObject(getGames);
						String responseCode1 = jsonObjNew.get("code").toString();
						if (responseCode1.equalsIgnoreCase("200")) {
							JSONArray arr1 = jsonObjNew.getJSONArray("data");
							String value = "0";

							for (int j = 0; j < arr1.length(); j++) {

								String gameurl = arr1.getJSONObject(j).getString("url");
								String type = arr1.getJSONObject(j).getString("category");
								String thumbnailUrl = arr1.getJSONObject(j).getString("thumbnailUrl");
								String title = arr1.getJSONObject(j).getString("title");
								String gameid = arr1.getJSONObject(j).getString("id");
						%>
						<div class="swiper-slide">
							<a
								href="<%="/OnlineLogger?ani=" + ani + "&url=" + gameurl + "&type=" + type + "&gameid=" + gameid + "&cat_id=" + catid
		+ "&imgurl= " + thumbnailUrl + ""%>"
								class="column-center-image"> <img class="col-img-2 img"
								src="<%=thumbnailUrl%>" alt="img">
							</a>
							<p class="video_title"><%=title%></p>
						</div>
						<%
						}
						}
						%>

					</div>
				</div>


				<footer>
					<div class="container">
						<div class="row">
							<div class="col-md-12">
								<p>&copy; copyright All Right Reserved.</p>
							</div>
						</div>
					</div>
				</footer>

				<div class="clear"></div>


			</div>
		</div>

		<a href="#" class="back-to-top-badge"><i
			class="ion-android-arrow-dropup"></i>Back to Top</a>

	</div>
	<script type="text/javascript" src="scripts/jquery.js"></script>
	<script type="text/javascript" src="scripts/plugins.js"></script>
	<script type="text/javascript" src="scripts/custom.js"></script>
	<script>
		var swiper = new Swiper('.cusslides', {
			spaceBetween : 30,
			// autoplay: {
			//     delay: 2500,
			//     disableOnInteraction: false,
			// },
			pagination : {
				el : '.swiper-pagination',
				clickable : true,
			},
		});
	</script>
</body>