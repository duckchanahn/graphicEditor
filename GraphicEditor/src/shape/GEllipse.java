package shape;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class GEllipse extends GShape {
	private static final long serialVersionUID = 1L;
	
	private Ellipse2D.Double ellipse;
	
	public GEllipse() {
		super();
		this.shape = new Ellipse2D.Double();
		this.ellipse = (Double) this.shape;
	}
	
	@Override
	public void setOrigin(int x, int y) {
		basicx = x;
		basicy = y;
		this.ellipse.setFrame(Math.abs(x), Math.abs(y), 0, 0);
	}

	@Override
	public void setPoint(int x, int y) {
		this.ellipse.setFrame(Math.min(x, basicx), Math.min(y, basicy), Math.abs(x-basicx), Math.abs(y-basicy));

	}
	
	public void addPoint(int x, int y) {}
	public GShape newInstance() {return new GEllipse();}
	public GShape selectedCheck(GShape gShape) {return null;}

}
