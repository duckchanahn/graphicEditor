package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.ToolTipManager;

import drawingPanel.GDrawingPanel;
import global.GConstants.EEditMenu;

public class GEditMenu extends JMenu {
	private GDrawingPanel drawingPanel;
	private ToolTipManager m = ToolTipManager.sharedInstance();

	public GEditMenu(String text) {
		super(text);

		ActionHandler actionHandler = new ActionHandler();
		// ����������, ���÷���. Ŭ���� �״��, ����źƮ���� ���� Ŭ������ �о��������. Ŭ���� �������� ����κ� ������� ����źƮ�� �����ϸ�
		// ���α׷� ���ư�. �̰� ���߿��� xml���� © �� ����. ������ xml�� �о�ͼ� �ڵ� ���鵵�� ��.
		//
		for (EEditMenu eMenuItem : EEditMenu.values()) {
			if(eMenuItem.getText().equals("�и�")) {
				this.addSeparator();
			}else {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			menuItem.setActionCommand(eMenuItem.getMethod());
				switch (eMenuItem.getText()) {
				case "�ǵ�����":
					menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
					break;
				case "�ٽý���":
					menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
					break;
				case "�߶󳻱�":
					menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
					break;
				case "����":
					menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
					break;
				case "�ٿ��ֱ�":
					menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
					break;
				case "�׷�":
					menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
					break;
				case "�׷�����":
					menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.SHIFT_MASK));
					break;
				}
			menuItem.setToolTipText(eMenuItem.getText());
			menuItem.addActionListener(actionHandler);
			this.add(menuItem);
			}
			m.setDismissDelay(1000);
		}
	}
	
	public void seperate() {
		this.addSeparator();	
		}
	
	public void setDrawingPanel(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}

	public void associate(GDrawingPanel drawingPanel) {
		this.setDrawingPanel(drawingPanel);

	}

	public void initialize() {
		// TODO Auto-generated method stub

	}

	public GDrawingPanel getDrawingPanel() {
		return drawingPanel;
	}

	private void invokeMethod(String name) {
		try {
			this.getClass().getMethod(name).invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}

	}

	public void undo() {
		this.drawingPanel.undo();
	}

	public void redo() {
		this.drawingPanel.redo();
	}

	public void cut() {
		// ���� Ŭ�����忡 ���õ� ������ ������. ���õȽ����� ������гο� �ִ�.
		this.drawingPanel.cut(); // �Լ�ȣ�⸸
	}

	public void copy() {
		this.drawingPanel.copy();
	}

	public void paste() {
		this.drawingPanel.paste();
	}

	public void group() {
		this.drawingPanel.group();
	}

	public void ungroup() {
		this.drawingPanel.ungroup();
	}

	private class ActionHandler implements ActionListener {
		// �׼� Ŀ�ǵ� �ȿ� ���� ��. �׼� Ŀ�ǵ� ���� �����̳ʿ� ����
		@Override
		public void actionPerformed(ActionEvent e) {
			invokeMethod(e.getActionCommand());
		}
	}

}
