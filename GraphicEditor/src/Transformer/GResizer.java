package Transformer;

import java.awt.Graphics2D;

public class GResizer extends GTransformer {

	public GResizer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initTransforming(Graphics2D graphics, int x, int y) {
		this.getgShape().initResizing(graphics, x, y);		
	}

	@Override
	public void keepTransforming(Graphics2D graphics, int x, int y) {

		this.getgShape().setResizing(graphics, x, y);
		this.getgShape().setOrigin();
		this.getgShape().transformResizeShape(graphics, x, y);
		
	}

	@Override
	public void finishTransforming(Graphics2D graphics, int x, int y) {

		this.getgShape().finishResizing(graphics, x, y);

	}
	@Override
	public void continueTransforming(Graphics2D graphics, int x, int y) {}


}
