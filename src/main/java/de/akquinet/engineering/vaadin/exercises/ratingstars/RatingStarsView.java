package de.akquinet.engineering.vaadin.exercises.ratingstars;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import de.akquinet.engineering.vaadin.ComponentView;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class RatingStarsView implements View, ComponentView
{
    public static final String VIEW_NAME = "ratingstars";

    private final VerticalLayout rootLayout = new VerticalLayout();

    public RatingStarsView(){

        final Label title = new Label("Please rate this course");
        title.addStyleName(ValoTheme.LABEL_H2);

        // TODO: after adding the maven dependency show the RatingStars add-on
        // Tips:
        // 1) create an instance of the RatingStars class
        // 2) use setValueCaption(<number 1..5>, <description>) to configure the stars and their descriptions

        final Button submitButton = new Button("submit", event -> {
            // TODO: show a notification
        });
        submitButton.setEnabled(false);

        // TODO: enable the submit button, when the rating is selected

        // TODO: add the rating stars to the layout

        rootLayout.addComponents(title, submitButton);
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
