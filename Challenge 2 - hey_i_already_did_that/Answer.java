/*
 * The second challenge issued by foo.bar
 * Created 10.23 by CB Fay
 */
 
package com.google.challenges; 

import java.util.Arrays;
import java.util.ArrayList;

public class Answer {

	public static int answer(String ID, int radix) {
		Answer obj = new Answer();
		return obj.checkCycles(ID, radix);
	}
	
	// creates an object that holds an an int, string, and radix
	private class Minion {

		private String mString;
		private int radix;
		private int number;

		// construct a Minion from a string
		Minion (String mString, int radix) {
			this.mString = mString;
			this.number = Integer.parseInt(mString);
			this.radix = radix;
		}
		
		// construct a Minion from an array of integers
		Minion (int[] numbers, int radix) {
			mString = "";
			for (int i = 0; i < numbers.length; i++) {
				this.mString += numbers[i];
			}
			this.number = Integer.parseInt(mString);
			this.radix = radix;
		}
		
		// construct a Minion from another Minion
		Minion (Minion m) {
			this.number = m.number();
			this.mString = m.mString();
			this.radix = m.radix;
		}

		// Minion functionality
		String mString() {
			return mString;
		}
		
		int number() {
			return number;
		}
		
		int getradix() {
			return radix;
		}
		
		int size() {
			return mString().length();
		}
		
		int intAt(int i) {
			return Integer.parseInt(mString().substring(i, i+1));
		}

	} // <- Minion class
	
	
	// represents Commander Lambda's Algorithm
	private Minion runAlgorithm(String ID, int radix) {

		Minion n = new Minion(ID, radix);
		Minion x = new Minion(getX(n));
		Minion y = new Minion(getY(x));
		Minion z = new Minion(getZ(x, y));
		return z;
		
	} // <- runAlgorithm method
	
	
	// *solution method*
	private int checkCycles(String ID, int radix) {

		// stores integers that are produced with Lambda's algorithm
		ArrayList<Integer> seenBefore = new ArrayList<Integer>();

		Minion n = new Minion(ID, radix); // contains initial input
		seenBefore.add(n.number());
		
		boolean unique = true; // prevents unreachable statements
		int cycleLength; // this will be returned

		Minion z = new Minion(runAlgorithm(ID, radix));

		while (unique) {

			// check to see if each iteration of z has already been stored in seenBefore
			for (int i = 0; i < seenBefore.size(); i++) {
				if (seenBefore.get(i) == z.number()) {
					unique = false;
					
					cycleLength = seenBefore.size() - i;
					return cycleLength;
				}
			}
			seenBefore.add(z.number());
			z = runAlgorithm(z.mString(), z.getradix());
			
		}
		return 0; // this should never execute
		
	} // <- answer method
	
	
	// "x has the digits of n in descending order..."
	private Minion getX(Minion n) {

		// copy n into a new array called x;
		int[] xArray = new int[n.size()];

			for (int i = 0; i < n.size(); i++) {
				xArray[i] = n.intAt(i);
			}

		// Reverse Quicksort xArray
		RevQS(xArray, 0, xArray.length-1);
		Minion x = new Minion(xArray, n.getradix());
		return x;

	} // <- getX method
	

	// "...and y has the digits of n in ascending order."
	private Minion getY(Minion x) {

		// Reverse Quicksort xArray
		Minion y = new Minion(strReverse(x.mString()), x.getradix());
		return y;

	} // <- getY method
	
	
	// "Define z = x - y..."
	private Minion getZ (Minion x, Minion y) {

		int[] xArray = new int[x.size()];
		for (int i = 0; i < x.size(); i++) {
			xArray[i] = x.intAt(i);
		}

		int[] yArray = new int[y.size()];
		for (int i = 0; i < y.size(); i++) {
			yArray[i] = y.intAt(i);
		}

		Minion z = new Minion(ArraySubtract(xArray, yArray, x.getradix()), x.getradix());
		return z;

	} // <- getZ method
	

	// used by getX
	private void RevQS(int[] items, int beginning, int end) {

		int left, right;
		int middle;
		int temp; // for swapping

		left = beginning; right = end;
		middle = items[(beginning+end)/2];

		do {
			while ((items[left] > middle) && (left < end)) left++;
			while ((items[right] < middle) && (right > beginning))  right--;

			if (left <= right) {

				temp = items[left];
				items[left] = items[right];
				items[right] = temp;

				left++; right--;
			}
		}
		while (left <= right);

		if(beginning < right) RevQS(items, beginning, right);
		if(end > left) RevQS(items, left, end);

	} // <- RevQS method
	

	// used by getY
	private String strReverse(String original) {

		String reversed = "";
			for (int i = 1; i < original.length(); i++)
				reversed += original.charAt(original.length()-i);

		reversed += original.charAt(0);
		return reversed;
		
	} // <- strReverse method
	

	// used by getZ
	private int[] ArraySubtract(int[] top, int[] bottom, int radix) {

		int[] dividend = new int[top.length];
		int columnVal = top.length; // the value of a 1 in the adjacent left column
		int borrowColumn;

		for (int i = top.length-1; i > 0; i--) {

			// if the current column can be subtracted without borrowing...
			if (top[i] >= bottom[i]) {
				dividend[i] = (top[i] - bottom[i]);
			}
			
			else { // borrow value from leftward columns
				top[i] += radix;
				borrowColumn = i-1;

				while (top[borrowColumn] == 0) {
					top[borrowColumn] += radix;
					borrowColumn--;
				}
				
				top[borrowColumn]--;
				dividend[i] = (top[i] - bottom[i]);
			}

		}
		dividend[0] = (top[0] - bottom[0]); // subtract the leftmost column
		return dividend;

	} // <- ArraySubtract method

	
} // <- Answer class