package SQL;

import java.sql.DriverManager;
    import java.sql.Connection;   
    import java.sql.SQLException;  
    import java.sql.Statement;


    public class DeleteRecords {  
       
        private Connection connect() {  
            // SQLite connection string  
            String url = "jdbc:sqlite:C://sqlite/Mochi-Mochinéma.db";  
            Connection conn = null;  
            try {  
                conn = DriverManager.getConnection(url);  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
            return conn;  
        }  
       
      
        public void deleteAbonne(String pseudo){  
            String sql = "DELETE FROM abonne WHERE abonne_pseudo = '"+ pseudo + "';";  
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement(); 
                stmt.executeUpdate(sql);
                stmt.close();
                System.out.println("A record has been deleted.");
            
                  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  



       
        public static void main(String[] args) {  
            DeleteRecords app = new DeleteRecords();
            app.deleteAbonne("ez i");
        }  
       
    }  