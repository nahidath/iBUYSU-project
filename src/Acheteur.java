
import java.util.ArrayList;
import java.util.HashSet;


public class Acheteur extends Utilisateur {
    private HashSet<Article> liste_article = new HashSet<>();

    public Acheteur(String email, String mdp, String nom, String prenom) {
        super(email,mdp,nom,prenom);
    }




    public void encherir(double prix) {
        ArticleParEnchere article = (ArticleParEnchere)PageArticle.getPage().getContenu();
	
	    //double prix = Interaction.getNombre();
        
	    if(prix > article.getPrix_actuel() ){
	    	article.updatePrix(prix, this);
	    } 

	    Fenetre.backToHome();
    }



    public void acheterArticle() {
        Article article = PageArticle.getPage().getContenu();
        article.setAcheteur(this);
	    Fenetre.backToHome();
    }


    public void articlesAchetes(){
        Interaction.affiche_ResultSearch(new ArrayList<Article>(liste_article) );
    }



    public void devenirVendeur(String rib, String num) {
        if(! Outils.isNumero(num)){
            Fenetre.affichePopup( new String[]{"numéro pas au bon format"} );
            Interaction.setBooboo(false);

            return;
        }
        String email=Systeme.getAcheteur().getEmail();
        String mdp = Systeme.getAcheteur().getMdp();
        String nom = Systeme.getAcheteur().getNom();
        String prenom = Systeme.getAcheteur().getPrenom();
    
        Systeme.deleteAccount();
    
        Vendeur v = new Vendeur(email, mdp, nom, prenom, num, rib );
        Systeme.setVendeur(v);
        //PageUser.getPage().addMyButton();
        Interaction.affiche_Profil(v);
    }




    public HashSet<Article> getListe_article() {
        return this.liste_article;
    }

}
