





public static void backToHome(){
	setContenu( PageAccueil.getPage() );
	setPage_actuelle("accueil");
}

public static void setContenu(Container contenu){
	frame.remove(centre);
	frame.add(contenu, BorderLayout.CENTER);
	frame.setVisible(true);
	centre=contenu;
} 

public static void affichePopup(String[] message){
	JOptionPane.showConfirmDialog(this.centre, message, "ERROR", JOptionPane.OK_CANCEL_OPTION);
}
___________________________________________________________________________





keyPressed(e){
	if(page_actuelle.equals("result search") ){
	
	}
	
}






mousePressed(e){

	
	
}





actionPerformed(ActionEvent e){
	Object source=e.getSource();
////////tool bar
	if( source == ToolBar.getToolBar().getLogo() )
		backToHome();		
	
	if( source == ToolBar.getToolBar().getCategorie() ){
		String selected = (String)ToolBar.getCategorie().getSelectedItem();
		Utilisateur.naviguerCategorie(selected);
	}
	
	if( source == ToolBar.getToolBar().getSearch() ){
		String texte = ToolBar.getToolBar().getMots_cles().getText();
		String[] mots = texte.split(";");
		Utilisateur.naviguerMotsCles(mots);	
	}
	
	if( source == ToolBar.getToolBar().isConnect() ){
		Systeme.authenticate();
	}
	
	if( source == ToolBar.getToolBar().getProfil() ){
		Systeme..profil();
	}
	
/////////////////
	if(page_actuelle.equals("result search") ){		
		if(source == PageResultSearch.getPage().getListe_tri() ){
			String selected = (String)PageResultSearch.getPage().getListe_tri().getSelectedItem();
			if(! selected.equals(PageResultSearch.getPage().getTri())){
				PageResultSearch.getPage().setContenu( Outils.trierListeArticle( PageResultSearch.getPage().getContenu(), selected ));
				PageResultSearch.getPage().setTri(selected);
			}	
		}
	}
	
	
	
	
	if(page_actuelle.equals("authenticate"){
		if(source == PageAuthenticate.getPage().getLogin(){
			Systeme.login();
		}
		
		if(source == PageAuthenticate.getPage().getSignin(){
			Systeme.signin();
		}
	}
	
	
	
	
	if(page_actuelle.equals("login"){
		do{
			JTextField email = new JTextField();
			JTextField pwd= new JTextField();
			Object[] message = {"email:", email,"password:", pwd};
			
			int option = JOptionPane.showConfirmDialog(this.centre, message, "login", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				Interaction.setTexte( email.getText() );
				Outils.attente(2);
				Interaction.setTexte( pwd.getText() );
			}else{
				backToHome();
			}	
		}while(! Interaction.getBooboo());			
	}
	
	
	
	
	if(page_actuelle.equals("signin"){
		if(source == PageSignin.getPage().getVendeur() )
			Systeme.signinVendeur();
			
		if(source == PageSignin.getPage().getAcheteur() )
			Systeme.signinAcheteur();
	}
	
	
	
	
	if(page_acttuelle.equals("signin acheteur"){
		if(source == PageSigninAcheteur.getPage().getSubmit(){
			do{
				String nom = PageSigninAcheteur.getPage().getNom().getText();
				Interaction.setTexte(nom);
				Outils.attente(2);
				
				String prenom = PageSigninAcheteur.getPage().getPrenom().getText();
				Interaction.setTexte(prenom);
				Outils.attente(2);
				
				String email = PageSigninAcheteur.getPage().getEmail().getText();
				Interaction.setTexte(email);
				Outils.attente(2);
				
				String password = PageSigninAcheteur.getPage().getPassword().getText();
				Interaction.setTexte(password);
			}while( ! Interaction.getBooboo() );
		}
	}
	
	
	
	
	if(page_actuelle.equals("signin vendeur"){
		if(source == PageSigninVendeur.getPage().getSubmit(){
			do{
				String nom = PageSigninVendeur.getPage().getNom().getText();
				Interaction.setTexte(nom);
				Outils.attente(2);
				
				String prenom = PageSigninVendeur.getPage().getPrenom().getText();
				Interaction.setTexte(prenom);
				Outils.attente(2);
				
				String email = PageSigninVendeur.getPage().getEmail().getText();
				Interaction.setTexte(email);
				Outils.attente(2);
				
				String password = PageSigninVendeur.getPage().getPassword().getText();
				Interaction.setTexte(password);
				Outils.attente(2);
				
				String tel = PageSigninVendeur.getPage().getTelephone().getText();
				Interaction.setTexte(tel);
				Outils.attente(2);
				
				String rib = PageSigninVendeur.getPage().getRib().getText();
				Interaction.setTexte(rib);
			}while(! Interaction.getBooboo());
		}	
	}
	
	
	
	
	if(page_actuelle.equals("profil"){
		if(Systeme.getType_usr().equals("vendeur"){
			if(source == PageUser.getPage().getModif_infos())
				Systeme.getVendeur().modifierInfos();
			if(source == PageUser.getPage().getModif_password())
				Systeme.getVendeur().modifierPassword();
			if(source == PageUser.getPage().getMes_articles())
				Systeme.getVendeur().listeArticle();
			if(source == PageUser.getPage().getPoster())
				Systeme.getVendeur().posterArticle();
		}
		
		if(Systeme.getType_usr().equals("acheteur"){
			if(source == PageUser.getPage().getModif_infos())
				Systeme.getAcheteur().modifierInfos();
			if(source == PageUser.getPage().getModif_password())
				Systeme.getAcheteur().modifierPassword();
			if(source == PageUser.getPage().getDevenir_vendeur()){
				do{
					JTextField rib = new JTextField();
					JTextField num = new JTextField();
					Object[] message {"rib :", rib, "numéro :", num};
	
					int option = JOptionPane.showConfirmDialog(this.centre, message, "complétez votre profil", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						Systeme.getAcheteur().devenirVendeur(rib.getText() , num.getText());
					}else{
						Interaction.affiche_Profil( PageUser.getContenu() );
					}
				}while(! Interaction.getBooboo());
			}
		}

		if(source == PageUser.getPage.getArticles_achetes() ){
			Systeme.getAcheteur().articlesAchetes()
		}
		
		if(source == PageUser.getPage().getDisconnect() ){
			Systeme.disconnect();
		}
		
		if(source == PageUser.getPage().getDelete_account()){
			Systeme.deleteAccount();
		}			
	}
	
	
	
	
	
	if(page_actuelle.equals("modif infos"){
		if(Systeme.getType_usr().equals("acheteur"){
			do{
				JTextField email = new JTextField();
				Object[] message {"email :", email};

				int option = JOptionPane.showConfirmDialog(this.centre, message, "modifiez vos infos", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					Interaction.setTexte( email.getText() );
				}else{
					Interaction.affiche_Profil( PageUser.getContenu() );
				}
			}while(! Interaction.getBooboo());
		}
		
		if(Systeme.getType_usr().equals("vendeur"){
			JTextField email = new JTextField();
			JTextField tel = new JTextField();
			JTextField rib = new JTextField();

			Object[] message {"email :", email, "numero :",tel,"R.I.B :",rib};

			int option = JOptionPane.showConfirmDialog(this.centre, message, "modifiez vos infos", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				Interaction.setTexte(email.getText());
				Outils.attente(2);
				Interaction.setTexte(tel.getText());
				Outils.attente(2);			
				Interaction.setTexte(rib.getText());
			}else{
				Interaction.affiche_Profil( PageUser.getContenu() );
			}	
		}
	}
	
	
	
	
	
	if(page_actuelle.equals("modif password"){
		do{
			JTextField old_pwd = new JPasswordField();
			JTextField new_pwd= new JPasswordField();
			Object[] message = {"old password:", old_pwd,"new password:", new_pwd};
			
			int option = JOptionPane.showConfirmDialog(this.centre, message, "changer votre pwd", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				Interaction.setTexte( old_pwd.getText() );
				Outils.attente(2);
				
				Interaction.setTexte( new_old.getText() );
			}else{
				Interaction.affiche_Profil( PageUser.getContenu() );
			}
		}while(! Interaction.getBooboo() );	
	
	}
	
	
	
	
	
	if(page_actuelle.equals("article"){
		if(Systeme.getType_usr().equals("acheteur"){
			if(source == PageArticle.getPage().getAcheter()){
				if(PageArticle.getPage().getContenu() instanceof ArticleParEnchere){
					Systeme.getVendeur().encherir();
					double prix = Double.parseDouble(JOptionPane.showInputDialog(this.centre, "Saisissez votre enchère")) ;
					Interaction.setNombre(prix);				
					
				}else
					Systeme.getAcheteur().acheter();
			}
		}
		
		if(Systeme.getType_usr().equals("vendeur"){
			if(source == PageArticle.getPage().getAccepter_vente()){
				Systeme.getVendeur().accepterVente();
			}
			
			if(source == PageArticle.getPage().getRetirer_vente()){
				Systeme.getVendeur().retirerVente();
			}
			
			if(source == PageArticle.getPage().getEdit()){
				Systeme.getVendeur().editArticle( PageArticle.getPage().getContenu());
			}
		}

		if(source == PageArticle.getPage().getEvaluer()){
			JTextField note = new JTextField();
			JTextField com = new JTextField();
			Object[] message = {"note :", note,"com :", com};
			
			int option = JOptionPane.showConfirmDialog(this.centre, message, "evaluez l'utilisateur", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				Interaction.setTexte(note.getText() );
				Interaction.setTexte(com.getText() );
			}
		}
	}
	
	
	
	
	if(page_actuelle.equals("poster"){   /////////////////rajouter les images
		if(source == PagePoster.getPage().getSubmit()){
			String titre = PagePoster.getPage().getTitre().getText();
			Interaction.setTexte(titre);
			Outils.attente(2);
			
			String etat = PagePoster.getPage().getEtat().getText();
			Interaction.setTexte(etat);
			Outils.attente(2);
			
			String localisation = PagePoster.getPage().getLocalisation().getText();
			Interaction.setTexte(localisation);
			Outils.attente(2);
			
			String categorie = PagePoster.getPage().getCategorie().getText();
			Interaction.setTexte(categorie);
			Outils.attente(2);
			
			double prix = Double.parseDouble( PagePoster.getPage().getPrix().getText() );
			Interaction.setNombre(prix);
			Outils.attente(2);
			
			String mots_cles = PagePoster.getPage().getMots_cles().getText();
			String[] key = mots_cles.split(";");
			Interaction.setTab(key);
			Outils.attente(2);
			
			String description= PagePoster.getPage().getDescription().getText();
			Interaction.setTexte(description);
			Outils.attente(2);
			
			if( PagePoster.getPage().Type_vente().getSelection() == PagePoster.getPage().getVente_enchere() ){
				int duree = Integer.parseInt( PagePoster.getPage().getDuree().getText() );
				Interaction.setEntier(duree);
				Outils.attente(2);
				Interaction.setEnEnchere(true);
			}else{ Interaction.setEnEnchere(false); }
			
		}

		if(source == PagePoster.getPage().getFichier()){
			boolean boo = false;
			do{
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.addChoosableFileFilter(new ImageFilter());
				jFileChooser.setAcceptAllFileFilterUsed(false);
				jFileChooser.setDialogTitle("Choisir un fichier");
				int result = jFileChooser.showSaveDialog(this);
				if(result == JFileChooser.APPROVE_OPTION){
					boo=true;
					File file = jFileChooser.getSelectedFile();
					File rep = new File("./Images/"); 
					file.renameTo(new File(rep, Interaction.getTexte() ));				
				}else{
					affichePopup( {"veuillez charger une image"} );
				}
			}while(! boo);
		}
	}
	
	
	
	
	
	if(page_actuelle.equals("editer"){
		JTextField titre = new JTextField( PageArticle.getContenu().getTitre() );
		JTextField prix = new JTextField( PageArticle.getContenu().getPrix() );
		JTextField description = new JTextField( PageArticle.getContenu().getDescription );
		Object[] message = {"titre:", titre,"prix:", prix,"description:",description};
	
		int option = JOptionPane.showConfirmDialog(this.centre, message, "editez votre article", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			Interaction.setTexte( titre.getText() );
			Outils.attente(2);
			
			Interaction.setNombre( Double.parseDouble( prix.getText() ) );
			Outils.attente(2);
			
			Interaction.setTexte( description.getText() );
		}else{
			Interaction.affiche_Article( PageArticle.getContenu() );
		}
			
							
	}

	public class ImageFilter{
		public boolean accept(File f) {
			if (f.isDirectory()) {
				return true;
			}
		
			String extension = Outils.getExtension(f);
			if (extension != null) {
				if  extension.equals("jpeg") ||
					extension.equals("jpg") ||
					extension.equals("png")) {
						return true;
				} else {
					return false;
				}
			}
		
			return false;
		}
	}	
}
