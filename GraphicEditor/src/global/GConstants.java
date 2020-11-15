package global;


import shape.GEllipse;
import shape.GLine;
import shape.GPolygon;
import shape.GRectangle;
import shape.GShape;
import shape.Pen;
import shape.Select;

public class GConstants {
	// ���ڿ� ���ڸ� ������ ���̴�.

	public enum EMainFrame {

		x(200), y(100), w(420), h(600),;

		private int value;

		private EMainFrame(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	public enum EMenu {

		fileMenu("File"), editMenu("Edit"), colorMenu("color"),
		// ���߿� resource�� �����س��� ���� ����.
		;

		private String text;

		private EMenu(String Text) {
			this.text = Text;
		}

		public String getText() {
			return this.text;
		}

	}
	
	public enum EDrawSome {
		rectangle("Rectangle"),
		ellipse("Ellipse"),
		line("Line")
		;
		private String text;
		private EDrawSome(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
	}

	public enum EShapeTool {

		Pen("��", new Pen(), "image/Pen1.png", "image/Pen2.png"),
		Rectangle("�׸�", new GRectangle(), "image/Rectangle1.png", "image/Rectangle2.png"),
		Polygon("������", new GPolygon(), "image/Polygon1.png", "image/Polygon2.png"),
		Select("����", new Select(), "image/Select1.png", "image/Select2.png"),
		Ellipse("��", new GEllipse(), "image/Ellipse1.png", "image/Ellipse2.png"),
		Line("��", new GLine(), "image/Line1.png", "image/Line2.png"),
		;


		private String text;
		private GShape shape;
		private String image;
		private String pImage;

		private EShapeTool(String Text, GShape shape, String image, String pImage) {
			this.text = Text;
			this.shape = shape;
			this.image = image;
			this.pImage = pImage;
		}

		public String getText() {
			return this.text;
		}

		public GShape getShape() {
			return this.shape;
		}
		
		public String getImage() {
			return this.image;
		}
		
		public String getPImage() {
			return this.pImage;
		}

	}
	
	public enum EDrawTool {

//		Redf("����f", "image/FillRed1.png", "image/FillRed2.png"),
//		Blackf("����f", "image/FillBlack1.png", "image/FillBlack2.png"),
//		Greenf("�ʷ�f", "image/FillGreen1.png", "image/FillGreen2.png"),
		
		Redl("����l", "image/LineRed1.png", "image/LineRed2.png"),
		Blackl("����l", "image/LineBlack1.png", "image/LineBlack2.png"),
		Greenl("�ʷ�l", "image/LineGreen1.png", "image/LineGreen2.png"),
		Colorf("ä���", "image/FillColor1.png", "image/FillColor2.png"),
		Linef("��", "image/LineColor1.png", "image/LineColor2.png"),
		downL("���", "image/downL1.png", "image/downL2.png"),
		upL("���", "image/upL1.png", "image/upL2.png"),
		;


		private String text;
		private String normalI;
		private String pressI;
		
		private EDrawTool(String Text, String normalI, String pressI) {
			this.text = Text;
			this.normalI = normalI;
			this.pressI = pressI;
		}

		public String getText() {
			return this.text;
		}
		public String getNormalI() {
			return this.normalI;
		}
		public String getPressI() {
			return this.pressI;
		}
	}

	public enum EFileMenu {

		newItem("���θ����", "nnew"), 
		openItem("����", "open"),
		saveItem("����", "save"),
		saveAsItem("�ٸ��̸����� ����", "saveAs"),
		seperate("�и�", "seperate"),
		printItem("�μ�", "print"),
		closeItem("�ݱ�", "close"),;

		private String text;
		private String method;

		private EFileMenu(String text, String method) {
			this.text = text;
			this.method = method;
		}

		public String getText() {
			return this.text;
		}
		
		public String getMethod() {
			return this.method;
		}

	}
	
	public enum EEditMenu {
		undo("�ǵ�����", "undo"),
		redo("�ٽý���", "redo"),
		seperate1("�и�", "seperate"),
		cut("�߶󳻱�", "cut"),
		copy("����", "copy"),
		paste("�ٿ��ֱ�", "paste"),
		seperate2("�и�", "seperate"),
		group("�׷�", "group"),
		ungroup("�׷�����", "ungroup")
		;
		private String text;
		private String method;
		
		private EEditMenu(String text, String method) {
			this.text = text;
			this.method = method;
		}
		public String getText() {
			return this.text;
		}
		public String getMethod() {
			return this.method;
		}
	}
	
	public enum EColorMenu { 
	      SetLineColor , 
	      ClearLineColor , 
	      SetFillColor , 
	      ClearFillColor };

}
