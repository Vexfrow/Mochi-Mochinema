package SQL;

import java.sql.DriverManager;
import java.sql.Connection;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;

import mochinema.Abonne;
import mochinema.Critique;
import mochinema.Date;
import mochinema.Film;  
       
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


        public Film[] selectAllFilm(){  
            String sql = "SELECT * FROM film";  
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sql);  
                int posTabFilm = 0;
                  
                // loop through the result set  
                while (rs.next()) {  
                    posTabFilm++;
                }  

                rs = stmt.executeQuery(sql);  
                Film[] tabFilm = new Film[posTabFilm];
                int i = 0;
                // loop through the result set  
                while (rs.next()) {  
                    tabFilm[i] = new Film(rs.getInt("film_id"), rs.getString("film_titre"), rs.getInt("film_annee_production"));
                    i++;
                }  

                stmt.close();
                return tabFilm;
            } catch (SQLException e) {  
                System.out.println(e.getMessage()); 
                return null;
            }  
        } 



        public Abonne selectAbonneSpecifique(String pseudo, String motDePasse){  
            String sql = "SELECT * FROM abonne \n WHERE abonne_pseudo = '"+pseudo+"' AND abonne_mot_passe = '"+motDePasse+"';";  
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sql);  

                Date d = new Date(rs.getString("abonne_date_naissance"));
                Abonne a = new Abonne(rs.getString("abonne_pseudo"), rs.getString("abonne_nom"), rs.getString("abonne_prenom"), rs.getString("abonne_adresse_mail"), rs.getString("abonne_mot_passe"), d);
                stmt.close();
                return a;

            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
                return null;
            }  
        }  


        public Film selectFilm(int idFilm){  
            String sql = "SELECT * FROM film \n WHERE film_id = '"+idFilm+"';";  
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sql);  
                Film f = new Film(rs.getInt("film_id"), rs.getString("film_titre"), rs.getInt("film_annee_production"));
                stmt.close();
                return f;

            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
                return null;
            }  
        } 


        public Critique[] selectCritiques(int idFilm){  
            String sql = "SELECT * FROM critique \n WHERE film_id = '"+idFilm+"';";  
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sql);  
                int posTabCritique = 0;
                  
                // loop through the result set  
                while (rs.next()) {  
                    posTabCritique++;
                }  

                rs = stmt.executeQuery(sql);  
                Critique[] tabCritiques = new Critique[posTabCritique];
                int i = 0;
                // loop through the result set  
                while (rs.next()) {  
                    tabCritiques[i] = new Critique(rs.getString("abonne_pseudo"), rs.getInt("film_id"), rs.getString("critique_critique"), rs.getInt("critique_note"));
                    i++;
                }  

                stmt.close();
                return tabCritiques;

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


        public float moyenneNote(int film_id){
            String sql = "SELECT AVG(critique_note) AS mean \n FROM critique \n WHERE film_id = " + film_id+";";
            try {
                Connection conn = this.connect();
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql);
                float s = rs.getFloat("mean");
                stmt.close();
                return s;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return 0;
            }
        }

 
         
        /** 
         * @param args the command line arguments 
         */  
        public static void main(String[] args) {  
            SelectRecords app = new SelectRecords();  
            // app.selectAll();  
            app.selectAllFilm();
        }


        public Abonne selectAbonneSpecifique(String pseudo) {
            String sql = "SELECT * FROM abonne \n WHERE abonne_pseudo = '"+pseudo+"';";  
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sql);  

                Date d = new Date(rs.getString("abonne_date_naissance"));
                Abonne a = new Abonne(rs.getString("abonne_pseudo"), rs.getString("abonne_nom"), rs.getString("abonne_prenom"), rs.getString("abonne_adresse_mail"), rs.getString("abonne_mot_passe"), d);
                stmt.close();
                return a;

            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
                return null;
            }  
        }  
       
    }  