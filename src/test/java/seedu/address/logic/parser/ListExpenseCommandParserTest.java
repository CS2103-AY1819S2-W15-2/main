package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;
import seedu.address.logic.commands.expensecommands.ListExpenseCommand;
import seedu.address.logic.parser.expenseparsers.ListExpenseCommandParser;
import seedu.address.model.attributes.View;

public class ListExpenseCommandParserTest {

    private ListExpenseCommandParser parser = new ListExpenseCommandParser();

    @Test
    public void parse_validArgs_returnsListExpenseCommand() {
        assertParseSuccess(parser, " v/all", new ListExpenseCommand(View.ALL));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " alla", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListExpenseCommand.MESSAGE_USAGE));
    }
}
