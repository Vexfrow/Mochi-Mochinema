package SQL;

import java.sql.Connection;
    import java.sql.DriverManager;  
    import java.sql.SQLException;  
    import java.sql.Statement;  
       
    public class CreateTable {  
       
        public static void createNewTableCinema() {  
            // SQLite connection string  
            String url = "jdbc:sqlite:C://sqlite/Mochi-Mochinéma.db";  
              
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
            // SQLite connection string  
            String url = "jdbc:sqlite:C://sqlite/Mochi-Mochinéma.db";  
              
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
            // SQLite connection string  
            String url = "jdbc:sqlite:C://sqlite/Mochi-Mochinéma.db";  
              
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
            // SQLite connection string  
            String url = "jdbc:sqlite:C://sqlite/Mochi-Mochinéma.db";  
              
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



        // public static void createNewTableAbonne() {  
        //     // SQLite connection string  
        //     String url = "jdbc:sqlite:C://sqlite/Mochi-Mochinéma.db";  
              
        //     // SQL statement for creating a new table  
        //     String sql = "CREATE TABLE IF NOT EXISTS cinema (\n"  
        //             + " cinema_id integer PRIMARY KEY,\n"  
        //             + " cinema_nom text NOT NULL,\n"  
        //             + " cinema_ville text NOT NULL,\n"  
        //             + " CONSTRAINT ck_cinema_id CHECK (cinema_id >= 0)\n"
        //             + " );";  
              
        //     try{  
        //         Connection conn = DriverManager.getConnection(url);  
        //         Statement stmt = conn.createStatement();  
        //         stmt.execute(sql); 
        //         System.out.println("A new table has been created.");
        //     } catch (SQLException e) {  
        //         System.out.println(e.getMessage());  
        //     }  
        // }  
       
        /** 
         * @param args the command line arguments 
         */  
        public static void main(String[] args) {  
            createNewTableCinema();  
            createNewTableProfessionel();
            createNewTableFilm();
            createNewTableParticipant();
        }  
       
    }  