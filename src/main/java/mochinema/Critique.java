public class Critique {


    private String pseudo;
    private String titreFilm;
    private String critique;
    private float note;



    public Critique(String pseudo, String titreFilm, String critique, float note){

        this.pseudo = pseudo;
        this.titreFilm = titreFilm;
        this.critique = critique;
        this.note = note;
    }

    public String getPseudo(){
        return this.pseudo;
    }

    public String getFilm(){
        return this.titreFilm;
    }

    public String getCritique(){
        return this.critique;
    }

    public String getNote(){
        return this.note;
    }

}
