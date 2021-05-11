import edu.duke.*;
public class Part3 {
    public int findStopCodon(String dna,int startIndex,String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }

    public String findGene(String dna,int where){
        int startIndex = dna.indexOf("ATG",where);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");

        int temp = Math.min(taaIndex,tagIndex);
        int minIndex = Math.min(temp,tgaIndex);
        if(minIndex == dna.length()){
            return "";
        }
        return dna.substring(startIndex,minIndex+3);
    }

    public StorageResource getAllGenes(String dna){
	StorageResource allGenes = new StorageResource();
	int startIndex = 0;
	while(true){
	    String currentGene = findGene(dna,startIndex);
	    if(currentGene.isEmpty()){
		break;
	    }
	    allGenes.add(currentGene);
	    startIndex = dna.indexOf(currentGene,startIndex) + currentGene.length();
	}
	return allGenes;
    }

    public float cgRatio(String dna){
                int cg = 0;
                for(int i = 0; i < dna.length(); i++){
                        if(dna.charAt(i) == 'C' || dna.charAt(i)=='G')
                                cg += 1;
                }
                return cg/(float)dna.length();
        }


    public void processGenes(StorageResource sr){
	    int count = 0;
	    int count_cg = 0;
	    int max_length = 0;
	    for(String gene : sr.data()){
		    if(gene.length() > 60){
			    System.out.println(""+gene+" longer than 9 characters");
			    count += 1;
		    }
		    if(cgRatio(gene) > 0.35){
			    System.out.println(""+gene+" has cg ratio greater than 0.35");
			    count_cg += 1;
		    }
		    if(gene.length() > max_length){
			    max_length = gene.length();
		    }
	    }
	    System.out.println("genes greater than length 9: " + count);
	    System.out.println("cg ratio greater than 0.35: " + count_cg);
	    System.out.println("maximum length among genes: " + max_length);
    }


    public static void main(String args[]){
	    Part3 part3 = new Part3();
	    FileResource fr = new FileResource("brca1.fa");
            String dna = fr.asString();
	    StorageResource allGenes = part3.getAllGenes(dna);
	    System.out.println(allGenes.size());
	    for(String gene: allGenes.data()){
		    System.out.println(gene);
	    }
	    part3.processGenes(allGenes);
    }
}
