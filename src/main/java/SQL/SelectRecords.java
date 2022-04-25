package SQL;

import java.sql.DriverManager;
    import java.sql.Connection;  
    import java.sql.ResultSet;  
    import java.sql.SQLException;  
    import java.sql.Statement;

import mochinema.Abonne;
import mochinema.Date;  
       
    public class SelectRecords {  
       
        private Connection connect() {  
            // SQLite connection string  
            String url =  Create.urlDatabase;  
            Connection conn = null;  
            try {  
                conn = DriverManager.getConnection(url);  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
            return conn;  
        }  
       
      
        public void selectAllAbonne(){  
            String sql = "SELECT * FROM abonne";  
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sql);  
                  
                // loop through the result set  
                while (rs.next()) {  
                    System.out.println(rs.getString("abonne_pseudo") +  "\t" +   
                                       rs.getString("abonne_prenom") + "\t" +  
                                       rs.getString("abonne_nom"));  
                }  
                stmt.close();
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  



        public Abonne selectAbonneSpecifique(String pseudo, String motDePasse){  
            String sql = "SELECT * FROM abonne \n WHERE abonne_pseudo = '"+pseudo+"' AND abonne_mot_passe = '"+motDePasse+"';";  
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sql);  
                  
                // loop through the result set  
                if (!rs.isBeforeFirst()) {  
                    stmt.close();
                    return null;  
                } else{
                    Date d = new Date(rs.getString("abonne_date_naissance"));
                    Abonne a = new Abonne(rs.getString("abonne_pseudo"), rs.getString("abonne_nom"), rs.getString("abonne_prenom"), rs.getString("abonne_adresse_mail"), rs.getString("abonne_mot_passe"), d);
                    stmt.close();
                    return a;
                }
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
                return null;
            }  
        }  


        public boolean connection(String pseudo, String motDePasse){  
            String sql = "SELECT * FROM abonne \n WHERE abonne_pseudo = '"+pseudo+"' AND abonne_mot_passe = '"+motDePasse+"';";  
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sql);  
                  
                boolean b = rs.isBeforeFirst();
                stmt.close();
                return b;
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
                return false;
            }  
        }  



        public String getMDPAbonne(String pseudo){  
            String sql = "SELECT * FROM abonne \n WHERE abonne_pseudo = '"+pseudo+"' ;";  
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sql);  
                String s = rs.getString("abonne_mot_passe");
                stmt.close();
                return s;

            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
                return null;
            }  
        }  




           
         
        /** 
         * @param args the command line arguments 
         */  
        public static void main(String[] args) {  
            SelectRecords app = new SelectRecords();  
            // app.selectAll();  
            app.selectAllAbonne();
        }  
       
    }  