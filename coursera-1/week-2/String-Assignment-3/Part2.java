class Part2{
	public float cgRatio(String dna){
		int cg = 0;
		for(int i = 0; i < dna.length(); i++){
			if(dna.charAt(i) == 'C' || dna.charAt(i)=='G')
				cg += 1;
		}
		return cg/(float)dna.length();
	}
	public int countCTG(String dna){
		int count = 0;
		int startIndex = dna.indexOf("CTG");
		if(startIndex == -1){
			return count;
		}
		count += 1;
		while(true){
			startIndex = dna.indexOf("CTG",startIndex + 3);
			if(startIndex == -1){
				break;
			}
			count += 1;
		}
		return count;
	}
	public static void main(String args[]){
		Part2 part2 = new Part2();
		System.out.println(part2.cgRatio("ATGCCATAG"));
		System.out.println(part2.countCTG("ATGCCCTGATAGCTGCTGCT"));
	}
}
