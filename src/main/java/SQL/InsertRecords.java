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
                System.out.println("Un nouveau abonné a été rajouté.");

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
                System.out.println("Un nouveau cinéma a été rajouté.");

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
                System.out.println("Un nouveau professionel a été rajouté.");

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
                System.out.println("Un nouveau film a été rajouté.");

            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  


        public void insertPrix(int idCinema, int idFilm, int prix) {  
            String sql = "INSERT INTO prix(cinema_id, film_id, prix_prix) VALUES(?,?,?)";  
       
            try{  
                Connection conn = this.connect();  
                PreparedStatement pstmt = conn.prepareStatement(sql);  
                pstmt.setInt(1, idCinema);  
                pstmt.setInt(2, idFilm);
                pstmt.setInt(3, prix);   
                pstmt.executeUpdate();  

                pstmt.close();
                System.out.println("Un nouveau prix a été rajouté.");

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
                System.out.println("Un nouveau participant a été rajouté.");

            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  


        
        public void insertCritique(String pseudo, int filmID, String critique, int note) {
            String sql = "INSERT INTO critique(abonne_pseudo,film_id, critique_critique, critique_note) VALUES(?,?,?,?)";

            try{
                Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, pseudo);
                pstmt.setInt(2, filmID);
                pstmt.setString(3, critique);
                pstmt.setFloat(4, note); 
                pstmt.executeUpdate();

                pstmt.close();
                System.out.println("Une nouvelle critique a été rajoutée.");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


        public static void insertDonneeStart(){
            InsertRecords app = new InsertRecords();  
            
            try{
                app.insertAbonne("Invite", "Invite", "Invite", "Invite", "00/00/0000", "Invite");
                app.insertAbonne("CommeTuVeut", "Karim", "Longchamp", "karim.longchamp@gmail.com", "25/10/2009", "Clear4All!");
                app.insertAbonne("Kekw12", "Nicolas", "Redfield", "nicolas.redfield@outlook.com", "21/04/1985", "Litter47");
                app.insertAbonne( "Kameto", "Karim", "Benzema", "Kameto@gmail.com", "10/02/1993", "KamKam25");
                app.insertAbonne("Laink", "Iturralde", "Thomas", "WankilLaink@gmail.com", "8/01/1992", "TerraLove");

                app.insertCinema(1, "Les 7 nefs", "Montelimar");
                app.insertCinema(2, "Pathe", "Valence");
                app.insertCinema(3, "Le Palace", "Montélimar");
   
                app.insertFilm(1, "Ocean Eleven", "2001");
                app.insertFilm(2, "Moi Moche et Méchant", "2010");
                app.insertFilm(3, "Kaamelott", "2021");
                app.insertFilm(4, "Pacific Rim", "2013");
                app.insertFilm(5, "Pacific Rim : Uprising", "2018");
                app.insertFilm(6, "Moi Moche et Méchant 2", "2013");

                app.insertProfessionel("Clooney", "George", "06/05/1961");
                app.insertProfessionel("Carell", "Steve", "16/08/1962");
                app.insertProfessionel("Coffin", "Pierre", "01/11/1967");
                app.insertProfessionel("Astier", "Alexandre", "01/11/1967");
                app.insertProfessionel("Del Toro", "Guillermo", "09/08/1964");
                app.insertProfessionel("Roberts", "Julia", "28/10/1967");
                app.insertProfessionel("Pitt", "Brad", "18/12/1963");
                app.insertProfessionel("Kristen", "Wiig", "22/08/1973");
                app.insertProfessionel("Beth", "Jehnny", "24/12/1984");
                app.insertProfessionel("Pitiot", "Franck", "27/06/1964");
                app.insertProfessionel("Kikuchi", "Rinko", "06/01/1981");
                app.insertProfessionel("Elba", "Idris", "06/09/1972");
                app.insertProfessionel("Hunnam", "Charlie", "10/04/1980");
                app.insertProfessionel("DeKnight", "Steven", "08/04/1964");
                app.insertProfessionel("Boyega", "John", "17/03/1992");
                app.insertProfessionel("Eastwood", "Scott", "08/04/1986");
                
                app.insertParticipant("Clooney", "George", "06/05/1961", 1, "realisateur");
                app.insertParticipant("Clooney", "George", "6 mai 1961",1, "acteur");
                app.insertParticipant("Pitt", "Brad", "18/12/1963", 1, "acteur");
                app.insertParticipant("Roberts", "Julia", "28/10/1967",1, "acteur");
                app.insertParticipant("Coffin", "Pierre", "01/11/1967", 2, "realisateur");
                app.insertParticipant("Carell", "Steve", "16/08/1962", 2, "doubleur");
                app.insertParticipant("Kristen", "Wiig", "22/08/1973", 2, "doubleur");                 
                app.insertParticipant("Astier", "Alexandre", "01/11/1967", 3, "realisateur");
                app.insertParticipant("Beth", "Jehnny", "01/11/1967", 3, "acteur");
                app.insertParticipant("Pitiot", "Franck", "27/06/1964", 3, "acteur");                
                app.insertParticipant("Del Toro", "Guillermo", "09/08/1964", 4, "realisateur");
                app.insertParticipant("Kikuchi", "Rinko","06/01/1981", 4, "acteur");
                app.insertParticipant("Elba", "Idris", "06/09/1972", 4, "acteur");
                app.insertParticipant("Hunnam", "Charlie", "10/04/1980", 4, "acteur"); 
                app.insertParticipant("DeKnight", "Steven", "08/04/1964", 5, "realisateur");
                app.insertParticipant("Kikuchi", "Rinko","06/01/1981", 5, "acteur");
                app.insertParticipant("Boyega", "John", "17/03/1992", 5, "acteur");
                app.insertParticipant("Eastwood", "Scott", "08/04/1986", 5, "acteur");   
                app.insertParticipant("Coffin", "Pierre", "01/11/1967", 6, "realisateur");
                app.insertParticipant("Carell", "Steve", "16/08/1962", 6, "doubleur");
                app.insertParticipant("Kristen", "Wiig", "22/08/1973", 6, "doubleur");                

                app.insertCritique("Kekw12", 1, "J'adore trop. Tout est trop bien. Le son, les braquages c'est ouf", 9);
                app.insertCritique("Kameto", 1, "Ce film a un grand potentiel et je sens tout le talent de Goerge Clooney dans chaque scene d'actions", 9);
                app.insertCritique("CommeTuVeut", 1, "Meilleur film ever", 9);
                app.insertCritique("Kekw12", 2, "Trop drole Gru", 8);

                app.insertPrix(1,1,10);
                app.insertPrix(1,2,20);
                app.insertPrix(1,4,15);
                app.insertPrix(1,5,15);
                app.insertPrix(2,1,15);
                app.insertPrix(2,2,5);
                app.insertPrix(2,6,5);
                app.insertPrix(3,1,15);
                app.insertPrix(3,2,5);
                app.insertPrix(3,3,20);
                app.insertPrix(3,4,15);
                app.insertPrix(3,5,15);
                app.insertPrix(3,6,5);



            }catch(Exception e){
                System.out.println("Ah Zut, c'est enquiquinant tout de même");

            }

        }


       
        public static void main(String[] args) {  
            insertDonneeStart();
        }  
       
    }  
