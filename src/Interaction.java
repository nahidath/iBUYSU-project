import java.awt.Image;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Interaction {


    private static String texte = null;

    public static void setTexte(String s){
        texte=s;
    }
    
    public static String getTexte(){
        while(texte==null){ Outils.attente(1); }
        String res=texte;
        texte=null;
        return res; 
    }


    //////////////////////////////////////////////////

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

    //////////////////////////////////////////////////

    public static void affiche_ResultSearch(ArrayList<Article> liste){
        PageResultSearch.getPage().setContenu(liste);
        Fenetre.setContenu(PageResultSearch.getPage());
        Fenetre.setPage_actuelle("result search");
    }

    public static void affiche_Authenticate(){
        Fenetre.setContenu(PageAuthenticate.getPage());
        Fenetre.setPage_actuelle("authenticate");
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

    public static void affiche_Poster(){
        Fenetre.setContenu(PagePoster.getPage());
        Fenetre.setPage_actuelle("poster");
    }

    public static void affiche_Evaluation(Utilisateur utilisateur){
        ArrayList<Evaluation> liste = Memory.getMemory().evaluationsParUser(utilisateur);
        if(liste != null)
            PageEval.getPage().setContenu(liste);
        PageEval.getPage().setEvalue(utilisateur);
        Fenetre.setContenu(PageEval.getPage());
        Fenetre.setPage_actuelle("eval");
    }




}
