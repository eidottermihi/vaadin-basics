package de.akquinet.engineering.vaadin.exercises.databinding;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class Genre
{
    private final String basicGenre;
    private final String subGenre;

    public Genre(final String basicGenre, final String subGenre)
    {
        this.basicGenre = basicGenre;
        this.subGenre = subGenre;
    }

    public String getBasicGenre()
    {
        return basicGenre;
    }

    public String getSubGenre()
    {
        return subGenre;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        final Genre genre = (Genre) o;

        if (basicGenre != null ? !basicGenre.equals(genre.basicGenre) : genre.basicGenre != null)
        {
            return false;
        }
        return subGenre != null ? subGenre.equals(genre.subGenre) : genre.subGenre == null;

    }

    @Override
    public int hashCode()
    {
        int result = basicGenre != null ? basicGenre.hashCode() : 0;
        result = 31 * result + (subGenre != null ? subGenre.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Genre{" +
                "basicGenre='" + basicGenre + '\'' +
                ", subGenre='" + subGenre + '\'' +
                '}';
    }
}
