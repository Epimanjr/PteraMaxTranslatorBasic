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
     * Gender of the word.
     */
    private Gender gender;

    /**
     * Name of the word.
     */
    private String name;

    /**
     * Create a word with a specific language, gender and name.
     *
     * @param language Language of the word.
     * @param gender Gender of the word.
     * @param name Name of the word.
     */
    public Word(Language language, Gender gender, String name) {
        this.language = language;
        this.gender = gender;
        this.name = name;
    }

    /**
     * Check if the word already exists in file.
     *
     * @return boolean
     * @throws java.io.FileNotFoundException If the file not exists.
     * @throws exception.BadLineException If there is bad line.
     */
    public boolean existInFile() throws FileNotFoundException, IOException, BadLineException {
        // Init filename
        String filename = Config.folderName + "/" + Config.fileWordName + language.getIso() + ".pmt";
        // Read the file
        BufferedReader br = new BufferedReader(new FileReader(filename));
        while (br.ready()) {
            String[] array = br.readLine().split(";");
            if (array.length < 2) {
                br.close();
                throw new BadLineException();
            }
            if (this.name.equals(array[1])) {
                br.close();
                return true;
            }
        }
        br.close();
        return false;
    }

    /**
     * Insert the word in a file.
     *
     * @throws IOException IOException
     * @throws java.io.FileNotFoundException FileNotFoundException
     * @throws exception.BadLineException BadLineException
     * @throws exception.AlreadyExistException AlreadyExistException
     */
    public void insertInFile() throws IOException, FileNotFoundException, BadLineException, AlreadyExistException {
        if(existInFile()) {
            throw new AlreadyExistException();
        }
        // Init filename
        String filename = Config.folderName + "/" + Config.fileWordName + language.getIso()+ ".pmt";
        // Write in the file
        PrintWriter pw = new PrintWriter(new FileWriter(filename, true));
        pw.println(toStringForFile());
        pw.close();
    }

    /**
     * Return a line to print into a file.
     *
     * @return String
     */
    public String toStringForFile() {
        return this.gender + ";" + this.name;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
