viderListe(nom_liste){
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

______________________________________

afficherListe(nom_liste){
	switch(nom_liste){
		case "users":
			for(User u : users)
				Sop(u);
			break;
		case "articles":
			for(Article a : articles)
				Sop(a);
			break;
		case "ventes":
			for(Map.Entry v : ventes.entrySet() )
				Sop(v.getKey() + " "+v.getValue() );
			break;
		case "mots_cles":
			for(Map.Entry m : mots_cles.entrySet() )
				Sop(m.getKey() + " "+m.getValue() );
			break;
		case "evaluations":
			for(Map.Entry e : evaluations.entrySet() )
				Sop(e.getKey() + " "+e.getValue() );
			break;
	}
}

________________________________________

addUser(user){
	users.add(user);
}
removeUser(user){
	users.remove(user);
	
	if(user instanceof Vendeur ){
		ventes.remove((Vendeur)user);
	}
}

________________________________________


connexion(email, mdp){	
	for(Utilisateur u : users)
		if(u.getEmail().equals(email) && u.getPassword().equals(mdp)
			return u;
	return null;
}

_________________________________________

existEmail(email){
	boolean bool=false;
	for(Utilisateur u : users)
		if(u.getEmail().equals(email))
			bool=true;
	return bool;
}

_________________________________________

addArticle(article, categorie, mots_cles){
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
			mots_cles.put(key,kw);
		}
		kw.add(article); 	
	}
}	

__________________________________________

removeArticle(article){
	String[] key_words = article.getMots_cles();
	String categ = article.getCategorie();
	
	this.articles.remove(article);

	categories.get(categorie).remove(article);

	for(String key : key_words)
		mots_cles.get(key).remove(article);
}

___________________________________________

vendeur_article(article, vendeur){
	HashSet<Article> v = ventes.get(vendeur);
	if(v==null){
		v= new HashSet<Article>();
		ventes.put(vendeur,v);
	}
	v.add(article);
}

___________________________________________

rechercheArticleMotsCles(mots_cles){
	HashSet<Article> res = new HashSet<Article>();
	boolean first = true;
	
	for(String key : mots_cles){
		if( first){
			first=false;
			res.addAll(this.mots_cles.get(key));
		}else{
			res.retainAll(this.mots_cles.get(key));
		}
	}

	return res;
	
___________________________________________

rechercheArticleCategorie(categorie){
	return categorie.get(categorie);
}


____________________________________________

evaluation_utilisateur(evaluateur, evaluee, note, commentaire){
	Utilisateur[] u={evaluateur, evaluee};
	String[] s={String.valueof(note), commentaire};
	
	evaluations.put(u,s);
}

___________________________________________

articleParVendeur(vendeur){
	return ventes.get(vendeur);
}

articleQuelVendeur(article){
	for(Map.Entry m : ventes.entrySet()){
		if(m.getValue().contains(article) )
			return m.getKey();
	}
}






}




















































