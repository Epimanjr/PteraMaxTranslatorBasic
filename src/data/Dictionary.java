package data;

import exception.AlreadyExistException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime
 */
public class Dictionary {

    /**
     * Language 1.
     */
    private Language language1;

    /**
     * Language 2.
     */
    private Language language2;

    /**
     * List of links.
     */
    private ArrayList<Link> listLinks;

    /**
     * Create a dictionary with specific languages.
     *
     * @param language1 .
     * @param language2 .
     */
    public Dictionary(Language language1, Language language2) {
        this.language1 = language1;
        this.language2 = language2;
        this.listLinks = new ArrayList<>();
    }

    /**
     * Insert a new link in the memory.
     *
     * @param l Link
     * @throws AlreadyExistException If the link already exist.
     */
    public void insertNewLink(Link l) throws AlreadyExistException {
        if (this.listLinks.contains(l)) {
            throw new AlreadyExistException();
        }
        // Insert
        this.listLinks.add(l);
        // Save in file
        this.saveList();
    }

    /**
     * Public method to initialize.
     */
    public final void init() {
        System.out.print("Init dictionary ... ");
        initializeListLinks();
        System.out.println("OK (" + this.listLinks.size() + " links.)");
    }

    /**
     * Initialize, that's to say search in file.
     */
    private void initializeListLinks() {
        // File name in which we can find all links.
        String filename = Config.folderName + "/" + Config.fileLinkName + this.language1.getIso() + this.language2.getIso() + ".pmt";
        BufferedReader br;
        try {
            // Init
            br = new BufferedReader(new FileReader(filename));

            // Loop
            while (br.ready()) {
                // Add to list
                this.listLinks.add(new Link(this.language1, this.language2, br.readLine()));
            }
            // Close
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error: file " + filename + " not found.");
        } catch (IOException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Language getLanguage1() {
        return language1;
    }

    public void setLanguage1(Language language1) {
        this.language1 = language1;
    }

    public Language getLanguage2() {
        return language2;
    }

    public void setLanguage2(Language language2) {
        this.language2 = language2;
    }

    public ArrayList<Link> getListLinks() {
        return listLinks;
    }

    public void setListLinks(ArrayList<Link> listLinks) {
        this.listLinks = listLinks;
    }

    /**
     * Save the list in a file.
     */
    private void saveList() {
        // File name in which we can find all links.
        String filename = Config.folderName + "/" + Config.fileLinkName + this.language1.getIso() + this.language2.getIso() + ".pmt";
        
        PrintWriter pw;
        try {
            // Init
            pw = new PrintWriter(new FileWriter(filename));
            // Loop
            for(Link l : listLinks) {
                pw.println(l.toStringForFile());
            }
            // Close
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
