package data;

import java.util.ArrayList;

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
    
    
}
