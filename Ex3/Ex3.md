# RailFence & RowColumn Transformation Ciphers

**Nimish S**
**312217104098**

### RailFence Cipher
#### Program:
```java
import java.util.*;
import java.lang.*;

class RailFence {
	public static void main(String args[]) {
		String inputStr = null;
		int choice = -1, key = -1;
		Scanner scan = new Scanner(System.in);

		while(choice != 3) {
			System.out.println("\n0-Input\n1-Encrypt:\n2-Decrypt\n3-Exit\n");
			choice = scan.nextInt();
			switch(choice) {
				case 0: {
					System.out.println("Input String (lower case): ");
					inputStr = scan.next();
					System.out.println("Input Key (lower case): ");
					key = scan.nextInt();
					break;
				}
				case 1: {
					String cipher = encrypt(inputStr, key);
					System.out.println("\nCIPHER   :   "+cipher);
					break;
				}
				case 2: {
					String plaintext = decrypt(inputStr, key);
					System.out.println("\nPLAINTEXT:   "+plaintext);
					break;
				}
			}
		}
	}

	public static String encrypt(String inputStr, int key) {
		int inputLen = inputStr.length();
		char[][] keyMat = new char[key][inputLen];
		//init keyMat
		for (int i=0; i<key; i++) {
			for(int j=0; j<inputLen; j++) {
				keyMat[i][j] = '-';
			}
		}
		char[] inputArr = inputStr.toCharArray();

		//write diagonally
		boolean direction = false;
		int row = 0;
		for (int i=0; i<inputLen; i++) {
			if(row == 0 || row == (key-1)) {direction = !direction;}
			
			keyMat[row][i] = inputArr[i];

			row = (direction)? ++row : --row;
		}
		printTable(keyMat, inputLen, key);

		//read horizontally
		char[] cipherArr = new char[inputLen];
		int index = 0;
		for (int i=0; i<key; i++) {
			for(int j=0; j<inputLen; j++) {
				if(keyMat[i][j] != '-'){
					cipherArr[index++] = keyMat[i][j];
				}
			}
		}
		String cipherStr = new String(cipherArr);
		return cipherStr;
	}

	public static String decrypt(String inputStr, int key) {
		int inputLen = inputStr.length();
		char[][] keyMat = new char[key][inputLen];
		//init keyMat
		for (int i=0; i<key; i++) {
			for(int j=0; j<inputLen; j++) {
				keyMat[i][j] = '-';
			}
		}

		boolean direction = false;
		int row = 0;

		//create placeholder
		for (int i=0; i<inputLen; i++) {
			if(row == 0 || row == (key-1)) {direction = !direction;}
			
			keyMat[row][i] = '*';

			row = (direction)? ++row : --row;
		}

		//write horizontally
		char[] inputArr = inputStr.toCharArray();
		int index = 0;
		for (int i=0; i<key; i++) {
			for(int j=0; j<inputLen; j++) {
				if(keyMat[i][j] == '*'){
					keyMat[i][j] = inputArr[index++];
				}
			}
		}
		printTable(keyMat, inputLen, key);

		//read diagonally
		char[] plaintextArr = new char[inputLen];
		direction = false;
		row = 0;index = 0;
		for (int i=0; i<inputLen; i++) {
			if(row == 0 || row == (key-1)) {direction = !direction;}
			
			plaintextArr[index++] = keyMat[row][i];

			row = (direction)? ++row : --row;
		}
		String plaintext = new String(plaintextArr);
		return plaintext;
	}

	// PRINT TABLE
	private static void printTable(char[][] table, int inputLen, int key){
		System.out.println("PRINTING TABLE: ");
		for (int i=0;i<key;i++){
			for (int j=0; j<inputLen; j++){
				System.out.print("  "+table[i][j]);
	 		}
			System.out.println("");
    	}
	}
}
```

#### Output:
![RailFence](/Users/nimish/Desktop/1.png)

### RowColumn Transformation Cipher
#### Program:
```java
import java.util.*;
public class RowColumn
{
	public static void main(String args[])
    {
        int row,column,choice;
        char ch;
        RowColumn obj=new RowColumn();
        Scanner in = new Scanner(System.in);
        do{
            System.out.println("\n1-Encrypt\n2-Decrypt\n");
            choice=in.nextInt();
            System.out.println("Enter the string:");
            String s=in.next();
            System.out.println("Enter the key:");
            String key=in.next();
            row=s.length()/key.length();
            if(s.length()%key.length()!=0)
                row++;
            column=key.length();
            switch(choice)
            {
                case 1: obj.creatematrixE(s,key,row,column);
                        obj.createkey(key,column);
                        obj.encrypt(row,column);
                        obj.resultEncrypted(row,column,obj.encrypt);
                        break;
                case 2: obj.creatematrixD(s,key,row,column);
                        obj.createkey(key,column);
                        obj.decrypt(row,column);
                        obj.resultDecrypted(row,column,obj.decrypt);
                        break;
            }
            System.out.println("\ncontinue? y/n");
            ch = in.next().charAt(0);
        }while(ch!='n');
        
    }
    char arr[][],encrypt[][],decrypt[][],keya[],keytemp[];
    public void creatematrixE(String s,String key,int row,int column)
    {
        arr=new char[row][column];
        int k=0;
        keya=key.toCharArray();
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
            {
                if(k<s.length())
                {
                    arr[i][j]=s.charAt(k);
                    k++;
                }
                else
                {
                    arr[i][j]=' ';
                }
            }
        }
    }
    public void createkey(String key,int column)
    {
        keytemp=key.toCharArray();
        for(int i=0;i<column-1;i++)
        {
            for(int j=i+1;j<column;j++)
            {
                if(keytemp[i]>keytemp[j])
                {
                    char temp=keytemp[i];
                    keytemp[i]=keytemp[j];
                    keytemp[j]=temp;
                }
            }
        }
    }
    public void creatematrixD(String s,String key,int row,int column)
    {
        arr=new char[row][column];
        int k=0;
        keya=key.toCharArray();
        for(int i=0;i<column;i++)
        {
            for(int j=0;j<row;j++)
            {
                if(k<s.length())
                {
                    arr[j][i]=s.charAt(k);
                    k++;
                }
                else
                {
                    arr[j][i]=' ';
                }
            }
        }
    }
    public void encrypt(int row,int column)
    {
        encrypt=new char[row][column];
        for(int i=0;i<column;i++)
        {
            for(int j=0;j<column;j++)
            {
                if(keya[i]==keytemp[j])
                {
                    for(int k=0;k<row;k++)
                    {
                        encrypt[k][j]=arr[k][i];
                    }
                    keytemp[j]='?';
                    break;
                }
            }
        }
    }
    public void decrypt(int row,int column)
    {
        decrypt=new char[row][column];
        for(int i=0;i<column;i++)
        {
            for(int j=0;j<column;j++)
            {
                if(keya[j]==keytemp[i])
                {
                for(int k=0;k<row;k++)
                {   
                    decrypt[k][j]=arr[k][i];
                }
                keya[j]='?';
                break;
                }
            }
        }
    }
    public void resultEncrypted(int row,int column,char arr[][])
    {
        System.out.println("ENCRYPTED:");
        for(int i=0;i<column;i++)
        {
            for(int j=0;j<row;j++)
            {
                System.out.print(arr[j][i]);
            }
        }
    }
    public void resultDecrypted(int row,int column,char arr[][])
    {
        System.out.println("DECRYPTED:");
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
            {
                System.out.print(arr[i][j]);
            }
        }
    }
    
}
```

#### Output:
![RowColumn](/Users/nimish/Desktop/2.png)