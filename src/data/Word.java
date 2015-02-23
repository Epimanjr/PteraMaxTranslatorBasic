/*
 * Manage a word.
 */
package data;

import java.util.Objects;

/**
 *
 * @author Maxime BLAISE
 */
public class Word {

    /**
     * Language of the word.
     */
    private Language language;

    /**
     * Name of the word.
     */
    private String name;

    /**
     * Phonetic of the word.
     */
    private String phonetic;

    /**
     * Create a word with a specific language, gender and name.
     *
     * @param language Language of the word.
     * @param name Name of the word.
     * @param phonetic Phonetic of the word.
     */
    public Word(Language language, String name, String phonetic) {
        this.language = language;
        this.name = name;
        this.phonetic = phonetic;
    }

    /**
     * Create a word with a specific language and a fileline.
     *
     * @param l Language
     * @param fileline Line of a file
     */
    public Word(Language l, String fileline) {
        this.language = l;
        String[] array = fileline.split(";");
        if (array.length >= 2) {
            this.name = array[0];
            this.phonetic = array[1];
        }
    }

    /**
     * Return a line to print into a file.
     *
     * @return String
     */
    public String toStringForFile() {
        return this.name + ";" + this.phonetic;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Word other = (Word) obj;
        return this.name.equalsIgnoreCase(other.name);
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    @Override
    public String toString() {
        return name;
    }
    
    

}
