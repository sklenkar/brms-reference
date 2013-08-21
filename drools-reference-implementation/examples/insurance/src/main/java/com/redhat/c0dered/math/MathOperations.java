package com.redhat.c0dered.math;


public class MathOperations {

	public static MathResult add(float a, float b) {
		return new MathResult("Success!", Float.toString(a+b));
	}
	
	public static MathResult add(String a, String b) {
		try {
			float fa = Float.parseFloat(a);
			float fb = Float.parseFloat(b);
			return new MathResult("Success!",Float.toString(fa+fb));
		} catch (NumberFormatException nfe) {
			return new MathResult("Invalid Operand Error", "0");
		}
	}
	
	public static MathResult subtract(float a, float b) {
		return new MathResult("Success!", Float.toString(a-b));
	}
	
	public static MathResult subtract(String a, String b) {
		try {
			float fa = Float.parseFloat(a);
			float fb = Float.parseFloat(b);
			return new MathResult("Success!",Float.toString(fa-fb));
		} catch (NumberFormatException nfe) {
			return new MathResult("Invalid Operand Error", "0");
		}
	}
	
	public static MathResult multiply(float a, float b) {
		return new MathResult("Success!", Float.toString(a*b));
	}
	
	public static MathResult multiply(String a, String b) {
		try {
			float fa = Float.parseFloat(a);
			float fb = Float.parseFloat(b);
			return new MathResult("Success!",Float.toString(fa*fb));
		} catch (NumberFormatException nfe) {
			return new MathResult("Invalid Operand Error", "0");
		}
	}
	
	public static MathResult divide(float a, float b) {
		if(b == 0) {
			return new MathResult("Divide by Zero Error","0");
		} else {
			return new MathResult("Success!", Float.toString(a/b));
		}
	}
	
	public static MathResult divide(String a, String b) {
		try {
			float fa = Float.parseFloat(a);
			float fb = Float.parseFloat(b);
			if(fb == 0) {
				return new MathResult("Divide by Zero Error","0");
			} else {
				return new MathResult("Success!",Float.toString(fa/fb));
			}
		} catch (NumberFormatException nfe) {
			return new MathResult("Invalid Operand Error", "0");
		}
	}
}
