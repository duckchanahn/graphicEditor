package shape;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.util.Vector;

public class Pen extends GShape {
	private static final long serialVersionUID = 1L;

	protected Line2D.Double line;

	Graphics2D g2;

	public Pen() {
		super();
		this.shape = new Line2D.Double();
		this.line = (Double) this.shape;
		pen = new Vector<Point>();
	}

	Point p1;

	@Override
	public void setOrigin(int x, int y) {
		p1 = new Point(x, y);
		pen.add(p1);
	}

	@Override
	public void setPoint(int x, int y) {
		Point p2 = new Point(x, y);
		pen.add(p2);
	}

	@Override
	public void addPoint(int x, int y) {

	}

	@Override
	public GShape newInstance() {
		return new Pen();
	}

	@Override
	public GShape selectedCheck(GShape gShape) {
		return null;
	}

}