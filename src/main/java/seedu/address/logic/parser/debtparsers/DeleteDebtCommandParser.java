package seedu.address.logic.parser.debtparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.debtcommands.DeleteDebtCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteDebtCommand object
 */
public class DeleteDebtCommandParser implements Parser<DeleteDebtCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteDebtCommand
     * and returns an DeleteDebtCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteDebtCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteDebtCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDebtCommand.MESSAGE_USAGE), pe);
        }
    }
}
