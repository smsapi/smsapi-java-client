package pl.smsapi.api.action.phonebook;

public class PhonebookContactEdit extends AbstractContactControl<PhonebookContactEdit> {

    /**
     * Select contact phone number to edit.
     */
    public PhonebookContactEdit number(String number) {
        params.put("edit_contact", number);
        return this;
    }

    /**
     * Set new phone number.
     */
    public PhonebookContactEdit setNumber(String number) {
        params.put("new_number", number);
        return this;
    }
}
