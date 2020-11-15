package drawingPanel;

import java.util.Vector;

import shape.GShape;

public class Clipboard {
	private Vector<GShape> shapes;
	private Vector<Vector<GShape>> shapeVector;
	private Vector<Vector<GShape>> redoVector;

	public Clipboard() {
		this.shapes = new Vector<GShape>();
		this.shapeVector = new Vector<Vector<GShape>>();
		this.redoVector = new Vector<Vector<GShape>>();
	}
	public void setContents(Vector<GShape> shapes) {
		this.shapes.clear();
		this.shapes.addAll(shapes);	//어레이 한꺼번에 들어감.
	}
	public Vector<GShape> getContents() {
		Vector<GShape> clonedShapes = new Vector<GShape>();
		for(GShape shape: this.shapes) {
			clonedShapes.add(shape.clone());
		}
		return clonedShapes;
	}
	
	@SuppressWarnings("unchecked")
	public void setVector(Vector<GShape> shapeVector) {
		this.shapeVector.add((Vector<GShape>) shapeVector.clone());
		System.out.println(this.shapeVector.size());
	}
	
	public Vector<GShape> getVector() {
		if(shapeVector.size() > 1) {
			this.redoVector.add(shapeVector.elementAt(shapeVector.size()-1));
			shapeVector.remove(shapeVector.size()-1);
			return shapeVector.elementAt(shapeVector.size()-1);
		}
		return null;
	}
	
}
