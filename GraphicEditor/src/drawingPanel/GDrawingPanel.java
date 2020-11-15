package drawingPanel;

import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Vector;

import javax.swing.JPanel;

import Transformer.GDrawer;
import Transformer.GMover;
import Transformer.GMultiMover;
import Transformer.GMultiResizer;
import Transformer.GMultiRotator;
import Transformer.GResizer;
import Transformer.GRotator;
import Transformer.GSelectTrans;
import Transformer.GTransformer;
import global.GConstants.EShapeTool;
import shape.GGroup;
import shape.GPolygon;
//import shape.Polygon;
import shape.GShape;
import shape.GShape.EOnState;
import shape.Select;

public class GDrawingPanel extends JPanel implements Printable {

	private static final long serialVersionUID = 1L;

	private enum EActionState {eReady, eTransforming, ePolygon};

	private EActionState eActionState;
	
	private MouseHandler mouseHandler;
	private Clipboard clipboard;
	
	private Vector<GShape> selectV;
//	private Vector<Integer> cv;
	private Vector<GShape> shapeVector;

	
	//working variables
	private GShape currentShape;
	private GShape currentTool;
	private GGroup group;

	private boolean updated; 
	private boolean firstPoint;
	private boolean transAnc;
	
	private GTransformer transformer;
	private GSelectTrans selectTrans;
	
	private Color lineColor, fillColor;
	
	public boolean isupdated() {return this.updated;}
	public void setupdated(boolean updated) {this.updated = updated;}
	public void clearcurrent() {this.currentShape = null; this.repaint();}
	public Object getShapeVector() {return shapeVector;}
	@SuppressWarnings("unchecked")
	public void restoreShapeVector(Object shapeVector) {
		if(shapeVector == null) {
			this.shapeVector.clear();
		} else {
		this.shapeVector = (Vector<GShape>)shapeVector;
		} 
		this.repaint();
		}

	public void setCurrentTool(EShapeTool currentTool) {
		this.currentTool = currentTool.getShape();
	}

	public void setCurrentTool(GShape shape) {
		this.currentTool = shape;
		
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	 //생성자
	public GDrawingPanel() {
		this.eActionState = EActionState.eReady;
		this.updated = false;
		this.firstPoint = false;
		this.transAnc = false;
		this.wSelect = false;
		
		this.setForeground(Color.BLACK);
		this.setBackground(Color.WHITE);

		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);
		
		this.clipboard = new Clipboard();

		this.shapeVector = new Vector<GShape>();
		this.transformer = null;
		this.selectTrans = null;
		
//		this.cv = null;
		
		initializeGraphicsAttributes();

	}

	public void initialize() {
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////

	
	boolean wSelect; 
	
	//paint
	public void paint(Graphics graphics) {
		super.paint(graphics);

		for (GShape shape : this.shapeVector) {	
			shape.setSelected(false);
		}
	
		for (GShape shape : this.shapeVector) {
			
			shape.draw((Graphics2D) graphics);
			
			if(shape instanceof GGroup) {
				for(GShape gshape: shape.getVector()) {
					gshape.setSelected(false);
					gshape.draw((Graphics2D)graphics);
				}
			} 
		}

		if (currentShape != null) {
			this.currentShape.setSelected(true);
			if(this.transformer.getgShape() instanceof Select) {
				currentShape.select((Graphics2D) graphics);
			}else {
				this.currentShape.draw((Graphics2D) graphics);
			}
			
		}
		
		if(this.selectV != null) {
		for(GShape shape: this.selectV) {
			if(transAnc) {
				shape.setSelected(true);
				shape.drawAnchor((Graphics2D) graphics);
			}
		}
		}
		
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//컬러
	float dashes;
	
	   public void setLineColor(Color lineColor) {
		      if (selectedSetColor(true, lineColor)) {
		         return;
		      }
		      this.lineColor = lineColor;
		   }

		   public void setFillColor(Color fillColor) {
		      if (selectedSetColor(false, fillColor)) {
		         return;
		      }
		      this.fillColor = fillColor;
		   }

			public void setStroke(boolean flag) {
				if(currentShape != null) {
				currentShape.setStroke(flag);
				repaint();
				}
			}
			
			private boolean selectedSetColor(boolean flag, Color color) {
				if (currentShape != null) {
					if (flag) {
						currentShape.setLineColor(color);
					} else {
						currentShape.setFillColor(color);
					}
					repaint();
					return true;
				}
				return false;
			}

			private void initializeGraphicsAttributes() {
				lineColor = Color.BLACK;
				fillColor = Color.WHITE;
				float dashes = 4;
				this.dashes = dashes;
			}
			/////////////////////////////////////////////////////////////////////////////////////////////////////
		   /////////////////////////////////////////////////////////////////////////////////////////////////////
		   // 상황판단
	private EOnState onShape(int x, int y) {

		for(GShape shape: this.shapeVector) {
			EOnState eOnState = shape.onShape(x, y);
			if(eOnState != null) {
				this.currentShape = shape;
				return eOnState;
			}
		}
		return null;
	}
	
	private void defineActionState(int x, int y) {

		if(!(currentTool instanceof GPolygon)) {
			this.eActionState = EActionState.eTransforming;
		} else {
			this.eActionState = EActionState.ePolygon;
		}
		
		EOnState eOnState = onShape(x, y);
	
		if(eOnState == null) {
				this.transformer = new GDrawer(); 
				this.select = false;
			}else {
				if(currentShape instanceof GGroup) {
					this.selectV = currentShape.getVector();
				}else if(selectV != null) {
					if(!selectV.contains(currentShape)) {
						selectV.clear();
//						this.cv = null;
					}
				}
				
				switch(eOnState) {
					case eOnShape:
						this.transformer = new GMover(); 
						this.selectTrans = new GMultiMover();
						break;
					case eOnResize:
						this.transformer = new GResizer();
						this.selectTrans = new GMultiResizer();
						break;
					case eOnRotate:
						this.transformer = new GRotator();
						this.selectTrans = new GMultiRotator();
						break;
					default:
						eActionState =  null;
						break;
				}
				this.eActionState = EActionState.eTransforming;
			}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//Select
	private void selectOnShape() {
		this.selectV = new Vector<GShape>();
		int count = 0;
//		cv = new Vector<Integer>();
		GShape gS;
		for(GShape gShape: this.shapeVector) {
			gS = this.transformer.getgShape().selectedCheck(gShape);
			if(gS != null) {
			this.selectV.add(gS);
			gS.setSelected(true);
//			cv.add(count);
			}
			count++;
		}
//		for(int i = 0; i<cv.size(); i++) {
//			shapeVector.setElementAt(selectV.elementAt(i), cv.elementAt(i));
//		}
//		repaint();
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//트랜스포밍
	boolean select;
	private void initTransforming(int x, int y) {
		for (GShape shape : this.shapeVector) {	
			shape.setSelected(false);
		}
		repaint();

		if (this.transformer instanceof GDrawer) {
			select = false;
			this.currentShape = this.currentTool.clone();
			this.currentShape.setLineColor(lineColor);
			this.currentShape.setFillColor(fillColor);
			this.transformer.setgShape(this.currentShape);
			this.transformer.initTransforming((Graphics2D) this.getGraphics(), x, y);
		} else {
			

			
			if (selectV == null) {
				select = false;
				this.transformer.setgShape(this.currentShape);
				this.transformer.initTransforming((Graphics2D) this.getGraphics(), x, y);
			} else if(selectV.size() == 0) {
				select = false;
				this.transformer.setgShape(this.currentShape);
				this.transformer.initTransforming((Graphics2D) this.getGraphics(), x, y);
			} else if (selectV.size() == 1){
				select = false;
				this.currentShape = this.selectV.elementAt(0);
				this.transformer.setgShape(this.currentShape);
				this.transformer.initTransforming((Graphics2D) this.getGraphics(), x, y);	
			}else {
				select = true;
				if(currentShape instanceof GGroup) {
					this.selectTrans.setgroup(true);
				} else {
					this.selectTrans.setgroup(false);
				}
				this.selectTrans.setgShape(currentShape);
				this.selectTrans.setgShapeVector(selectV);
				this.selectTrans.setValues((Graphics2D) this.getGraphics(), x, y);
				this.selectTrans.initTransforming((Graphics2D) this.getGraphics(), x, y);
			}
		
		}

	}
	
	private void continueTransforming(int x, int y) {
		this.transformer.continueTransforming((Graphics2D)this.getGraphics(), x, y);
	}	

	private void keepTransforming(int x, int y) {
		Graphics2D graphics2d = (Graphics2D) this.getGraphics();
		this.transAnc = false;
		repaint();
		if (!select) {
			this.transformer.keepTransforming(graphics2d, x, y);
		} else {
			this.selectTrans.keepTransforming(graphics2d, x, y);
		}
	}
	


	private void finishTransforming(int x, int y) { 
		///////////////////////////////////////////////////////
		
		this.transAnc = true;
		if(!select) {
			this.transformer.finishTransforming((Graphics2D)this.getGraphics(), x, y);
			if(!(this.transformer.getgShape() instanceof Select)) {
				if(this.transformer instanceof GDrawer) {
					this.shapeVector.add(this.currentShape);
				}	
				this.currentShape.drawAnchor((Graphics2D) this.getGraphics());
				} else {
//					selectOnShape();
					this.wSelect = true;
					currentShape = null;
					repaint();
				}
		} else {
			this.selectTrans.finishTransforming((Graphics2D)this.getGraphics(), x, y);
			if(selectTrans.getgroup()) {
				Vector<GShape> imsi = this.selectTrans.getgShapeVector();
				for(int i = currentShape.getVector().size() - 1; i >= 0; i--) {
					this.currentShape.setVector(imsi.elementAt(i), i);
					this.currentShape.getVector().elementAt(i).setSelected(false);
				}
				this.currentShape.setSelected(true);
				this.currentShape.drawAnchor((Graphics2D) this.getGraphics());
			} else {
			this.changeVector();
			}
		}
		this.clipboard.setVector(shapeVector);
//		this.currentTool = EShapeTool.Select.getShape();
		this.updated = true;
	}
	
	public void changeVector() {
		Vector<GShape> selectTrans = this.selectTrans.getgShapeVector();			
		int index = selectTrans.size() - 1;
		for(int i = this.shapeVector.size() - 1; i >= 0; i--) {
			if(this.shapeVector.elementAt(i).equals(selectV.elementAt(index))) {
				this.shapeVector.set(i, selectTrans.elementAt(index));
			}
		}
		repaint();
	}
	
	public void removeVector() {
		for(int i = this.shapeVector.size()-1; i>=0; i--) {
			if(this.shapeVector.get(i).isSelected()) {
				this.shapeVector.elementAt(i).setSelected(false);
				this.shapeVector.remove(i);	//기존 벡터에서 없앰.
			}
		}
		repaint();
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//Edit
	public void cut() {
		Vector<GShape> selectedShapes = new Vector<GShape>();
		for(int i = this.shapeVector.size()-1; i>=0; i--) {
			if(this.shapeVector.get(i).isSelected()) {
				selectedShapes.add(this.shapeVector.get(i));	//선택된 도형 선택, 새로운 벡터에 옮김
				this.shapeVector.remove(i);	//기존 벡터에서 없앰.
			}
		}
		this.clipboard.setVector(shapeVector);
		this.currentShape = null;
		this.clipboard.setContents(selectedShapes);	//클립보드에 저장?
		this.repaint();
	}
	public void copy() {
		Vector<GShape> selectedShapes = new Vector<GShape>();
		for(int i = this.shapeVector.size()-1; i>=0; i--) {
			if(this.shapeVector.get(i).isSelected()) {
				selectedShapes.add(this.shapeVector.get(i));	//선택된 도형 선택, 새로운 벡터에 옮김
			}
		}
		this.clipboard.setContents(selectedShapes);	//클립보드에 저장?
		this.repaint();
	}
	public void paste() {
		Vector<GShape> shapes = this.clipboard.getContents();
		this.shapeVector.addAll(shapes);
		this.clipboard.setVector(shapeVector);
		this.repaint();
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	public void group() {
		
		currentShape = null;
		this.currentShape = new GGroup();
		for(GShape shape: this.shapeVector) {
			if(shape.isSelected()) {
				shape.setSelected(false);
				shape.setG(true);
				this.currentShape.addVector(shape);
			}
		}
		this.currentShape.setOrigin(0, 0);
		this.currentShape.setPoint(0, 0);
		
		this.currentShape.setSelected(true);
		this.currentShape.drawAnchor((Graphics2D)this.getGraphics());
		
		this.removeVector();
		this.shapeVector.add(currentShape);
		this.currentShape = null;
		this.transAnc = true;
		this.selectV = null;
		this.clipboard.setVector(shapeVector);
		repaint();
	}
	
	public void ungroup() {
		
		this.removeVector();
		for(GShape shape: this.currentShape.getVector()) {
			shape.setG(false);
			this.shapeVector.add(shape);
		}
		this.clipboard.setVector(shapeVector);
		repaint();
		
	}
	
	public void undo() {
		
		Vector<GShape> empty = this.clipboard.getVector();
		if(empty != null) {
			this.shapeVector = empty;
//			System.out.println(this.shapeVector.size());
		}
		repaint();
	}
	public void redo() {
		
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////

	private class MouseHandler implements MouseListener, MouseMotionListener {
		@Override
		public void mouseClicked(MouseEvent e) {

			if (eActionState == EActionState.ePolygon) {
				if (e.getClickCount() == 1) {
					mouse1Clicked(e);
				} else if (e.getClickCount() == 2) {
					mouse2Clicked(e);
				}
			}
		}

		public void mouse1Clicked(MouseEvent e) {
			if (firstPoint) {
				firstPoint = false;
			} else {
				continueTransforming(e.getX(), e.getY());
			}
		}

		public void mouse2Clicked(MouseEvent e) {
			finishTransforming(e.getX(), e.getY());
			firstPoint = true;
			eActionState = EActionState.eReady;
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (eActionState == EActionState.ePolygon) {
				keepTransforming(e.getX(), e.getY());
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eActionState == EActionState.eReady) {
				defineActionState(e.getX(), e.getY()); // 전과 2범
				initTransforming(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			transAnc = false;
			if (eActionState == EActionState.eTransforming) {
				keepTransforming(e.getX(), e.getY());

			}
		}

		public void mouseReleased(MouseEvent e) {
//			System.out.println(1);
			if (eActionState == EActionState.eTransforming) {
				finishTransforming(e.getX(), e.getY()); // 체포완료!
				eActionState = EActionState.eReady;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}


	@Override
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
		if (page > 0) {return NO_SUCH_PAGE;}
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		for (GShape shape: this.shapeVector) {shape.draw((Graphics2D)g);}
		return PAGE_EXISTS;
	}

}
