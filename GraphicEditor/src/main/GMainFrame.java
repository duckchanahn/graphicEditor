package main;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import drawingPanel.GDrawingPanel;
import global.GConstants.EMainFrame;
import menu.GMenuBar;
import toolbar.GDrawTool;
import toolbar.GShapeTool;

public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	//component
	private GMenuBar menuBar;
	private GShapeTool ShapeTool;
	private GDrawTool drawTool;
    private GDrawingPanel drawingPanel;
    
	public GMainFrame() {
		// constructor
        //attribute
		this.setLocation(EMainFrame.x.getValue(), EMainFrame.y.getValue());
		this.setLayout(new BorderLayout());
		this.setSize(EMainFrame.w.getValue(), EMainFrame.h.getValue());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 위에 3가지는 프로그램을 짰을 때 기본적으로 진행해야한다.
		
		//menu
		this.menuBar = new GMenuBar();
		this.setJMenuBar(this.menuBar);
		
		//toolBar
		this.ShapeTool = new GShapeTool();
		this.add(this.ShapeTool,BorderLayout.SOUTH);
		
		this.drawTool = new GDrawTool();
		this.add(this.drawTool,BorderLayout.NORTH);
		
		//drawingPaenl
		this.drawingPanel = new GDrawingPanel();
		this.add(this.drawingPanel,BorderLayout.CENTER);
		
		//association
		this.menuBar.associate(this.drawingPanel);
		this.ShapeTool.associate(this.drawingPanel);
		this.drawTool.associate(this.drawingPanel);
//		this.drawTool.associate(this.drawingPanel);
		
	}
	public void initialize() {
		   this.menuBar.initialize();
		   this.ShapeTool.initialize();
		   this.drawTool.initialize();
		   this.drawingPanel.initialize();
		}
}
