public class Point {
	private final double x;
	private final double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return this.x;
		} 
	public double getY() {
		return this.y;
		}


	public double distanceTo(Point p) {
		double dispX = this.x - p.x;
		double dispY = this.y - p.y;
		return Math.sqrt(dispX*dispX+dispY*dispY);
	}

	public Point midPoint(Point p) {
		double midX = (this.x + p.x)/2;
		double midY = (this.y + p.y)/2;
		return new Point(midX, midY);
	}

	public double angleTo(Point p) {
		double cumX = (p.x - this.x); 
		double cumY = (p.y - this.y); 
		return Math.atan2(cumY, cumX);
	}

	public Point moveTo(double angle, double dis){
		return new Point(this.x + dis * Math.cos(angle), this.y + dis * Math.sin(angle));
	}


	@Override
	public String toString() {
		String sX = String.format("%.3f", this.x);
	    String sY = String.format("%.3f", this.y);
		return  "point " + "(" + sX + ", " + sY + ")";
	}

	public boolean equals(Point p) {
		if (this.x == p.x && this.y == p.y) {
			return true;
		}
		else {
			return false;
		}
	}
}
