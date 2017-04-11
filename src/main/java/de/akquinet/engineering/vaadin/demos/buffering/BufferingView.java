package de.akquinet.engineering.vaadin.demos.buffering;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.akquinet.engineering.vaadin.ComponentView;

import java.time.LocalDate;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class BufferingView implements View, ComponentView
{
    public static final String VIEW_NAME = "buffering";

    private final VerticalLayout rootLayout = new VerticalLayout();

    private boolean isBuffered = true;

    public BufferingView()
    {
        init();
    }

    private void init()
    {
        rootLayout.removeAllComponents();

        final TextField nameField = new TextField("Name");
        final TextField emailField = new TextField("Email");
        final TextField phoneField = new TextField("Phone");
        final DateField dateOfBirthField = new DateField("Birthday");

        final FormLayout formLayout = new FormLayout(nameField, emailField, phoneField, dateOfBirthField);

        final Contact contact = new Contact("John Doe", "john@doe.com", "555-123", LocalDate.of(1969, 7, 4));
        final Binder<Contact> binder = new Binder<>();
        binder.forField(nameField)
                .asRequired("name is mandatory")
                .bind(Contact::getName, Contact::setName);

        binder.forField(emailField)
                .withValidator(new EmailValidator("email is invalid"))
                .bind(Contact::getEmail, Contact::setEmail);

        binder.bind(phoneField, Contact::getPhoneNumber, Contact::setPhoneNumber);

        binder.forField(dateOfBirthField)
                .bind(Contact::getDateOfBirth, Contact::setDateOfBirth);


        if (isBuffered)
        {
            binder.readBean(contact);
        }
        else
        {
            // Loads the values from the person instance
            // Sets person to be updated when any bound field is updated
            binder.setBean(contact);
        }

        final Button saveButton = new Button("save", event ->
        {
            try
            {
                // write all or nothing depending on the validation
                binder.writeBean(contact);
            }
            catch (final ValidationException e)
            {
                Notification.show("validation error", Notification.Type.ERROR_MESSAGE);
            }
        });
        final Button cancelButton = new Button("cancel", event -> binder.readBean(contact));
        final Button showContactButton = new Button("showContact", event -> Notification.show(contact.toString()));
        final Button toggleBuffered = new Button();
        toggleBuffered.addClickListener(event ->
        {
            isBuffered = !isBuffered;
            init();
            updateToggleCaption(toggleBuffered);
        });
        updateToggleCaption(toggleBuffered);

        final HorizontalLayout buttonBar = new HorizontalLayout();
        buttonBar.addComponents(cancelButton, saveButton, showContactButton, toggleBuffered);
        rootLayout.addComponents(formLayout, buttonBar);
    }

    private void updateToggleCaption(final Button toggleBuffered)
    {
        toggleBuffered.setCaption("Buffered is " + (isBuffered ? "on" : "off"));
    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event)
    {

    }

    @Override
    public Component getComponent()
    {
        return rootLayout;
    }
}
