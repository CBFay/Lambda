/*
 * The first challenged by foo.bar
 * Created 10.20 by CB Fay
 */

/*
Write a function answer(plaintext) that takes a string parameter and returns
a string of 1's and 0's representing the bumps and absence of bumps in the
input string. Your function should be able to encode the 26 lowercase letters,
handle capital letters by adding a Braille capitalization mark before that
character, and use a blank character (000000) for spaces. All signs on the 
space station are less than fifty characters long and use only letters and spaces.
*/

import java.util.*;

class LambdaChallenge1 {
	
	public static void main(String[] args) {
		
		String test = LambdaChallenge1.answer("The quick brown fox jumped over the lazy dog");
		System.out.println(test);
		
	}

	static String answer(String plaintext) {

		String encoded = "";

		Map<Character, String> BrailleCipher = new HashMap<Character, String>();
		
			BrailleCipher.put('a', "100000");
			BrailleCipher.put('b', "110000"); 
			BrailleCipher.put('c', "100100"); 
			BrailleCipher.put('d', "100110");
			BrailleCipher.put('e', "100010");
			BrailleCipher.put('f', "110100");
			BrailleCipher.put('g', "110110");
			BrailleCipher.put('h', "110010");
			BrailleCipher.put('i', "010100");
			BrailleCipher.put('j', "010110");
			BrailleCipher.put('k', "101000");
			BrailleCipher.put('l', "111000");
			BrailleCipher.put('m', "101100");
			BrailleCipher.put('n', "101110");
			BrailleCipher.put('o', "101010");
			BrailleCipher.put('p', "111100");
			BrailleCipher.put('q', "111110");
			BrailleCipher.put('r', "111010");
			BrailleCipher.put('s', "011100");
			BrailleCipher.put('t', "011110");
			BrailleCipher.put('u', "101001");
			BrailleCipher.put('v', "111001");
			BrailleCipher.put('w', "010111");
			BrailleCipher.put('x', "101101");
			BrailleCipher.put('y', "101111");
			BrailleCipher.put('z', "101011");
			BrailleCipher.put(' ', "000000");
		
		for (int i = 0; i < plaintext.length(); i++) {
			
			if (Character.isUpperCase(plaintext.charAt(i)))  {
				
				encoded += "000001"; // insert capitalization mark
				encoded += BrailleCipher.get(Character.toLowerCase(plaintext.charAt(i)));
				
			}
			
			else encoded += BrailleCipher.get(plaintext.charAt(i));
				
		}
		
		return encoded;
	}
	
}