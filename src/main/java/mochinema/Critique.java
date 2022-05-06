package mochinema;

public class Critique {


    private String pseudo;
    private int idFilm;
    private String critique;
    private float note;



    public Critique(String pseudo, int idFilm, String critique, float note){

        this.pseudo = pseudo;
        this.idFilm = idFilm;
        this.critique = critique;
        this.note = note;
    }

    public String getPseudo(){
        return this.pseudo;
    }

    public int getFilm(){
        return this.idFilm;
    }

    public String getCritique(){
        return this.critique;
    }

    public float getNote(){
        return this.note;
    }

}
