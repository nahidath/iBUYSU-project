

public void encherir(){
	ArticleParEnchere article = (ArticleParEnchere)Page.getPage().getContenu();
	
	double prix = Interaction.getNombre();
	
	if(prix > article.getPrix_actuel() ){
		article.updatePrix(prix, this);
	} 

	Fenetre.backToHome();
}


public void acheter(article){
	article.setAcheteur(this);

	Fenetre.backToHome();
}

public void articlesAchetes(){
	Interaction.affiche_ResultSearch(liste_article);
}

public void devenirVendeur(String rib, String num){

	if(! Outils.isNumero(numero){
		Fenetre.affichePopup( {"numéro pas au bon format"} );
		Interaction.setBooboo(false);
	}
	String email=Systeme.getAcheteur().getEmail();
	String mdp = Systeme.getAcheteur().getMdp();
	String nom = Systeme.getAcheteur().getNom();
	String prenom = Systeme.getAcheteur().getPrenom();

	Syteme.deleteAccount();

	Vendeur v = new Vendeur(email, mdp, nom, prenom, num, rib );
	Systeme.setVendeur(v );
	Interaction.affiche_Profil(v);
}
