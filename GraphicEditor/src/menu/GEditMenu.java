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
		// 폴리모피즘, 리플렉션. 클래스 그대로, 컨스탄트에서 값을 클래스에 밀어놓고있음. 클래스 만들어놓고 어느부분 비워놓고 컨스탄트만 조정하면
		// 프로그램 돌아감. 이걸 나중에는 xml으로 짤 수 있음. 요즘은 xml을 읽어와서 코드 만들도록 함.
		//
		for (EEditMenu eMenuItem : EEditMenu.values()) {
			if(eMenuItem.getText().equals("분리")) {
				this.addSeparator();
			}else {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			menuItem.setActionCommand(eMenuItem.getMethod());
				switch (eMenuItem.getText()) {
				case "되돌리기":
					menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
					break;
				case "다시실행":
					menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
					break;
				case "잘라내기":
					menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
					break;
				case "복사":
					menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
					break;
				case "붙여넣기":
					menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
					break;
				case "그룹":
					menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
					break;
				case "그룹해제":
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
		// 벡터 클립보드에 선택된 쉐잎을 저장해. 선택된쉐잎은 드로잉패널에 있다.
		this.drawingPanel.cut(); // 함수호출만
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
		// 액션 커맨드 안에 넣지 마. 액션 커맨드 포함 컨테이너에 넣자
		@Override
		public void actionPerformed(ActionEvent e) {
			invokeMethod(e.getActionCommand());
		}
	}

}
