package de.akquinet.engineering.vaadin.exercises.layouts;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import de.akquinet.engineering.vaadin.ComponentView;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class LayoutsView implements ComponentView, View
{
    public static final String VIEW_NAME = "layouts";

    private final VerticalLayout rootLayout = new VerticalLayout();

    public LayoutsView()
    {
        final Label header = new Label("This is the header");
        header.setStyleName("header");

        final Label footer = new Label("This is the footer");
        footer.setStyleName("footer");

        final Label navigation = new Label("This is the navigation area");
        navigation.setStyleName("navigation");

        final Panel content = new Panel();

        // fill the content
        final VerticalLayout contentLayout = new VerticalLayout();
        content.setContent(contentLayout);
        for (int i=0; i<50; ++i){
            contentLayout.addComponent(new Label("Bla, bla, bla, ..."));
        }

        // TODO: do the layout
        // set the header and the footer to 120px height
        // set the navigation area's width to 250px
        // Tips:
        // 1) use sizing functions, setMargin(..), setSpacing(..) and setExpandRatio(..)
        // 2) remember to add the components to the layout before calling setExpandRatio

        rootLayout.setSpacing(false);
        rootLayout.setSizeFull();
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
