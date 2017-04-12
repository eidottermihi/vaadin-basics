package de.akquinet.engineering.vaadin.exercises.databinding;

import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class Movie
{
    private String title;
    private int durationInMinutes;
    private Set<Locale> locales;
    private Genre genre;

    public Movie(){}

    public static Movie create(final String title, final int durationInMinutes,
                               final Set<Locale> locales, final Genre genre){
        final Movie movie = new Movie();
        movie.title = title;
        movie.durationInMinutes = durationInMinutes;
        movie.setLocales(locales);
        movie.genre = genre;
        return movie;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(final String title)
    {
        this.title = title;
    }

    public int getDurationInMinutes()
    {
        return durationInMinutes;
    }

    public void setDurationInMinutes(final int durationInMinutes)
    {
        this.durationInMinutes = durationInMinutes;
    }

    public Set<Locale> getLocales()
    {
        return Collections.unmodifiableSet(locales);
    }

    public void setLocales(final Set<Locale> locales)
    {
        this.locales = new HashSet<>(locales);
    }

    public Genre getGenre()
    {
        return genre;
    }

    public void setGenre(final Genre genre)
    {
        this.genre = genre;
    }

    @Override
    public String toString()
    {
        return "Movie{" +
                "title='" + title + '\'' +
                ", durationInMinutes=" + durationInMinutes +
                ", locales=" + locales +
                ", genre=" + genre +
                '}';
    }
}
