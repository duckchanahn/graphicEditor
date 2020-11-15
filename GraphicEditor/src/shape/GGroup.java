package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Vector;

public class GGroup extends GShape{
	private static final long serialVersionUID = 1L;
	
	private Rectangle rectangle;

	public GGroup() {
		super();
		this.shape = new Rectangle();
		this.rectangle = (Rectangle)this.shape;
	}

	@Override
	public void setOrigin(int x, int y) {
		int count = 0;
		groupx = new double[selectV.size()];
		groupy = new double[selectV.size()];
		groupw = new double[selectV.size()];
		grouph = new double[selectV.size()];
		basic = new Point2D.Double();
		last = new Point2D.Double();
		for (GShape shape : selectV) {
			groupx[count] = shape.basicx;
			groupy[count] = shape.basicy;
			groupw[count] = shape.basicx + shape.getWidth();
			grouph[count] = shape.basicy + shape.getHeight();
			count++;
		}
		Arrays.sort(groupx);
		Arrays.sort(groupy);
		
		basic.setLocation(groupx[0], groupy[0]);
		last.setLocation(groupw[groupw.length-1], grouph[grouph.length-1]);
		this.rectangle.setFrame(basic.getX(), basic.getY(), last.getX() - basic.getX(), last.getY() - basic.getY());
	}
	public void setPoint(int x, int y) {
		this.rectangle.setFrame(basic.getX(), basic.getY(), last.getX() - basic.getX(), last.getY() - basic.getY());
	}

	@Override
	public void addPoint(int x, int y) {}
	@Override
	public GShape newInstance() {return new GGroup();}
	@Override
	public GShape selectedCheck(GShape gShape) {return null;}

}
