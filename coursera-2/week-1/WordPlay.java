class WordPlay{

	public boolean isVowel(char ch){
	        char[] vowels = {'a','e','i','o','u','A','E','I','O','U'};
       	       	for(char vowel: vowels){
			if(ch == vowel)
				return true;
		}
		return false;
	}

	public String replaceVowels(String phrase,char ch){
		StringBuilder string = new StringBuilder(phrase);
		for(int i=0; i<phrase.length(); i++){
			if(isVowel(phrase.charAt(i))){
			string.setCharAt(i,ch);
			}
		}
		return string.toString();
	}

	public String emphasize(String phrase, char ch){
		StringBuilder string = new StringBuilder(phrase);
		char evenIdxChar = '*';
		char oddIdxChar = '+';
		for(int i = 0; i < phrase.length(); i++){
			if(ch == Character.toLowerCase(phrase.charAt(i)) || ch == Character.toUpperCase(phrase.charAt(i))){
				if(i%2 == 0)
					string.setCharAt(i,evenIdxChar);
				else
					string.setCharAt(i,oddIdxChar);
			}
		}
		return string.toString();
	}
	
	public static void main(String[] args){
		WordPlay play = new WordPlay();
		System.out.println(play.isVowel('r'));
		System.out.println(play.replaceVowels("HelloWorld",'*'));
		System.out.println(play.emphasize("dna ctgaaactga",'a'));
		System.out.println(play.emphasize("Mary Bella Abracadabra",'a'));
	}
}	
