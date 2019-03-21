package seedu.address.ui.testutil;

import static org.junit.Assert.assertEquals;

import java.util.List;

import guitests.guihandles.BudgetCardHandle;
import guitests.guihandles.DebtCardHandle;
import guitests.guihandles.ExpenseCardHandle;
import guitests.guihandles.ExpenseListPanelHandle;
import guitests.guihandles.RecurringCardHandle;
import guitests.guihandles.ResultDisplayHandle;

import seedu.address.model.budget.Budget;
import seedu.address.model.debt.Debt;
import seedu.address.model.expense.Expense;
import seedu.address.model.recurring.Recurring;

/**
 * A set of assertion methods useful for writing GUI tests.
 */
public class GuiTestAssert {
    /**
     * Asserts that {@code actualCard} displays the same values as {@code expectedCard}.
     */
    public static void assertCardEquals(ExpenseCardHandle expectedCard, ExpenseCardHandle actualCard) {
        assertEquals(expectedCard.getId(), actualCard.getId());
        assertEquals(expectedCard.getAmount(), actualCard.getAmount());
        assertEquals(expectedCard.getDate(), actualCard.getDate());
        assertEquals(expectedCard.getName(), actualCard.getName());
        assertEquals(expectedCard.getCategory(), actualCard.getCategory());
    }

    /**
     * Asserts that {@code actualCard} displays the same values as {@code expectedCard}.
     */
    public static void assertCardEquals(BudgetCardHandle expectedCard, BudgetCardHandle actualCard) {
        assertEquals(expectedCard.getEndDate(), actualCard.getEndDate());
        assertEquals(expectedCard.getAmount(), actualCard.getAmount());
        assertEquals(expectedCard.getStartDate(), actualCard.getStartDate());
        assertEquals(expectedCard.getCategory(), actualCard.getCategory());
    }

    /**
     * Asserts that {@code actualCard} displays the same values as {@code expectedCard}.
     */
    public static void assertCardEquals(DebtCardHandle expectedCard, DebtCardHandle actualCard) {
        assertEquals(expectedCard.getId(), actualCard.getId());
        assertEquals(expectedCard.getAmount(), actualCard.getAmount());
        assertEquals(expectedCard.getDueDate(), actualCard.getDueDate());
        assertEquals(expectedCard.getName(), actualCard.getName());
        assertEquals(expectedCard.getCategory(), actualCard.getCategory());
    }

    /**
     * Asserts that {@code actualCard} displays the same values as {@code expectedCard}.
     */
    public static void assertCardEquals(RecurringCardHandle expectedCard, RecurringCardHandle actualCard) {
        assertEquals(expectedCard.getId(), actualCard.getId());
        assertEquals(expectedCard.getAmount(), actualCard.getAmount());
        assertEquals(expectedCard.getDate(), actualCard.getDate());
        assertEquals(expectedCard.getName(), actualCard.getName());
        assertEquals(expectedCard.getCategory(), actualCard.getCategory());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedExpense}.
     */
    public static void assertCardDisplaysExpense(Expense expectedExpense, ExpenseCardHandle actualCard) {
        assertEquals(expectedExpense.getName().name, actualCard.getName());
        assertEquals("$" + expectedExpense.getAmount(), actualCard.getAmount());
        assertEquals(expectedExpense.getCategory().toString(), actualCard.getCategory());
        assertEquals(expectedExpense.getDate().toString(), actualCard.getDate());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedRecurring}.
     */
    public static void assertCardDisplaysRecurring(Recurring expectedRecurring, RecurringCardHandle actualCard) {
        assertEquals(expectedRecurring.getName().name, actualCard.getName());
        assertEquals("$" + expectedRecurring.getAmount(), actualCard.getAmount());
        assertEquals(expectedRecurring.getCategory().toString(), actualCard.getCategory());
        assertEquals(expectedRecurring.getDate().toString(), actualCard.getDate());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedBudget}.
     */
    public static void assertCardDisplaysBudget(Budget expectedBudget, BudgetCardHandle actualCard) {
        assertEquals("$" + expectedBudget.getAmount(), actualCard.getAmount());
        assertEquals(expectedBudget.getCategory().toString(), actualCard.getCategory());
        assertEquals(expectedBudget.getStartDate().toString(), actualCard.getStartDate());
        assertEquals(expectedBudget.getEndDate().toString(), actualCard.getEndDate());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedDebt}.
     */
    public static void assertCardDisplaysDebt(Debt expectedDebt, DebtCardHandle actualCard) {
        assertEquals("$" + expectedDebt.getAmount(), actualCard.getAmount());
        assertEquals(expectedDebt.getCategory().toString(), actualCard.getCategory());
        assertEquals(expectedDebt.getPersonOwed().name, actualCard.getName());
        assertEquals(expectedDebt.getDeadline().toString(), actualCard.getDueDate());
    }

    /**
     * Asserts that the list in {@code expenseListPanelHandle} displays the details of {@code expenses} correctly and
     * in the correct order.
     */
    public static void assertListMatching(ExpenseListPanelHandle expenseListPanelHandle, Expense... expenses) {
        for (int i = 0; i < expenses.length; i++) {
            expenseListPanelHandle.navigateToCard(i);
            assertCardDisplaysExpense(expenses[i], expenseListPanelHandle.getExpenseCardHandle(i));
        }
    }

    /**
     * Asserts that the list in {@code expenseListPanelHandle} displays the details of {@code expenses} correctly and
     * in the correct order.
     */
    public static void assertListMatching(ExpenseListPanelHandle expenseListPanelHandle, List<Expense> expenses) {
        assertListMatching(expenseListPanelHandle, expenses.toArray(new Expense[0]));
    }

    /**
     * Asserts the size of the list in {@code expenseListPanelHandle} equals to {@code size}.
     */
    public static void assertListSize(ExpenseListPanelHandle expenseListPanelHandle, int size) {
        int numberOfPeople = expenseListPanelHandle.getListSize();
        assertEquals(size, numberOfPeople);
    }

    /**
     * Asserts the message shown in {@code resultDisplayHandle} equals to {@code expected}.
     */
    public static void assertResultMessage(ResultDisplayHandle resultDisplayHandle, String expected) {
        assertEquals(expected, resultDisplayHandle.getText());
    }
}
