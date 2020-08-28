import java.util.*;

class Vigenere {

	public static void main(String args[]) {
		String inputStr, key;
		int inputLen;

		Scanner scan = new Scanner(System.in);

		System.out.print("INPUT STRING (lower case): ");
		inputStr = scan.nextLine();
		inputLen = inputStr.length();
		System.out.print("INPUT KEY (lower case): ");
		key = scan.nextLine();
		key = generateKey(key, inputLen);

		System.out.print("0-ENCRYPT/1-DECRYPT: ");
		int choice = scan.nextInt();

		if(choice == 0){
			String cipher = encrypt(inputStr, inputLen, key);
			System.out.println(cipher);
		} else {
			String plaintext = decrypt(inputStr, inputLen, key);
			System.out.println(plaintext);
		}
	}

	public static String encrypt(String inputStr, int inputLen, String key){
		String cipher = "";
		for(int i=0; i<inputLen; i++){
			int code = ((inputStr.charAt(i)-97) + (key.charAt(i)-97)) % 26;
			code += 97;
			cipher += (char)code;
		}
		return cipher;
	}
	public static String decrypt(String cipher, int inputLen, String key) { 
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