package de.akquinet.engineering.vaadin.exercises.grid;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Label;
import com.vaadin.ui.StyleGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.FooterRow;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.renderers.NumberRenderer;

import de.akquinet.engineering.vaadin.ComponentView;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class GridView implements View, ComponentView {
	public static final String VIEW_NAME = "grid";

	private final VerticalLayout rootLayout = new VerticalLayout();

	public GridView() {
		final List<Player> playerList = PlayerGenerator.createPlayers(10000);

		final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy",
				UI.getCurrent().getLocale());

		final Grid<Player> playerGrid = new Grid<>();
		final ListDataProvider<Player> dataProvider = DataProvider.ofCollection(playerList);
		playerGrid.setDataProvider(dataProvider);

		// Use this style generator for the points and medals column
		final StyleGenerator<Player> alignRightStyle = player -> "align-right";

		// Bonus: show the rank of each player

		// TODO: show the player's name
		Column<Player, String> nameColumn = playerGrid.addColumn(Player::getName).setCaption("Name");

		// TODO: show the player's date of birth and make it hidable
		// Tips:
		// 1) use the dateTimeFormatter in the ValueProvider
		Column<Player, String> birthdayColumn = playerGrid
				.addColumn(player -> dateTimeFormatter.format(player.getDateOfBirth())).setCaption("Birthday");

		// 2) set a comparator (SerializableComparator<Player>) and use the
		// LocalDate's compareTo function,
		// so that date are compared not by their string representation but by
		// their values
		birthdayColumn.setComparator((o1, o2) -> o1.getDateOfBirth().compareTo(o2.getDateOfBirth()));

		// TODO: show the player's age and make it hidable
		Column<Player, Integer> ageColumn = playerGrid.addColumn(Player::getAge).setCaption("Age").setHidable(true);

		// Bonus: show the player's sex with symbols in different colors
		// Tips:
		// 1) use the GenderPresentation class to get the gender icons
		// 2) use an HtmlRenderer
		// 3) set a style generator and get the style from the
		// GenderPresentation class
		// 4) set a comparator to make to column comparable, you can use the
		// Gender's compare function
		Column<Player, String> sexColumn = playerGrid
				.addColumn(player -> GenderPresentation.getPresentation(player.getGender()).getIcon().getHtml())
				.setStyleGenerator(player -> GenderPresentation.getPresentation(player.getGender()).getStyleName())
				.setRenderer(new HtmlRenderer()).setCaption("Sex");

		// TODO: show the player's points (align right)
		// Tip: you can use the NumberRenderer (new NumberRenderer(new
		// DecimalFormat())) to get the points formatted
		Column<Player, Long> pointsColumn = playerGrid.addColumn(Player::getPoints).setCaption("Points")
				.setStyleGenerator(alignRightStyle).setRenderer(new NumberRenderer(DecimalFormat.getNumberInstance()));

		// TODO: show the player's medals (align right)
		Column<Player, Integer> medalsColumn = playerGrid.addColumn(Player::getMedals).setCaption("Medals")
				.setStyleGenerator(alignRightStyle);

		// TODO: enable filtering by player's name
		// Tips:
		// 1) create a new header row with appendHeaderRow()
		HeaderRow headerRow = playerGrid.appendHeaderRow();
		// 2) create a text field and set this component into the new row in the
		// cell of the name
		TextField nameFilterTextField = new TextField();
		headerRow.getCell(nameColumn).setComponent(nameFilterTextField);
		// 3) add a value change listener to the text field
		// 4) in the listener set a filter on the dataProvider, use this
		// interface:
		// setFilter(ValueProvider<T, V> valueProvider, SerializablePredicate<V>
		// valueFilter)
		// and check whether or not the cell contains the filter string
		nameFilterTextField.addValueChangeListener(event -> dataProvider.setFilter(Player::getName,
				name -> name.contains(nameFilterTextField.getValue())));

		// Bonus
		Column<Player, Integer> rankColumn = playerGrid.addColumn(player -> {
			List<Player> players = new ArrayList<>(dataProvider.getItems());
			Collections.sort(players, (o1, o2) -> Long.valueOf(o1.getPoints()).compareTo(o2.getPoints()) * -1);
			return players.indexOf(player) + 1;
		}).setCaption("Rank");

		// Footer
		FooterRow footerRow = playerGrid.appendFooterRow();
		Label totalPlayers = new Label();
		totalPlayers.setValue("Total players: " + playerList.size());
		footerRow.getCell(nameColumn).setComponent(totalPlayers);
		Label avgAge = new Label();
		avgAge.setIcon(VaadinIcons.GROUP);
		avgAge.setValue(NumberFormat.getNumberInstance()
				.format(playerList.stream().mapToInt(player -> player.getAge()).average().getAsDouble()));
		footerRow.getCell(ageColumn).setComponent(avgAge);

		// layout grid
		// TODO: set the grid's frozen column count to 1
		playerGrid.setFrozenColumnCount(1);
		playerGrid.setHeight("100%");
		playerGrid.setWidth("100%");

		playerGrid.setIcon(VaadinIcons.SEARCH_PLUS);
		playerGrid.setCaption("Players");

		rootLayout.setHeight("100%");
		rootLayout.addComponent(playerGrid);
	}

	@Override
	public void enter(final ViewChangeListener.ViewChangeEvent event) {

	}

	@Override
	public Component getComponent() {
		return rootLayout;
	}
}
