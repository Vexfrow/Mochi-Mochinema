package mochinema;

public class Film {

    private int ID;
    private String titre;
    private int anneeProduction;


    public Film(int ID, String titre, int anneeProduction){
        this.ID = ID;
        this.titre = titre;
        this.anneeProduction = anneeProduction;
    }



    public int getID(){
        return ID;
    }

    public String getTitre(){
        return titre;
    }

    public int getDateProduction(){
        return anneeProduction;
    }
    
}
