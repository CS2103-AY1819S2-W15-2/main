package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.address.model.expense.Expense;

/**
 * Provides a handle to a expense card in the expense list panel.
 */
public class ExpenseCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#expenseId";
    private static final String NAME_FIELD_ID = "#expenseName";
    private static final String AMOUNT_FIELD_ID = "#expenseAmount";
    private static final String CATEGORY_FIELD_ID = "#expenseCategory";
    private static final String DATE_FIELD_ID = "#expenseDate";

    private final Label idLabel;
    private final Label nameLabel;
    private final Label amountLabel;
    private final Label categoryLabel;
    private final Label dateLabel;

    public ExpenseCardHandle(Node cardNode) {
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
     * Returns true if this handle contains {@code expense}.
     */
    public boolean equals(Expense expense) {
        return getName().equals(expense.getName().name)
                && getAmount().equals("$" + expense.getAmount().toString())
                && getCategory().equals(expense.getCategory().toString())
                && getDate().equals(expense.getDate().toString());
    }
}
