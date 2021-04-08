
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;


public class PageAccueil extends Page {
    private static PageAccueil page=new PageAccueil();

    private static Image cursor =Imagerie.getImage("Divers/Cursor_bas.png");
    private static int decalage=0;

    private ArrayList<Article> contenu = new ArrayList<> ();

    private Image fond   = Imagerie.getImage("Divers/fond.jpeg");

    private PageAccueil() {
        setPreferredSize (new Dimension(900, 900) ) ;
	    setContenu( Outils.trierListeArticle( new ArrayList<Article>(Memory.getMemory().getArticles()), "plus récent" ));
    }

    public void addMyButton(){}

    public void initMyButton(){}

    public static PageAccueil getPage() {
        return page;
    }

    public ArrayList<Article> getContenu() {
        return this.contenu;
    }

    public void setContenu(ArrayList<Article> value) {
        reset();
        this.contenu = value;
    }

    /////////////////////////////////////////

    public void setDecalage(int i){
        decalage+=i;
        if(decalage >= contenu.size())
            decalage=contenu.size()-1;
        if(decalage < 0)
            decalage=0;
        page.repaint();
    }
    public int getDecalage(){ return decalage; }
    public Article getArticleCourant(){
        //System.out.println(contenu.get(decalage).getTitre());
        return contenu.get(decalage);
    }


    public void reset(){ decalage=0; }



    public void paintComponent(Graphics g){
        super.paintComponent(g);

        int tc = 200;

        for(int x=0; x<40; x++){
            for(int y=0; y<21; y++){
                g.drawImage(fond, x*tc, y*tc, tc, tc, this);
            }
        }

        tc = 100;
        g.setFont(new Font("Arial",0,25));
        g.setColor(new Color(0, 0, 0));
        int x=1, y=4;

        g.drawImage(cursor, 9*tc , 3*tc + (int)(tc*0.2), (int)(tc*0.8), (int)(tc*0.8), this);
        int pos_min_1 = decalage-2;
        int pos_max_1 = decalage+2;
        for(int i=pos_min_1; i<=pos_max_1; i++ ){
            if (i >= 0 && i<contenu.size() && contenu.get(i) != null) {
                //g.drawRect(x*tc,y*tc,tc,tc) ;
                g.drawImage(contenu.get(i).getImage(), x * tc + (int) (tc * 0.1), y * tc, (int) (tc * 0.8), tc, this);
                y++;
                x--;
                g.clearRect(x * tc, y * tc, tc * 3, tc);
                g.drawString(contenu.get(i).getTitre(), x * tc+(int)(tc/2), y * tc + ((int) tc / 2));
                x++;
                y--;


            }
            x += 4;
        }

    }

}
