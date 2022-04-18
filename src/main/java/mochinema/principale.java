package mochinema;

import SQL.*;

public class principale {


    /*
        Classe principale qui s'occupe d'initialiser la base de donnée et de la remplir avec les données déjà présentes
    */

    public static void main(String[] args){
        Connect.main(args);
        Create.main(args);
        CreateTable.main(args);
        //InsertRecords.main(args);
        SelectRecords.main(args);

    }
    
}
