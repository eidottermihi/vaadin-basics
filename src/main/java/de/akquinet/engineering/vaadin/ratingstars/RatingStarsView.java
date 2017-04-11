package de.akquinet.engineering.vaadin.ratingstars;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import de.akquinet.engineering.vaadin.ComponentView;
import org.vaadin.teemu.ratingstars.RatingStars;

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

        final RatingStars ratingStars = new RatingStars();
        ratingStars.setValueCaption(1, "sh**!!!");
        ratingStars.setValueCaption(2, "poor");
        ratingStars.setValueCaption(3, "average");
        ratingStars.setValueCaption(4, "good");
        ratingStars.setValueCaption(5, "akquitastic!");

        final Button submitButton = new Button("submit", event -> {
            if (ratingStars.getValue() > 3.5){
                Notification.show("Thank you!");
            }
            else {
                Notification.show("Invalid rating, please try again!", Notification.Type.ERROR_MESSAGE);
            }
        });
        submitButton.setEnabled(false);
        ratingStars.addValueChangeListener(event -> submitButton.setEnabled(true));

        rootLayout.addComponents(title, ratingStars, submitButton);
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
