import java.util.*;
import java.math.*; 
import java.nio.charset.Charset;

class PublicKey {
	BigInteger n, e;
	
	public PublicKey(BigInteger n, BigInteger e) {
		this.n = n;
		this.e = e;
	}

	public void print(){
		System.out.println("PUBLIC_KEY");
		System.out.println("[n]\n"+this.n.toString());
		System.out.println("[e]\n"+this.e.toString()+"\n");
	}
}

class PrivateKey {
	BigInteger n, d;
	
	public PrivateKey(BigInteger n, BigInteger d) {
		this.n = n;
		this.d = d;
	}

	public void print(){
		System.out.println("PRIVATE_KEY");
		System.out.println("[n]\n"+this.n.toString());
		System.out.println("[d]\n"+this.d.toString()+"\n");
	}
}

class RSA {
	public static void main(String[] args) {
		int choice = -1, bitlength = 512;
		Charset charset = Charset.forName("ASCII");
		String inputString = null;
		BigInteger p, q, n, d, e, phi;
		PublicKey publicKey;
		PrivateKey privateKey;

		Scanner scan = new Scanner(System.in);

		// RSA Variables
		p = BigInteger.probablePrime(bitlength, new Random());
		q = BigInteger.probablePrime(bitlength, new Random());
		n = p.multiply(q);
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength / 2, new Random());
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);

      	// Create Private / Public Keys
	 	publicKey = new PublicKey(n, e);
		privateKey = new PrivateKey(n, d);

		// privateKey.print();
		// publicKey.print();

		byte[] encrypted = null;
		do {
			System.out.println("\nMENU\n0 INPUT\n1 Encrypt\n2 Decrypt\n3 Exit\n");
			choice = scan.nextInt();
			if (choice == 0) {
				System.out.print("ENTER INPUT:  \t");
				inputString = scan.next();
				System.out.println("BYTES: \t\t"+bytesToString(inputString.getBytes(charset)));
			}

			else if(choice == 1) {
				encrypted = encrypt(inputString.getBytes(charset), publicKey);
				System.out.println("ENCRYPTED: \n"+bytesToString(encrypted));
			}

			else if (choice == 2) {
				byte[] decrypted = decrypt(encrypted, privateKey);
				String decryptedBytes = bytesToString(decrypted);
				System.out.println("BYTES: \t\t"+decryptedBytes);
				System.out.println("DECRYPTED:   \t"+ new String(decrypted));
			}
		} while (choice != 3);
	}

	 

    // Encrypt
    public static byte[] encrypt(byte[] message, PublicKey publicKey) {
		return (new BigInteger(message)).modPow(publicKey.e, publicKey.n).toByteArray();
	}

    // Decrypt
    public static byte[] decrypt(byte[] cipher, PrivateKey privateKey) {
		return (new BigInteger(cipher)).modPow(privateKey.d, privateKey.n).toByteArray();
	}
    
	private static String bytesToString(byte[] encrypted) {
        String res = "";
        for (byte b : encrypted) {
            res += Byte.toString(b)+" ";
        }
        return res;
    }
}