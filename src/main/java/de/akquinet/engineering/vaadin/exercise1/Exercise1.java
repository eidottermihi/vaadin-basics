package de.akquinet.engineering.vaadin.exercise1;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import de.akquinet.engineering.vaadin.ComponentView;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class Exercise1 implements ComponentView, View
{
    public static final String VIEW_NAME = "Exercise1";

    private final VerticalLayout verticalLayout = new VerticalLayout();

    @Override
    public Component getComponent()
    {
        return verticalLayout;
    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event)
    {
        verticalLayout.addComponent(new Label("Hello World!"));
    }
}
