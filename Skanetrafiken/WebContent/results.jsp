<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="data.DataBean, data.Line"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="meta.jsp" />
<title>Search Results</title>
</head>
<body>
<jsp:include page="Navbar.jsp" />
<section>
  <div class="container">
    <div class="row">
      <div class="col-lg-8 mx-auto">
		<h2>Departures</h2>
		<%
		  // Get model with data.
		  DataBean bean = (DataBean) request.getAttribute("dataBean");
		  out.print("<h3>Station: " + bean.getStation() + "</h3>");
		%>
	    <hr class='light'>
	  </div>
	  <div class="col-lg-8 mx-auto">
		<ul>
		
		  <%
		    // Loop through all data lines and write the information.
		    for(Line line : bean.getLines()){
		   	  out.print("<li> <div>Line: " + line.getLine() +
		   			  	"</div> <div>End Station: " + line.getDestination() +
		   				"</div> <div>Time: " + line.getTime() + 
		   				"</div> <div>Position: " + line.getPosition() +
		   				"</div> <div>Type: " + line.getLineType() +
		   				"</div> <div>Date: " + line.getDate() + 
		   				"</div> <hr class='light'> </li>");
		    }
		
		  %>
		
		</ul>
      </div>
    </div>
  </div>
</section>
<jsp:include page="Footer.jsp" />
</body>
</html>