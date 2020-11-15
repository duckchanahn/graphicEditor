package Transformer;

import java.awt.Graphics2D;

import shape.GShape;

public class GMultiResizer extends GSelectTrans {


	@Override
	public void setValues(Graphics2D graphics, int x, int y) {
		for (GShape shape : this.getgShapeVector()) {
			shape.setAnchor(this.getgShape().getAnchor());
		}
	}

	@Override
	public void initTransforming(Graphics2D graphics, int x, int y) {	
		for (GShape shape : this.getgShapeVector()) {
			shape.initResizing(graphics, x, y);	
		}
	}

	@Override
	public void keepTransforming(Graphics2D graphics, int x, int y) {
		this.getgShape().setResizing(graphics, x, y);
		for (GShape shape : this.getgShapeVector()) {
			shape.setOrigin();
			shape.setxy(this.getgShape().getxy());
			shape.transformResizeShape(graphics, x, y);
		}
	}

	@Override
	public void finishTransforming(Graphics2D graphics, int x, int y) {
		for (GShape shape : this.getgShapeVector()) {
			shape.finishResizing(graphics, x, y);
		}
	}
}
