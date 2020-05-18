package hr.fer.zemris.java.hw02.demo;

import hr.fer.zemris.java.hw02.ComplexNumber;

/**
 * Program that shows one way of use of <code>ComplexNumber</code> class.
 * 
 * @author Ante Gazibarić
 * @version 1.0
 */
public class ComplexNumberDemo {
	
	/**
	 * Main method. Arguments from command line are not used.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		
		ComplexNumber c1 = new ComplexNumber(2, 3);
		ComplexNumber c2 = ComplexNumber.parse("2.5-3i");
		ComplexNumber c3 = c1.add(ComplexNumber.fromMagnitudeAndAngle(2, 1.57)).div(c2).power(3).root(2)[1];
		System.out.println(c3);
		
	}
	
}
