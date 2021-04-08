import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

public class PageAuthenticate extends Page {
    private static PageAuthenticate page=new PageAuthenticate();

    private JButton login = new JButton("login");
    private JButton signin = new JButton("signin");

    private Image fond   = Imagerie.getImage("Divers/fond.jpeg");


    private PageAuthenticate() {
        setPreferredSize(new Dimension(900,900));
        setLayout(null);

        initMyButton();

        addMyButton();
    }

    public void addMyButton(){
        add(login);
        add(signin);
    }

    public void initMyButton(){
        Dimension dimension = new Dimension(200, 100);
        login.setPreferredSize(dimension);
        signin.setPreferredSize(dimension);

        login.addActionListener(Fenetre.getFenetre());
        signin.addActionListener(Fenetre.getFenetre());

    }
    
    //////////////////////////////////

    public static PageAuthenticate getPage() {
        return page;
    }
    public JButton getLogin() {
        return this.login;
    }
    public JButton getSignin() {
        return this.signin;
    }

    //////////////////////////////////////

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
        login.setBounds(10* tc, 10 * tc, tc * 6, tc * 3);
        login.setFont(font);

        signin.setBounds(21* tc, 10 * tc, tc * 6, tc * 3);
        signin.setFont(font);
    }

}
