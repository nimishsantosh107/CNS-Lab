import java.util.*;
import java.lang.*;

class RailFence {
	public static void main(String args[]) {
		String inputStr = null;
		int choice = -1, key = -1;
		Scanner scan = new Scanner(System.in);

		while(choice != 3) {
			System.out.println("\n0-Input\n1-Encrypt:\n2-Decrypt\n3-Exit\n");
			choice = scan.nextInt();
			switch(choice) {
				case 0: {
					System.out.println("Input String (lower case): ");
					inputStr = scan.next();
					System.out.println("Input Key (lower case): ");
					key = scan.nextInt();
					break;
				}
				case 1: {
					String cipher = encrypt(inputStr, key);
					System.out.println("\nCIPHER   :   "+cipher);
					break;
				}
				case 2: {
					String plaintext = decrypt(inputStr, key);
					System.out.println("\nPLAINTEXT:   "+plaintext);
					break;
				}
			}
		}
	}

	public static String encrypt(String inputStr, int key) {
		int inputLen = inputStr.length();
		char[][] keyMat = new char[key][inputLen];
		//init keyMat
		for (int i=0; i<key; i++) {
			for(int j=0; j<inputLen; j++) {
				keyMat[i][j] = '-';
			}
		}
		char[] inputArr = inputStr.toCharArray();

		//write diagonally
		boolean direction = false;
		int row = 0;
		for (int i=0; i<inputLen; i++) {
			if(row == 0 || row == (key-1)) {direction = !direction;}
			
			keyMat[row][i] = inputArr[i];

			row = (direction)? ++row : --row;
		}
		printTable(keyMat, inputLen, key);

		//read horizontally
		char[] cipherArr = new char[inputLen];
		int index = 0;
		for (int i=0; i<key; i++) {
			for(int j=0; j<inputLen; j++) {
				if(keyMat[i][j] != '-'){
					cipherArr[index++] = keyMat[i][j];
				}
			}
		}
		String cipherStr = new String(cipherArr);
		return cipherStr;
	}

	public static String decrypt(String inputStr, int key) {
		int inputLen = inputStr.length();
		char[][] keyMat = new char[key][inputLen];
		//init keyMat
		for (int i=0; i<key; i++) {
			for(int j=0; j<inputLen; j++) {
				keyMat[i][j] = '-';
			}
		}

		boolean direction = false;
		int row = 0;

		//create placeholder
		for (int i=0; i<inputLen; i++) {
			if(row == 0 || row == (key-1)) {direction = !direction;}
			
			keyMat[row][i] = '*';

			row = (direction)? ++row : --row;
		}

		//write horizontally
		char[] inputArr = inputStr.toCharArray();
		int index = 0;
		for (int i=0; i<key; i++) {
			for(int j=0; j<inputLen; j++) {
				if(keyMat[i][j] == '*'){
					keyMat[i][j] = inputArr[index++];
				}
			}
		}
		printTable(keyMat, inputLen, key);

		//read diagonally
		char[] plaintextArr = new char[inputLen];
		direction = false;
		row = 0;index = 0;
		for (int i=0; i<inputLen; i++) {
			if(row == 0 || row == (key-1)) {direction = !direction;}
			
			plaintextArr[index++] = keyMat[row][i];

			row = (direction)? ++row : --row;
		}
		String plaintext = new String(plaintextArr);
		return plaintext;
	}

	// PRINT TABLE
	private static void printTable(char[][] table, int inputLen, int key){
		System.out.println("PRINTING TABLE: ");
		for (int i=0;i<key;i++){
			for (int j=0; j<inputLen; j++){
				System.out.print("  "+table[i][j]);
	 		}
			System.out.println("");
    	}
	}
}