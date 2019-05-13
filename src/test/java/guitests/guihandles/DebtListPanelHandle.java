package guitests.guihandles;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import seedu.address.model.debt.Debt;

/**
 * Provides a handle for {@code DebtListPanel} containing the list of {@code DebtCard}.
 */
public class DebtListPanelHandle extends NodeHandle<ListView<Debt>> {
    public static final String DEBT_LIST_VIEW_ID = "#debtListView";

    private static final String CARD_PANE_ID = "#debtCardPane";

    private Optional<Debt> lastRememberedSelectedDebtCard;

    public DebtListPanelHandle(ListView<Debt> debtListPanelNode) {
        super(debtListPanelNode);
    }

    /**
     * Returns a handle to the selected {@code DebtCardHandle}.
     * A maximum of 1 item can be selected at any time.
     * @throws AssertionError if no card is selected, or more than 1 card is selected.
     * @throws IllegalStateException if the selected card is currently not in the scene graph.
     */
    public DebtCardHandle getHandleToSelectedCard() {
        List<Debt> selectedDebtList = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedDebtList.size() != 1) {
            throw new AssertionError("Debt list size expected 1.");
        }

        return getAllCardNodes().stream()
                .map(DebtCardHandle::new)
                .filter(handle -> handle.equals(selectedDebtList.get(0)))
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
        List<Debt> selectedCardsList = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedCardsList.size() > 1) {
            throw new AssertionError("Card list size expected 0 or 1.");
        }

        return !selectedCardsList.isEmpty();
    }

    /**
     * Navigates the listview to display {@code debt}.
     */
    public void navigateToCard(Debt debt) {
        if (!getRootNode().getItems().contains(debt)) {
            throw new IllegalArgumentException("Debt does not exist.");
        }

        guiRobot.interact(() -> {
            getRootNode().scrollTo(debt);
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
     * Selects the {@code DebtCard} at {@code index} in the list.
     */
    public void select(int index) {
        getRootNode().getSelectionModel().select(index);
    }

    /**
     * Returns the debt card handle of a debt associated with the {@code index} in the list.
     * @throws IllegalStateException if the selected card is currently not in the scene graph.
     */
    public DebtCardHandle getDebtCardHandle(int index) {
        return getAllCardNodes().stream()
                .map(DebtCardHandle::new)
                .filter(handle -> handle.equals(getDebt(index)))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private Debt getDebt(int index) {
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
     * Remembers the selected {@code DebtCard} in the list.
     */
    public void rememberSelectedDebtCard() {
        List<Debt> selectedItems = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 0) {
            lastRememberedSelectedDebtCard = Optional.empty();
        } else {
            lastRememberedSelectedDebtCard = Optional.of(selectedItems.get(0));
        }
    }

    /**
     * Returns true if the selected {@code DebtCard} is different from the value remembered by the most recent
     * {@code rememberSelectedDebtCard()} call.
     */
    public boolean isSelectedDebtCardChanged() {
        List<Debt> selectedItems = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 0) {
            return lastRememberedSelectedDebtCard.isPresent();
        } else {
            return !lastRememberedSelectedDebtCard.isPresent()
                    || !lastRememberedSelectedDebtCard.get().equals(selectedItems.get(0));
        }
    }

    /**
     * Returns the size of the list.
     */
    public int getListSize() {
        return getRootNode().getItems().size();
    }
}
