package de.akquinet.engineering.vaadin.demos.buffering;

import java.time.LocalDate;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class Contact
{
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;

    public Contact(){
        this("", "", "", null);
    }

    public Contact(final String name, final String email, final String phoneNumber, final LocalDate dateOfBirth)
    {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(final String email)
    {
        this.email = email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(final LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString()
    {
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
