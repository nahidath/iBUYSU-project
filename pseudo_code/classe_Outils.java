public static HashSet<Article> trierListeArticle(ArrayList<Article> liste; String type){
	HashSet<Article> res=new HashSet<Article>();
	Article a;
	if(type.equals("plus récent")){
		for(int i=0; i<liste.size(); i++){
			a=minDate(liste);
			liste.remove(a);
			res.add(a);
		}
	}else{
		if(type.equals("plus ancien")){
			a=maxDate(liste);
			liste.remove(a);
			res.add(a);
		}else{
			if(type.equals("moins cher")){
				a=minPrix(liste);
				liste.remove(a);
				res.add(a);
			}else{
				if(type.equals("plus cher")){
					a=maxPrix(liste);
					liste.remove(a);
					res.add(a);
				}
			}
		}
	}
}




private static Article maxDate(HashSet<Article> liste){
	Article res= liste.get(0);
	liste.remove(0);
	for(Article a : liste){
		if( res.getDate().compareTo( a.getDate() ) > 0 )
			res=a;
	}
	return res;
}

private static Article minDate(HashSet<Article> liste){
	Article res= liste.get(0);
	liste.remove(0);
	for(Article a : liste){
		if( res.getDate().compareTo( a.getDate() ) < 0 )
			res=a;
	}
	return res;
}


private static Article maxPrix(HashSet<Article> liste){
	Article res= liste.get(0);
	liste.remove(0);
	for(Article a : liste){
		if( res.getPrix() < a.getPrix() )
			res=a;
	}
	return res;
}

private static Article minPrix(HashSet<Article> liste){
	Article res= liste.get(0);
	liste.remove(0);
	for(Article a : liste){
		if( res.getPrix() > a.getPrix() )
			res=a;
	}
	return res;
}

public static String getExtension(File f) {
	String ext = null;
	String s = f.getName();
	int i = s.lastIndexOf('.');

	if (i > 0 &&  i < s.length() - 1) {
		ext = s.substring(i+1).toLowerCase();
	}
	return ext;
}