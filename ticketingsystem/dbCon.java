
package ticketingsystem;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class dbCon {
    public Connection con;//for dbase connection
    public Statement s;//for creating connection statement
    
    public dbCon(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root","");
            s = con.createStatement();
            System.out.println("Connection Established");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbCon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(dbCon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void main(String[] args) {
        dbCon obj = new dbCon();
        
       
    }
    
}
