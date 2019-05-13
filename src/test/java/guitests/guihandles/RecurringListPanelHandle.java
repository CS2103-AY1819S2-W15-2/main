package guitests.guihandles;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import seedu.address.model.recurring.Recurring;

/**
 * Provides a handle for {@code RecurringListPanel} containing the list of {@code RecurringCard}.
 */
public class RecurringListPanelHandle extends NodeHandle<ListView<Recurring>> {
    public static final String RECURRING_LIST_VIEW_ID = "#recurringExpenseListView";

    private static final String CARD_PANE_ID = "#recurringExpenseCardPane";

    private Optional<Recurring> lastRememberedSelectedRecurringCard;

    public RecurringListPanelHandle(ListView<Recurring> recurringListPanelNode) {
        super(recurringListPanelNode);
    }

    /**
     * Returns a handle to the selected {@code RecurringCardHandle}.
     * A maximum of 1 item can be selected at any time.
     * @throws AssertionError if no card is selected, or more than 1 card is selected.
     * @throws IllegalStateException if the selected card is currently not in the scene graph.
     */
    public RecurringCardHandle getHandleToSelectedCard() {
        List<Recurring> selectedRecurringList = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedRecurringList.size() != 1) {
            throw new AssertionError("Recurring list size expected 1.");
        }

        return getAllCardNodes().stream()
                .map(RecurringCardHandle::new)
                .filter(handle -> handle.equals(selectedRecurringList.get(0)))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    /**
     * Returns the index of the selected card.
     */
    public int getSelectedCardIndex() {
        return getRootNode().getSelectionModel().getSelectedIndex();
    }

    /**
     * Returns true if a card is currently selected.
     */
    public boolean isAnyCardSelected() {
        List<Recurring> selectedCardsList = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedCardsList.size() > 1) {
            throw new AssertionError("Card list size expected 0 or 1.");
        }

        return !selectedCardsList.isEmpty();
    }

    /**
     * Navigates the listview to display {@code recurring}.
     */
    public void navigateToCard(Recurring recurring) {
        if (!getRootNode().getItems().contains(recurring)) {
            throw new IllegalArgumentException("Recurring does not exist.");
        }

        guiRobot.interact(() -> {
            getRootNode().scrollTo(recurring);
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Navigates the listview to {@code index}.
     */
    public void navigateToCard(int index) {
        if (index < 0 || index >= getRootNode().getItems().size()) {
            throw new IllegalArgumentException("Index is out of bounds.");
        }

        guiRobot.interact(() -> {
            getRootNode().scrollTo(index);
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Selects the {@code RecurringCard} at {@code index} in the list.
     */
    public void select(int index) {
        getRootNode().getSelectionModel().select(index);
    }

    /**
     * Returns the recurring card handle of a recurring associated with the {@code index} in the list.
     * @throws IllegalStateException if the selected card is currently not in the scene graph.
     */
    public RecurringCardHandle getRecurringCardHandle(int index) {
        return getAllCardNodes().stream()
                .map(RecurringCardHandle::new)
                .filter(handle -> handle.equals(getRecurring(index)))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private Recurring getRecurring(int index) {
        return getRootNode().getItems().get(index);
    }

    /**
     * Returns all card nodes in the scene graph.
     * Card nodes that are visible in the listview are definitely in the scene graph, while some nodes that are not
     * visible in the listview may also be in the scene graph.
     */
    private Set<Node> getAllCardNodes() {
        return guiRobot.lookup(CARD_PANE_ID).queryAll();
    }

    /**
     * Remembers the selected {@code RecurringCard} in the list.
     */
    public void rememberSelectedRecurringCard() {
        List<Recurring> selectedItems = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 0) {
            lastRememberedSelectedRecurringCard = Optional.empty();
        } else {
            lastRememberedSelectedRecurringCard = Optional.of(selectedItems.get(0));
        }
    }

    /**
     * Returns true if the selected {@code RecurringCard} is different from the value remembered by the most recent
     * {@code rememberSelectedRecurringCard()} call.
     */
    public boolean isSelectedRecurringCardChanged() {
        List<Recurring> selectedItems = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 0) {
            return lastRememberedSelectedRecurringCard.isPresent();
        } else {
            return !lastRememberedSelectedRecurringCard.isPresent()
                    || !lastRememberedSelectedRecurringCard.get().equals(selectedItems.get(0));
        }
    }

    /**
     * Returns the size of the list.
     */
    public int getListSize() {
        return getRootNode().getItems().size();
    }
}
