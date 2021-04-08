public PageAcceuil(){

	setPreferredSize (new Dimension(900, 900) ) ;
	setContenu( Outils.trierListeArticle( Memory.getMemory().getArticles(), "plus récent" ));
	
	
}
