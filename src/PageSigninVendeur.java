import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

public class PageSigninVendeur extends Page {
    private static PageSigninVendeur page=new PageSigninVendeur();


    private JTextField nom = new JTextField();
    private JTextField prenom = new JTextField();
    private JTextField email = new JTextField();
    private JTextField password = new JPasswordField();
    private JTextField telephone = new JTextField();
    private JTextField rib = new JTextField();
    private JButton submit = new JButton("submit");

    private Image fond   = Imagerie.getImage("Divers/fond.jpeg");


   
    private PageSigninVendeur() {
        setPreferredSize(new Dimension(900,900));
        setLayout(null);

        initMyButton();

        addMyButton();
    }


    public void addMyButton(){
        add(nom);
        add(prenom);
        add(email);
        add(password);
        add(submit);
        add(telephone);
        add(rib);
    }

    public void initMyButton(){
        Dimension dimension = new Dimension(200, 100);
        nom.setPreferredSize(dimension);
        prenom.setPreferredSize(dimension);
        email.setPreferredSize(dimension);
        password.setPreferredSize(dimension);
        submit.setPreferredSize(dimension);
        telephone.setPreferredSize(dimension);
        rib.setPreferredSize(dimension);

        nom.addActionListener(Fenetre.getFenetre());
        prenom.addActionListener(Fenetre.getFenetre());
        email.addActionListener(Fenetre.getFenetre());
        password.addActionListener(Fenetre.getFenetre());
        submit.addActionListener(Fenetre.getFenetre());
        telephone.addActionListener(Fenetre.getFenetre());
        rib.addActionListener(Fenetre.getFenetre());
    }

    //////////////////////////////////////////////////

    public static PageSigninVendeur getPage() {
        return page;
    }
    public JTextField getNom() {
        return this.nom;
    }
    public JTextField getPrenom() {
        return this.prenom;
    }
    public JTextField getEmail() {
        return this.email;
    }
    public JTextField getPassword() {
        return this.password;
    }
    public JTextField getTelephone() {
        return this.telephone;
    }
    public JTextField getRib() {
        return this.rib;
    }
    public JButton getSubmit() {
        return this.submit;
    }

    //////////////////////////////////////////////////


    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Font font_lab = new Font("Arial",0,45);
        Font font = new Font("Arial",0,25);
        int tc=100;

        /*for(int x=0; x<40; x++){
            for(int y=0; y<21; y++){
                g.drawImage(fond, x*tc, y*tc, tc, tc, this);
            }
        }*/
        tc=50;

        //nom
        JLabel label_nom = new JLabel("nom : ");
        label_nom.setBounds(11* tc, 5 * tc, tc * 6, tc * 1);
        label_nom.setFont(font_lab);
        //label_nom.setBackground(Color.white);
        add(label_nom);
        nom.setBounds(20* tc, 5 * tc, tc * 6, tc * 1);
        nom.setFont(font);

        //prenom
        JLabel label_prenom = new JLabel("prenom :");
        label_prenom.setBounds(11* tc, 7 * tc, tc * 6, tc * 1);
        label_prenom.setFont(font_lab);
        //label_prenom.setBackground(Color.white);
        add(label_prenom);
        prenom.setBounds(20* tc, 7 * tc, tc * 6, tc * 1);
        prenom.setFont(font);

        //email
        JLabel label_email = new JLabel("email : ");
        label_email.setBounds(11* tc, 9 * tc, tc * 6, tc * 1);
        label_email.setFont(font_lab);
        //label_email.setBackground(Color.white);
        add(label_email);
        email.setBounds(20* tc, 9 * tc, tc * 6, tc * 1);
        email.setFont(font);

        //password
        JLabel label_pwd= new JLabel("password : ");
        label_pwd.setBounds(11* tc, 11 * tc, tc * 6, tc * 1);
        label_pwd.setFont(font_lab);
        //label_pwd.setBackground(Color.white);
        add(label_pwd);
        password.setBounds(20* tc, 11 * tc, tc * 6, tc * 1);
        password.setFont(font);

        //telephone
        JLabel label_tel= new JLabel("telephone : ");
        label_tel.setBounds(11* tc, 13 * tc, tc * 6, tc * 1);
        label_tel.setFont(font_lab);
        //label_tel.setBackground(Color.white);
        add(label_tel);
        telephone.setBounds(20* tc, 13 * tc, tc * 6, tc * 1);
        telephone.setFont(font);

        //rib
        JLabel label_rib= new JLabel("R.I.B : ");
        label_rib.setBounds(11* tc, 15 * tc, tc * 6, tc * 1);
        label_rib.setFont(font_lab);
        //label_rib.setBackground(Color.white);
        add(label_rib);
        rib.setBounds(20* tc, 15 * tc, tc * 6, tc * 1);
        rib.setFont(font);


        submit.setBounds(15* tc, 17 * tc, tc * 6, tc * 1);
        submit.setFont(font);
    }

}
