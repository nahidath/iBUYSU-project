import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;


public class PageUser extends Page {
    private static PageUser page = new PageUser();
    

    private JButton modif_infos = new JButton("modifier infos");
    private JButton modif_password = new JButton("modifier password");
    private JButton delete_account = new JButton("delete account");
    private JButton mes_articles = new JButton("mes articles");
    private JButton disconnect = new JButton("se déconnecter");
    private JButton poster = new JButton("poster");
    private JButton devenir_vendeur = new JButton("devenir vendeur");
    private JButton articles_achetes = new JButton("articles achetés");

    private Image fond   = Imagerie.getImage("Divers/fond.jpeg");
    

    private Utilisateur contenu;



    private PageUser() {
        setPreferredSize(new Dimension(900,900));
        setLayout(null);

        initMyButton();

        addMyButton();

    }
    public void addMyButton(){
        removeAll();
        resetBounds();
        add(modif_infos);
        add(modif_password);
        add(delete_account);
        add(mes_articles);
        add(disconnect);
        add(poster);
        add(devenir_vendeur);
        add(articles_achetes);

    }
    public void resetBounds() {
        int tc = 50;
        mes_articles.setBounds(-1*tc, -1*tc, tc * -1, tc * -1);
        poster.setBounds(-1*tc, -1*tc, tc * -1, tc * -1);;
        devenir_vendeur.setBounds(-1*tc, -1*tc, tc * -1, tc * -1);;
        articles_achetes.setBounds(-1*tc, -1*tc, tc * -1, tc * -1);;
    }

    public void initMyButton(){
        Dimension dimension = new Dimension(100, 50);
        modif_infos.setPreferredSize(dimension);
        modif_password.setPreferredSize(dimension);
        delete_account.setPreferredSize(dimension);
        mes_articles.setPreferredSize(dimension);
        disconnect.setPreferredSize(dimension);
        poster.setPreferredSize(dimension);
        devenir_vendeur.setPreferredSize(dimension);
        articles_achetes.setPreferredSize(dimension);

        modif_infos.addActionListener(Fenetre.getFenetre());
        modif_password.addActionListener(Fenetre.getFenetre());
        delete_account.addActionListener(Fenetre.getFenetre());
        mes_articles.addActionListener(Fenetre.getFenetre());
        disconnect.addActionListener(Fenetre.getFenetre());
        poster.addActionListener(Fenetre.getFenetre());
        devenir_vendeur.addActionListener(Fenetre.getFenetre());
        articles_achetes.addActionListener(Fenetre.getFenetre());
    }

    ///////////////////////////////////////////


    public static PageUser getPage() {
        return page;
    }
    public JButton getModif_infos() {
        return this.modif_infos;
    }
    public JButton getModif_password() {
        return this.modif_password;
    }
    public JButton getDelete_account() {
        return this.delete_account;
    }
    public JButton getMes_articles() {
        return this.mes_articles;
    }
    public JButton getDisconnect() { return this.disconnect; }
    public JButton getPoster() {
        return this.poster;
    }
    public void setContenu(Utilisateur value) {
        this.removeAll();
        this.addMyButton();
        this.contenu = value;
    }
    public Utilisateur getContenu() { return this.contenu;}
    public JButton getDevenir_vendeur() {
        return this.devenir_vendeur;
    }
    public JButton getArticles_achetes() {
        return this.articles_achetes;
    }


    ////////////////////////////////////////////////////////////

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Font font = new Font("Arial",0,35);

        int tc=100;


        /*for(int x=0; x<40; x++){
            for(int y=0; y<21; y++){
                g.drawImage(fond, x*tc, y*tc, tc, tc, this);
            }
        }*/
        tc=50;
        //nom
        JLabel label_nom = new JLabel("Bonjour "+contenu.getPrenom()+", bienvenue");
        label_nom.setBounds((int)(14.5*tc), 5*tc, tc*10, tc);
        label_nom.setFont(font);
        //label_nom.setBackground(Color.white);
        add(label_nom);

        font = new Font("Arial",0,20);

        //infos
        String chaine_infos = "\n\n          Nom : "+contenu.getNom()+
                "\n\n\n\n       Prénom : "+contenu.getPrenom()+
                "\n\n\n\n               email : \n"+contenu.getEmail()+
                "\n\n\n         évaluations : "+( contenu.getNb_eval()>0 ? contenu.getEvaluation() : "\n    'pas encore évalué'");

        JTextArea infos = new JTextArea();
        infos.setEditable(false);
        infos.setBounds(16*tc,7*tc, tc * 6, tc*8);
        infos.setFont(font);
        infos.setLineWrap(true);
        infos.setWrapStyleWord(true);
        infos.setText(chaine_infos);
        add(infos);

        //modifier infos
        modif_infos.setBounds(17*tc, 16*tc, tc*4, tc );
        modif_infos.setFont(font);
        //modifier password
        modif_password.setBounds(16*tc, 18*tc, tc*6, tc );
        modif_password.setFont(font);
        //supprimer compte
        delete_account.setBounds(32*tc, 19*tc, tc*6, tc );
        delete_account.setFont(font);
        //disconnect
        disconnect.setBounds(32*tc, 4*tc, tc*6, tc );
        disconnect.setFont(font);


        if(Systeme.getType_usr().equals("vendeur")) {
            //mes articles
            mes_articles.setBounds(4 * tc, 8 * tc, tc * 6, tc);
            mes_articles.setFont(font);

            //poster
            poster.setBounds(4 * tc, (int)(10.5 * tc), tc * 6, tc);
            poster.setFont(font);

            //articles achetés
            articles_achetes.setBounds(4 * tc, 13 * tc, tc * 6, tc);
            articles_achetes.setFont(font);
        }

        if(Systeme.getType_usr().equals("acheteur")) {
            //devenir vendeur
            devenir_vendeur.setBounds(4 * tc, 13 * tc, tc * 6, tc);
            devenir_vendeur.setFont(font);

            //articles achetés
            articles_achetes.setBounds(4 * tc, 8 * tc, tc * 6, tc);
            articles_achetes.setFont(font);
        }



    }


}
