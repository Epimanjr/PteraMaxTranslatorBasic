/*
 * Manage a language.
 */
package data;

/**
 *
 * @author Maxime BLAISE
 */
public enum Language {
    // Available language
    Czech("čeština", "cs"),
    French("français", "fr");
    
    /**
     * The name of the language.
     */
    private String name="";
    
    /**
     * ISO 639-1.
     */
    private String iso="";
    
    /**
     * Constructor of a language.
     * 
     * @param name
     * @param iso 
     */
    Language(String name, String iso) {
        this.name = name;
        this.iso = iso;
    }

    /**
     * Get the name of the language.
     * 
     * @return original name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the ISO code of this language.
     * 
     * @return ISO
     */
    public String getIso() {
        return iso;
    }
    
    
}