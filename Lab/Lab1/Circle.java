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
		return this.centre.distanceTo(point) <= radius;
		}

	public static int MaxDiscCov(Circle[] c, Point[] p) {
		int covMax = 0;

		for (int i = 0; i < c.length; i++) {
			int current = 0;
			for (int j = 0; j < p.length; j++) {
				if (c[i] == null) {

				}
				else if (c[i].contains(p[j])) {
					current++;
				}
			}
			if (current > covMax) {
				covMax = current;
				}
		}

		return covMax;
	}

	@Override
	public String toString() {
		return "circle of radius " + this.radius + " centered at " + this.centre;
	}
		


}

