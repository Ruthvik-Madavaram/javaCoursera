class CaesarCipher{
	
	public String encrypt(String input, int key) {
		StringBuilder encrypted = new StringBuilder(input);
		String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
		String shiftedInputUpper = alphabetUpper.substring(key) + alphabetUpper.substring(0,key);
		String shiftedInputLower = alphabetLower.substring(key) + alphabetLower.substring(0,key);

		for(int i = 0; i < input.length(); i++) {
			char currChar = encrypted.charAt(i);
			int idx = -1;
			if(Character.isLowerCase(currChar))
				idx = alphabetLower.indexOf(currChar);
			else
				idx = alphabetUpper.indexOf(currChar);
			if(idx != -1) {
				char newChar = ' ';
				if(Character.isLowerCase(currChar))
					newChar = shiftedInputLower.charAt(idx);
				else
					newChar = shiftedInputUpper.charAt(idx);
				encrypted.setCharAt(i, newChar);
			}
		}
		return encrypted.toString();
	}

	public String encryptTwoKeys(String input,int key1,int key2){
		StringBuilder encrypted = new StringBuilder(input);
		for(int i = 0; i< input.length();i++){
			String replace = "";
			if(i%2==0)
				replace += encrypt(Character.toString(encrypted.charAt(i)),key1);
			else
				replace += encrypt(Character.toString(encrypted.charAt(i)),key2);
			encrypted.setCharAt(i,replace.charAt(0));
		}
		return encrypted.toString();
	}
	
	public static void main(String[] args) {
		CaesarCipher cipher = new CaesarCipher();
		System.out.println(cipher.encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
		System.out.println(cipher.encrypt("First Legion",23));
		System.out.println(cipher.encrypt("First Legion",17));
		System.out.println(cipher.encryptTwoKeys("First Legion",23,17));
	}
}

