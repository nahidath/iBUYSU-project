import java.awt.*;
import java.util.*;
import java.util.List;


public class Vendeur extends Acheteur {
    private String numero_tel;

    private String rib;
    private int nombreNonValable = 0;

    public Vendeur(String email, String mdp, String nom, String prenom, String numero, String rib) {
        super(email,mdp,nom,prenom);
        this.numero_tel=numero;
        this.rib=rib;
    }

	public String getNumero_tel() {
		return numero_tel;
	}

	public String getRib() {
		return rib;
	}

	public int getNombreNonValable() {
		return nombreNonValable;
	}
	public void setNombreNonValable(){
    	nombreNonValable++;
	}


	public void posterArticleCreateur(Article article){
		Memory.getMemory().addArticle(article, article.getCategorie(), article.getMots_cles());
		Memory.getMemory().vendeur_article(article, this);
		String filename = article.getTitre()+".png";
		//System.out.println(filename);
		Image image = Imagerie.getImage("Article/"+filename);
		article.setImage(image);

	}

    public Article posterArticle(String titre, String etat, String localisation, String categorie, double prix, String description, String[] key, boolean enchere ,int duree) {
	    Article article;

	    HashSet<String> mots_cle = new HashSet<String>( Arrays.asList( key ) );

	    if(enchere){

	    	article = new ArticleParEnchere(titre, etat, localisation, categorie, prix, new Date() , duree );
	    }else{
	    	article = new Article(titre, etat, localisation, categorie, prix, new Date() );
	    }


	    Memory.getMemory().addArticle(article, categorie, mots_cle);
	    Memory.getMemory().vendeur_article(article, this);


	    for(String m : mots_cle){
	    	article.ajouterMotsCles(m);
	    }
	    article.decrire(description);


	    Interaction.affiche_Profil(this);

	    return article;

    }

    public void setImageArticle(Article article, String extension){
		String filename = article.getId()+"article."+extension;
		//System.out.println(filename);
		Image image = Imagerie.getImage("Article/"+filename);
		//Interaction.setTexte(filename);
		article.setImage(image);
	}


    public void retirerVente() {
		Article article = PageArticle.getPage().getContenu();
        Memory.getMemory().removeArticle(article);
		article.setRetirer();
	    Interaction.affiche_Profil(this);
    }

    public void accepterVente() {
		Article article = PageArticle.getPage().getContenu();
        Memory.getMemory().removeArticle(article);
	    /////////////////////////////////////////////////notifier l'acheteur de l'acceptation de la vente
	    article.getAcheteur().getListe_article().add(article);
	    article.setAcheter();
	    nombreNonValable--;
		ToolBar.getToolBar().addMyButton();
	    Interaction.affiche_Profil(this);
	    //System.out.println("vente acceptée");
    }

    public void refuserVente(){
		Article article = PageArticle.getPage().getContenu();
		article.resetValable();
		nombreNonValable--;
		ToolBar.getToolBar().addMyButton();
		Fenetre.getFenetre().refreshPage();
	}




    public void listeArticle() {
        HashSet<Article> liste = Memory.getMemory().articleParVendeur(this);
        if(liste!=null)
	    	Interaction.affiche_ResultSearch(new ArrayList<Article>(liste));
    }

    public void editArticle(Article article, String titre, double prix, String description) {

	    article.setTitre(titre);
	    article.setPrix(prix);
	    article.decrire(description);
    }




    public void modifierInfos(String s, String tel, String r ) {
        Interaction.affiche_ModifInfos();

	    //String s = Interaction.getTexte();

	    if( Outils.isEmail(s) && ! Memory.getMemory().existEmail(s) )
            	this.email=s;
        else{
            Fenetre.affichePopup(new String[]{"veuillez retaper un email au bon format [ exemple aaa@lip6.fr ]"});
	    	Interaction.setBooboo(false);
	    }
	    //String tel= Interaction.getTexte();

	    if(Outils.isNumero(tel) )
	    	this.numero_tel=tel;
	    else{
	    	Fenetre.affichePopup(new String[] { "numéro pas au bon format" } );
	    	Interaction.setBooboo(false);
	    }
	    //String r = Interaction.getTexte();
	    if(! r.equals("") )
	    	this.rib=r;

	    Interaction.affiche_Profil( this );
    }

}
