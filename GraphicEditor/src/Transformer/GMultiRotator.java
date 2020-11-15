package Transformer;

import java.awt.Graphics2D;

import shape.GAnchors.EAnchors;
import shape.GShape;

public class GMultiRotator extends GSelectTrans {


	@Override
	public void setValues(Graphics2D graphics, int x, int y) {
		
	}
	
	@Override
	public void initTransforming(Graphics2D graphics, int x, int y) {
//		this.getgShape().setCenter();

		if(this.getgShapeVector() == null) {
			this.getgShape().finishRotating(graphics, x, y);
		    this.getgShape().initRotating(graphics, x, y);
		} else {
			for(GShape shape: this.getgShapeVector()) {
				shape.setAnchor(EAnchors.RR);
				shape.finishRotating(graphics, x, y);
				shape.initRotating(graphics, x, y);
			}
		}
	    
	}

	@Override
	public void keepTransforming(Graphics2D graphics, int x, int y) {
		this.getgShape().setTheta(graphics, x, y);
		for (GShape shape : this.getgShapeVector()) {
			shape.setTheta(this.getgShape().getTheta());
			shape.transformRotateShape(graphics, x, y);
		}
	}

	@Override
	public void finishTransforming(Graphics2D graphics, int x, int y) {
		if(this.getgShapeVector() == null) {
			this.getgShape().setBasic();
			this.getgShape().finishRotating(graphics, x, y);
				} else {
			for(GShape shape: this.getgShapeVector()) {
				shape.setBasic();
				shape.finishRotating(graphics, x, y);
				}
		}
	}

}
