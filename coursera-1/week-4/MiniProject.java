import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class MiniProject{

	public void totalBirths(FileResource fr){
		int total = 0;
		int total_boys = 0;
		int total_girls = 0;

		for(CSVRecord record : fr.getCSVParser(false)){
			total += Integer.parseInt(record.get(2));
			if(record.get(1).equals("M")){
				total_boys += Integer.parseInt(record.get(2));
			}
			if(record.get(1).equals("F")){
				total_girls += Integer.parseInt(record.get(2));
			}
		}
		System.out.println("Total Births: "+total);
		System.out.println("Total Boys: "+total_boys);
		System.out.println("Total girls: "+total_girls);
	}

	public long getRank(int year, String name, String gender) {
        	long rank = -1;
                FileResource fr = new FileResource("us_babynames_small/testing/yob"+year+"short.csv");
		for(CSVRecord record: fr.getCSVParser(false)){
			if(record.get(1).equals(gender) && record.get(0).equals(name)){
				rank = record.getRecordNumber();
			}
		}
		if(gender.equals("F"))
			return rank;
		return rank - totalFemaleOccurences(year);
	}

	public long totalFemaleOccurences(int year){
		long total = 0;
		FileResource fr = new FileResource("us_babynames_small/testing/yob"+year+"short.csv");
                for(CSVRecord record: fr.getCSVParser(false)){
			if(record.get(1).equals("F")){
				total += 1;
			}
		}
		return total;
	}

	public String getName(int year,int rank,String gender){
		String name = null;
		FileResource fr = new FileResource("us_babynames_small/testing/yob"+year+"short.csv");
		for(CSVRecord record : fr.getCSVParser(false)){
			if(record.get(1).equals("F")){
				if(record.getRecordNumber() == rank)
					name = record.get(0);
			}
			else{
				if(record.getRecordNumber() - (int)totalFemaleOccurences(year) == rank)
				       name = record.get(0);
			}
		}
		if(name == null)
			return "NO NAME";
		return name;
	}

	public void whatIsNameInYear(String name,int year,int newYear,String gender){
		FileResource fr = new FileResource("us_babynames_small/testing/yob"+year+"short.csv");
		int rank = 0;	
		for(CSVRecord record: fr.getCSVParser()){
			if(record.get(0).equals(name) && record.get(1).equals("F") && record.get(1).equals(gender))
				rank = (int)record.getRecordNumber();
			else if(record.get(0).equals(name) && record.get(1).equals("M") && record.get(1).equals(gender))
				rank = (int)record.getRecordNumber() - (int)totalFemaleOccurences(year);
		}
		FileResource newfr = new FileResource("us_babynames_small/testing/yob"+newYear+"short.csv");
		for(CSVRecord record: newfr.getCSVParser()){
			if(record.get(1).equals("F") && record.get(1).equals(gender)){
				if(record.getRecordNumber() == rank)
					System.out.println(name + " born in " + Integer.toString(year) + " would be " + record.get(0) + " if she was born in " + Integer.toString(newYear));
			}
			else if(record.get(1).equals("M") && record.get(1).equals(gender)){
				if(record.getRecordNumber() - (int)totalFemaleOccurences(newYear) == rank)
					System.out.println(name + " born in " + Integer.toString(year) + " would be " + record.get(0) + " if she was born in " + Integer.toString(newYear));
			}
		}
	}
	
	public int yearOfHighestRank(String name,String gender){
		File file = null;
		DirectoryResource dr = new DirectoryResource();
		int highestRankSoFar = 99999;
		for(File f: dr.selectedFiles()){
			FileResource fr = new FileResource(f);
			for(CSVRecord record: fr.getCSVParser()){
				if(record.get(0).equals(name) && record.get(1).equals(gender) && record.get(1).equals("F")){
					if(record.getRecordNumber() < highestRankSoFar){
						highestRankSoFar = (int)record.getRecordNumber();
						file = f;
					}
				}

				else if(record.get(0).equals(name) && record.get(1).equals(gender) && record.get(1).equals("M")){
					if(record.getRecordNumber() - (int)totalFemaleOccurences(Integer.parseInt(f.getName().substring(3,7))) < highestRankSoFar){
                                                highestRankSoFar = (int)record.getRecordNumber() - (int)totalFemaleOccurences(Integer.parseInt(f.getName().substring(3,7)));
                                                file = f;
                                        }
				}
			}
		}
		String fileName = file.getName();
		return Integer.parseInt(fileName.substring(3,7));
	}

	public double getAverageRank(String name,String gender){
		double rank = 0.0;
		double count = 0.0;
		DirectoryResource dr = new DirectoryResource();
                for(File f: dr.selectedFiles()){
                        FileResource fr = new FileResource(f);
                        for(CSVRecord record: fr.getCSVParser()){
                                if(record.get(0).equals(name) && record.get(1).equals(gender) && record.get(1).equals("F")){
                                	rank += record.getRecordNumber();
					count += 1;
                                }

                                else if(record.get(0).equals(name) && record.get(1).equals(gender) && record.get(1).equals("M")){
                                        rank += getRank((Integer.parseInt(f.getName().substring(3,7))),name,gender);
					count += 1;
                                }
                        }
                }
		return rank/count;
	}

	public int getTotalBirthsRankedHigher(int year,String name,String gender){
		int result = 0;
		FileResource fr = new FileResource("us_babynames_small/testing/yob"+year+"short.csv");
		for(CSVRecord record: fr.getCSVParser()){
			if(record.get(1).equals(gender) && record.get(1).equals("F")){
				if(record.get(0).equals(name))
					break;
				result += Integer.parseInt(record.get(2));
			}
			else if(record.get(1).equals(gender) && record.get(1).equals("M")){
				if(record.get(0).equals(name))
                                        break;
                                result += Integer.parseInt(record.get(2));
			}
		}
		return result;
	}
			


	public static void main(String args[]){
		MiniProject mp = new MiniProject();
		mp.totalBirths(new FileResource());
		System.out.println(mp.getRank(2012,"Mason","M"));
		System.out.println(mp.getName(2012,2,"M"));
		System.out.println(mp.getName(2012,7,"M"));
		mp.whatIsNameInYear("Isabella",2012,2014,"F");
		System.out.println(mp.yearOfHighestRank("Mason","M"));
		System.out.println(mp.getAverageRank("Jacob","M"));
		System.out.println(mp.getTotalBirthsRankedHigher(2012,"Ethan","M"));
	}
}

