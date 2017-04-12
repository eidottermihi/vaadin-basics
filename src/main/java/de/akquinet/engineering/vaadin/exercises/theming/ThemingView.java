package de.akquinet.engineering.vaadin.exercises.theming;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import de.akquinet.engineering.vaadin.ComponentView;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class ThemingView implements View, ComponentView
{
    public static final String VIEW_NAME = "theming";

    private final VerticalLayout rootLayout = new VerticalLayout();

    public ThemingView(){
        final BrowserWindowOpener opener = new BrowserWindowOpener(ThemingUI.class);

        final Button showThemeButton = new Button("Show Theme");
        opener.extend(showThemeButton);
        rootLayout.addComponent(showThemeButton);
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
