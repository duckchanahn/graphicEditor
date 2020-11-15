package shape;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
//import java.awt.geom.;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

import shape.GAnchors.EAnchors;

public abstract class GShape implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	public enum EOnState {eOnShape, eOnResize, eOnRotate}; // 상태

	protected Shape shape;

	protected Shape currentShape;
	protected AffineTransform affineTransform;

	protected int basicx, basicy;
	protected int moveX, moveY;
	protected double dw, dh;
	protected int centerX, centerY;
	protected Point centerPoint;

	protected GAnchors anchors;
	protected EAnchors eAnchors;

	private boolean selected;
	public boolean isSelected() {return selected;}
	public void setSelected(boolean selected) {this.selected = selected;}

	private boolean resize;
	
	protected Color lineColor, fillColor;
	protected float dashes;
	
	//그룹용
	protected Vector<GShape> selectV;
	public void setVector(Vector<GShape> selectV) {this.selectV = selectV;}
	public Vector<GShape> getVector() {return this.selectV;}
	public void addVector(GShape shape) { this.selectV.add(shape); }
	public void setVector(GShape shape, int i) { this.selectV.set(i, shape);}
	public void clearVector() {this.selectV.clear();}
	
	protected double[] groupx;
	protected double[] groupy;
	protected double[] groupw;
	protected double[] grouph;
	protected Point2D basic;
	protected Point2D last;
	
	protected Vector<Point> pen;
	
	abstract public void setOrigin(int x, int y);
	abstract public void setPoint(int x, int y);
	abstract public void addPoint(int x, int y);
	abstract public GShape newInstance();
	
	public GShape() {
//		this.pretheta = 0;
		this.selected = false;
		this.resize = false;
		this.group = false;
		this.anchors = new GAnchors();
		this.dashes = 1;
		this.selectV = new Vector<GShape>();
		this.affineTransform = new AffineTransform();
		this.pen = new Vector<Point>();
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	public void setStroke(boolean flag) {
		if(flag) {
		this.dashes++;
		}else {
			if(dashes > 0) {
			this.dashes--;
			}
		}
	}
	
	public void setSelected(boolean selected, Graphics2D graphics2d) {
		this.selected = selected;
		if (selected) {
			this.anchors.setBoundingRect(this.shape.getBounds());
			this.anchors.draw(graphics2d);
		}
	}

	 public GShape clone() {// 딥 카피
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(this);

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			return (GShape) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	 
		
	 
	public void draw(Graphics2D graphics) {
		 graphics.setComposite(AlphaComposite.Src);
         graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
         graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
         graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         
		if(!(this.shape instanceof Line2D.Double)) {
		if(dashes != 0) {
			graphics.setStroke(new BasicStroke(dashes,BasicStroke.CAP_ROUND,0));	
		}
	      if(this.fillColor != null) {
	          graphics.setColor(this.fillColor);
	          graphics.fill(this.shape);
	       }
	       if(this.lineColor != null) {
	          graphics.setColor(this.lineColor);
	          graphics.draw(this.shape);
	       }
		} else {
			if(dashes != 0) {
				graphics.setStroke(new BasicStroke(dashes,BasicStroke.CAP_ROUND,0));	
			}
		       if(this.lineColor != null) {
		          graphics.setColor(this.lineColor);
		       }
		    	   drawLine(graphics);
		}
	}
	
	   public void drawLine(Graphics2D g2) {
		      if(pen != null) {
		      for (int i = 1; i < pen.size(); i++) {
		         if (pen.get(i - 1) == null)
		            continue;
		         else if (pen.get(i) == null)
		            continue;
		         else
		      g2.drawLine((int) pen.get(i - 1).getX(), (int) pen.get(i - 1).getY(),
		            (int) pen.get(i).getX(), (int) pen.get(i).getY());
		      }
		      }
		   }
	boolean group;
	public void setG(boolean g) { this.group = g; }
	
	public void drawAnchor(Graphics2D graphics) {
		if(!group) {
		if (this.selected && !(this.shape instanceof Pen)) {
			this.anchors.setBoundingRect(this.shape.getBounds());
			this.anchors.draw(graphics);
		}
		}
	}
	
	public void select(Graphics2D graphics) {
		float dashes[] = {4};
		graphics.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashes, 0));
		graphics.draw(this.shape);
		
	}
	
	abstract public GShape selectedCheck(GShape gShape);

	public EOnState onShape(int x, int y) {
		if (this.selected) {
			eAnchors = this.anchors.onShape(x, y);
			if (eAnchors == EAnchors.RR) { // rotate
				return EOnState.eOnRotate;
			} else if (eAnchors == null) {
				if (this.shape.contains(x, y)) {
					return EOnState.eOnShape;
				}
			} else { // resize
				return EOnState.eOnResize;
			}
		} else {
			if (this.shape.contains(x, y)) {
				this.selected = true;
				return EOnState.eOnShape;
			}
		}
		return null;
	}	
	
	public void setBasic() {
		this.basicx = this.shape.getBounds().x;
		this.basicy = this.shape.getBounds().y;
	}
	
	public EAnchors getAnchor() {return this.eAnchors;}
	public void setAnchor(EAnchors eAnchors) {this.eAnchors = eAnchors;}
	
	public void initMoving(Graphics2D graphics, int x, int y) {
		this.moveX = x;
		this.moveY = y;
	}
	
	public void transformMoveShape(Graphics2D graphics, int x, int y) {	
		this.currentShape = this.shape;

		this.affineTransform.setToTranslation(x-moveX, y-moveY);
		this.shape = this.affineTransform.createTransformedShape(this.currentShape);

		this.basicx = basicx + (x-moveX);
		this.basicy = basicy + (y-moveY);
		
		this.moveX = x;
		this.moveY = y;
	}
	
	public void initResizing(Graphics2D graphics, int x, int y) {
		this.moveX = x;
		this.moveY = y;
		this.preX = x;
		this.preY = y;
		this.nowX = x;
		this.nowY = y;
	}
	
	double preX, preY;
	double nowX, nowY;
	
	double deltaW;
	double deltaH;

	double width;
	double height;
	
	Point2D p = new Point2D.Double();
	Point2D xy = new Point2D.Double();

	double x1;
	double y1;
	
	public void setxy(Point2D xy) {this.xy = xy;}
	public Point2D getxy() {return xy;}
	
	public void setOrigin() {
		switch(eAnchors) {
		case SE:
			p.setLocation(
					anchors.getxAnchor().elementAt(0),
					anchors.getyAnchor().elementAt(0));
			break;	
		case SS:
			p.setLocation(
				anchors.getxAnchor().elementAt(1),
				anchors.getyAnchor().elementAt(1));
			break;	
		case SW: 
			p.setLocation(
				anchors.getxAnchor().elementAt(2),
				anchors.getyAnchor().elementAt(2));
			break;	
		case WW: 
			p.setLocation(
					anchors.getxAnchor().elementAt(3),
					anchors.getyAnchor().elementAt(3));
			break;	
		case NW:
			p.setLocation(
					anchors.getxAnchor().elementAt(4),
					anchors.getyAnchor().elementAt(4));
			break;	
		case NN: 
			p.setLocation(
					anchors.getxAnchor().elementAt(5),
					anchors.getyAnchor().elementAt(5));
			break;	
		case NE: 
			p.setLocation(
					anchors.getxAnchor().elementAt(6),
					anchors.getyAnchor().elementAt(6));
			break;	
		case EE: 
			p.setLocation(
					anchors.getxAnchor().elementAt(7),
					anchors.getyAnchor().elementAt(7));
			break;	
		default: break;	
		}
		}
	private void setPre(int index) {
		if (index == 2) {preX = preX + width; preY = preY + height;} 
		else if (index == 1) {preX = preX + width;}
		else if (index == 0) {preY = preY + height;}
	}
	
	public void setResizing(Graphics2D graphics, int x, int y) {
		deltaW = 0;
		deltaH = 0;
		
		if(!resize) {
			preX = this.shape.getBounds().x;
			preY = this.shape.getBounds().y;
		}
		
		this.nowX = x;
		this.nowY = y;
		
		 deltaW =-(nowX-preX);	deltaH=-(nowY-preY);
			
			width = this.shape.getBounds().getWidth();
			height = this.shape.getBounds().getHeight();
			
			switch(eAnchors) {
			case SE: this.setPre(2);
				deltaW =  nowX-preX; deltaH=  nowY-preY; break;
			case SS: this.setPre(2);
				deltaW =  0; deltaH=  nowY-preY; break;
			case SW: this.setPre(0);
				deltaW =-(nowX-preX); deltaH=  nowY-preY; break;
			case WW: 
				deltaW =-(nowX-preX); deltaH=  0;
				this.centerY = anchors.getyAnchor().elementAt(3); break;
			case NW: 
				deltaW =-(nowX-preX); deltaH=-(nowY-preY); break;
			case NN: 
				deltaW =  0; deltaH=-(nowY-preY); 
				this.centerX = anchors.getxAnchor().elementAt(5); break;
			case NE: this.setPre(1);
				deltaW =  nowX-preX; deltaH=-(nowY-preY); break;
			case EE: this.setPre(2);
				deltaW = nowX-preX;	deltaH=  0; break;
			default: break;	
			}
			
			x1 = 1.0;
			y1 = 1.0;
			if(width > 0.0) {x1 = deltaW / width + x1;} 
			if(height > 0.0) {y1 = deltaH / height + y1;}
			
			xy.setLocation(x1, y1);
	}
	
	public void transformResizeShape(Graphics2D graphics, int x, int y) {

		this.currentShape = this.shape;

		this.affineTransform.setToTranslation(p.getX(), p.getY()); // 더하기~
		this.affineTransform.scale(xy.getX(), xy.getY());
		this.affineTransform.translate(-p.getX(), -p.getY()); // 더하기		
			
		this.shape = this.affineTransform.createTransformedShape(this.currentShape);
		
		preX = this.shape.getBounds().x;
		preY = this.shape.getBounds().y;

	}
	
	public void finishResizing(Graphics2D graphics, int x, int y) {this.resize =false;}

	public void initRotating(Graphics2D graphics, int x, int y) {
		 centerPoint = new Point(centerX, centerY);
		 this.preX = x;
		 this.preY = y;
	     currentShape = this.shape;
	     
	      p1 = new Point(centerX, centerY);
//		System.out.println("1");
	}

	private Point2D p1;
	double theta;
	
	public void setTheta(Graphics2D graphics, int x, int y) {
		 Point p2 = new Point(x, y); 
		 theta = Math.atan2(p2.y - this.p1.getY(), p2.x - this.p1.getX());
	}
	
	public double getTheta() {return theta;}
	public void setTheta(double theta) {
		this.theta = theta;
	}
	
	public void transformRotateShape(Graphics2D graphics, int x, int y) {

		 affineTransform.setToRotation(theta, centerX, centerY);
		 this.shape = this.affineTransform.createTransformedShape(this.currentShape);
		 
		 this.preX = x;
		 this.preY = y;
	}

	public void finishRotating(Graphics2D graphics, int x, int y) {
		eAnchors = EAnchors.NN;
		this.setResizing(graphics, x, y);
		
		eAnchors = EAnchors.WW;
		this.setResizing(graphics, x, y);
	}
	public int getWidth() {return this.shape.getBounds().width;}
	public int getHeight() {return this.shape.getBounds().height;}
	public void a() {
		System.out.println(this.shape.getBounds());
	}
}
