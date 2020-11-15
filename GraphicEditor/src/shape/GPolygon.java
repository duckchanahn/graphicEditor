package shape;

import java.awt.Polygon;

public class GPolygon extends GShape {
	private static final long serialVersionUID = 1L;
	private Polygon polygon;
	
	public GPolygon() {
		super();
		this.polygon = new Polygon();
		this.shape = this.polygon;
	}
	public void setOrigin(int x, int y) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}
	public void setPoint(int x, int y) {
		
		this.polygon.xpoints[this.polygon.npoints-1] = x;
		this.polygon.ypoints[this.polygon.npoints-1] = y;
	}
	public void addPoint(int x, int y) {
//		System.out.println("1");
		this.polygon.addPoint(x, y);
	}

	@Override
	public GShape newInstance() {return new GPolygon();}
	@Override
	public GShape selectedCheck(GShape gShape) {return null;}
	
}
