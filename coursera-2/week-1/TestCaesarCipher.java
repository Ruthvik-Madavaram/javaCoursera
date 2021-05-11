import edu.duke.FileResource;

class CaesarCipherOOPS{
	private String alphabet;
	private String shiftedAlphabet;
	private int key;
	public CaesarCipherOOPS(int key) {
		this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		this.shiftedAlphabet = alphabet.substring(key)+alphabet.subSequence(0, key);
		this.key = key;
	}
	public String encrypt(String input) {
		StringBuilder sb = new StringBuilder(input);
		for(int i = 0;i < sb.length();i++) {
			char c = sb.charAt(i);
			int idx = alphabet.indexOf(c);
			if(idx != -1) {
				c = shiftedAlphabet.charAt(idx);
				sb.setCharAt(i, c);
			}
		}
		return sb.toString();
	}
	public String decrypt(String encrypted) {
		CaesarCipherOOPS cc = new CaesarCipherOOPS(26 - key);
		return cc.encrypt(encrypted);
	}
}
public class TestCaesarCipher {
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
	
	void simpleTests() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipherOOPS cc = new CaesarCipherOOPS(18);
        String encrypted = cc.encrypt(input);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("encrypted string: " + encrypted + "decrypted string: " + decrypted);
        breakCaesarCipher(encrypted);
    }
	
	void breakCaesarCipher(String input) {
        int dkey = getKey(input);
        CaesarCipherOOPS cc = new CaesarCipherOOPS(26 - dkey);
        System.out.println("decrypted msg by guessing key: " + cc.encrypt(input));
    }

	public static void main(String[] args) {
		TestCaesarCipher cipher = new TestCaesarCipher();
		cipher.simpleTests();

	}

}
