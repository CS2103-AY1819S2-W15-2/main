package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_DEBT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_DEBT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_DEBT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DEBT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARKS_DEBT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.FinanceTracker;
import seedu.address.model.debt.Debt;

/**
 * A utility class containing a list of {@code Debt} objects to be used in tests.
 */
public class TypicalDebts {

    public static final Debt DUCK_RICE = new DebtBuilder().withPersonOwed("Amy")
            .withCategory("food").withDeadline("1-12-2019").withAmount("3.50")
            .withRemarks("for duck rice last week").build();
    public static final Debt TAXI = new DebtBuilder().withPersonOwed("Bob")
            .withCategory("transport").withDeadline("05-12-2019").withAmount("17.20")
            .withRemarks("taxi from NUS to Home").build();
    public static final Debt GROCERIES = new DebtBuilder().withPersonOwed("Charlie")
            .withAmount("57.30").withDeadline("08-08-2019").withCategory("shopping")
            .withRemarks("groceries: fish, eggs, chicken, beef, oyster sauce").build();
    public static final Debt LAPTOP = new DebtBuilder().withPersonOwed("Debbie")
            .withAmount("1300.00").withDeadline("15-12-2019").withCategory("work")
            .withRemarks("money for my work laptop").build();
    public static final Debt PHONE_BILLS = new DebtBuilder().withPersonOwed("Evan")
            .withAmount("20.00").withDeadline("27-03-2019").withCategory("utilities")
            .withRemarks("for lending me money to pay my phone bills").build();
    public static final Debt DOCTOR = new DebtBuilder().withPersonOwed("Frank")
            .withAmount("30.50").withDeadline("16-07-2019").withCategory("healthcare")
            .withRemarks("money borrowed to see the doctor").build();
    public static final Debt TV = new DebtBuilder().withPersonOwed("George").withAmount("750.00")
            .withDeadline("21-07-2017").withCategory("ENTERTAINMENT")
            .withRemarks("borrowed money to get a new TV").build();

    // Manually added
    public static final Debt JAPAN = new DebtBuilder().withPersonOwed("Holly")
            .withAmount("1750.00").withDeadline("08-06-2018").withCategory("travel")
            .withRemarks("money borrowed during Japan trip").build();
    public static final Debt STOCKS = new DebtBuilder().withPersonOwed("Ivan")
            .withAmount("1152.25").withDeadline("03-03-2019").withCategory("others")
            .withRemarks("borrowed to purcahse mapletree stocks").build();

    // Manually added - Debt's details found in {@code CommandTestUtil}
    public static final Debt DEBT = new DebtBuilder().withPersonOwed(VALID_NAME_DEBT)
            .withAmount(VALID_AMOUNT_DEBT).withDeadline(VALID_CATEGORY_DEBT)
            .withCategory(VALID_DEADLINE_DEBT).withRemarks(VALID_REMARKS_DEBT).build();

    public static final String KEYWORD_MATCHING_CHICKEN = "Chicken"; // A keyword that matches CHICKEN

    private TypicalDebts() {} // prevents instantiation

    /**
     * Returns an {@code FinanceTracker} with all the typical debts.
     */
    public static FinanceTracker getTypicalFinanceTrackerWithExpenses() {
        FinanceTracker ft = new FinanceTracker();
        for (Debt debt : getTypicalDebts()) {
            ft.addDebt(debt);
        }
        return ft;
    }

    public static List<Debt> getTypicalDebts() {
        return new ArrayList<>(Arrays.asList(DUCK_RICE, TAXI, GROCERIES, LAPTOP, PHONE_BILLS, DOCTOR, TV));
    }
}
