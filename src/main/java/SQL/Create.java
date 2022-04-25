package SQL;

import java.sql.Connection;
    import java.sql.DatabaseMetaData;  
    import java.sql.DriverManager;  
    import java.sql.SQLException;  
       
    public class Create {  

        public static String urlDatabase = "jdbc:sqlite:./Mochi-Mochinéma.db";
      
        public static void createNewDatabase() {  
            
            try {  
                Connection conn = DriverManager.getConnection(urlDatabase);  
                if (conn != null) {  
                    DatabaseMetaData meta = conn.getMetaData();  
                    System.out.println("The driver name is " + meta.getDriverName());  
                    System.out.println("A new database has been created.");  
                    System.out.println("URL = " + meta.getURL());  
                }  
       
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  
      
        public static void main(String[] args) {  
            createNewDatabase();  
        }  
    }  