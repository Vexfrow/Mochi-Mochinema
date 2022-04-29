package SQL;

import java.sql.Connection;
    import java.sql.DriverManager;  
    import java.sql.SQLException;  
    import java.sql.Statement;  
       
    public class CreateTable {  

        private static String url =  Create.urlDatabase;

       
        public static void createNewTableCinema() {                
            // SQL statement for creating a new table  
            String sql = "CREATE TABLE IF NOT EXISTS cinema (\n"  
                    + " cinema_id integer PRIMARY KEY,\n"  
                    + " cinema_nom text NOT NULL,\n"  
                    + " cinema_ville text NOT NULL,\n"  
                    + " CONSTRAINT ck_cinema_id CHECK (cinema_id >= 0)\n"
                    + " );";  
              
            try{  
                Connection conn = DriverManager.getConnection(url);  
                Statement stmt = conn.createStatement();  
                stmt.execute(sql); 
                System.out.println("A new table has been created.");
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  


        public static void createNewTableProfessionel() {  
            // SQL statement for creating a new table  
            String sql = "CREATE TABLE IF NOT EXISTS professionel (\n"  
                    + " professionel_nom text,\n"  
                    + " professionel_prenom text,\n"  
                    + " professionel_date_naissance text,\n" 
                    + " PRIMARY KEY (professionel_nom, professionel_prenom, professionel_date_naissance)" 
                    + " );";  
              
            try{  
                Connection conn = DriverManager.getConnection(url);  
                Statement stmt = conn.createStatement();  
                stmt.execute(sql); 
                System.out.println("A new table has been created.");
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  



        public static void createNewTableFilm(){              
            // SQL statement for creating a new table  
            String sql = "CREATE TABLE IF NOT EXISTS film (\n"  
                    + " film_id integer PRIMARY KEY,\n"  
                    + " film_titre text,\n"  
                    + " film_annee_production INTEGER (4,0),\n" 
                    + " CONSTRAINT ck_film_id CHECK (film_id >= 0)" 
                    + " );";  
              
            try{  
                Connection conn = DriverManager.getConnection(url);  
                Statement stmt = conn.createStatement();  
                stmt.execute(sql); 
                System.out.println("A new table has been created.");
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  



        public static void createNewTableParticipant(){                
            // SQL statement for creating a new table  
            String sql = "CREATE TABLE IF NOT EXISTS participant (\n"  
                    + " professionel_nom text,\n"  
                    + " professionel_prenom text,\n"
                    + " professionel_date_naissance text,\n"
                    + " film_id integer,\n"
                    + " participant_profession text,\n"
                    + " PRIMARY KEY(professionel_nom, professionel_prenom, professionel_date_naissance, film_id),\n"
                    + " FOREIGN KEY(professionel_nom) REFERENCES professionel(professionel_nom),\n"
                    + " FOREIGN KEY(professionel_prenom) REFERENCES professionel(professionel_prenom),\n"
                    + " FOREIGN KEY(professionel_date_naissance) REFERENCES professionel(professionel_date_naissance),\n"
                    + " FOREIGN KEY(film_id) REFERENCES film(film_id),\n"
                    + " CONSTRAINT ck_participant CHECK (participant_profession == 'realisateur' OR participant_profession == 'acteur' OR participant_profession == 'doubleur')" 
                    + " );";  
              
            try{  
                Connection conn = DriverManager.getConnection(url);  
                Statement stmt = conn.createStatement();  
                stmt.execute(sql); 
                System.out.println("A new table has been created.");

            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        } 


        public static void createNewTableAbonne() {              
            // SQL statement for creating a new table  
            String sql = "CREATE TABLE IF NOT EXISTS Abonne (\n"  
                    + "abonne_pseudo TEXT PRIMARY KEY,\n"  
                    + "abonne_nom TEXT NOT NULL,\n"  
                    + "abonne_prenom TEXT NOT NULL,\n"
                    + "abonne_adresse_mail TEXT NOT NULL,\n"  
                    + "abonne_date_naissance Date,\n"
                    + "abonne_mot_passe TEXT NOT NULL\n"
                    + ");";  
              
            try{  
                Connection conn = DriverManager.getConnection(url);  
                Statement stmt = conn.createStatement();  
                stmt.execute(sql); 
                System.out.println("A new table has been created.");
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        } 

        
        public static void createNewTableCritique() {              
            // SQL statement for creating a new table  
            String sql = "CREATE TABLE IF NOT EXISTS Critique (\n"  
                    + "abonne_pseudo TEXT,\n"  
                    + "film_id INTEGER,\n"  
                    + "critique_critique VARCHAR(240) NOT NULL,\n"
                    + "critique_note INTEGER,\n"  
                    + "CONSTRAINT pk_critique_pseudo_id PRIMARY KEY (abonne_pseudo, film_id),\n"
                    + "CONSTRAINT fk_critique_pseudo FOREIGN KEY (abonne_pseudo) REFERENCES Abonne (abonne_pseudo) ON DELETE CASCADE ON UPDATE CASCADE,\n"
                    + "CONSTRAINT fk_critique_film FOREIGN KEY (film_id) REFERENCES Film(film_id) ON DELETE CASCADE ON UPDATE CASCADE,\n"
                    + "CONSTRAINT ck_critique_note CHECK (critique_note >= 0 AND critique_note <= 10), \n"
                    + "CONSTRAINT ck_critique_taille CHECK (LENGTH(critique_critique) <= 240) \n"
                    + ");";  
              
            try{  
                Connection conn = DriverManager.getConnection(url);  
                Statement stmt = conn.createStatement();  
                stmt.execute(sql); 
                System.out.println("A new table has been created.");
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }


        public static void createNewTablePrix() {              
            // SQL statement for creating a new table  
            String sql = "CREATE TABLE IF NOT EXISTS Prix (\n"  
                    + "cinema_id INTEGER,\n"  
                    + "film_id INTEGER,\n"  
                    + "prix_prix INTEGER NOT NULL,\n" 
                    + "CONSTRAINT pk_prix_id_id PRIMARY KEY (cinema_id, film_id),\n"
                    + "CONSTRAINT fk_prix_cinID FOREIGN KEY (cinema_id) REFERENCES Abonne (cinema_id) ON DELETE CASCADE ON UPDATE CASCADE,\n"
                    + "CONSTRAINT fk_prix_filmID FOREIGN KEY (film_id) REFERENCES Film(film_id) ON DELETE CASCADE ON UPDATE CASCADE,\n"
                    + "CONSTRAINT ck_prix_prix CHECK (prix_prix >= 0) \n"
                    + ");";  
              
            try{  
                Connection conn = DriverManager.getConnection(url);  
                Statement stmt = conn.createStatement();  
                stmt.execute(sql); 
                System.out.println("A new table has been created.");
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }

       
        /** 
         * @param args the command line arguments 
         */  
        public static void main(String[] args) {  
            createNewTableCinema();  
            createNewTableProfessionel();
            createNewTableFilm();
            createNewTableParticipant();
            createNewTableAbonne();
            createNewTableCritique();
            createNewTablePrix();
        }  
       
    }  