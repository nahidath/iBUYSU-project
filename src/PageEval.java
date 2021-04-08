import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class PageEval extends Page{
    private static PageEval page = new PageEval();
    private Utilisateur evalue;
    private ArrayList<Evaluation> contenu = new ArrayList<Evaluation>();

    private JButton retour = new JButton("retour");
    private JLabel texteMessage;

    private JTextPane textPane = new JTextPane();
    private JScrollPane jScrollPane= new JScrollPane(textPane);

    public PageEval(){
        setPreferredSize (new Dimension(1000, 850) ) ;
        setLayout(null);

        /*
        try {
            dessiner();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }*/
        addMyButton();
        initMyButton();

        add(jScrollPane);
        jScrollPane.setBounds(0,0,1000,800);
        textPane.setEditable(false);
    }

    public static PageEval getPage() {
        return page;
    }

    public void addMyButton(){
        if(texteMessage != null)
            remove(texteMessage);
        add(retour);
    }
    public void initMyButton(){
        retour.addActionListener(Fenetre.getFenetre());
    }

    public JButton getRetour() {
        return retour;
    }

    public void setContenu(ArrayList<Evaluation> e){
        addMyButton();
        contenu=e;
    }
    public void setEvalue(Utilisateur u){
        evalue=u;
    }

    public void dessiner() throws BadLocationException {
        StyledDocument doc = textPane.getStyledDocument();
        textPane.getDocument().remove(0, doc.getLength() );
        for(int i=0; i<contenu.size(); i++){
            Evaluation eval = contenu.get(i);
            textPane.insertComponent( eval.dessiner() );

            SimpleAttributeSet style = new SimpleAttributeSet();
            StyleConstants.setFontFamily(style, "Calibri");
            StyleConstants.setFontSize(style, 20);
            try {
                doc.insertString(doc.getLength(), "\n\n", style );
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }

        texteMessage = new JLabel("Voici les évaluations de "+evalue.getPrenom());
        int tc=50;
        Font font = new Font("Arial",0,25);
        texteMessage.setBounds(0, 800, tc * 15, tc);
        texteMessage.setFont(font);
        retour.setBounds(tc*15,800, tc * 3, tc);
        retour.setFont(font);
        add(texteMessage);

    }

}
