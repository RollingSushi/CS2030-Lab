import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
						
			Point p1 = new Point(sc.nextDouble(), sc.nextDouble());
			
			Point p2 = new Point(sc.nextDouble(), sc.nextDouble());
			
			double radius = sc.nextDouble();
			
			Circle createdCircle = createCircle(p1,p2,radius);
			
			if (createdCircle == null) {
				System.out.println("No valid circle can be created");
			}
			else {
				System.out.println("Created: " + createdCircle.toString());
			}		
		
	}
			
		
	public static Circle createCircle(Point p1, Point p2, double radius) {

			Point midPoint = p1.midPoint(p2);
			if (radius < midPoint.x || (p1.x == p2.x && p1.y == p2.y)) {
				return null;
			}
			else {				
				double distance = Math.sqrt( (radius * radius) - (midPoint.x * midPoint.x));
				Point centre = midPoint.moveTo((Math.PI/2) - p1.angleTo(p2),distance);
				return Circle.getCircle(centre, radius);

			}
	}
	

}

