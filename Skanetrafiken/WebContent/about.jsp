<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="meta.jsp" />
<title>About</title>
</head>
<body>
<jsp:include page="Navbar.jsp" />
<jsp:include page="Header.jsp" />
  <section>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 mx-auto">
	      <h2>About this application</h2>
	      <p>This application was made using an API from Skånetrafiken. It gets any departing public transportation by Skånetrafiken from any station they can depart from. To use type in the name of the station that you want to depart from and the application will give you a list of departures from the station. </p>
	      <p>If you allow the use of cookies the last search data will be saved and the form on the home page will be filled out with the information the next time it is loaded. You can always opt out by deselecting the check box on your next search this will time out the cookie.</p>
	    </div>
      </div>
    </div>
  </section>
<jsp:include page="Footer.jsp" />
</body>
</html>