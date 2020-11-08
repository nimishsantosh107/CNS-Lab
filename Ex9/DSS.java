import java.util.Scanner;
import java.security.*;

public class DSS {
    private static PublicKey publicKey;
    private static byte[] signature;

    public static byte[] createDigitalSignature(String msg) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        publicKey = pair.getPublic();
        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initSign(privateKey);
        sign.update(msg.getBytes());
        signature = sign.sign();
        return signature;
    }

    public static boolean verifySignature(String msg) throws Exception {
        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initVerify(publicKey);
        sign.update(msg.getBytes());
        // System.out.println(sign);
        boolean result = sign.verify(signature);
        return result;
    }

    public static void main(String args[]) throws Exception {
        int ch;
        String msg;
        byte[] signature;
        Scanner sc = new Scanner(System.in);
        System.out.println("DSS");
        while (true) {
            System.out.println("1. Create Digital Signature 2. Verify digital signature 3. Exit");
            ch = sc.nextInt();
            sc.nextLine();
            if (ch == 1) {
                System.out.println("Enter text:");
                msg = sc.nextLine();
                signature = createDigitalSignature(msg);
                System.out.println("Digital signature: [START]" + new String(signature, "UTF8") + "[END]");
            } else if (ch == 2) {
                System.out.println("Enter received text:");
                msg = sc.nextLine();
                if (verifySignature(msg)) {
                    System.out.println("Verified!");
                } else {
                    System.out.println("Not verified!");
                }
            } else {
                sc.close();
                System.exit(0);
            }
        }
    }
}