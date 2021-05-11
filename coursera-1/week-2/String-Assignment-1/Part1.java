class Part1{
	String findSimpleGene(String dna){
		int startIndex = dna.indexOf("ATG");
		if(startIndex == -1){
			return "";
		}
		int endIndex = dna.indexOf("TAA",startIndex+3);
		if(endIndex == -1){
			return "";
		}
		if((endIndex-startIndex)%3 == 0){
			return dna.substring(startIndex,endIndex+3);
		}
		return "";
	}

	void testSimpleGene(){
		String dna1 = "AAAGTGGATAA";
		String dna2 = "AGATGAAGGGT";
		String dna3 = "GGGGGGGGGGG";
		String dna4 = "ATGGTATAA";
		String dna5 = "ATGTATAA";

		System.out.println("DNA-1 Gene: " + findSimpleGene(dna1));
		System.out.println("DNA-2 Gene: " + findSimpleGene(dna2));
		System.out.println("DNA-3 Gene: " + findSimpleGene(dna3));
		System.out.println("DNA-4 Gene: " + findSimpleGene(dna4));
		System.out.println("DNA-5 Gene: " + findSimpleGene(dna5));
	}

	public static void main(String args[]){
		Part1 part1 = new Part1();
		part1.testSimpleGene();
	}
}
