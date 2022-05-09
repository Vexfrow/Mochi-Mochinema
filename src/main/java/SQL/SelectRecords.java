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
import mochinema.Professionel;  
       
    public class SelectRecords {  
       
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
       
    


        //Permet de récuperer un tableau contenant tout les films de la base de données
        public Film[] selectAllFilm(){  

            //Commande SQL pour récupérer les informations sur tout les film
            String sql = "SELECT * FROM film"; 
            
            //Commande SQL pour avoir le nombre de film
            String sqlNbFilm = "SELECT COUNT(film_id) AS number FROM film";
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  

                //On execute la commande SQL afin d'avoir le nombre de film
                ResultSet rs    = stmt.executeQuery(sqlNbFilm);  

                Film[] tabFilm = new Film[rs.getInt("number")];

                //On execute la commande SQL afin d'avoir les informations de chaque film
                rs = stmt.executeQuery(sql);  

                int i = 0;
                // On re-parcours les resultats afin de récuperer les films dans la base de données 
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




        //Permet de récuperer les données d'un abonnée à partir de son pseudo et de son MDP
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


        //Permet de récuperer les données d'un film à partir de son id
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



        //Permet de récuperer le réalisateur d'un film à partir de son id
        public Professionel selectRealisateur(int idFilm){  
            String sql = "SELECT * FROM participant \n WHERE film_id = '"+idFilm+"' AND participant_profession = 'realisateur';";  
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sql);  
                Date d = new Date(rs.getString("professionel_date_naissance"));
                Professionel f = new Professionel(rs.getString("professionel_nom"), rs.getString("professionel_prenom"), d);
                stmt.close();
                return f;

            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
                return null;
            }  
        } 



         //Permet de récuperer un tableau contenant toutes les critiques du film en question
         public Professionel[] selectActeurs(int idFilm){  
            String sql = "SELECT * FROM participant \n WHERE film_id = '"+idFilm+"' AND (participant_profession = 'acteur' OR  participant_profession = 'doubleur');";  

            //Commande SQL pour avoir le nombre de critique pour le film
            String sqlNbProfessionel = "SELECT COUNT(professionel_nom) AS number FROM participant \n WHERE film_id = '"+idFilm+"' AND (participant_profession = 'acteur' OR  participant_profession = 'doubleur');";
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sqlNbProfessionel);  

                Professionel[] tabActeurs = new Professionel[rs.getInt("number")];

                rs = stmt.executeQuery(sql);  
                int i = 0;

                while (rs.next()) {  
                    Date d = new Date(rs.getString("professionel_date_naissance"));
                    tabActeurs[i] =  new Professionel(rs.getString("professionel_nom"), rs.getString("professionel_prenom"), d);
                    i++;
                }  

                stmt.close();
                return tabActeurs;

            } catch (SQLException e) {  
                System.out.println(e.getMessage()); 
                return null;
            }   
        }


        //Permet de récuperer un tableau contenant toutes les critiques du film en question
        public Critique[] selectCritiques(int idFilm){  
            String sql = "SELECT * FROM critique \n WHERE film_id = "+idFilm+";";  

            //Commande SQL pour avoir le nombre de critique pour le film
            String sqlNbCritique = "SELECT COUNT(film_id) AS number FROM critique WHERE film_id = "+idFilm+";";
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sqlNbCritique);  

                Critique[] tabCritiques = new Critique[rs.getInt("number")];

                rs = stmt.executeQuery(sql);  
                int i = 0;

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


        //Permet de récuperer la moyenne des notes d'un film
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



        
        //Permet de recuperer les données d'un abonnée à partir de son pseudo
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

 
         
        /** 
         * @param args the command line arguments 
         */  
        public static void main(String[] args) {  
            SelectRecords app = new SelectRecords();  
            // app.selectAll();  
            app.selectAllFilm();
        }

 
       
    }  