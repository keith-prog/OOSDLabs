
public class Rectangle {

	private double width;
	private double length;
	
	public Rectangle() {
		setWidth(1);
		setLength(1);
	}
	
	

	/**
	 * @param width
	 * @param length
	 */
	public Rectangle(double width, double length) {
		setWidth(width);
		setLength(length);
	}



	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(double width) {
		if(width  > 0 && width < 40.0) {
			this.width = width;
		}else {
			System.out.println("The width is out of range!!!");
		}
			
		
	}

	/**
	 * @return the length
	 */
	public double getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(double length) {
		if(length>0 && length < 40.0) {
			this.length = length;
		}else {
			System.out.println("The length is out of range!!!");
		}
	}
	
	
}
