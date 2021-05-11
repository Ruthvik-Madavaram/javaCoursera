class CaesarBreaker{
	public int[] countLetters(String input) {
		String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] counts = new int[26];

        for(int i=0; i<input.length(); i++){
            char ch = Character.toUpperCase(input.charAt(i));
            int index = alphabets.indexOf(ch);
            if (index != -1){
                counts[index] +=1;
            }
        }
        return counts;
	}
	
	public int maxIndex(int[] vals){
        int maxDex = 0;
        for (int i=0; i<vals.length; i++){
            if (vals[i]> vals[maxDex]){
                maxDex = i;
            }
        }
        return maxDex;
    }
	
	public int getKey(String encrypted) {
		int[] freqs = countLetters(encrypted);
		int maxDex = maxIndex(freqs);
		int dkey = maxDex - 4;
		if(maxDex < 4) {
			dkey = 26 - (4-maxDex);
		}
		return dkey;
	}
	
	public String decrypt(String encrypted) {
		CaesarCipher cc = new CaesarCipher();
		int dkey = getKey(encrypted);
		return cc.encrypt(encrypted, 26 - dkey);
	}
	
	public void testDecrypt() {
		CaesarCipher cc =  new CaesarCipher();
		String encrypted = cc.encrypt("FIRST LEGION ATTACK EAST FLANK!",23);
		System.out.println(decrypt(encrypted));
		
	}
	
	public static void main(String[] args) {
		CaesarBreaker cb = new CaesarBreaker();
		cb.testDecrypt();
	}
	
}
