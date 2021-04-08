

_________________________________________


public static void affiche_ResultSearch(HashSet<Article> liste){
	PageResultSearch.getPage().setContenu(liste);
	Fenetre.setContenu(PageResultSearch.getPage());
	Fenetre.setPage_actuelle("result search");
}


public static void affiche_Authenticate(){
	Fenetre.setContenu(PageAuthenticate.getPage());
	Fenetre.setPage_actuelle("authenticate");
}

public static void affiche_Login(){
	Fenetre.setContenu(PageLogin.getPage());
	Fenetre.setPage_actuelle("login");
}

public static void affiche_Signin(){
	Fenetre.setContenu(PageSignin.getPage());
	Fenetre.setPage_actuelle("signin");
}

public static void affiche_SigninAch(){
	Fenetre.setContenu(PageSigninAcheteur.getPage());
	Fenetre.setPage_actuelle("signin acheteur");
}

public static void affiche_SigninVend(){
	Fenetre.setContenu(PageSigninVendeur.getPage());
	Fenetre.setPage_actuelle("signin vendeur");
}

public static void affiche_Profil(Utilisateur u){
	PageUser.getPage().setContenu(u);
	Fenetre.setContenu(PageUser.getPage());
	Fenetre.setPage_actuelle("profil");
}

public static void affiche_ModifInfos(){
	//Fenetre.setContenu(PageInfosCompte.getPage());
	Fenetre.setPage_actuelle("modif infos");
}

public static void affiche_ModifPassword(){
	Fenetre.setPage_actuelle("modif password");
}

public static void affiche_Article(Article article){
	PageArticle.getPage().setContenu(article);
	Fenetre.setContenu(PageArticle.getPage());
	Fenetre.setPage_actuelle("article");
}

public static void affiche_Enchere(ArticleParEnchere article){
	Fenetre.setPage_actuelle("enchere");
}

public static void affiche_Poster(){
	Fenetre.setContenu(PagePoster.getPage());
	Fenetre.setPage_actuelle("poster");
}

public static void affiche_edit(){
	Fenetre.setPage_actuelle("editer");
}
___________________________________________

private static String texte=null;

public static void setTexte(String s){
	texte=s;
}

public static String getTexte(){
	while(texte==null){ Outils.attente(1); }
	String res=texte;
	texte=null;
	return res; 
}

______________________________________________

private static double nombre;
private static boolean test_nombre=false;

public static void setNombre(double d){
	nombre = d;
	test_nombre=true;
}

public static double getNombre(){
	while(! test_nombre){ Outils.attente(1); }
	test_nombre=false;
	return nombre;
}

_____________________________________________

private static int entier;
private static boolean test_entier=false;

public static void setEntier(int i){
	entier=i;
	test_entier=true;
}

public static int getEntier(){
	while(! test_entier){ Outils.attente(1); }
	test_entier=false;
	return entier;
}

____________________________________________

private static String[] tab;
private static boolean test_tab=false;

public static void setTab(String[] t){
	tab=t;
	test_tab=true;
}

public static String[] getTab(){
	while(! test_tab){ Outils.attente(1); }
	test_tab=false;
	return tab;
}

____________________________________________

private static boolean enEnchere=false;
private static boolean test_enchere=false;

public static void setEnEnchere(boolean b){
	enEnchere=b;
	test_enchere=true;
}

public static String isEnEnchere(){
	while(! test_enchere){ Outils.attente(1); }
	test_enchere=false;
	return enEnchere; 
}

___________________________________________

private static Image image=null;
private static boolean test_image=false;

public static void setImage(Image i){
	image=i;
	test_image=true;
}

public static Image getImage(){
	while(! test_image){ Outils.attente(1); }
	test_image=false;
	return image;
}

____________________________________________

private static boolean booboo=false;
private static boolean test_boo=false;

public static void setBooboo(boolean b){
	booboo=b;
	test_boo=true;
}

public static boolean getBooboo(){
	while(! test_boo){ Outils.attente(1); }
	test_boo=false;
	return booboo;
}

_________________________________________













