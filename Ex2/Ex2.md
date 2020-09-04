# Ex2: Hill cipher and Vigenere Cipher

*Nimish S*
*312217104098*


### Hill Cipher 
#### Program:
```java
import java.lang.Math.*;
import java.util.Scanner;

public class HillCipher {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int choice = 0;
        int n = 0;
        String plainText = "";
        int[][] plainTextMatrix = null; 
        int[][] key = null;
        int[][] cipherMatrix = null;
        
        while(choice != 5) {
            printMenu();
            choice = input.nextInt();            
            switch(choice) {
                case 1:{
                     System.out.println("Enter the size of key matrix : \n");
                     n = input.nextInt();
                     System.out.println("Enter key matrix : \n");
                     key = new int[n][n];
                     for ( int i = 0 ; i < n ; i++ ) {
                         for ( int j = 0; j < n ; j++ ) {
                             key[i][j] = input.nextInt();
                         }
                     }
                     if ( getDeterminant(key, n, n) == 0) {
                         System.out.println("Invese does not exist.\n");
                     }
                     break;
                }
                case 2:{
                    System.out.println(" Enter the plain text :  \n");
                    plainText = input.next();
                    plainText = checkPlainText(plainText, n);
                    plainTextMatrix = matCharToASCII( plainTextToMatrix(plainText, n) , n , plainText.length() / n );
                    break;
                }
                case 3:{
                    if ( plainText.equals("") ) {
                        System.out.println("Enter required parameters for encrypting\n");
                    }else {
                        System.out.println("Encrypting plain text... \n");
                        cipherMatrix = matrixMultiply( key, plainTextMatrix, n, plainText.length() / n );
                        char[][] temp = new char[n][plainText.length()/n];
                        for ( int j = 0 ; j < plainText.length() / n ; j++ ) {
                            for ( int i = 0; i < n ; i++ ) {
                                int tempNum =  cipherMatrix[i][j] % 26;
                                cipherMatrix[i][j] = tempNum < 0 ? 26 + tempNum : tempNum;
                            }
                        }
                        temp =  matASCIIToChar( cipherMatrix, n , plainText.length() / n );
                        System.out.println(matrixToString( temp , n , plainText.length() / n ));
                    }
                    break;                        
                }
                case 4:{
                    if ( plainText.equals("")) {
                        System.out.println("Enter required parameters for encrypting\n");
                    }else {
                        key = getInverse( key , n );
                        for( int i = 0 ; i < n ; i++ ){
                              for ( int j = 0 ; j < n ;j ++ ){
                                int temp =  key[i][j] % 26;
                                key[i][j] = temp < 0 ? 26 + temp : temp; 
                              }
                              System.out.println("");
                            }
                        plainTextMatrix = matrixMultiply(key , cipherMatrix, n, plainText.length() / n );
                        for ( int j = 0 ; j < plainText.length() / n ; j++ ) {
                            for ( int i = 0; i < n ; i++ ) {
                                plainTextMatrix[i][j] %= 26;
                            }
                        }
                        System.out.println(matrixToString(matASCIIToChar(plainTextMatrix, n, plainText.length() / n ), n , plainText.length() / n ));
                    }
                    break;
                }
                case 5:{
                    break;
                }
                default:{
                    System.out.println("Invalid option\n");
                    break;
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Input key");
        System.out.println("2. Input plain text");
        System.out.println("3. Encrypt");
        System.out.println("4. Decrypt");
        System.out.println("5. Exit");
    }
    
    private static String checkPlainText ( String plainText, int n ) {
        
        return plainText.length() % n == 0 ? 
                plainText : 
                plainText + new String(new char[(n - plainText.length() % n)]).replace('\0', plainText.charAt(plainText.length()-1));
    }
    
    private static int[][] matCharToASCII ( char[][] matrix, int row, int column ){
        
        int[][] numericMatrix = new int[row][column];
        StringBuilder alphaIndex = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        for ( int  i = 0 ; i < row ; i++ ) {
            for (int j = 0 ; j < column ; j++ ) {
                numericMatrix[i][j] = alphaIndex.indexOf( Character.toString( matrix[i][j] ) );
            }
        }
        return numericMatrix;
        
    }
    
    private static char[][] matASCIIToChar ( int[][] matrix, int row, int column ){
        
        char[][] alphaMatrix = new char[row][column];
        StringBuilder alphaIndex = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        for ( int  i = 0 ; i < row ; i++ ) {
            for (int j = 0 ; j < column ; j++ ) {
                alphaMatrix[i][j] = alphaIndex.charAt( matrix[i][j] );
            }
        }
        return alphaMatrix;
        
    }
    
    private static String matrixToString ( char[][] matrix, int row, int column ) {
        
        StringBuilder str = new StringBuilder();
        for ( int  j = 0 ; j < column ; j++ ) {
            for (int i = 0 ; i < row ; i++ ) {
                str.append( Character.toString( matrix[i][j] ) );
            }
        }
        return str.toString();
    }
    
    private static char[][] plainTextToMatrix ( String finalPlainText, int n ) {
        
        int entries = finalPlainText.length() / n;
        char[][] plainTextMatrixCollection = new char[n][entries];
        
        for ( int j = 0 ; j < entries ; j++ ) {
            for ( int i = 0 ; i < n ; i++ ) {
                plainTextMatrixCollection[i][j] = (char)finalPlainText.charAt( j * n + i  );
            }
        }
        return plainTextMatrixCollection;
    }
    
    
    private static int[][] matrixMultiply( int[][] keyMatrix, int[][] productMatrix, int n, int entries) {
        
        int finalMatrix[][] = new int[n][entries];
        for ( int i = 0 ; i < n ; i ++ ) {
            for ( int j = 0 ; j < entries ; j++ ) {
                for (int k = 0 ; k < n ; k++ ) {
                    finalMatrix[i][j] += keyMatrix[i][k] * productMatrix[k][j];
                }
            }
        }
        
        return finalMatrix;
    }
    
    static void getCofactor( int matrix[][], int temp[][], int p, int q, int n ) { 
        
       int i = 0, j = 0;  
       for (int row = 0; row < n; row++) { 
           for (int col = 0; col < n; col++) { 
               if (row != p && col != q) { 
                   temp[i][j++] = matrix[row][col]; 
                   if (j == n - 1) { 
                       j = 0; 
                       i++; 
                   } 
               } 
           } 
       } 
    } 
    
    static int getDeterminant( int matrix[][], int n, int N) { 
        int D = 0; 
        if (n == 1) 
            return matrix[0][0];  
        int temp[][] = new int[N][N];  
        int sign = 1;   
        for ( int i = 0; i < n; i++ ) { 
            getCofactor( matrix , temp, 0, i, n ); 
            D += sign * matrix[0][i] * getDeterminant( temp , n - 1, N);  
            sign = -sign; 
        } 
      
        return D; 
    } 
    
    
    static int getInverseDeterminant( int d ) {
        //Run through 25 and find which leaves 1, as d * d^-1 = 1 mod 26 
        for ( int i = 1 ; i < 26 ; i++ ) {
            if ( ( d * i ) % 26 == 1 ) {
                return i;
            }
        }
        return d;
    }
    
    static int[][] getAdjointMatrix ( int[][] matrix , int n ) {
        
        int adj[][] = new int[n][n];
        int sign = 1; 
        int [][]temp = new int[n][n]; 
        for (int i = 0; i < n; i++) { 
            for (int j = 0; j < n; j++) {  
                getCofactor( matrix , temp, i, j, n ); 
                sign = ((i + j) % 2 == 0)? 1: -1; 
                adj[j][i] = (sign)*(getDeterminant(temp, n-1 , n)); 
            } 
        } 
        return adj;
    }
    
    static int[][] getInverse ( int[][] matrix, int n) {
        
        int determinant = getDeterminant( matrix, n, n );
        int determinantInverse = getInverseDeterminant(determinant);
        int[][] adjointMatrix = getAdjointMatrix( matrix , n );
        for ( int i = 0 ; i < n ; i++ ) {
            for ( int j = 0 ; j < n ; j++ ) {
                adjointMatrix[i][j] *= determinantInverse;
            }
        }
        return adjointMatrix;
    }
}

```
#### Output:
![Hill](/Users/nimish/Desktop/1.png)

### Vigenere Cipher:
#### Program:
```java
import java.util.*;

class Vigenere {

	public static void main(String args[]) {
		String inputStr = null, key = null;
		int inputLen = -1, choice = -1;
		Scanner scan = new Scanner(System.in);

		while(choice != 3) {
			System.out.println("0-Input\n1-Encrypt:\n2-Decrypt\n3-Exit");
			choice = scan.nextInt();
			switch(choice) {
				case 0: {
					System.out.println("Input String (lower case): ");
					inputStr = scan.next();
					inputLen = inputStr.length();
					System.out.println("Input Key (lower case): ");
					key = scan.next();
					key = generateKey(key, inputLen);
					break;
				}
				case 1: {
					String cipher = encrypt(inputStr, inputLen, key);
					System.out.println(cipher);
					break;
				}
				case 2: {
					String plaintext = decrypt(inputStr, inputLen, key);
					System.out.println(plaintext);
					break;
				}
			}
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
```
#### Output:
![Vigenere](/Users/nimish/Desktop/2.png)

