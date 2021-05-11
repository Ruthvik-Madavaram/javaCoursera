import edu.duke.*;
import java.util.HashMap;

public class CountCodon {
    private HashMap<String,Integer> counts=new HashMap<String,Integer>();
    CountCodon(HashMap<String,Integer> counts)
    {
        this.counts = counts;
    }
    public void buildCodonMap(int start,String dna)
    {
        counts.clear();
        int i = start;
        while(i <= dna.length()-3)
        {
            String codon = dna.substring(i,i+3);
            if(!counts.containsKey(codon))
            {
                counts.put(codon,1);
            }
            else
                counts.put(codon,counts.get(codon)+1);
            i=i+3;
        }
        System.out.println("# Unique codons: "+ counts.size());
        System.out.println("Most common codon: "+getMostCommonCodon());
    }
    public String getMostCommonCodon()
    {
        int largestCount = 0;
        String largestCountCodon = null;
        for(String codon: counts.keySet())
        {
            if(counts.get(codon) > largestCount)
            {
                largestCount = counts.get(codon);
                largestCountCodon = codon;
            }
        }
        return largestCountCodon;
    }
    public void printCodonCounts(int start,int end)
    {
        for(String codon: counts.keySet()) {
            int codonCount = counts.get(codon);
            if (codonCount >= start && codonCount <= end) {
                System.out.println(codon+ " " + codonCount);
            }
        }
    }
    public void tester()
    {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        for(int i = 0; i < 3;i++){
		buildCodonMap(i,dna);
		printCodonCounts(1,5);
	}
    }
    public static void main(String args[])
    {
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        CountCodon cc = new CountCodon(counts);
        cc.tester();
    }
}
