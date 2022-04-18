package mochinema;

public class Film {

    private int ID;
    private String titre;
    private Date dateProduction;


    public Film(int ID, String titre, Date dateProduction){
        this.ID = ID;
        this.titre = titre;
        this.dateProduction = dateProduction;
    }



    public int getID(){
        return ID;
    }

    public String getTitre(){
        return titre;
    }

    public Date getDateProduction(){
        return dateProduction;
    }
    
}
