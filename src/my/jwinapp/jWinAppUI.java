/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.jwinapp;
import clsrestapi.AboutUs;
import clsrestapi.CRAException;
import clsrestapi.ContactInfo;
import clsrestapi.OurWork;
import clsrestapi.ShowCaseVideo;
import clsrestapi.ClsRestApi;
import clsrestapi.Reels;
import clsrestapi.SocialNetwork;
import clsrestapi.Video;
import javax.swing.JTextField;
import javax.swing.DefaultCellEditor;
import javax.swing.table.DefaultTableModel;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


/**
 *
 * @author Ken Lowrie
 */
public class jWinAppUI extends javax.swing.JFrame {

    private ClsRestApi cra;
    private AboutUs aboutUs;
    private ContactInfo contactInfo;
    private Reels reels;
    private OurWork ourWork;

    private void logMsg(String msg){
        System.out.println(msg);
    }
    /**
     * Creates new form jWinAppUI
     */
    public jWinAppUI() {
        try {
            cra = new ClsRestApi("./cache", "JavaDesktop", "http://localhost:8000");
        } catch (IOException | CRAException ex) {
            Logger.getLogger(jWinAppUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        // I'm designing the video details panel in NetBeans, but I don't want it shown
        // as a Tab in the GUI, so remove it! I could just go steal the code after it's designed
        // and put it here or in a method? Hmmm. TODO. That would probably be better, since I need
        // to construct these on the fly as they click on a video in the table.
        jTabbedPaneMyApp.remove(jPanelVideoDetails);
        loadAbout();
        loadContact();
        loadOurWork();
    }
    
    private void loadAbout(){
        aboutUs = cra.getAboutUs();
        reels = cra.getReels();
        contactInfo = cra.getContactInfo();

        Video latestReel = reels.apiObj.getReel(0); //TODO: Check for error
        System.out.println("reel.frame: "+latestReel.frame);
        
        String drFrame = cra.getReelsResource(latestReel.frame);
        
        System.out.println("frame: "+drFrame);
        
        if (null == drFrame){
            //TODO: Figure out how to get path to execution root (test when running java -jar in different locations...
            drFrame = "src/my/jwinapp/frame-default.png"; // "/my/jwinapp/"+video.thumb+".png"            
        }
        //Icky poo poo
        //ImageIcon imageIcon = new ImageIcon(new ImageIcon(drFrame).getImage().getScaledInstance(560,315, Image.SCALE_DEFAULT));
        //jLabelVideoPlayer.setIcon(imageIcon);
        jLabelVideoPlayer.setIcon(new javax.swing.ImageIcon(drFrame));
        
        for(SocialNetwork sn: contactInfo.apiObj.socialNetworks){
            String resName = cra.getContactInfoResource(sn.image);
            if (resName.isEmpty()){
                //TODO: Figure out how to get path to execution root (test when running java -jar in different locations...
                resName = "src/my/jwinapp/social-default.png"; // "/my/jwinapp/"+video.thumb+".png"
            } else {
            }
            
            switch (sn.network.toLowerCase()){
                case "vimeo":
                    jLabelVimeo.setIcon(new javax.swing.ImageIcon(resName));
                    break;
                case "facebook":
                    jLabelFB.setIcon(new javax.swing.ImageIcon(resName));
                    break;
                case "twitter":
                    jLabelTwitter.setIcon(new javax.swing.ImageIcon(resName));
                    break;
                case "instagram":
                    jLabelInstagram.setIcon(new javax.swing.ImageIcon(resName));
                    break;
                default:
                    System.out.println("unknown network: "+sn.network);
                    break;
            }
            
        }
        jTextAreaAboutUs.append(aboutUs.apiObj.aboutus.replace(". ", "." + System.lineSeparator() + System.lineSeparator()));
        
    }
    
    private void loadContact(){
        contactInfo = cra.getContactInfo();
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(contactInfo.apiObj.location);
        sb.append(System.lineSeparator()).append(System.lineSeparator());
        sb.append(contactInfo.apiObj.address.getMailingAddress());
        sb.append(System.lineSeparator());
        sb.append("Email: ").append(contactInfo.apiObj.email).append(System.lineSeparator());
        sb.append("Phone: ").append(contactInfo.apiObj.phone);
        
        jTextPaneContact.setText(sb.toString());
        StyledDocument doc = jTextPaneContact.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

    }

    private void loadOurWork(){
        ourWork = cra.getOurWork();
        
        Object columnNames[] = { "Thumbnail", "Description" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0){
            // Return the class of the column so the correct renderer is used
            @Override
            public Class getColumnClass(int column){
                return getValueAt(0,column).getClass();
            }
            // This prevents any editing of the contents
            boolean[] canEdit = new boolean [] {
                //Col 0,  Col 1
                false,    true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };

        for (ShowCaseVideo video: ourWork.apiObj.videoList){
            String resName = cra.getOurWorkResource(video.thumb);
            if (resName.isEmpty()){
                //TODO: Figure out how to get path to execution root (test when running java -jar in different locations...
                resName = "src/my/jwinapp/img-default.png"; // "/my/jwinapp/"+video.thumb+".png"
            } else {
            }
            Object icon = new javax.swing.ImageIcon(resName);
            Object rowData[] = {icon, "<html><strong>" + video.type + "</strong><br>" + video.title};
            
            model.addRow(rowData);
        }
        JTextField tf = new JTextField();
        tf.setEditable(false);
        DefaultCellEditor editor = new DefaultCellEditor(tf);
        jTableOurWork.setDefaultEditor(Object.class, editor);
        
        jTableOurWork.setModel(model);
        jTableOurWork.getTableHeader().setUI(null);    // Disable the column headings
        // Set the preferred width for the two columns
        jTableOurWork.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTableOurWork.getColumnModel().getColumn(1).setPreferredWidth(400);
        // Set the height for the rows (so the image fits in nicely, they are 100px)
        jTableOurWork.setRowHeight(110);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneMyApp = new javax.swing.JTabbedPane();
        jPanelHome = new javax.swing.JPanel();
        jLabelFB = new javax.swing.JLabel();
        jLabelTwitter = new javax.swing.JLabel();
        jLabelInstagram = new javax.swing.JLabel();
        jLabelVimeo = new javax.swing.JLabel();
        jLabelVideoPlayer = new javax.swing.JLabel();
        jPanelOurWork = new javax.swing.JPanel();
        jScrollPaneOurWork = new javax.swing.JScrollPane();
        jTableOurWork = new javax.swing.JTable();
        jPanelAboutUs = new javax.swing.JPanel();
        jScrollPaneAboutUs = new javax.swing.JScrollPane();
        jTextAreaAboutUs = new javax.swing.JTextArea();
        jPanelContact = new javax.swing.JPanel();
        jLabelGoogleMap = new javax.swing.JLabel();
        jScrollPaneContact = new javax.swing.JScrollPane();
        jTextPaneContact = new javax.swing.JTextPane();
        jPanelVideoDetails = new javax.swing.JPanel();
        jLabelVideoDetailsPlayer = new javax.swing.JLabel();
        jScrollPaneVideoDetails = new javax.swing.JScrollPane();
        jTextAreaVideoDetails = new javax.swing.JTextArea();
        jLabelCloudyLogic = new javax.swing.JLabel();
        jLabelRefApp = new javax.swing.JLabel();
        jLabelGitHub = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(584, 560));
        setMinimumSize(new java.awt.Dimension(584, 560));

        jLabelFB.setText("Facebook");
        jLabelFB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelFB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openFB(evt);
            }
        });

        jLabelTwitter.setText("Twitter");
        jLabelTwitter.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelTwitter.setMaximumSize(new java.awt.Dimension(128, 162));
        jLabelTwitter.setMinimumSize(new java.awt.Dimension(128, 162));
        jLabelTwitter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openTwitter(evt);
            }
        });

        jLabelInstagram.setText("Instagram");
        jLabelInstagram.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelInstagram.setMaximumSize(new java.awt.Dimension(128, 162));
        jLabelInstagram.setMinimumSize(new java.awt.Dimension(128, 162));
        jLabelInstagram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openInstagram(evt);
            }
        });

        jLabelVimeo.setText("Vimeo");
        jLabelVimeo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelVimeo.setMaximumSize(new java.awt.Dimension(110, 162));
        jLabelVimeo.setMinimumSize(new java.awt.Dimension(110, 162));
        jLabelVimeo.setPreferredSize(new java.awt.Dimension(128, 169));
        jLabelVimeo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openVimeo(evt);
            }
        });

        javax.swing.GroupLayout jPanelHomeLayout = new javax.swing.GroupLayout(jPanelHome);
        jPanelHome.setLayout(jPanelHomeLayout);
        jPanelHomeLayout.setHorizontalGroup(
            jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelVideoPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelHomeLayout.createSequentialGroup()
                        .addComponent(jLabelFB, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelVimeo, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelHomeLayout.createSequentialGroup()
                        .addComponent(jLabelTwitter, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelInstagram, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanelHomeLayout.setVerticalGroup(
            jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelVideoPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFB, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelVimeo, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTwitter, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelInstagram, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPaneMyApp.addTab("Home", jPanelHome);

        jTableOurWork.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jTableOurWork.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableOurWork.getTableHeader().setReorderingAllowed(false);
        jTableOurWork.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableOurWorkMouseClicked(evt);
            }
        });
        jScrollPaneOurWork.setViewportView(jTableOurWork);
        if (jTableOurWork.getColumnModel().getColumnCount() > 0) {
            jTableOurWork.getColumnModel().getColumn(0).setResizable(false);
            jTableOurWork.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanelOurWorkLayout = new javax.swing.GroupLayout(jPanelOurWork);
        jPanelOurWork.setLayout(jPanelOurWorkLayout);
        jPanelOurWorkLayout.setHorizontalGroup(
            jPanelOurWorkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOurWorkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneOurWork, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelOurWorkLayout.setVerticalGroup(
            jPanelOurWorkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOurWorkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneOurWork, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneMyApp.addTab("Our Work", jPanelOurWork);

        jPanelAboutUs.setBorder(javax.swing.BorderFactory.createTitledBorder("AboutUs"));

        jTextAreaAboutUs.setColumns(20);
        jTextAreaAboutUs.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jTextAreaAboutUs.setLineWrap(true);
        jTextAreaAboutUs.setRows(5);
        jTextAreaAboutUs.setWrapStyleWord(true);
        jScrollPaneAboutUs.setViewportView(jTextAreaAboutUs);

        javax.swing.GroupLayout jPanelAboutUsLayout = new javax.swing.GroupLayout(jPanelAboutUs);
        jPanelAboutUs.setLayout(jPanelAboutUsLayout);
        jPanelAboutUsLayout.setHorizontalGroup(
            jPanelAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAboutUsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneAboutUs, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelAboutUsLayout.setVerticalGroup(
            jPanelAboutUsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAboutUsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneAboutUs, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneMyApp.addTab("About Us", jPanelAboutUs);
        jPanelAboutUs.getAccessibleContext().setAccessibleName("");

        jLabelGoogleMap.setText("Put a Google map in this area, when you click it launch a big version");

        jTextPaneContact.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPaneContact.setViewportView(jTextPaneContact);

        javax.swing.GroupLayout jPanelContactLayout = new javax.swing.GroupLayout(jPanelContact);
        jPanelContact.setLayout(jPanelContactLayout);
        jPanelContactLayout.setHorizontalGroup(
            jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContactLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneContact))
            .addGroup(jPanelContactLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabelGoogleMap, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );
        jPanelContactLayout.setVerticalGroup(
            jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContactLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabelGoogleMap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 410, Short.MAX_VALUE)
                .addComponent(jScrollPaneContact, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPaneMyApp.addTab("Contact", jPanelContact);

        jLabelVideoDetailsPlayer.setText("Hi There!");

        jTextAreaVideoDetails.setColumns(20);
        jTextAreaVideoDetails.setRows(5);
        jTextAreaVideoDetails.setText("Here is some text");
        jScrollPaneVideoDetails.setViewportView(jTextAreaVideoDetails);

        javax.swing.GroupLayout jPanelVideoDetailsLayout = new javax.swing.GroupLayout(jPanelVideoDetails);
        jPanelVideoDetails.setLayout(jPanelVideoDetailsLayout);
        jPanelVideoDetailsLayout.setHorizontalGroup(
            jPanelVideoDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVideoDetailsLayout.createSequentialGroup()
                .addGroup(jPanelVideoDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVideoDetailsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelVideoDetailsPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPaneVideoDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelVideoDetailsLayout.setVerticalGroup(
            jPanelVideoDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVideoDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelVideoDetailsPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneVideoDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneMyApp.addTab("", jPanelVideoDetails);

        jLabelCloudyLogic.setText("<html><a href=\"https://cloudylogic.com\">Cloudy Logic Studios, LLC</a>.");
        jLabelCloudyLogic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openCloudyLogic(evt);
            }
        });

        jLabelRefApp.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabelRefApp.setText("Java Reference App");

        jLabelGitHub.setText("<html><a href=\"https://github.com/kenlowrie/jwinapp\">github.com/kenlowrie/jWinApp</a>");
        jLabelGitHub.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openGitHub(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabelCloudyLogic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jLabelRefApp)
                .addGap(77, 77, 77)
                .addComponent(jLabelGitHub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneMyApp)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneMyApp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCloudyLogic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRefApp)
                    .addComponent(jLabelGitHub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPaneMyApp.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        openURL("https://github.com/kenlowrie/jWinApp");
    }//GEN-LAST:event_openGitHub

    private void jTableOurWorkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableOurWorkMouseClicked
        // TODO add your handling code here:
        //jTableOurWork.updateUI();
        jScrollPaneOurWork.setViewportView(jPanelVideoDetails);
        //TODO: remove this
    }//GEN-LAST:event_jTableOurWorkMouseClicked

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
    private javax.swing.JLabel jLabelCloudyLogic;
    private javax.swing.JLabel jLabelFB;
    private javax.swing.JLabel jLabelGitHub;
    private javax.swing.JLabel jLabelGoogleMap;
    private javax.swing.JLabel jLabelInstagram;
    private javax.swing.JLabel jLabelRefApp;
    private javax.swing.JLabel jLabelTwitter;
    private javax.swing.JLabel jLabelVideoDetailsPlayer;
    private javax.swing.JLabel jLabelVideoPlayer;
    private javax.swing.JLabel jLabelVimeo;
    private javax.swing.JPanel jPanelAboutUs;
    private javax.swing.JPanel jPanelContact;
    private javax.swing.JPanel jPanelHome;
    private javax.swing.JPanel jPanelOurWork;
    private javax.swing.JPanel jPanelVideoDetails;
    private javax.swing.JScrollPane jScrollPaneAboutUs;
    private javax.swing.JScrollPane jScrollPaneContact;
    private javax.swing.JScrollPane jScrollPaneOurWork;
    private javax.swing.JScrollPane jScrollPaneVideoDetails;
    private javax.swing.JTabbedPane jTabbedPaneMyApp;
    private javax.swing.JTable jTableOurWork;
    private javax.swing.JTextArea jTextAreaAboutUs;
    private javax.swing.JTextArea jTextAreaVideoDetails;
    private javax.swing.JTextPane jTextPaneContact;
    // End of variables declaration//GEN-END:variables
}
