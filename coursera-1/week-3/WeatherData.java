import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
class WeatherData{

	public CSVRecord coldestHourInFile(CSVParser parser){
		CSVRecord coldestSoFar = null;
       	        for (CSVRecord record : parser) {
                	if (coldestSoFar == null) {
                		coldestSoFar = record;
           	        } 
			else {
                		double currentTemp = Double.parseDouble(record.get("TemperatureF"));
                		double largestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));

                		if (currentTemp < largestTemp) {
                    			coldestSoFar = record;
                		}		

            		}	
        	}
        	return coldestSoFar;
	}

	public String fileWithColdestTemperature(){
		File file = null;
		DirectoryResource dr = new DirectoryResource();
		double coldestTemp = 9999.0;
		for(File f: dr.selectedFiles()){
			FileResource fr = new FileResource(f);
			double coldestTempInFile = Double.parseDouble(coldestHourInFile(fr.getCSVParser()).get("TemperatureF"));
			if(coldestTempInFile < coldestTemp){
				coldestTemp = coldestTempInFile;
				file = f;
			}
		}
		return file.getName();
	}

	public CSVRecord lowestHumidityInFile(CSVParser parser){
                CSVRecord humiditySoFar = null;
                for (CSVRecord record : parser) {
                        if (humiditySoFar == null) {
                                humiditySoFar = record;
                        }
                        else {
                                double currentHumidity = Double.parseDouble(record.get("Humidity"));
                                double largestHumidity = Double.parseDouble(humiditySoFar.get("Humidity"));

                                if (currentHumidity < largestHumidity) {
                                        humiditySoFar = record;
                                }

                        }
                }
                return humiditySoFar;
        }

	public CSVRecord lowestHumidityInManyFiles(){
		CSVRecord lowestHumidity = null;
		DirectoryResource dr = new DirectoryResource();
		double lowestHumiditySoFar = 9999.0;
		for(File f: dr.selectedFiles()){
			FileResource fr = new FileResource(f);
			CSVRecord record = lowestHumidityInFile(fr.getCSVParser());
			if(record.get("Humidity").equals("N?A"))
				continue;
			if(Double.parseDouble(record.get("Humidity"))<lowestHumiditySoFar){
				lowestHumiditySoFar = Double.parseDouble(record.get("Humidity"));
				lowestHumidity = record;
			}
		}
		return lowestHumidity;
	}

	public double averageTemperatureInFile(CSVParser parser){
		double totalTemp = 0.0;
		double total = 0.0;
		for(CSVRecord record: parser){
			totalTemp += Double.parseDouble(record.get("TemperatureF"));
			total += 1;
		}
		return totalTemp/total;
	}

	public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value){
		double totalTemp = 0.0;
		double total = 0.0;
		for(CSVRecord record : parser){
			if(Integer.parseInt(record.get("Humidity")) >= value)
				totalTemp += Double.parseDouble(record.get("TemperatureF"));
			total += 1.0;
		}
		return totalTemp/total;
	}
	public void testAverageTemperatureWithHighHumidityInFile(){
		FileResource fr = new FileResource();
                CSVParser parser = fr.getCSVParser();
		double result = averageTemperatureWithHighHumidityInFile(parser,80);
		if(result == 0.0){
			System.out.println("Np temperatures with that humidity");
		}
		else{
			System.out.println("Average Temperature with high humidity is "+ result);
		}
	}

	public void testAverageTemperatureInFile(){
		FileResource fr = new FileResource();
		System.out.println("Average Temperature in File is " + averageTemperatureInFile(fr.getCSVParser()));
	}

	public void testLowestHumidityInManyFiles(){
		CSVRecord record = lowestHumidityInManyFiles();
		System.out.println("Lowest Humidity was " + record.get("Humidity") + " at "+record.get("TimeEST"));
		}	

	public void testLowestHumidityInFile() {
                FileResource fr = new FileResource();
                CSVParser parser = fr.getCSVParser();
                CSVRecord result = lowestHumidityInFile(parser);
                System.out.println(result.get("Humidity")+" at"+result.get("TimeEST"));
        }

	public void testFileWithColdestTemperature(){
		System.out.println("Coldest day was in file "+fileWithColdestTemperature());
	}

	public void test() {
        	FileResource fr = new FileResource();
    		CSVParser parser = fr.getCSVParser();
    		CSVRecord result = coldestHourInFile(parser);
        	System.out.println(result.get("TemperatureF")+" at"+result.get("TimeEST"));
	}

	public static void main(String[] args){
		WeatherData data = new WeatherData();
		//data.test();
		//data.testFileWithColdestTemperature();
		//data.testLowestHumidityInFile();
		//data.testLowestHumidityInManyFiles();
		//data.testAverageTemperatureInFile();
		data.testAverageTemperatureWithHighHumidityInFile();
	}
}



