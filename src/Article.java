import java.awt.Image;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Article {
    private String titre;
    private String description="";
    private String etat;
    private String localisation;
    private String categorie;
    private double prix;
    private HashSet<String> mots_cles = new HashSet<String> ();
    private boolean valable=true;
    private Date date;
    private Image image;
    private static int count = 0;
    private final int id;
    private Acheteur acheteur;
    private boolean acheter = false;
    private boolean retirer = false;
    private boolean evaluationAch=false;
    private boolean evaluationVen=false;


    public Article(String titre, String etat, String localisation, String categorie, double prix, Date date) {
        id=count++;
        this.titre=titre;
        this.etat=etat;
        this.localisation=localisation;
        this.categorie=categorie;
        this.prix=prix;
        this.date=date;
    }


    public void decrire(String description) {
        this.description=description;
    }

    public void ajouterMotsCles(String mots) {
        mots_cles.add(mots);
    }

    /////////////////////////////////////////////////////////


    public String getCategorie() {
        return categorie;
    }
    public Date getDate() {return this.date; }

    public Image getImage() { return this.image; }
    public void setImage(Image value) { this.image = value; }

    public boolean isEvaluationAch() { return evaluationAch; }
    public void setEvaluationAch() { evaluationAch=true; }

    public boolean isEvaluationVen() { return evaluationVen; }
    public void setEvaluationVen() { evaluationVen=true; }

    public boolean isAcheter() {return acheter; }
    public void setAcheter(){ acheter=true; }

    public boolean isRetirer() { return retirer; }
    public void setRetirer(){ retirer=true;}

    /*
    public void setImage(){
        String filename = getId()+"article";
        //System.out.println(filename);
        Image image = Imagerie.getImage("Article/"+filename);
        //Interaction.setTexte(filename);
        setImage(image);
    }*/

    public Acheteur getAcheteur() { return this.acheteur; }
    public void setAcheteur(Acheteur value) {
        this.acheteur = value;
        valable=false;
        Memory.getMemory().articleQuelVendeur(this).setNombreNonValable();
    }

    public void resetValable() { //si on ajoute un bouton refuser vente
        acheteur=null;
        valable=true;
    }


    public HashSet<String> getMots_cles() { return this.mots_cles; }

    public void setTitre(String value) { this.titre = value; }
    public String getTitre() { return this.titre; }

    public void setPrix(double value) { this.prix = value; }
    public double getPrix() { return this.prix; }

    public String getDescription() { return this.description; }

    public int getId() { return this.id; }

    public String getEtat() { return etat; }

    public String getLocalisation() { return localisation; }

    public boolean isValable() { return valable; }

    public static String getNextFilename(){
        return (count-1)+"article";
    }
}
