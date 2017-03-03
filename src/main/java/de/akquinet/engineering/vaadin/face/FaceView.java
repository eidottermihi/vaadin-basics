package de.akquinet.engineering.vaadin.face;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import de.akquinet.engineering.vaadin.ComponentView;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class FaceView implements ComponentView, View
{
    public static final String VIEW_NAME = "face";

    private final VerticalLayout rootLayout = new VerticalLayout();

    public FaceView()
    {
        final Button leftEyeButton = new Button(VaadinIcons.EYE);
        leftEyeButton.setStyleName(ValoTheme.BUTTON_LINK);
        final Button rightEyeButton = new Button(VaadinIcons.EYE);
        rightEyeButton.setStyleName(ValoTheme.BUTTON_LINK);

        final HorizontalLayout horizontalLayout = new HorizontalLayout(leftEyeButton, rightEyeButton);
        horizontalLayout.setComponentAlignment(leftEyeButton, Alignment.MIDDLE_CENTER);
        horizontalLayout.setComponentAlignment(rightEyeButton, Alignment.MIDDLE_CENTER);
        horizontalLayout.setWidth("100%");

        final Button noseButton = new Button(VaadinIcons.CARET_UP);
        noseButton.setStyleName(ValoTheme.BUTTON_LINK);

        final TextField teethField = new TextField();
        teethField.setWidth("200px");
        teethField.setValue("|||||||||||||||||||||");

        final VerticalLayout faceLayout = new VerticalLayout();
        faceLayout.addComponents(horizontalLayout, noseButton, teethField);
        faceLayout.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
        faceLayout.setComponentAlignment(noseButton, Alignment.MIDDLE_CENTER);
        faceLayout.setExpandRatio(noseButton, 1f);
        faceLayout.setComponentAlignment(teethField, Alignment.MIDDLE_CENTER);
        faceLayout.setSizeFull();

        final CssLayout borderLayout = new CssLayout(faceLayout);
        borderLayout.setStyleName("face");
        borderLayout.setWidth("350px");
        borderLayout.setHeight("400px");

        rootLayout.addComponent(borderLayout);
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
