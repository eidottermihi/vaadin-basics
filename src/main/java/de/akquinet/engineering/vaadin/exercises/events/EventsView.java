package de.akquinet.engineering.vaadin.exercises.events;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.akquinet.engineering.vaadin.ComponentView;
import de.akquinet.engineering.vaadin.timerextension.TimerExtension;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class EventsView implements ComponentView, View {
	// the start view needs to have the empty string as view name
	public static final String VIEW_NAME = "";

	private final VerticalLayout rootLayout = new VerticalLayout();

	// use this for the bonus task
	private int secondsOnPage = 0;
	private TimerExtension timerExtension = null;

	public EventsView() {
		final Label nameLabel = new Label();

		final TextField nameField = new TextField("Name");
		// add a value change listener to the nameField to update the label's
		// value with the entered name
		nameField.addValueChangeListener(event -> nameLabel.setCaption(nameField.getValue()));

		// BLUR gives the old Vaadin 7 behavior, LAZY is default.
		nameField.setValueChangeMode(ValueChangeMode.EAGER);

		// add a button to show the name in a notification
		// Tips:
		// 1) create a button with a caption and a click listener
		// 2) in the click listener call Notification.show(..) to show the
		// current value of the name field
		// 3) add the button to the layout
		Button button = new Button("Show notification");
		button.addClickListener(event -> Notification.show(nameField.getValue(), Notification.Type.TRAY_NOTIFICATION));

		// Bonus: show the seconds on page by counting the timer event that
		// occurs every second
		Label timerLabel = new Label("TimerLabel");
		timerExtension = TimerExtension.create(timerLabel);
		timerExtension.setIntervalInMs(1);
		timerExtension.start();
		timerExtension.addTimerListener(event -> {
			timerLabel.setValue("seconds on page: " + secondsOnPage++);
			if (secondsOnPage > 200) {
				timerExtension.stop();
				System.out.println("<<<<<<<< Stopped timer!");
			}
		});

		rootLayout.addComponents(nameField, nameLabel, button, timerLabel);
	}

	@Override
	public Component getComponent() {
		return rootLayout;
	}

	@Override
	public void enter(final ViewChangeListener.ViewChangeEvent event) {
	}

}
