package Transformer;

import java.awt.Graphics2D;
import java.util.Vector;

import shape.GShape;

public class GDrawer extends GTransformer {
	
	public GDrawer() {
	}

	@Override
	public void initTransforming(Graphics2D graphics, int x, int y) {
		this.getgShape().setOrigin(x, y);
	}

	@Override
	public void keepTransforming(Graphics2D graphics, int x, int y) {
		this.getgShape().setPoint(x, y);
	}

	@Override
	public void finishTransforming(Graphics2D graphics, int x, int y) {
		
	}

	@Override
	public void continueTransforming(Graphics2D graphics, int x, int y) {
		this.getgShape().addPoint(x, y);
	}


}
