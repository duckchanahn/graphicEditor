package menu;
import javax.swing.JMenuBar;

import drawingPanel.GDrawingPanel;
import global.GConstants.EMenu;

public class GMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	// component
	private GFileMenu fileMenu;
	private GEditMenu editMenu;
	private GEMenuColor colorMenu;
	
	private GDrawingPanel drawingPanel; 

	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}

	public GMenuBar() {
		this.fileMenu = new GFileMenu(EMenu.fileMenu.getText());
		this.add(this.fileMenu);
		
	    this.editMenu = new GEditMenu(EMenu.editMenu.getText());
	    this.add(this.editMenu);
	      
	    this.colorMenu = new GEMenuColor(EMenu.colorMenu.getText());
	    this.add(this.colorMenu);
		// �޴��� �ϳ� ����� 2�پ� �߰����־���Ѵ�.
		// ���� ������ �۵��� ���ϴµ� class�� �и��ؼ� ���������� �۵��ϰ� �������Ѵ�.
	}

	public void initialize() {
//		  System.out.println(drawingPanel.getClass());
		// TODO Auto-generated method stub
	    this.fileMenu.associate(drawingPanel);
	    this.editMenu.associate(drawingPanel);
	    this.colorMenu.associate(drawingPanel);

		this.fileMenu.initialize();
		this.editMenu.initialize();
		this.colorMenu.initialize();
	}

}
