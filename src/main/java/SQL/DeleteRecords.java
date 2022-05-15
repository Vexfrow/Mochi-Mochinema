package SQL;

import java.sql.DriverManager;
    import java.sql.Connection;   
    import java.sql.SQLException;  
    import java.sql.Statement;


    public class DeleteRecords {  
       
        private Connection connect() {  
            String url =  Create.urlDatabase;  
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
                deleteCommentaire(pseudo);

                stmt.close();
                System.out.println("Un abonné a été supprimé.");
            
                  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  

        public void deleteCommentaire(String pseudo){
            String sql = "DELETE FROM critique WHERE abonne_pseudo = '" + pseudo + "';";

            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement(); 
                stmt.executeUpdate(sql);
                stmt.close();
                System.out.println("Commentaire supprimé.");
            
                  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            } 
        }

        public void deleteCommentaireParticulier(String pseudo, int filmID){
            String sql = "DELETE FROM critique WHERE abonne_pseudo = '" + pseudo + "' AND film_id = " + filmID + ";";

            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement(); 
                stmt.executeUpdate(sql);
                stmt.close();
                System.out.println("Commentaire supprimé.");
            
                  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }             
        }


       
        public static void main(String[] args) { 
        }  
       
    }  