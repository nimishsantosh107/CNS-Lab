1.  Caesar
    Playfair (monarchy)

2.  Hill (matrix/inv)
    Vignere (large table)

3.  Railfence (zigzag)
    RowColumn

4.  *DES*
    -   64 bit symmetric block cipher
    -   64 bit key
    -   16 rounds (48 bit 16subkeys from 64 bit 1key)

5.  AES
    -   128 (16 bytes) bit Symmetric block cipher with a key (128, 192, 256 bits)
    -   128 key - 10 rounds, 192 key - 12 rounds, 256 - 14 rounds.
    -   subkey - 1 word (4 bytes / 32 bits)
    -   44subkeys - 4subkeys / round
    -   4*4 grid (1 byte in each grid) 1 word / row
    -   ADD KEY -> {SUB BYTES -> SHIFT ROWS, MIX COLUMS(except last round)permute ->ADD ROUND KEY }`ROUND`

6.  RSA
    -   p,q - prime numbers
    -   N = p*q,   phi = (p-1) * (q-1)
    -   e = 1 < e < phi , GCD(e, phi)=1
    -   d = e.modinv(phi)
    -   public - n,e    private - n,d
    -   c = m^e mod(n)  n,e
    -   m = c^d mod(n)  n,d

7.  DH
    -   n (2048), g
    -   a,b -> g^a mod(n), g^b mod(n)
    -   exchange ga, gb
    -   gb^a mod(n) == ga^b mod()

8.  SHA1
    -   160 bit output
    -   80  rounds
    -   split input to 448 mod(512) and then pad 64 = 512
    -   A,B,C,D,E - 32*5 = 160 input at each stage

9.  *DSS*