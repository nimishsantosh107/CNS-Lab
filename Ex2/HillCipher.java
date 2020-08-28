import java.util.*;

class HillCipher {

	public static void main(String args[]) {
		String inputStr, key;
		int inputLen;
		
		Scanner scan = new Scanner(System.in);

		System.out.print("INPUT STRING (lower case): ");
		inputStr = scan.nextLine();
		inputLen = inputStr.length();
		System.out.print("INPUT KEY OF LENGTH "+ inputLen*inputLen +" (lower case): ");
		key = scan.nextLine();

		int[][] inputMat = new int[inputLen][1];
		int[][] keyMat = new int[inputLen][inputLen];
		getMat(inputStr, key, inputLen, inputMat, keyMat);

		printMat(inputMat, inputLen, 1);
		printMat(keyMat, inputLen, inputLen);
		
	}
	
	public static void getMat(String inputStr, String key, int inputLen, int[][] inputMat, int[][] keyMat) {
		for(int i=0; i<inputLen; i++) {
        	int code = (int)inputStr.charAt(i);
        	code = (code - 97);
          	inputMat[i][0] = code;
		}

		for(int i=0; i<inputLen; i++) {
			for(int j=0; j<inputLen; j++) {
	        	int code = (int)key.charAt((i*inputLen) + j);
        		code = (code - 97);
          		keyMat[i][j] = code;
			}
		}
	}

	public static int[][] multiply(int[][] inputMat, int[][] keyMat, int inputLen) {
		int[][] res = new int[inputLen][1];
		for(int i=0; i<inputLen; i++){
			for(int j=0; j<1; j++){
				res[i][j] = 0;
				for(int k=0; k<inputLen; k++){
					res[i][j] += keyMat[i][k] * inputMat[k][j];
				}
			}
		}
		return res;
	}

  	public static void printMat(int[][] mat, int inputLen, int d) {  		
   		for (int i=0;i<inputLen;i++){
        	for (int j=0; j<d; j++){
           		System.out.print("  "+mat[i][j]);
         	}
         	System.out.println("");
       	}
  	}
}
