import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

public class PageSignin extends Page {
    private static PageSignin page = new PageSignin();

    private JButton vendeur = new JButton("vendeur");
    private JButton acheteur = new JButton("acheteur");

    private Image fond   = Imagerie.getImage("Divers/fond.jpeg");


    private PageSignin() {
        setPreferredSize(new Dimension(900,900));
        setLayout(null);

        initMyButton();

        addMyButton();
    }
    public void addMyButton(){
        add(vendeur);
        add(acheteur);
    }

    public void initMyButton(){
        Dimension dimension = new Dimension(200, 100);
        vendeur.setPreferredSize(dimension);
        acheteur.setPreferredSize(dimension);

        vendeur.addActionListener(Fenetre.getFenetre());
        acheteur.addActionListener(Fenetre.getFenetre());
    }

    /////////////////////////////

    public static PageSignin getPage() {
        return page;
    }
    public JButton getVendeur() {
        return this.vendeur;
    }
    public JButton getAcheteur() {
        return this.acheteur;
    }

    ////////////////////////////

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Font font = new Font("Arial",0,50);

        int tc=100;

        for(int x=0; x<40; x++){
            for(int y=0; y<21; y++){
                g.drawImage(fond, x*tc, y*tc, tc, tc, this);
            }
        }
        tc=50;

        acheteur.setBounds(10* tc, 10 * tc, tc * 6, tc * 3);
        acheteur.setFont(font);

        vendeur.setBounds(21* tc, 10 * tc, tc * 6, tc * 3);
        vendeur.setFont(font);
    }

}
