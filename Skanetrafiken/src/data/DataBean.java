package data;

import java.util.ArrayList;

/*
 * Model Class for holding data.
 */
public class DataBean {
	// Declaration of variables.
	ArrayList<Line> lines = new ArrayList<Line>();
	String station = "";
	
	/*
	 * Setters.
	 */
	public void addLine(String line, String time, String date, String position, String destination, String lineType) {
		lines.add(new Line(line, time, date, position, destination, lineType));
	}
	
	public void setStation(String station) {
		this.station = station;
	}

	/*
	 * Getters.
	 */
	public ArrayList<Line> getLines() {
		return lines;
	}
	
	public String getStation() {
		return station;
	}
	
}

