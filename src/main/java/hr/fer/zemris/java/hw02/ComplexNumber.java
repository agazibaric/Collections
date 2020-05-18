package hr.fer.zemris.java.hw02;

import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.atan2;
import static java.lang.Math.sqrt;
import static java.lang.Math.abs;
import static java.lang.Math.pow;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class represents a complex number. Once object is created it can not be modified.
 * It offers following complex number operation: 
 * adding, subtracting, multiplying, dividing, power and root operations.
 * It also offers several static factory methods for creating <code>ComplexNumber</code> object.
 * 
 * @author Ante Gazibarić
 * @version 1.0
 */
public class ComplexNumber {
	
	/**
	 * represents real part of complex number
	 */
	private double real;
	/**
	 * represents imaginary part of complex number
	 */
	private double imaginary;
	
	/**
	 * threshold for comparing complex numbers
	 */
	public static double THRESHOLD = 1E-6;
	
	/**
	 * Constructor for the given real and imaginary parts of complex number
	 * 
	 * @param real       real part of complex number
	 * @param imaginary  imaginary part of complex number
	 */
	public  ComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	/**
	 * Factory method for creating new object with given real part of complex number.
	 * Imaginary part of complex number is set to 0.
	 * 
	 * @param real  real part of complex number
	 * @return      new <code>ComplexNumber</code> object
	 */
	public static ComplexNumber fromReal(double real) {
		return new ComplexNumber(real, 0.0);
	}
	
	/**
	 * Factory method for creating new object with given imaginary part of complex number.
	 * Real part of complex number is set to 0.
	 * 
	 * @param imaginary  imaginary part of complex number
	 * @return			 new <code>ComplexNumver</code> object
	 */
	public static ComplexNumber fromImaginary(double imaginary) {
		return new ComplexNumber(0.0, imaginary);
	}
	
	/**
	 * Factory method for creating new object witch given magnitude and angle of complex number.
	 * 
	 * @param magnitude  magnitude of complex number
	 * @param angle		 angle of complex number
	 * @return           new <code>ComplexNumber</code>
	 */
	public static ComplexNumber fromMagnitudeAndAngle(double magnitude, double angle) {
		double real = magnitude * cos(angle);
		double imaginary = magnitude * sin(angle);
		return new ComplexNumber(real, imaginary);
	}
	
	/**
	 * Method adds two complex numbers and returns their sum as new object of type <code>ComplexNumber</code>.
	 * 
	 * @param other  complex number with which it's added
	 * @return       sum of two complex numbers as new object of type <code>ComplexNumber</code>
	 */
	public ComplexNumber add(ComplexNumber other) {
		return new ComplexNumber(
			this.real + other.real,
			this.imaginary + other.imaginary
			);
	}
	
	/**
	 * Method subtracts two complex numbers and returns result as new object of type <code>ComplexNumber</code>.
	 * 
	 * @param other  complex number witch which it's subtracted
	 * @return		 difference between two complex numbers as new object of type <code>ComplexNumber</code>
	 */
	public ComplexNumber sub(ComplexNumber other) {
		return new ComplexNumber(
			this.real - other.real,
			this.imaginary - other.imaginary
			);
	}
	
	/**
	 * Method multiplies two complex numbers and returns that product as new object of type <code>ComplexNumber</code>.
	 * 
	 * @param other  complex number with which it's multiplied by
	 * @return       product of two complex numbers as new object of type <code>ComplexNumber</code>
	 */
	public ComplexNumber mul(ComplexNumber other) {
		double real = this.real * other.real - this.imaginary * other.imaginary;
		double imaginary = this.real * other.imaginary + this.imaginary * other.real;
		return new ComplexNumber(real, imaginary);
	}
	
	/**
	 * Method divides two complex numbers and returns the quotient as new object of type <code>ComplexNumber</code>.
	 * 
	 * @param other  complex number witch which it's dividing
	 * @return       quotient of two complex numbers as new object of type <code>ComplexNumber</code>.
	 * @throws       <code>IllegalArgumentException</code> if real and imaginary part of <code>other</code> is zero
	 */
	public ComplexNumber div(ComplexNumber other) {
		double denominator = pow(other.real, 2) + pow(other.imaginary, 2);
		if(denominator == 0) 
			throw new IllegalArgumentException("Can not divide by zero. You entered: " + other);
		
		double realNumerator = this.real * other.real + this.imaginary * other.imaginary;
		double imaginaryNumerator = this.imaginary * other.real - this.real * other.imaginary;
		return new ComplexNumber(realNumerator / denominator, imaginaryNumerator / denominator);
	}
	
	/**
	 * Method returns result of power operation of complex number with given exponent as new object of type <code>ComplexNumber</code>.
	 * 
	 * @param n  exponent 
	 * @return   result of exponentiation of complex number as new object of type <code>ComplexNumber</code>
	 * @throws   <code>IllegalArgumentException</code> if <code>n</code> is less then zero
	 */
	public ComplexNumber power(int n) {
		if(n < 0)
			throw new IllegalArgumentException("Exponent must not be less then zero. You entered: " + n);
		
		double angle = n * getAngle();
		double magnitude = pow(getMagnitude(), n);
		return fromMagnitudeAndAngle(magnitude, angle);
	}
	
	/**
	 * Method returns roots of complex number as an array of objects of type <code>ComplexNumber</code>.
	 * 
	 * @param n  root degree
	 * @return   array of <code>ComplexNumber</code> that represents roots of complex number
	 * @throws   <code>IllegalArgumentException</code> if <code>n</code> is less then or equals to zero
	 */
	public ComplexNumber[] root(int n) {
		if(n <= 0) 
			throw new IllegalArgumentException("Root degree must be greater then zero. You entered: " + n);
		
		ComplexNumber[] roots = new ComplexNumber[n];
		double angle = getAngle();
		double magnitude = pow(getMagnitude(), -n);
		for (int k = 0; k < n; k++) {
			double newAngle = (angle + 2*k*Math.PI) / n;
			roots[k] = fromMagnitudeAndAngle(magnitude, newAngle);
		}
		return roots;
	}
	
	/**
	 * Method used for getting complex number from <code>String</code> input.
	 * It can parse string with the following forms: "a+bi" or "a-bi",
	 * where "a" and "b" can be any real numbers.
	 * Whitespaces between the numbers does not matter.
	 * 
	 * @param s string input that represents complex number
	 * @return  <code>ComplexNumber</code> 
	 * @throws  <code>IllegalArgumentException</code> if the given string is not in right format
	 */
	public static ComplexNumber parse(String s) {
		
		double real = 0.0, imaginary = 0.0;
		// Remove whitespaces
		String s1 = s.replaceAll("\\s", "");

		// Complex number that contains just real part
		Pattern p1 = Pattern.compile("([-]?[0-9]+\\.?[0-9]*?)$");
		// Complex number that contains just imaginary part
		Pattern p2 = Pattern.compile("([-]?[0-9]+\\.?[0-9]*?)(i$)");
		// Complex number that contains real and imaginary part
		Pattern p3 = Pattern.compile("([-]?[0-9]+\\.?[0-9]*?)([-|+][0-9]+\\.?[0-9]*?)(i$)");

		Matcher m1 = p1.matcher(s1);
		Matcher m2 = p2.matcher(s1);
		Matcher m3 = p3.matcher(s1);

		if (m3.find()) {
			real = Double.parseDouble(m3.group(1));
			imaginary = Double.parseDouble(m3.group(2));
		} else if (m2.find()) {
			imaginary = Double.parseDouble(m2.group(1));
		} else if (m1.find()) {
			real = Double.parseDouble(m1.group(1));
		} else {
			// No matching, invalid string input
			throw new IllegalArgumentException("Can not interepret input as a complex number. You entered: " + s);
		}
		
		return new ComplexNumber(real, imaginary);
	}
	
	/**
	 * Returns real part of complex number.
	 * 
	 * @return real part of complex number as <code>double</code>
	 */
	public double getReal() {
		return real;
	}
	
	/**
	 * Returns imaginary part of complex number.
	 * 
	 * @return imaginary part of complex number as <code>double</code>
	 */
	public double getImaginary() {
		return imaginary;
	}
	
	/**
	 * Returns magnitude of complex number.
	 * 
	 * @return magnitude of complex number as <code>double</code>
	 */
	public double getMagnitude() {
		return sqrt(pow(real, 2) + pow(imaginary, 2));
	}
	
	/**
	 * Returns angle of complex number.
	 * 
	 * @return angle of complex number as <code>double</code>
	 */
	public double getAngle() {
		return atan2(imaginary, real);
	}
	
	@Override
	public String toString() {
		if(real == 0) 
			return imaginary + "i";
		if(imaginary == 0)
			return "" + real;
		if(imaginary > 0)
			return real + " + " + imaginary + "i";
		return real + " - " + -imaginary + "i";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof ComplexNumber))
			return false;

		ComplexNumber other = (ComplexNumber) obj;

		if (abs(this.real - other.real) > THRESHOLD)
			return false;
		if (abs(this.imaginary - other.imaginary) > THRESHOLD)
			return false;
		
		return true;
	}
	
}
