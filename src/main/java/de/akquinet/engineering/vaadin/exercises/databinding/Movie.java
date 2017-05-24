package de.akquinet.engineering.vaadin.exercises.databinding;

import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class Movie
{
	@Size(min = 1, max = 20, message = "Title must be between 1 - 20 characters.")
    private String title;
	
	@NotNull
	@Min(value = 1, message = "Duration must be a positive number.")
    private int durationInMinutes;
	
	@Min(value = 1, message = "You need to choose at least one language.")
    private Set<Locale> locales;
	
	@NotNull(message = "You need to choose a genre.")
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
