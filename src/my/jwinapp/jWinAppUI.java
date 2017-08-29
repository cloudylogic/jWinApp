/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.jwinapp;
import clsrestapi.AboutUs;
import clsrestapi.ContactInfo;
import javax.swing.table.DefaultTableModel;
import java.awt.Desktop;
import java.net.URL;


/**
 *
 * @author Ken Lowrie
 */
public class jWinAppUI extends javax.swing.JFrame {

    private AboutUs aboutus;
    private ContactInfo contactInfo;

    private void logMsg(String msg){
        System.out.println(msg);
    }
    /**
     * Creates new form jWinAppUI
     */
    public jWinAppUI() {
        initComponents();
        loadAbout();
        loadContact();
    }
    
    private void loadAbout(){
        aboutus = new AboutUs().load();
        
        jTextAreaAbout.append(aboutus.apiObj.aboutus.replace(". ", "." + System.lineSeparator() + System.lineSeparator()));
        
    }
    
    private void loadContact(){
        contactInfo = new ContactInfo().load();
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(contactInfo.apiObj.location);
        sb.append(System.lineSeparator()).append(System.lineSeparator());
        sb.append(contactInfo.apiObj.address.getMailingAddress());
        sb.append(System.lineSeparator());
        sb.append("Email: ").append(contactInfo.apiObj.email).append(System.lineSeparator());
        sb.append("Phone: ").append(contactInfo.apiObj.phone);
        
        jTextAreaContact.append(sb.toString());        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelHome = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabelCloudyLogic = new javax.swing.JLabel();
        jLabelGitHub = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanelOurWork = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanelAboutUs = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaAbout = new javax.swing.JTextArea();
        jPanelContact = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaContact = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane4.setViewportView(jTextArea3);

        jButton4.setText("Facebook");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openFB(evt);
            }
        });

        jButton5.setText("Twitter");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openTwitter(evt);
            }
        });

        jButton6.setText("Instagram");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openInstagram(evt);
            }
        });

        jButton7.setText("Vimeo");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openVimeo(evt);
            }
        });

        jLabelCloudyLogic.setText("<html><a href=\"https://cloudylogic.com\">Cloudy Logic Studios, LLC</a>.");
        jLabelCloudyLogic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openCloudyLogic(evt);
            }
        });

        jLabelGitHub.setText("<html><a href=\"https://github.com/kenlowrie/jwinapp\">github.com/kenlowrie/jwinapp</a>");
        jLabelGitHub.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openGitHub(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setText("Java Reference App");

        javax.swing.GroupLayout jPanelHomeLayout = new javax.swing.GroupLayout(jPanelHome);
        jPanelHome.setLayout(jPanelHomeLayout);
        jPanelHomeLayout.setHorizontalGroup(
            jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHomeLayout.createSequentialGroup()
                        .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelHomeLayout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelHomeLayout.createSequentialGroup()
                        .addComponent(jLabelCloudyLogic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(27, 27, 27)
                        .addComponent(jLabelGitHub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelHomeLayout.setVerticalGroup(
            jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCloudyLogic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelGitHub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton7.getAccessibleContext().setAccessibleName("Vimeo");

        jTabbedPane1.addTab("Home", jPanelHome);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton2.setText("jButton2");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelOurWorkLayout = new javax.swing.GroupLayout(jPanelOurWork);
        jPanelOurWork.setLayout(jPanelOurWorkLayout);
        jPanelOurWorkLayout.setHorizontalGroup(
            jPanelOurWorkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOurWorkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelOurWorkLayout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelOurWorkLayout.setVerticalGroup(
            jPanelOurWorkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOurWorkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jButton2)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Our Work", jPanelOurWork);

        jPanelAboutUs.setBorder(javax.swing.BorderFactory.createTitledBorder("AboutUs"));

        jTextAreaAbout.setColumns(20);
        jTextAreaAbout.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jTextAreaAbout.setLineWrap(true);
        jTextAreaAbout.setRows(5);
        jTextAreaAbout.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextAreaAbout);

        javax.swing.GroupLayout jPanelAboutUsLayout = new javax.swing.GroupLayout(jPanelAboutUs);
        jPanelAboutUs.setLayout(jPanelAboutUsLayout);
        jPanelAboutUsLayout.setHorizontalGroup(
            jPanelAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAboutUsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelAboutUsLayout.setVerticalGroup(
            jPanelAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAboutUsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("About Us", jPanelAboutUs);
        jPanelAboutUs.getAccessibleContext().setAccessibleName("");

        jTextAreaContact.setColumns(20);
        jTextAreaContact.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jTextAreaContact.setRows(5);
        jScrollPane3.setViewportView(jTextAreaContact);

        jLabel2.setText("Put a Google map in this area, when you click it launch a big version");

        javax.swing.GroupLayout jPanelContactLayout = new javax.swing.GroupLayout(jPanelContact);
        jPanelContact.setLayout(jPanelContactLayout);
        jPanelContactLayout.setHorizontalGroup(
            jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContactLayout.createSequentialGroup()
                .addGroup(jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelContactLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE))
                    .addGroup(jPanelContactLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelContactLayout.setVerticalGroup(
            jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContactLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Contact", jPanelContact);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        Object rowData1[] = { "Row1-Column1", "Row1-Column2" };
        Object rowData2[] = { "Row2-Column1", "Row2-Column2" };

        Object columnNames[] = { "Column One", "Column Two" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        model.addRow(rowData1);
        model.addRow(rowData2);
        jTable1.setModel(model);
    }//GEN-LAST:event_jButton2MouseClicked

    private void openURL(String url){
        try {
           Desktop.getDesktop().browse(new URL(url).toURI());            
        } catch( Exception E){
            // TODO: Log an error to a log of some sort or do a popup...
        }
        
    }
    
    private void browseSocialNetwork(String network){
        if( contactInfo.dbgObj.parseOK){
            String snUrl = contactInfo.apiObj.getSocialNetworkUrl(network);
                        
            if( snUrl != null ){
                openURL(snUrl);
            } 
        }       
    }
    
    private void openFB(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openFB
        // TODO add your handling code here:
        browseSocialNetwork("Facebook");
    }//GEN-LAST:event_openFB

    private void openTwitter(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openTwitter
        // TODO add your handling code here:
        browseSocialNetwork("Twitter");        
    }//GEN-LAST:event_openTwitter

    private void openInstagram(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openInstagram
        // TODO add your handling code here:
        browseSocialNetwork("Instagram");        
    }//GEN-LAST:event_openInstagram

    private void openVimeo(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openVimeo
        // TODO add your handling code here:
        browseSocialNetwork("Vimeo");        
    }//GEN-LAST:event_openVimeo

    private void openCloudyLogic(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openCloudyLogic
        // TODO add your handling code here:
        openURL("https://cloudylogic.com");
    }//GEN-LAST:event_openCloudyLogic

    private void openGitHub(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openGitHub
        // TODO add your handling code here:
        openURL("https://github.com/kenlowrie/jwinapp");
    }//GEN-LAST:event_openGitHub

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(jWinAppUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jWinAppUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jWinAppUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jWinAppUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jWinAppUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelCloudyLogic;
    private javax.swing.JLabel jLabelGitHub;
    private javax.swing.JPanel jPanelAboutUs;
    private javax.swing.JPanel jPanelContact;
    private javax.swing.JPanel jPanelHome;
    private javax.swing.JPanel jPanelOurWork;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextAreaAbout;
    private javax.swing.JTextArea jTextAreaContact;
    // End of variables declaration//GEN-END:variables
}
