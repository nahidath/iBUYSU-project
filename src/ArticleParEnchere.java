import java.util.Date;
import java.util.List;


public class ArticleParEnchere extends Article {
    private double prix_actuel;

    private int duree;

    private Acheteur acheteur_actuel;

    public ArticleParEnchere(final String titre, final String etat, final String localisation, final String categorie, final double prix, final Date date, final int duree) {
        super(titre,etat,localisation,categorie,prix,date);
        this.duree=duree*24*60;

        prix_actuel=prix;
    }

    public void updatePrix(final double prix, final Acheteur acheteur) {
        prix_actuel=prix;
        acheteur_actuel=acheteur;
    }

    public int getDuree() {
        return duree;
    }

    public Acheteur getAcheteur_actuel() {
        return this.acheteur_actuel;
    }

    public double getPrix_actuel() {
        return this.prix_actuel;
    }

    public void diminueDuree(){
        if(duree  > 0){
            duree--;
            if( duree == 0 ){
                this.setAcheteur(acheteur_actuel);
            }
            if( PageArticle.getPage().getContenu() == this ) {
                PageArticle.getPage().repaint();
            }
        }
    }

    public int[] dureeFormat(){
        int[] res = new int[3];
        res[0]= duree / (24 * 60);
        res[1]=  (duree % (24 * 60)) / 60 ;
        res[2] = (duree % (24 * 60)) % 60;

        return res;

    }

}
