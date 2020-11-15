package shape;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;

public class GLine extends GShape {

	private Line2D.Double line;

	public GLine() {
		super();
		this.shape = new Line2D.Double();
		this.line = (Double) this.shape;
	}
	Point p1;
	@Override
	public void setOrigin(int x, int y) {
		p1 = new Point(x, y);
		this.line.setLine(p1, p1);
	}

	@Override
	public void setPoint(int x, int y) {
		Point p2 = new Point(x, y);
		this.line.setLine(p1, p2);
	}

	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public GShape newInstance() {
		// TODO Auto-generated method stub
		return new GLine();
	}

	@Override
	public GShape selectedCheck(GShape gShape) {
		// TODO Auto-generated method stub
		return null;
	}

}
