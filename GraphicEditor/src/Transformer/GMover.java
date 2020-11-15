package Transformer;

import java.awt.Graphics2D;
import java.util.Vector;

import shape.GShape;

public class GMover extends GTransformer {

	public GMover() {
		
	}

	@Override
	public void initTransforming(Graphics2D graphics, int x, int y) {
		this.getgShape().initMoving(graphics, x, y);	
		}

	@Override
	public void keepTransforming(Graphics2D graphics, int x, int y) {
		this.getgShape().transformMoveShape(graphics, x, y);
		}

	@Override
	public void finishTransforming(Graphics2D graphics, int x, int y) {
		this.getgShape().setBasic();
		}

	@Override
	public void continueTransforming(Graphics2D graphics, int x, int y) {}
}
