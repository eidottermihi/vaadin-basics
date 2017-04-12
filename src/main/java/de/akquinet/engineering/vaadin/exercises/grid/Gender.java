package de.akquinet.engineering.vaadin.exercises.grid;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public enum Gender
{
    MALE,
    FEMALE;

    public static int compare(final Gender g1, final Gender g2) {
        return Integer.compare(g1.ordinal(), g2.ordinal());
    }
}
