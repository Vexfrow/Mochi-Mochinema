package mochinema;

public class Date {
    
    private int jour;
    private int mois;
    private int annee;

    
    public Date(String date){
        decomposerDate(date);
    }


    /*
        Prend une date avec le format "JJ/MM/YYYY" et la décompose en jours, mois, années
    */
    private void decomposerDate(String date){
        jour = Character.getNumericValue(date.charAt(0))*10 +  Character.getNumericValue(date.charAt(1));
        mois = Character.getNumericValue(date.charAt(3))*10 +  Character.getNumericValue(date.charAt(4));
        annee = Character.getNumericValue(date.charAt(6))*1000 +  Character.getNumericValue(date.charAt(7))*100 +Character.getNumericValue(date.charAt(8))*10 + Character.getNumericValue(date.charAt(9));
    }

    public int getJour(){
        return jour;
    }


    public int getMois(){
        return mois;
    }

    public int getAnnee(){
        return annee;
    }


    /*
        Renvoie vrai si la date rentré en paramètre est la même que celle actuel
    */

    public boolean egale(Date d){
        return(jour == d.jour && mois == d.mois && annee == d.annee);
    }


    /*
        Renvoie vrai si la date rentré en paramètre est plus grande à la date actuel
    */

    public boolean superieur(Date d){
        return((jour >= d.jour && mois == d.mois && annee == d.annee) || (mois >= d.mois && annee == d.annee) || (annee >= d.annee));
    }


    public String toString(){
        String t = "" + jour + '/'+mois +'/'+annee;
        return t;
    }

}
