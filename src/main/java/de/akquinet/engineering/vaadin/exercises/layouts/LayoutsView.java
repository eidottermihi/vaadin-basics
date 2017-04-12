package de.akquinet.engineering.vaadin.exercises.layouts;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
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
        header.setWidth("100%");
        header.setStyleName("header");
        header.setHeight("120px");
        final Label footer = new Label("This is the footer");
        footer.setWidth("100%");
        footer.setStyleName("footer");
        footer.setHeight("120px");
        final Label navigation = new Label("This is the navigation area");
        navigation.setStyleName("navigation");
        navigation.setHeight("100%");
        navigation.setWidth("250px");
        final Panel content = new Panel();
        content.setSizeFull();
        final VerticalLayout contentLayout = new VerticalLayout();
        content.setContent(contentLayout);
        for (int i=0; i<50; ++i){
            contentLayout.addComponent(new Label("Bla, bla, bla, ..."));
        }

        final HorizontalLayout main = new HorizontalLayout(navigation, content);
        main.setSizeFull();
        main.setMargin(false);
        main.setSpacing(false);
        main.setExpandRatio(content, 1.0f);
        rootLayout.addComponents(header, main, footer);
        rootLayout.setExpandRatio(main, 1.0f);
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
