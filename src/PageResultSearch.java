import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

public class PageResultSearch extends Page{
    private static PageResultSearch page =new PageResultSearch();

    private JComboBox liste_tri = new JComboBox(new String[]{"plus récent","plus ancien","moins cher","plus cher" });
    private String tri="";
    private JButton trier = new JButton("trier");

    private static Image cursor =Imagerie.getImage("Divers/Cursor_bas.png");
    private static int decalage=0;

    private ArrayList<Article> contenu = new ArrayList<Article>();

    private Image fond   = Imagerie.getImage("Divers/fond.jpeg");


    private PageResultSearch() {
        setPreferredSize(new Dimension(900,900));
        setLayout(null);

        initMyButton();

        addMyButton();

    }
    public void addMyButton(){
        add(liste_tri);
        add(trier);
    }

    public void initMyButton(){
        Dimension dimension = new Dimension(100, 50);

        trier.setPreferredSize(dimension);
        liste_tri.setPreferredSize(dimension);

        trier.addActionListener(Fenetre.getFenetre());
    }

    ////////////////////////////////////

    public static PageResultSearch getPage() {
        return page;
    }
    public JComboBox getListe_tri() {
        return this.liste_tri;
    }
    public String getTri() {
       return this.tri;
    }
    public void setTri(String value) {
        this.tri = value;
    }
    public ArrayList<Article> getContenu() {
        return this.contenu;
    }
    public void setContenu(ArrayList<Article> value) {
        reset();
        this.contenu = value;
    }

    public JButton getTrier() { return trier; }
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
        return contenu.get(decalage);
    }


    public void reset(){ decalage=0; }



    public void paintComponent(Graphics g){
        super.paintComponent(g);

        int tc = 100;

        for(int x=0; x<40; x++){
            for(int y=0; y<21; y++){
                g.drawImage(fond, x*tc, y*tc, tc, tc, this);
            }
        }
        tc=50;

        Font font = new Font("Arial",0,20);
        g.setFont(font);
        int x=1, y=5;

        //modifier infos
        liste_tri.setBounds(7*tc, 6*tc, tc*3, tc );
        liste_tri.setFont(font);
        //modifier password
        trier.setBounds(11*tc, 6*tc, tc*3, tc );
        trier.setFont(font);

        tc = 100 ;
        g.drawImage(cursor, 9*tc , 4*tc + (int)(tc*0.2), (int)(tc*0.8), (int)(tc*0.8), this);
        int pos_min_1 = decalage-2;
        int pos_max_1 = decalage+2;
        for(int i=pos_min_1; i<=pos_max_1; i++ ){
            if (i >= 0 && i<contenu.size() && contenu.get(i) != null) {
                //g.drawRect(x*tc,y*tc,tc,tc) ;
                g.drawImage(contenu.get(i).getImage(), x * tc + (int) (tc * 0.1), y * tc, (int) (tc * 0.8), tc, this);
                y++;
                x--;
                g.clearRect(x * tc, y * tc, tc * 3, tc);
                g.drawString(contenu.get(i).getTitre(), x * tc + tc, y * tc + ((int) tc / 2));
                if(contenu.get(i).isAcheter() && Systeme.getType_usr().equals("vendeur") && Memory.getMemory().articleQuelVendeur(contenu.get(i))==Systeme.getVendeur() ) {
                    y++;
                    g.clearRect(x * tc, y * tc, tc * 3, tc);
                    g.drawString("article vendu", x * tc+tc, y * tc + ((int) tc / 2));
                    y--;
                }else{
                    if(contenu.get(i).isRetirer() && Systeme.getType_usr().equals("vendeur") && Memory.getMemory().articleQuelVendeur(contenu.get(i))==Systeme.getVendeur() ) {
                        y++;
                        g.clearRect(x * tc, y * tc, tc * 3, tc);
                        g.drawString("article retiré", x * tc+tc, y * tc + ((int) tc / 2));
                        y--;
                    }
                }
                x++;
                y--;


            }
            x += 4;
        }

    }

}
