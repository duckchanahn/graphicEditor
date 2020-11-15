package Transformer;

import java.awt.Graphics2D;
import java.util.Vector;

import shape.GShape;
import shape.GShape.EOnState;

public abstract class GSelectTrans {
	
	private GShape gShape;
	private Vector<GShape> gShapeVector;
	protected boolean group;
	public void setgroup(boolean b) {this.group = b;}
	public boolean getgroup() {return this.group;}
	
	public GShape getgShape() {return gShape;}
	public void setgShape(GShape gShape) {this.gShape = gShape;}
	
	public Vector<GShape> getgShapeVector() {return gShapeVector;}
	public void setgShapeVector(Vector<GShape> gShapeVector) {this.gShapeVector = gShapeVector;}
	public void addgShapeVector(GShape shape) {this.gShapeVector.add(shape);}
	
	public abstract void setValues(Graphics2D graphics, int x, int y);
	public abstract void initTransforming(Graphics2D graphics, int x, int y);
	public abstract void keepTransforming(Graphics2D graphics, int x, int y);
	public abstract void finishTransforming(Graphics2D graphics, int x, int y);

	

}
