package toolbar;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import drawingPanel.GDrawingPanel;
import global.GConstants.EShapeTool;

public class GShapeTool extends JToolBar {
	private static final long serialVersionUID = 1L;
	// component
//	private Vector<JRadioButton> buttons;
	private Vector<JButton> buttons;
	private ImageIcon icon;
	private Vector<String> v;
	private String a;

	// association
	private GDrawingPanel drawingPanel;

	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}

	public GShapeTool() {
		ActionHandler actionHandler = new ActionHandler();
		this.buttons = new Vector<JButton>();
		this.setLayout(new GridLayout(1, 4));


		JPanel shape = new JPanel();

		int count = 0;
		shape.setLayout(new GridLayout(2, 3));
		this.v = new Vector<String>();
		for (EShapeTool eToolBar : EShapeTool.values()) {
//			if (eToolBar.getText() != "펜" && eToolBar.getText() != "선택") {
				icon = new ImageIcon(eToolBar.getImage());
				this.v.add(eToolBar.getImage());
				JButton button = new JButton();
				button.setIcon(icon);
				button.setActionCommand(eToolBar.name()); 
				button.addActionListener(actionHandler);
				this.buttons.add(button);
				button.setBorderPainted(false);
				button.setContentAreaFilled(false);
				button.setFocusable(false);
				shape.add(button);
				button.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int count = 0;
						for(JButton button: buttons) {
							ImageIcon imageBasic;
							
							imageBasic = new ImageIcon(v.elementAt(count));
							button.setIcon(imageBasic);
							count++;
						}
						ImageIcon imagePressed;
						imagePressed = new ImageIcon(eToolBar.getPImage());
						button.setIcon(imagePressed);
						a = eToolBar.getText();
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						if (eToolBar.getText() != a) {
							ImageIcon imagePressed;
							imagePressed = new ImageIcon(eToolBar.getPImage());
							button.setIcon(imagePressed);
							button.setCursor(new Cursor(Cursor.HAND_CURSOR));
						}
					}

					@Override
					public void mouseExited(MouseEvent e) {
						if (eToolBar.getText() != a) {
							ImageIcon imageBasic;
							imageBasic = new ImageIcon(eToolBar.getImage());
							button.setIcon(imageBasic);
							button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
					}
				});
//				shape.add(button);
//			}
			count++;
		}
//		JPanel shape1 = null;
//		JPanel shape2;
//		for (JButton button : buttons) {
//			if(button.getActionCommand() == "Pen") {
//				shape1 = new JPanel();
//				shape1.add(button);
//				
//			} else if(button.getActionCommand() == "Select") {
//				shape2 = new JPanel();
//				shape2.add(button);
//			} else {
//				shape.add(button);
//			}
//		}
//		this.add(shape1);
		this.add(shape);
		
//	

		this.add(shape);
//		this.add(all);
		JPanel bin;
		bin= new JPanel();
		bin.setBackground(Color.WHITE);
		this.add(bin);
		
		bin= new JPanel();
//		bin.setPreferredSize(new Dimension(30,60));
		bin.setBackground(Color.WHITE);
		this.add(bin);
//		bin= new JPanel();
//		this.add(bin);
	}

	public void initialize() {
//		EShapeTool.Select.getShape();
		drawingPanel.setCurrentTool(EShapeTool.Select.getShape()); //미리 네모 클릭되어 있게 함
		}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			drawingPanel.setCurrentTool(EShapeTool.valueOf(event.getActionCommand()));
//			initialize();
		}

	}
}
