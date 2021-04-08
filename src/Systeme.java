import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Systeme {
    private static Acheteur acheteur=null;
    private static Vendeur vendeur=null;
    private static boolean connect = false;
    private static String type_usr=null;


    public static void setVendeur(Vendeur v){
        System.out.println("set vendeur : "+v);
        vendeur=v;
        type_usr="vendeur";
        connect=true;
        ToolBar.getToolBar().addMyButton();
        Memory.getMemory().addUser(v);
        PageArticle.getPage().addMyButton();
        PageUser.getPage().addMyButton();
    }

    public static Acheteur getAcheteur(){
        return acheteur;
    }
    public static Vendeur getVendeur(){
        return vendeur;
    }
    public static boolean isConnect(){
        return connect;
    }
    public static String getType_usr(){
        return type_usr;
    }



    public static void authenticate() {
        Interaction.affiche_Authenticate();
    }

    public static boolean login(String email, String password) {

	    //System.out.println("email : "+email+" mdp : "+password);

	    if(Memory.getMemory().existEmail(email)) {
	    	Utilisateur u= Memory.getMemory().connexion(email,password);
	    	if(u!=null){
	    	    //System.out.println(u);
	    		if(u instanceof Acheteur && ! (u instanceof Vendeur)){
	    			acheteur = (Acheteur)u; type_usr="acheteur";
	    		}
	    		if(u instanceof Vendeur){
	    			vendeur = (Vendeur)u; type_usr="vendeur";
	    		}
	    	connect = true;
            ToolBar.getToolBar().addMyButton();
            PageArticle.getPage().addMyButton();
            PageUser.getPage().addMyButton();
	    	Fenetre.backToHome();
	    	return true;
	    	}else{
	    		Fenetre.affichePopup(new String[]{"mot de passe ne correspond pas"});
	    		return false;
	    	}
	    }else{
	    	Fenetre.affichePopup(new String[]{"email non existant"});
	    	return false;
	    }
    }




    public static void signin() {
        Interaction.affiche_Signin();
    }

    public static boolean signinAcheteur(String nom, String prenom, String email, String password ) {

	    boolean[] check= checkAcheteurData(email, password);
	    if(check[0] && check[1]) {
            acheteur = new Acheteur(email, password, nom, prenom);
            connect = true;
            type_usr="acheteur";

            ToolBar.getToolBar().addMyButton();
            PageArticle.getPage().addMyButton();
            PageUser.getPage().addMyButton();
            Fenetre.backToHome();

            return true;

	    }else
	    	return  false;


    }

    public static boolean[] checkAcheteurData(String email, String mdp){
        boolean[] res = {true, true};
        String[] message = new String[3];
        //email
        if(! Outils.isEmail(email) ){
            res[0]=false;
            for(int i=0; i<message.length; i++){
                if(message[i]==null) message[i]="veuillez retaper un email au bon format [ exemple aaa@lip6.fr ]";
            }
        }
        if(Memory.getMemory().existEmail(email)) {
            res[0]=false;
            for(int i=0; i<message.length; i++){
                if(message[i]==null) message[i]="email déjà utilisé";
            }
        }

        //password
        if(! Outils.isPassword(mdp)){

            res[1]=false;
            for(int i=0; i<message.length; i++){
                if(message[i]==null) message[i]="veuillez taper un mot de passe au bon format  [ minimum 8 caractère : 1 maj | 1 min | 1 chiffre | 1 caractère spécial ]";
            }
        }

        if( ! res[0] || ! res[1] )
            Fenetre.affichePopup(message);

        return res;
    }





    public static boolean signinVendeur(String nom, String prenom, String email, String password, String numero, String rib ) {

	    boolean[] check=checkVendeurData(email, password, numero);
	    if(check[0] && check[1] && check[2]){

            vendeur = new Vendeur(email, password, nom, prenom, numero, rib);
            connect = true;
            type_usr="vendeur";

            ToolBar.getToolBar().addMyButton();
            PageArticle.getPage().addMyButton();
            PageUser.getPage().addMyButton();
            Fenetre.backToHome();

            return true;
        }
	    else
	    	return false;

    }

    public static boolean[] checkVendeurData(String email, String mdp, String numero){
	    String[] message = new String[4];
	    boolean[] res = {true, true, true};
        //email
        if(! Outils.isEmail(email) ){
			res[0]=false;
			for(int i=0; i<message.length; i++){
				if(message[i]==null) message[i]="veuillez retaper un email au bon format [ exemple aaa@lip6.fr ]";
			}
        }
        if(Memory.getMemory().existEmail(email)) {
			res[0]=false;
			for(int i=0; i<message.length; i++){
				if(message[i]==null) message[i]="email déjà utilisé";
			}
        }

        //password
        if(! Outils.isPassword(mdp)){
			res[1]=false;
			for(int i=0; i<message.length; i++){
				if(message[i]==null) message[i]="veuillez taper un mot de passe au bon format  [ minimum 8 caractère : 1 maj | 1 min | 1 chiffre | 1 caractère spécial ]";
			}
        }

        if(! Outils.isNumero(numero)){
			res[2]=false;
			for(int i=0; i<message.length; i++){
				if(message[i]==null) message[i]="numéro pas au bon format";
			}
        }

        if(!res[1] || !res[2] || !res[0] )
		    Fenetre.affichePopup(message);
        return res;
    }





    public static void profil() {
        if(type_usr.equals("acheteur"))
	    	Interaction.affiche_Profil(acheteur);
	    if(type_usr.equals("vendeur"))
	    	Interaction.affiche_Profil(vendeur);
    }

    public static void disconnect(boolean boo) {
        acheteur=null;
        vendeur=null;
        connect=false;
        type_usr="";
        ToolBar.getToolBar().addMyButton();
        if(boo)
            Fenetre.backToHome();

    }

    public static void deleteAccount() {
        if(type_usr.equals("acheteur"))
	    	Memory.getMemory().removeUser(acheteur);
	    if(type_usr.equals("vendeur"))
	    	Memory.getMemory().removeUser(vendeur);
	    disconnect(true);
    }


    private static void remplirSite(){
        //String[] cat = {"meuble","accessoire","cuisine","livre","jeux","téléphone"};
        String[] loc = {"Paris","Marseille","Grigny","Corbeil","Gentilly","Kremlin Bicetre"};
        Vendeur nidhal = new Vendeur("nidhal", "root", "Teyeb", "Nidhal", "0673028372", "ribNidhal" );
        Vendeur chiffa= new Vendeur("chiffa","root", "Wehbi", "Chiffa", "0712938374", "ribChiffa" );
        Vendeur nahidath= new Vendeur("nahidath","root", "Gassa", "Nahidath", "0796953701", "ribNahidath" );
        Vendeur ugo= new Vendeur("ugo","root", "Moutymbo", "Ugo", "0619203683", "ribUgo" );
        Vendeur sinem= new Vendeur("sinem","root", "Oruc", "Sinem", "0774209282", "ribSinem" );
        Vendeur rizvan= new Vendeur("rizvan","root", "Kandjee", "Rizvan", "0619283729", "ribRizvan" );

        /*
        type_usr="vendeur";
        vendeur=nidhal;
        connect=true;
        */

        Article a0,a1,a2,a3,a4,a5,a6,a7,a8,a9,a10, a11,a12,a13,a14;

        a0 = new Article( "Sac Chanel", "neuf", loc[(int)(Math.random()* loc.length)], "Accessoire", 1009.99, new Date());
        a0.decrire("Le 2.55 est un sac à main pour femme de la maison de haute couture française Chanel.\nCouleur : Noir");

        a1 = new Article( "Pantalon rouge", "neuf", loc[(int)(Math.random()* loc.length)], "Vêtement", 68.89, new Date());
        a1.decrire("Pantalon Rouge H&M pour femme.\nTaille : 38");

        a2 = new ArticleParEnchere( "Table Antique", "D'occasion - Très bon état", loc[(int)(Math.random()* loc.length)], "Meuble", 2255.00, new Date(), 7);
        a2.decrire("Table Antique fabriquée en 1977.\nLongueur : 1.80 m\nLargeur : 0.90 m");

        a3 = new Article( "Bedding Rose", "neuf", loc[(int)(Math.random()* loc.length)], "Maison", 260.10, new Date());
        a3.decrire("Bedding Rose pour un lit King size.\nLongueur : 1.80 m\nLargeur : 1.70 m");

        a4 = new Article( "Pantalon Beige", "neuf", loc[(int)(Math.random()* loc.length)], "Vêtement", 78.89, new Date());
        a4.decrire("Pantalon Beige H&M pour femme.\nTaille : 42");

        a5 = new Article( "Palette", "neuf", loc[(int)(Math.random()* loc.length)], "MakeUp", 46.90, new Date());
        a5.decrire("Palette (18 shadows) Huda Beauty");

        a6 = new ArticleParEnchere( "Nike Jordan", "neuf", loc[(int)(Math.random()* loc.length)], "Chaussure", 205.60, new Date(), 2);
        a6.decrire("Nike Jordan en bleue unisex.\nTaille : 41");

        a7 = new Article( "Coque iPhone", "neuf", loc[(int)(Math.random()* loc.length)], "Téléphone", 10.19, new Date());
        a7.decrire("Coque iPhone 6 en plastique.");

        a8 = new Article( "HeadSet", "neuf", loc[(int)(Math.random()* loc.length)], "Téléphone", 56.24, new Date());
        a8.decrire("HeadSet disponible en 4 couleurs differentes.");

        a9 = new ArticleParEnchere( "Hoodie", "neuf", loc[(int)(Math.random()* loc.length)], "Vêtement", 39.99, new Date(), 9);
        a9.decrire("Hoodie rose de la nouvelle collection d'hiver ZARA pour femme.\nTaille : 40");

        a10 = new Article( "Outils de Cuisine", "neuf", loc[(int)(Math.random()* loc.length)], "Cuisine", 19.99, new Date());
        a10.decrire("Collection 5 pièces en boie de chez ManoMano.");

        a11 = new Article( "T-shirt", "neuf", loc[(int)(Math.random()* loc.length)], "Vêtement", 32.76, new Date());
        a11.decrire("T-shirt Bleue pour Homme de la collection 2019/2020 chez POLO RALPH LAUREN.");

        a12 = new ArticleParEnchere( "Blender", "neuf", loc[(int)(Math.random()* loc.length)], "Cuisine", 109.99, new Date(), 5);
        a12.decrire("Blender 600 Watt de chez Moulinex.");

        a13 = new Article( "Robe Rouge", "neuf", loc[(int)(Math.random()* loc.length)], "Vêtement", 17.89, new Date());
        a13.decrire("Robe Rouge manches courtes H&M pour femme.\nTaille : 40");

        a14 = new ArticleParEnchere( "Armoire", "D'occasion - Bon état", loc[(int)(Math.random()* loc.length)], "Meuble", 506.89, new Date(), 8);
        a14.decrire("Armoire en bois de hêtre.\nLongueur : 1.80 m\nLargeur :  0.90 m");

        a0.ajouterMotsCles("Sac"); a0.ajouterMotsCles("Marque"); a0.ajouterMotsCles("Produit luxe");
        a1.ajouterMotsCles("Pantalon"); a1.ajouterMotsCles("H&M"); a1.ajouterMotsCles("Rouge");
        a2.ajouterMotsCles("Antique"); a2.ajouterMotsCles("Table"); a2.ajouterMotsCles("Bois");
        a4.ajouterMotsCles("Pantalon"); a4.ajouterMotsCles("Mode Femme"); a4.ajouterMotsCles("Beige");
        a3.ajouterMotsCles("Rose"); a3.ajouterMotsCles("Maison"); a3.ajouterMotsCles("Couverture lit");
        a5.ajouterMotsCles("Palette"); a5.ajouterMotsCles("Couleur nude"); a5.ajouterMotsCles("Huda Beauty");
        a6.ajouterMotsCles("Nike"); a6.ajouterMotsCles("Bleue"); a6.ajouterMotsCles("Sport");
        a7.ajouterMotsCles("iPhone 6"); a7.ajouterMotsCles("Coque"); a7.ajouterMotsCles("Multicolore");
        a8.ajouterMotsCles("Jeux"); a8.ajouterMotsCles("Technologie"); a8.ajouterMotsCles("Couleur noir");
        a9.ajouterMotsCles("Mode Femme"); a9.ajouterMotsCles("Rose"); a9.ajouterMotsCles("Hoodie");
        a10.ajouterMotsCles("Cuisine"); a10.ajouterMotsCles("Ensemble de cuillères");
        a11.ajouterMotsCles("Polo"); a11.ajouterMotsCles("T-shirt"); a11.ajouterMotsCles("Bleue");
        a12.ajouterMotsCles("Blender"); a12.ajouterMotsCles("Moulinex"); a12.ajouterMotsCles("Maison");
        a13.ajouterMotsCles("Robe"); a13.ajouterMotsCles("H&M"); a13.ajouterMotsCles("Rouge");
        a14.ajouterMotsCles("Armoire"); a14.ajouterMotsCles("Bois de hêtre"); a14.ajouterMotsCles("Maison");

        nidhal.posterArticleCreateur(a6);
        nidhal.posterArticleCreateur(a11);
        chiffa.posterArticleCreateur(a2);
        sinem.posterArticleCreateur(a3);
        ugo.posterArticleCreateur(a4);
        rizvan.posterArticleCreateur(a5);
        nahidath.posterArticleCreateur(a0);
        nahidath.posterArticleCreateur(a7);
        nahidath.posterArticleCreateur(a8);
        chiffa.posterArticleCreateur(a9);
        ugo.posterArticleCreateur(a10);
        rizvan.posterArticleCreateur(a1);
        sinem.posterArticleCreateur(a12);
        sinem.posterArticleCreateur(a13);
        nidhal.posterArticleCreateur(a14);

        /*for(int i =0; i<15; i++){
            article = new Article( "Nike Jordan", "neuf", loc[(int)(Math.random()* loc.length)], Chaussures, Math.random()*100, new Date());
            article.decrire("hhahahahahh");
            nidhal.posterArticleCreateur(article);
        }*/
    }

    public static void majDuree(){
        for(int i=0; i<Memory.getMemory().getArticles().size(); i++){
            Article a = Memory.getMemory().getArticles().get(i);
            if(a instanceof ArticleParEnchere){
                ((ArticleParEnchere)a).diminueDuree();
            }
        }
    }

    public static void main(String[] args){
        remplirSite();

        Compteur compteur = new Compteur();
        compteur.start();

        Fenetre.getFrame().setVisible(true);

    }


}
