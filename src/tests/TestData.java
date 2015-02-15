
package tests;

import data.Gender;
import data.Language;
import data.Link;
import data.Word;
import exception.AlreadyExistException;
import exception.BadLineException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime
 */
public class TestData {
    
    public static void main(String[] args) {
        Word w1 = new Word(Language.Czech, Gender.Masculine, "w1cs");
        try {
            w1.insertInFile();
            System.out.println("First attempt OK");
            w1.insertInFile();
            System.out.println("Second attempt OK");
        } catch (IOException | BadLineException | AlreadyExistException ex) {
            Logger.getLogger(TestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Link l1 = new Link(w1, new Word(Language.French, Gender.Masculine, "w2fr"));
        try {
            l1.insertInFile();
            System.out.println("First attempt OK");
            l1.insertInFile();
            System.out.println("Second attempt OK");
        } catch (IOException | BadLineException | AlreadyExistException ex) {
            Logger.getLogger(TestData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
