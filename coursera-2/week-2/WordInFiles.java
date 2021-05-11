import edu.duke.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordInFiles {

    private HashMap<String, ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
    WordInFiles(HashMap<String, ArrayList<String>> map)
    {
        this.map = map;
    }
 
    private void addWordsFromFile(File file)
    {
        FileResource resource = new FileResource(file);
	for(String word: resource.words()){
		if(!map.containsKey(word)){
			ArrayList<String> files = new ArrayList<String>();
			files.add(file.getName());
			map.put(word,files);
		}
		else{
			ArrayList<String> files = map.get(word);
                        files.add(file.getName());
                        map.put(word,files);
		}
	}
    }

    public void buildWordFileMap()
    {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File file : dr.selectedFiles()) {
            addWordsFromFile(file);
        }
    }

    public int maxNumber()
    {
        int res = 0;
        for(String word: map.keySet())
        {
            int noOfFiles = map.get(word).size();
            if(noOfFiles > res)
                res = noOfFiles;
        }
        return res;
    }

    public ArrayList<String> wordsInNumFiles(int number)
    {
        ArrayList<String> words = new ArrayList<String>();
        for(String word: map.keySet())
        {
            ArrayList<String> files = map.get(word);
            if(files.size() == number)
                words.add(word);
        }
        return words;
    }

    public void printFilesIn(String word)
    {
        ArrayList<String> files = map.get(word);
        for(String fileName: files)
        {
            System.out.println(fileName);
        }
    }

    public void tester()
    {
        buildWordFileMap();
        int max = maxNumber();
        System.out.println("Maximum number of files any word is in: " + max);
        for(String word: map.keySet())
        {
            if(map.get(word).size() == max)
                System.out.println(word+" "+ map.get(word));
        }
        System.out.println();
        for(String word: map.keySet())
        {
            System.out.print(word+"  ");
            printFilesIn(word);
            System.out.println();
        }
    }

    public static void main(String args[]){
        HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
        WordInFiles wif =new WordInFiles(map);
        wif.tester();
    }


}
