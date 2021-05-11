import edu.duke.*;
public class Part4 {
    public void search(){
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
	String contents = ur.asString();
	System.out.println(contents);
       // for (String s : ur.words()) {
            //s.toLowerCase();
	 //   System.out.println(s);
	    
           /* if(s.contains("youtube.com")){
                int position = s.indexOf("youtube");
                System.out.println(" Youtube link found "+s+"At the position "+position);
            }*/
       // }
    }
    public static void main(String[] args) {
        Part4 part4 = new Part4();
        part4.search();
    }

}
