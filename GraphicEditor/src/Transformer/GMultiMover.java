package Transformer;

import java.awt.Graphics2D;
import java.util.Vector;

import shape.GShape;

public class GMultiMover extends GSelectTrans {

	@Override
	public void setValues(Graphics2D graphics, int x, int y) {}

	@Override
	public void initTransforming(Graphics2D graphics, int x, int y) {
		if(this.group) {
		this.getgShape().initMoving(graphics, x, y);
		}
		for (GShape shape : this.getgShapeVector()) {
			shape.initMoving(graphics, x, y);
		}
	}

	@Override
	public void keepTransforming(Graphics2D graphics, int x, int y) {
		if(this.group) {
			this.getgShape().transformMoveShape(graphics, x, y);
		}
			
		for (GShape shape : this.getgShapeVector()) {
			shape.transformMoveShape(graphics, x, y);
		}
	}

	@Override
	public void finishTransforming(Graphics2D graphics, int x, int y) {
		if(this.group) {
			this.getgShape().setBasic();
		}
			
		for (GShape shape : this.getgShapeVector()) {
			shape.setBasic();
		}
	}
}
