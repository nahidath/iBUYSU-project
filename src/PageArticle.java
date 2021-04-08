import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;


public class PageArticle extends Page {
    private static PageArticle page = new PageArticle();

    private JButton acheter = new JButton("acheter");
    private JButton accepter_vente = new JButton("accepter vente");
    private JButton refuser_vente = new JButton("refuser vente");
    private JButton retirer_vente = new JButton("retirer vente");
    private JButton edit = new JButton("edit");
    private JButton evaluer = new JButton("évaluer");
    private JButton voirUsr = new JButton("voir l'usr");

    private Article contenu;

    private Image fond   = Imagerie.getImage("Divers/fond.jpeg");



    private PageArticle() {
        setPreferredSize(new Dimension(900,900));
        setLayout(null);

        initMyButton();

        addMyButton();

    }

    public void addMyButton(){
        removeAll();
        resetBounds();
        add(acheter);
        add(accepter_vente);
        add(retirer_vente);
        add(edit);
        add(evaluer);
        add(refuser_vente);
        add(voirUsr);
    }

    public void resetBounds(){
        int tc=50;
        acheter.setBounds(-1*tc, -1*tc, tc * -1, tc * -1);
        accepter_vente.setBounds(-1*tc, -1*tc, tc * -1, tc * -1);
        retirer_vente.setBounds(-1*tc, -1*tc, tc * -1, tc * -1);
        edit.setBounds(-1*tc, -1*tc, tc * -1, tc * -1);
        evaluer.setBounds(-1*tc, -1*tc, tc * -1, tc * -1);
        refuser_vente.setBounds(-1*tc, -1*tc, tc * -1, tc * -1);
        voirUsr.setBounds(-1*tc, -1*tc, tc * -1, tc * -1);
    }

    public void initMyButton() {
        //Dimension dimension = new Dimension(100, 50);
        /*acheter.setPreferredSize(dimension);
        accepter_vente.setPreferredSize(dimension);
        retirer_vente.setPreferredSize(dimension);
        edit.setPreferredSize(dimension);
        evaluer.setPreferredSize(dimension);*/

        acheter.addActionListener(Fenetre.getFenetre());
        accepter_vente.addActionListener(Fenetre.getFenetre());
        retirer_vente.addActionListener(Fenetre.getFenetre());
        edit.addActionListener(Fenetre.getFenetre());
        evaluer.addActionListener(Fenetre.getFenetre());
        refuser_vente.addActionListener(Fenetre.getFenetre());
        voirUsr.addActionListener(Fenetre.getFenetre());
        

    }

    //////////////////////////////////////
    
    public static PageArticle getPage(){ return page; }
    public Article getContenu(){ return this.contenu; }
    public void setContenu(Article value){
        if(value!=null) {
            this.addMyButton();
            this.contenu = value;
        }
    }
    public JButton getAcheter(){ return this.acheter; }
    public JButton getAccepter_vente(){ return this.accepter_vente; }
    public JButton getRetirer_vente(){ return this.retirer_vente; }
    public JButton getEdit(){ return this.edit; }
    public JButton getEvaluer(){ return this.evaluer; }
    public JButton getRefuser_vente() {return refuser_vente;}
    public JButton getVoirUsr() { return voirUsr; }
    /////////////////////////////////////

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Font font = new Font("Arial",0,20);

        int tc=100;

        /*for(int x=0; x<40; x++){
            for(int y=0; y<21; y++){
                g.drawImage(fond, x*tc, y*tc, tc, tc, this);
            }
        }*/

        tc=50;
        //g.setFont(new Font("Arial",0,20));
        //titre
        JLabel label_titre = new JLabel(contenu.getTitre());
        label_titre.setBounds(4*tc,6*tc, tc * 4, tc);
        label_titre.setFont(font);
        //label_titre.setBackground(Color.white);
        add(label_titre);
        //description
        JTextArea text_description = new JTextArea();
        text_description.setEditable(false);
        text_description.setBounds(2*tc,8*tc, tc * 6, tc*8);
        text_description.setFont(font);
        text_description.setLineWrap(true);
        text_description.setWrapStyleWord(true);
        text_description.setText(contenu.getDescription());
        add(text_description);
        //image
        g.drawImage(contenu.getImage(), 13 * tc ,5 * tc, tc * 10, tc *10, this);
        //bloc droite
        JTextArea multi = new JTextArea();
        multi.setBounds(26*tc, 7*tc, tc*6, tc*11);
        multi.setFont(font);
        multi.setEditable(false);
        multi.setLineWrap(true);
        multi.setWrapStyleWord(true);
            //prix
        if( contenu instanceof ArticleParEnchere )
            multi.append("\nprix : "+((ArticleParEnchere) contenu).getPrix_actuel()+"€\n\n\n");
        else
            multi.append("\nprix : "+contenu.getPrix()+"€\n\n\n");

            //état
        multi.append("état : "+contenu.getEtat()+"\n\n\n");
            //localisation
        multi.append("à aller chercher à "+contenu.getLocalisation()+"\n\n\n");
            //vendeur
        multi.append("vendu par "+Memory.getMemory().articleQuelVendeur(contenu).getNom() + " " + Memory.getMemory().articleQuelVendeur(contenu).getPrenom()+"\n\n\n");
        add(multi);

        if( contenu instanceof ArticleParEnchere ){
            String d = ((ArticleParEnchere) contenu).dureeFormat()[0]+"j "+((ArticleParEnchere) contenu).dureeFormat()[1]+"h "+((ArticleParEnchere) contenu)  .dureeFormat()[2]+"m";
            multi.append( "il reste "+ d +" pour enchérir\n\n\n" );
            if(((ArticleParEnchere) contenu).getAcheteur_actuel() != null)
                multi.append(" plus haute enchère par "+((ArticleParEnchere) contenu).getAcheteur_actuel().getNom()+" "+((ArticleParEnchere) contenu).getAcheteur_actuel().getPrenom());
        }

        if( Systeme.isConnect() ) {
            //acheter
            if (contenu.isValable() && (Systeme.getType_usr().equals("acheteur") || (Systeme.getType_usr().equals("vendeur") && Memory.getMemory().articleQuelVendeur(contenu) != Systeme.getVendeur()))) {
                acheter.setBounds(16 * tc, 17 * tc, tc * 4, tc * 1);
                acheter.setFont(font);
            }

            //accepter vente
            if (Systeme.getType_usr().equals("vendeur") && Memory.getMemory().articleQuelVendeur(contenu) == Systeme.getVendeur() && !contenu.isValable() & !contenu.isAcheter()) {
                if (Memory.getMemory().articleParVendeur(Systeme.getVendeur()).contains(contenu)) {
                    accepter_vente.setBounds((int)(13.5 * tc), 17 * tc, tc * 4, tc * 1);
                    accepter_vente.setFont(font);
                }
            }
            if (Systeme.getType_usr().equals("vendeur") && Memory.getMemory().articleQuelVendeur(contenu) == Systeme.getVendeur() && !contenu.isValable() & !contenu.isAcheter()) {
                if (Memory.getMemory().articleParVendeur(Systeme.getVendeur()).contains(contenu)) {
                    refuser_vente.setBounds((int)(18.5 * tc), 17 * tc, tc * 4, tc * 1);
                    refuser_vente.setFont(font);
                }
            }

            //retirer vente et edit
            if (Systeme.getType_usr().equals("vendeur") & !contenu.isAcheter()) {
                if (Memory.getMemory().articleQuelVendeur(contenu) == Systeme.getVendeur()) {
                    retirer_vente.setBounds(33 * tc, 4 * tc, tc * 4, tc * 1);
                    retirer_vente.setFont(font);

                    edit.setBounds(33 * tc, 10 * tc, tc * 4, tc * 1);
                    edit.setFont(font);
                }
            }
            //evaluer
            if (contenu.isAcheter()) {
                multi.append("numéro de tel : " + Memory.getMemory().articleQuelVendeur(contenu).getNumero_tel());
            }
            if (contenu.isAcheter()) {
                if (Systeme.getType_usr().equals("acheteur") && !contenu.isEvaluationAch()) {
                    evaluer.setBounds(16 * tc, 17 * tc, tc * 4, tc * 1);
                    evaluer.setFont(font);
                }
                if (Systeme.getType_usr().equals("vendeur")) {
                    if(Memory.getMemory().articleQuelVendeur(contenu) == Systeme.getVendeur() && !contenu.isEvaluationVen()) {
                        evaluer.setBounds(16 * tc, 17 * tc, tc * 4, tc * 1);
                        evaluer.setFont(font);
                    }
                    if(Memory.getMemory().articleQuelVendeur(contenu) != Systeme.getVendeur() && !contenu.isEvaluationAch()) {
                        evaluer.setBounds(16 * tc, 17 * tc, tc * 4, tc * 1);
                        evaluer.setFont(font);
                    }
                }

            }

            if( Systeme.getType_usr().equals("acheteur") || (Systeme.getType_usr().equals("vendeur") && Memory.getMemory().articleQuelVendeur(contenu) != Systeme.getVendeur() ) ){
                voirUsr.setBounds( 33 * tc, 4 * tc, tc * 4, tc * 1 );
                voirUsr.setFont(font);
            }
        }

    }



}
