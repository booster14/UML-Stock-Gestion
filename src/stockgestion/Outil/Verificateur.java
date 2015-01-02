package stockgestion.Outil;

import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTextField;

public class Verificateur {
    
    //Il ne faut pas de caracteres speciales qui fait merder les requetes sql, comme le ', ", etc
    //Et faut que string non vide
    public static boolean isValidString(String texte){
        return !texte.equals("");
    }    
    
    //String non vide puis je sais pas....
    public static boolean isValidInt(String texte){
        return !texte.equals("");
    }
    
    //String non vide etc
    public static boolean isValidDouble(String texte){
        return !texte.equals("");
    }
    
    public static boolean isValidSelection(JList list){
        return list.getSelectedIndices().length > 0;
    }
    
    //Mettre le champs non valide en rouge
    public static void error(JComponent component){
        component.setBackground(Color.red);
    }
    
    public static void valid(JComponent component){
        component.setBackground(Color.white);
    }
    
    public static boolean isValideArticle(JTextField nom, JTextField codeBarre, JTextField prix, JTextField quantite, JTextField seuil, JList fournisseur){
        boolean valid = true;
        
        if(!isValidString(nom.getText())){
            valid = false;
            error(nom);
        } else{
            valid(nom);
        }
        
        if(!isValidInt(codeBarre.getText())){
            valid = false;
            error(codeBarre);
        } else{
            valid(codeBarre);
        }
        
        if(!isValidDouble(prix.getText())){
            valid = false;
            error(prix);
        } else{
            valid(prix);
        }
        
        if(!isValidInt(quantite.getText())){
            valid = false;
            error(quantite);
        } else{
            valid(quantite);
        }
        
        if(!isValidInt(seuil.getText())){
            valid = false;
            error(seuil);
        } else{
            valid(seuil);
        }
        
        if(!isValidSelection(fournisseur)){
            valid = false;
            error(fournisseur);
        } else{
            valid(fournisseur);
        }  
        
        return valid;
    }
    
    public static boolean isValideFournisseur(JTextField nom, JTextField adresse, JTextField codePostal, JTextField numTel){
        boolean valid = true;
        
        if(!isValidString(nom.getText())){
            valid = false;
            error(nom);
        } else{
            valid(nom);
        }       
        
        if(!isValidString(adresse.getText())){
            valid = false;
            error(adresse);
        } else{
            valid(adresse);
        }   
        
        if(!isValidInt(codePostal.getText())){
            valid = false;
            error(codePostal);
        } else{
            valid(codePostal);
        }        
        
        if(!isValidInt(numTel.getText())){
            valid = false;
            error(numTel);
        } else{
            valid(numTel);
        }
        
        return valid;
    }
}
