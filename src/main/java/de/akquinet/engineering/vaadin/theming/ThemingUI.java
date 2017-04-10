package de.akquinet.engineering.vaadin.theming;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

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
        label.setStyleName(ValoTheme.LABEL_H1);
        label.addStyleName(ValoTheme.LABEL_BOLD);
        label.addStyleName(ValoTheme.LABEL_COLORED);

        final TextField rightAlignedTextField = new TextField("a right-aligned text field");
        rightAlignedTextField.setStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);

        final Button defaultButton = new Button("default button");
        final Button primaryButton = new Button("primary button");
        primaryButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        final Button dangerButton = new Button("danger button");
        dangerButton.setStyleName(ValoTheme.BUTTON_DANGER);
        final Button friendlyButton = new Button("friendly button");
        friendlyButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        final Button linkButton = new Button("link button");
        linkButton.setStyleName(ValoTheme.BUTTON_LINK);

        final Button customButton = new Button("custom button");
        customButton.setStyleName("special-custom-style");

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
