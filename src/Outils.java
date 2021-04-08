import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.File;


public class Outils {
    public static boolean isEmail(String chaine){
        return chaine.endsWith("@gmail.com") || chaine.endsWith("@hotmail.fr") || chaine.endsWith("@yahoo.fr") || chaine.endsWith("@upmc.fr") || chaine.endsWith("@lip6.fr") || chaine.endsWith("@sorbonne-universite.fr");
    }

    public static boolean isNumero(String chaine){
        return (chaine.startsWith("06") || chaine.startsWith("07") ) && chaine.length()==10;
    }

    public static boolean isPassword(String chaine){
        boolean present_maj=false;
        boolean present_min=false;
        boolean present_spec=false;
        boolean present_num=false;
        for(int i=0; i<chaine.length(); i++){
            if(Character.isUpperCase(chaine.charAt(i)) )
                    present_maj=true;
            if(Character.isLowerCase(chaine.charAt(i)) )
                present_min=true;
            if(! Character.isLetterOrDigit(chaine.charAt(i)) )
                present_spec=true;
            if(Character.isDigit(chaine.charAt(i)) )
                present_num=true;
        }
        return present_maj && present_min && present_num && present_spec && chaine.length()>=8;
    }

    public static void attente(int x) {
        for(int i=0; i<x; i++){
            //System.out.println(".");
            try{ Thread.sleep(1000); }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Article> trierListeArticle(ArrayList<Article> liste, String type){
        ArrayList<Article> res=new ArrayList<>();
        Article a;
        int taille = liste.size();
        if(type.equals("plus récent")){
            for(int i=0; i<taille; i++){
                a=minDate(liste);
                liste.remove(a);
                res.add(a);
            }
        }else{
            if(type.equals("plus ancien")){
                for(int i=0; i<taille; i++) {
                    a = maxDate(liste);
                    liste.remove(a);
                    res.add(a);
                }
            }else{
                if(type.equals("moins cher")){
                    for(int i=0; i<taille; i++) {
                        a = minPrix(liste);
                        liste.remove(a);
                        res.add(a);
                    }
                }else{
                    if(type.equals("plus cher")){
                        for(int i=0; i<taille; i++) {
                            a = maxPrix(liste);
                            liste.remove(a);
                            res.add(a);
                        }
                    }
                }
            }
        }
        return res;
    }

    private static Article maxDate(ArrayList<Article> liste ){
        Article res= liste.get(0);
        for(Article a : liste){
            if( res.getDate().compareTo( a.getDate() ) > 0 )
                res=a;
        }
        return res;
    }

    private static Article minDate(ArrayList<Article> liste){
        Article res= liste.get(0);
        for(Article a : liste){
            if( res.getDate().compareTo( a.getDate() ) < 0 )
                res=a;
        }
        return res;
    }

    private static Article maxPrix(ArrayList<Article> liste){
        Article res= liste.get(0);
        for(Article a : liste){
            if( res.getPrix() < a.getPrix() )
                res=a;
        }
        return res;
    }

    private static Article minPrix(ArrayList<Article> liste){
        Article res= liste.get(0);
        for(Article a : liste){
            if( res.getPrix() > a.getPrix() )
                res=a;
        }
        return res;
    }

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

    public static File copyFile(File source) throws IOException {
        File dest = new File("dest");
        Files.copy(source.toPath(), dest.toPath());

        return dest;
    }

    public static void customButton(JButton button){
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setForeground(Color.white);
        button.setFont( new Font(Font.DIALOG,Font.BOLD,25));
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);

        //button.setBackground( new Color(218,112,166) );
    }

}
