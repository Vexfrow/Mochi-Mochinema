package mochinema;

public class Professionel {


    private String nom;
    private String prenom;
    private Date dateNaissance;


    public Professionel(String nom, String prenom, Date dateNaissance){
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

    public Date getDateNaissance(){
        return dateNaissance;
    }
    
}
