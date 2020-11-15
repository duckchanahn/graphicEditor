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
		// 메뉴를 하나 만들면 2줄씩 추가해주어야한다.
		// 현재 누르면 작동을 안하는데 class를 분리해서 독립적으로 작동하게 만들어야한다.
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
