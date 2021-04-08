import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;


public abstract class Utilisateur {

    protected String email;
    private String mdp;

    private String nom;
    private String prenom;
    private int evaluation;
    private int nb_eval=0;

    public Utilisateur(String email, String mdp, String nom, String prenom){
        this.email=email;
        this.mdp=mdp;
        this.nom=nom;
        this.prenom=prenom;
       

        Memory.getMemory().addUser(this);
    }

    /////////////////////////////////////////////////////////
    public String getEmail() {
        return email;
    }
    public String getMdp() {
        return mdp;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setEvaluation(int evaluation) {
        nb_eval++;
        this.evaluation+=evaluation/nb_eval ;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public int getNb_eval() {
        return nb_eval;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "email='" + email + '\'' +
                ", mdp='" + mdp + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", evaluation=" + evaluation +
                '}';
    }

    //////////////////////////////////////////////////////

    public static void naviguerMotsCles(String[] mots_cles){
        HashSet<Article> liste = Memory.getMemory().rechercheArticleMotsCles(mots_cles);
        Interaction.affiche_ResultSearch(new ArrayList<Article>(liste));
    }
    
    public static void naviguerCategorie(String categorie){
        HashSet<Article> liste = Memory.getMemory().rechercheArticleCategorie(categorie);
        Interaction.affiche_ResultSearch(new ArrayList<Article>(liste));
    }

    public void evaluerUsr(Article article, String note, String com){
        Utilisateur a_evalue;
        if(Systeme.getType_usr().equals("acheteur")){
            a_evalue = Memory.getMemory().articleQuelVendeur(article);
        }else{
            if( Memory.getMemory().articleQuelVendeur(article) == this ){
                a_evalue = article.getAcheteur();
            }else{
                a_evalue = Memory.getMemory().articleQuelVendeur(article);
            }

        }

    
        Memory.getMemory().evaluation_utilisateur(this, a_evalue, note, com);
        if(Systeme.getType_usr().equals("acheteur"))
            article.setEvaluationAch();
        if(Systeme.getType_usr().equals("vendeur")){
            if( Memory.getMemory().articleQuelVendeur(article) == Systeme.getVendeur() )
                article.setEvaluationVen();
            if( Memory.getMemory().articleQuelVendeur(article) != Systeme.getVendeur() )
                article.setEvaluationAch();
        }
        Fenetre.getFenetre().refreshPage();
        
    }

    public void modifierPassword(String old_p, String new_p) {
        //Interaction.affiche_ModifPassword();
	
	    //String old_p = Interaction.getTexte();
	    //String new_p = Interaction.getTexte();
        
	    if( old_p.equals(this.mdp)){
	    	if(Outils.isPassword(new_p)){
	    		this.mdp=new_p;
            
	    		Interaction.affiche_Profil( this );
	    	}else{
	    		Fenetre.affichePopup(new String[] {"veuillez taper un mot de passe au bon format  [ minimum 8 caractère : 1 maj | 1 min | 1 chiffre | 1 caractère spécial ]"} );
	    		//Interaction.setBooboo(false);
	    	}			
	    }else{
	    	Fenetre.affichePopup( new String[]{"le mot de passe ne correspond pas"} );
	    	//Interaction.setBooboo(false);
	    }
    }

    public void modifierInfos(String s) {
        //Interaction.affiche_ModifInfos();
	
	    //String s = Interaction.getTexte();
        
	    if( Outils.isEmail(s) && ! Memory.getMemory().existEmail(s) ){
	    		this.email=s;
        
	    		Interaction.affiche_Profil( this );
	    }else{
	    	Fenetre.affichePopup(new String[] { "veuillez retaper un email au bon format [ exemple aaa@lip6.fr ]" } );
	    	//Interaction.setBooboo(false);
	    }  
    }


}
