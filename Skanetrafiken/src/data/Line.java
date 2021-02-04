package data;

/*
 * Class which holds information of one line.
 */
public class Line {
	// Data variables.
	String line = null;
	String time = null;
	String date = null;
	String position = null;
	String destination = null;
	String lineType = null;
	
	/*
	 * Constructor which accepts the data values.
	 */
	public Line(String line, String time, String date, String position, String destination, String lineType) {
		this.line = line;
		this.time = time;
		this.date = date;
		this.position = position;
		this.destination = destination;
		this.lineType = lineType;
	}

	/*
	 * Getters
	 */
	public String getLine() {
		return line;
	}

	public String getTime() {
		return time;
	}

	public String getDate() {
		return date;
	}

	public String getPosition() {
		return position;
	}

	public String getDestination() {
		return destination;
	}

	public String getLineType() {
		return lineType;
	}
}

