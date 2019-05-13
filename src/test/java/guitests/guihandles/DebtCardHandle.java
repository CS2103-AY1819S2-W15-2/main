package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.address.model.debt.Debt;

/**
 * Provides a handle to a debt card in the debt list panel.
 */
public class DebtCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#debtId";
    private static final String NAME_FIELD_ID = "#borrowerName";
    private static final String AMOUNT_FIELD_ID = "#debtAmount";
    private static final String CATEGORY_FIELD_ID = "#debtCategory";
    private static final String DUE_DATE_FIELD_ID = "#debtDue";

    private final Label idLabel;
    private final Label nameLabel;
    private final Label amountLabel;
    private final Label categoryLabel;
    private final Label dueDateLabel;

    public DebtCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        nameLabel = getChildNode(NAME_FIELD_ID);
        amountLabel = getChildNode(AMOUNT_FIELD_ID);
        categoryLabel = getChildNode(CATEGORY_FIELD_ID);
        dueDateLabel = getChildNode(DUE_DATE_FIELD_ID);
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

    public String getDueDate() {
        return dueDateLabel.getText();
    }

    /**
     * Returns true if this handle contains {@code debt}.
     */
    public boolean equals(Debt debt) {
        return getName().equals(debt.getPersonOwed())
                && getAmount().equals(debt.getAmount().value)
                && getCategory().equals(debt.getCategory().toString())
                && getDueDate().equals(debt.getDeadline().toString());
    }
}
