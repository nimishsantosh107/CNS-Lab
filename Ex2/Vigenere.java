import java.util.*;

class Vigenere {

	public static void main(String args[]) {
		String inputStr = null, key = null;
		int inputLen = -1, choice = -1;
		Scanner scan = new Scanner(System.in);

		while(choice != 3) {
			System.out.println("0-Input\n1-Encrypt:\n2-Decrypt\n3-Exit");
			choice = scan.nextInt();
			switch(choice) {
				case 0: {
					System.out.println("Input String (lower case): ");
					inputStr = scan.next();
					inputLen = inputStr.length();
					System.out.println("Input Key (lower case): ");
					key = scan.next();
					key = generateKey(key, inputLen);
					break;
				}
				case 1: {
					String cipher = encrypt(inputStr, inputLen, key);
					System.out.println(cipher);
					break;
				}
				case 2: {
					String plaintext = decrypt(inputStr, inputLen, key);
					System.out.println(plaintext);
					break;
				}
			}
		}
	}

	public static String encrypt(String inputStr, int inputLen, String key){
		// Ei = (Pi + Ki) mod 26
		String cipher = "";
		for(int i=0; i<inputLen; i++){
			int code = ((inputStr.charAt(i)-97) + (key.charAt(i)-97)) % 26;
			code += 97;
			cipher += (char)code;
		}
		return cipher;
	}
	public static String decrypt(String cipher, int inputLen, String key) { 
		// Di = (Ei - Ki + 26) mod 26
	    String plaintext=""; 
	    for (int i = 0 ; i < inputLen; i++) { 
	        int x = ((cipher.charAt(i)-97) -  (key.charAt(i)-97) + 26) %26;  
	        x += 97; 
	        plaintext+=(char)(x); 
	    } 
	    return plaintext; 
	} 
	
	public static String generateKey(String key, int inputLen){
		String modifiedKey = "";
		for (int i=0; i<inputLen; i++){
			modifiedKey += key.charAt(i%key.length());
		}
		return modifiedKey;
	}
}