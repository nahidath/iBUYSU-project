import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;


public class Memory {
    private static Memory memory= new Memory();

    private Map<Vendeur,  HashSet<Article>> ventes = new HashMap<Vendeur,  HashSet<Article>>();
    private Map<String, HashSet<Article>> mots_cles = new HashMap<String, HashSet<Article>>();
    private Map<Utilisateur[], String[]> evaluations = new HashMap< Utilisateur[], String[]>();
    private Map<String, HashSet<Article>> categories = new HashMap<String, HashSet<Article>>();

    private HashSet<Utilisateur> users = new HashSet<Utilisateur> ();

    private HashSet<Article> articles = new HashSet<Article> ();



    private Memory() {
    }

    public static Memory getMemory() {
        return memory;
    }

    public void viderListe(String nom_liste) {
        switch(nom_liste){
            case "users":
                users.clear();
                break;
            case "articles":
                articles.clear();
                break;
            case "ventes":
                ventes.clear();
                break;
            case "mots_cles":
                mots_cles.clear();
                break;
            case "evaluations":
                evaluations.clear();
                break;
        }
    }

    public void afficherListe(String nom_liste) {
        switch(nom_liste){
            case "users":
                for(Utilisateur u : users)
                    System.out.println(u);
                break;
            case "articles":
                for(Article a : articles)
                    System.out.println(a);
                break;
            case "ventes":
                for(Map.Entry v : ventes.entrySet() )
                    System.out.println(v.getKey() + " "+v.getValue() );
                break;
            case "mots_cles":
                for(Map.Entry m : mots_cles.entrySet() )
                    System.out.println(m.getKey() + " "+m.getValue() );
                break;
            case "evaluations":
                for(Map.Entry e : evaluations.entrySet() )
                    System.out.println(e.getKey() + " "+e.getValue() );
                break;
        }
    }

    public void addUser(Utilisateur user) {
        users.add(user);
    }

    public void removeUser(Utilisateur user) {
        users.remove(user);
	
	    if(user instanceof Vendeur ){
	    	ventes.remove((Vendeur)user);
	    }
    }



    public Utilisateur connexion(String email, String mdp) {
        for(Utilisateur u : users) {
            if (u.getEmail().equals(email) && u.getMdp().equals(mdp))
                return u;
        }
	    return null;
    }



    public boolean existEmail(String email) {
    	boolean bool=false;
	    for(Utilisateur u : users) {
            if (u.getEmail().equals(email))
                bool = true;
        }
	    return bool;
    }




    public void addArticle(Article article, String categorie, HashSet<String> mots_cles) {
        articles.add(article);

	    HashSet<Article> cat = categories.get(categorie);
	    if(cat==null){
	    	cat= new HashSet<Article>();
	    	categories.put(categorie,cat);
	    }
	    cat.add(article);

	    for(String key : mots_cles){
	    	HashSet<Article> kw = this.mots_cles.get(key);
	    	if(kw==null){
	    		kw= new HashSet<Article>();
	    		this.mots_cles.put(key,kw);
	    	}
	    	kw.add(article); 	
	    }

	    PageAccueil.getPage().setContenu(new ArrayList<>(articles));
    }

    public void removeArticle(Article article) {
        HashSet<String> key_words = article.getMots_cles();
	    String categ = article.getCategorie();
        
	    this.articles.remove(article);

	    categories.get(categ).remove(article);

	    for(String key : key_words)
            mots_cles.get(key).remove(article);


        PageAccueil.getPage().setContenu(new ArrayList<>(articles));
    }




    public void vendeur_article(Article article, Vendeur vendeur) {
        HashSet<Article> v = ventes.get(vendeur);
	    if(v==null){
	    	v= new HashSet<Article>();
	    	ventes.put(vendeur,v);
	    }
	    v.add(article);
    }







    public HashSet<Article> rechercheArticleMotsCles(String[] mots_cles) {
        HashSet<Article> res = new HashSet<Article>();
	    boolean first = true;
        
	    for(String key : mots_cles){
	    	HashSet<Article> x = this.mots_cles.get(key);
	    	if(x!=null) {
                if (first) {
                    first = false;
                    res.addAll(x);
                } else {
                    res.retainAll(x);
                }
            }
	    }

	    return res;
    }

    public HashSet<Article> rechercheArticleCategorie(String categorie) {
        return this.categories.get(categorie);
    }

    public void evaluation_utilisateur(Utilisateur evaluateur, Utilisateur evaluee, String note, String commentaire) {
        Utilisateur[] u={evaluateur, evaluee};
	    String[] s={note, commentaire};

	    evaluations.put(u,s);
	    int n = Integer.valueOf(note);
	    evaluee.setEvaluation(n);
    }
    public ArrayList<Evaluation> evaluationsParUser(Utilisateur user){
        ArrayList<Evaluation> res = new ArrayList<>();
        for(Map.Entry m : evaluations.entrySet()){
            Utilisateur[] u = (Utilisateur[]) m.getKey();
            if(user == u[1]){
                String[] s = (String[]) m.getValue();
                res.add( new Evaluation(u[0], u[1], s[0], s[1]) );
            }
        }

        return res;
    }

    public HashSet<Article> articleParVendeur(Vendeur vendeur) {
        return ventes.get(vendeur);
    }

    public Vendeur articleQuelVendeur(Article article){
        for(Map.Entry m : ventes.entrySet()){
            if(((HashSet<Article>)m.getValue()).contains(article) )
                return (Vendeur)m.getKey();
        }
        return null;
    }

    public ArrayList<Article> getArticles() {
        return new ArrayList<Article>(articles);
    }

    public String[] getCategories(){
        String[] res= new String[categories.size()];
        int i =0;
        for(String s : categories.keySet()){
            res[i]=s;
            i++;
        }
        return res;
    }
}
