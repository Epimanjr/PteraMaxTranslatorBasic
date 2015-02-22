/*
 * Manage a word.
 */
package data;

import exception.AlreadyExistException;
import exception.BadLineException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * Type of the word.
     */
    private String type;

    /**
     * Gender of the word.
     */
    private String gender;

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
     * @param type Type of the word.
     * @param gender Gender of the word.
     * @param name Name of the word.
     * @param phonetic Phonetic of the word.
     */
    public Word(Language language, String type, String gender, String name, String phonetic) {
        this.language = language;
        this.type = type;
        this.gender = gender;
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
        if (array.length >= 4) {
            this.type = array[0];
            this.gender = array[1];
            this.name = array[2];
            this.phonetic = array[3];
        }
    }

    /**
     * Return a line to print into a file.
     *
     * @return String
     */
    public String toStringForFile() {
        return this.type + ";" + this.gender + ";" + this.name + ";" + this.phonetic;
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

}
