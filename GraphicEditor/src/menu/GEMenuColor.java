package menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import drawingPanel.GDrawingPanel;
import global.GConstants.EColorMenu;


public class GEMenuColor extends JMenu{
   private static final long serialVersionUID = 1L;
   
   private GDrawingPanel drawingPanel;
   private ColorMenuHandler colorMenuHandler;
   
   public GEMenuColor(String text) {
      super(text);
      colorMenuHandler = new ColorMenuHandler();
      for (EColorMenu items: EColorMenu.values()) {
         JMenuItem menuItem = new JMenuItem(items.name());
         menuItem.addActionListener(colorMenuHandler);
         menuItem.setActionCommand(items.name());
         this.add(menuItem); 
         }
      
   }

   public void associate(GDrawingPanel drawingPanel) {

      this.drawingPanel = drawingPanel;
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

   private void delLineColor() {
      drawingPanel.setLineColor(Color.BLACK);
   }

   private void delFillColor() {
      drawingPanel.setFillColor(Color.WHITE);
   }
   
   private class ColorMenuHandler implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         switch (EColorMenu.valueOf(e.getActionCommand())) {
         case SetLineColor:
            setLineColor();
            break;
         case SetFillColor:
            setFillColor();
            break;
         case ClearLineColor:
            delLineColor();
            break;
         case ClearFillColor:
            delFillColor();
            break;
         }
      }
   }


}