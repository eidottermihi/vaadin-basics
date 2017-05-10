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
import de.akquinet.engineering.vaadin.demos.binding.BindingView;
import de.akquinet.engineering.vaadin.demos.buffering.BufferingView;
import de.akquinet.engineering.vaadin.exercises.buttonbar.ButtonBarView;
import de.akquinet.engineering.vaadin.exercises.databinding.DataBindingView;
import de.akquinet.engineering.vaadin.exercises.events.EventsView;
import de.akquinet.engineering.vaadin.exercises.face.FaceView;
import de.akquinet.engineering.vaadin.exercises.grid.EditableGridView;
import de.akquinet.engineering.vaadin.exercises.grid.GridView;
import de.akquinet.engineering.vaadin.exercises.layouts.LayoutsView;
import de.akquinet.engineering.vaadin.exercises.ratingstars.RatingStarsView;
import de.akquinet.engineering.vaadin.exercises.resources.ResourcesView;
import de.akquinet.engineering.vaadin.exercises.theming.ThemingView;

import javax.servlet.annotation.WebServlet;
import java.util.Locale;

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
    protected void init(final VaadinRequest request) {
        setLocale(Locale.US);

        getPage().setTitle("Vaadin Basics");

        final HorizontalLayout rootLayout = new HorizontalLayout();
        rootLayout.setSpacing(false);
        rootLayout.setSizeFull();

        rootLayout.addComponent(createNavigation());

        final Panel contentPanel = new Panel();
        contentPanel.setSizeFull();
        rootLayout.addComponent(contentPanel);
        rootLayout.setExpandRatio(contentPanel, 1.0f);

        setNavigator(new Navigator(this, new HistoryApiNavigationStateManager(getPage()), new CustomViewDisplay(contentPanel)));

        getNavigator().addView(EventsView.VIEW_NAME, new EventsView());
        getNavigator().addView(ButtonBarView.VIEW_NAME, new ButtonBarView());
        getNavigator().addView(LayoutsView.VIEW_NAME, new LayoutsView());
        getNavigator().addView(FaceView.VIEW_NAME, new FaceView());
        getNavigator().addView(DataBindingView.VIEW_NAME, new DataBindingView());
        getNavigator().addView(GridView.VIEW_NAME, new GridView());
        getNavigator().addView(EditableGridView.VIEW_NAME, new EditableGridView());
        getNavigator().addView(ThemingView.VIEW_NAME, new ThemingView());
        getNavigator().addView(ResourcesView.VIEW_NAME, new ResourcesView());
        getNavigator().addView(RatingStarsView.VIEW_NAME, new RatingStarsView());
        getNavigator().addView(BindingView.VIEW_NAME, new BindingView());
        getNavigator().addView(BufferingView.VIEW_NAME, new BufferingView());

        setContent(rootLayout);
    }

    private Component createNavigation(){

        final Image logo = new Image();
        logo.setSource(new ThemeResource("img/akquinet_logo.png"));
        logo.setWidth("150px");

        final VerticalLayout navigationLayout = new VerticalLayout();
        navigationLayout.addComponent(logo);
        navigationLayout.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        navigationLayout.addComponent(new Label("<hr/>", ContentMode.HTML));

        final Label exercisesLabel = new Label("Exercises");
        exercisesLabel.setStyleName(ValoTheme.LABEL_H3);
        navigationLayout.addComponent(exercisesLabel);
        navigationLayout.addComponent(createNavigationButton("Events and Listeners", EventsView.VIEW_NAME));
        navigationLayout.addComponent(createNavigationButton("Events and Timer", EventsView.VIEW_NAME + "/startTimer"));
        navigationLayout.addComponent(createNavigationButton("Button Bar", ButtonBarView.VIEW_NAME));
        navigationLayout.addComponent(createNavigationButton("Layouts", LayoutsView.VIEW_NAME));
        navigationLayout.addComponent(createNavigationButton("More Layouts", FaceView.VIEW_NAME));
        navigationLayout.addComponent(createNavigationButton("Data Binding", DataBindingView.VIEW_NAME));
        navigationLayout.addComponent(createNavigationButton("Grid", GridView.VIEW_NAME));
        navigationLayout.addComponent(createNavigationButton("Editable Grid", EditableGridView.VIEW_NAME));
        navigationLayout.addComponent(createNavigationButton("Theming", ThemingView.VIEW_NAME));
        navigationLayout.addComponent(createNavigationButton("Resources", ResourcesView.VIEW_NAME));
        navigationLayout.addComponent(createNavigationButton("Using Add-ons", RatingStarsView.VIEW_NAME));

        final Label demoLabel = new Label("Demos");
        demoLabel.setStyleName(ValoTheme.LABEL_H3);
        navigationLayout.addComponent(demoLabel);

        navigationLayout.addComponent(createNavigationButton("Binding", BindingView.VIEW_NAME));
        navigationLayout.addComponent(createNavigationButton("Buffering", BufferingView.VIEW_NAME));

        final Panel navigationPanel = new Panel(navigationLayout);
        navigationPanel.setStyleName(ValoTheme.PANEL_BORDERLESS);
        navigationPanel.setHeight("100%");
        navigationPanel.setWidth("300px");
        return navigationPanel;
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
