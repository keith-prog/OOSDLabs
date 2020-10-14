package lab1q1;
//Student Name 	: Oisin Cawley
//Student Id Number: 
//Date 			: Nov-2015
//Purpose 			: My first class implementation

public class lab1q1
{ // begin class ThermTest
	public static void main(String args[]) 
	{ // being main method

		Thermometer thermA = new Thermometer();	// Create an instance of our Thermometer class
		
		Thermometer thermB = new Thermometer(10.0);
		
		System.out.println("Temp. of Thermometer A is " + thermA.getCelsius() );
		thermA.setCelsius(20.0);
		System.out.println("Temp. of Thermometer A is " + thermA.getCelsius() );
		
		System.out.println("Temp. of Thermometer B is " + thermB.getCelsius() );
		
		System.out.println();
		
		//Get Fahrenheit
		
		double tempB = thermB.getFahrenheit(); // Should be 50F if set to 10C
		
		System.out.println("Temp of Thermometer is " + tempB);
		
		//Set Fahrenheit
		
		thermB.setFahrenheit(100);
		
		tempB = thermB.getCelsius();
		
		System.out.println("temp of thermB " + tempB);
		
		
		
	} // end main
} // end class ThermTest