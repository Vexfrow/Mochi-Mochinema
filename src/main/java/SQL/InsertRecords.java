package SQL;

import java.sql.Connection;
    import java.sql.DriverManager;  
    import java.sql.PreparedStatement;  
    import java.sql.SQLException;  
       
    public class InsertRecords {  
       
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
       
      
        public void insertAbonne(String pseudo, String name, String surname, String mail, String birthday, String password) {  
            String sql = "INSERT INTO abonne(abonne_pseudo, abonne_nom, abonne_prenom, abonne_adresse_mail, abonne_date_naissance, abonne_mot_passe) VALUES(?,?,?,?,?,?)";  
       
            try{  
                Connection conn = this.connect();  
                PreparedStatement pstmt = conn.prepareStatement(sql);  
                pstmt.setString(1, pseudo);  
                pstmt.setString(2, name);
                pstmt.setString(3, surname);  
                pstmt.setString(4, mail);
                pstmt.setString(5, birthday);  
                pstmt.setString(6, password);  
                pstmt.executeUpdate();  
                pstmt.close();
                System.out.println("A new record has been inserted.");

            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  

        public void insertCinema(int id, String name, String city) {  
            String sql = "INSERT INTO cinema(cinema_id, cinema_nom, cinema_ville) VALUES(?,?,?)";  
       
            try{  
                Connection conn = this.connect();  
                PreparedStatement pstmt = conn.prepareStatement(sql);  
                pstmt.setInt(1, id);  
                pstmt.setString(2, name);
                pstmt.setString(3, city); 
                pstmt.executeUpdate();  
                pstmt.close();
                System.out.println("A new record has been inserted.");

            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  

        public void insertProfessionel(String name, String surname, String birthday) {  
            String sql = "INSERT INTO professionel(professionel_nom, professionel_prenom, professionel_date_naissance) VALUES(?,?,?)";  
       
            try{  
                Connection conn = this.connect();  
                PreparedStatement pstmt = conn.prepareStatement(sql);  
                pstmt.setString(1, name);  
                pstmt.setString(2, surname);
                pstmt.setString(3, birthday);  
                pstmt.executeUpdate();  

                pstmt.close();
                System.out.println("A new record has been inserted.");

            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  


        public void insertFilm(int id, String title, String releaseDate) {  
            String sql = "INSERT INTO film(film_id, film_titre, film_annee_production) VALUES(?,?,?)";  
       
            try{  
                Connection conn = this.connect();  
                PreparedStatement pstmt = conn.prepareStatement(sql);  
                pstmt.setInt(1, id);  
                pstmt.setString(2, title);
                pstmt.setString(3, releaseDate);   
                pstmt.executeUpdate();  

                pstmt.close();
                System.out.println("A new record has been inserted.");

            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  



        public void insertParticipant(String name, String surname, String birthday, int filmId, String job) {  
            String sql = "INSERT INTO participant(professionel_nom, professionel_prenom, professionel_date_naissance, film_id, participant_profession) VALUES(?,?,?,?,?)";  
       
            try{  
                Connection conn = this.connect();  
                PreparedStatement pstmt = conn.prepareStatement(sql);  
                pstmt.setString(1, name);  
                pstmt.setString(2, surname);
                pstmt.setString(3, birthday);  
                pstmt.setInt(4, filmId);
                pstmt.setString(5, job);   
                pstmt.executeUpdate();  

                pstmt.close();
                System.out.println("A new record has been inserted.");

            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  



        // public void insertAbonne(String pseudo, String name, String surname, String mail, String birthday, String password) {  
        //     String sql = "INSERT INTO abonne(abonne_pseudo, abonne_nom, abonne_prenom, abonne_adresse_mail, abonne_date_naissance, abonne_mot_de_passe) VALUES(?,?,?,?,?,?)";  
       
        //     try{  
        //         Connection conn = this.connect();  
        //         PreparedStatement pstmt = conn.prepareStatement(sql);  
        //         pstmt.setString(1, pseudo);  
        //         pstmt.setString(2, name);
        //         pstmt.setString(3, surname);  
        //         pstmt.setString(4, mail);
        //         pstmt.setString(5, birthday);  
        //         pstmt.setString(6, password);  
        //         pstmt.executeUpdate();  
        //         System.out.println("A new record has been inserted.");

        //     } catch (SQLException e) {  
        //         System.out.println(e.getMessage());  
        //     }  
        // }  
       
        public static void main(String[] args) {  
       
            InsertRecords app = new InsertRecords();   
            app.insertAbonne("JIJI","Jean", "David", "Jean.david@gmail.com", "09/12/1982", "JIJI82");  
            app.insertCinema(1, "Les 7 nefs", "Montelimar");
            app.insertProfessionel("George", "Cooney", "06/05/1961");
            app.insertFilm(1, "Ocean Eleven", "2001");
            app.insertParticipant("George", "Clooney", "06/05/1961", 1, "realisateur");
            app.insertParticipant("George", "Cooney", "06/05/1961", 1, "realisateur");
        }  
       
    }  