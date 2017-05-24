package de.akquinet.engineering.vaadin.exercises.buttonbar;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import de.akquinet.engineering.vaadin.ComponentView;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class ButtonBarView implements ComponentView, View {
	public static final String VIEW_NAME = "buttonbar";

	private final VerticalLayout rootLayout = new VerticalLayout();

	public ButtonBarView() {
		final HorizontalLayout buttonBar = new HorizontalLayout();
		final Label alertLabel = new Label();
		alertLabel.setWidth("100%");
		alertLabel.setHeight("50px");
		alertLabel.setStyleName("noAlert");
		rootLayout.addComponents(buttonBar, alertLabel);
		final Button yellowButton = new Button("Yellow", event -> alertLabel.setStyleName("yellowAlert"));
		final Button redButton = new Button("Red", event -> alertLabel.setStyleName("redAlert"));
		final Button resetButton = new Button("Reset", event -> alertLabel.setStyleName("noAlert"));
		final HorizontalLayout buttonGroupLayout = new HorizontalLayout();
		buttonGroupLayout.setDefaultComponentAlignment(Alignment.BOTTOM_RIGHT);
		buttonGroupLayout.addComponents(yellowButton, redButton, resetButton);
		Label label = new Label("Alert panel");
		buttonBar.addComponents(label, buttonGroupLayout);
		buttonBar.setWidth(100.0f, Unit.PERCENTAGE);
		buttonBar.setExpandRatio(label, 1.0f);
	}

	@Override
	public void enter(final ViewChangeListener.ViewChangeEvent event) {

	}

	@Override
	public Component getComponent() {
		return rootLayout;
	}
}
