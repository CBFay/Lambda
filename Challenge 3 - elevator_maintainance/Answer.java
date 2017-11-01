package com.google.challenges;

public class Answer {
	
	public static String[] answer(String[] l) {
		VQuicksort solution = new VQuicksort();
		solution.sort(l);
		return l;
	}
	
	//  A Class that Quicksorts Elevator Versions
	private static class VQuicksort { 
		private String[] versions;  // instance variable
		
		public void sort(String[] values) {
			if (values == null) return; // don't sort Arrays with no elements
			if (values.length == 1) return; // don't sort Arrays with only one element
			
			this.versions = values;
			quicksort(0, values.length-1);
		}
		
		private void quicksort(int low, int high) {
			int i = low, j = high;
			String pivot = versions[(low + high)/2];
			
			while (i < j) {
				
				while (isBigVer(versions[i], pivot) == 2) i++;
				while (isBigVer(versions[j], pivot) == 1) j--;
				
				if (i <= j) { // swap
					String temp = versions[i];
					versions[i] = versions[j];
					versions[j] = temp;
					i++;
					j--;
				}
			}
			if (low < j) quicksort(low, j);
			if (i < high) quicksort(i, high);
		}
	} // <- VQuicksort class
	
	static private int isBigVer(String ver1, String ver2) { // receives version 1 and version 2
		int ver1_loc = 0; // version 1 location
		int ver2_loc = 0; // version 2 location
		
		int ver1_limit = ver1.length()-1;
		int ver2_limit = ver2.length()-1;
		
		for (int i = 0; i < 3; i++) { // for a maximum of 3 times...
			int ver1_val = verVal(ver1, ver1_loc);
			int ver2_val = verVal(ver2, ver2_loc);
			
			if (ver1_val > ver2_val) 		return 1; // if ver1 is bigger
			else if (ver1_val < ver2_val) 	return 2; // if ver2 is bigger
			else { // if they're equal
				if (ver2_loc >= ver2_limit && ver1_loc < ver1_limit) 	return 1;
				if (ver1_loc >= ver1_limit && ver2_loc < ver2_limit) 	return 2;
				if (ver1_loc >= ver1_limit && ver2_loc >= ver2_limit)	return 0;
			}
			
			while (ver1.charAt(ver1_loc) != '.' && ver1_loc != ver1_limit ) {
				ver1_loc++; // move the ver1_loc to the next '.'
			}
			
			while (ver2.charAt(ver2_loc) != '.' && ver2_loc != ver2_limit ) {
				ver2_loc++; // move the ver1_loc to the next '.'
			}
			
			ver1_loc++;
			ver2_loc++;
		} 
		return 0;
	} // <- isBigVer method
	
	// find the length of an integer that is followed by a "." in string form.
	static private int sizeVer(String ver) {
		int ver_size;
		for (ver_size = 0; ver_size < ver.length(); ver_size++) {
			if (ver.charAt(ver_size) == '.')
				return ver_size;
		}
		return ver_size;
	}
	
	// return the value of an integer contained in a string
	static private int verVal(String ver, int start_loc) {
		int size = sizeVer(ver.substring(start_loc));
		try {
			int value = Integer.parseInt(ver.substring(start_loc, start_loc+size));
			return value;
		} catch(NumberFormatException ex){
			return -1;
		}
	}
} // <- Answer class