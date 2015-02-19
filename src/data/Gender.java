package data;

/**
 *
 * @author Maxime BLAISE
 */
public enum Gender {

    Masculine("M"),
    Feminine("F"),
    Neutral("N");

    /**
     * Gender.
     */
    private final String gender;

    /**
     * Constructor of a gender.
     * 
     * @param gender .
     */
    private Gender(String gender) {
        this.gender = gender;
    }

    /**
     * Get gender.
     *
     * @return String
     */
    public String getGender() {
        return gender;
    }

}
