package de.akquinet.engineering.vaadin.exercises.theming;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
@Theme("specialtheme")
public class ThemingUI extends UI
{
    @Override
    protected void init(final VaadinRequest request)
    {
        getPage().setTitle("Special Theme");

        final Label label = new Label("Styling");
        // TODO: make this label an H1 header, bold and colored

        final TextField rightAlignedTextField = new TextField("a right-aligned text field");
        // TODO: make this text field's text right aligned

        final Button defaultButton = new Button("default button");

        final Button primaryButton = new Button("primary button");
        // TODO: make this a primary button

        final Button dangerButton = new Button("danger button");
        // TODO: make this a danger button

        final Button friendlyButton = new Button("friendly button");
        // TODO: make this a friendly button

        final Button linkButton = new Button("link button");
        // TODO: style this button like a link

        final Button customButton = new Button("custom button");
        // TODO: make this button yellow with a bold and italic font using a custom style

        final Button closeButton = new Button("close window", event -> {
            // Close the popup
            JavaScript.eval("close();");

            // Detach the UI from the session
            getUI().close();
        });

        final VerticalLayout rootLayout = new VerticalLayout();
        rootLayout.addComponents(label, rightAlignedTextField, defaultButton, primaryButton,
                dangerButton, friendlyButton, linkButton, customButton,
                closeButton);
        setContent(rootLayout);
    }
}
