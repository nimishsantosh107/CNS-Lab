import java.util.*;
import java.math.*;

public class DH {
    public Vector < Long > genFactors(long phi) {
        Vector < Long > factors = new Vector < > ();
        while (phi % 2 == 0) {
            factors.add((long) 2);
            phi /= 2;
        }
        for (long i = 3; i <= Math.sqrt(phi); i += 2) {
            while (phi % i == 0) {
                factors.add(i);
                phi /= i;
            }
        }
        if (phi > 2) {
            factors.add(phi);
        }
        return factors;
    }

    public long getPrimitiveRoots(long p) {
        Vector < Long > factors = this.genFactors(p - 1);
        Vector < Long > primitiveRoots = new Vector <> ();
        for (long i = 2; i < p; i++) {
            boolean flag = false;
            for (Long l: factors) {
                BigInteger bigI = BigInteger.valueOf(i);
                BigInteger bigPhiByL = BigInteger.valueOf((p - 1) / l);
                BigInteger bigP = BigInteger.valueOf(p);
                BigInteger bigPRoot = bigI.modPow(bigPhiByL, bigP);
                if (bigPRoot.compareTo(BigInteger.valueOf(1)) == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                primitiveRoots.add(i);
        }
        return primitiveRoots.get(new Random().nextInt(primitiveRoots.size()));
    }

    public static void main(String args[]) {
        BigInteger n, g, a, b, ga, gb, k1, k2;
        DH dh = new DH();
        Scanner sc = new Scanner(System.in);
        int length = 8;
        Random random = new Random();
        n = BigInteger.probablePrime(length, random);
        g = BigInteger.valueOf(dh.getPrimitiveRoots(n.longValue()));
        System.out.println("Primitive root of n = " + n + " is " + g);
        System.out.println("Choose 1st secret (Alice):");
        a = sc.nextBigInteger();
        System.out.println("Choose 2nd secret (Bob):");
        b = sc.nextBigInteger();
        ga = g.modPow(a, n);
        gb = g.modPow(b, n);
        k1 = gb.modPow(a, n);
        k2 = ga.modPow(b, n);
        if (k1.compareTo(k2) == 0) {
            System.out.println("Alice and Bob can communicate");
            System.out.println("Secret key = " + k1);
        } else {
            System.out.println("Alice and Bob cannot communicate");
        }
        sc.close();
    }
}