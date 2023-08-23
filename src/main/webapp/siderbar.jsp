<%@page import="gen.DataCollector"%>
<div class="sidebar-menu-scroll">
	<a class="current-menu" href="/index"><img src="images/icons/Icon-Home.PNG" /><em>Home</em></a>
	<a href="Subscription_notification.html"><img
		src="images/icons/Icon-Highlights.PNG" /><em>Highlights</em></a> <a
		href="subscriptions.html"><img src="images/icons/trophy.png" /><em>Play
			& Win</em></a> <a href="/Category?_id=2"><img src="images/icons/helicopter.png" /><em>Action</em></a>
	<a href="/Category?_id=8"><img src="images/icons/Icon-Quiz.PNG" /><em>Quiz</em></a>
	<a href="/Category?_id=4"><img src="images/icons/mountain.png" /><em>Adventure</em></a>
	<a href="/Category?_id=9"><img src="images/icons/Icon-Puzzle.PNG" /><em>Puzzle</em></a> <a
		href="/Category?_id=1"><img src="images/icons/cars.png" /><em>Racing</em></a>
	<a href="/Category?_id=7"><img src="images/icons/Icon-Lotto.PNG" /><em>Lotto</em></a> <a
		href="#"><img src="images/icons/terms-and-conditions.png" /><em>Terms &
			Conditions</em></a> <a href="#"><img src="images/icons/clock.png" /><em>Recently
			Played</em></a> <a href="#"><img src="images/icons/privacy.png" /><em>Privacy
			Policy</em></a>
			<a href="/Logout"><img src="images/icons/terms-and-conditions.png" /><em>Logout</em></a>

</div>
<%
	String ani = (String) session.getAttribute("ani");
	System.out.print("Session - " +ani);
	 if (ani == null) {
			request.setAttribute("errorMessage", "Session Expired");

		 getServletContext().getRequestDispatcher("/landing.jsp").forward(request, response);
	} 
%>