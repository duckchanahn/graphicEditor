package global;


import shape.GEllipse;
import shape.GLine;
import shape.GPolygon;
import shape.GRectangle;
import shape.GShape;
import shape.Pen;
import shape.Select;

public class GConstants {
	// 글자와 숫자를 정리할 것이다.

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
		// 나중에 resource를 분해해놓는 것이 좋다.
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

		Pen("펜", new Pen(), "image/Pen1.png", "image/Pen2.png"),
		Rectangle("네모", new GRectangle(), "image/Rectangle1.png", "image/Rectangle2.png"),
		Polygon("폴리곤", new GPolygon(), "image/Polygon1.png", "image/Polygon2.png"),
		Select("선택", new Select(), "image/Select1.png", "image/Select2.png"),
		Ellipse("원", new GEllipse(), "image/Ellipse1.png", "image/Ellipse2.png"),
		Line("선", new GLine(), "image/Line1.png", "image/Line2.png"),
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

//		Redf("빨강f", "image/FillRed1.png", "image/FillRed2.png"),
//		Blackf("검정f", "image/FillBlack1.png", "image/FillBlack2.png"),
//		Greenf("초록f", "image/FillGreen1.png", "image/FillGreen2.png"),
		
		Redl("빨강l", "image/LineRed1.png", "image/LineRed2.png"),
		Blackl("검정l", "image/LineBlack1.png", "image/LineBlack2.png"),
		Greenl("초록l", "image/LineGreen1.png", "image/LineGreen2.png"),
		Colorf("채우기", "image/FillColor1.png", "image/FillColor2.png"),
		Linef("선", "image/LineColor1.png", "image/LineColor2.png"),
		downL("얇게", "image/downL1.png", "image/downL2.png"),
		upL("얇게", "image/upL1.png", "image/upL2.png"),
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

		newItem("새로만들기", "nnew"), 
		openItem("열기", "open"),
		saveItem("저장", "save"),
		saveAsItem("다른이름으로 저장", "saveAs"),
		seperate("분리", "seperate"),
		printItem("인쇄", "print"),
		closeItem("닫기", "close"),;

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
		undo("되돌리기", "undo"),
		redo("다시실행", "redo"),
		seperate1("분리", "seperate"),
		cut("잘라내기", "cut"),
		copy("복사", "copy"),
		paste("붙여넣기", "paste"),
		seperate2("분리", "seperate"),
		group("그룹", "group"),
		ungroup("그룹해제", "ungroup")
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
