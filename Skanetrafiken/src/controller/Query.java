package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.APIHandler;
import data.DataBean;

/**
 * Servlet implementation class query
 */
@WebServlet("/query")
public class Query extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Query() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Check if the right info got sent
		response.setContentType("text/html");
		DataBean bean = new DataBean();
		String station = request.getParameter("station");
		String allowCookie = request.getParameter("allowCookie");
		
		// Check if user approved of cookies.
		if (allowCookie != null && allowCookie.equals("true")) {
			Cookie saveSearch = new Cookie("allowSavedSearch", station.replace(' ', '+'));
			response.addCookie(saveSearch);
		} else {
			Cookie cookies[] = request.getCookies();
			for(Cookie cookie: cookies) {
				if (cookie.getName().equals("allowSavedSearch")) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		
		// Send API URL and bean to APIHandler for processing.
		new APIHandler("http://www.labs.skanetrafiken.se/v2.2/querystation.asp?inpPointfr=" + station.replace(' ', '+'), bean);

		// Add the DataBean to the request and forward to results page.
		request.setAttribute("dataBean", bean);
		request.getRequestDispatcher("results.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
