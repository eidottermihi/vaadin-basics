package de.akquinet.engineering.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import de.akquinet.engineering.vaadin.exercise1.Exercise1;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("basicstheme")
public class BasicsUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final HorizontalLayout rootLayout = new HorizontalLayout();
        rootLayout.setSizeFull();

        rootLayout.addComponent(createNavigation());

        final Panel contentPanel = new Panel();
        contentPanel.setSizeFull();
        rootLayout.addComponent(contentPanel);
        rootLayout.setExpandRatio(contentPanel, 1f);

        setNavigator(new Navigator(this, new CustomViewDisplay(contentPanel)));
        getNavigator().addView(Exercise1.VIEW_NAME, new Exercise1());
        getNavigator().navigateTo(Exercise1.VIEW_NAME);
        
        setContent(rootLayout);
    }

    private Component createNavigation(){

        final Image logo = new Image();
        logo.setSource(new ThemeResource("img/akquinet_logo.png"));
        logo.setWidth("150px");

        final VerticalLayout navigationLayout = new VerticalLayout();
        navigationLayout.setWidth("200px");
        navigationLayout.addComponent(logo);
        navigationLayout.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        navigationLayout.addComponent(new Label("<hr/>", ContentMode.HTML));
        navigationLayout.addComponent(createNavigationButton("Exercise 1", Exercise1.VIEW_NAME));

        return navigationLayout;
    }

    private Button createNavigationButton(final String buttonCaption, final String viewName){
        final Button button = new Button(buttonCaption);
        button.setStyleName(ValoTheme.BUTTON_LINK);
        return button;
    }

    @WebServlet(urlPatterns = "/*", name = "BasicsUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = BasicsUI.class, productionMode = false)
    public static class BasicsUIServlet extends VaadinServlet {
    }
}
