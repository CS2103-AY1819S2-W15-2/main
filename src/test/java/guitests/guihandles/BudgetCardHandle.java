package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.address.model.budget.Budget;

/**
 * Provides a handle to a budget card in the budget list panel.
 */
public class BudgetCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#budgetId";
    private static final String AMOUNT_FIELD_ID = "#budgetAmount";
    private static final String CATEGORY_FIELD_ID = "#budgetCategory";
    private static final String START_DATE_FIELD_ID = "#budgetStartDate";
    private static final String END_DATE_FIELD_ID = "#budgetEndDate";

    private final Label idLabel;
    private final Label amountLabel;
    private final Label categoryLabel;
    private final Label startDateLabel;
    private final Label endDateLabel;

    public BudgetCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        amountLabel = getChildNode(AMOUNT_FIELD_ID);
        categoryLabel = getChildNode(CATEGORY_FIELD_ID);
        startDateLabel = getChildNode(START_DATE_FIELD_ID);
        endDateLabel = getChildNode(END_DATE_FIELD_ID);
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getAmount() {
        return amountLabel.getText();
    }

    public String getCategory() {
        return categoryLabel.getText();
    }

    public String getStartDate() {
        return startDateLabel.getText();
    }

    public String getEndDate() {
        return endDateLabel.getText();
    }

    /**
     * Returns true if this handle contains {@code budget}.
     */
    public boolean equals(Budget budget) {
        return getAmount().equals(budget.getAmount().value)
                && getCategory().equals(budget.getCategory().toString())
                && getStartDate().equals(budget.getStartDate().toString())
                && getEndDate().equals(budget.getEndDate().toString());
    }
}
