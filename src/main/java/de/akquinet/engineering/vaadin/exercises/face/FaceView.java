package de.akquinet.engineering.vaadin.exercises.face;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
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
        leftEyeButton.addStyleName("eye");
        leftEyeButton.setDescription("left eye");
        leftEyeButton.setWidth("100%");
        
        final Button rightEyeButton = new Button(VaadinIcons.EYE);
        rightEyeButton.setStyleName(ValoTheme.BUTTON_LINK);
        rightEyeButton.addStyleName("eye");
        rightEyeButton.setDescription("right eye");
        rightEyeButton.setWidth("100%");
        
        final HorizontalLayout horizontalLayout = new HorizontalLayout(leftEyeButton, rightEyeButton);
        horizontalLayout.setExpandRatio(leftEyeButton, 1.0f);
        horizontalLayout.setExpandRatio(rightEyeButton, 1.0f);
        horizontalLayout.setWidth("100%");

        final Button noseButton = new Button(VaadinIcons.CARET_UP);
        noseButton.setStyleName(ValoTheme.BUTTON_LINK);
        noseButton.addStyleName("nose");
        noseButton.setDescription("nose");

        final TextField mouthField = new TextField();
        mouthField.setWidth("200px");
        mouthField.setValue("|||||||||||||||||||||");
        mouthField.setDescription("mouth");

        final VerticalLayout faceLayout = new VerticalLayout();
        faceLayout.setStyleName("face");
        faceLayout.addComponents(horizontalLayout, noseButton, mouthField);
        faceLayout.setComponentAlignment(noseButton, Alignment.MIDDLE_CENTER);
        faceLayout.setComponentAlignment(mouthField, Alignment.BOTTOM_CENTER);

        // TODO: arrange the components so that they form a face
        // Tip: use setWidth(), setExpandRatio() and setComponentAlignment()

        faceLayout.setSizeFull();
        faceLayout.setWidth("350px");
        faceLayout.setHeight("400px");

        rootLayout.addComponent(faceLayout);
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
