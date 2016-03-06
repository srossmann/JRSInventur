/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jrsinventur;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.ws.util.ByteArrayBuffer;
import gnu.io.PortInUseException;
import gnu.io.RXTXPort;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import ru.sir.ymodem.YModem;

/**
 *
 * @author rossmann
 */
public class MainForm extends javax.swing.JFrame {

    DefaultListModel listenModell = new DefaultListModel();

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane2 = new javax.swing.JSplitPane();
        HauptPanel = new javax.swing.JPanel();
        ButtonPanel = new javax.swing.JPanel();
        btnMDE = new javax.swing.JButton();
        FehlerPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        FehlerListe = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane2.setDividerLocation(500);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        btnMDE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/DT-970_IMG.png"))); // NOI18N
        btnMDE.setText("jButton1");
        btnMDE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMDEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ButtonPanelLayout = new javax.swing.GroupLayout(ButtonPanel);
        ButtonPanel.setLayout(ButtonPanelLayout);
        ButtonPanelLayout.setHorizontalGroup(
            ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMDE)
                .addContainerGap(701, Short.MAX_VALUE))
        );
        ButtonPanelLayout.setVerticalGroup(
            ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMDE, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout HauptPanelLayout = new javax.swing.GroupLayout(HauptPanel);
        HauptPanel.setLayout(HauptPanelLayout);
        HauptPanelLayout.setHorizontalGroup(
            HauptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        HauptPanelLayout.setVerticalGroup(
            HauptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HauptPanelLayout.createSequentialGroup()
                .addComponent(ButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 399, Short.MAX_VALUE))
        );

        jSplitPane2.setTopComponent(HauptPanel);

        FehlerListe.setName("FehlerListe"); // NOI18N
        jScrollPane1.setViewportView(FehlerListe);

        javax.swing.GroupLayout FehlerPanelLayout = new javax.swing.GroupLayout(FehlerPanel);
        FehlerPanel.setLayout(FehlerPanelLayout);
        FehlerPanelLayout.setHorizontalGroup(
            FehlerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
        );
        FehlerPanelLayout.setVerticalGroup(
            FehlerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jSplitPane2.setRightComponent(FehlerPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2)
        );

        setSize(new java.awt.Dimension(862, 630));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMDEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMDEActionPerformed
        try {
            LogInfo("Hallo");
            
            MDECommunication MDE = new MDECommunication();
            MDE.ListCommPort();
            
            RXTXPort ser = new RXTXPort("");
            
            InputStream input = ser.getInputStream();
            OutputStream output = ser.getOutputStream();
            //OutputStream out = serialPort.getOutputStream();

            YModem m = new YModem(input, output);
            Path file = Paths.get("/tmp/foo");
            
            m.send(file);
        } catch (PortInUseException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnMDEActionPerformed

    public void LogInfo(String Info) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String uhrzeit = sdf.format(new Date());
        listenModell.addElement(uhrzeit + " " + Info);

        FehlerListe.setModel(listenModell);

    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ButtonPanel;
    private javax.swing.JList FehlerListe;
    private javax.swing.JPanel FehlerPanel;
    private javax.swing.JPanel HauptPanel;
    private javax.swing.JButton btnMDE;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane2;
    // End of variables declaration//GEN-END:variables
}