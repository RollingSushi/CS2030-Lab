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

			int numOfCircles = Main.comb(numOfPoints, 2);
			Circle[] allCircles = new Circle[numOfCircles];
			int index = 0;
			for (int i = 0; i < numOfPoints; i++) {
				for (int j = i + 1; j < numOfPoints; j++) {
					if (allPoints[i].distanceTo(allPoints[j]) > 2) {
						
					}
					else {
						allCircles[index] = createCircle(allPoints[i], allPoints[j], 1);
						index++;
					}
					
				}
				
			}
			
			int MaxDiscCovered = Circle.MaxDiscCov(allCircles, allPoints);
			System.out.println("Maximum Disc Coverage: " + MaxDiscCovered);
		
	}

	public static int comb(int n , int r)
	{
		if( r== 0 || n == r)
			return 1;
		else
			return comb(n-1,r)+comb(n-1,r-1);
	}
			
		
	public static Circle createCircle(Point p1, Point p2, double radius) {

			Point midPoint = p1.midPoint(p2);
			double distance = Math.sqrt((radius * radius) - (midPoint.distanceTo(p2)* midPoint.distanceTo(p2)));
			if ((radius < midPoint.distanceTo(p2)) || (p1.equals(p2))) {
				return null;
			}
			else if (Double.isNaN(distance)) {
				return null;
			}

			else {				
				Point centre = midPoint.moveTo((Math.PI/2) - p1.angleTo(p2) , distance);
				return Circle.getCircle(centre, radius);

			}
	}
	

}

