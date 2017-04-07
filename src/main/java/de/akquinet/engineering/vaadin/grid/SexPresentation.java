package de.akquinet.engineering.vaadin.grid;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FontIcon;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public enum SexPresentation
{
    MALE(Sex.MALE, VaadinIcons.MALE, "male", "male"),

    FEMALE(Sex.FEMALE, VaadinIcons.FEMALE, "female", "female");

    private Sex sex;
    private FontIcon icon;
    private String name;
    private String styleName;

    SexPresentation(final Sex sex, final FontIcon icon,
                            final String name, final String styleName){
        this.sex = sex;
        this.icon = icon;
        this.name = name;
        this.styleName = styleName;
    }

    public Sex getSex()
    {
        return sex;
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

    public static SexPresentation getPresentation(final Sex sex){
        for (final SexPresentation sexPresentation : values()){
            if (sex == sexPresentation.sex){
                return sexPresentation;
            }
        }

        throw new IllegalArgumentException("argument not valid");
    }
}
