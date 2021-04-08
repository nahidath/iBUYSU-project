import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ToolBar extends JPanel implements ActionListener {
    private Image img_logo =Imagerie.getImage("Icons/logo.jpeg");
    private Image img_connect = Imagerie.getImage("Icons/connect.png");
    private Image img_profil = Imagerie.getImage("Icons/profil.png");
    private Image img_search = Imagerie.getImage("Icons/search.jpg");
    private Image img_notif = Imagerie.getImage("Divers/red.png");

    private JButton logo = new JButton(new ImageIcon(img_logo.getScaledInstance(100,100, Image.SCALE_SMOOTH)));
    private JTextField mots_cles = new JTextField("Rechercher");
    private JComboBox categorie = new JComboBox( Memory.getMemory().getCategories() );
    private JButton connect = new JButton(new ImageIcon(img_connect.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
    private JButton profil = new JButton(new ImageIcon(img_profil.getScaledInstance(100,100, Image.SCALE_SMOOTH)));
    private JButton search = new JButton(new ImageIcon(img_search.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
    private JButton notif = new JButton(new ImageIcon(img_notif.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

    private static JToolBar barre = new JToolBar();
    private static ToolBar toolBar= new ToolBar();

    private ToolBar() {
        setPreferredSize (new Dimension(2000, 130) ) ;
        barre.setFloatable(false);
        //barre.setRollover(true);
        initMyButton();
        add(barre);
        addMyButton();
    }

    public void addMyButton(){
        barre.removeAll();
        barre.add( logo );
        barre.addSeparator(new Dimension(250,5));

        barre.add( categorie );
        barre.addSeparator(new Dimension(250,5));
        barre.add( mots_cles );
        barre.addSeparator(new Dimension(50,5));
        barre.add( search );
        barre.addSeparator(new Dimension(250,5));
        if( Systeme.isConnect() )
            barre.add( profil );
        else
            barre.add( connect );
        if( Systeme.isConnect() && Systeme.getType_usr().equals("vendeur") && Systeme.getVendeur().getNombreNonValable()>0 ){
            notif.setText(""+Systeme.getVendeur().getNombreNonValable());
            Outils.customButton(notif);
            barre.add(notif);
            barre.addSeparator(new Dimension(45,5));
        }else
            barre.addSeparator(new Dimension(100,5));
    }


    public static ToolBar getToolBar() { return toolBar; }

    public static JToolBar getBarre() { return barre; }

    public void initMyButton(){
        mots_cles.setPreferredSize(new Dimension(300, 100));
        mots_cles.setHorizontalAlignment(10);
        mots_cles.setFont(new Font("Rechercher", 0, 50));
        categorie.setPreferredSize(new Dimension(300,100));
        categorie.setFont(new Font("Arial",0,50));

        logo.addActionListener(this);
        connect.addActionListener(this);
        profil.addActionListener(this);
        search.addActionListener(this);

    }

    public JButton getLogo() {
        return this.logo;
    }

    public JTextField getMots_cles() {
        return this.mots_cles;
    }

    public JComboBox getCategorie() {
        return this.categorie;
    }

    public JButton getConnect() {
        return this.connect;
    }

    public JButton getProfil() {
        return this.profil;
    }

    public JButton getSearch() {
        return this.search;
    }


    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        ////////tool bar
        if (source == ToolBar.getToolBar().getLogo())
            Fenetre.getFenetre().backToHome();

        if (source == ToolBar.getToolBar().getSearch()) {
            String texte = ToolBar.getToolBar().getMots_cles().getText();
            if(texte.equals("Rechercher") || texte.equals("")){
                String selected = (String) ToolBar.getToolBar().getCategorie().getSelectedItem();
                Utilisateur.naviguerCategorie(selected);
            }else {
                String[] mots = texte.split(";");
                Utilisateur.naviguerMotsCles(mots);
            }
        }

        if (source == ToolBar.getToolBar().getConnect()) {
            Systeme.authenticate();
        }

        if (source == ToolBar.getToolBar().getProfil()) {
            Systeme.profil();
        }
    }
}
