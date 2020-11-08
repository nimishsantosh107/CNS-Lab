import java.util.*;
import java.math.*;

public class DH2 {
    
    public boolean primeMillerRabin(long n, int iterations) {
        if (n == 0 || n == 1)
            return false;
        if (n == 2)
            return true;
        if (n % 2 == 0)
            return false;
        long s = n - 1;
        while (s % 2 == 0)
            s /= 2;
        Random rand = new Random();
        for (int i = 0; i < iterations; i++) {
            long r = Math.abs(rand.nextLong());
            long a = r % (n - 1) + 1, temp = s;
            long mod = modularExp(a, temp, n);
            while (temp != n - 1 && mod != 1 && mod != n - 1) {
                mod = modularMul(mod, mod, n);
                temp *= 2;
            }
            if (mod != n - 1 && temp % 2 == 0)
                return false;
        }
        return true;
    }

    public long modularExp(long a, long b, long c) {
        long res = 1;
        for (int i = 0; i < b; i++) {
            res *= a;
            res %= c;
        }
        return res % c;
    }

    public long modularMul(long a, long b, long mod) {
        return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).mod(BigInteger.valueOf(mod)).longValue();
    }

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
        Vector < Long > primitiveRoots = new Vector < > ();
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
        BigInteger q, g, xa, xb, ya, yb, k1, k2;
        DH2 dh = new DH2();
        Scanner sc = new Scanner(System.in);
        int length = 8;
        Random random = new Random();
        q = BigInteger.probablePrime(length, random);
        System.out.println("Selected probable prime number = " + q);
        long l = q.longValue();
        if (dh.primeMillerRabin(l, 3)) {
            System.out.println(q + " is possibly prime by Miller Rabin Test");
            g = BigInteger.valueOf(dh.getPrimitiveRoots(q.longValue()));
            System.out.println("Primitive root of q = " + q + " is " + g);
            System.out.println("Choose 1st secret (Alice):");
            xa = sc.nextBigInteger();
            System.out.println("Choose 2nd secret (Bob):");
            xb = sc.nextBigInteger();
            ya = g.modPow(xa, q);
            yb = g.modPow(xb, q);
            k1 = yb.modPow(xa, q);
            k2 = ya.modPow(xb, q);
            if (k1.compareTo(k2) == 0) {
                System.out.println("Alice and Bob can communicate");
                System.out.println("Secret key = " + k1);
            } else {
                System.out.println("Alice and Bob cannot communicate");
            }
        } else {
            System.out.println(q + " is not prime");
        }
        sc.close();
    }
}