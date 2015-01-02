package stockgestion.Outil;

import java.awt.Color;
import javax.swing.JComponent;

public class Verificateur {
    
    //Il ne faut pas de caracteres speciales qui fait merder les requetes sql, comme le ', ", etc
    //Et faut que string non vide
    public static boolean isValidString(String texte){
        return false;
    }    
    
    //String non vide puis je sais pas....
    public static boolean isValidInt(String texte){
        try{
            Integer.parseInt(texte);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
    //String non vide etc
    public static boolean isValidDouble(String texte){
        try{
            Double.parseDouble(texte);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
    //Mettre le champs non valide en rouge
    public static void error(JComponent component){
        component.setBackground(Color.red);
    }
}
