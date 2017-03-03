package de.akquinet.engineering.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
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
import de.akquinet.engineering.vaadin.buttonbar.ButtonBarView;
import de.akquinet.engineering.vaadin.events.EventsView;
import de.akquinet.engineering.vaadin.face.FaceView;
import de.akquinet.engineering.vaadin.layouts.LayoutsView;

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
        final View defaultView = new EventsView();
        // the start view needs to have the empty string as view name
        getNavigator().addView("", defaultView);
        getNavigator().addView(EventsView.VIEW_NAME, defaultView);
        getNavigator().addView(LayoutsView.VIEW_NAME, new LayoutsView());
        getNavigator().addView(FaceView.VIEW_NAME, new FaceView());
        getNavigator().addView(ButtonBarView.VIEW_NAME, new ButtonBarView());

        setContent(rootLayout);
    }

    private Component createNavigation(){

        final Image logo = new Image();
        logo.setSource(new ThemeResource("img/akquinet_logo.png"));
        logo.setWidth("150px");

        final VerticalLayout navigationLayout = new VerticalLayout();
        navigationLayout.setWidth("300px");
        navigationLayout.addComponent(logo);
        navigationLayout.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        navigationLayout.addComponent(new Label("<hr/>", ContentMode.HTML));
        navigationLayout.addComponent(createNavigationButton("Events and Listeners", EventsView.VIEW_NAME));
        navigationLayout.addComponent(createNavigationButton("Layouts", LayoutsView.VIEW_NAME));
        navigationLayout.addComponent(createNavigationButton("More Layouts", FaceView.VIEW_NAME));
        navigationLayout.addComponent(createNavigationButton("Button Bar", ButtonBarView.VIEW_NAME));

        return navigationLayout;
    }

    private Button createNavigationButton(final String buttonCaption, final String viewName){
        final Button button = new Button(buttonCaption);
        button.setStyleName(ValoTheme.BUTTON_LINK);
        button.addClickListener(e -> getNavigator().navigateTo(viewName));
        return button;
    }

    @WebServlet(urlPatterns = "/*", name = "BasicsUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = BasicsUI.class, productionMode = false)
    public static class BasicsUIServlet extends VaadinServlet {
    }
}
