class Part2{

	int howMany(String stringA, String stringB){
		int count = 0;
		int startIndex = stringB.indexOf(stringA);
		if(startIndex == -1){
			return count;
		}
		count += 1;
		startIndex = stringB.indexOf(stringA) + stringA.length();
		while(true){
			if(stringB.indexOf(stringA,startIndex)==-1)
				break;
			startIndex = stringB.indexOf(stringA,startIndex) + stringA.length();
			count += 1;
		}
		return count;
	}

	public static void main(String args[]){
		Part2 part2 = new Part2();
		System.out.println(part2.howMany("GAA","ATGAACGAATTGAATC"));
		System.out.println(part2.howMany("AA","ATAAAA"));
		System.out.println(part2.howMany("x","banana"));
	}
}

