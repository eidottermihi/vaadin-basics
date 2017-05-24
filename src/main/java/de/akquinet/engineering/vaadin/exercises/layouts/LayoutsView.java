package de.akquinet.engineering.vaadin.exercises.layouts;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import de.akquinet.engineering.vaadin.ComponentView;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class LayoutsView implements ComponentView, View {
	public static final String VIEW_NAME = "layouts";

	private final VerticalLayout rootLayout = new VerticalLayout();

	public LayoutsView() {
		final Label header = new Label("This is the header");
		header.setStyleName("header");

		final Label footer = new Label("This is the footer");
		footer.setStyleName("footer");

		final Label navigation = new Label("This is the navigation area");
		navigation.setStyleName("navigation");
		navigation.setHeight("100%");

		final Panel content = new Panel();

		// fill the content
		final VerticalLayout contentLayout = new VerticalLayout();
		content.setContent(contentLayout);
		for (int i = 0; i < 50; ++i) {
			contentLayout.addComponent(new Label("Bla, bla, bla, ..."));
		}
//		contentLayout.setMargin(false);
//		contentLayout.setSpacing(false);
		content.setSizeFull();

		// TODO: do the layout
		// set the header and the footer to 120px height
		header.setHeight(120.0f, Unit.PIXELS);
		header.setWidth(100.0f, Unit.PERCENTAGE);
		footer.setHeight(120.0f, Unit.PIXELS);
		footer.setWidth(100.0f, Unit.PERCENTAGE);
		// set the navigation area's width to 250px
		navigation.setWidth("250px");
		
		rootLayout.addComponent(header);
		HorizontalLayout mainLayout = new HorizontalLayout();
		mainLayout.addComponents(navigation, content);
		mainLayout.setWidth("100%");
		mainLayout.setExpandRatio(content, 1.0f);
		mainLayout.setHeight("100%");
//		mainLayout.setMargin(false);
		mainLayout.setSpacing(false);
		
		rootLayout.addComponent(mainLayout);
		rootLayout.addComponent(footer);
		
		// Tips:
		// 1) use sizing functions, setMargin(..), setSpacing(..) and
		// setExpandRatio(..)
		
		// 2) remember to add the components to the layout before calling
		// setExpandRatio
		rootLayout.setExpandRatio(mainLayout, 1.0f);
		rootLayout.setSpacing(false);
		rootLayout.setMargin(true);
		rootLayout.setSizeFull();
	}

	@Override
	public void enter(final ViewChangeListener.ViewChangeEvent event) {

	}

	@Override
	public Component getComponent() {
		return rootLayout;
	}
}
