<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="meta.jsp" />
<title>Home</title>
</head>
<body>
<jsp:include page="Navbar.jsp" />
<jsp:include page="Header.jsp" />
<%
  // declare variables for form data.
  String savedSearch = "''";
  String checked = "";
  Cookie cookies[] = request.getCookies();
  
  // loop though cookies to see if there is a saved cookie.
  for(Cookie cookie: cookies){
	  if(cookie.getName().equals("allowSavedSearch")){
		  // Replace data with cookie value.
		  savedSearch = "'" + cookie.getValue().replace('+', ' ') + "'";
		  checked = "checked";
	  }
  }
%>
<section>
  <div class="container">
    <div class="row">
      <div class="col-lg-8 mx-auto">
      	<h1>Search departures</h1>
		<form action="query" method="post">
		  <p> Station name: <input type="text" name="station" value=<%= savedSearch %> /> </p>
		  <p> <input type="checkbox" name="allowCookie" value="true" <%= checked %>/> 
     	  <label for="allowCookie">I allow the use of cookies to save my latest search.</label> </p>
   		  <input type="submit" value="Search">
		</form>
      </div>
    </div>
  </div>
</section>
<jsp:include page="Footer.jsp" />
</body>
</html>