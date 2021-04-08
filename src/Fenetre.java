import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class Fenetre extends JFrame implements KeyListener, ActionListener {
	private static JFrame frame = new JFrame();
	private static Fenetre fenetre = new Fenetre();
    private static Page centre;
    private static String page_actuelle = "accueil";


    

    private Fenetre() {
		frame.setPreferredSize (new Dimension(2000, 1300) ) ;
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(Imagerie.getImage("Icons/logo.jpeg"));
        frame.setTitle("IBuySU");
        frame.setFocusable(true);
        frame.setResizable(true);
        frame.addKeyListener(this);

		centre=PageAccueil.getPage();

        frame.setContentPane(centre);
        frame.add(ToolBar.getToolBar(), BorderLayout.NORTH );
        //frame.add(centre, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.pack();

    }

    public static Fenetre getFenetre() {
        return fenetre;
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void resetFrame() {
		frame = new JFrame();
        fenetre = new Fenetre();
    }

    public static void setPage_actuelle(String value) {
        page_actuelle = value;
    }

    public static String getPage_actuelle() {
        return page_actuelle;
    }

	public static void backToHome(){
		setContenu( PageAccueil.getPage() );
		setPage_actuelle("accueil");
	}
	
	public static void setContenu(Page contenu){
		//frame.remove(centre);
		//frame.(contenu, BorderLayout.CENTER);
		centre=contenu;
		if(centre instanceof PageEval) {
			try {
				((PageEval) centre).dessiner();
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			frame.setContentPane(new JPanel());
			frame.add(ToolBar.getToolBar(), BorderLayout.NORTH );
			frame.add(centre, BorderLayout.CENTER);
		}else{
			centre.repaint();
			frame.setContentPane(centre);
			frame.add(ToolBar.getToolBar(), BorderLayout.NORTH );
		}
		frame.setVisible(true);

	} 
	
	public static void affichePopup(String[] message){
		JOptionPane.showConfirmDialog(centre, message, "ERROR", JOptionPane.OK_CANCEL_OPTION);
	}
	public static int affichePopup(String[] message, String titre){
		return JOptionPane.showConfirmDialog(centre, message, titre, JOptionPane.OK_CANCEL_OPTION);
	}
	public void refreshPage(){
    	centre.removeAll();
		centre.addMyButton();
    	centre.repaint();
		frame.add(ToolBar.getToolBar(), BorderLayout.NORTH );
	}


	////////////////////////////////////////////////////
	
	public void keyPressed(KeyEvent e) {
		if(page_actuelle.equals("accueil") ){
			int keyCode = e.getKeyCode();
			switch (keyCode){
				case KeyEvent.VK_LEFT:
					PageAccueil.getPage().setDecalage(-1);
					break;
				case KeyEvent.VK_RIGHT:
					PageAccueil.getPage().setDecalage(1);
					break;
				case KeyEvent.VK_ENTER:
					Interaction.affiche_Article( PageAccueil.getPage().getArticleCourant() );
					PageAccueil.getPage().reset();
					break;
			}
		}
        if(page_actuelle.equals("result search") ){
			int keyCode = e.getKeyCode();
			switch (keyCode){
				case KeyEvent.VK_LEFT:
					PageResultSearch.getPage().setDecalage(-1);
					break;
				case KeyEvent.VK_RIGHT:
					PageResultSearch.getPage().setDecalage(1);
					break;
				case KeyEvent.VK_ENTER:
					Interaction.affiche_Article( PageResultSearch.getPage().getArticleCourant() );
					PageResultSearch.getPage().reset();
					break;
			}
        }
    }

    public void keyTyped(KeyEvent e){} public void keyReleased(KeyEvent e){}

    public String toString(){
    	return "la fenetre";
	}

	////////////////////////////////////////////////////////

	private boolean testFichier = false;
	private File file=null;

	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();

	/////////////////
		if(page_actuelle.equals("result search") ){
			if(source == PageResultSearch.getPage().getTrier()){
				String selected = (String)PageResultSearch.getPage().getListe_tri().getSelectedItem();
				if(! selected.equals(PageResultSearch.getPage().getTri())){
					PageResultSearch.getPage().setContenu( Outils.trierListeArticle( PageResultSearch.getPage().getContenu(), selected ));
					refreshPage();
					PageResultSearch.getPage().setTri(selected);
				}	
			}
		}




		if(page_actuelle.equals("authenticate")){
			if(source == PageAuthenticate.getPage().getLogin()){
				boolean booboo;
				do{
					JTextField email = new JTextField();
					JTextField pwd= new JPasswordField();
					Object[] message = {"email:", email,"password:", pwd};

					int option = JOptionPane.showConfirmDialog(this.centre, message, "login", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						booboo = Systeme.login(email.getText(), pwd.getText() );
					}else{
						backToHome();
						break;
					}
				}while(! booboo);
			}

			if(source == PageAuthenticate.getPage().getSignin()){
				Systeme.signin();
			}
		}




		if(page_actuelle.equals("signin")){
			if(source == PageSignin.getPage().getVendeur() ){
				Interaction.affiche_SigninVend();
			}

			if(source == PageSignin.getPage().getAcheteur() ){
				Interaction.affiche_SigninAch();
			}
		}




		if(page_actuelle.equals("signin acheteur")){
			if(source == PageSigninAcheteur.getPage().getSubmit()){
				String nom = PageSigninAcheteur.getPage().getNom().getText();
				String prenom = PageSigninAcheteur.getPage().getPrenom().getText();
				String email = PageSigninAcheteur.getPage().getEmail().getText();
				String password = PageSigninAcheteur.getPage().getPassword().getText();

				Systeme.signinAcheteur(nom, prenom, email, password);
			}
		}




		if(page_actuelle.equals("signin vendeur")){
			if(source == PageSigninVendeur.getPage().getSubmit()){
				String nom = PageSigninVendeur.getPage().getNom().getText();
				String prenom = PageSigninVendeur.getPage().getPrenom().getText();
				String email = PageSigninVendeur.getPage().getEmail().getText();
				String password = PageSigninVendeur.getPage().getPassword().getText();
				String tel = PageSigninVendeur.getPage().getTelephone().getText();
				String rib = PageSigninVendeur.getPage().getRib().getText();

				Systeme.signinVendeur(nom, prenom, email, password, tel, rib);
			}
		}




		if(page_actuelle.equals("profil")){
			if(Systeme.getType_usr().equals("vendeur")){
				if(source == PageUser.getPage().getModif_infos())
					Interaction.affiche_ModifInfos();
				if(source == PageUser.getPage().getModif_password())
					Interaction.affiche_ModifPassword();
					//Systeme.getVendeur().modifierPassword();
				if(source == PageUser.getPage().getMes_articles())
					Systeme.getVendeur().listeArticle();
				if(source == PageUser.getPage().getArticles_achetes() )
					Systeme.getVendeur().articlesAchetes();
				if(source == PageUser.getPage().getPoster())
					Interaction.affiche_Poster();
					//Systeme.getVendeur().posterArticle();
			}

			if(Systeme.getType_usr().equals("acheteur")){
				if(source == PageUser.getPage().getModif_infos())
					Interaction.affiche_ModifInfos();
				if(source == PageUser.getPage().getModif_password())
					Interaction.affiche_ModifPassword();
				//Systeme.getAcheteur().modifierPassword();
				if(source == PageUser.getPage().getArticles_achetes() )
					Systeme.getAcheteur().articlesAchetes();
				if(source == PageUser.getPage().getDevenir_vendeur()){
					JTextField rib = new JTextField();
					JTextField num = new JTextField();
					Object[] message={"rib :", rib, "numéro :", num};

					int option = JOptionPane.showConfirmDialog(this.centre, message, "complétez votre profil", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						Systeme.getAcheteur().devenirVendeur(rib.getText() , num.getText());
					}else{
						Interaction.affiche_Profil( PageUser.getPage().getContenu() );
					}

				}
			}

			if(source == PageUser.getPage().getDisconnect() ){
				Systeme.disconnect(true);
			}

			if(source == PageUser.getPage().getDelete_account()){
				Systeme.deleteAccount();
			}			
		}





		if(page_actuelle.equals("modif infos")){
			if(Systeme.getType_usr().equals("acheteur")){

				JTextField email = new JTextField( Systeme.getAcheteur().getEmail() );
				Object[] message ={"email :", email};

				int option = JOptionPane.showConfirmDialog(this.centre, message, "modifiez vos infos", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					Systeme.getAcheteur().modifierInfos(email.getText());
				}else{
					Interaction.affiche_Profil( PageUser.getPage().getContenu() );
				}

			}

			if(Systeme.getType_usr().equals("vendeur")){
				JTextField email = new JTextField(Systeme.getVendeur().getEmail());
				JTextField tel = new JTextField(Systeme.getVendeur().getNumero_tel());
				JTextField rib = new JTextField(Systeme.getVendeur().getRib());

				Object[] message = {"email :", email, "numero :",tel,"R.I.B :",rib};

				int option = JOptionPane.showConfirmDialog(this.centre, message, "modifiez vos infos", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					Systeme.getVendeur().modifierInfos(email.getText(),  tel.getText(), rib.getText() );
				}else{
					Interaction.affiche_Profil( PageUser.getPage().getContenu() );
				}	
			}
		}





		if(page_actuelle.equals("modif password")){
			JTextField old_pwd = new JPasswordField();
			JTextField new_pwd= new JPasswordField();
			Object[] message = {"old password:", old_pwd,"new password:", new_pwd};

			int option = JOptionPane.showConfirmDialog(this.centre, message, "changer votre pwd", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				if( Systeme.getType_usr().equals("vendeur") )
					Systeme.getVendeur().modifierPassword(old_pwd.getText(), new_pwd.getText());
				if( Systeme.getType_usr().equals("acheteur") )
					Systeme.getAcheteur().modifierPassword(old_pwd.getText(), new_pwd.getText());

			}else{
				Interaction.affiche_Profil( PageUser.getPage().getContenu() );
			}

		
		}





		if(page_actuelle.equals("article")){
			if(Systeme.getType_usr().equals("acheteur")){
				if(source == PageArticle.getPage().getAcheter()){
					if(PageArticle.getPage().getContenu() instanceof ArticleParEnchere){
						JTextField prix = new JTextField();
						Object[] message = { "enchère en €", prix};

						int option = JOptionPane.showConfirmDialog(this, message,"Saisissez votre enchère", JOptionPane.OK_CANCEL_OPTION );
						if(option == JOptionPane.OK_OPTION){
							Systeme.getAcheteur().encherir( Double.parseDouble( prix.getText() ) );
						}
					}else
						Systeme.getAcheteur().acheterArticle();
				}
				if(source == PageArticle.getPage().getVoirUsr()){
					Utilisateur u = Memory.getMemory().articleQuelVendeur( PageArticle.getPage().getContenu() );
					Interaction.affiche_Evaluation( u );
				}
			}

			if(Systeme.getType_usr().equals("vendeur")){
				if(source == PageArticle.getPage().getAccepter_vente()){
					Systeme.getVendeur().accepterVente();
				}
				if(source == PageArticle.getPage().getRefuser_vente()){
					Systeme.getVendeur().refuserVente();
				}

				if(source == PageArticle.getPage().getRetirer_vente()){
					Systeme.getVendeur().retirerVente();
				}

				if(source == PageArticle.getPage().getEdit()){
					JTextField titre = new JTextField( PageArticle.getPage().getContenu().getTitre() );
					JTextField prix = new JTextField( ""+PageArticle.getPage().getContenu().getPrix() );
					JTextField description = new JTextField( PageArticle.getPage().getContenu().getDescription() );
					Object[] message = {"titre:", titre,"prix:", prix,"description:",description};

					int option = JOptionPane.showConfirmDialog(this, message, "editez votre article", JOptionPane.OK_CANCEL_OPTION);

					if (option == JOptionPane.OK_OPTION) {
						Systeme.getVendeur().editArticle( PageArticle.getPage().getContenu(), titre.getText(), Double.parseDouble( prix.getText() ), description.getText()  );
						refreshPage();
					}
				}

				if(source == PageArticle.getPage().getAcheter()){
					if(PageArticle.getPage().getContenu() instanceof ArticleParEnchere){
						JTextField prix = new JTextField();
						Object[] message = { "enchère en €", prix};

						int option = JOptionPane.showConfirmDialog(this, message,"Saisissez votre enchère", JOptionPane.OK_CANCEL_OPTION );
						if(option == JOptionPane.OK_OPTION){
							Systeme.getVendeur().encherir( Double.parseDouble( prix.getText() ) );
						}/*
						JOptionPane.showInputDialog(this.centre, ) ;
						double prix = Double.parseDouble( );

						//Interaction.setNombre(prix);*/
					}else
						Systeme.getVendeur().acheterArticle();
				}

				if(source == PageArticle.getPage().getVoirUsr()){
					Utilisateur u = Memory.getMemory().articleQuelVendeur( PageArticle.getPage().getContenu() );
					Interaction.affiche_Evaluation( u );
				}


			}

			if(source == PageArticle.getPage().getEvaluer()){
				JTextField note = new JTextField();
				JTextField com = new JTextField();
				Object[] message = {"note :", note,"com :", com};

				int option = JOptionPane.showConfirmDialog(this.centre, message, "evaluez l'utilisateur", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					if(Systeme.getType_usr().equals("acheteur"))
						Systeme.getAcheteur().evaluerUsr( PageArticle.getPage().getContenu(), note.getText(), com.getText());
					if(Systeme.getType_usr().equals("vendeur"))
						Systeme.getVendeur().evaluerUsr( PageArticle.getPage().getContenu(), note.getText(), com.getText());
				}
			}
		}




		if(page_actuelle.equals("poster")){

			if(source == PagePoster.getPage().getSubmit()){
				if(testFichier) {
					String titre = PagePoster.getPage().getTitre().getText();
					String etat = (String)PagePoster.getPage().getEtat().getSelectedItem();
					String localisation = PagePoster.getPage().getLocalisation().getText();
					String categorie = PagePoster.getPage().getCategorie().getText();
					double prix = Double.parseDouble(PagePoster.getPage().getPrix().getText());
					String mots_cles = PagePoster.getPage().getMots_cles().getText();
					String[] key = mots_cles.split(";");
					String description = PagePoster.getPage().getDescription().getText();

					Article article;
					if (PagePoster.getPage().getType_vente().getSelection() == PagePoster.getPage().getVente_enchere()) {
						int duree = Integer.parseInt(PagePoster.getPage().getDuree().getText());

						article=Systeme.getVendeur().posterArticle(titre, etat, localisation, categorie, prix, description, key, true, duree);
					} else {
						article=Systeme.getVendeur().posterArticle(titre, etat, localisation, categorie, prix, description, key, false, 0);
					}

					File dest = null;
					try {
						dest = Outils.copyFile(file);

						File rep = new File("./Images/Article/");

						dest.renameTo(new File(rep, Article.getNextFilename()+"."+Outils.getExtension(file) ));

						Systeme.getVendeur().setImageArticle(article, Outils.getExtension(file)  );

					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
					File rep = new File("./Images/Article/");

					dest.renameTo(new File(rep, Article.getNextFilename()+"."+Outils.getExtension(file) ));

					testFichier=false;
					file=null;
				}else{
					affichePopup( new String[]{"veuillez charger une image pour votre article"} );
				}


			}

			if(source == PagePoster.getPage().getFichier()){
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.addChoosableFileFilter(new ImageFilter());
				jFileChooser.setAcceptAllFileFilterUsed(false);
				jFileChooser.setDialogTitle("Choisir un fichier");
				int result = jFileChooser.showSaveDialog(this);
				if(result == JFileChooser.APPROVE_OPTION){
					file = jFileChooser.getSelectedFile();
					testFichier=true;
				}else{
					affichePopup( new String[]{"veuillez charger une image"} );
				}
			}
		}

		if(page_actuelle.equals("eval")){
			if(source == PageEval.getPage().getRetour()){
				Interaction.affiche_Article(null);
			}
		}
	}


	public class ImageFilter extends FileFilter {
		private String extension=null;
		public boolean accept(File f) {
			if (f.isDirectory()) {
				return true;
			}

			extension = Outils.getExtension(f);
			if (extension != null) {
				if ( extension.equals("jpeg") ||
						extension.equals("jpg") ||
						extension.equals("png")) {
					return true;
				} else {
					return false;
				}
			}

			return false;
		}

		@Override
		public String getDescription() {
			return null;
		}
	}
}
	