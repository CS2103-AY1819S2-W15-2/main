package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARKS_EXPENSE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.FinanceTracker;
import seedu.address.model.expense.Expense;

/**
 * A utility class containing a list of {@code Expense} objects to be used in tests.
 */
public class TypicalExpenses {

    public static final Expense DUCK_RICE = new ExpenseBuilder().withName("Duck Rice")
            .withCategory("food").withDate("01-01-2019").withAmount("3.50").build();
    public static final Expense TAXI = new ExpenseBuilder().withName("Taxi")
            .withCategory("transport").withDate("05-01-2019").withAmount("17.20")
            .withRemarks("from NUS to Home").build();
    public static final Expense GROCERIES = new ExpenseBuilder().withName("Groceries from NTUC")
            .withAmount("57.30").withDate("08-01-2019").withCategory("shopping")
            .withRemarks("fish, eggs, chicken, beef, oyster sauce").build();
    public static final Expense LAPTOP = new ExpenseBuilder().withName("new ASUS laptop")
            .withAmount("1300.00").withDate("15-12-2018").withCategory("work").build();
    public static final Expense PHONE_BILLS = new ExpenseBuilder().withName("Phone bills")
            .withAmount("20.00").withDate("31-01-2019").withCategory("utilities").build();
    public static final Expense DOCTOR = new ExpenseBuilder().withName("doctor consultation")
            .withAmount("30.50").withDate("15-01-2019").withCategory("healthcare")
            .withRemarks("down with flu").build();
    public static final Expense TV = new ExpenseBuilder().withName("new Panasonic TV").withAmount("750.00")
            .withDate("21-07-2017").withCategory("ENTERTAINMENT").build();

    // Manually added
    public static final Expense JAPAN = new ExpenseBuilder().withName("trip to Japan")
            .withAmount("1750.00").withDate("08-06-2018").withCategory("travel")
            .withRemarks("Japan trip from 1st June to 8th June").build();
    public static final Expense STOCKS = new ExpenseBuilder().withName("1000x of MapleTree Log Trust")
            .withAmount("1152.25").withDate("03-03-2019").withCategory("others").build();

    // Manually added - Expense's details found in {@code CommandTestUtil}
    public static final Expense EXPENSE = new ExpenseBuilder().withName(VALID_NAME_EXPENSE)
            .withAmount(VALID_AMOUNT_EXPENSE).withDate(VALID_DATE_EXPENSE)
            .withCategory(VALID_CATEGORY_EXPENSE).withRemarks(VALID_REMARKS_EXPENSE).build();

    public static final String KEYWORD_MATCHING_CHICKEN = "Chicken"; // A keyword that matches CHICKEN

    private TypicalExpenses() {} // prevents instantiation

    /**
     * Returns an {@code FinanceTracker} with all the typical expenses.
     */
    public static FinanceTracker getTypicalFinanceTrackerWithExpenses() {
        FinanceTracker ft = new FinanceTracker();
        for (Expense expense : getTypicalExpenses()) {
            ft.addExpense(expense);
        }
        return ft;
    }

    public static List<Expense> getTypicalExpenses() {
        return new ArrayList<>(Arrays.asList(DUCK_RICE, TAXI, GROCERIES, LAPTOP, PHONE_BILLS, DOCTOR, TV));
    }
}
