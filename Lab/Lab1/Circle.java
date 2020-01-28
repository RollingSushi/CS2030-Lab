public class Circle {
	private final Point centre;
	private final double radius;

	private Circle(Point centre, double radius) {
		this.centre = centre;
		this.radius = radius;
	}

	public static Circle getCircle(Point p, double radius) {
		if (radius <= 0) {
			return null;
		}
		else {
			return new Circle(p, radius);
		}
	}

	public boolean contains(Point point) {
		double dx= centre.getX()- point.getX();
		double dy= centre.getY()- point.getY();
		return Math.sqrt(dx*dx+dy*dy) < radius;
		}

	public static int MaxDiscCov(Circle[] c, Point[] p) {
		int current = 1;
		int currentMax = 0;

		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < p.length; j++)
				if (c[i].contains(p[j])) {
					current++;

					if (current > currentMax) {
						currentMax = current;
						current = 1;
					}
				}
		}
		
		return currentMax;
	}

	@Override
	public String toString() {
		return "circle of radius " + this.radius + " centered at " + this.centre;
	}
		


}

