package mochinema;

public class Cinema {

    private int idCinema;
    private String nomCinema;
    private String villeCinema;



    public Cinema(int id, String nom, String ville){
        idCinema = id;
        nomCinema = nom;
        villeCinema = ville;
    }


    public int getId() {
        return idCinema;
    }


    public String getNom() {
        return nomCinema;
    }


    public String getVille() {
        return villeCinema;
    }



    

}
