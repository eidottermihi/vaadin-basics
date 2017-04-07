/*
 * WEAT EABR
 */
package de.akquinet.engineering.vaadin.grid;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public enum Sex
{
    MALE,
    FEMALE;

    public static int compare(final Sex s1, final Sex s2) {
        return Integer.compare(s1.ordinal(), s2.ordinal());
    }
}
