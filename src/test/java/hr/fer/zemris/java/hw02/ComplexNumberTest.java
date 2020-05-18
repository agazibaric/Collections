package hr.fer.zemris.java.hw02;

import org.junit.Assert;
import org.junit.Test;

public class ComplexNumberTest {

	@Test
	public void forMagnitudeAndAngle() {
		double magnitude = Math.sqrt(2);
		double angle = Math.PI / 4;
		ComplexNumber n1 = ComplexNumber.fromMagnitudeAndAngle(magnitude, angle);
		
		double realExpexted = 1;
		double imaginaryExpexted = 1;
		
		Assert.assertEquals(realExpexted, n1.getReal(), ComplexNumber.THRESHOLD);
		Assert.assertEquals(imaginaryExpexted, n1.getReal(), ComplexNumber.THRESHOLD);
	}
	
	@Test
	public void forAddingComplexNumbers() {
		ComplexNumber n1 = new ComplexNumber(1, 1);
		ComplexNumber n2 = new ComplexNumber(1, 1);
		ComplexNumber n3 = n1.add(n2);
		
		double actualReal = n3.getReal();
		double actualImaginary = n3.getImaginary();
		double expectedReal = 2.0;
		double expectedImaginary = 2.0;
		
		Assert.assertEquals(expectedReal, actualReal, ComplexNumber.THRESHOLD);
		Assert.assertEquals(expectedImaginary, actualImaginary, ComplexNumber.THRESHOLD);
	}
	
	@Test
	public void forSubtractinComplexNumbers() {
		ComplexNumber n1 = new ComplexNumber(2, 2);
		ComplexNumber n2 = new ComplexNumber(1, 1);
		ComplexNumber n3 = n1.sub(n2);
		
		double actualReal = n3.getReal();
		double actualImaginary = n3.getImaginary();
		double expectedReal = 1.0;
		double expectedImaginary = 1.0;
		
		Assert.assertEquals(expectedReal, actualReal, ComplexNumber.THRESHOLD);
		Assert.assertEquals(expectedImaginary, actualImaginary, ComplexNumber.THRESHOLD);
	}
	
	@Test
	public void forMultiplyingComplexNumbers() {
		ComplexNumber n1 = new ComplexNumber(1, 1);
		ComplexNumber n2 = new ComplexNumber(2, 3);
		ComplexNumber n3 = n1.mul(n2);
		
		double actualReal = n3.getReal();
		double actualImaginary = n3.getImaginary();
		double expectedReal = -1.0;
		double expectedImaginary = 5.0;
		
		Assert.assertEquals(expectedReal, actualReal, ComplexNumber.THRESHOLD);
		Assert.assertEquals(expectedImaginary, actualImaginary, ComplexNumber.THRESHOLD);
	}
	
	@Test
	public void forDividingComplexNumbers() {
		ComplexNumber n1 = new ComplexNumber(3, 2);
		ComplexNumber n2 = new ComplexNumber(0, 1);
		ComplexNumber n3 = n1.div(n2);
		
		double actualReal = n3.getReal();
		double actualImaginary = n3.getImaginary();
		double expectedReal = 2.0;
		double expectedImaginary = -3.0;
		
		Assert.assertEquals(expectedReal, actualReal, ComplexNumber.THRESHOLD);
		Assert.assertEquals(expectedImaginary, actualImaginary, ComplexNumber.THRESHOLD);
	}
	
	@Test
	public void forPowerOperation() {
		ComplexNumber n1 = new ComplexNumber(1, 2);
		int exponent = 4;
		ComplexNumber n2 = n1.power(exponent);
		
		double actualReal = n2.getReal();
		double actualImaginary = n2.getImaginary();
		double expectedReal = -7.0;
		double expectedImaginary = -24.0;
		
		Assert.assertEquals(expectedReal, actualReal, ComplexNumber.THRESHOLD);
		Assert.assertEquals(expectedImaginary, actualImaginary, ComplexNumber.THRESHOLD);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void forPowerWithInvalidExponent() {
		ComplexNumber n1 = new ComplexNumber(1, 2);
		int exponent = -2;
		n1.power(exponent);
	}
	
	@Test
	public void forRootOperation() {
		ComplexNumber n1 = new ComplexNumber(1, 1);
		int rootDegree = 5;
		ComplexNumber[] roots = n1.root(rootDegree);
		double[] actualAngle = new double[rootDegree];
		double[] expectedAngle = new double[rootDegree];
		double[] actualMagnitude = new double[rootDegree];
		double[] expectedMagnitude = new double[rootDegree];
		double magnitude = Math.sqrt(2);
		double angle = Math.PI / 4;
		
		for (int k = 0; k < rootDegree; k++) {
			expectedAngle[k] = (angle + 2*k*Math.PI) / rootDegree;
			while(expectedAngle[k] > Math.PI) {
				expectedAngle[k] -=  2* Math.PI;
			}
			expectedMagnitude[k] = Math.pow(magnitude, -rootDegree);
			actualAngle[k] = roots[k].getAngle();
			actualMagnitude[k] = roots[k].getMagnitude();
			
			Assert.assertEquals(expectedAngle[k], actualAngle[k], ComplexNumber.THRESHOLD);
			Assert.assertEquals(expectedMagnitude[k], actualMagnitude[k], ComplexNumber.THRESHOLD);
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void forRootWithInvalidDegree() {
		ComplexNumber n1 = new ComplexNumber(1, 2);
		int exponent = 0;
		n1.root(exponent);
	}
	
}
