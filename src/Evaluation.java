import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Evaluation extends JPanel {
    private Utilisateur evaluateur;
    private Utilisateur evalue;
    private int note;
    private String commentaire;



    public Evaluation(Utilisateur evaluateur, Utilisateur evalue, String note, String commentaire) {
        setPreferredSize (new Dimension(1000, 100) ) ;
        setLayout(null);

        this.evaluateur=evaluateur;
        this.evalue=evalue;
        this.note= Integer.valueOf(note);
        this.commentaire=commentaire;

    }

    public JTextPane dessiner(){

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        //textPane
        SimpleAttributeSet style = new SimpleAttributeSet();
        StyleConstants.setFontFamily(style, "Calibri");
        StyleConstants.setFontSize(style, 20);

        SimpleAttributeSet align = new SimpleAttributeSet();
        StyleConstants.setAlignment(align, StyleConstants.ALIGN_LEFT);

        StyledDocument doc = textPane.getStyledDocument();

        try {
            doc.insertString(doc.getLength(), evaluateur.getNom()+" "+evaluateur.getPrenom()+" : "+note+"\n"+commentaire, style );

        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        return textPane;

    }
}
