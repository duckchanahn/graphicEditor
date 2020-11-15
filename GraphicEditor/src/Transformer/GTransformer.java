package Transformer;

import java.awt.Graphics2D;
import java.util.Vector;

import shape.GShape;

public abstract class GTransformer {

	private GShape gShape;
	
	public GTransformer() {
		this.setgShape(null);		
	}
	
	public GShape getgShape() {return gShape;}
	public void setgShape(GShape gShape) {this.gShape = gShape;}

	public abstract void initTransforming(Graphics2D graphics, int x, int y);
	public abstract void keepTransforming(Graphics2D graphics, int x, int y);
	public abstract void finishTransforming(Graphics2D graphics, int x, int y);
	public abstract void continueTransforming(Graphics2D graphics, int x, int y);

}
