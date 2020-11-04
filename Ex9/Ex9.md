## Ex9: Digital Signature Standard (DSS)

#### Nimish S
#### 312217104098

**Aim:** To implement the DSS algorithm

**Program:**

1. Main.java
```java
import java.util.*;
class Main {
    public static void main(String[] args) throws Exception {
        int choice;
        Scanner sc = new Scanner(System.in);
        DSS dss = new DSS();
        do {
            System.out.print("1. Create Digital Signature\n2. Verify Digital Signature\n3. Exit\n");
            choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                System.out.print("ENTER KEY: ");
                String plainText = sc.nextLine();
                String signature = dss.createDigitalSignature(plainText);
                System.out.println("DIGITAL SINGATURE: " + signature);
            } else if (choice == 2) {
                System.out.print("ENTERY KEY: ");
                String verificationText = sc.nextLine();
                boolean verified = dss.verifyDigitalSignature(verificationText);
                if (verified) System.out.println("\nSIGNATURE VERIFIED\n");
                else System.out.println("\nSIGNATURE ERROR\n");
            }
        } while (choice != 3);
    }
}
```

2. DSS.java
```java
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
public class DSS {
    public byte[] signature;
    public PublicKey pubkey;
    public String createDigitalSignature(String plainText) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair();
        PrivateKey privKey = pair.getPrivate();
        pubkey = pair.getPublic();
        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initSign(privKey);
        byte[] bytes = plainText.getBytes();
        sign.update(bytes);
        signature = sign.sign();
        return new String(signature, "UTF8");
    }
    public boolean verifyDigitalSignature(String verificationText) throws Exception {
        Signature sign = Signature.getInstance("SHA256withDSA");
        byte[] bytes = verificationText.getBytes();
        sign.initVerify(pubkey);
        sign.update(bytes);
        boolean verified = sign.verify(signature);
        return verified;
    }
}
```

**Output:**
![output](/Users/nimish/Desktop/1.png)

**Result:** DSS algorithm was implemented and executed.