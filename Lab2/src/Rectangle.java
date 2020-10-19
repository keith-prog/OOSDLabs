
public class Rectangle {

	private double width;
	private double length;
	private int area;
	private int perimeter; 
	
	public Rectangle() {
		setWidth(1);
		setLength(1);
		setArea(1);
		setPerimeter(1);
	}
	
	

	/**
	 * @param width
	 * @param length
	 */
	public Rectangle(double width, double length, int area, int perimeter) {
		setWidth(width);
		setLength(length);
		setArea(area);
		setPerimeter(perimeter);
	}



	/**
	 * @return the area
	 */
	public int getArea() {
		return area;
	}



	/**
	 * @param area the area to set
	 */
	public void setArea(int area) {
		this.area = area;
	}



	/**
	 * @return the perimeter
	 */
	public int getPerimeter() {
		return perimeter;
	}



	/**
	 * @param perimeter the perimeter to set
	 */
	public void setPerimeter(int perimeter) {
		this.perimeter = perimeter;
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
	
	public void printRectangle(int width, int length){
	
		for(int i = 1 ; i<=length; i++)
	     {
	       for(int j = 1; j<=width; j++)
	       {
	    	   if(i==1||i==length|| j==1|| j==width)
	    	   {
	    		   System.out.print("*");
	    	   }
	    	   else
	    	   {
	    		   System.out.print(" ");
	    	   }
	       }
	System.out.print("\n");
	     }
	}
}	
	

