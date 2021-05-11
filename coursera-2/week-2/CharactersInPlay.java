import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
	private ArrayList<String> characters;
	private ArrayList<Integer> counts;
	
	public CharactersInPlay() {
		characters = new ArrayList<String>();
		counts = new ArrayList<Integer>();
	}
	
	public void update(String person) {
		if(! characters.contains(person)) {
			characters.add(person);
			counts.add(1);
		}
		else {
			int index = characters.indexOf(person);
			counts.set(index, counts.get(index)+1);
		}
	}
	
	public void findAllCharacters() {
		FileResource fr = new FileResource("macbethSmall.txt");
		for(String lines: fr.lines()) {
			int idx = lines.indexOf(".");
			if(idx != -1) {
				update(lines.substring(0,idx).trim());
			}
		}
		
	}
	
	public void charactersWithNumParts(int num1,int num2) {
		for(int i = 0; i < counts.size();i++) {
			int count = counts.get(i);
			if(count >= num1 && count <= num2) {
				System.out.println(characters.get(i));
			}
		}
	}
	
	public void tester() {
		findAllCharacters();
		int mainCharacterCount = 1;
		for(int i = 0; i < counts.size();i++) {
			if(counts.get(i) > mainCharacterCount)
				System.out.println(characters.get(i) + "\t" + counts.get(i));
		}
		charactersWithNumParts(2, 4);
	}
	
	public static void main(String[] args) {
		CharactersInPlay c  = new CharactersInPlay();
		c.tester();
	}

}
