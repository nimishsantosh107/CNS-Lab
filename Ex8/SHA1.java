import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.*;
import java.util.*;
public class SHA1 {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter plaintext:");
        String text = s.next();
        System.out.println("SHA1 HASH: " + hash(text).toUpperCase());
        s.close();
    }

    public static String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");  
            // digest() method is called to calculate message digest             
            // of the input string returned as array of byte 
            byte[] messageDigest = md.digest(input.getBytes());     
            // This constructor is used to translate the sign-magnitude representation of a BigInteger into a BigInteger.
            BigInteger num = new BigInteger(1, messageDigest);      
            // Convert message digest into hex value 
            String hashedText = num.toString(16);                   
            // Add preceding 0s to make it 32 bit 
            while (hashedText.length() < 32) {
                hashedText = "0" + hashedText;
            }
            return hashedText;
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
}