import java.util.Scanner;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);

			int numOfPoints = sc.nextInt();
			Point[] allPoints = new Point[numOfPoints];
			for (int i = 0; i < numOfPoints; i++) {
				allPoints[i] = new Point(sc.nextDouble(), sc.nextDouble());
			}			

			int numOfCircles = Combination.comb(numOfPoints, 2);
			Circle[] allCircles = new Circle[numOfCircles];
			int index = 0;
			for (int i = 0; i < numOfPoints; i++) {
				for (int j = i + 1; j < numOfPoints; j++) {
					allCircles[index] = createCircle(allPoints[i], allPoints[j], 1);
					index++;
					System.out.println(Arrays.toString(allCircles));
				}
				
			}
			
			int MaxDiscCovered = Circle.MaxDiscCov(allCircles, allPoints);
			System.out.println("Maximum Disc Coverage: " + MaxDiscCovered);
		
	}
			
		
	public static Circle createCircle(Point p1, Point p2, double radius) {

			Point midPoint = p1.midPoint(p2);
			if (radius < p1.distanceTo(p2) || (p1.equals(p2))) {
				return null;
			}
			else {				
				double distance = Math.sqrt((radius * radius) - (midPoint.getX()* midPoint.getX()));
				Point centre = midPoint.moveTo((Math.PI/2) - p1.angleTo(p2), distance);
				return Circle.getCircle(centre, radius);

			}
	}
	

}

