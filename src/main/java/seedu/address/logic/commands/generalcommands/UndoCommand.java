package seedu.address.logic.commands.generalcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXPENSES;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Reverts the {@code model}'s address book to its previous state.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo success!";
    public static final String MESSAGE_FAILURE = "No more commands to undo!";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoFinanceTracker()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.undoFinanceTracker();
        model.updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}