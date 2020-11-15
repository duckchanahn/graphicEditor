package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ToolTipManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import drawingPanel.GDrawingPanel;
import global.GConstants.EFileMenu;


public class GFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	private File directory, file;
	private ToolTipManager m = ToolTipManager.sharedInstance();
	
	private GDrawingPanel drawingPanel;
	public void associate(GDrawingPanel drawingPanel) { this.drawingPanel = drawingPanel; }

	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	public GFileMenu(String text) {
		super(text);
		
		this.file = null;
		this.directory = new File("data");
		
		ActionHandler actionHandler = new ActionHandler();
		
		for(EFileMenu emenuItem : EFileMenu.values()) {
		if(emenuItem.getText().equals("�и�")) {
			this.addSeparator();
		}else {
			JMenuItem menuItem = new JMenuItem(emenuItem.getText());
			menuItem.setActionCommand(emenuItem.getMethod());
			switch (emenuItem.getText()) {
			case "���θ����":
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
				break;
			case "Save":
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
				break;
			case "�μ�":
				menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
				break;
				
			}
			
			menuItem.setActionCommand(emenuItem.getMethod());
			menuItem.addActionListener(actionHandler);
			this.add(menuItem);
		}
		}
		m.setDismissDelay(1000);
//		this.file = new File("null");
//		this.directory = new File("data");
	}
	
	public void seperate() {
	this.addSeparator();	
	}

	
	public void nnew() {
		if(this.drawingPanel.isupdated()) {
		int result = JOptionPane.showConfirmDialog(this.drawingPanel, "�����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.CLOSED_OPTION) {
		}else if(result == JOptionPane.YES_OPTION) {
			this.save();
			this.drawingPanel.setupdated(false);
		}
		}
		
		this.clear();
	}
	
	public void clear() {
		this.drawingPanel.restoreShapeVector(null);
		this.drawingPanel.clearcurrent();
		this.drawingPanel.setupdated(true);
	}
	
	public void open() { 
		if(this.drawingPanel.isupdated()) {
		int result = JOptionPane.showConfirmDialog(this.drawingPanel, "�����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.CLOSED_OPTION) {
		}else if(result == JOptionPane.YES_OPTION) {
			this.save();
		}
		this.drawingPanel.setupdated(false);
		this.clear();
		}
		JFileChooser chooser = new JFileChooser(this.directory);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics data", "god");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this.drawingPanel);	
		if(returnVal == JFileChooser.APPROVE_OPTION) {	
			this.directory = chooser.getCurrentDirectory();
			this.file = chooser.getSelectedFile();
			this.readObject();		
		}
	}

	
	private void readObject() {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(file)));

			Object object = objectInputStream.readObject(); // Ÿ���� �𸣴ϱ� �ϴ� ������Ʈ�� �ް� Ÿ�� ĳ���� ����� ��.
			this.drawingPanel.restoreShapeVector(object);
			objectInputStream.close();
			this.drawingPanel.setupdated(false);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writeObject() {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream( // ������Ʈ�� �Ϸķ� �����ִ� ����
					new BufferedOutputStream( // ������ ���� ����.
							new FileOutputStream(file))); // ���� Ȥ�� ��Ʈ��ũ
			objectOutputStream.writeObject(this.drawingPanel.getShapeVector());// Ȯ������
			objectOutputStream.close();
			this.drawingPanel.setupdated(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		if (this.drawingPanel.isupdated()) { // ���� ������ ���� �����ض�.
			if (file == null) {
				this.saveAs();// ���ϼ���
			} else {
				this.writeObject();	
			}
		}
	}
	
	public void saveAs() {
		JFileChooser chooser = new JFileChooser(this.directory);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics data", "god");	//~.exe ó�� �ͽ��ټ��� ������ �� ����. .god�� ������. ���͸���.
		//xml���ε� ���� �ؼ� ���� �� �ְ� ��.
		//����Ʈ �� �� �ִ°͵� �����.
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(this.drawingPanel);	//���̾�α��� �θ� ������? �ߴ� ��ġ ��������. null���� ���� ȭ�� �߾ӿ� ��
		if(returnVal == JFileChooser.APPROVE_OPTION) {	//ok ������ ��.
			this.directory = chooser.getCurrentDirectory();
			this.file = chooser.getSelectedFile();
			this.writeObject();
			this.drawingPanel.setupdated(false);
		}
	}
	
	public void close() {
		//���� ���Ŀ� ��ģ���� �ִ��� ������ Ȯ��
		this.save();
		System.exit(0);
	}
	
	public void print() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        PageFormat pageFormat = new PageFormat();
        printerJob.setPrintable(drawingPanel, pageFormat);
        boolean ok = printerJob.printDialog(printRequestAttributeSet);
        if (ok) {
            try {
            	printerJob.print(printRequestAttributeSet);
            } catch (PrinterException ex) {
             /* The job did not successfully complete */
            }
        }
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			invokeMethod(event.getActionCommand());
		}

	}

	public void invokeMethod(String name) {
		
			try {
				this.getClass().getMethod(name).invoke(this);
			} catch (IllegalAccessException  | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}


}
