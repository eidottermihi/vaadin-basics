package de.akquinet.engineering.vaadin.grid;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class Player
{
    private String name;
    private LocalDate dateOfBirth;
    private Sex sex;
    private long points;
    private int medals;

    public Player()
    {
    }

    public Player(final String name, final LocalDate dateOfBirth,
                  final Sex sex, final long points, final int medals)
    {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.points = points;
        this.medals = medals;
    }

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(final LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge(){
        final LocalDate now = LocalDate.now(ZoneId.of("UTC"));
        return Period.between(dateOfBirth, now).getYears();
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public Sex getSex()
    {
        return sex;
    }

    public void setSex(final Sex sex)
    {
        this.sex = sex;
    }

    public long getPoints()
    {
        return points;
    }

    public void setPoints(final long points)
    {
        this.points = points;
    }

    public int getMedals()
    {
        return medals;
    }

    public void setMedals(final int medals)
    {
        this.medals = medals;
    }
}
