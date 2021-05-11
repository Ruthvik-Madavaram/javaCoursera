class Part2{
	String findSimpleGene(String dna,String startCodon,String endCodon){
		char firstChar = dna.charAt(0);
		if(Character.isLowerCase(firstChar)){
			startCodon = startCodon.toLowerCase();
			endCodon = endCodon.toLowerCase();
		}
		int startIndex = dna.indexOf(startCodon);
		if(startIndex == -1){
			return "";
		}
		int endIndex = dna.indexOf(endCodon,startIndex+3);
		if(endIndex == -1){
			return "";
		}
		if((endIndex-startIndex)%3 == 0){
			if(Character.isLowerCase(firstChar))
				return dna.substring(startIndex,endIndex+3).toLowerCase();
			else
				return dna.substring(startIndex,endIndex+3);
		}
		return "";
	}

	void testSimpleGene(){
		String startCodon = "ATG";
		String endCodon = "TAA";
		String dna1 = "AAAGTGGATAA";
		String dna2 = "AGATGAAGGGT";
		String dna3 = "GGGGGGGGGGG";
		String dna4 = "atgttataa";
		String dna5 = "ATGTATAA";

		System.out.println("DNA-1 Gene: " + findSimpleGene(dna1,startCodon,endCodon));
		System.out.println("DNA-2 Gene: " + findSimpleGene(dna2,startCodon,endCodon));
		System.out.println("DNA-3 Gene: " + findSimpleGene(dna3,startCodon,endCodon));
		System.out.println("DNA-4 Gene: " + findSimpleGene(dna4,startCodon,endCodon));
		System.out.println("DNA-5 Gene: " + findSimpleGene(dna5,startCodon,endCodon));
	}

	public static void main(String args[]){
		Part2 part2 = new Part2();
		part2.testSimpleGene();
	}
}
