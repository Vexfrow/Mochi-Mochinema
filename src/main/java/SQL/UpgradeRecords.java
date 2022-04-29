package SQL;

import java.sql.DriverManager;
    import java.sql.Connection;   
    import java.sql.SQLException;  
    import java.sql.Statement;


    public class UpgradeRecords{  
       
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
       
      //nom prenom adresse mail pas le pseudo
        public void upgradeAbonne(String pseudo, String nouveauPrenom, String nouvelleAdresseMail, String nouveauNom){ 
            String sql = "UPDATE abonne SET abonne_prenom ='" +  nouveauPrenom +"', abonne_nom = '" + nouveauNom +  "', abonne_adresse_mail = '" +nouvelleAdresseMail + "' WHERE abonne_pseudo = '"+ pseudo + "';";
            
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement(); 
                stmt.executeUpdate(sql);
                stmt.close();
                System.out.println("Les données d'un abonné ont été modifiées.");
                  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  


        public void upgradeAbonneMdp(String pseudo, String nouveauMot){ 
            String sql = "UPDATE abonne SET abonne_mot_passe = '" +  nouveauMot +"' WHERE abonne_pseudo = '"+ pseudo + "';";

            try {
                Connection conn = this.connect();
                Statement stmt  = conn.createStatement(); 
                stmt.executeUpdate(sql);
                stmt.close();
                System.out.println("Les données d'un abonné ont été modifiées.");


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }



       
        public static void main(String[] args) { 
            UpgradeRecords ur = new UpgradeRecords();
            ur.upgradeAbonne("Invite", "Invite", "InviteDu96@gmail.com", "Invite");
            ur.upgradeAbonneMdp("Invite", "Camembert");
        }  
       
    }  