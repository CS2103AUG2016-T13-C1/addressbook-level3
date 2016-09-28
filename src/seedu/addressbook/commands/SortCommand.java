package seedu.addressbook.commands;


/**
 * Clears the address book.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Sorts address book in alphabetical order.\n\t"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book has been sorted!";
    public static final String MESSAGE_DUPLICATE_PERSON = "Found duplicate records when sorting!";

    public SortCommand() {}


    @Override
    public CommandResult execute() {
        addressBook.sort();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
