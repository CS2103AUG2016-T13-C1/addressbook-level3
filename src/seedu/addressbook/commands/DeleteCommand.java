package seedu.addressbook.commands;

import java.util.ArrayList;

import seedu.addressbook.common.Messages;
import seedu.addressbook.common.Range;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;


/**
 * Deletes a person identified using it's last displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Deletes the person identified by the index number used in the last person listing or by a range of index numbers.\n\t"
            + "Parameters: INDEX or RANGE\n\t"
            + "Example: " + COMMAND_WORD + " 1\n\t"
            + "Example: " + COMMAND_WORD + " 1 3";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_PEOPLE_SUCCESS = "Deleted People:\n %1$s";


    public DeleteCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
    }
    
    public DeleteCommand(Range targetVisibleRange) {
        super(targetVisibleRange);
    }


    @Override
    public CommandResult execute() {
        try {
            if (hasTargetRange()) {
                final ArrayList<ReadOnlyPerson> targets = getTargetPersonRange();
                for (ReadOnlyPerson target : targets) {
                    addressBook.removePerson(target);
                }
                return new CommandResult(String.format(MESSAGE_DELETE_PEOPLE_SUCCESS, getTargetString(targets)));
            } else {
                final ReadOnlyPerson target = getTargetPerson();
                addressBook.removePerson(target);
                return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, target));
            }
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        }
    }
    
    private String getTargetString(ArrayList<ReadOnlyPerson> targets) {
        StringBuilder sb = new StringBuilder();
        
        for (ReadOnlyPerson target : targets) {
            sb.append(target.toString() + "\n\n");
        }
        
        return sb.toString();
    }
}
