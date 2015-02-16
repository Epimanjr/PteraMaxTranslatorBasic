package data;

import exception.AlreadyExistException;
import exception.BadLineException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Maxime BLAISE
 */
public class Link {

    /**
     * First word of the link.
     */
    private final Word word1;

    /**
     * Second word of the link.
     */
    private final Word word2;

    /**
     * Create a link with specifics words.
     *
     * @param word1 .
     * @param word2 .
     */
    public Link(Word word1, Word word2) {
        this.word1 = word1;
        this.word2 = word2;
    }

    /**
     * Check if the link already exists in file.
     *
     * @return boolean
     * @throws java.io.FileNotFoundException If the file not exists.
     * @throws exception.BadLineException If there is bad line.
     */
    public boolean existInFile() throws FileNotFoundException, IOException, BadLineException {
        // Init filename
        String filename = Config.folderName + "/" + Config.fileLinkName + this.word1.getLanguage().getIso() + this.word2.getLanguage().getIso() + ".pmt";
        // Read the file
        BufferedReader br = new BufferedReader(new FileReader(filename));
        while (br.ready()) {
            String[] array = br.readLine().split(";");
            if (array.length < 4) {
                br.close();
                throw new BadLineException();
            }
            if (this.word1.getName().equals(array[1]) && this.word2.getName().equals(array[3])) {
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
     * @throws java.io.FileNotFoundException
     * @throws exception.BadLineException
     * @throws exception.AlreadyExistException
     */
    public void insertInFile() throws IOException, FileNotFoundException, BadLineException, AlreadyExistException {
        if (existInFile()) {
            throw new AlreadyExistException();
        }
        // Init filename
        String filename = Config.folderName + "/" + Config.fileLinkName + this.word1.getLanguage().getIso() + this.word2.getLanguage().getIso() + ".pmt";
        try ( // Write in the file
                PrintWriter pw = new PrintWriter(new FileWriter(filename, true))) {
            pw.println(toStringForFile());
        }
    }

    /**
     * Return a line to print into a file.
     *
     * @return String
     */
    public String toStringForFile() {
        return this.word1.toStringForFile() + ";" + this.word2.toStringForFile();
    }

    public Word getWord1() {
        return word1;
    }

    public Word getWord2() {
        return word2;
    }

}
