package shape;

import java.awt.Rectangle;

public class Select extends GShape {
	private static final long serialVersionUID = 1L;

	private Rectangle rectangle;
	private int rw, rh;
	
	public Select() {
		super();
		this.shape = new java.awt.Rectangle();
		this.rectangle = (java.awt.Rectangle) this.shape;	
		}
	

	@Override
	public void setOrigin(int x, int y) {
		basicx = x;
		basicy = y;
		this.rectangle.setBounds(Math.abs(x), Math.abs(y), 0, 0);	
	}

	@Override
	public void setPoint(int x, int y) {
//		rw = x-this.rectangle.x;
//		rh = y-this.rectangle.y;
//		this.rectangle.setSize(rw, rh);
		this.rectangle.setFrame(Math.min(x, basicx), Math.min(y, basicy), Math.abs(x-basicx), Math.abs(y-basicy));
	}

	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public GShape newInstance() {return new Select();}

	@Override
	public GShape selectedCheck(GShape gShape) {
		if (this.rectangle.contains(gShape.shape.getBounds())) {
			return gShape;
		}
		return null;
	}

}
