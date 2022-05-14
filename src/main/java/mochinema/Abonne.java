package mochinema;

public class Abonne {

    private String pseudo;
    private String nom;
    private String prenom;
    private String adresseMail;
    private String motDePasse;
    private String dateDeNaissance;


    public Abonne(String pseudo, String nom, String prenom, String adresseMail, String motDePasse, String dateDeNaissance){
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.adresseMail = adresseMail;
        this.motDePasse = motDePasse;
        this.dateDeNaissance = dateDeNaissance;
    }



    public String getPseudo(){
        return pseudo;
    }

    public String getNom(){
        return nom;
    }
    


    public String getMail(){
        return adresseMail;
    }

    public String getMDP(){
        return motDePasse;
    }


    public String getDateNaissance(){
        return dateDeNaissance;
    }

    public String getPrenom(){
        return prenom;
    }
    
    
}
