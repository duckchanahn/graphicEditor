package shape;

import java.awt.Rectangle;

public class GRectangle extends GShape {
	private static final long serialVersionUID = 1L;
	
	private Rectangle rectangle;
	
//	public void setRectangle(java.awt.Shape rectangle) {this.rectangle = (java.awt.Rectangle)rectangle;}

	public GRectangle() {
		super();
		this.shape = new Rectangle();
		
		this.rectangle = (Rectangle)this.shape;
	}
	
	public void setOrigin(int x, int y) {
		basicx = x;
		basicy = y;		
		this.rectangle.setBounds(Math.abs(x), Math.abs(y), 0, 0);
	}

	public void setPoint(int x, int y) {
		this.rectangle.setFrame(Math.min(x, basicx), Math.min(y, basicy), Math.abs(x-basicx), Math.abs(y-basicy));
		}

	public void addPoint(int x, int y) {}
	public GShape newInstance() {return new GRectangle();}
	public GShape selectedCheck(GShape gShape) {return null;}
}
