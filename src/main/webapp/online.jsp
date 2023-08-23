<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@page import="gen.*"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.*"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>



<!DOCTYPE HTML>

<head>
<jsp:include page="head.jsp" />

</head>
<%
	DataCollector coll = new DataCollector();
	OnlineGames online = new OnlineGames();
	List<String> catArray = new ArrayList<String>();
	String ani = (String) session.getAttribute("ani");

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
				<div class="container">
					<div class="bigslides">
						<h3 class="cus-heading">Highlights</h3>
						<div class="swiper-container cusslides">
							<div class="swiper-wrapper">
								<%
									ResultSet res3 = coll.getHighlightGames();
									while (res3.next()) {
										String gameurl = res3.getString("gameurl");
										String type = res3.getString("status");
										String gameid = res3.getString("gameid");
										String cat_id = res3.getString("cat_id");
								%>
								<div class="swiper-slide">
									<a
										href="<%="/Logger?ani=" + ani + "&url=" + gameurl + "&type=" + type + "&gameid=" + gameid + "&cat_id="
						+ cat_id + ""%>"
										class="column-center-image"> <img class="col-img-2 img"
										src="<%=res3.getString("banner_url")%>" alt="img">
									</a>
									<p class="video_title"><%=res3.getString("gamename")%></p>
								</div>
								<%
									}
									res3.close();
								%>



							</div>
							<!-- Add Pagination -->
							<div class="swiper-pagination"></div>
						</div>
					</div>


					<%
						String Fininput = online.getCategories();
						JSONObject jsonObj = new JSONObject(Fininput);
						String responseCode = jsonObj.get("code").toString();
						List<String> gamesArray = new ArrayList<String>();
						int count = 0;
						if (responseCode.equalsIgnoreCase("200")) {
							JSONArray arr = jsonObj.getJSONArray("data");
							for (int i = 0; i < arr.length(); i++) {
								int catValue=0;
								String catid = arr.getJSONObject(i).getString("id");

								String name = arr.getJSONObject(i).getString("name");
								//System.out.println("Catname -------- "+name+"-----"+catid);
					%>



					<div class="staff-slider" id="<%=name%>">
						<div class="cus-heading"><%=name%></div>
						<div class="swiper-wrapper">
		

							<%
							ResultSet resG = coll.getGamesByCatName(name);
							
							while(resG.next()){
								catValue++;
								catArray.add(name);
								
								String gameurl = resG.getString("gameurl");
								String type = resG.getString("status");
								String gameid = resG.getString("gameid");
								String cat_id = resG.getString("cat_id");
								System.out.println("Catname -------- "+name+"-----"+ resG.getString("gamename"));
								%>
								<div class="swiper-slide">
									<a
										href="<%="/Logger?ani=" + ani + "&url=" + gameurl + "&type=" + type + "&gameid="
											+ gameid + "&cat_id=" + catid + ""%>"
										class="column-center-image"> <img class="col-img-2 img"
										src="<%=resG.getString("imgurl")%>" alt="img">
									</a>
									<p class="video_title"><%=resG.getString("gamename")%></p>
								</div>
								
								<%
								
							}
							
								String getGames = online.getgameByCatid(catid);
										JSONObject jsonObjNew = new JSONObject(getGames);
										String responseCode1 = jsonObjNew.get("code").toString();
										if (responseCode1.equalsIgnoreCase("200")) {
											JSONArray arr1 = jsonObjNew.getJSONArray("data");
											String value = "0";
											if(arr1.length() == 0){
												System.out.println("------catValue "+name +"--"+ arr1.length());
												%>
												<script type="text/javascript">
														var myobj = document.getElementById("<%=name%>");
														myobj.remove();
												</script>
												<%
											}
											for (int j = 0; j < arr1.length(); j++) {

												String gameid = arr1.getJSONObject(j).getString("id");
												String title = arr1.getJSONObject(j).getString("title");
												
												for (String element : gamesArray) {
												

													if (element.equalsIgnoreCase(gameid)) {
														value = "1";
														continue;

													}
												}

												if (value.equalsIgnoreCase("1")) {
													value = "0";	
												} else if (value.equalsIgnoreCase("0")) {
													catValue++;
													
													gamesArray.add(gameid);

													String gameurl = arr1.getJSONObject(j).getString("url");
													String type = arr1.getJSONObject(j).getString("category");
													String thumbnailUrl = arr1.getJSONObject(j).getString("thumbnailUrl");
							%>
							<div class="swiper-slide">
								<a
									href="<%="/OnlineLogger?ani=" + ani + "&url=" + gameurl + "&type=" + type + "&gameid="
										+ gameid + "&cat_id=" + catid + ""%>"
									class="column-center-image"> <img class="col-img-2 img"
									src="<%=thumbnailUrl%>" alt="img">
								</a>
								<p class="video_title"><%=title%></p>
							</div>
							
							<%
								}
										if(catValue == 0){
											
											%>
											<script type="text/javascript">
													var myobj = document.getElementById("<%=name%>");
													myobj.remove();
											</script>
											<%
										}		
										

											}

										}
										
										
							%>
						</div>
					</div>
					<%
						}

						}
					%>

					<%
						ResultSet getCat = coll.getCategories();
						while(getCat.next()){
						if(catArray.contains(getCat.getString("cat_name"))){
							System.out.println(getCat.getString("cat_name"));
							continue;
						}
						
					%>		
						

					<div class="staff-slider" id="<%=getCat.getString("cat_name")%>">
						<div class="cus-heading"><%=getCat.getString("cat_name")%></div>
						<div class="swiper-wrapper">
					<%
		
						ResultSet resDG = coll.getGamesByCatName(getCat.getString("cat_name"));
							
							while(resDG.next()){
								
								String gameurl = resDG.getString("gameurl");
								String type = resDG.getString("status");
								String gameid = resDG.getString("gameid");
								String cat_id = resDG.getString("cat_id");
								%>
								<div class="swiper-slide">
									<a
										href="<%="/Logger?ani=" + ani + "&url=" + gameurl + "&type=" + type + "&gameid="
											+ gameid + "&cat_id=" + cat_id + ""%>"
										class="column-center-image"> <img class="col-img-2 img"
										src="<%=resDG.getString("imgurl")%>" alt="img">
									</a>
									<p class="video_title"><%=resDG.getString("gamename")%></p>
								</div>
								
								<%
								
							}
						%>		
						</div>
					</div>
					<%}
					%>
					<jsp:include page="footer.jsp" />
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
				autoplay : 4000,
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
		<script>
			// var appendNumber = 4;
			// var prependNumber = 1;
			var swiper = new Swiper('.playwin .swiper-container', {
				slidesPerView : 3,
				centeredSlides : true,
				spaceBetween : 5,
				autoplay : 10000,
				pagination : {
					el : '.swiper-pagination',
					clickable : true,
				},
				navigation : {
					nextEl : '.swiper-button-next',
					prevEl : '.swiper-button-prev',
				},
			});
		</script>
</body>