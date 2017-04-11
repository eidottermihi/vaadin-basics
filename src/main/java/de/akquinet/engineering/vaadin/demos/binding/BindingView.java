package de.akquinet.engineering.vaadin.demos.binding;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Slider;
import com.vaadin.ui.VerticalLayout;
import de.akquinet.engineering.vaadin.ComponentView;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class BindingView implements View, ComponentView
{
    public static final String VIEW_NAME = "binding";

    private final VerticalLayout rootLayout = new VerticalLayout();

    public BindingView(){
        final Slider slider = new Slider(0, 100);
        slider.setCaption("set the value");
        slider.setWidth("400px");

        final Label label = new Label("42");

        final Binder<Label> binder = new Binder<>();
        binder.forField(slider)
                .withConverter(
                        doubleToString -> Integer.toString((int)(double) doubleToString),
                        Double::parseDouble)
                .bind(Label::getValue, Label::setValue);
        binder.setBean(label);

        rootLayout.addComponents(slider, label);
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
