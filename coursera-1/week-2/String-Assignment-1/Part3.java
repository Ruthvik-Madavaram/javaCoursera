public class Part3{

     boolean twoOccurences(String stringA, String stringB){
        int length_a = stringA.length();
        int length_b = stringB.length();
        int i = 0;
        int count = 0;
        while(length_b >= length_a){
            if(stringB.indexOf(stringA,i) >= 0){
                i += stringB.indexOf(stringA,i) + length_a;
                length_b -= stringB.indexOf(stringA,i) + length_a;
                count += 1;
            }
            else{
                i += 1;
                length_b -= 1;
            }
        }
        if(count >= 2){
            return true;
        }
        return false;
    }
    
    String lastPart(String stringA,String stringB){
        if(stringB.indexOf(stringA) >= 0){
            return stringB.substring(stringB.indexOf(stringA) + stringA.length(),stringB.length());
        }
        else{
            return stringB;
        }
    }
    void testing(){
        System.out.println(twoOccurences("by","A story by Abby long"));
        System.out.println(twoOccurences("a","banana"));
        System.out.println(twoOccurences("atg","ctgtatgta"));
        System.out.println(lastPart("an","banana"));
        System.out.println(lastPart("zoo","forest"));
    }
     public static void main(String []args){
        System.out.println("Hello World");
        Part3 part3 = new Part3();
        part3.testing();
        
     }
}
