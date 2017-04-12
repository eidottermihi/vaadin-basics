package de.akquinet.engineering.vaadin.exercises.grid;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.IconGenerator;
import com.vaadin.ui.ItemCaptionGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.HtmlRenderer;
import de.akquinet.engineering.vaadin.ComponentView;

import java.util.List;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class EditableGridView implements View, ComponentView
{
    public static final String VIEW_NAME = "editablegrid";

    private final VerticalLayout rootLayout = new VerticalLayout();

    public EditableGridView()
    {
        final List<Player> playerList = PlayerGenerator.createPlayers(10);

        final Grid<Player> grid = new Grid<>(Player.class);
        grid.setItems(playerList);

        grid.setColumns("name", "dateOfBirth");

        final Binder<Player> binder = grid.getEditor().getBinder();

        final TextField nameField = new TextField();
        binder.forField(nameField)
                .asRequired("name is mandatory")
                .bind("name");
        grid.getColumn("name").setEditorComponent(nameField);

        final DateField dateField = new DateField();
        dateField.setDateFormat("yyyy-MM-dd");
        binder.bind(dateField, "dateOfBirth");
        grid.getColumn("dateOfBirth").setEditorComponent(dateField);

        final Grid.Column<Player, String> genderColumn = grid
                .addColumn(player ->
                {
                    final GenderPresentation genderPresentation = GenderPresentation
                            .getPresentation(player.getGender());
                    return String.format("%s %s",
                                         genderPresentation
                                                 .getIcon().getHtml(),
                                         genderPresentation.getName());
                })
                .setRenderer(new HtmlRenderer())
                .setCaption("Sex");

        final ComboBox<Gender> genderComboBox = new ComboBox<>();
        genderComboBox.setItems(Gender.values());
        genderComboBox.setItemIconGenerator((IconGenerator<Gender>) gender -> GenderPresentation
                .getPresentation(gender).getIcon());
        genderComboBox
                .setItemCaptionGenerator((ItemCaptionGenerator<Gender>) gender -> GenderPresentation
                        .getPresentation(gender).getName());
        genderComboBox.setEmptySelectionAllowed(false);
        final Binder.Binding<Player, Gender> genderBinding = binder
                .bind(genderComboBox, Player::getGender, Player::setGender);
        genderColumn.setEditorBinding(genderBinding);

        grid.getEditor().setEnabled(true);

        grid.setSizeFull();
        rootLayout.setSizeFull();
        rootLayout.addComponent(grid);
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
