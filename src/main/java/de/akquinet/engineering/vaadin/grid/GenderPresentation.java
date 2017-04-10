package de.akquinet.engineering.vaadin.grid;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FontIcon;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public enum GenderPresentation
{
    MALE(Gender.MALE, VaadinIcons.MALE, "male", "male"),

    FEMALE(Gender.FEMALE, VaadinIcons.FEMALE, "female", "female");

    private Gender gender;
    private FontIcon icon;
    private String name;
    private String styleName;

    GenderPresentation(final Gender gender, final FontIcon icon,
                       final String name, final String styleName){
        this.gender = gender;
        this.icon = icon;
        this.name = name;
        this.styleName = styleName;
    }

    public Gender getGender()
    {
        return gender;
    }

    public FontIcon getIcon()
    {
        return icon;
    }

    public String getName()
    {
        return name;
    }

    public String getStyleName()
    {
        return styleName;
    }

    public static GenderPresentation getPresentation(final Gender gender){
        for (final GenderPresentation genderPresentation : values()){
            if (gender == genderPresentation.gender){
                return genderPresentation;
            }
        }

        throw new IllegalArgumentException("argument not valid");
    }
}
