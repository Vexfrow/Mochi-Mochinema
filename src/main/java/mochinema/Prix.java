package mochinema;

public class Prix {

    private Cinema cinema;
    private Film film;
    private int prix;

    public Prix(Cinema cinema, Film film, int prix){
        this.cinema = cinema;
        this.film = film;
        this.prix = prix;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public Film getFilm() {
        return film;
    }

    public int getPrix() {
        return prix;
    }


}
