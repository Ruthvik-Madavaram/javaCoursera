import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int num_points = 0;
        for(Point points : s.getPoints()) {
        	num_points += 1;
        }
        return num_points;
    }

    public double getAverageLength(Shape s) {
        double count = getNumPoints(s);
	double perimeter = getPerimeter(s);
        return perimeter/count;
    }

    public double getLargestSide(Shape s) {
        double largest_side = 0;
	Point prevpt = s.getLastPoint();
	for(Point currpt : s.getPoints()){
		double currdist = prevpt.distance(currpt);
		if(currdist > largest_side){
			largest_side = currdist;
		}
		prevpt = currpt;
	}
        return largest_side;
    }

    public double getLargestX(Shape s) {
        double largest_x = 0.0;
	for(Point currPt : s.getPoints()){
		if(largest_x < currPt.getX()){
			largest_x = currPt.getX();
		}
	}
        return largest_x;
    }

    public double getLargestPerimeterMultipleFiles() {
	double largest_perimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);  
	    Shape s = new Shape(fr);
	    double length = getPerimeter(s);
	    if(largest_perimeter < length){
		    largest_perimeter = length;
	    }
        }

        return largest_perimeter;
    }

    public String getFileWithLargestPerimeter() {
        File temp = null;
	double largest_perimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if(largest_perimeter < length){
                    largest_perimeter = length;
		    temp = f;
            }
        }

        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of points: "+ getNumPoints(s));
    	System.out.println("Average length: " + getAverageLength(s));
	System.out.println("Largest side: " + getLargestSide(s));
	System.out.println("Largest x co-ordinate: " + getLargestX(s));	
    }
    
    public void testPerimeterMultipleFiles() {
        System.out.println("Largest Perimeter Among Multiple Files: " + getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        System.out.println("File name which has largest perimeter: " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
	pr.testPerimeterMultipleFiles();
	pr.testFileWithLargestPerimeter();
    }
}
