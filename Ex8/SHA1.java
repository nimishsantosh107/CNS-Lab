import java.util.*;
import java.lang.*;

public class SHA1 {
	public int messageLength = 0;
	private int H0 = 0x67452301;
	private int H1 = 0xEFCDAB89;
	private int H2 = 0x98BADCFE;
	private int H3 = 0x10325476; 
	private int H4 = 0xC3D2E1F0;

	String messageToBits(String s) {
		byte[] bytes = s.getBytes();
		StringBuilder binary = new StringBuilder();
		for (byte b: bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
		}
		return binary.toString();
	}

	String padMessage(String s) {
		Integer ml = s.length();
		String mlString = String.format("%64s",
			Integer.toBinaryString(ml)).replace(' ', '0');
		s += "1";
		ml++;
		while (ml % 512 != 448) {
			s += "0";
			ml++;
		}
		s += mlString;
		return s;
	}

	String process(String s) {
		Integer[] w = new Integer[80];
		for (int i = 0; i < s.length(); i += 512) {
			String m = s.substring(i, i + 512);
			for (int j = 0; j < 16; j++) {
				w[j] = Integer.parseUnsignedInt(m.substring(32 * j, 32 * (j + 1)), 2);
			}
			for (int j = 16; j < 80; j++) {
				w[j] = Integer.rotateLeft(
					w[j - 3] ^ w[j - 8] ^ w[j - 14] ^ w[j - 16], 1
				);
			}
			Integer A = H0, B = H1, C = H2, D = H3, E = H4;
			Integer f, k;
			for (int j = 0; j < 80; j++) {
				if (j < 20) {
					f = (B & C) | (~B & D);
					k = 0x5A827999;
				} else if (j < 40) {
					f = B ^ C ^ D;
					k = 0x6ED9EBA1;
				} else if (j < 60) {
					f = (B & C) | (B & D) | (C & D);
					k = 0x8F1BBCDC;
				} else {
					f = B ^ C ^ D;
					k = 0xCA62C1D6;
				}
				Integer temp = Integer.rotateLeft(A, 5) + f + E + k + w[j];
				E = D;
				D = C;
				C = Integer.rotateLeft(B, 30);
				B = A;
				A = temp;
			}
			H0 += A;
			H1 += B;
			H2 += C;
			H3 += D;
			H4 += E;
		}
		return Integer.toHexString(H0) + Integer.toHexString(H1) +
			Integer.toHexString(H2) + Integer.toHexString(H3) +
			Integer.toHexString(H4);
	}

	String getMessageDigest(String s) {
		return process(padMessage(messageToBits(s)));
	}

	public static void main(String[] args) {
		SHA1 sha1 = new SHA1();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the message:");
		String word = sc.nextLine();
		System.out.println("Message digest:");
		System.out.println(sha1.getMessageDigest(word));
		sc.close();
	}
}