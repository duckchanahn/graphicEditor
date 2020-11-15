package toolbar;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JToolBar;

import drawingPanel.GDrawingPanel;
import global.GConstants.EDrawTool;

public class GDrawTool extends JToolBar {
	private static final long serialVersionUID = 1L;

	private GDrawingPanel drawingPanel;
	private ColorMenuHandler colorMenuHandler;

	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}

	public GDrawTool() {
		colorMenuHandler = new ColorMenuHandler();
		for (EDrawTool eDrawTool : EDrawTool.values()) {
			ImageIcon i = new ImageIcon(eDrawTool.getNormalI());
			JButton button = new JButton(i);
			button.setActionCommand(eDrawTool.name());
			this.add(button);
			button.addActionListener(colorMenuHandler);
			button.setBorderPainted(false);
			button.setContentAreaFilled(false);
			button.setFocusable(false);			
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					ImageIcon imagePressed;
					imagePressed = new ImageIcon(eDrawTool.getPressI());			
					button.setIcon(imagePressed);
					button.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					ImageIcon image;
					image = new ImageIcon(eDrawTool.getNormalI());			
					button.setIcon(image);
					button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				});
		}

	}

	public void initialize() {
	}
	
	private void setLineColor() {
		Color lineColor = JColorChooser.showDialog(null, "Selected Fill Color", null);
		if (lineColor != null) {
			drawingPanel.setLineColor(lineColor);
		}
	}

	private void setFillColor() {
		Color fillColor = JColorChooser.showDialog(null, "Selected Line Color", null);
		if (fillColor != null) {
			drawingPanel.setFillColor(fillColor);
		}
	}

	private void LineColorBlack() {
		drawingPanel.setLineColor(Color.BLACK);
	}
	private void LineColorGreen() {
		drawingPanel.setLineColor(Color.GREEN);
	}
	private void LineColorRed() {
		drawingPanel.setLineColor(Color.RED);
	}
	
	private void setThickStroke() {	
		drawingPanel.setStroke(true);
	}
	private void setThinStroke() {
		drawingPanel.setStroke(false);
	}

	private class ColorMenuHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (EDrawTool.valueOf(e.getActionCommand())) {
			case Colorf:
				setFillColor();
				break;
			case Redl:
				LineColorRed();
				break;
			case Blackl:
				LineColorBlack();
				break;	
			case Greenl:
				LineColorGreen();
				break;
			case Linef:
				setLineColor();
				break;
			case downL:
				setThinStroke();
				break;
			case upL:
				setThickStroke();
				break;
			default:
				break;
			}
		}
}
}
