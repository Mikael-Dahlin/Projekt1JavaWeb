package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/*
 * Class that handles the API request and response.
 */
public class APIHandler {
	// Build the API call by adding station and replace any spaces into a URL
		String URLtoSend;
		DataBean bean;
		
		public APIHandler(String UrlString, DataBean queryBean) {
			URLtoSend = UrlString;
			bean = queryBean;
			handleAPI();
		}
				
		private void handleAPI() {
			// Call a method to make a XMLdoc out of the response.		
			Document doc;
			try {
				doc = apiRequest(new URL(URLtoSend));

				// Add the response station to the DataBean.
				bean.setStation(doc.getElementsByTagName("Name").item(0).getTextContent());
	
				// Use the station id to call second API.
				NodeList nList = doc.getElementsByTagName("Id");
				String id = nList.item(0).getTextContent();
				URLtoSend = "http://www.labs.skanetrafiken.se/v2.2/stationresults.asp?selPointFrKey=" + id;
								
				// Call a method to make a XMLdoc out of the second response.
				doc = apiRequest(new URL(URLtoSend));
								
				// Create a Node list that gets everything in and under the "line" tag
				nList = doc.getElementsByTagName("Line");
								
				// loop through the content of the tag
				for (int temp = 0; temp < nList.getLength(); temp++) {
								
					// Save a node of the current list id 
					Node node = nList.item(temp);
									
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						NodeList cNodes = node.getChildNodes();
										
						// Declare the variables that should be found.
						String line = "";
						String time = "";
						String date = "";
						String pos = "";
						String dest = "";
						String lineType = "";
										
						// Loop through the nodes to find the information.
						for ( int i = 0; i<cNodes.getLength(); i++) {
							switch (cNodes.item(i).getNodeName()) {
								case "Name":
									line = cNodes.item(i).getTextContent();
									break;
								case "StopPoint":
									pos = cNodes.item(i).getTextContent();
									break;
								case "JourneyDateTime":
									String dateTime[] = cNodes.item(i).getTextContent().split("T");
									time = dateTime[1];
									date = dateTime[0];
									break;
								case "RealTime":
									if(cNodes.item(i).hasChildNodes()) {
										pos = cNodes.item(i).getFirstChild().getFirstChild().getTextContent();								
									}
									break;
								case "Towards":
									dest = cNodes.item(i).getTextContent();
									break;
								case "LineTypeName":
									lineType = cNodes.item(i).getTextContent();
									break;
							}
						}
						// Add new Line to DataBean.
						bean.addLine(line, time, date, pos, dest, lineType);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*
		 * 		
		 */
		
			
	/*
	 * Method for receiving an API response.
	 */
	private Document apiRequest(URL api_url) {
		// Create a HTTP connection to sent the GET request over
		HttpURLConnection linec;
		
		// a String to save the full response to use later
		String ApiResponse = "";
		try {
			linec = (HttpURLConnection) api_url.openConnection();
			linec.setDoInput(true);
			linec.setDoOutput(true);
			linec.setRequestMethod("GET");

			// Make a Buffer to read the response from the API
			BufferedReader in = new BufferedReader(new InputStreamReader(linec.getInputStream()));

			// a String to temporarily save each line in the response
			String inputLine;

			// loop through the whole response
			while ((inputLine = in.readLine()) != null) {

				// Save the temporary line into the full response
				ApiResponse += inputLine;
			}
					
			// Close the reader.
			in.close();
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convertStringToXMLDocument(ApiResponse);
	}

	/*
	 *  Method for converting a String to a XML doc, if it is in a XML format.
	 */
	private static Document convertStringToXMLDocument(String xmlString) {
		// Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// API to obtain DOM Document instance
		DocumentBuilder builder = null;
		try {
			// Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();

			// Parse the content to Document object
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
					
			// normalize the document.
			doc.getDocumentElement().normalize();

			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
