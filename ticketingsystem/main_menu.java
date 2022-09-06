package ticketingsystem;

import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.lang.Math.*;
import static java.lang.Math.random;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;   
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static ticketingsystem.LogForm.connection;




public class main_menu extends javax.swing.JFrame {
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
     Date date = new Date();  
    
    public main_menu() {
        initComponents();
        lblCustomer.setEditable(false);
        lblType.setEditable(false);
        lblFee.setEditable(false);
        lblDestination.setEditable(false);
        lblFrom.setEditable(false);
        lblChange.setEditable(false);
        lblDate.setEditable(false);
        lblPrice.setEditable(false);
    }
    
    public Connection connection;//handles dbase connection;declares static bec it will be accessed at static main()
    public Statement statement;//handles sql statement;declares static bec it will be accessed at static main()
    ResultSet rs;
    
     public void addInfo(String name, String type, String from, String destination){
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
        try{
        connection = DriverManager.getConnection("jdbc:mysql://localhost/project", "root","");
        PreparedStatement ps = connection.prepareStatement("INSERT INTO customer_table(customer_name,  customer_type,  customer_from,  customer_destination, date) VALUES(?, ?, ?, ?, ?)");
     
          ps.setString(1, name);
          ps.setString(2, type);
          ps.setString(3, from);
          ps.setString(4, destination);
          ps.setDate(5, new java.sql.Date(System.currentTimeMillis()));
         //String sql = "INSERT INTO person (name,age,address,contact) values ('" + name + "','" + age + "','" + address + "','" + contact + "')";
          
          int row = ps.executeUpdate();
          
          if(row>0){
                JOptionPane.showMessageDialog(this, "Added Record");
          }else{
                JOptionPane.showMessageDialog(this, "Failed to add record");
          }
             
        }catch(Exception e){
             Logger.getLogger(LogForm.class.getName()).log(Level.SEVERE, null, e);
        }
       
            
       
         
     }
    
   
    
    public double price_ticket(){
        double price = 0;
        if(jComboBox1.getSelectedItem().equals("Balintawak") && jComboBox2.getSelectedItem().equals("EDSA")
           || jComboBox1.getSelectedItem().equals("EDSA") && jComboBox2.getSelectedItem().equals("Balintawak")){
            price = 120;
        }else if(jComboBox1.getSelectedItem().equals("Balintawak") && jComboBox2.getSelectedItem().equals("R. Papa")
           || jComboBox1.getSelectedItem().equals("R. Papa") && jComboBox2.getSelectedItem().equals("Balintawak")){
            price = 180;
        }else if(jComboBox1.getSelectedItem().equals("Balintawak") && jComboBox2.getSelectedItem().equals("5th Avenue")
           || jComboBox1.getSelectedItem().equals("5th Avenue") && jComboBox2.getSelectedItem().equals("Balintawak")){
            price = 210;
        }else if(jComboBox1.getSelectedItem().equals("Balintawak") && jComboBox2.getSelectedItem().equals("Monumento")
           || jComboBox1.getSelectedItem().equals("Monumento") && jComboBox2.getSelectedItem().equals("Balintawak")){
            price = 180;
        }
        
        
        return price;
    }
    
    
   
   
    
    public String getText(){
        String type ="";
        
        if(senRad.isSelected()){
            type = "Senior Citizen";      
        }else if(studRad.isSelected()){
            type = "Student";
        }else if(childRad1.isSelected()){
             type = "Child";    
        }else if(regRad.isSelected()){
            type = "Regular";
        }
        return type;
    }
    
     public double price_discount(){
        double disc_fee = 0;
        double fee = Integer.parseInt(jTextField1.getText());
        
        
        if(senRad.isSelected()){
            disc_fee = price_ticket() - 50;      
        }else if(studRad.isSelected()){
           disc_fee = price_ticket() - 30; 
        }else if(childRad1.isSelected()){
            disc_fee = price_ticket() - 20;    
        }else if(regRad.isSelected()){
            disc_fee = price_ticket(); 
        }
        
       
        return disc_fee;
    }
     
     
     public boolean check_fee(){
          boolean test = true;
          double fee = Integer.parseInt(jTextField1.getText());
               if(price_discount() > fee){
                test = false;
                }else{
                    test = true;
                }     
          return test;
     }
     
     public void clear(){
            nameCustomer.setText("");
            jTextField1.setText("");
            buttonGroup1.clearSelection();
            jComboBox1.setSelectedIndex(0);
            jComboBox2.setSelectedIndex(0);
     }
    
    
 
    
    public void add_ticket(){
        boolean flag = false;
        boolean clr = false;
        String name = nameCustomer.getText();
        int customer_fee = 0;
        String destination = String.valueOf(jComboBox2.getSelectedItem());
        String from = String.valueOf(jComboBox1.getSelectedItem());
        
        
        
        while(!flag){
             try{
                   customer_fee = Integer.parseInt(jTextField1.getText());
                if (customer_fee>= 0){
                    if(name.length() > 8){
                         if(!from.equals(destination)){
                             if(getText().length() > 0){
                                 if(check_fee() == true){
                                     flag = true;
                                 }else{
                                   JOptionPane.showMessageDialog(this, "Money isn't enough");
                                   break;
                                 }
                                
                             }else{
                                 JOptionPane.showMessageDialog(this, "Please, select customer type");
                                 break;
                             }
                         }else{
                             JOptionPane.showMessageDialog(this, "Ooops! Wrong route?");
                             break;
                         }
                    }else{
                         JOptionPane.showMessageDialog(this, "Please enter full name");
                        break;
                    }
                   
                                
                }else{
                    JOptionPane.showMessageDialog(this, "Please enter format");
                }
             }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(this, "Please enter format of amount");
                    break;
             }
        }
        
        if(flag){
           int min = 1000;  
           int max = 4000;
           String nameCust = nameCustomer.getText();
           String custType = getText();
           String custFrom = String.valueOf(jComboBox1.getSelectedItem());
           String custDes = String.valueOf(jComboBox2.getSelectedItem());
            double change = Integer.parseInt(jTextField1.getText()) - price_discount();
            int b = (int)(Math.random()*(max-min+1)+min);
            ticketNumber.setText(Integer.toString(b));
            lblFee.setText("₱"+Integer.toString(customer_fee));
            lblCustomer.setText(nameCustomer.getText());
            lblType.setText(getText());
            lblFrom.setText(String.valueOf(jComboBox1.getSelectedItem()));
            lblDestination.setText(String.valueOf(jComboBox2.getSelectedItem()));
            lblDate.setText((formatter.format(date)));
            lblPrice.setText("₱"+Double.toString(price_discount()));
            lblChange.setText("₱"+Double.toString(change));
            addInfo(nameCust, custType, custFrom, custDes);
            clr = true;
        }
        
        if(clr==true){
            clear();
        }
    }
    
    
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        nameTicket = new javax.swing.JLabel();
        nameTicket1 = new javax.swing.JLabel();
        nameTicket2 = new javax.swing.JLabel();
        nameTicket3 = new javax.swing.JLabel();
        nameTicket4 = new javax.swing.JLabel();
        nameTicket5 = new javax.swing.JLabel();
        lblCustomer = new javax.swing.JTextField();
        lblType = new javax.swing.JTextField();
        lblFrom = new javax.swing.JTextField();
        lblDestination = new javax.swing.JTextField();
        lblFee = new javax.swing.JTextField();
        lblChange = new javax.swing.JTextField();
        nameTicket6 = new javax.swing.JLabel();
        lblDate = new javax.swing.JTextField();
        nameTicket7 = new javax.swing.JLabel();
        lblPrice = new javax.swing.JTextField();
        ticketNumber = new javax.swing.JLabel();
        nameTicket9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        senRad = new javax.swing.JRadioButton();
        studRad = new javax.swing.JRadioButton();
        regRad = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        nameCustomer = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        childRad1 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Main Page"); // NOI18N
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Gill Sans MT", 0, 36)); // NOI18N
        jLabel1.setText("TICKETING SYSTEM");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/train.png"))); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/train1.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Gill Sans MT", 0, 36)); // NOI18N
        jLabel9.setText("TRAIN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(19, 19, 19))))
        );

        jPanel3.setBackground(new java.awt.Color(51, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "TICKET PREVIEW", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Gill Sans MT", 0, 18))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153)));

        nameTicket.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameTicket.setText("Name of Customer: ");

        nameTicket1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameTicket1.setText("Customer Type:");

        nameTicket2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameTicket2.setText("FROM:");

        nameTicket3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameTicket3.setText("TO:");

        nameTicket4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameTicket4.setText("CUSTOMER FEE:");

        nameTicket5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameTicket5.setText("CHANGE:");

        lblCustomer.setBackground(new java.awt.Color(153, 153, 153));
        lblCustomer.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));

        lblType.setBackground(new java.awt.Color(153, 153, 153));
        lblType.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));

        lblFrom.setBackground(new java.awt.Color(153, 153, 153));
        lblFrom.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));

        lblDestination.setBackground(new java.awt.Color(153, 153, 153));
        lblDestination.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));

        lblFee.setBackground(new java.awt.Color(153, 153, 153));
        lblFee.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));

        lblChange.setBackground(new java.awt.Color(153, 153, 153));
        lblChange.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));

        nameTicket6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameTicket6.setText("DATE:");

        lblDate.setBackground(new java.awt.Color(153, 153, 153));
        lblDate.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));

        nameTicket7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameTicket7.setText("TICKET PRICE:");

        lblPrice.setBackground(new java.awt.Color(153, 153, 153));
        lblPrice.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(0, 0, 0)));

        ticketNumber.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ticketNumber.setText("0000");

        nameTicket9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nameTicket9.setText("Ticket Number:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nameTicket)
                                    .addComponent(lblCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblType, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nameTicket1)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nameTicket2)
                                    .addComponent(nameTicket3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                    .addComponent(lblDestination))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(82, 82, 82)
                                        .addComponent(nameTicket6))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblFee, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(nameTicket4)
                                .addGap(38, 38, 38)
                                .addComponent(nameTicket7)))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameTicket5)
                            .addComponent(lblChange, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(52, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nameTicket9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ticketNumber)
                .addGap(24, 24, 24))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameTicket9)
                            .addComponent(ticketNumber))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameTicket)
                            .addComponent(nameTicket1))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblType, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameTicket2)
                            .addComponent(lblFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameTicket3)
                            .addComponent(lblDestination, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(nameTicket6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(nameTicket4))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameTicket7)
                            .addComponent(nameTicket5))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFee, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblChange, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "ADD A TICKET", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Gill Sans MT", 0, 18))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel2.setText("CUSTOMER TYPE");

        senRad.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup1.add(senRad);
        senRad.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        senRad.setText("Senior");
        senRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                senRadActionPerformed(evt);
            }
        });

        studRad.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup1.add(studRad);
        studRad.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        studRad.setText("Student");
        studRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studRadActionPerformed(evt);
            }
        });

        regRad.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup1.add(regRad);
        regRad.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        regRad.setText("Regular");

        jLabel3.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel3.setText("DESTINATION");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Balintawak", "Monumento", "5th Avenue", "R. Papa", "EDSA" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel4.setText("FROM");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EDSA", "R. Papa", "5th Avenue", "Monumento", "Balintawak" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel5.setText("NAME OF CUSTOMER");

        nameCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameCustomerActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        jButton1.setText("CONFIRM");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        childRad1.setBackground(new java.awt.Color(51, 204, 255));
        buttonGroup1.add(childRad1);
        childRad1.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        childRad1.setText("Child");

        jLabel6.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel6.setText("CUSTOMER FEE ");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameCustomer, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(studRad)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(childRad1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(regRad)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(senRad)))
                                .addGap(0, 2, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studRad)
                    .addComponent(senRad)
                    .addComponent(childRad1)
                    .addComponent(regRad))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        jMenu1.setText("Menu");

        jMenuItem1.setText("Admin Panel");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void studRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studRadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studRadActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
       
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void nameCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameCustomerActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        nameTicket1.setVisible(true);
        nameTicket2.setVisible(true);
        nameTicket3.setVisible(true);
        nameTicket4.setVisible(true);
        nameTicket5.setVisible(true);
        nameTicket.setVisible(true);
        lblCustomer.setVisible(true); lblCustomer.setEditable(false);
        lblType.setVisible(true);  lblType.setEditable(false);
        lblFee.setVisible(true); lblFee.setEditable(false);
        lblFrom.setVisible(true); lblDestination.setVisible(true);
        lblFrom.setEditable(false); lblDestination.setEditable(false);
        lblChange.setVisible(true); lblChange.setEditable(false);
        
        if(Double.parseDouble(jTextField1.getText()) > 1000){
             JOptionPane.showMessageDialog(this, "Oops! Somethings wrong.");
        }else{
            if(jTextField1.getText().length() >= 4){
                 JOptionPane.showMessageDialog(this, "Oops! Somethings wrong.");
            }else{
               add_ticket();   
            }
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void senRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_senRadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_senRadActionPerformed
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new NewJFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        
    }//GEN-LAST:event_jComboBox2ActionPerformed

   
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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main_menu().setVisible(true);    
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JRadioButton childRad1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField lblChange;
    private javax.swing.JTextField lblCustomer;
    private javax.swing.JTextField lblDate;
    private javax.swing.JTextField lblDestination;
    private javax.swing.JTextField lblFee;
    private javax.swing.JTextField lblFrom;
    private javax.swing.JTextField lblPrice;
    private javax.swing.JTextField lblType;
    private javax.swing.JTextField nameCustomer;
    private javax.swing.JLabel nameTicket;
    private javax.swing.JLabel nameTicket1;
    private javax.swing.JLabel nameTicket2;
    private javax.swing.JLabel nameTicket3;
    private javax.swing.JLabel nameTicket4;
    private javax.swing.JLabel nameTicket5;
    private javax.swing.JLabel nameTicket6;
    private javax.swing.JLabel nameTicket7;
    private javax.swing.JLabel nameTicket9;
    private javax.swing.JRadioButton regRad;
    private javax.swing.JRadioButton senRad;
    private javax.swing.JRadioButton studRad;
    private javax.swing.JLabel ticketNumber;
    // End of variables declaration//GEN-END:variables

   
}
