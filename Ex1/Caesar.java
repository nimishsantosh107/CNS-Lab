
import java.util.*;

class Caesar {
	public static void main(String args[]) {
		int shift;
		String inputStr;
		
		
		Scanner scan = new Scanner(System.in);

		System.out.print("INPUT STRING (lower case): ");
		inputStr = scan.nextLine();
		System.out.print("INPUT SHIFT : ");
		shift = scan.nextInt();
				

		System.out.print("0-ENCRYPT/1-DECRYPT: ");
		int choice = scan.nextInt();

		// ENCRYPT
		if(choice == 0) {
			String cipher = "";
			for(int i=0; i<inputStr.length(); i++) {
				int code = (int)inputStr.charAt(i);
				code = ((code - 97 + shift) % 26) + 97;
				char codechar = (char)code;
				cipher += codechar;
			}
			System.out.println(cipher);
		}
		// DECRYPT
		else {
			String plaintext = "";
			for(int i=0; i<inputStr.length(); i++) {
				int code = (int)inputStr.charAt(i);

				code = ((code - 97 + (26-shift)) % 26) + 97;
				
				char codechar = (char)code;
				plaintext += codechar;
			}
			System.out.println(plaintext);
		}
	}
}