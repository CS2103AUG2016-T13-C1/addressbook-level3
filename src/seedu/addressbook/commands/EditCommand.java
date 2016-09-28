package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

public class EditCommand extends Command{
	
	public static final String COMMAND_WORD = "edit";
	
	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Edit the details of a specific person\n\t"
            + "Parameters: INDEX EDITTYPE/UPDATEDETAIL \n\t"
            + "Example: " + COMMAND_WORD
            + " 2 p/98765432 ";

	public static final String MESSAGE_SUCCESS = "Edited Detials: %1$s";
    public static final String MESSAGE_INVALID_PERSON = "New Details are Invalid!";
    
    private static final String NAME_EDIT = "n";
    private static final String PHONE_EDIT = "p";
    private static final String EMAIL_EDIT = "e";
    private static final String ADDRESS_EDIT = "a";
    
    private final String editType;
    private final String personDetail;
    
    public EditCommand(int targetVisibleIndex, String editType, String personDetail) {
		super(targetVisibleIndex);
		this.editType = editType;
		this.personDetail = personDetail;
	}
    
	@Override
	public CommandResult execute() {
		 try {
	            final ReadOnlyPerson target = getTargetPerson();
	            Person newPersonDetails = getNewPerson(target);
	            
	            addressBook.removePerson(target);
	            addressBook.addPerson(newPersonDetails);
	            return new CommandResult(String.format(MESSAGE_SUCCESS, target));

	        } catch (IndexOutOfBoundsException ie) {
	            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
	        } catch (PersonNotFoundException pnfe) {
	            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
	        } catch (IllegalValueException ive){
	        	return new CommandResult(MESSAGE_INVALID_PERSON);
	        }
	
	}

	private Person getNewPerson(ReadOnlyPerson target) throws IllegalValueException {
		if(editType.equals(NAME_EDIT)){
			Name newName = new Name(personDetail);
			return new Person(newName, target.getPhone(), target.getEmail(), target.getAddress(), target.getTags());
		} else if(editType.equals(PHONE_EDIT)) {
			Phone newPhone = new Phone(personDetail, target.getPhone().isPrivate());
			return new Person(target.getName(), newPhone, target.getEmail(), target.getAddress(), target.getTags());
		} else if(editType.equals(EMAIL_EDIT)) {
			Email newEmail = new Email(personDetail, target.getEmail().isPrivate());
			return new Person(target.getName(), target.getPhone(), newEmail, target.getAddress(), target.getTags());		
		} else if(editType.equals(ADDRESS_EDIT)) {
			Address newAddress = new Address(personDetail, target.getAddress().isPrivate());
			return new Person(target.getName(), target.getPhone(), target.getEmail(), newAddress, target.getTags());
		}
	 return new Person(target.getName(), target.getPhone(), target.getEmail(), target.getAddress(), target.getTags());
	}

}
