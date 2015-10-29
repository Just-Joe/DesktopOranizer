/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package desktoporg;


import java.awt.Component;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileSystemView;


/**
 *
 * @author jo27710
 */
public class OrgPanel extends javax.swing.JFrame {

    int IconSize= 50;
    int IconCount = 0;
    
    /**
     * Creates new form OrgPanel
     */
    public OrgPanel() {
        initComponents();
        this.setSize(this.getWidth()+1, this.getHeight()+1);
        this.validate();
        IconPanel.addMouseListener(new PopClickListener());
        initFileDrop();
    }
    
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
}
    
    private void initFileDrop(){
        IconPanel.setDropTarget(new DropTarget(){
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>)
                        evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {
                        // process files
                        ImageIcon ogico = (ImageIcon)FileSystemView.getFileSystemView().getSystemIcon(file);
                        Image newImage = getScaledImage(ogico.getImage(),IconSize,IconSize);
                        ImageIcon ico = new ImageIcon(newImage);
                        
                        IconCount++;
                        JLabel newIcon = new JLabel("");
                        newIcon.setName(file.getPath());
                        newIcon.setIcon(ico);
                       
                        newIcon.addMouseListener(new MouseAdapter(){
                            public void mouseClicked(MouseEvent e)  
                            {   
                                String path =e.getComponent().getName();
                                try{
                                    Desktop.getDesktop().open(new File(path));
                                }
                                catch(IOException io){
                                    
                                }
                            }  
                        }); 
                        newIcon.setSize(IconSize, IconSize);
                        IconPanel.add(newIcon);
                        IconPanel.repaint();
                        IconPanel.validate();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } 
        });
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Scroll = new javax.swing.JScrollPane();
        IconPanel = new javax.swing.JPanel();
        NorthResize = new javax.swing.JButton();
        WestResize = new javax.swing.JButton();
        EastResize = new javax.swing.JButton();
        SouthResize = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        IconPanel.setPreferredSize(new java.awt.Dimension(200, 282));
        IconPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                IconPanelMouseDragged(evt);
            }
        });
        IconPanel.setLayout(new java.awt.GridLayout(6, 2, 1, 1));
        Scroll.setViewportView(IconPanel);

        NorthResize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                NorthResizeMouseReleased(evt);
            }
        });

        WestResize.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                WestResizeMouseDragged(evt);
            }
        });

        EastResize.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                EastResizeMouseDragged(evt);
            }
        });

        SouthResize.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                SouthResizeMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(WestResize, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SouthResize, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NorthResize, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(EastResize, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(NorthResize, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EastResize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(WestResize, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)))
                .addGap(0, 0, 0)
                .addComponent(SouthResize, javax.swing.GroupLayout.PREFERRED_SIZE, 3, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IconPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IconPanelMouseDragged
        // TODO add your handling code here:
        Point newP = evt.getPoint();
        this.setLocation(this.getX()+(int)newP.getX(), this.getY()+(int)newP.getY());
        
    }//GEN-LAST:event_IconPanelMouseDragged

    private void WestResizeMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WestResizeMouseDragged
        // TODO add your handling code here:
        //System.out.println("West Dragged to: " + evt.getX() + "," + evt.getY());
        this.setLocation(this.getX()+evt.getX(), this.getY());
        this.setSize(this.getWidth()-evt.getX(), this.getHeight());
    }//GEN-LAST:event_WestResizeMouseDragged

    private void EastResizeMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EastResizeMouseDragged
        // TODO add your handling code here:
        //System.out.println("East Dragged to: " + evt.getX() + "," + evt.getY());
        this.setSize(this.getWidth()+evt.getX(), this.getHeight());
    }//GEN-LAST:event_EastResizeMouseDragged

    private void SouthResizeMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SouthResizeMouseDragged
        // TODO add your handling code here:
        //System.out.println("South Dragged to: " + evt.getX() + "," + evt.getY());
        this.setSize(this.getWidth(), this.getHeight()+evt.getY());
    }//GEN-LAST:event_SouthResizeMouseDragged

    private void NorthResizeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NorthResizeMouseReleased
        // TODO add your handling code here:
        //System.out.println("North Dragged to: " + evt.getX() + "," + evt.getY());
        this.setLocation(this.getX(), this.getY()+evt.getY());
        this.setSize(this.getWidth(), this.getHeight()-evt.getY());
    }//GEN-LAST:event_NorthResizeMouseReleased

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
        //North
        Scroll.setLocation(3,3);
        Scroll.setSize(this.getWidth()-6,this.getHeight()-6);
        
        if(IconPanel.getHeight()<Scroll.getHeight()){
            IconPanel.setSize(IconPanel.getWidth(), Scroll.getHeight());
        }
        if(IconPanel.getWidth()<Scroll.getWidth()){
            IconPanel.setSize(Scroll.getWidth(), IconPanel.getHeight());
        }
        
        NorthResize.setLocation(3, 0);
        NorthResize.setSize(Scroll.getWidth(), 3);
        
        EastResize.setLocation(Scroll.getWidth()+3,3);
        EastResize.setSize(3,Scroll.getHeight());
        
        SouthResize.setLocation(3,Scroll.getHeight()+3);
        SouthResize.setSize(Scroll.getWidth(),3);       
                
        WestResize.setLocation(0,3);
        WestResize.setSize(3,Scroll.getHeight());
        
        GridLayout IconGrid= (GridLayout)IconPanel.getLayout();
        //Set to verticle
        if(IconPanel.getWidth()>IconPanel.getHeight()){
            int cols = IconPanel.getWidth()/IconSize;
            int rows = (IconCount/IconGrid.getColumns())+1;
            IconGrid.setColumns(cols);
            IconGrid.setRows(rows);
            
            System.out.println("Collums : " + cols + " Rows: " + rows + " for "+ IconCount + " icons.");
        }
        //Set to Horizontal
        else{
            int rows = IconPanel.getHeight()/IconSize;
            int cols = (IconCount/IconGrid.getRows())+1;
            IconGrid.setRows(rows);
            IconGrid.setColumns(cols);
            
            System.out.println("Collums : " + cols + " Rows: " + rows + " for "+ IconCount + " icons.");
        }
        
    }//GEN-LAST:event_formComponentResized


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrgPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrgPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrgPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrgPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrgPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EastResize;
    private javax.swing.JPanel IconPanel;
    private javax.swing.JButton NorthResize;
    private javax.swing.JScrollPane Scroll;
    private javax.swing.JButton SouthResize;
    private javax.swing.JButton WestResize;
    // End of variables declaration//GEN-END:variables
}


class RightClickMenu extends JPopupMenu {
    JMenuItem ExitItem;
    public RightClickMenu(){
        ExitItem = new JMenuItem("Exit");
        ExitItem.addActionListener(new ExitActionListener());
        add(ExitItem);
    }
}

class PopClickListener extends MouseAdapter {
    public void mousePressed(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e){
        RightClickMenu menu = new RightClickMenu();
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}

class ExitActionListener implements ActionListener {
  public void actionPerformed(ActionEvent e) {
    System.out.println("About to exit!");
    System.exit(0);
  }
}
