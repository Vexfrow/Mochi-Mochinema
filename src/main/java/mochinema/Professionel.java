package mochinema;

public class Professionel {


    private String nom;
    private String prenom;
    private String dateNaissance;


    public Professionel(String nom, String prenom, String dateNaissance){
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }


    public String getNom(){
        return nom;
    }

    public String getPrenom(){
        return prenom;
    }

    public String getDateNaissance(){
        return dateNaissance;
    }
    
}
