import edu.duke.*;
import org.apache.commons.csv.*;
class Export{

	public String countryInfo(CSVParser parser, String country){
		for(CSVRecord record: parser){
			if(record.get("Country").equals(country)){
				return country+": "+record.get("Exports")+": "+record.get("Value (dollars)");
			}
		}
		return "NOT FOUND";
	}
	
	public void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2){
                for(CSVRecord record: parser){
                        if(record.get("Exports").indexOf(exportItem1) != -1 && record.get("Exports").indexOf(exportItem2) != -1)
                                System.out.println(record.get("Country"));
                }
        }

        public int numberOfExporters(CSVParser parser, String exportItem){
                int count = 0;
                for(CSVRecord record: parser){
                        if(record.get("Exports").indexOf(exportItem) != -1){
                                count += 1;
                        }
                }
                return count;
        }

	public void bigExporters(CSVParser parser, String amount){
		for(CSVRecord record: parser){
			if(record.get("Value (dollars)").length() > amount.length()){
				System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
			}
		}
	}

	public void tester(){
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		//System.out.println(countryInfo(parser,"Germany"));
		//listExportersTwoProducts(parser,"gold","diamonds");
		//System.out.println("Number of countries exporting gold: "+ numberOfExporters(parser,"gold"));
		bigExporters(parser,"$999,999,999");
	}

	public static void main(String[] args){
		Export export = new Export();
		export.tester();
	}
}
