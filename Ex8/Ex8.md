## Ex:8 SHA-1

#### Nimish S
#### 312217104098

**Program:**

```java
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.*;
import java.util.*;

public class SHA1Hash {
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
            byte[] messageDigest = md.digest(input.getBytes());     
            BigInteger num = new BigInteger(1, messageDigest);      
            String hashedText = num.toString(16);                   
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
```

**Output:**

![output](/Users/nimish/Desktop/1.png)