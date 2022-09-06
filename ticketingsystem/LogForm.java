
package ticketingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class LogForm extends javax.swing.JFrame {

        public static Connection connection;//handles dbase connection;declares static bec it will be accessed at static main()
        public static Statement statement;//handles sql statement;declares static bec it will be accessed at static main()
        public PreparedStatement ps;
        ResultSet rs;//handles resultsets or data/contents of rows in your dbase/table
        String sql;//handles sql statements
        
    public LogForm() {
        initComponents();
    }
    
    int ctr;
    
    private void validation(){
        
        if(txtUsername.getText().length()>0 && txtPassword.getText().length()> 0){
             if(txtPassword.getText().length()>8){
                   JOptionPane.showMessageDialog(this, "Password must not be more than 8 characters");
              }else{
                  try{
                        connection = DriverManager.getConnection("jdbc:mysql://localhost/project", "root","");
                        ps = connection.prepareStatement("SELECT * FROM admin WHERE username=? AND password=?");
                        ps.setString(1, txtUsername.getText());
                        ps.setString(2, String.valueOf(txtPassword.getText()));
                        rs = ps.executeQuery();

                        //testing kung tama
                        if(rs.next()){
                              JOptionPane.showMessageDialog(this, "Username and Password Matched");
                              new main_menu().setVisible(true);
                              dispose();
                        }else{
                              ctr++;
                              JOptionPane.showMessageDialog(this, "Username and Password Wrong");

                              if(ctr == 3){
                                   JOptionPane.showMessageDialog(this, "Closing...");
                                   dispose();
                              }
                        }
                    }catch(Exception e){
                         Logger.getLogger(LogForm.class.getName()).log(Level.SEVERE, null, e);
                    }
                }  
            
     }else{
          JOptionPane.showMessageDialog(this, "Please input username and password");
      }          
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        txtUsername = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUsername.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        txtUsername.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel2.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 300, 40));

        jButton1.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jButton1.setText("DONE");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 220, 30));

        jLabel1.setFont(new java.awt.Font("Gill Sans MT", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Login");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 100, 50));

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel2.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 300, 40));

        jLabel4.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        jLabel4.setText("PASSWORD");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, -1, -1));

        jLabel5.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        jLabel5.setText("USERNAME");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pass.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 60, 60));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user_1.png"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 60, 60));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blue-abstract-background-2.jpg"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 290));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        validation();
    }//GEN-LAST:event_jButton1ActionPerformed

   
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

}
