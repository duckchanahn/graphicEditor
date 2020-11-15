package Transformer;

import java.awt.Graphics2D;

import shape.GAnchors.EAnchors;
import shape.GShape;

public class GRotator extends GTransformer {

	@Override
	public void initTransforming(Graphics2D graphics, int x, int y) {
		this.getgShape().finishRotating(graphics, x, y);
	    this.getgShape().initRotating(graphics, x, y);
	    }

	@Override
	public void keepTransforming(Graphics2D graphics, int x, int y) {
		this.getgShape().setTheta(graphics, x, y);
		this.getgShape().transformRotateShape(graphics, x, y);
		}

	@Override
	public void finishTransforming(Graphics2D graphics, int x, int y) {
		this.getgShape().setBasic();
		this.getgShape().finishRotating(graphics, x, y);
		}
	@Override
	public void continueTransforming(Graphics2D graphics, int x, int y) {}
}
