package seedu.address.ui;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.recurring.Recurring;



/**
 * Panel containing the list of persons.
 */
public class RecurringExpenseListPanel extends UiPart<Region> {
    private static final String FXML = "RecurringExpenseListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RecurringExpenseListPanel.class);

    @FXML
    private ListView<Recurring> recurringExpenseListView;

    public RecurringExpenseListPanel(ObservableList<Recurring> recurringExpenseList,
                                     ObservableValue<Recurring> selectedRecurringExpense,
                                     Consumer<Recurring> onSelectedRecurringExpenseChange) {
        super(FXML);
        recurringExpenseListView.setItems(recurringExpenseList);
        recurringExpenseListView.setCellFactory(listView -> new RecurringExpenseListViewCell());
        recurringExpenseListView.getSelectionModel().selectedItemProperty().addListener((observable,
                                                                                         oldValue, newValue) -> {
            logger.fine("Selection in recurring expense list panel changed to : '" + newValue + "'");
            onSelectedRecurringExpenseChange.accept(newValue);
        });
        selectedRecurringExpense.addListener((observable, oldValue, newValue) -> {
            logger.fine("Selected recurring expense changed to: " + newValue);

            // Don't modify selection if we are already selecting the selected expense,
            // otherwise we would have an infinite loop.
            if (Objects.equals(recurringExpenseListView.getSelectionModel().getSelectedItem(), newValue)) {
                return;
            }

            if (newValue == null) {
                recurringExpenseListView.getSelectionModel().clearSelection();
            } else {
                int index = recurringExpenseListView.getItems().indexOf(newValue);
                recurringExpenseListView.scrollTo(index);
                recurringExpenseListView.getSelectionModel().clearAndSelect(index);
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Recurring} using a
     * {@code RecurringExpenseListCard}.
     */
    class RecurringExpenseListViewCell extends ListCell<Recurring> {
        @Override
        protected void updateItem(Recurring recurring, boolean empty) {
            super.updateItem(recurring, empty);

            if (empty || recurring == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new RecurringExpenseCard(recurring, getIndex() + 1).getRoot());
            }
        }
    }

}
