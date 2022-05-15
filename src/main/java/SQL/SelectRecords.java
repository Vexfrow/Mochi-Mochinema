package SQL;

import java.sql.DriverManager;
import java.sql.Connection;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;

import mochinema.Abonne;
import mochinema.Cinema;
import mochinema.Critique;
import mochinema.Film;
import mochinema.Prix;
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

                Abonne a = new Abonne(rs.getString("abonne_pseudo"), rs.getString("abonne_nom"), rs.getString("abonne_prenom"), rs.getString("abonne_adresse_mail"), rs.getString("abonne_mot_passe"), rs.getString("abonne_date_naissance"));
                stmt.close();
                return a;

            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
                return null;
            }  
        }  


        //Permet de récuperer les données d'un film à partir de son id
        public Film selectFilmId(int idFilm){  
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
                Professionel f = new Professionel(rs.getString("professionel_nom"), rs.getString("professionel_prenom"), rs.getString("professionel_date_naissance"));
                stmt.close();
                return f;

            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
                return null;
            }  
        } 


         public Professionel[] selectActeurs(int idFilm){  
            String sql = "SELECT * FROM participant \n WHERE film_id = '"+idFilm+"' AND (participant_profession = 'acteur' OR  participant_profession = 'doubleur');";  
        
            String sqlNbProfessionel = "SELECT COUNT(professionel_nom) AS number FROM participant \n WHERE film_id = '"+idFilm+"' AND (participant_profession = 'acteur' OR  participant_profession = 'doubleur');";
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sqlNbProfessionel);  

                Professionel[] tabActeurs = new Professionel[rs.getInt("number")];

                rs = stmt.executeQuery(sql);  
                int i = 0;

                while (rs.next()) {  
                    tabActeurs[i] =  new Professionel(rs.getString("professionel_nom"), rs.getString("professionel_prenom"), rs.getString("professionel_date_naissance"));
                    i++;
                }  

                stmt.close();
                return tabActeurs;

            } catch (SQLException e) {  
                System.out.println(e.getMessage()); 
                return null;
            }   
        }



        public Professionel[] selectAllActeurs(){  
            String sql = "SELECT * FROM participant \n WHERE participant_profession = 'acteur' OR  participant_profession = 'doubleur';";  
        
            String sqlNbProfessionel = "SELECT COUNT(professionel_nom) AS number FROM participant \n WHERE participant_profession = 'acteur' OR  participant_profession = 'doubleur';";
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sqlNbProfessionel);  

                Professionel[] tabActeurs = new Professionel[rs.getInt("number")];

                rs = stmt.executeQuery(sql);  
                int i = 0;

                while (rs.next()) {  
                    tabActeurs[i] =  new Professionel(rs.getString("professionel_nom"), rs.getString("professionel_prenom"), rs.getString("professionel_date_naissance"));
                    i++;
                }  

                stmt.close();
                return tabActeurs;

            } catch (SQLException e) {  
                System.out.println(e.getMessage()); 
                return null;
            }   
        }



        public int[] selectAllAnneeFilm(){  
            String sql = "SELECT film_annee_production FROM film;";  
        
            String sqlNbProfessionel = "SELECT COUNT(film_annee_production) AS number FROM film ;";
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sqlNbProfessionel);  

                int[] tabAnnee = new int[rs.getInt("number")];

                rs = stmt.executeQuery(sql);  
                int i = 0;

                while (rs.next()) {  
                    tabAnnee[i] =  rs.getInt("film_annee_production");
                    i++;
                }  

                stmt.close();
                return tabAnnee;

            } catch (SQLException e) {  
                System.out.println(e.getMessage()); 
                return null;
            }   
        }


        //Permet de récuperer un tableau contenant toutes les critiques du film en question
        public Critique[] selectCritiquesFilm(int idFilm){  
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



        //Permet de récuperer un tableau contenant toutes les critiques d'un abonnée
        public Critique[] selectCritiquesAbonne(String pseudo) {
            String sql = "SELECT * FROM critique \n WHERE abonne_pseudo = '"+pseudo+"';";  

            //Commande SQL pour avoir le nombre de critique pour le film
            String sqlNbCritique = "SELECT COUNT(film_id) AS number FROM critique WHERE abonne_pseudo = '"+pseudo+"';";
              
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



         //Permet de récuperer un tableau contenant tout les film dans lequel a joué un acteur
         public Film[] selectFilmParticipant(String nom, String prenom, String dateNaissance) {
            String sql = "SELECT film_titre, film_id, film_annee_production FROM participant JOIN film USING (film_id) \n WHERE professionel_nom = '"+nom+"' AND professionel_prenom = '"+prenom+"' AND professionel_date_naissance = '"+dateNaissance+"';";

            //Commande SQL pour avoir le nombre de film pour l'acteur
            String sqlNbCritique = "SELECT COUNT(film_id) AS number FROM participant WHERE professionel_nom = '"+nom+"' AND professionel_prenom = '"+prenom+"' AND professionel_date_naissance = '"+dateNaissance.toString()+"';";
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sqlNbCritique);  
                Film[] tabFilm = new Film[rs.getInt("number")];

                rs = stmt.executeQuery(sql);  
                int i = 0;

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



        //Permet de récuperer un tableau contenant tout les film sortis après une année demandée
        public Film[] selectFilmAnnee(int annee) {
            String sql = "SELECT film_titre, film_id, film_annee_production FROM film WHERE film_annee_production >= "+ annee+" ORDER BY film_annee_production ASC;";

            //Commande SQL pour avoir le nombre de film pour l'acteur
            String sqlNbFilm = "SELECT COUNT(film_id) AS number FROM film WHERE film_annee_production >= "+ annee+";";
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sqlNbFilm);  
                Film[] tabFilm = new Film[rs.getInt("number")];

                rs = stmt.executeQuery(sql);  
                int i = 0;

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


        //Permet de récuperer un tableau contenant tout les film sortis après une année demandée
        public Film[] selectFilmAnneeActeur(String nom, String prenom, String dateNaissance, int annee) {
            String sql = "SELECT film_titre, film_id, film_annee_production FROM film WHERE film_annee_production >= "+ annee
                        + " INTERSECT "
                        + "SELECT film_titre, film_id, film_annee_production FROM participant JOIN film USING (film_id) \n WHERE professionel_nom = '"+nom+"' AND professionel_prenom = '"+prenom+"' AND professionel_date_naissance = '"+dateNaissance+"' "
                        + "ORDER BY film_annee_production ASC;";

              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sql);  

                int taille = 0;
                while(rs.next()){
                    taille++;
                }

                Film[] tabFilm = new Film[taille];

                rs = stmt.executeQuery(sql);  
                int i = 0;

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

                Abonne a = new Abonne(rs.getString("abonne_pseudo"), rs.getString("abonne_nom"), rs.getString("abonne_prenom"), rs.getString("abonne_adresse_mail"), rs.getString("abonne_mot_passe"), rs.getString("abonne_date_naissance"));
                stmt.close();
                return a;

            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
                return null;
            }  
        } 




         //Permet de recuperer les données d'un abonnée à partir de son pseudo
         public Prix[] selectPrix(int id) {
            String sql = "SELECT prix_prix, prix.cinema_id, cinema_ville, cinema_nom FROM prix JOIN cinema ON (prix.cinema_id = cinema.cinema_id) WHERE film_id = '"+id+"';";  

            //Commande SQL pour avoir le nombre de cinema qui propose le film
            String sqlNbPrix = "SELECT COUNT(cinema_id) AS number FROM prix WHERE film_id = "+id+";";
              
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sqlNbPrix);

                Prix[] tabPrix = new Prix[rs.getInt("number")];
                int i = 0;
                rs = stmt.executeQuery(sql);

                while(rs.next()){
                    SelectRecords sr = new SelectRecords();
                    Cinema cin = new Cinema(rs.getInt("cinema_id"), rs.getString("cinema_nom"), rs.getString("cinema_ville"));
                    Film f = sr.selectFilmId(id);

                    tabPrix[i] = new Prix(cin, f, rs.getInt("prix_prix"));
                    i++;
                }
                
                stmt.close();
                return tabPrix;

            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
                return null;
            }  
        } 



        public Critique selectPrevCrit(String pseudo, int filmID){
            String sql = "SELECT * FROM Critique WHERE abonne_pseudo = '"+ pseudo + "' AND film_id = "+filmID + ";";
            try {  
                Connection conn = this.connect();  
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sql);  

                Critique c = new Critique(pseudo, filmID, rs.getString("critique_critique"), rs.getInt("critique_note"));
                stmt.close();
                return c;

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