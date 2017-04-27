package de.akquinet.engineering.vaadin.demos.binding;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.RangeValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Setter;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.akquinet.engineering.vaadin.ComponentView;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class BindingView implements View, ComponentView
{
    public static final String VIEW_NAME = "binding";

    private final VerticalLayout rootLayout = new VerticalLayout();

    public BindingView()
    {
        final Slider slider = new Slider("Slider", 0, 100);
        slider.setWidth("400px");

        final Label boundLabel = new Label("42");
        boundLabel.setCaption("Label");

        final TextField textField = new TextField("TextField");
        textField.setValueChangeMode(ValueChangeMode.BLUR);

        final Binder<Label> binder = new Binder<>();
        binder.forField(slider)
                .withConverter(
                               doubleToString -> Integer.toString(doubleToString.intValue()),
                               Double::parseDouble)
                .bind(Label::getValue,
                      (Setter<Label, String>) (label, s) ->
                      {
                          label.setValue(s);
                          textField.setValue(s);
                      });
        binder.setBean(boundLabel);

        binder.forField(textField)
                .withValidator(s -> s != null && !s.trim().isEmpty(), "this field may not be empty")
                .withConverter(new StringToIntegerConverter("only integer values are allowed!"))
                .withValidator(RangeValidator.of("the value must be between 0 and 100!", 0, 100))
                .bind(
                      label ->
                      {
                          final String labelValue = label.getValue();
                          try
                          {
                              final double d = Double.parseDouble(labelValue);
                              return (int) d;
                          }
                          catch (final NumberFormatException e)
                          {
                              return null;
                          }
                      },
                      (label, i) ->
                      {
                          label.setValue(Integer.toString(i));
                          slider.setValue((double) i);
                      });

        rootLayout.addComponents(slider, textField, boundLabel);
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
