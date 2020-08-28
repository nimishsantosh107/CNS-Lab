import java.util.*;

class Tuple {
	public int i=-1,j=-1;
	
	void set(int i, int j){
		this.i = i;
		this.j = j;
	}
}

class Playfair {
	public static void main(String[] args){
		String inputtext, key;
		char[][] table = new char[5][5];
		ArrayList<String> splittext;

		Scanner scan = new Scanner(System.in);

		//GET INPUT
		System.out.print("INPUT STRING (lower case): ");
		inputtext = scan.nextLine();
		System.out.print("INPUT KEY (lower case)   : ");
		key = scan.nextLine();
		key = unique(key);

		//SPLIT INPUT
		splittext = splitText(inputtext);

		//GET TABLE
		table = getTable(key);
		printTable(table);
		
		System.out.print("0-ENCRYPT/1-DECRYPT: ");
		int choice = scan.nextInt(); 

		String output;
		// ENCRYPT
		if(choice == 0) {
			output = encrypt(table, splittext);
		}
		// DECRYPT
		else {
			output = decrypt(table, splittext);
		}
		System.out.println(output);
	}

	//ENCRYPT
	private static String encrypt(char[][] table, ArrayList<String> splittext){
		String cipher = "";
		for(int i=0; i<splittext.size(); i++){
			Tuple ind1 = getTableIndex(table, splittext.get(i).charAt(0));
			Tuple ind2 = getTableIndex(table, splittext.get(i).charAt(1));

			//CASE 1
			if(ind1.i == ind2.i){
				cipher += table[ind1.i][(ind1.j+1)%5];
				cipher += table[ind2.i][(ind2.j+1)%5];
			}
			//CASE 2
			else if(ind1.j == ind2.j){
				cipher += table[(ind1.i+1)%5][ind1.j];
				cipher += table[(ind2.i+1)%5][ind2.j];
			}
			//CASE 3
			else {
				cipher += table[ind1.i][ind2.j];
				cipher += table[ind2.i][ind1.j];
			}
		}
		return cipher;
	}

	//DECRYPT
	private static String decrypt(char[][] table, ArrayList<String> splittext){
		String plaintext = "";
		for(int i=0; i<splittext.size(); i++){
			Tuple ind1 = getTableIndex(table, splittext.get(i).charAt(0));
			Tuple ind2 = getTableIndex(table, splittext.get(i).charAt(1));

			//CASE 1
			if(ind1.i == ind2.i){
				plaintext += table[ind1.i][(ind1.j+4)%5];
				plaintext += table[ind2.i][(ind2.j+4)%5];
			}
			//CASE 2
			else if(ind1.j == ind2.j){
				plaintext += table[(ind1.i+4)%5][ind1.j];
				plaintext += table[(ind2.i+4)%5][ind2.j];
			}
			//CASE 3
			else {
				plaintext += table[ind1.i][ind2.j];
				plaintext += table[ind2.i][ind1.j];
			}
		}
		return plaintext;
	}

	//SPLIT INPUT TEXT
	private static ArrayList<String> splitText(String plaintext){
		ArrayList<String> splittext = new ArrayList<String>();
		if(plaintext.length()%2 != 0) {plaintext+='z';}

		for(int i=0; i<plaintext.length(); i++){
			if(i%2 == 0){
				String temp = "";
				temp = temp + plaintext.charAt(i) + plaintext.charAt(i+1);
				splittext.add(temp);
			}
		}
		return splittext;
	}

	// CREATE TABLE
	private static char[][] getTable(String key){
		char[][] table = new char[5][5];
		ArrayList<Character> line = new ArrayList<Character>();

		for (int i=0; i<key.length(); i++){
			line.add(key.charAt(i));
		}
		for (int i=97; i<123; i++){ 
			char charcode = (char)i;
			if( (i!=106) && (key.indexOf(charcode) == -1) ){
				line.add(charcode);
			}
		}
		for (int i=0;i<5;i++){
			for (int j=0; j<5; j++){
				table[i][j] = line.get((i*5)+j);
			}
		}
		return table;
	}

	// RETURN INDEX OF CHAR IN TABLE
	private static Tuple getTableIndex(char[][] table, char c){
		Tuple res = new Tuple();
		if(c == 'j'){
			res.set(4,4);
			return res;
		}
		for (int i=0;i<5;i++){
			for (int j=0; j<5; j++){
				if(table[i][j] == c){
					res.set(i,j);
					return res;	
				}
			}
		}
		return res;
	}

	// PRINT TABLE
	private static void printTable(char[][] table){
		for (int i=0;i<5;i++){
			for (int j=0; j<5; j++){
				System.out.print("  "+table[i][j]);
			}
			System.out.println("");
		}
	}

	public static String unique(String s) { 
        String str = new String(); 
        for (int i = 0; i < s.length(); i++) { 
            char c = s.charAt(i); 
            if (str.indexOf(c) < 0) { 
                str += c; 
            } 
        } 
        return str; 
    } 
}