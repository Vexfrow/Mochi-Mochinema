package mochinema;

public class Professionnel {


    private String nom;
    private String prenom;
    private Date dateNaissance;


    public Professionnel(String nom, String prenom, Date dateNaissance){
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }


    public String getNom(){
        return nom;
    }

    public String getPrenom(){
        return nom;
    }

    public Date getDateNaissance(){
        return dateNaissance;
    }
    
}
