import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

public class PagePoster extends Page {
    private static PagePoster page= new PagePoster();

    private JTextField titre = new JTextField();
    private JComboBox etat = new JComboBox(new String[]{"neuf","très bonne état", "bonne état", "passable", "bof", "abimé"}  );
    private JTextField localisation = new JTextField();
    private JTextField categorie = new JTextField();
    private JTextField prix = new JTextField();
    private ButtonGroup type_vente = new ButtonGroup();
    private JRadioButton vente_directe = new JRadioButton("directe");
    private JRadioButton vente_enchere = new JRadioButton("enchere");
    private JTextField duree = new JTextField();
    private JTextField mots_cles = new JTextField();
    private JTextField description = new JTextField();
    private JButton submit = new JButton("submit");
    private JButton fichier = new JButton("image");

    private Image fond   = Imagerie.getImage("Divers/fond.jpeg");
    


    private PagePoster() {
        setPreferredSize(new Dimension(900,900));
        setLayout(null);

        initMyButton();

        addMyButton();
    }
    public void addMyButton(){
        add(titre);
        add(etat);
        add(localisation);
        add(categorie);
        add(prix);
        type_vente.add(vente_directe);;
        type_vente.add(vente_enchere);
        add(vente_directe);
        add(vente_enchere);
        add(duree);
        add(mots_cles);
        add(description);
        add(submit);
        add(fichier);
    }

    public void initMyButton(){
        Dimension dimension = new Dimension(200, 100);
        titre.setPreferredSize(dimension);
        etat.setPreferredSize(dimension);
        localisation.setPreferredSize(dimension);
        categorie.setPreferredSize(dimension);
        prix.setPreferredSize(dimension);
        vente_directe.setPreferredSize(dimension);
        vente_directe.setSelected(true);
        vente_enchere.setPreferredSize(dimension);
        duree.setPreferredSize(dimension);
        mots_cles.setPreferredSize(dimension);
        description.setPreferredSize(dimension);
        submit.setPreferredSize(dimension);
        fichier.setPreferredSize(dimension);

        vente_directe.addActionListener(Fenetre.getFenetre());
        vente_enchere.addActionListener(Fenetre.getFenetre());
        submit.addActionListener(Fenetre.getFenetre());
        fichier.addActionListener(Fenetre.getFenetre());

    }

    //////////////////////////////////

    public static PagePoster getPage() {
       return page;
    }
    public JTextField getTitre() {
       return this.titre;
    }
    public JComboBox getEtat() {
        return this.etat;
    }
    public JTextField getLocalisation() {
        return this.localisation;
    }
    public JTextField getCategorie() {
        return this.categorie;
    }
    public JTextField getPrix() {
        return this.prix;
    }
    public ButtonGroup getType_vente() {
        return this.type_vente;
    }
    public JRadioButton getVente_directe() {
        return this.vente_directe;
    }
    public JRadioButton getVente_enchere() {
        return this.vente_enchere;
    }
    public JTextField getDuree() {
        return this.duree;
    }
    public JTextField getMots_cles() {
        return this.mots_cles;
    }
    public JTextField getDescription() {
        return this.description;
    }
    public JButton getSubmit() {
        return this.submit;
    }
    public JButton getFichier() {
        return this.fichier;
    }

    //////////////////////////////////

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Font font_lab = new Font("Arial",0,25);
        Font font = new Font("Arial",0,20);
        int tc=100;

        /*for(int x=0; x<40; x++){
            for(int y=0; y<21; y++){
                g.drawImage(fond, x*tc, y*tc, tc, tc, this);
            }
        }*/
        tc=50;

        //titre
        JLabel label_titre = new JLabel("titre : ");
        label_titre.setBounds(5* tc, 7 * tc, tc * 4, tc * 1);
        label_titre.setFont(font_lab);
        add(label_titre);
        titre.setBounds(10* tc, 7 * tc, tc * 6, tc * 1);
        titre.setFont(font);
        //etat
        JLabel label_etat = new JLabel("etat : ");
        label_etat.setBounds(5* tc, 9 * tc, tc * 4, tc * 1);
        label_etat.setFont(font_lab);
        add(label_etat);
        etat.setBounds(10* tc, 9 * tc, tc * 6, tc * 1);
        etat.setFont(font);
        //localisation
        JLabel label_loc = new JLabel("localisation : ");
        label_loc.setBounds(5* tc, 11 * tc, tc * 4, tc * 1);
        label_loc.setFont(font_lab);
        add(label_loc);
        localisation.setBounds(10* tc, 11 * tc, tc * 6, tc * 1);
        localisation.setFont(font);
        //categorie
        JLabel label_cat = new JLabel("categorie : ");
        label_cat.setBounds(5* tc, 13 * tc, tc * 4, tc * 1);
        label_cat.setFont(font_lab);
        add(label_cat);
        categorie.setBounds(10* tc, 13 * tc, tc * 6, tc * 1);
        categorie.setFont(font);
        //prix
        JLabel label_prix = new JLabel("prix : ");
        label_prix.setBounds(5* tc, 15 * tc, tc * 4, tc * 1);
        label_prix.setFont(font_lab);
        add(label_prix);
        prix.setBounds(10* tc, 15 * tc, tc * 6, tc * 1);
        prix.setFont(font);


        //vente directe
        JLabel label_type = new JLabel("type de vente : ");
        label_type.setBounds(21* tc, 7 * tc, tc * 6, tc * 1);
        label_type.setFont(font_lab);
        add(label_type);
        vente_directe.setBounds(30* tc, 7 * tc, tc * 3, tc * 1);
        vente_directe.setFont(font);
        //vente enchère
        vente_enchere.setBounds(33* tc, 7 * tc, tc * 3, tc * 1);
        vente_enchere.setFont(font);
        //duree
        JLabel label_duree = new JLabel("durée (si vente en enchère) : ");
        label_duree.setBounds(21* tc, 9 * tc, tc * 8, tc * 1);
        label_duree.setFont(font_lab);
        add(label_duree);
        duree.setBounds(30* tc, 9 * tc, tc * 6, tc * 1);
        duree.setFont(font);
        //mots cles
        JLabel label_kw = new JLabel("mots cles (séparés par des ';' : ");
        label_kw.setBounds(21* tc, 11 * tc, tc * 8, tc * 1);
        label_kw.setFont(font_lab);
        add(label_kw);
        mots_cles.setBounds(30* tc, 11 * tc, tc * 6, tc * 1);
        mots_cles.setFont(font);
        //description
        JLabel label_desc = new JLabel("description : ");
        label_desc.setBounds(21* tc, 13 * tc, tc * 6, tc * 1);
        label_desc.setFont(font_lab);
        add(label_desc);
        description.setBounds(30* tc, 13 * tc, tc * 6, tc * 3);
        description.setFont(font);
        //submit
        submit.setBounds(25* tc, 18 * tc, tc * 6, tc * 1);
        submit.setFont(font);
        //fichier
        fichier.setBounds(7* tc, 18 * tc, tc * 6, tc * 1);
        fichier.setFont(font);

    }

}
