package systemtests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_DEBT;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_DEBT;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_DEBT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_DEBT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.REMARKS_DESC_DEBT;
import static seedu.address.logic.commands.CommandTestUtil.REMARKS_DESC_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DEBT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_EXPENSE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXPENSES;
import static seedu.address.testutil.TypicalExpenses.EXPENSE;
import static seedu.address.testutil.TypicalExpenses.KEYWORD_MATCHING_CHICKEN;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXPENSE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXPENSE;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.expensecommands.EditCommand;
import seedu.address.logic.commands.generalcommands.RedoCommand;
import seedu.address.logic.commands.generalcommands.UndoCommand;
import seedu.address.model.Model;
import seedu.address.model.attributes.Address;
import seedu.address.model.attributes.Amount;
import seedu.address.model.attributes.Email;
import seedu.address.model.attributes.Name;
import seedu.address.model.expense.Expense;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ExpenseBuilder;

public class EditCommandSystemTest extends FinanceTrackerSystemTest {

    @Test
    public void edit() {
        Model model = getModel();

        /* ----------------- Performing edit operation while an unfiltered list is being shown ---------------------- */

        /* Case: edit all fields, command with leading spaces, trailing spaces and multiple spaces between each field
         * -> edited
         */
        Index index = INDEX_FIRST_EXPENSE;
        String command = " " + EditCommand.COMMAND_WORD + "  " + index.getOneBased() + "  " + NAME_DESC_EXPENSE + "  "
                + AMOUNT_DESC_EXPENSE + " " + CATEGORY_DESC_EXPENSE + "  " + DATE_DESC_EXPENSE + " "
                + REMARKS_DESC_EXPENSE;
        Expense editedExpense = new ExpenseBuilder(EXPENSE).build();
        assertCommandSuccess(command, index, editedExpense);

        /* Case: undo editing the last expense in the list -> last expense restored */
        command = UndoCommand.COMMAND_WORD;
        String expectedResultMessage = UndoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedResultMessage);

        /* Case: redo editing the last expense in the list -> last expense edited again */
        command = RedoCommand.COMMAND_WORD;
        expectedResultMessage = RedoCommand.MESSAGE_SUCCESS;
        model.setExpense(getModel().getFilteredExpenseList().get(INDEX_FIRST_EXPENSE.getZeroBased()), editedExpense);
        assertCommandSuccess(command, model, expectedResultMessage);

        /* Case: edit a expense with new values same as existing values -> edited */
        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + NAME_DESC_EXPENSE + AMOUNT_DESC_EXPENSE
                + CATEGORY_DESC_EXPENSE + DATE_DESC_EXPENSE + REMARKS_DESC_EXPENSE;
        assertCommandSuccess(command, index, EXPENSE);

        /* Case: edit a expense with new values same as another expense's values but with different name -> edited */
        assertTrue(getModel().getFinanceTracker().getExpenseList().contains(EXPENSE));
        index = INDEX_SECOND_EXPENSE;
        assertNotEquals(getModel().getFilteredExpenseList().get(index.getZeroBased()), EXPENSE);
        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + NAME_DESC_EXPENSE + AMOUNT_DESC_DEBT
                + CATEGORY_DESC_DEBT + DEADLINE_DESC_DEBT + REMARKS_DESC_EXPENSE + REMARKS_DESC_DEBT;
        editedExpense = new ExpenseBuilder(EXPENSE).withName(VALID_NAME_EXPENSE).build();
        assertCommandSuccess(command, index, editedExpense);

        /* Case: edit a expense with new values same as another expense's values but with different phone and email
         * -> edited
         */
        index = INDEX_SECOND_EXPENSE;
        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + NAME_DESC_DEBT + AMOUNT_DESC_EXPENSE
                + CATEGORY_DESC_EXPENSE + DEADLINE_DESC_DEBT + REMARKS_DESC_EXPENSE + REMARKS_DESC_DEBT;
        editedExpense =
                new ExpenseBuilder(EXPENSE).withAmount(VALID_AMOUNT_EXPENSE).withDate(VALID_CATEGORY_EXPENSE).build();
        assertCommandSuccess(command, index, editedExpense);

        /* Case: clear tags -> cleared */
        index = INDEX_FIRST_EXPENSE;
        command = EditCommand.COMMAND_WORD + " " + index.getOneBased();
        Expense expenseToEdit = getModel().getFilteredExpenseList().get(index.getZeroBased());
        editedExpense = new ExpenseBuilder(expenseToEdit).build();
        assertCommandSuccess(command, index, editedExpense);

        /* ------------------ Performing edit operation while a filtered list is being shown ------------------------ */

        /* Case: filtered expense list, edit index within bounds of address book and expense list -> edited */
        showPersonsWithName(KEYWORD_MATCHING_CHICKEN);
        index = INDEX_FIRST_EXPENSE;
        assertTrue(index.getZeroBased() < getModel().getFilteredExpenseList().size());
        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + " " + NAME_DESC_DEBT;
        expenseToEdit = getModel().getFilteredExpenseList().get(index.getZeroBased());
        editedExpense = new ExpenseBuilder(expenseToEdit).withName(VALID_NAME_DEBT).build();
        assertCommandSuccess(command, index, editedExpense);

        /* Case: filtered expense list, edit index within bounds of address book but out of bounds of expense list
         * -> rejected
         */
        showPersonsWithName(KEYWORD_MATCHING_CHICKEN);
        int invalidIndex = getModel().getFinanceTracker().getExpenseList().size();
        assertCommandFailure(EditCommand.COMMAND_WORD + " " + invalidIndex + NAME_DESC_DEBT,
                Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);

        /* --------------------- Performing edit operation while a expense card is selected -------------------------*/

        /* Case: selects first card in the expense list, edit a expense -> edited, card selection remains unchanged but
         * browser url changes
         */
        showAllPersons();
        index = INDEX_FIRST_EXPENSE;
        selectPerson(index);
        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + NAME_DESC_EXPENSE + AMOUNT_DESC_EXPENSE
                + CATEGORY_DESC_EXPENSE + DATE_DESC_EXPENSE + REMARKS_DESC_EXPENSE;
        // this can be misleading: card selection actually remains unchanged but the
        // browser's url is updated to reflect the new expense's name
        assertCommandSuccess(command, index, EXPENSE, index);

        /* --------------------------------- Performing invalid edit operation -------------------------------------- */

        /* Case: invalid index (0) -> rejected */
        assertCommandFailure(EditCommand.COMMAND_WORD + " 0" + NAME_DESC_DEBT,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));

        /* Case: invalid index (-1) -> rejected */
        assertCommandFailure(EditCommand.COMMAND_WORD + " -1" + NAME_DESC_DEBT,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));

        /* Case: invalid index (size + 1) -> rejected */
        invalidIndex = getModel().getFilteredExpenseList().size() + 1;
        assertCommandFailure(EditCommand.COMMAND_WORD + " " + invalidIndex + NAME_DESC_DEBT,
                Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);

        /* Case: missing index -> rejected */
        assertCommandFailure(EditCommand.COMMAND_WORD + NAME_DESC_DEBT,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));

        /* Case: missing all fields -> rejected */
        assertCommandFailure(EditCommand.COMMAND_WORD + " " + INDEX_FIRST_EXPENSE.getOneBased(),
                EditCommand.MESSAGE_NOT_EDITED);

        /* Case: invalid name -> rejected */
        assertCommandFailure(EditCommand.COMMAND_WORD + " " + INDEX_FIRST_EXPENSE.getOneBased()
                + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        /* Case: invalid phone -> rejected */
        assertCommandFailure(EditCommand.COMMAND_WORD + " " + INDEX_FIRST_EXPENSE.getOneBased()
                + INVALID_AMOUNT_DESC, Amount.MESSAGE_CONSTRAINTS);

        /* Case: invalid email -> rejected */
        assertCommandFailure(EditCommand.COMMAND_WORD + " " + INDEX_FIRST_EXPENSE.getOneBased()
                + INVALID_CATEGORY_DESC, Email.MESSAGE_CONSTRAINTS);

        /* Case: invalid address -> rejected */
        assertCommandFailure(EditCommand.COMMAND_WORD + " " + INDEX_FIRST_EXPENSE.getOneBased()
                + INVALID_DATE_DESC, Address.MESSAGE_CONSTRAINTS);

        /* Case: invalid tag -> rejected */
        assertCommandFailure(EditCommand.COMMAND_WORD + " " + INDEX_FIRST_EXPENSE.getOneBased()
                + INVALID_DEADLINE_DESC, Tag.MESSAGE_CONSTRAINTS);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Index, Expense, Index)} except that
     * the browser url and selected card remain unchanged.
     * @param toEdit the index of the current model's filtered list
     * @see EditCommandSystemTest#assertCommandSuccess(String, Index, Expense, Index)
     */
    private void assertCommandSuccess(String command, Index toEdit, Expense editedExpense) {
        assertCommandSuccess(command, toEdit, editedExpense, null);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Model, String, Index)} and in addition,<br>
     * 1. Asserts that result display box displays the success message of executing {@code EditCommand}.<br>
     * 2. Asserts that the model related components are updated to reflect the expense at index {@code toEdit} being
     * updated to values specified {@code editedExpense}.<br>
     * @param toEdit the index of the current model's filtered list.
     * @see EditCommandSystemTest#assertCommandSuccess(String, Model, String, Index)
     */
    private void assertCommandSuccess(String command, Index toEdit, Expense editedExpense,
            Index expectedSelectedCardIndex) {
        Model expectedModel = getModel();
        expectedModel.setExpense(expectedModel.getFilteredExpenseList().get(toEdit.getZeroBased()), editedExpense);
        expectedModel.updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);

        assertCommandSuccess(command, expectedModel,
                String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedExpense), expectedSelectedCardIndex);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Model, String, Index)} except that the
     * browser url and selected card remain unchanged.
     * @see EditCommandSystemTest#assertCommandSuccess(String, Model, String, Index)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage) {
        assertCommandSuccess(command, expectedModel, expectedResultMessage, null);
    }

    /**
     * Executes {@code command} and in addition,<br>
     * 1. Asserts that the command box displays an empty string.<br>
     * 2. Asserts that the result display box displays {@code expectedResultMessage}.<br>
     * 3. Asserts that the browser url and selected card update accordingly depending on the card at
     * {@code expectedSelectedCardIndex}.<br>
     * 4. Asserts that the status bar's sync status changes.<br>
     * 5. Asserts that the command box has the default style class.<br>
     * Verifications 1 and 2 are performed by
     * {@code FinanceTrackerSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see FinanceTrackerSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     * @see FinanceTrackerSystemTest#assertSelectedCardChanged(Index)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage,
            Index expectedSelectedCardIndex) {
        executeCommand(command);
        expectedModel.updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
        assertCommandBoxShowsDefaultStyle();
        if (expectedSelectedCardIndex != null) {
            assertSelectedCardChanged(expectedSelectedCardIndex);
        } else {
            assertSelectedCardUnchanged();
        }
        assertStatusBarUnchangedExceptSyncStatus();
    }

    /**
     * Executes {@code command} and in addition,<br>
     * 1. Asserts that the command box displays {@code command}.<br>
     * 2. Asserts that result display box displays {@code expectedResultMessage}.<br>
     * 3. Asserts that the browser url, selected card and status bar remain unchanged.<br>
     * 4. Asserts that the command box has the error style.<br>
     * Verifications 1 and 2 are performed by
     * {@code FinanceTrackerSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see FinanceTrackerSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}
