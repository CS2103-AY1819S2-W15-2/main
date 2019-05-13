package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.address.model.recurring.Recurring;

/**
 * Provides a handle to a recurring card in the recurring list panel.
 */
public class RecurringCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#recurringExpenseId";
    private static final String NAME_FIELD_ID = "#recurringExpenseName";
    private static final String AMOUNT_FIELD_ID = "#recurringExpenseAmount";
    private static final String CATEGORY_FIELD_ID = "#recurringExpenseCategory";
    private static final String DATE_FIELD_ID = "#recurringExpenseDate";

    private final Label idLabel;
    private final Label nameLabel;
    private final Label amountLabel;
    private final Label categoryLabel;
    private final Label dateLabel;

    public RecurringCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        nameLabel = getChildNode(NAME_FIELD_ID);
        amountLabel = getChildNode(AMOUNT_FIELD_ID);
        categoryLabel = getChildNode(CATEGORY_FIELD_ID);
        dateLabel = getChildNode(DATE_FIELD_ID);
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getName() {
        return nameLabel.getText();
    }

    public String getAmount() {
        return amountLabel.getText();
    }

    public String getCategory() {
        return categoryLabel.getText();
    }

    public String getDate() {
        return dateLabel.getText();
    }

    /**
     * Returns true if this handle contains {@code recurring}.
     */
    public boolean equals(Recurring recurring) {
        return getName().equals(recurring.getName().name)
                && getAmount().equals(recurring.getAmount().value)
                && getCategory().equals(recurring.getCategory().toString())
                && getDate().equals(recurring.getDate().toString());
    }
}
